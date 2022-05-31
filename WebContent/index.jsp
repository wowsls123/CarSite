<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html lang="ko" dir="ltr">
  <head>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
    <meta charset="utf-8">
    <title>RentCarMain</title> 
    <link rel="stylesheet" href="css/common.css">
    <link rel="stylesheet" href="css/main.css">
    
  </head>
  <body>
    <header id="header">
      <h1><a href="index.do">RentCar</a></h1>
      <nav id="membership">
        <h2 class="hidden">멤버쉽네비게이션</h2>
        <ul>
          <li class="goLogin">
          <c:if test="${sessionID != null }">
	        ${sessionID }
	        <a href="logout.do">로그아웃</a>
	        
            </c:if>
            
          <c:if test="${sessionID == null }">
           <c:if test="${ resultlogin == -1 || resultlogin == 0 }">
			<script>
			alert("아이디 혹은 비밀번호가 틀렸습니다.");
			</script>
			</c:if>
         
          	<a href="Login.jsp">로그인</a>
          </c:if>
          </li>
          <li class="goJoin">
          <c:if test="${sessionID != null }">
            	<a href="info_view.do?member_id=${sessionID}">개인정보</a>
            </c:if>
            <c:if test="${sessionID ==null }">
            	<a href="join.jsp">회원가입</a>
            </c:if>
          </li>
        </ul>
      </nav>
      <nav id="gnb">
        <h2 class="hidden">펀웹주요이용메뉴</h2>
        <ul>
          <li><a href="CarSell.do?member_id=${sessionID }">내차 팔기</a></li>
          <li><a href="CarBuy.do">내차 사기</a></li>
          <li><a href="Notice.do">고객센터</a></li>
          <li><a href="#">브랜드인증관</a></li>
          <li><a href="#">전국직영점</a></li>
        </ul>
      </nav>
    </header>
    <hr>
    <main>
      <section id="contactUs"style="width:100%; height: 363px">
        <h2 class="hidden">contact us</h2>
        
        <div id="carouselExampleIndicators" class="carousel slide" data-bs-ride="carousel" style="width:100%; height: 363px">
<div id="carouselExampleSlidesOnly" class="carousel slide" data-bs-ride="carousel">
 <div class="carousel-indicators">
    <button type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide-to="0" class="active" aria-current="true" aria-label="Slide 1"></button>
    <button type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide-to="1" aria-label="Slide 2"></button>
    
  </div>
  <div class="carousel-inner">
    <div class="carousel-item active">
      <img src="https://ifh.cc/g/z38w0A.jpg" class="d-block w-100" alt="유재석사진1">
    </div>
    <div class="carousel-item">
       <img src="https://ifh.cc/g/PTo2TJ.jpg" class="d-block w-100" alt="유재석사진2">
    </div>
  </div>
    <button class="carousel-control-prev" type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide="prev">
    <span class="carousel-control-prev-icon" aria-hidden="true"></span>
    <span class="visually-hidden">Previous</span>
  </button>
  <button class="carousel-control-next" type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide="next">
    <span class="carousel-control-next-icon" aria-hidden="true"></span>
    <span class="visually-hidden">Next</span>
  </button>
</div>


</div>
      </section>
      <div id="solutionWrap">
       <section id="ssolution" style=height:500px;">
      <div id="solutionWrap"  style="width:100%; height: 100%;padding: 50px 150px; ">
        
            <h2 class="hidden">solutions</h2>
            <article class="hosting" style="width: 250px;height: 30px;">
              <a href="#">
                <h3>원하는 차가 있어요</h3>
              </a>
               </article>
              <form action="SellList_select.do" method="post" style="height: 30px; display: flex;">
             
             <div style="display: flex; " class="col-sm-2">
              	<select style="width: 152px;height: 30px; z-index:9999" name="Car_brand">
              		<option style="height: 100px" value="">제조사 선택</option>
              		<option value="대우">대우</option>
              		<option value="아우디">아우디</option>
              		<option value="폭스바겐">폭스바겐</option>
              	</select>
              	
              	<div style="display: flex; " class="col-sm-2">
              	<select style="width: 152px;height: 30px; z-index:9998" name="Car_type">
              		<option value="">타입 선택</option>
              		<option value="경차">경차</option>
              		<option value="소형">소형</option>
              		<option value="중형">중형</option>
              		<option value="대형">대형</option>
              	</select>
              	
              	<div style="display: flex; " class="col-sm-2;">
              	<select style="width: 152px;height: 30px; z-index:9997" name="Car_color">
              		<option value="">컬러 선택</option>
              		<option value="white">white</option>
              		<option value="red">red</option>
              		<option value="black">black</option>
              		<option value="blue">blue</option>
              	</select>
              	 	 </div>
              </div>
              </div>
              <c:if test= "${sessionID != null }">
              <section style="position:absolute; display: flex; height: 60px; width:300px ;flex-direction: column-reverse;">
              <input style="display: flex; height: 30px;width:48px ; justify-content: left " type="submit" value="검색">    
              </section>
              	</c:if>
              	<c:if test= "${sessionID == null }">
              	</c:if>   
           
              	</form>   
           
            <div style="display: flex; align-items: bottom; height: 100%;flex-direction: column;">
               <article class="hosting" style="width: 300px;height: 200px; display: flex; align-items: bottom;">
              <a href="#" style="display: flex;width: 300px;height: 200px; flex-direction:column;">
                <h3 style="display: flex;width: 300px;height: 200px; flex-direction:column-reverse;">예산이 정해져 있어요</h3>
              </a>
              </article>
                <form action="SellList_selectPrice.do" method="post" style="display: flex;display: flex; align-items: bottom; ">
              	<input style="height: 30px" type="text" name="sCar_salePrice1">~<input style="height: 30px" type="text" name="sCar_salePrice2">             	
              	 <c:if test= "${sessionID != null }">
              	<input type="submit" value="검색" style="width:48px; height: 30px; ">
              	</c:if>
              	<c:if test= "${sessionID == null }">
              	</c:if>   
              
              </form>

              </div>
               
              </div>
        </section>
      </div>
      <section id="board">
        <h2 class="hidden">customer center</h2>
        <section id="sec_news">
          <h3 style="font-size: 24px;">공지사항</h3>
         <table style="width:400px; position: relative; left: 25px; top:10px;" >
              <c:forEach items="${Notice}" var="dto"> 
              <tbody>
                <tr>
                  <td class="title" style="font-size: 20px;"><a href="Notice_view.do?bNum=${dto.bNum}">${dto.bTitle }</a></td>
                </tr>
                
              </tbody> 
              </c:forEach>
         </table>
        </section>
        <section id="notice">
          <h3>판매&amp;구매 후기</h3>
          <button onClick="location.href='SellReview.do'">판매 후기</button>
          <button onClick="location.href='BuyReview.do'">구매 후기</button>
          
        </section>
      </section>
    </main>
    <hr>
    <div id="footerWrap">
      <footer id="footer">
        <div class="info">
          <small class="copyright">All contents Copyright 2011 FunWeb Inc. all rights reserved</small>
          <address>Contact mail : funweb@funwebbiz.com Tel: +82 64 123 4315 Fax +82 64 123 4321</address>
        </div>
        <ul class="sns">
          <li class="facebook"><a href="#">페이스북 바로가기</a></li>
          <li class="twitter"><a href="#">트위터 바로가기</a></li>
        </ul>
      </footer>
    </div>
  </body>
</html>