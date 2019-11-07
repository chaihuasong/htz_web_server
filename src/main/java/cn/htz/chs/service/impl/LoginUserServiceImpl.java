package cn.htz.chs.service.impl;

import cn.htz.chs.common.page.ColumnFilter;
import cn.htz.chs.common.page.PageRequest;
import cn.htz.chs.mapper.LoginUserMapper;
import cn.htz.chs.model.LoginUser;
import cn.htz.chs.service.LoginUserService;
import cn.htz.chs.service.MenuService;
import cn.htz.chs.utils.PasswordUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class LoginUserServiceImpl extends ServiceImpl<LoginUserMapper, LoginUser> implements LoginUserService {

    @Autowired
    private MenuService menuService;

    @Override
    public boolean save(LoginUser record) {
        Long id = null;
        if (record.getId() == null || record.getId() == 0) {
            // 新增用户
            super.save(record);
            id = record.getId();
        } else {
            // 更新用户信息
            updateById(record);
        }
        return true;
    }

    @Override
    public LoginUser findById(Long userId) {
        LambdaQueryWrapper<LoginUser> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(LoginUser::getId, userId);
        return baseMapper.selectOne(wrapper);
    }

    @Override
    public LoginUser findByName(String userName) {
        LambdaQueryWrapper<LoginUser> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(LoginUser::getUserName, userName);
        return baseMapper.selectOne(wrapper);
    }

    @Override
    public void updateLoginTime(LoginUser user) {
        user.setLastUpdateTime(new Date());
        updateById(user);
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

    @Override
    public void createUser(LoginUser user) {
        user.setCreateTime(new Date());
        PasswordUtil.encryptPassword(user);
        save(user);
    }

    @Override
    public Set<String> findPermissions(String userName) {
        LoginUser user = findByName(userName);
        if (user != null) {
            return menuService.findPermsByUserId(user.getId());
        }
        return new HashSet<>();
    }

    public int delete(List<LoginUser> users) {
        for (LoginUser user : users) {
            removeById(user.getId());
        }
        return 1;
    }

}
