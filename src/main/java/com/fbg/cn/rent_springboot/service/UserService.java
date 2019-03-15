package com.fbg.cn.rent_springboot.service;

import com.fbg.cn.rent_springboot.common.base.BaseService;
import com.fbg.cn.rent_springboot.dao.model.User;
import com.fbg.cn.rent_springboot.dao.model.UserExample;
import com.fbg.cn.rent_springboot.dao.model.wrap.PageQuery;

import java.util.List;

/**
* UserService接口
* Created by thinkam on 19-03-14.
*/
public interface UserService extends BaseService<User, UserExample> {
    //自己手写自定义 模糊查询
    List<User> selectByLimit(String content, String order, int start, int pageSize);

    List<User> selectByFengYe(String content, String order, int start,
                              int pageSize);

    List<User> findUsers(PageQuery page);

}