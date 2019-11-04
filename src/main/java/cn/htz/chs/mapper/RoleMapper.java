package cn.htz.chs.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import cn.htz.chs.model.SysRole;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
@Mapper
public interface RoleMapper extends BaseMapper<SysRole> {

    Set<String> findRoleByUserId(long userId);

}
