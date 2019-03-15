package com.fbg.cn.rent_springboot;

import com.fbg.cn.rent_springboot.common.base.BaseServiceImpl;
import com.fbg.cn.rent_springboot.dao.mapper.UserMapper;
import com.fbg.cn.rent_springboot.dao.model.User;
import com.fbg.cn.rent_springboot.dao.model.UserExample;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestBaseService extends BaseServiceImpl<UserMapper,User,UserExample>{


    @Test
    public  void init(){
        TestBaseService testBaseService = new TestBaseService();
        testBaseService.initMapper();
        User user = testBaseService.selectByPrimaryKey(1);
        System.out.println(user);
    }
}
