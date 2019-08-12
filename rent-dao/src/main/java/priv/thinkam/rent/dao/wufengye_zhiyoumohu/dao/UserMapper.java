package priv.thinkam.rent.dao.wufengye_zhiyoumohu.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import priv.thinkam.rent.dao.wufengye_zhiyoumohu.po.User;
import priv.thinkam.rent.dao.wufengye_zhiyoumohu.po.UserExample;

public interface UserMapper {
    long countByExample(UserExample example);

    int deleteByExample(UserExample example);

    int deleteByPrimaryKey(Integer userId);

    int insert(User record);

    int insertSelective(User record);

    List<User> selectByExample(UserExample example);

    User selectByPrimaryKey(Integer userId);

    int updateByExampleSelective(@Param("record") User record, @Param("example") UserExample example);

    int updateByExample(@Param("record") User record, @Param("example") UserExample example);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
}