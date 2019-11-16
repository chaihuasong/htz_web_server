package cn.htz.chs.model;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("sutras")
public class Sutra extends BaseModel {
    private String name;
    private String cover;
    private String description;
    private long playedCount;
    private int itemTotal;
}
