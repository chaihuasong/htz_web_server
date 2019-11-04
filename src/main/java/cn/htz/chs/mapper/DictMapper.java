package cn.htz.chs.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import cn.htz.chs.model.SysDict;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface DictMapper extends BaseMapper<SysDict> {
}
