package cn.htz.chs.controller;

import cn.htz.chs.common.result.CommonResult;
import cn.htz.chs.manager.XiaoEAccessTokenManager;
import cn.htz.chs.service.XiaoEGoodsService;
import cn.htz.chs.service.XiaoETongService;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@Slf4j
public class GetXETGoodsController {
    @Autowired
    XiaoEGoodsService xiaoEGoodsService;

    @GetMapping("get_xiaoe_goods_list")
    public CommonResult getXiaoEGoodsList(String goods_id, String goods_type, String resource_type, String last_id, int page_size) {
        String  accessToken = XiaoEAccessTokenManager.getInstance().getAccessToken();
        String[] resourceType = {"1", "2", "3", "6"};
        JSONObject params = new JSONObject();
        params.put("access_token", accessToken);
        JSONObject data = new JSONObject();
        data.put("goods_id", goods_id);
        data.put("goods_type", goods_type);
        data.put("resource_type", resourceType);
        data.put("last_id", last_id);
        data.put("page_size", page_size);
        params.put("data", data);
        //String paramsConv = params.toString().replaceAll("\"", "'");
        System.out.println("params:" + params.toString());
        try {
            String rs = xiaoEGoodsService.getXiaoEGoodsList(params.toString());
            return CommonResult.success("成功", rs);
        } catch (Exception e) {
        }
        return CommonResult.success("失败");
    }

    @GetMapping("get_xiaoe_goods_detail")
    public CommonResult getXiaoEGoodsDetail(String goods_id, String goods_type) {
        String  accessToken = XiaoEAccessTokenManager.getInstance().getAccessToken();
        JSONObject params = new JSONObject();
        params.put("access_token", accessToken);
        JSONObject data = new JSONObject();
        data.put("goods_id", goods_id);
        data.put("goods_type", goods_type);
        params.put("data", data);
        //String paramsConv = params.toString().replaceAll("\"", "'");
        System.out.println("params:" + params.toString());
        try {
            String rs = xiaoEGoodsService.getXiaoEGoodsDetail(params.toString());
            return CommonResult.success("成功", rs);
        } catch (Exception e) {
        }
        return CommonResult.success("失败");
    }
}
