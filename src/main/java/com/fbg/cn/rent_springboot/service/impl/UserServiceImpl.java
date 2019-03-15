package com.fbg.cn.rent_springboot.service.impl;

import com.fbg.cn.rent_springboot.common.annotation.BaseServiceA;
import com.fbg.cn.rent_springboot.common.base.BaseServiceImpl;
import com.fbg.cn.rent_springboot.dao.mapper.UserMapper;
import com.fbg.cn.rent_springboot.dao.mapper.custom.UserExampleMapper;
import com.fbg.cn.rent_springboot.dao.model.User;
import com.fbg.cn.rent_springboot.dao.model.UserExample;
import com.fbg.cn.rent_springboot.dao.model.UserExample.Criteria;
import com.fbg.cn.rent_springboot.dao.model.wrap.PageQuery;
import com.fbg.cn.rent_springboot.dao.model.wrap.UserExampleCustomer;
import com.fbg.cn.rent_springboot.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
* UserService实现
* Created by thinkam on 19-03-14.
*/
@Service
@Transactional
@BaseServiceA
public class UserServiceImpl extends BaseServiceImpl<UserMapper, User, UserExample> implements UserService {

    private static Logger _log = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    UserMapper userMapper;

    @Autowired
    UserExampleMapper userExampleMapper;

    @Override
    public List<User> selectByLimit(String content, String order, int start,
                                    int pageSize) {
        UserExample userExample = new UserExample();
//		userExample.setOffset();
//		userExample.setLimit();
        //以下是模糊查询
        Criteria criteria = userExample.createCriteria();
        criteria.andUsernameLike("%"+content+"%");
        List<User> userList = userMapper.selectByExample(userExample);
        return userList;
    }

    @Override
    public List<User> selectByFengYe(String content, String order, int start,
                                     int pageSize) {
        UserExampleCustomer userExampleCustomer = new UserExampleCustomer();
        UserExample.Criteria criteria = userExampleCustomer.createCriteria();
        if (content!=null) {
            criteria.andUsernameLike("%"+content+"%");
        }
        userExampleCustomer.setOffset(start);
        userExampleCustomer.setLimit(pageSize);
        List<User> users = userExampleMapper.selectByCustomerExample(userExampleCustomer);
        return users;
    }

    @Override
    public List<User> findUsers(PageQuery page) {
        UserExample userExample = new UserExample();
        userExample.setOffset(page.getOffset());
        userExample.setLimit(page.getLimit());
        UserExample.Criteria criteria = userExample.createCriteria();
        if (page.getContent()!=null) {
            criteria.andUsernameLike("%"+page.getContent()+"%");
        }
        List<User> users = userMapper.selectByExample(userExample);
        return users;
    }


}