package cn.htz.chs.controller;

import cn.htz.chs.common.result.CommonResult;
import cn.htz.chs.service.WechatService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestController
@Slf4j
public class GetXMLYAccessTokenController {
    @Autowired
    WechatService wechatService;
    /**
     * 微信分享证书获取
     * @param
     * @return signature
     * @throws IOException
     */
    @GetMapping("get_xmly_accesstoken")
    public CommonResult createSignature(String code, String state, String device_id) {
        try {
            String rs = "code:" + code + " state:" + state + " device_id:" + device_id;
            Map<String, Object> map = new HashMap<>();
            map.put("token", rs);
            return CommonResult.success("成功", rs);
        } catch (Exception e) {
        }
        return CommonResult.success("失败");
    }
}
