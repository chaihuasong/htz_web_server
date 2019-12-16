package cn.htz.chs.service;

import cn.htz.chs.utils.HttpRequest;
import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class XiaoEGoodsService {
    private static final String RESULT_OK = "ok";

    /**
     * 小鹅通商品
     * @return
     * @throws IOException
     */
    public String getXiaoEGoodsList(String param) {
        String url = "https://api.xiaoe-tech.com/xe.goods.relation.get/3.0.0";
        String resultStr = HttpRequest.sendPostJson(url, param);
        JSONObject object = JSONObject.parseObject(resultStr);
        System.out.println("getXiaoEGoods param:" + param);
        System.out.println("getXiaoEGoods:" + object.toString());
        if (object != null && object.containsKey("data")) {
            System.out.println("getXiaoEGoods:" + object.getString("data"));
            return object.getString("data");
        } else {
            System.out.println("getXiaoEGoods return null!");
            return null;
        }
    }

    /**
     * 小鹅通商品详情
     * @return
     * @throws IOException
     */
    public String getXiaoEGoodsDetail(String param) {
        String url = "https://api.xiaoe-tech.com/xe.goods.detail.get/3.0.0";
        String resultStr = HttpRequest.sendPostJson(url, param);
        JSONObject object = JSONObject.parseObject(resultStr);
        System.out.println("getXiaoEGoodsDetail param:" + param);
        System.out.println("getXiaoEGoodsDetail:" + object.toString());
        if (object != null && object.containsKey("data")) {
            System.out.println("getXiaoEGoodsDetail:" + object.getString("data"));
            return object.getString("data");
        } else {
            System.out.println("getXiaoEGoodsDetail return null!");
            return null;
        }
    }

}
