package cn.htz.chs.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class WXBaseModel {
    // 数据库自增id
    @TableId(value = "id",type = IdType.AUTO)
    private Long id;

    private Date createTime;

    private Date lastUpdateTime;
}