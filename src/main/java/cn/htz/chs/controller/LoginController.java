package cn.htz.chs.controller;

import cn.htz.chs.annotation.Log;
import cn.htz.chs.common.result.CommonResult;
import cn.htz.chs.jwt.JWTToken;
import cn.htz.chs.jwt.JWTUtil;
import cn.htz.chs.jwt.PermissionProperties;
import cn.htz.chs.model.SysUser;
import cn.htz.chs.service.UserService;
import cn.htz.chs.utils.DateUtil;
import cn.htz.chs.utils.PasswordUtil;
import cn.htz.chs.vo.LoginBean;
import com.google.code.kaptcha.Constants;
import com.google.code.kaptcha.Producer;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestController
@Slf4j
public class LoginController {
    @Autowired
    private Producer producer;
    @Autowired
    private PermissionProperties properties;
    @Autowired
    private UserService userService;

    @GetMapping("captcha.jpg")
    public void captcha(HttpServletResponse response, HttpServletRequest request) throws ServletException, IOException {
        response.setHeader("Cache-Control", "no-store, no-cache");
        response.setContentType("image/jpeg");
        // 生成文字验证码
        String text = producer.createText();
        // 生成图片验证码
        BufferedImage image = producer.createImage(text);
        // 保存到验证码到 session
        request.getSession().setAttribute(Constants.KAPTCHA_SESSION_KEY, text);
        ServletOutputStream out = response.getOutputStream();
        ImageIO.write(image, "jpg", out);
        out.close();
    }

    @PostMapping("login")
    public CommonResult login(@RequestBody LoginBean loginBean, HttpServletRequest request) {
        String username = loginBean.getName();
        String password = loginBean.getPassword();
        String captcha = loginBean.getCaptcha();
        // 从session中获取之前保存的验证码跟前台传来的验证码进行匹配
        Object kaptcha = request.getSession().getAttribute(Constants.KAPTCHA_SESSION_KEY);
        if (kaptcha == null) {
            return CommonResult.error("验证码已失效");
        }
        if (!captcha.equals(kaptcha)) {
            return CommonResult.error("验证码不正确");
        }
        SysUser user = userService.findByName(username);
        if (user == null) {
            return CommonResult.error("用户名不存在");
        }
        String passwdWithSalt = PasswordUtil.encryptPassword(password, user.getSalt());
        if (!StringUtils.equals(user.getPassword(), passwdWithSalt)) {
            return CommonResult.error("密码错误");
        }
        userService.updateLoginTime(user);
        String token = JWTUtil.sign(username, passwdWithSalt);
        LocalDateTime expireTime = LocalDateTime.now().plusSeconds(properties.getJwtTimeOut());
        String expireTimeStr = DateUtil.formatFullTime(expireTime);
        JWTToken jwtToken = new JWTToken(token, expireTimeStr);
        Map<String, Object> map = new HashMap<>();
        map.put("token", jwtToken.getToken());
        return CommonResult.success("登录成功", map);
    }

    @Log("登出")
    @GetMapping("/logout")
    public CommonResult logout() {
        return CommonResult.success("登出成功");
    }
}
