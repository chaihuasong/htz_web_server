package cn.htz.chs.service;

import cn.htz.chs.common.page.PageResult;
import cn.htz.chs.model.Sutra;
import cn.htz.chs.model.SutraItem;
import com.baomidou.mybatisplus.extension.service.IService;

public interface SutraItemService extends IService<SutraItem> {
    /**
     * 获取指定 name 专辑名
     * @param title 专辑名
     * @return 返回专辑
     */
    SutraItem findByTitle(String title);

    PageResult queryItems();

    void deleteByTitle(String title);
}
