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
      <h3>결제모듈</h3>
      <hr />
      <div class="shopping">
        <div>
          <img src="/img/mintchoco.jpg" id="goods" />
        </div>
        <div>
          <h4>민초</h4>
          <div class="star">
            <img src="/img/star-on.png" />
            <img src="/img/star-on.png" />
            <img src="/img/star-on.png" />
            <img src="/img/star-on.png" />
            <img src="/img/star-on.png" />
            <div>5.0</div>
          </div>
          <hr />
          <div class="price">
            <span>2000</span>원
            <span>(1개당 가격)</span>
          </div>
          <div class="count">
            <span>수량</span>
            <button class="btn btn-secondary btn-sm">-</button>
            <span class="amount">1</span>
            <button class="btn btn-secondary btn-sm">+</button>
          </div>
          <div class="total">
            총
            <span id="totalPrice">2000</span>원
          </div>
          <button id="payment" aclass="btn btn-danger">결제하기</button>
        </div>
      </div>
    </div>
    <%@ include file ="/WEB-INF/views/common/footer.jsp" %>
  </body>
  <script>
    $(".count>button").click(function () {
      var currAmount = Number($(".amount").html());
      if ($(this).html() == "+") {
        $(".amount").html(currAmount + 1);
      } else {
        if (currAmount != 1) {
          $(".amount").html(currAmount - 1);
        }
      }
      currAmount = Number($(".amount").html());
      var price = Number($(".price>span").first().html());
      $("#totalPrice").html(currAmount * price);
    });
    $("#payment").click(function () {
      var price = $("#totalPrice").html();
      var d = new Date();
      var date =
        d.getFullYear() +
        "" +
        (d.getMonth() + 1) +
        "" +
        d.getDate() +
        "" +
        d.getHours() +
        "" +
        d.getMinutes() +
        "" +
        d.getSeconds();
      IMP.init("imp09120871"); //결제api 사용을 위한 코드입력
      IMP.request_pay(
        {
          merchant_uid: "상품명_" + date, //거래 아이디
          name: "결제 테스트", //결제 이름
          amount: price, //결제 금액
          buyer_email: "next007@iei.or.kr", //구매자 email 주소
          buyer_name: "park",
          buyer_phone: "010-9605-2115",
          buyer_addr: "경기도 김포시",
          buyer_postcode: "10174",
        },
        function (rsp) {
          if(rsp.success) {
            //결제 성공시
            alert("결제 성공");
            console.log("카드 승인번호: " + rep.apply_num);
          } else {
            //결제 실패시
            alert("결제 실패");
          }
        }
      );
    });
  </script>
  <script
    type="text/javascript"
    src="https://cdn.iamport.kr/js/iamport.payment-1.1.5.js"
  ></script>
</html>
