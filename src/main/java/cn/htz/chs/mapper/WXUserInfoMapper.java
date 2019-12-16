package cn.htz.chs.mapper;

import cn.htz.chs.model.SysWxUserInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface WXUserInfoMapper extends BaseMapper<SysWxUserInfo> {
}
