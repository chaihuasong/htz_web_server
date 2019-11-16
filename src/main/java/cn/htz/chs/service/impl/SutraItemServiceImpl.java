package cn.htz.chs.service.impl;

import cn.htz.chs.common.page.PageResult;
import cn.htz.chs.mapper.SutraItemsMapper;
import cn.htz.chs.model.SutraItem;
import cn.htz.chs.service.SutraItemService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class SutraItemServiceImpl extends ServiceImpl<SutraItemsMapper, SutraItem> implements SutraItemService {
    @Override
    public SutraItem findByTitle(String title) {
        LambdaQueryWrapper<SutraItem> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(SutraItem::getTitle, title);
        return baseMapper.selectOne(wrapper);
    }

    @Override
    public PageResult queryItems() {
        PageResult pageResult = null;
        LambdaQueryWrapper<SutraItem> queryWrapper = new LambdaQueryWrapper<>();
        Page<SutraItem> page = new Page<>();

        IPage result = baseMapper.selectPage(page, queryWrapper);;
        pageResult = new PageResult(result);
        return pageResult;
    }

    @Override
    public void deleteByTitle(String title) {
        LambdaQueryWrapper<SutraItem> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(SutraItem::getTitle, title);
        baseMapper.delete(wrapper);
    }
}
