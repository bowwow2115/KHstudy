<%@page import="com.parksh.member.dao.MemberDao"%>
<%@page import="com.parksh.member.vo.Member"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
	//1.인코딩
	request.setCharacterEncoding("utf-8");
	//2.값추출
	String memberId = request.getParameter("memberId");
	String memberPw = request.getParameter("memberPw");
	String memberName = request.getParameter("memberName");
	String phone = request.getParameter("phone");
	String address = request.getParameter("address");

	Member m = new Member();
	m.setMemberId(memberId);
	m.setMemberPw(memberPw);
	m.setMemberName(memberName);
	m.setPhone(phone);
	m.setAddress(address);

	// 3. 처리 로직
	MemberDao dao = new MemberDao();
	int result = dao.insertMember(m);
	%>

	<% if (result>0) {
		 %>
	<h2>회원가입 성공</h2>
	<%  %>
	<%} else { %>
	<h2>회원가입 실패</h2>
	<%} %>


</body>

</html>