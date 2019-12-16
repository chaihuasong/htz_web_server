package cn.htz.chs.controller;

import cn.htz.chs.common.result.CommonResult;
import cn.htz.chs.service.XiaoETongService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestController
@Slf4j
public class GetXETAccessTokenController {
    @Autowired
    XiaoETongService xiaoETongService;
    /**
     * 小鹅通证书获取
     * @param
     * @return signature
     * @throws IOException
     */
    @GetMapping("get_xiaoe_accesstoken")
    public CommonResult getAccessToken() {
        try {
            String rs = xiaoETongService.getAccessToken();
            return CommonResult.success("成功", rs);
        } catch (Exception e) {
        }
        return CommonResult.success("失败");
    }
}
