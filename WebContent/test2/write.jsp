<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%	//POST 방식에서 한글깨짐을 방지
	request.setCharacterEncoding("UTF-8");
	String cp = request.getContextPath();
%>
	
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

<form action="<%=cp %>/modelDri/write.action" method="post" >
	아이디:<input type="text" name="userId"><br/>
	패스워드:<input type="password" name="userPwd"><br/>
	이름:<input type="text" name="userName"><br/>
	<input type="hidden" name="mode" value="save">
	<!-- 스트럿츠2에는 mode를 보내는 hidden이 항상 포함된다. -->
	<input type="submit" value="보내기">
</form>

</body>
</html>