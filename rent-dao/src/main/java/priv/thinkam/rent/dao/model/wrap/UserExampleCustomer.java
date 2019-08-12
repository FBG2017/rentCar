package priv.thinkam.rent.dao.model.wrap;

import java.io.Serializable;

import priv.thinkam.rent.dao.model.UserExample;

public class UserExampleCustomer extends UserExample implements Serializable{

	private static final long serialVersionUID = 1L;
	private Integer offset;//start
	private Integer limit;//pageSize
	private String content;//模糊查询
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Integer getOffset() {
		return offset;
	}
	public void setOffset(Integer offset) {
		this.offset = offset;
	}
	public Integer getLimit() {
		return limit;
	}
	public void setLimit(Integer limit) {
		this.limit = limit;
	}
	
	@Override
	public String toString() {
		return super.toString();
	}
}
