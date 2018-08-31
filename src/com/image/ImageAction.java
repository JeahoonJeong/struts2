package com.image;

import java.io.File;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.board.BoardDTO;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;
import com.util.FileManager;
import com.util.MyUtil;
import com.util.dao.CommonDAO;
import com.util.dao.CommonDAOImpl;

public class ImageAction extends ActionSupport implements Preparable, ModelDriven<ImageDTO>{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private ImageDTO dto;
	
	public ImageDTO getDto(){
		return dto;
	}

	@Override
	public ImageDTO getModel() {
		return dto;
	}

	@Override
	public void prepare() throws Exception {
		dto = new ImageDTO();
	}
	
	
	public String list() throws Exception {
		
		CommonDAO dao = CommonDAOImpl.getInstance();
		MyUtil myUtil = new MyUtil();
		
		HttpServletRequest request = ServletActionContext.getRequest();
		
		String cp = request.getContextPath();
		
		int numPerPage = 9;
		int totalPage = 0;
		int totalDataCount = 0;
		
		int currentPage = 1;
		
		if(dto.getPageNum()!=null && !dto.getPageNum().equals("")){
			currentPage = Integer.parseInt(dto.getPageNum());
		}
		
		totalDataCount = dao.getIntValue("image.getDataCount");
		
		if(totalDataCount!=0){
			totalPage = myUtil.getPageCount(numPerPage, totalDataCount);
		}
		
		if(currentPage>totalPage){
			currentPage = totalPage;
		}
		
		int start = (currentPage-1)*numPerPage+1;
		int end = currentPage*numPerPage;
		
		Map<String, Object> hMap = new HashMap<String, Object>();
		
		hMap.put("start", start);
		hMap.put("end", end);
		
		
		List<Object> lists = (List<Object>)dao.getListData("image.getList", hMap);
		
		
		int listNum,n = 0;
		//인덱스 처리
		ListIterator<Object> it = lists.listIterator();
		while(it.hasNext()){
			
			ImageDTO vo = (ImageDTO)it.next();
			listNum = totalDataCount-(start+n-1);
			vo.setListNum(listNum);
			n++;
			
		}
		
		
		String urlList = "";
		
		urlList = cp + "/image/list.action";
		
		String imagePath = cp+"/pds/imagedata";
		request.setAttribute("imagePath", imagePath);
		request.setAttribute("lists", lists);
		request.setAttribute("pageNum", currentPage);
		request.setAttribute("totalDataCount", totalDataCount);
		request.setAttribute("totalPage", totalPage);
		request.setAttribute("pageIndexList", myUtil.pageIndexList(currentPage, totalPage, urlList));
		
		return SUCCESS;
	}
	
	public String login() throws Exception{
		
		
		
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		
		if(request.getParameter("id")==null && session.getAttribute("member")==null){
			return INPUT;
		}
		
		MemberDTO mDto = new MemberDTO();
		mDto.setMemberId(request.getParameter("id"));
		mDto.setMemberPwd(request.getParameter("pwd"));
		session.setAttribute("member", mDto);
		return SUCCESS;
	}
	
	public String logout() throws Exception{
	
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		
		session.removeAttribute("member");
		
		return SUCCESS;
	}
	
	public String delete() throws Exception{
		CommonDAO dao = CommonDAOImpl.getInstance();
		
		
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		
		if(session.getAttribute("member")==null){
			return SUCCESS;
		}
		
		MemberDTO mdto = (MemberDTO)session.getAttribute("member");
		
		String id = (String)dao.getReadData("image.getId",dto.getNum());
		
		if(id.equals(mdto.getMemberId())){
			dao.deleteData("image.deleteData", dto.getNum());
		}
		
		return SUCCESS;
	}
	
	
	
	public String write() throws Exception {
		
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		
		if(session.getAttribute("member")==null){
			return SUCCESS;
		}
		
		CommonDAO dao = CommonDAOImpl.getInstance();
		
		if(dto==null || dto.getMode()==null || dto.getMode().equals("")){
			return INPUT;
		}
		
		String root = session.getServletContext().getRealPath("/");
		String savePath = root + File.separator + "pds" + File.separator +"imagedata";
		
		String saveFileName = FileManager.doFileUpload(dto.getUpload(), dto.getUploadFileName(), savePath);
		
		String originalFileName = dto.getUploadFileName();
		
		int maxNum = dao.getIntValue("image.getMaxNum");
		dto.setNum(maxNum+1);
		
		Map<String, Object> hMap = new HashMap<String, Object>();
		hMap.put("num", dto.getNum());
		hMap.put("subject", dto.getSubject());
		hMap.put("saveFileName", saveFileName);
		hMap.put("originalFileName", originalFileName);
		
		dao.insertData("image.insertData", hMap);
		
		// member_image 테이블에 로그인 정보와 이미지 이름 insert
		
		return SUCCESS;
	}
	
	
	
	//
	private InputStream inputStream;
	
	public InputStream getInputStream() {
		return inputStream;
	}

	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}

	
	
}
