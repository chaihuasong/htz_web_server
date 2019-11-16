package cn.htz.chs.model;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("sutra_items")
public class SutraItem extends BaseModel {
    private String sutraId;
    private String title;
    private String description;
    private String original;
    private String audioId;
    private String lyricId;
    private int lesson;
    private long playedCount;
    private long duration;
    private String hash;
}
