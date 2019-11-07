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
import cn.htz.chs.utils.SendSmsUtil;
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
public class SendSMSController {

    @PostMapping("sendsms")
    public CommonResult login(String telephone, HttpServletRequest request) {
        String code = SendSmsUtil.getSixRandomCode();
        //SendSmsUtil.send(telephone, code);
        return CommonResult.success("success", code);
    }
}
