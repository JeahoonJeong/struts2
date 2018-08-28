package com.board;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;
import com.util.dao.CommonDAO;
import com.util.dao.CommonDAOImpl;

public class BoardAction extends ActionSupport implements Preparable, ModelDriven<BoardDTO>{
	
	
	private static final long serialVersionUID = 1L;
	
	private BoardDTO dto;

	public BoardDTO getDto() {
		return dto;
	}
	
	@Override
	public BoardDTO getModel() {
		return dto;
	}

	@Override
	public void prepare() throws Exception {
		dto = new BoardDTO();
	}
	
	
	
	public String created() throws Exception {
		
		HttpServletRequest request = ServletActionContext.getRequest();
		
		if(dto == null || dto.getMode()==null || dto.getMode().equals("")){
			//게시물 창
			//mode에 따라 이름이 다르게 함
			request.setAttribute("mode", "created");
			return INPUT;
		}
		
		//게시물 저장
		CommonDAO dao = CommonDAOImpl.getInstance();
		int maxBoardNum = dao.getIntValue("board.maxBoardNum");
		
		dto.setBoardNum(maxBoardNum+1);
		dto.setIpAddr(request.getRemoteAddr());
		dto.setGroupNum(dto.getBoardNum());
		dto.setDepth(0);
		dto.setOrderNo(0);
		dto.setParent(0);
		
		dao.insertData("board.insertData", dto);
		
		return SUCCESS;
		
	}
	
	
	public String list() throws Exception {
		
		return SUCCESS;
	}
	
	
}






















