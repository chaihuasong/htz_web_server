package cn.htz.chs.service;

import com.baomidou.mybatisplus.extension.service.IService;
import cn.htz.chs.common.page.PageRequest;
import cn.htz.chs.common.page.PageResult;
import cn.htz.chs.model.SysDept;

import java.util.List;

public interface DeptService extends IService<SysDept> {

    List<SysDept> findTree(String name);

    PageResult findPage(PageRequest pageRequest);

    int delete(List<SysDept> records);
}
