package cn.htz.chs.service.impl;

import cn.htz.chs.mapper.WXUserInfoMapper;
import cn.htz.chs.model.SysWxUserInfo;
import cn.htz.chs.service.WXUserInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class WXUserInfoServiceImpl extends ServiceImpl<WXUserInfoMapper, SysWxUserInfo> implements WXUserInfoService {
}
