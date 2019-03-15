package com.fbg.cn.rent_springboot.common.webservice;

import java.util.List;

public class _Main {

	public static void main(String[] args) {
		System.setProperty("java.net.preferIPv4Stack", "true");
			MobileCodeWS ms = new MobileCodeWS();
			MobileCodeWSSoap mc = ms.getMobileCodeWSSoap();
			String mobileCodeInfo = mc.getMobileCodeInfo("18797816082", "");
			System.out.println(mobileCodeInfo);
			
			ArrayOfString info = mc.getDatabaseInfo();
			List<String> str = info.getString();
			for(String i:str){
				System.out.println(i);
			}
			
	}
}
