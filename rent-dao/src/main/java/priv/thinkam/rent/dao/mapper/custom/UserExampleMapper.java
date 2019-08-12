package priv.thinkam.rent.dao.mapper.custom;

import java.util.List;

import priv.thinkam.rent.dao.model.User;
import priv.thinkam.rent.dao.model.wrap.UserExampleCustomer;

public interface UserExampleMapper {
	List<User> selectByCustomerExample(UserExampleCustomer userExampleCustomer);
}
