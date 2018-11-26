package priv.thinkam.rent.dao.model.wrap;

public class UserWrap {
    private Integer userId;
    private String username;
    private String password;
    private Byte role;
    private String tokens;
    private String IP;
    public String getIP() {
		return IP;
	}
    public void setIP(String iP) {
		IP = iP;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Byte getRole() {
		return role;
	}
	public void setRole(Byte role) {
		this.role = role;
	}
	public String getTokens() {
		return tokens;
	}
	public void setTokens(String tokens) {
		this.tokens = tokens;
	}
    
}
