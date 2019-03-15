package com.fbg.cn.rent_springboot.dao.mapper;

import com.fbg.cn.rent_springboot.dao.model.Stuff;
import com.fbg.cn.rent_springboot.dao.model.StuffExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface StuffMapper {
    long countByExample(StuffExample example);

    int deleteByExample(StuffExample example);

    int deleteByPrimaryKey(Integer stuffId);

    int insert(Stuff record);

    int insertSelective(Stuff record);

    List<Stuff> selectByExample(StuffExample example);

    Stuff selectByPrimaryKey(Integer stuffId);

    int updateByExampleSelective(@Param("record") Stuff record, @Param("example") StuffExample example);

    int updateByExample(@Param("record") Stuff record, @Param("example") StuffExample example);

    int updateByPrimaryKeySelective(Stuff record);

    int updateByPrimaryKey(Stuff record);
}