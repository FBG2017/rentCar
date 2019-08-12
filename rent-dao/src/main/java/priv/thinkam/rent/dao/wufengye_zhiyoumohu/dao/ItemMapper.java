package priv.thinkam.rent.dao.wufengye_zhiyoumohu.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import priv.thinkam.rent.dao.wufengye_zhiyoumohu.po.Item;
import priv.thinkam.rent.dao.wufengye_zhiyoumohu.po.ItemExample;

public interface ItemMapper {
    long countByExample(ItemExample example);

    int deleteByExample(ItemExample example);

    int deleteByPrimaryKey(Integer itemId);

    int insert(Item record);

    int insertSelective(Item record);

    List<Item> selectByExample(ItemExample example);

    Item selectByPrimaryKey(Integer itemId);

    int updateByExampleSelective(@Param("record") Item record, @Param("example") ItemExample example);

    int updateByExample(@Param("record") Item record, @Param("example") ItemExample example);

    int updateByPrimaryKeySelective(Item record);

    int updateByPrimaryKey(Item record);
}