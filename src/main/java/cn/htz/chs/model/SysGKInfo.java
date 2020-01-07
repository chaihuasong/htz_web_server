package cn.htz.chs.model;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
@TableName("sys_gk_info")
public class SysGKInfo extends WXBaseModel {
    private String unionid;
    private String date;
    private String zaoqi;
    private String jingzuo;
    private String zhanzhuang;
    private String qifenbao;
    private String yundong;
    private String shanben;
    private String jingdian;
    private String zaoshui;
    private String share;
}
