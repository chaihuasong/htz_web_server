package cn.htz.chs.service;

import cn.htz.chs.manager.XiaoEAccessTokenManager;
import cn.htz.chs.utils.HttpRequest;
import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class XiaoETongService {
    private int mCount = 0;

    public String getAccessToken() {
        String  accessToken = XiaoEAccessTokenManager.getInstance().getAccessToken();
        mCount++;
        System.out.println("XiaoETongService mCount------------->" + mCount);
        System.out.println("XiaoETongService accessToken------------->" + accessToken);
        return accessToken;
    }

}
