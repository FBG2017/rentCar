package priv.thinkam.rent.common.token;

/*JWT     Json  Web  Tokens

header (头部)
payload（数据）
signature(签名)
*/
public interface JwtConstants {
	String JWT_HEAD_KEY = "Authorization";
	String JWT_TOKEN_PARAM = "token";
	String ENCODE_STRING = "iancreatebyFbg";
}
