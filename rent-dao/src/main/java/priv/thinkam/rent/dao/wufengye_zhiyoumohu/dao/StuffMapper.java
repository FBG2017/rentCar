package priv.thinkam.rent.dao.wufengye_zhiyoumohu.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import priv.thinkam.rent.dao.wufengye_zhiyoumohu.po.Stuff;
import priv.thinkam.rent.dao.wufengye_zhiyoumohu.po.StuffExample;

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