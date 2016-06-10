package com.nuc.frameworkUtil;
/**
 * 页面相应信息类
 * @author yadi
 *
 */
public class ResultMessage {
	private String success;//成功
	private String message;//提示信息
	private Object data;//数据集合
	public String getSuccess() {
		return success;
	}
	public void setSuccess(String success) {
		this.success = success;
	}
	
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	
	
}
