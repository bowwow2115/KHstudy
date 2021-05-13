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
      <fieldset>
        <legend>AJAX</legend>
        <div>
          <h3>1. 서버로 데이터 보내기</h3>
        </div>
        <input type="text" id="ajax1" />
        <button class="btn btn-info" onclick="jsAjax();">보내기(JS)</button>
        <button class="btn btn-danger" id="jQ1">보내기(jQuery)</button>

        <form action="/ajaxTest1" method="get">
          <input type="text" name="msg" />
          <button>전송</button>
        </form>

        <div>
          <h3>2.서버에서 기본형 데이터 받기</h3>
          <button class="btn btn danger" id="jQ2">데이터받기</button>
          <p class="ajaxResult"></p>
        </div>

        <div>
          <h3>3. 서버로 기본형 데이터 보내고 기본형 데이터 받기</h3>
          <input type="text" id="firstNum" />
          <input type="text" id="secondNum" />
          <button class="btn btn-danger" id="jQ3">두 수의 합</button>
          <p class="ajaxResult"></p>
        </div>

        <div>
          <h3>4. 서버로 객체 데이터 보내기</h3>
          <input type="text" id="name" /> <input type="text" id="age" />
          <input type="text" id="addr" />
          <button class="btn btn-danger" id="jQ4">객체 보내기</button>
        </div>

        <div>
          <h3>5. 서버로 기본형 데이터를 보내고 , 결과로 객체받기</h3>
          <input type="text" id="ajax5" />
          <button class="btn btn-danger" id="jQ5">조회</button>
          <p class="ajaxResult"></p>
        </div>

        <div>
          <h3>6. 서버에서 리스트 타입 결과받기</h3>
          <input type="text" id="ajax6" />
          <button class="btn btn-danger" id="jQ6"></button>
          <p class="ajaxResult"></p>
        </div>

        <div>
          <h3>7.서버에서 맵탑입 결과 받기</h3>
          <button class="btn btn-danger" id="jQ7">조회</button>
          <p class="ajaxResult"></p>
        </div>

        <div>
          <h3>8. 서버에서 객체 타입 결과 받기(GSON)</h3>
          <input type="text" id="ajax8" />
          <button class="btn btn-danger" id="jQ8">조회</button>
          <p class="ajaxResult"></p>
        </div>

        <div>
          <h3>9. 서버에서 리스트 타입 결과 받기(GSON)</h3>
          <button class="btn btn-danger" id="jQ9">조회</button>
          <p class="ajaxResult"></p>
        </div>

        <div>
          <h3>10</h3>
          <button class="btn btn-danger" id="jQ10">조회</button>
          <p class="ajaxResult"></p>
        </div>
      </fieldset>
    </div>
    <%@ include file="/WEB-INF/views/common/footer.jsp"%>
    <script>
      function jsAjax() {
        //1.XMLHttpRequest();
        var xhttp = new XMLHttpRequest();
        var msg = document.getElementById("ajax1").value; //input 입력값
        //2.요청정보를 설정
        xhttp.open("GET", "/ajaxTest1?msg=" + msg, true);
        //3.데이터 처리에 따라 동작할 함수 설정
        xhttp.onreadystatechange = function () {
          if (this.readyState == 4 && this.status == 200) {
            //4의 의미는 요청이 끝나고 응답하려는 상태에 도달했다는 뜻
            console.log("서버전송 성공");
          } else if (this.readyState == 4 && this.status == 404) {
            console.log("서블릿 없음");
          }
        };
        //4. 서버에 요청
        xhttp.send();
      }
      $("#jQ1").click(function () {
        var msg = $("#ajax1").val();
        $.ajax({
          url: "/ajaxTest1",
          data: {
            msg: msg,
          },
          type: "get",
          success: function () {
            console.log("서버전송 성공");
          },
          error: function () {
            console.log("에러");
          },
          complete: function () {
            console.log("성공/실패 여부와 상관없이 출력");
          },
        });
      });
      $("#jQ2").click(function () {
        var result = $(this).next();
        $.ajax({
          url: "/ajaxtest2",
          type: "get",
          success: function (data) {
            result.html(data); //서버가 주는 데이터가 들어옴
            console.log("됐는데");
          },
          error: function () {
            console.log("실패");
          },
          complete: function () {
            console.log("뭐야");
          },
        });
      });
      $("#jQ3").click(function () {
        var result = $(this).next();
        var num1 = $("#firstNum").val();
        var num2 = $("#secondNum").val();
        $.ajax({
          url: "/ajaxTest3",
          type: "post",
          data: {
            firstNum: num1,
            secondNum: num2,
          },

          success: function (data) {
            console.log("서버전송 성공");
            result.html(data);
          },
          error: function () {
            console.log("에러");
          },
          complete: function () {
            console.log("성공/실패 여부와 상관없이 출력");
          },
        });
      });
      $("#jQ4").click(function () {
        var name = $("#name").val();
        var age = $("#age").val();
        var addr = $("#addr").val();
        //객체 생성
        var student = { name: name, age: age, addr: addr };
        $.ajax({
          url: "/ajaxTest4",
          type: "get",
          data: student,
          success: function () {
            console.log("성공");
          },
        });
      });
      $("#jQ4").click(function () {
        var name = $("#name").val();
        var age = $("#age").val();
        var addr = $("#addr").val();
        //객체 생성
        var student = { name: name, age: age, addr: addr };
        $.ajax({
          url: "/ajaxTest4",
          type: "get",
          data: student,
          success: function () {
            console.log("성공");
          },
        });
      });
      $("#jQ5").click(function () {
        var memberId = $("#ajax5").val();
        var result = $(this).next();
        $.ajax({
          url: "/ajaxTest5",
          type: "get",
          data: { memberId: memberId },
          success: function (data) {
            if (data == null) {
              result.html("회원 정보가 없습니다.");
            } else {
              result.empty();
              var memberNo = data.memberNo;
              result.append("회원번호: " + memberNo + "<br>");
              var memberId = data.memberId;
              result.append("회원아이디: " + memberId + "<br>");
              var memberName1 = data.memberName1;
              result.append("회원 이름1: " + memberName1 + "<br>"); //인코딩이 안됨
              var memberName2 = data.memberName2;
              result.append("회원 이름2: " + memberName2 + "<br>"); //디코딩이 되어야함
              var memberName3 = decodeURIComponent(data.memberName2);
              result.append("회원이름3: " + memberName3);
            }
          },
        });
      });
      $("#jQ6").click(function () {
        var result = $(this).next();
        $.ajax({
          url: "/ajaxTest6",
          type: "get",
          success: function (data) {
            if (data.length != 0) {
              var html = "";
              for (var i = 0; i < data.length; i++) {
                var memberNo = data[i].memberNo;
                var memberName = decodeURIComponent(data[i].memberName);
                var phone = data[i].phone;
                html +=
                  "회원번호: " +
                  memberNo +
                  " / 이름: " +
                  memberName +
                  " /전화번호: " +
                  phone +
                  "<br>";
                if (i == 4) {
                  break;
                }
              }
              result.html(html);
            } else {
              result.html("회원 정보가 없습니다.");
            }
          },
        });
      });
      $("#jQ7").click(function () {
        var result = $(this).next();
        $.ajax({
          url: "/ajaxTest7",
          type: "get",
          success: function (data) {
            var html = "";
            var keys = Object.keys(data); //객체의 키값들만 전부 가져옴
            for (var i = 0; i < keys.length; i++) {
              var memberNo = data[keys[i]].memberNo;
              var memberName = decodeURIComponent(data[keys[i]].memberName);
              var phone = data[keys[i]].phone;
              html +=
                "회원번호: " +
                memberNo +
                " / 이름: " +
                memberName +
                " /전화번호: " +
                phone +
                "<br>";
            }
            result.html(html);
          },
        });
      });
      $("#jQ8").click(function () {
        var memberId = $("#ajax8").val();
        var result = $(this).next();
        $.ajax({
          url: "/ajaxTest8",
          data: { memberId: memberId },
          success: function (data) {
            if (data != null) {
              var html = "";
              var memberNo = data.memberNo;
              var memberName = data.memberName;
              var phone = data.phone;
              result.html(
                "회원번호: " +
                  memberNo +
                  " / 이름: " +
                  memberName +
                  " /전화번호: " +
                  phone +
                  "<br>"
              );
            } else {
              result.html("회원정보가 없습니다.");
            }
          },
        });
      });
      $("#jQ9").click(function () {
    	    var result = $(this).next();
    	    $.ajax({
    	      url: "/ajaxTest9",
    	      success: function (data) {
    	    	  if(data.length != 0){
    	    		  var html = "";
    	    		  for(var i=0;i<data.length;i++){
    	    			  var memberNo = data[i].memberNo;
    	    			  var memberName = data[i].memberName;
    	    			  var phone = data[i].phone;
    	    			  html +=
    	    	                ("회원번호: " +
    	    	                memberNo +
    	    	                " / 이름: " +
    	    	                memberName +
    	    	                " /전화번호: " +
    	    	                phone +
    	    	                "<br>");
    	    		  }
    	    		  result.html(html);
    	    	  }else{
    	    		  result.html("회원정보가 없습니다.");
    	    	  }
    	      }
    	    });
    	  });
      $("#jQ10").click(function () {
        var result = $(this).next();
        $.ajax({
          url: "/ajaxTest10",
          success: function (data) {
            var html = "";
            //data 객체에서 순서대로 key 값을 꺼내서 자동으로 for문 수행
            for (var key in data) {
              var memberNo = data[key].memberNo;
              var memberName = data[key].memberName;
              var phone = data[key].phone;
              html +=
                "회원번호: " +
                memberNo +
                " / 이름: " +
                memberName +
                " /전화번호: " +
                phone +
                "<br>";
            }
            result.html(html);
          },
        });
      });
    </script>
  </body>
</html>
