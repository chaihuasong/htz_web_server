package cn.htz.chs.service;

import cn.htz.chs.model.SysGKInfo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

public interface GKInfoService extends IService<SysGKInfo> {
    SysGKInfo getByUnionId(String unionid);

    SysGKInfo getByUnionIdAndDate(String unionid, String date);

    void update(SysGKInfo entity);

    List<SysGKInfo> getMonthGKInfo(String unionid, String month);
}
