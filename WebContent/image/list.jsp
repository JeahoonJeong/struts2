<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%
	request.setCharacterEncoding("utf-8");
	String cp = request.getContextPath();
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>게 시 판</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>

<link rel="stylesheet" href="<%=cp%>/image/data/style.css" type="text/css"/>
<script type="text/javascript" src="<%=cp%>/image/data/image.js"></script>

</head>

<body>
<div id="imageList" >
	<div id="imageList_title">
	이미지 게시판	
	</div>
	<input  style=" margin-top: 0px; margin-left: 200px " type="button" value=" 로그인 " class="btn2" 
					onclick="javascript:location.href='<%=cp%>/image/login.action'"/>
	<input  style=" margin-top: 0px;  " type="button" value=" 로그아웃 " class="btn2" 
					onclick="javascript:location.href='<%=cp%>/image/logout.action'"/>
	<div id="imageList_header_left">
			<dl>
				<dd id="imageList_header_left">Total ${totalDataCount } articles, ${totalPage } pages /Now page is ${pageNum }</dd>
				
			</dl>
			
			
	</div>
	<div id="imageList_header_right">
		
		<dl>
			
			<dd>
				<input  type="button" value=" 게시물 등록 " class="btn2" 
					onclick="javascript:location.href='<%=cp%>/image/write.action'"/>
			</dd>
			
		</dl>
	</div>
	

	<div id="imageList_list">
		<div id="lists" align="center" >
				<dl>
				<c:forEach var="dto" items="${lists }" >
					<dd id="image" >
						<a href="${imagePath }/${dto.saveFileName}">
						<img alt="" src="${imagePath }/${dto.saveFileName}" >
						</a><br/>
						${dto.listNum } <a href="<%=cp %>/image/delete.action?num=${dto.num}" >삭제</a>
					</dd>
				</c:forEach>
				</dl>
		</div>
		
		<%-- <c:set var = "i" value ="1"></c:set>
			<table>
				<c:forEach var="dto" items="${lists }"> 
					<c:if test="${i % 3 == 1}">
						<tr>
					</c:if>
						<td align="center">
							<img src="${imagePath }/${dto.saveFileName }" width="180" height="180" /><br/> 
							${dto.subject } 
							삭제
						</td> 		
					<c:if test="${i% 3 == 0}">
						</tr>
					</c:if>				
					<c:set var = "i" value ="${i+1 }"></c:set>
				</c:forEach>
			</table> 
			
			
			공백넣는 코드
			
			<c:if test="${n>0 || n%3!=0}">
				<c:forEach var="i" begin="${n%3+1}" end ="3" step="1">
					<td>&nbsp;</td>
				</C:forEach>
			</c:if>
				
			<c:if test="${n!=0}">
				</tr>
			</c:if>
			
			--%>
		
	</div>
	<div id="footer" >
		<p>
			<c:if test="${dataCount!=0 }">
				${pageIndexList }
			</c:if>
			<c:if test="${dataCount==0 }">
				등록된 게시물이 없습니다.
			</c:if>
		</p>
	</div>
</div>
</body>

</html>