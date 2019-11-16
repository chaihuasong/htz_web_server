package cn.htz.chs.service.impl;

import cn.htz.chs.common.page.ColumnFilter;
import cn.htz.chs.common.page.PageRequest;
import cn.htz.chs.common.page.PageResult;
import cn.htz.chs.mapper.SutraMapper;
import cn.htz.chs.model.Sutra;
import cn.htz.chs.service.SutraService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class SutraServiceImpl extends ServiceImpl<SutraMapper, Sutra> implements SutraService {
    @Override
    public Sutra findByName(String name) {
        LambdaQueryWrapper<Sutra> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Sutra::getName, name);
        return baseMapper.selectOne(wrapper);
    }

    @Override
    public PageResult queryItems() {
        PageResult pageResult = null;
        LambdaQueryWrapper<Sutra> queryWrapper = new LambdaQueryWrapper<>();
        Page<Sutra> page = new Page<>();

        IPage result = baseMapper.selectPage(page, queryWrapper);;
        pageResult = new PageResult(result);
        return pageResult;
    }

    @Override
    public void deleteByName(String name) {
        LambdaQueryWrapper<Sutra> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Sutra::getName, name);
        baseMapper.delete(wrapper);
    }

    /**
     * 获取过滤字段的值
     *
     * @param filterName
     * @return
     */
    public String getColumnFilterValue(PageRequest pageRequest, String filterName) {
        String value = null;
        ColumnFilter columnFilter = pageRequest.getColumnFilters().get(filterName);
        if (columnFilter != null) {
            value = columnFilter.getValue();
        }
        return value;
    }
}
