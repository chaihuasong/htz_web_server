package cn.htz.chs.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.util.Date;

@Data
public class SysLoginUser extends BaseModel {
    private String userName;
    private String password;
    @JsonIgnore
    private String salt;
    private String nickName;
    private String headImgUrl;
    private String phone;
    private String telephone;
    private String email;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date birthday;
    private Integer sex;
    private Integer status;
}
