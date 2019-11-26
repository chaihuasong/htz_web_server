package cn.htz.chs.service;

import cn.htz.chs.utils.HttpRequest;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import jdk.nashorn.internal.parser.JSONParser;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.security.MessageDigest;
import java.util.Formatter;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class WechatService {
    private static final String WX_APPID = "wx2bc72b461e524e59";
    private static final String WX_APPSECRET = "6b361dbb01bf2192113c7c63da03e1ad";

    /**
     * 根据jsapi_ticket等参数进行SHA1加密
     * @param url 当前页面url
     */
    public String createSignature(String url) throws Exception {
        String nonceStr = create_nonce_str();
        String timestamp = create_timestamp();

        String signature = "jsapi_ticket=" + getJsapiTicket();
        signature += "&noncestr="+nonceStr;
        signature += "×tamp="+timestamp;
        signature += "&url="+url;
        try {
            MessageDigest crypt = MessageDigest.getInstance("SHA-1");
            crypt.reset();
            crypt.update(signature.getBytes("UTF-8"));
            signature = byteToHex(crypt.digest());
        } catch (Exception e) {
        }
        Map<String, String> map = new HashMap<String, String>();
        map.put("timestamp", timestamp);
        map.put("nonceStr", nonceStr);
        map.put("signature", signature);
        map.put("appid", "wx2bc72b461e524e59");
        return JSON.toJSONString(map, true);
    }
    private static String byteToHex(final byte[] hash) {
        Formatter formatter = new Formatter();
        for (byte b : hash) {
            formatter.format("%02x", b);
        }
        String result = formatter.toString();
        formatter.close();
        return result;
    }


    private static String create_nonce_str() {
        return UUID.randomUUID().toString();
    }


    private static String create_timestamp() {
        return Long.toString(System.currentTimeMillis() / 1000);
    }

    /**
     * jsapi_ticket是公众号用于调用微信JS接口的临时票据
     * @return
     * @throws IOException
     */
    public String getJsapiTicket() {
        String url = "https://api.weixin.qq.com/cgi-bin/ticket/getticket";
        String accessToken = getBaseAccessToken();
        System.out.println("accessToken------------->" + accessToken);
        String param = "access_token="+ accessToken +"&type=jsapi";
        String ticket = HttpRequest.sendGet(url, param);
        System.out.println("ticket------------->" + ticket);
        JSONObject object = JSONObject.parseObject(ticket);
        return object.getString("ticket");
        /*try {
            String value = redisService.get("WEIXIN_JS_API_TICKET");
            if (!StringUtils.isEmpty(value)) {
                return value;
            }else{
                synchronized (this) {
                    //缓存中没有、或已经失效
                    //获取全局的access_token，唯一票据
                    String accessToken = getBaseAccessToken();
                    String url = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token="+ accessToken +"&type=jsapi";
                    String rs = apiService.doGet(url);
                    JSONObject obj_content = JSONObject.parseObject(rs);
                    String jsapi_ticket = obj_content.getString("ticket");
                    Integer time = Integer.parseInt(obj_content.getString("expires_in").toString());
                    //写缓存
                    redisService.set("WEIXIN_JS_API_TICKET", jsapi_ticket, time - 3600);
                    return jsapi_ticket;
                }
            }
        } catch (Exception e) {
            LOGGER.error("Get js_api_ticket from redis is error:{}",e);
        }*/
        //return "27_PgTjzNdHZDeQM5cUrnoPkg5zflHWwNEPF0rGUIZ-WXbssYg2L5fcIVHMaWwIdhX-CgSAqbcqpNNc9jiQnHygE68tMHqn_VDDBZovhckwewdddf0Y9f2v3FR3IoKwg3jtDLGO9UMe7xLIBe0pHVBjAIALZF";
    }


    /**
     * 微信全局票据 ---->>>> access_token
     * @return
     * @throws IOException
     */
    public String getBaseAccessToken() {
        String url = "https://api.weixin.qq.com/cgi-bin/token";
        String param = "grant_type=client_credential&appid="+ WX_APPID + "&secret=" + WX_APPSECRET;
        String resultStr = HttpRequest.sendGet(url, param);
        JSONObject object = JSONObject.parseObject(resultStr);
        return object.getString("access_token");
        /*try {
            String value = redisService.get("WEIXIN_BASE_ACCESS_TOKEN");
            if (!StringUtils.isEmpty(value)) {
                return value;
            }else{
                synchronized (this) {
                    //缓存中没有、或已经失效
                    String url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid="+WX_APPID+"&secret="+ WX_APPSECRET;
                    String rs = apiService.doGet(url);

                    JSONObject obj_content = JSONObject.parseObject(rs);
                    String accessToken = obj_content.getString("access_token");
                    Integer time = Integer.parseInt(obj_content.getString("expires_in").toString());

                    //写缓存
                    redisService.set("WEIXIN_BASE_ACCESS_TOKEN", accessToken, time - 3600);
                    return accessToken;
                }
            }
        } catch (Exception e) {
        }*/
        //return "27_PgTjzNdHZDeQM5cUrnoPkg5zflHWwNEPF0rGUIZ-WXbssYg2L5fcIVHMaWwIdhX-CgSAqbcqpNNc9jiQnHygE68tMHqn_VDDBZovhckwewdddf0Y9f2v3FR3IoKwg3jtDLGO9UMe7xLIBe0pHVBjAIALZF";
    }

}
