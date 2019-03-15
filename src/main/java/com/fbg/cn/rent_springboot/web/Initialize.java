package com.fbg.cn.rent_springboot.web;

import com.fbg.cn.rent_springboot.common.base.BaseInterface;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 系统接口
 * Created by thinkam on 17-10-31.
 */
public class Initialize implements BaseInterface {

	private static Logger logger = LoggerFactory.getLogger(Initialize.class);

	@Override
	public void init() {
		logger.info(">>>>> 系统初始化");
	}

}
