package com.fbg.cn.rent_springboot.service.impl;

import com.fbg.cn.rent_springboot.dao.mapper.UserMapper;
import com.fbg.cn.rent_springboot.dao.model.User;
import com.fbg.cn.rent_springboot.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TestServiceImpl implements TestService {
    @Autowired
    private UserMapper userMapper;
    @Override
    public User findUserById(Integer id) {
        User user = userMapper.selectByPrimaryKey(id);
        return user;
    }
}
