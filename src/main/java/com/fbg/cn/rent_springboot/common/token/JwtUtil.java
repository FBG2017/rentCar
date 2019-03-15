package com.fbg.cn.rent_springboot.common.token;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.util.Base64;
import java.util.Date;

/**
 * jwt工具类
 */
/*
 * token  
 * 		header(头部)
 * 					包含使用的算法
 * 					例如 {"alg": "HS256"}，指定算法就会加密，不指定就为none
 * 					base64url编码
 * 					1：eyJhbGciOiJIUzI1NiJ9
 * 		payload(数据)
 * 					iss：Issuer，发行者
					sub：Subject，主题
					aud：Audience，观众
					exp：Expiration time，过期时间
					nbf：Not before
					iat：Issued at，发行时间
					jti：JWT ID
					例如：
						{
						 "iss": "ninghao.net",
						 "exp": "1438955445",
						 "name": "wanghao", 自定义
						 "admin": true 		自定义
						}
			base64url处理完了就是这样的：		
			2：eyJpc3MiOiJuaW5naGFvLm5ldCIsImV4cCI6IjE0Mzg5NTU0NDUiLCJuYW1lIjoid2FuZ2hhbyIsImFkbWluIjp0cnVlfQ
 * 		signature(签名)
 * 					这部分内容有三个部分，先是用 Base64 编码的 header.payload ，
 * 					再用加密算法加密一下，加密的时候要放进去一个 Secret ，
 * 					这个相当于是一个密码，这个密码秘密地存储在服务端。
 * 					
 * 					const encodedString = base64UrlEncode(header) + 
 * 										"." + base64UrlEncode(payload); 
					HMACSHA256(encodedString, 'secret');
					
					3：SwyHTEx_RQppr97g4J5lKXtabJecpejuef8AqKYMAJc
 */

//1+2+3=
//eyJhbGciOiJIUzI1NiJ9.
//eyJpc3MiOiJuaW5naGFvLm5ldCIsImV4cCI6IjE0Mzg5NTU0NDUiLCJuYW1lIjoid2FuZ2hhbyIsImFkbWluIjp0cnVlfQ.
//SwyHTEx_RQppr97g4J5lKXtabJecpejuef8AqKYMAJc
public class JwtUtil {
	/**
	 * 生成jwt字符串
	 * 
	 * @param subject
	 *            subject
	 * @param expireTime
	 *            过期时间
	 * @return jwt字符串
	 */
	public static String buildJwt(String subject, Date expireTime) {
		String compactJws = Jwts.builder().setSubject(subject)// 设置主题
				.setExpiration(expireTime).signWith(SignatureAlgorithm.HS512, generateKey())// 设置算法（必须）
				.compact();
		return compactJws;
	}

	/**
	 * 解析jwt字符串
	 *            jwt字符串
	 */
	public static String parseJwt(String compactJws) {
		Jws<Claims> parseClaimsJws = Jwts.parser().setSigningKey(generateKey()).parseClaimsJws(compactJws);
		Claims body = parseClaimsJws.getBody();
		return body.getSubject();
	}

	/**
	 * 生成key
	 * @return key
	 */
	private static Key generateKey() {
		// base64编码后做AES加密
		byte[] encodedKey = Base64.getDecoder().decode(JwtConstants.ENCODE_STRING);
		SecretKey key = new SecretKeySpec(encodedKey, 0, encodedKey.length, "AES");
		return key;
	}
}
