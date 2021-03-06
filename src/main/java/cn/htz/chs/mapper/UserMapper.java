package cn.htz.chs.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import cn.htz.chs.model.SysUser;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface UserMapper extends BaseMapper<SysUser> {
}
