package cn.htz.chs.service.impl;

import cn.htz.chs.mapper.GKInfoMapper;
import cn.htz.chs.model.SysGKInfo;
import cn.htz.chs.service.GKInfoService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GKInfoServiceImpl extends ServiceImpl<GKInfoMapper, SysGKInfo> implements GKInfoService {
    @Override
    public SysGKInfo getByUnionId(String unionid) {
        LambdaQueryWrapper<SysGKInfo> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(SysGKInfo::getUnionid, unionid);
        return baseMapper.selectOne(wrapper);
    }

    @Override
    public SysGKInfo getByUnionIdAndDate(String unionid, String date) {
        LambdaQueryWrapper<SysGKInfo> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(SysGKInfo::getUnionid, unionid);
        wrapper.eq(SysGKInfo::getDate, date);
        return baseMapper.selectOne(wrapper);
    }

    @Override
    public void update(SysGKInfo entity) {
        LambdaQueryWrapper<SysGKInfo> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(SysGKInfo::getUnionid, entity.getUnionid());
        wrapper.eq(SysGKInfo::getDate, entity.getDate());
        baseMapper.update(entity, wrapper);
    }

    @Override
    public List<SysGKInfo> getMonthGKInfo(String unionid, String month) {
        LambdaQueryWrapper<SysGKInfo> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(SysGKInfo::getUnionid, unionid);
        wrapper.like(SysGKInfo::getDate, "-" + month + "-");
        return baseMapper.selectList(wrapper);
    }
}
