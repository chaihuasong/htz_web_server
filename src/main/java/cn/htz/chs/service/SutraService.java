package cn.htz.chs.service;

import cn.htz.chs.common.page.PageResult;
import cn.htz.chs.model.Sutra;
import cn.htz.chs.model.SutraItem;
import com.baomidou.mybatisplus.extension.service.IService;

public interface SutraService extends IService<Sutra> {
    /**
     * 获取指定 name 专辑名
     * @param name 专辑名
     * @return 返回专辑
     */
    Sutra findByName(String name);

    PageResult queryItems();

    void deleteByName(String name);
}
