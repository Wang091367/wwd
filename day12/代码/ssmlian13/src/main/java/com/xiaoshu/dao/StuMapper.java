package com.xiaoshu.dao;

import com.xiaoshu.base.dao.BaseMapper;
import com.xiaoshu.entity.Stu;
import com.xiaoshu.entity.StuExample;
import com.xiaoshu.entity.StuVo;

import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface StuMapper extends BaseMapper<Stu> {
    long countByExample(StuExample example);

    int deleteByExample(StuExample example);

    List<Stu> selectByExample(StuExample example);

    int updateByExampleSelective(@Param("record") Stu record, @Param("example") StuExample example);

    int updateByExample(@Param("record") Stu record, @Param("example") StuExample example);

	List<StuVo> findStu(StuVo stuVo);

	List<StuVo> findEchartsStu();
}