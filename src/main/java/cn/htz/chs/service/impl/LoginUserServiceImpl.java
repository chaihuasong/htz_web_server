package cn.htz.chs.service.impl;

import cn.htz.chs.common.page.ColumnFilter;
import cn.htz.chs.common.page.PageRequest;
import cn.htz.chs.common.page.PageResult;
import cn.htz.chs.mapper.LoginUserMapper;
import cn.htz.chs.model.SysLoginUser;
import cn.htz.chs.service.LoginUserService;
import cn.htz.chs.service.MenuService;
import cn.htz.chs.utils.PasswordUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class LoginUserServiceImpl extends ServiceImpl<LoginUserMapper, SysLoginUser> implements LoginUserService {

    @Autowired
    private MenuService menuService;

    @Override
    public boolean save(SysLoginUser record) {
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
    public SysLoginUser findById(Long userId) {
        LambdaQueryWrapper<SysLoginUser> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(SysLoginUser::getId, userId);
        return baseMapper.selectOne(wrapper);
    }

    @Override
    public SysLoginUser findByName(String userName) {
        LambdaQueryWrapper<SysLoginUser> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(SysLoginUser::getUserName, userName);
        return baseMapper.selectOne(wrapper);
    }

    @Override
    public void updateLoginTime(SysLoginUser user) {
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
    public void createUser(SysLoginUser user) {
        user.setCreateTime(new Date());
        PasswordUtil.encryptPassword(user);
        save(user);
    }

    @Override
    public Set<String> findPermissions(String userName) {
        SysLoginUser user = findByName(userName);
        if (user != null) {
            return menuService.findPermsByUserId(user.getId());
        }
        return new HashSet<>();
    }

    public int delete(List<SysLoginUser> users) {
        for (SysLoginUser user : users) {
            removeById(user.getId());
        }
        return 1;
    }

    @Override
    public PageResult findPage(PageRequest pageRequest) {
        PageResult pageResult = null;
        String name = getColumnFilterValue(pageRequest, "name");
        String email = getColumnFilterValue(pageRequest, "email");
        int pageNum = pageRequest.getPageNum();
        int pageSize = pageRequest.getPageSize();
        Page<SysLoginUser> page = new Page<>(pageNum, pageSize);
        LambdaQueryWrapper<SysLoginUser> queryWrapper = new LambdaQueryWrapper<>();
        if(!StringUtils.isEmpty(name)) {
            queryWrapper.eq(SysLoginUser::getUserName, name);
            if(!StringUtils.isEmpty(email)) {
                queryWrapper.eq(SysLoginUser::getEmail, email);
            }
        }
        IPage<SysLoginUser> result = baseMapper.selectPage(page, queryWrapper);
        pageResult = new PageResult(result);
        return pageResult;
    }
}
