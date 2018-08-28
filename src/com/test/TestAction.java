package com.test;

import com.opensymphony.xwork2.ActionSupport;

public class TestAction extends ActionSupport{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	private String userId;
	private String userName;
	private String message;
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	@Override
	public String execute() throws Exception {
		
		message = userName + "님 안녕하세요.";
		//return 값은 사용자 정의
		//success를 보통 사용한다.
		return SUCCESS;
		
	}
	
	
	
	
	
}
