package priv.thinkam.rent.web.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import priv.thinkam.rent.common.base.Result;
import priv.thinkam.rent.common.token.JwtSubjectUtil;
import priv.thinkam.rent.common.token.JwtUtil;
import priv.thinkam.rent.common.token.UserTokenSubject;
import priv.thinkam.rent.common.util.IpUtil;
import priv.thinkam.rent.dao.model.User;
import priv.thinkam.rent.dao.model.UserExample;
import priv.thinkam.rent.dao.model.UserExample.Criteria;
import priv.thinkam.rent.dao.model.wrap.PageQuery;
import priv.thinkam.rent.dao.model.wrap.UserExampleCustomer;
import priv.thinkam.rent.dao.model.wrap.UserWrap;
import priv.thinkam.rent.service.UserService;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * 用户管理
 */
@RestController
@Api(value = "用户管理", description = "用户管理")
public class UserController {
	private static final String IS_LOGIN = "is_login";
	private static final String TRUE = "true";
	public static final String USER = "user";
	private static Logger logger = LoggerFactory.getLogger(UserController.class);
	@Autowired
	private UserService userService;
	
	
	/**
	 * 登录失败次数上限
	 */
	private static final int SIGN_FAIL_LIMIL_TIMES = 5;
	/**
	 * 限制不能登录期限5分钟
	 */
	private static final int SIGN_LIMIT_MINUTES = 5;
	/**
	 * token过期时间（1天）
	 */
	private static final int TOKEN_EXPIRE_DAYS = 1;

	

	@ApiOperation("用户列表")
	@GetMapping("/users")//  (value="token",required=false)
	//@RequestParam(value="token",required=false)
	public Result list( String token,HttpServletRequest request,HttpSession session ) {
		String tokens = (String) request.getSession().getAttribute("token");
		//解析token
		if (tokens==null) {
			session.removeAttribute(IS_LOGIN);
			session.removeAttribute(USER);
			session.removeAttribute("tokens");
			return new Result(false,"token为空,请先登录");
		}
		String parseJwt=null;
		try {
			 parseJwt = JwtUtil.parseJwt(tokens);
		} catch (Exception e) {
			return new Result(false, "token不正确");
		}
		UserTokenSubject userToken = JwtSubjectUtil.parseSubject(parseJwt);
		if (userToken==null) {
			return new Result(false, "用户不存在");
		}
		List<User> users = userService.selectByExample(new UserExample());
		return new Result(true, users);
	}

	@ApiOperation("用户登录")
	@PostMapping("/sessions")
	public Result login(@RequestBody User user, HttpServletRequest request) {
		//log记录信息
		logger.debug("method login get param:" + user);
		//通过session判断是否已登录
		if (TRUE.equals(request.getSession().getAttribute(IS_LOGIN))) {
			return new Result(false, "您已登录！");
		}
		//获取参数并trim必要的参数
		String username = StringUtils.trim(user.getUsername());
		String password = StringUtils.trim(user.getPassword());
		//认证并返回结果
		if(username == null || password == null) {
			return new Result(false, "密码和用户名不能为空");
		}
		UserExample userExample = new UserExample();
		userExample.createCriteria().andUsernameEqualTo(username);
		List<User> users = userService.selectByExample(userExample);
		User u = users.size() == 0 ? null : users.get(0);
		if (u == null) {
			return new Result(false, "用户名不存在");
		}
		if (!u.getPassword().equals(password)) {
			return new Result(false, "密码和用户名不匹配");
		}
		//session记录登录信息
		request.getSession().setAttribute(IS_LOGIN, TRUE);
		request.getSession().setAttribute(USER, u);
		
		// 计算token过期时间
		Date now = new Date();
		Calendar cal = Calendar.getInstance();
		cal.setTime(now);
		cal.add(Calendar.DATE, TOKEN_EXPIRE_DAYS);
		Date expire = cal.getTime();

		// 生成token
		
		String token = JwtUtil.buildJwt(
				JwtSubjectUtil.generateSubject(new UserTokenSubject(u.getUsername(),IpUtil.getRemoteHost(request))), expire);
		UserWrap userWrap = new UserWrap();//将需要返回的body，重新定义个对象
		userWrap.setUserId(u.getUserId());
		userWrap.setUserName(u.getUsername());
		userWrap.setPassword(u.getPassword());
		userWrap.setRole(u.getRole());
		userWrap.setIP(IpUtil.getRemoteHost(request));
		userWrap.setTokens(token);
		request.getSession().setAttribute("token",token);
		return new Result(true, userWrap);
	}

	@ApiOperation("退出登录")
	@DeleteMapping("/sessions")
	public Result logout(HttpSession session) {
		//TODO:判断是否登录
		//session移除登录信息
		session.removeAttribute(IS_LOGIN);
		session.removeAttribute(USER);
		session.removeAttribute("tokens");
		logger.debug(IS_LOGIN + ":" + session.getAttribute(IS_LOGIN));
		return new Result(true);
	}
	
	@ApiOperation("分页模糊查询_逆向工程")
	@PostMapping("/users")//这里别用requestbody 毕竟你没写对象类
	public Result userByLimit(@RequestParam String content,@RequestParam String order,@RequestParam int start,@RequestParam int pageSize){
		List<User> users = userService.selectByLimit(content, order, start, pageSize);
		return new Result(true,users);
	}
	
	@ApiOperation("分页查询_手写xml")
	@PostMapping("/usersfengye")//
	public Result userByFengYe(@RequestParam String content,@RequestParam String order,@RequestParam Integer start,@RequestParam Integer page){
		//选择form-data或者x-www-form-urlencoded
		//参数
			/**
			 * content    
			 * order     
			 * start           0
			 * page            3
			 */
		//想给content传空值，怎么做  直接就是 content  值不写。（不要传什么null，这样不行）
		List<User> users = userService.selectByFengYe(content,order,start,page);
		return new Result(true,users);
	}
	
	@ApiOperation("分页查询_逆向工程")
	@PostMapping("/usersReturn")//路径大写没关系
	public Result userByNiXiang(@RequestBody PageQuery page){
		//使用requestbody
		/*传入的参数为，名称为string ，值可以为integer
						 * {
					"content": null,
					"offset": 0,
					"limit": 2,
					"order": null
				     }
		 * 
		 */
		
		//想给content传空值，怎么做  直接就是 content  值不写。（不要传什么null，这样不行）
		PageQuery pageQuery = new PageQuery();
		pageQuery.setContent(page.getContent());
		pageQuery.setOffset(page.getOffset());
		pageQuery.setLimit(page.getLimit());
		
		List<User> users = userService.findUsers(pageQuery);
		return new Result(true,users);
	}
	
	
	 /*原因：@RequestBody
	  　a) 该注解用于读取Request请求的body部分数据，根据request的header部分的Content-Type类型，匹配HttpMessageConverter进行解析，然后把相应的数据绑定到要返回的对象上；
	　　b) 再把HttpMessageConverter返回的对象数据绑定到 controller中方法的参数上。
	　　前台没有注明后台解析参考的content-type，默认为application/x-www-form-urlencoded ，同时参数是对象也是不是json字符串，后台找不到解析方法。
	*/
	
	@ApiOperation("用户注册")
	@PostMapping("/user_register")
	public Result reg(@RequestBody UserWrap userWrap) {
//		{   
//			"userName":"南昌",
//		    "password":"123456"
//		}
		User user = new User();
		user.setUsername(userWrap.getUserName());
		user.setPassword(userWrap.getPassword());
		user.setRole(Byte.parseByte("0"));
		userService.insert(user);
		return new Result(true);
	}
}
