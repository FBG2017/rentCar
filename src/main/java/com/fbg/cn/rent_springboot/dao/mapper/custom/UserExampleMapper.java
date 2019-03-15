package com.fbg.cn.rent_springboot.dao.mapper.custom;


import com.fbg.cn.rent_springboot.dao.model.User;
import com.fbg.cn.rent_springboot.dao.model.wrap.UserExampleCustomer;

import java.util.List;

public interface UserExampleMapper {
	List<User> selectByCustomerExample(UserExampleCustomer userExampleCustomer);
}
