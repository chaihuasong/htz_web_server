package cn.htz.chs.model;

import lombok.Data;

@Data
public class Files extends BaseModel {
    private long refCount;
    private long fileSize;
    private String mime;
    private String sha1Hash;
    private String url;
    private int status;
}
