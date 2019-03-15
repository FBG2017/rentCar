package com.fbg.cn.rent_springboot.dao;



import com.fbg.cn.rent_springboot.common.util.MybatisGeneratorUtil;
import com.fbg.cn.rent_springboot.common.util.PropertiesFileUtil;

import java.util.HashMap;
import java.util.Map;

public class Generator {
	// 根据命名规范，只修改此常量值即可
	private static String DATABASE = "rent";
	private static String PACKAGE_NAME = "com.fbg.cn.rent_springboot";
	private static String JDBC_DRIVER = PropertiesFileUtil.getInstance("gener/generator").get("generator.jdbc.driver");
	private static String JDBC_URL = PropertiesFileUtil.getInstance("gener/generator").get("generator.jdbc.url");
	private static String JDBC_USERNAME = PropertiesFileUtil.getInstance("gener/generator").get("generator.jdbc.username");
	private static String JDBC_PASSWORD = PropertiesFileUtil.getInstance("gener/generator").get("generator.jdbc.password");
	// 需要insert后返回主键的表配置，key:表名,value:主键名
	private static Map<String, String> LAST_INSERT_ID_TABLES = new HashMap<>();
	static {
		LAST_INSERT_ID_TABLES.put("user", "user_id");
		LAST_INSERT_ID_TABLES.put("category", "category_id");
		LAST_INSERT_ID_TABLES.put("stuff", "stuff_id");
		LAST_INSERT_ID_TABLES.put("item", "item_id");
	}

	/**
	 * 自动代码生成
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		MybatisGeneratorUtil.generator(JDBC_DRIVER, JDBC_URL, JDBC_USERNAME, JDBC_PASSWORD, DATABASE, PACKAGE_NAME, LAST_INSERT_ID_TABLES);
		PropertiesFileUtil instance = PropertiesFileUtil.getInstance("gener/generator");
		String name = instance.get("generator.jdbc.driver");
		System.out.println(name);
		//会根据在本项目中的文件名，来读取配置文件。aa.properties=--aa
		//放在根目录下，如果新建gg包，那么就会读取不到。gg/aa.properties---gg/aa
	}
}
