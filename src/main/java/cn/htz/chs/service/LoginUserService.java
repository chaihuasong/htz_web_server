package cn.htz.chs.service;


import cn.htz.chs.common.page.PageRequest;
import cn.htz.chs.common.page.PageResult;
import cn.htz.chs.model.LoginUser;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Set;

public interface LoginUserService extends IService<LoginUser> {

    int delete(List<LoginUser> users);

    /**
     * 获取指定用户ID对应的用户账户信息
     * @param userId 用户ID
     * @return 返回用户账户信息
     */
    LoginUser findById(Long userId);

    /**
     * 获取指定 userName 对应的用户账户信息
     * @param userName 用户名
     * @return 返回用户账户信息
     */
    LoginUser findByName(String userName);


    void updateLoginTime(LoginUser user);

    /**
     * 添加一条用户账户信息
     * @param user 需要添加的用户账户信息
     */
    void createUser(LoginUser user);

    /**
     * 查找用户的菜单权限标识集合
     * @param userName
     * @return
     */
    Set<String> findPermissions(String userName);

}
