package cn.htz.chs.controller;

import cn.htz.chs.common.result.CommonResult;
import cn.htz.chs.manager.XiaoEAccessTokenManager;
import cn.htz.chs.model.SysLoginUser;
import cn.htz.chs.model.SysWxUserInfo;
import cn.htz.chs.service.WXAppBrandService;
import cn.htz.chs.service.WXUserInfoService;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@Slf4j
public class WXUserInfoController {
    @Autowired
    WXUserInfoService wxUserInfoService;

    @GetMapping("save_wx_userinfo")
    public CommonResult saveWXUserInfo(@RequestBody SysWxUserInfo userInfo) {
        //String paramsConv = params.toString().replaceAll("\"", "'");
        System.out.println("params:" + userInfo.toString());
        wxUserInfoService.save(userInfo);
        return CommonResult.success("失败");
    }
}
