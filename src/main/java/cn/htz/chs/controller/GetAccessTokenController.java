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
public class GetAccessTokenController {
    @Autowired
    WechatService wechatService;
    /**
     * 微信分享证书获取
     * @param
     * @return signature
     * @throws IOException
     */
    @GetMapping("getaccesstoken")
    public CommonResult createSignature(String url) {
        try {
            String rs = wechatService.createSignature(url);
            Map<String, Object> map = new HashMap<>();
            map.put("token", rs);
            return CommonResult.success("成功", rs);
        } catch (Exception e) {
        }
        return CommonResult.success("失败");
    }
    /**
     * 微信纸牌小游戏用户证书获取
     * @param
     * @return signature
     * @throws IOException
     */
    @GetMapping("get_zhipai_unionid")
    public CommonResult get_zhipai_unionid() {
        try {
            String rs = wechatService.getUnionId();
            Map<String, Object> map = new HashMap<>();
            map.put("token", rs);
            return CommonResult.success("成功", rs);
        } catch (Exception e) {
        }
        return CommonResult.success("失败");
    }
}
