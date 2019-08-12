package priv.thinkam.rent.service.impl;

import java.util.List;

import priv.thinkam.rent.common.annotation.BaseService;
import priv.thinkam.rent.common.base.BaseServiceImpl;
import priv.thinkam.rent.dao.mapper.UserMapper;
import priv.thinkam.rent.dao.mapper.custom.UserExampleMapper;
import priv.thinkam.rent.dao.model.User;
import priv.thinkam.rent.dao.model.UserExample;
import priv.thinkam.rent.dao.model.UserExample.Criteria;
import priv.thinkam.rent.dao.model.wrap.PageQuery;
import priv.thinkam.rent.dao.model.wrap.UserExampleCustomer;
import priv.thinkam.rent.service.UserService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
* UserService实现
* Created by thinkam on 17-12-19.
*/
@Service
@Transactional
@BaseService
@CacheConfig(cacheNames = "sysUser")//缓存
public class UserServiceImpl extends BaseServiceImpl<UserMapper, User, UserExample> implements UserService {

    private static Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

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
			Criteria criteria = userExampleCustomer.createCriteria();
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
		Criteria criteria = userExample.createCriteria();
		if (page.getContent()!=null) {
			criteria.andUsernameLike("%"+page.getContent()+"%");
		}
		 List<User> users = userMapper.selectByExample(userExample);
		return users;
	}

	
}