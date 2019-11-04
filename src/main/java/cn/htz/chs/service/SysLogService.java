package cn.htz.chs.service;

import com.baomidou.mybatisplus.extension.service.IService;
import cn.htz.chs.common.page.PageRequest;
import cn.htz.chs.common.page.PageResult;
import cn.htz.chs.model.SysLog;

public interface SysLogService extends IService<SysLog> {

    PageResult findPage(PageRequest pageRequest);
}
