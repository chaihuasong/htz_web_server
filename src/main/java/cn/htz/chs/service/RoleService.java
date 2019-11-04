package cn.htz.chs.service;

import com.baomidou.mybatisplus.extension.service.IService;
import cn.htz.chs.common.page.PageRequest;
import cn.htz.chs.common.page.PageResult;
import cn.htz.chs.model.SysMenu;
import cn.htz.chs.model.SysRole;
import cn.htz.chs.model.SysRoleMenu;

import java.util.List;
import java.util.Set;

public interface RoleService extends IService<SysRole> {

    Set<String> findRoleByUserId(long userId);

    void delete(List<SysRole> roles);

    PageResult findPage(PageRequest pageRequest);

    List<SysMenu> findRoleMenus(long roleId);

    void saveRoleMenus(List<SysRoleMenu> sysRoleMenus);

    List<SysRole> findByName(String name);

}
