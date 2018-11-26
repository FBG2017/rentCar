package priv.thinkam.rent.common.token;

import priv.thinkam.rent.common.util.JsonUtils;


/**
 * jwt subject 工具类
 * 
 * @author liuzk
 *
 */
public class JwtSubjectUtil {
	/**
	 * 生成subject 对象转json
	 */
	public static String generateSubject(UserTokenSubject tokenSubject) {
		try {
			return JsonUtils.obj2json(tokenSubject);
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}

	/**
	 * 解析subject json转对象
	 */
	public static UserTokenSubject parseSubject(String subject) {
		try {
			UserTokenSubject obj = JsonUtils.json2pojo(subject, UserTokenSubject.class);
			return obj;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
