package com.test2;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;

public class TestAction extends ActionSupport 
	implements ModelDriven<User>, Preparable{//Model-Driven

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private User dto;
	
	/*public User getDto() {
		return dto;
	}*/
	
	@Override
	public User getModel() {
		return dto;
	}
	
	@Override
	public void prepare() throws Exception {
		dto = new User();
	}
	
	
	public String created() throws Exception {
		
		if(dto==null || dto.getMode()==null || dto.getMode().equals("")){//널값처리 반드시 이 형태로 한다.
			return INPUT;
		}
		
		HttpServletRequest request = ServletActionContext.getRequest();
		
		request.setAttribute("message", "스트럿츠2...");
		request.setAttribute("dto", dto);//getter를 이용하지 않는 방법
		return SUCCESS;
	}
	
}



















