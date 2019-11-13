package cn.htz.chs.controller;

import cn.htz.chs.common.result.CommonResult;
import cn.htz.chs.utils.SendSmsUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@Slf4j
public class SendSMSController {

    @PostMapping("sendsms")
    public CommonResult login(String telephone, HttpServletRequest request) {
        String code = SendSmsUtil.getSixRandomCode();
        SendSmsUtil.send(telephone, code);
        return CommonResult.success("success", code);
    }
}
