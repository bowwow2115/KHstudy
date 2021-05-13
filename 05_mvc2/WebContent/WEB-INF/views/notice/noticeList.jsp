<%@page import="notice.model.vo.Notice"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
ArrayList<Notice> list = (ArrayList<Notice>) request.getAttribute("list");
String pageNavi = (String) request.getAttribute("pageNavi");
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
		<fieldset>
			<legend>공지사항</legend>
			<%
			if (m != null && m.getMemberLevel() == 1) {
			%>
			<a class="btn btn-info writeBtn" href="/noticeWriteFrm">공지사항 작성</a>
			<%
			}
			%>

			<table class="table-hover" style="width: 100%">
				<tr class="table-primary">
					<th>제목</th>
					<th>제목</th>
					<th>제목</th>
					<th>제목</th>
				</tr>
				<%
				for (Notice n : list) {
				%>
				<tr class="table-light">
					<td><%=n.getRnum()%></td>
					<td><a href = "/noticeView?noticeNo=<%=n.getNoticeNo()%>"><%=n.getNoticeTitle()%></a></td>
					<td><%=n.getNoticeWriter()%></td>
					<td><%=n.getNoticeDate()%></td>
				</tr>
				<%
				}
				%>
			</table>
			<div id="pageNavi"><%=pageNavi%></div>
		</fieldset>

	</div>
	<%@ include file="/WEB-INF/views/common/footer.jsp"%>
</body>
</html>