<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	Member member = (Member)request.getAttribute("member");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%@include file="/WEB-INF/views/common/header.jsp"%>
	<div class="container">
		<form action="/updatemember1" method="post">
			<legend>내 정보</legend>
			<div class="form-group">
				<fieldset>
					<label class="control-label" for="meberId">아이디</label> <input
						type="text" id="memberId" name="memberId" class="form-control"
						value="<%=member.getMemberId()%>" readonly>
				</fieldset>
			</div>
			<div class="form-group">
				<fieldset>
					<label class="control-label" for="meberPw">패스워드</label> <input
						type="text" id="memberPw" name="memberPw" class="form-control"
						value="<%=member.getMemberPw()%>">
				</fieldset>
			</div>
			<div class="form-group">
				<fieldset>
					<label class="control-label" for="meberName">이름</label> <input
						type="text" id="memberName" name="memberName" class="form-control"
						value="<%=member.getMemberName()%>" >
				</fieldset>
			</div>
			<div class="form-group">
				<fieldset>
					<label class="control-label" for="memberAddress">주소</label> <input
						type="text" id="memberAddress" name="memberAddress" class="form-control"
						value="<%=member.getAddress()%>">
				</fieldset>
			</div>
			<div class="form-group">
				<fieldset>
					<label class="control-label" for="memberPhone">전화번호</label> <input
						type="text" id="memberPhone" name="memberPhone" class="form-control"
						value="<%=member.getPhone()%>">
				</fieldset>
			</div>
			<div class="form-group">
				<fieldset>
					<label class="control-label" for="meberEndate">가입일</label> <input
						type="text" id="meberEndate" name="meberEndate" class="form-control"
						value="<%=member.getEnrollDate()%>" readonly>
				</fieldset>
			</div>
			<div class="form-group">
				<fieldset style = "text-align: center">
				<button type = "submit" class="btn btn-outline-primary">정보수정</button>
				
				<%if(member.getMemberLevel() == 1) { %>
				<a href ="adminPage" class="btn btn-outline-danger">회원관리</a>
				<% }else {%>
				
				<a href ="/deleteMember?memberNo=<%=member.getMemberNo() %>" class="btn btn-outline-danger">회원탈퇴</a>
				<%} %>
				</fieldset>
			</div>
		</form>
	</div>
	<%@ include file="/WEB-INF/views/common/footer.jsp"%>
</body>
</html>