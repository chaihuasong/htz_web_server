package cn.htz.chs.manager;

import cn.htz.chs.utils.HttpRequest;
import com.alibaba.fastjson.JSONObject;

import java.io.IOException;

public class XiaoEAccessTokenManager {
    private static final String XET_APPID = "appw8Gkxo2j3844";
    private static final String XET_CLIENT_ID = "xopjVyXj6HW6192";
    private static final String XET_SECRET_KEY = "LNoDWZIVRNK7yzkOGeE3mu5nCFjxgh5o";
    private String mAccessToken;
    private long mTime = 0;

    private static XiaoEAccessTokenManager mInstance;

    public static XiaoEAccessTokenManager getInstance() {
        if (mInstance == null) {
            mInstance = new XiaoEAccessTokenManager();
        }
        return mInstance;
    }

    /**
     * 小鹅通全局票据 ---->>>> access_token
     * @return
     * @throws IOException
     */
    public String getAccessToken() {
        if ((System.currentTimeMillis() - mTime) < 3600000 && mAccessToken != null) {
            return mAccessToken;
        }
        String url = "https://api.xiaoe-tech.com/token";
        String param = "app_id="+ XET_APPID + "&client_id=" + XET_CLIENT_ID + "&secret_key=" + XET_SECRET_KEY + "&grant_type=client_credential";
        String resultStr = HttpRequest.sendGet(url, param);
        JSONObject object = JSONObject.parseObject(resultStr);
        JSONObject data = object.getJSONObject("data");
        mTime = System.currentTimeMillis();
        return data.getString("access_token");
    }
}
