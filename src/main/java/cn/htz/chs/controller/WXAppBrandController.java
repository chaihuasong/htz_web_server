package cn.htz.chs.controller;

import cn.htz.chs.common.result.CommonResult;
import cn.htz.chs.manager.XiaoEAccessTokenManager;
import cn.htz.chs.service.WXAppBrandService;
import cn.htz.chs.utils.WXUtils;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@Slf4j
public class WXAppBrandController {
    @Autowired
    WXAppBrandService wxAppBrandService;

    @GetMapping("get_wx_userid")
    public CommonResult getWXUserId() {
        String  accessToken = XiaoEAccessTokenManager.getInstance().getAccessToken();
        JSONObject params = new JSONObject();
        params.put("access_token", accessToken);
        JSONObject data = new JSONObject();
        params.put("data", data);
        //String paramsConv = params.toString().replaceAll("\"", "'");
        System.out.println("params:" + params.toString());
        return CommonResult.success("失败");
    }
}
