package priv.thinkam.rent.common.token;

public class TestTokens {

	public static void main(String[] args) {
		//String tokens="eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ7XCJ1c2VyTmFtZVwiOlwiYWRtaW5cIixcImxvZ2luSXBcIjpcIjEyNy4wLjAuMVwifSIsImV4cCI6MTU0Mjk0MjM1NH0.HiZe1EergGdYx-E4ztqSVHmyKzhlN-In8NY4G1h1cem-BmRDJSznZ9sKfkfhORS4iZUKjrtqYWCJr9X2BUAJzQ";
		String tokens="eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ7XCJ1c2VyTmFtZVwiOlwiYWRtaW5cIixcImxvZ2luSXBcIjpcIjEyNy4wLjAuMVwifSIsImV4cCI6MTU0Mjk2MjczOH0.XqIytT2tLOh0Uv3rpAXJR9a7LMfurqC3JXwpse1KDMPpkoGnJgEXS1ExhdI_hhz5fNsO9eoG3OewQlyGBoNooQ";
		String str = JwtUtil.parseJwt(tokens);
		System.out.println(str);
	}
}
