package cn.htz.chs.controller;

import cn.htz.chs.common.result.CommonResult;
import cn.htz.chs.service.WXUserInfoService;
import cn.htz.chs.utils.WXUtils;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;

@RestController
@Slf4j
public class WXUserInfoController {
    @Autowired
    WXUserInfoService wxUserInfoService;

    @PostMapping("save_wx_userinfo")
    public CommonResult saveWXUserInfo(HttpServletRequest request) {
        JSONObject jsonObject = new JSONObject(WXUtils.getRequestPayload(request));
        //String paramsConv = params.toString().replaceAll("\"", "'");
        System.out.println("params:" + jsonObject.toString());
        //wxUserInfoService.save(userInfo);
        return CommonResult.success("失败");
    }
}
