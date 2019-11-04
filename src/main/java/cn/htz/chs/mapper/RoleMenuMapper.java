package cn.htz.chs.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import cn.htz.chs.model.SysRoleMenu;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface RoleMenuMapper extends BaseMapper<SysRoleMenu> {
}
