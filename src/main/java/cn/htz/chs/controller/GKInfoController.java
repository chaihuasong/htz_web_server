package cn.htz.chs.controller;

import cn.htz.chs.common.result.CommonResult;
import cn.htz.chs.model.SysGKInfo;
import cn.htz.chs.service.GKInfoService;
import cn.htz.chs.utils.DateUtil;
import cn.htz.chs.utils.WXUtils;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

@RestController
@Slf4j
public class GKInfoController {
    @Autowired
    GKInfoService gkInfoService;

    @PostMapping("save_gk_info")
    public CommonResult saveGKInfo(HttpServletRequest request) {
        JSONObject jsonObject = new JSONObject(WXUtils.getRequestPayload(request));
        //String paramsConv = params.toString().replaceAll("\"", "'");
        System.out.println("params:" + jsonObject.toString());
        SysGKInfo entity = new SysGKInfo();
        String unionid = jsonObject.getString("unionid");
        String date = jsonObject.getString("date");
        entity.setUnionid(unionid);
        entity.setDate(date);
        entity.setZaoqi(jsonObject.getString("zaoqi"));
        entity.setJingzuo(jsonObject.getString("jingzuo"));
        entity.setZhanzhuang(jsonObject.getString("zhanzhuang"));
        entity.setQifenbao(jsonObject.getString("qifenbao"));
        entity.setYundong(jsonObject.getString("yundong"));
        entity.setShanben(jsonObject.getString("shanben"));
        entity.setJingdian(jsonObject.getString("jingdian"));
        entity.setZaoshui(jsonObject.getString("zaoshui"));
        entity.setShare(jsonObject.getString("share"));
        entity.setCreateTime(new Date());
        entity.setLastUpdateTime(new Date());
        System.out.println("entity:" + entity.toString());
        SysGKInfo res = gkInfoService.getByUnionIdAndDate(unionid, date);
        if (res != null) {
            gkInfoService.update(entity);
        } else {
            gkInfoService.save(entity);
        }
        return CommonResult.success("成功");
    }

    @GetMapping("get_gk_info")
    public CommonResult getGKInfo(String unionid, String date) {
        System.out.println("getGKInfo unionid:" + unionid + " date:" + date);
        SysGKInfo entity = gkInfoService.getByUnionIdAndDate(unionid, date);
        return CommonResult.success("成功", JSON.toJSONString(entity));
    }

    @GetMapping("get_month_gk_info")
    public CommonResult getMonthGKInfo(String unionid, String month) {
        System.out.println("getMonthGKInfo unionid:" + unionid + " month:" + month);
        List<SysGKInfo> entity = gkInfoService.getMonthGKInfo(unionid, month);
        return CommonResult.success("成功", JSON.toJSONString(entity));
    }
}
