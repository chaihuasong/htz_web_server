package cn.htz.chs.controller;

import cn.htz.chs.annotation.Log;
import cn.htz.chs.common.page.PageRequest;
import cn.htz.chs.common.result.CommonResult;
import cn.htz.chs.model.SysLoginUser;
import cn.htz.chs.model.SysUser;
import cn.htz.chs.service.LoginUserService;
import cn.htz.chs.service.UserService;
import cn.htz.chs.utils.PasswordUtil;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("loginUser")
@CrossOrigin(origins = "*",allowCredentials="true",allowedHeaders = "",methods = {})
public class LoginUserController {

    @Autowired
    private LoginUserService loginUserService;

    @Log("新增/修改用户")
    @RequiresPermissions({"sys:loginUser:add", "sys:loginUser:edit"})
    @PostMapping(value="/save")
    public CommonResult save(@RequestBody SysLoginUser record) {
        SysLoginUser loginUser = loginUserService.findById(record.getId());
        if(record.getPassword() != null) {
            if(loginUser == null) {
                // 新增用户
                if(loginUserService.findByName(record.getUserName()) != null) {
                    return CommonResult.error("用户名已存在!");
                }
                PasswordUtil.encryptPassword(record);
            } else {
                // 修改用户, 且修改了密码
                if(!record.getPassword().equals(loginUser.getPassword())) {
                    PasswordUtil.encryptPassword(record);
                }
            }
        }
        return CommonResult.success(loginUserService.save(record));
    }

    @Log("删除用户")
    @RequiresPermissions("sys:loginUser:delete")
    @PostMapping(value="/delete")
    public CommonResult delete(@RequestBody List<SysLoginUser> records) {
        return CommonResult.success(loginUserService.delete(records));
    }

    @RequiresPermissions("sys:loginUser:view")
    @GetMapping(value="/findByName")
    public CommonResult findByUserName(@RequestParam String name) {
        return CommonResult.success(loginUserService.findByName(name));
    }

    @RequiresPermissions("sys:loginUser:view")
    @GetMapping(value="/findPermissions")
    public CommonResult findPermissions(@RequestParam String name) {
        return CommonResult.success(loginUserService.findPermissions(name));
    }

    @Log("查看用户")
    @RequiresPermissions("sys:loginUser:view")
    @PostMapping(value="/findPage")
    public CommonResult findPage(@RequestBody PageRequest pageRequest) {
        return CommonResult.success(loginUserService.findPage(pageRequest));
    }
}
