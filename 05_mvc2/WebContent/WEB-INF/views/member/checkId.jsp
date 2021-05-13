<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
    String memberId = (String)request.getAttribute("memberId");
    boolean result = (Boolean)request.getAttribute("result");
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
	#checked-container{
		text-align: center;
		padding-top: 50px;
	}
	#checked{
		color:red;
		font-weight: bold;
	}
</style>
<!-- jQuery라이브러리 추가(2개) -->
<script type="text/javascript" src="/js/jquery-3.3.1.js"></script>
<!-- 부트스트랩용 jQuery -->
<script type="text/javascript" src="/js/bootstrap.bundle.min.js"></script>
</head>
<body>
	<div id = "checked-container">
	<%if(result){ %>
	[<span><%=memberId %></span>]는 사용이 가능합니다.
	<br><br>
	<button onclick = "setMemberId('<%=memberId%>')">닫기</button>
	<%}else{ %>
	[<span id = "checked"><%=memberId %></span>]는 이미 사용중입니다.
	<br><br>
	<form action ="/checkId" method = "post">
		<input type= "text" name ="checkId" placeholder ="사용할 아이디를 입력하세요">
		<input type = "submit" value = "중복검사">
	</form>
	<%} %>
	</div>
	<script>
		function setMemberId(memberId){
			$("#idChk",opener.document).prev().val(memberId);
			self.close();
		}
	</script>
</body>
</html>