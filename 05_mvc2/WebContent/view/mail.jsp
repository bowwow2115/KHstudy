<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8" />
    <title>Insert title here</title>
  </head>
  <body>
    <%@include file="/WEB-INF/views/common/header.jsp"%>
    <div class="container">
      <h3>Email 보내기</h3>
      <input
        type="text"
        id="email"
        class="short form-control"
        placeholder="email주소입력"
      />
      <button id="sendMail" class="btn btn-success">인증코드 보내기</button>
      <div class="auth">
        <input
          type="text"
          id="authCode"
          class="short form-control"
          placeholder="인증코드 입력."
        />
        <button class="btn btn-primary" id="authBtn">인증하기</button>
        <span id="timeZone"></span>
        <span id="authMsg"></span>
      </div>
    </div>
    <%@ include file ="/WEB-INF/views/common/footer.jsp" %>
  </body>
  <script>
    var mailCode;
    $("#sendMail").click(function () {
      var email = $("#email").val();
      $.ajax({
        url: "/sendMail",
        data: { email: email },
        success: function(data) {
          console.log(data);
          mailCode = data;
          $("#auth").slideDown();
        },
      });
    });
    $("#authBtn").click(function () {
      if ($("#authCode".val() == mailCode)) {
        $("#authMsg").html("인증성공");
        $("#authMsg").css("color", "blue");
      } else {
        $("#authMsg").html("인증번호를 확인하세요.");
        $("#authMsg").css("color", "red");
      }
    });
  </script>
</html>
