<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%@include file="/WEB-INF/views/common/header.jsp"%>
	<div class="container">
		<form action="/updatemember" method="post">
			<legend>내 정보</legend>
			<div class="form-group">
				<fieldset>
					<label class="control-label" for="meberId">아이디</label> <input
						type="text" id="memberId" name="memberId" class="form-control"
						value="<%=m.getMemberId()%>" readonly>
				</fieldset>
			</div>
			<div class="form-group">
				<fieldset>
					<label class="control-label" for="meberPw">패스워드</label> <input
						type="text" id="memberPw" name="memberPw" class="form-control"
						value="<%=m.getMemberPw()%>">
				</fieldset>
			</div>
			<div class="form-group">
				<fieldset>
					<label class="control-label" for="meberName">이름</label> <input
						type="text" id="memberName" name="memberName" class="form-control"
						value="<%=m.getMemberName()%>" >
				</fieldset>
			</div>
			<div class="form-group">
				<fieldset>
					<label class="control-label" for="memberAddress">주소</label> <input
						type="text" id="memberAddress" name="memberAddress" class="form-control"
						value="<%=m.getAddress()%>">
				</fieldset>
			</div>
			<div class="form-group">
				<fieldset>
					<label class="control-label" for="memberPhone">전화번호</label> <input
						type="text" id="memberPhone" name="memberPhone" class="form-control"
						value="<%=m.getPhone()%>">
				</fieldset>
			</div>
			<div class="form-group">
				<fieldset>
					<label class="control-label" for="meberEndate">가입일</label> <input
						type="text" id="meberEndate" name="meberEndate" class="form-control"
						value="<%=m.getEnrollDate()%>" readonly>
				</fieldset>
			</div>
			<button>수정</button>
		</form>
	</div>
	<%@ include file="/WEB-INF/views/common/footer.jsp"%>
</body>
</html>