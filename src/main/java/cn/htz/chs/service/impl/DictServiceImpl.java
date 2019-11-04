package cn.htz.chs.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.htz.chs.common.page.ColumnFilter;
import cn.htz.chs.common.page.PageRequest;
import cn.htz.chs.common.page.PageResult;
import cn.htz.chs.mapper.DictMapper;
import cn.htz.chs.model.SysDict;
import cn.htz.chs.service.DictService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class DictServiceImpl extends ServiceImpl<DictMapper, SysDict> implements DictService {
    @Override
    public void delete(List<SysDict> records) {
        for (SysDict record : records) {
            removeById(record.getId());
        }
    }

    @Override
    public PageResult findPage(PageRequest pageRequest) {
        ColumnFilter columnFilter = pageRequest.getColumnFilters().get("label");
        LambdaQueryWrapper<SysDict> wrapper = new LambdaQueryWrapper<>();
        if (columnFilter != null && !StringUtils.isEmpty(columnFilter.getValue())) {
            wrapper.eq(SysDict::getLabel, columnFilter.getValue());
        }
        int pageNum = pageRequest.getPageNum();
        int pageSize = pageRequest.getPageSize();
        Page<SysDict> page = new Page<>(pageNum, pageSize);
        IPage<SysDict> result = baseMapper.selectPage(page, wrapper);
        PageResult pageResult = new PageResult(result);
        return pageResult;
    }

    @Override
    public List<SysDict> findByLable(String lable) {
        LambdaQueryWrapper<SysDict> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(SysDict::getLabel, lable);
        return baseMapper.selectList(wrapper);
    }
}
