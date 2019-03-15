package com.fbg.cn.rent_springboot;

import com.fbg.cn.rent_springboot.common.util.SpringContextUtil;
import com.fbg.cn.rent_springboot.dao.mapper.UserMapper;
import com.fbg.cn.rent_springboot.dao.model.User;
import com.fbg.cn.rent_springboot.service.UserService;
import com.fbg.cn.rent_springboot.web.controller.HelloWorldController;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import javax.annotation.Resource;

import java.lang.reflect.Method;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@RunWith(SpringRunner.class)
@SpringBootTest
//@ComponentScan(basePackages = {"com.fbg.cn.rent_springboot"})
public class RentSpringbootApplicationTests {

	@Ignore
	@Test
	public void contextLoads() {
	}


	@Autowired
	private UserMapper userMapper;
	@Ignore
	@Test
	public  void  testDao(){
		User user=userMapper.selectByPrimaryKey(1);
		System.out.println(user);
	}

	@Resource
	private UserService userService;
	@Ignore
	@Test
	public void testService(){//nullPointerException

		//ctrl+alt+v
//		User user = userService.selectByPrimaryKey(1);
		User user = new User();
		user.setUsername("tom");
		user.setPassword("tom");
		user.setRole(Byte.parseByte("0"));
		userService.insert(user);
		System.out.println(user);
	}


	private MockMvc mvc;
	public  void setUp(){
		mvc= MockMvcBuilders.standaloneSetup(new HelloWorldController()).build();
	}
//	@Test
	public  void testController() throws Exception {
		ResultActions hello = mvc.perform(MockMvcRequestBuilders.get("hello").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content().string(equalTo("hello world")));
	}

//	@Ignore
	@Test
	public  void testioc(){
		UserService userService =(UserService) SpringContextUtil.getBean(UserService.class);
		System.out.println(userService.selectByPrimaryKey(1));
	}

	@Ignore
	@Test
	public  void testBaseService()  throws NoSuchMethodException {

	}
}
