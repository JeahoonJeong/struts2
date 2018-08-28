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

<h3>Domain Object :</h3>
Action이 커지는 것을 방지하기위해 멤버변수를 별개의 클래스로 만듬<br/><br/>
<form action="<%=cp %>/itwill/created_ok.action" method="post">
	아이디:<input type="text" name="user.userId"><br/>
	이름:<input type="text" name="user.userName"><br/>
	<input type="submit" value="보내기">
</form>
</body>
</html>