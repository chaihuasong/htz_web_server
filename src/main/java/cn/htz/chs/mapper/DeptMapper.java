package cn.htz.chs.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import cn.htz.chs.model.SysDept;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface DeptMapper extends BaseMapper<SysDept> {
    /**
     * 递归删除部门树
     * @param deptId
     */
    void deleteDeptTree(int deptId);
}
