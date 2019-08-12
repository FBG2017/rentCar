package priv.thinkam.rent.dao.model.wrap;

import java.io.Serializable;

public class PageQuery implements Serializable{
	private static final long serialVersionUID = 1L;
	private String content;
	private String order;
	private Integer offset;
	private Integer limit;
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getOrder() {
		return order;
	}
	public void setOrder(String order) {
		this.order = order;
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
