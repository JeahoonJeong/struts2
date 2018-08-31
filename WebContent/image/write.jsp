<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%
	//POST 방식에서 한글깨짐을 방지
	request.setCharacterEncoding("UTF-8");
	String cp = request.getContextPath();
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

<link rel="stylesheet" href="<%=cp%>/imagetest/data/style.css"
	type="text/css" />
<script type="text/javascript" src="<%=cp%>/imagetest/data/image.js"></script>

</head>
<body>
	<div id="imageWrite">
		<div id="imageWrite_title">이미지 게시판</div>
		<form action="<%=cp%>/image/write.action" name="myForm" method="post"
			enctype="multipart/form-data">
				<div id="subject">
					<dl>
						<dt id="subject_name">제목</dt>
						<dd id="subject_box">
							<input type="text" name="subject" size="40">
						</dd>
					</dl>
				</div>
				<div id="file">
					<dl>
						<dt id="file_name">파일</dt>
						<dd id="file_box">
							<input type="file" name="upload">
						</dd>
					</dl>
				</div>
			<div >
				<dl>
					<dd id="imageWrite_footer">
						<input type="hidden" name="mode" value="save">
						<input type="submit" value="등록하기">
						
						<input type="reset" value="다시입력"
							onclick="document.myForm.subject.focus();">
							
						<input type="button" value="작성취소"
							onclick="javascript:location.href='<%=cp%>/image/list.action'">		
					</dd>
				</dl>
			</div>
		</form>
	</div>
	<script type="text/javascript" src="<%=cp%>/imagetest/data/image.js"></script>
</body>
</html>