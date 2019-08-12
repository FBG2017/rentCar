package priv.thinkam.rent.service;

import java.util.List;

import priv.thinkam.rent.common.base.BaseService;
import priv.thinkam.rent.dao.model.User;
import priv.thinkam.rent.dao.model.UserExample;
import priv.thinkam.rent.dao.model.wrap.PageQuery;
import priv.thinkam.rent.dao.model.wrap.UserExampleCustomer;

/**
* UserService接口
* Created by thinkam on 17-12-19.
*/
public interface UserService extends BaseService<User, UserExample> {
	//自己手写自定义 模糊查询
	List<User> selectByLimit(String content,String order,int start,int pageSize);
	
	List<User> selectByFengYe(String content, String order, int start,
			int pageSize);
	
	List<User> findUsers(PageQuery page);
}