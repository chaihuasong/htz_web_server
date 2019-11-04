package cn.htz.chs.controller;

import cn.htz.chs.annotation.Log;
import cn.htz.chs.common.page.PageRequest;
import cn.htz.chs.common.result.CommonResult;
import cn.htz.chs.model.SysDict;
import cn.htz.chs.service.DictService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("dict")
public class DictController {

    @Autowired
    private DictService dictService;

    @Log("新增字典")
    @RequiresPermissions({"sys:dict:add", "sys:dict:edit"})
    @PostMapping(value="/save")
    public CommonResult save(@RequestBody SysDict record) {
        return CommonResult.success(dictService.saveOrUpdate(record));
    }

    @Log("删除字典")
    @RequiresPermissions("sys:dict:delete")
    @PostMapping(value="/delete")
    public CommonResult delete(@RequestBody List<SysDict> records) {
        dictService.delete(records);
        return CommonResult.success(1);
    }

    @RequiresPermissions("sys:dict:view")
    @PostMapping(value="/findPage")
    public CommonResult findPage(@RequestBody PageRequest pageRequest) {
        return CommonResult.success(dictService.findPage(pageRequest));
    }

    @RequiresPermissions("sys:dict:view")
    @GetMapping(value="/findByLable")
    public CommonResult findByLable(@RequestParam String lable) {
        return CommonResult.success(dictService.findByLable(lable));
    }
}
