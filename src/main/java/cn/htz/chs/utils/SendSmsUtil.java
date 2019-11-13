package cn.htz.chs.utils;

import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;

/**
 * Created by chaihuasong on 2019/10/31/031
 */
public class SendSmsUtil {
    private static final String ACCESSKEYID = "LTAI4FrrHXecXKEvG42JN8sH";
    private static final String ACCESSSECRET = "1lUdhA2kLU2wTE2bsZlB3rkpYgUYzS";

    public static void send(String telephone, String code) {
        DefaultProfile profile = DefaultProfile.getProfile("cn-hangzhou", ACCESSKEYID, ACCESSSECRET);
        IAcsClient client = new DefaultAcsClient(profile);

        CommonRequest request = new CommonRequest();
        request.setMethod(MethodType.POST);
        request.setDomain("dysmsapi.aliyuncs.com");
        request.setVersion("2017-05-25");
        request.setAction("SendSms");
        request.putQueryParameter("RegionId", "cn-hangzhou");
        request.putQueryParameter("PhoneNumbers", telephone);
        request.putQueryParameter("SignName", "黄庭禅");
        request.putQueryParameter("TemplateCode", "SMS_176521240");
        request.putQueryParameter("TemplateParam", "{\"code\":\"" + code + "\"}");
        try {
            CommonResponse response = client.getCommonResponse(request);
            System.out.println(response.getData());
        } catch (ServerException e) {
            e.printStackTrace();
        } catch (ClientException e) {
            e.printStackTrace();
        }
    }

    /**
     * 产生6位随机数(0000-999999)
     *
     * @return 6位随机数
     */
    public static String getSixRandomCode() {
        int resultCode = (int) (Math.random() * 999999);
        if (resultCode < 10) {
            return "00000" + resultCode;
        }
        if (resultCode < 100) {
            return "0000" + resultCode;
        }
        if (resultCode < 1000) {
            return "000" + resultCode;
        }
        if (resultCode < 10000) {
            return "00" + resultCode;
        }
        if (resultCode < 100000) {
            return "0" + resultCode;
        }

        return String.valueOf(resultCode);
    }
}