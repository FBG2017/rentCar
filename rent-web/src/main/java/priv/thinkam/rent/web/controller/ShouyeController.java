package priv.thinkam.rent.web.controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.lang.management.ManagementFactory;
import java.net.HttpURLConnection;
import java.net.URL;

import cn.com.webxml.ArrayOfString;
import cn.com.webxml.WeatherWS;
import cn.com.webxml.WeatherWSSoap;

import com.sun.management.OperatingSystemMXBean;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.methods.PostMethod;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import priv.thinkam.rent.common.base.Result;
import priv.thinkam.rent.dao.model.wrap.CityWrap;

@SuppressWarnings("restriction")
@RestController
@Api(value="首页详情",description="首页详情")
public class ShouyeController {

	@ApiOperation("系统信息")
	@GetMapping("/sysInfo")
	public Result getSystemInfo(HttpServletRequest request){
		HashMap<String, Object> map = new HashMap<String, Object>();
		String osName = System.getProperty("os.name");
		String osVersion = System.getProperty("os.version");
		
		String sysType = System.getProperty("os.arch");
		String path= request.getContextPath();  // getRealPath("/");
		String javaVersion = System.getProperty("java.version");
		String javaPath = System.getProperty("java.home");
		OperatingSystemMXBean osmb = (OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();
		long totalPhysicalMemorySize = osmb.getTotalPhysicalMemorySize()/1024/1024;//MB
		long freePhysicalMemorySize = osmb.getFreePhysicalMemorySize()/1024/1024;//MB
		
		map.put("sysVersion",osName+"    "+osVersion);
		map.put("sysType",sysType);
		map.put("path",path);
		map.put("javaVersion", javaVersion);
		map.put("javaPath",javaPath);
		map.put("totalMemory",totalPhysicalMemorySize);
		map.put("freeMemory", freePhysicalMemorySize);
		return new Result(true,map);
	}
	
	@ApiOperation("获取当前地址的未来7天，天气情况")
	@GetMapping("/weather/{city}")
	public Result getWheather(@PathVariable String city) throws IOException{
		String cityEco = new String(city.getBytes("ISO-8859-1"), "utf8"); 
		String url="http://ws.webxml.com.cn//WebServices/WeatherWS.asmx/getWeather?theCityCode="+cityEco+"&theUserID=null";
		URL url2 = new URL(url);
		HttpURLConnection conn=(HttpURLConnection) url2.openConnection();
		conn.setConnectTimeout(5000);
		conn.setRequestMethod("GET");
		ByteArrayOutputStream boas = null;
		if(conn.getResponseCode()==HttpURLConnection.HTTP_OK){ //结果码=200
			InputStream is=conn.getInputStream();
			//内存流 ，  
			 boas=new ByteArrayOutputStream();
			byte[] buffer=new byte[1024];
			int len=-1;
			while((len=is.read(buffer))!=-1){
				boas.write(buffer, 0, len);
			}
//		    System.out.println("GET请求获取的数据:"+boas.toString());	
		    boas.close();
		    is.close();
		}
		return new Result(true,boas.toString());
	}
	
	@ApiOperation("天气预报")
	@PostMapping("/weather")
	public Result getFutherWeather(@RequestParam String city) throws HttpException, IOException{
		System.setProperty("java.net.preferIPv4Stack", "true");
		HttpClient client=new HttpClient();
		String url="http://ws.webxml.com.cn//WebServices/WeatherWS.asmx/getWeather";
		PostMethod postMethod=new PostMethod(url);
		//3.设置请求参数
//		="+cityEco+"&
		postMethod.setParameter("theCityCode",city);
		postMethod.setParameter("theUserID","");
		//4.执行请求 ,结果码
		int code=client.executeMethod(postMethod);
		//5. 获取结果
		String result=postMethod.getResponseBodyAsString();
		System.out.println("Post请求的结果："+result);
		return new Result(true, result);
	}
	
	@ApiOperation("打jar包，应用webservice,获取城市 天气")
	@PostMapping("/Wea")
	public Result getWea(@RequestBody CityWrap cityWrap,HttpServletRequest request) throws UnsupportedEncodingException{
		System.setProperty("java.net.preferIPv4Stack", "true");
//		  request.setCharacterEncoding("utf-8");
		  //这个处理不了中文，只能去web.xml中配置过滤器
		  System.out.println(cityWrap.getCityName());
		  WeatherWS ws = new WeatherWS();
		  WeatherWSSoap wsSoap = ws.getWeatherWSSoap();
		  ArrayOfString weather = wsSoap.getWeather(cityWrap.getCityName(), null);
		  List<String> str = weather.getString();
		  /*for(String s:str){
			  System.out.println(s);
		  }*/
		  HashMap<String, Object> map = new HashMap<String,Object>();
		  if (str.size()>1) {
			  map.put("city",str.get(1));
			  map.put("date",str.get(7));
			  map.put("wendu",str.get(8));
			  map.put("cloud",str.get(9));
			  map.put("pic1", str.get(10));
			  map.put("pic2", str.get(11));
		}
		return new Result(true,map);
	}
	
	public static void main(String[] args) {
		System.setProperty("java.net.preferIPv4Stack", "true");
		WeatherWS ws = new WeatherWS();
		  WeatherWSSoap wsSoap = ws.getWeatherWSSoap();
		  ArrayOfString weather = wsSoap.getWeather("上海", null);
		  List<String> str = weather.getString();
		  HashMap<String, Object> map = new HashMap<String,Object>();
		  map.put("city",str.get(1));
		  map.put("date",str.get(5));
		  map.put("wendu",str.get(6));
		  map.put("cloud",str.get(7));
		  map.put("pic1", str.get(8));
		  map.put("pic2", str.get(9));
		  System.out.println(str.get(1)+str.get(7)+str.get(8)+str.get(9)+str.get(10)+str.get(11));
		  
		  System.out.println("-------------------------------");
		  for(String s:str){
			  System.out.println(s);
		  }
	}
	
}
