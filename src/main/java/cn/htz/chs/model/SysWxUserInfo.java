package cn.htz.chs.model;

import lombok.Data;

@Data
public class SysWxUserInfo extends WXBaseModel {
    private String unionid;
    private String nickName;
    private String gender;
    private String avatarUrl;
    private String country;
    private String province;
    private String city;
    private String language;
    private String note;
}
