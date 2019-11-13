package cn.htz.chs.controller;

import cn.htz.chs.common.result.CommonResult;
import cn.htz.chs.jwt.JWTToken;
import cn.htz.chs.jwt.JWTUtil;
import cn.htz.chs.jwt.PermissionProperties;
import cn.htz.chs.model.SysLoginUser;
import cn.htz.chs.service.LoginUserService;
import cn.htz.chs.utils.DateUtil;
import cn.htz.chs.utils.PasswordUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;


@RestController
@Slf4j
public class LoginSmsController {
    @Autowired
    private PermissionProperties properties;
    @Autowired
    private LoginUserService loginUserService;

    @PostMapping("loginsms")
    public CommonResult loginsms(String telephone, String password) {
        String username = telephone;
        SysLoginUser loginUser = loginUserService.findByName(username);
        if (loginUser == null) {
            loginUser = new SysLoginUser();
            loginUser.setUserName(telephone);
            loginUser.setPassword(password);
            loginUser.setTelephone(telephone);
            loginUserService.createUser(loginUser);
            loginUser = loginUserService.findByName(username);
        }
        String passwdWithSalt = PasswordUtil.encryptPassword(password, loginUser.getSalt());
        loginUserService.updateLoginTime(loginUser);
        String token = JWTUtil.sign(username, passwdWithSalt);
        LocalDateTime expireTime = LocalDateTime.now().plusSeconds(properties.getJwtTimeOut());
        String expireTimeStr = DateUtil.formatFullTime(expireTime);
        JWTToken jwtToken = new JWTToken(token, expireTimeStr);
        Map<String, Object> map = new HashMap<>();
        map.put("token", jwtToken.getToken());
        return CommonResult.success("登录成功", map);
    }
}
