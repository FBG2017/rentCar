package com.fbg.cn.rent_springboot.common.base;

/**
 * Created by thinkam on 12/17/17.
 */
public class Result {
	private boolean success;
	private Object data;
	
	private Integer total;
	private Integer rowSize ;
	
	public Result(boolean success, Object data) {
		
		this.success = success;
		this.data = data;
	}

	public Result(boolean success) {
		this(success, null);
	}
	
	public Result(boolean success, Object data,Integer total,Integer rowSize){
		this.success=success;
		this.data=data;
		this.total=total;
		this.rowSize=rowSize;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}
	 
	 
	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}

	public Integer getRowSize() {
		return rowSize;
	}

	public void setRowSize(Integer rowSize) {
		this.rowSize = rowSize;
	}

	@Override
	public String toString() {
		return "Result{" +
				"success=" + success +
				", data=" + data +
				",totalNum"+total+
				"rowNum"+rowSize+
				'}';
	}
}
