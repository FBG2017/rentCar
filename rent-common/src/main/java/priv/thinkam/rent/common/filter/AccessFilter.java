package priv.thinkam.rent.common.filter;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

import org.apache.velocity.runtime.directive.Break;

import priv.thinkam.rent.common.base.Result;
import priv.thinkam.rent.common.token.JwtSubjectUtil;
import priv.thinkam.rent.common.token.JwtUtil;
import priv.thinkam.rent.common.token.UserTokenSubject;

/**
 * 使用注解标注过滤器
 * @WebFilter将一个实现了javax.servlet.Filte接口的类定义为过滤器
 * 属性filterName声明过滤器的名称,可选
 * 属性urlPatterns指定要过滤 的URL模式,也可使用属性value来声明.(指定要过滤的URL模式是必选属性)
 * urlPatterns="/*" 表示过滤掉所有请求
 */
@WebFilter(filterName="AccessFilter",urlPatterns="/*")
public class AccessFilter implements Filter{

	@Override
	public void destroy() {
		 System.out.println("过滤器销毁");
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		response.setCharacterEncoding("utf-8");
		PrintWriter out;
		ServletOutputStream outputStream;
		System.out.println("执行过滤操作");
        HttpServletRequest req = (HttpServletRequest) request;
        String uri = req.getRequestURI();
        System.out.println("uri is :  " + uri);
        
        //对请求的uri(即api)进行判断，如果是登录的uri则直接放行，如果是其他api则对sign进行验证操作
        if( !uri.endsWith("sessions") ){
            //从请求的url中取出token、时间戳、sign
            String token = req.getHeader("token");
//            String timesamp = req.getHeader("timesamp");
//            String sign = req.getHeader("sign");
            System.out.println("token is :  " + token);
            
            StringBuffer requestUrl = req.getRequestURL();
            System.out.println("请求的Url是：   " + requestUrl);
            try {
            	String parseJwt = JwtUtil.parseJwt(token);
            	UserTokenSubject userToken = JwtSubjectUtil.parseSubject(parseJwt);
                if (userToken!=null) {
    				chain.doFilter(request, response);
    			}else {
    				out = response.getWriter();
    				String content="{" +'"'
    						+"success"+'"'+":" + false +
    						","+'"'+"data"+'"'+":"+'"'+"该用户不存在，请查证"+'"'+
    						'}';
    				out.println(content);
        			out.flush();
        			out.close();
    			}
			}catch(Exception e){
				String content="{" +'"'
						+"success"+'"'+":" + false +
						","+'"'+"data"+'"'+":"+'"'+"令牌解析失败，请重新登录"+'"'+
						'}';
				outputStream = response.getOutputStream();
				outputStream.write(content.getBytes("utf-8"));
				outputStream.flush();
				outputStream.close();
				//out = response.getWriter();
//				out.println(new Result(false,"令牌解析失败，请重新登录"));
				/*out.println(content);
    			out.flush();
    			out.close();*/
			}
    		
        }else{
            //登录操作
            chain.doFilter(request, response);
        }
        
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		 System.out.println("过滤器初始化");
	}

}
