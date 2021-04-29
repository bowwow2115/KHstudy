<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%@include file="/view/header.jsp"%>
	<form action="/view/join.jsp" method="post">
		<fieldset>
			<legend>회원가입</legend>
			아이디: <input type="text" name="memberId" /><br /> 비밀번호: <input
				type="text" name="memberPw" /><br /> 이름: <input type="text"
				name="memberName" /><br /> 전화번호: <input type="text" name="phone" /><br />
			주소: <input type="text" name="address" /><br /> <input type="submit"
				value="회원가입" /> <input type="reset" value="취소" />
		</fieldset>
	</form>
	<hr />

</body>

</html>