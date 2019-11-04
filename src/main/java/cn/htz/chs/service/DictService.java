package cn.htz.chs.service;

import com.baomidou.mybatisplus.extension.service.IService;
import cn.htz.chs.common.page.PageRequest;
import cn.htz.chs.common.page.PageResult;
import cn.htz.chs.model.SysDict;

import java.util.List;

public interface DictService extends IService<SysDict> {

    void delete(List<SysDict> records);

    PageResult findPage(PageRequest pageRequest);

    List<SysDict> findByLable(String lable);
}
