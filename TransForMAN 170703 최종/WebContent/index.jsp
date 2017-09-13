<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="keywords" content="">
<meta name="author" content="">
  


<title>Trans For MAN</title>

<link href="css/shop-homepage.css" rel="stylesheet">
 <link href="bootstrap-assets/css/bootstrap.min.css" rel="stylesheet">
  <link href="plugins/owl-carousel/owl.carousel.css" rel="stylesheet">
    <link href="plugins/owl-carousel/owl.theme.css" rel="stylesheet">
    <link href="plugins/owl-carousel/owl.transitions.css" rel="stylesheet">
    <link href="plugins/Lightbox/dist/css/lightbox.css" rel="stylesheet">
    <link href="plugins/Icons/et-line-font/style.css" rel="stylesheet">
    <link href="plugins/animate.css/animate.css" rel="stylesheet">
    <link href="css/main.css" rel="stylesheet">
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
	integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u"
	crossorigin="anonymous">

<!-- Optional theme -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css"
	integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp"
	crossorigin="anonymous">

<script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>

<!-- Latest compiled and minified JavaScript -->
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"
	integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa"
	crossorigin="anonymous"></script>

<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css">


<style>

.navbar-nav {
	float: right;
}

.carousel-inner>.item>img {
	height: 400px;
	width:550;
}

.input, .button {
	padding: 3px;
}

#join, #login, #findInfo, #updatePw, #updateInfo, #showMyInfo,
	#insertProduct, #insertStyling, #orderForm, #showOrderCompleteForm {
	width: 400px;
	margin: auto;
	padding: 4px;
}

#memberList, #stylingList, #productList {
	width: 100%;
	font-size: 11pt;
}

.detail {
	width: 600px;
	margin: auto;
	padding: 4px;
}

fieldset {
	padding: 13px;
}

#agreement {
	border: 1px solid lightgrey;
}

#agree {
	float: right;
}

fieldset>legend {
	text-align: center;
}

h1, h3 {
	text-align: center;
}

#selectMemberForm {
	float: right;
}

#searchKeyword {
	width: 65%;
}

#searchButton {
	width: 25%;
	margin-left: 10px;
}

.orderInput {
	margin-left: 10px;
}

input[type='text'], input[type='password'], input[type='button'], input[type='file']
	{
	width: 100%;
	height: 35px;
	padding-left: 10px;
}

.span {
	color: red;
	font-size: 8pt;
	text-align: center;
}

.address, .cart, .order {
	border-top-color: white;
}

#toggleBlack, #deleteStyling, #deleteProduct, #insertComment,
	#deleteCart, #orderCart, #indexBtn, #orderAll {
	width: 100%;
	padding: 0px;
	font-size: 9pt;
}

table {
	border-bottom: 1px solid grey;
	width: 550px;
	margin: auto;
}

table, tr, td {
	border-top: 1px solid grey;
	border-collapse: collapse;
	text-align: center;
	padding: 10px;
}

#select {
	text-align: center;
}

select {
	height: 35px;
}

textarea {
	width: 100%;
	height: 150px;
	padding-left: 10px;
	padding-right: 10px;
	resize: none;
}

#thumbnail {
	width: 150px;
	height: 120px;
	margin: 10px;
}

#smallThumbnail {
	width: 80px;
	height: 50px;
}

.thumbnail {
	width: 180px;
	height: 175px;
	text-align: center;
	display: inline-block;
	margin: 10px;
}

span {
	text-align: center;
}

a, a:link, a:hover, a:active, a:visited {
	color: grey;
	text-decoration: none;
}

.detailThumbnail {
	display: block;
	padding: 0px;
	margin: auto;
	line-height: 1.42857143;
	border: 1px solid #ddd;
	border-radius: 4px;
}

iframe {
	width: 100%;
	margin-bottom: 15px;
}

#styling_menu, #shopping_menu {
	font-size: 10pt;
}

p {
	padding: 10px;
}

.pull-right {
	margin-right: 15px;
	display: flex;
}

img {
	margin: auto;
}

.detailBtn {
	margin-left: 15px;
}

#commentInput, #commentButton {
	display: flex;
}

.commentImage {
	margin-right: 10px;
	margin-bottom: 10px;
}

#testimonials {
  background-image: url("./images/Testimonials.jpg");
  -moz-background-size: cover;
  -o-background-size: cover;
  -webkit-background-size: cover;
  background-size: cover;
  background-position: center;
  background-repeat: no-repeat;
  background-attachment: fixed;
  padding: 60px 0;
  text-align: center;
}
#testimonials h2 {
  text-transform: uppercase;
  color: #fff;
}
#testimonials p {
  color: #fff;
}
#testimonials .item .quote {
  margin: 20px auto;
  width: 80%;
}
#testimonials .item .quote .right {
  margin-left: 40px;
  font-size: 32px;
  color: #fff;
}
#testimonials .item .quote .left {
  float: left;
  margin-right: 40px;
  color: #fff;
}
#testimonials .item h5 {
  color: #fff;
  line-height: 36px;
}
#testimonials .item h5 span {
  font-weight: 700;
  text-transform: uppercase;
}
#testimonials .owl-pagination .owl-page.active span {
  background-color: #fff;
}
#testimonials .owl-pagination .owl-page span {
  border: 2px solid #fff;
  border-radius: 50%;
  background: transparent;
  opacity: 1;
  -moz-transition: all 0.3s ease-in-out;
  -o-transition: all 0.3s ease-in-out;
  -webkit-transition: all 0.3s ease-in-out;
  transition: all 0.3s ease-in-out;
}

footer {
  background: #24242a;
  padding: 60px 0;
  text-align: center;
  margin-bottom:-1;
  margin-top:-1;
}
footer h1 {
  text-transform: uppercase;
  font-weight: 700;
}
footer .social {
  margin: 40px 0;
}
footer .social a {
  color: #fff;
  margin-left: 20px;
  margin-right: 20px;
  -moz-transition: all 0.3s ease-in-out;
  -o-transition: all 0.3s ease-in-out;
  -webkit-transition: all 0.3s ease-in-out;
  transition: all 0.3s ease-in-out;
}
footer .social a:hover,
footer .social a:focus,
footer .social a:active {
  color: #a3a3a3;
}
footer h6 {
  text-transform: uppercase;
  color: #fff;
}

</style>
</head>
<body>
  
	<!-- Navigation -->
	<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
		<div class="container">
			<!-- Brand and toggle get grouped for better mobile display -->
			<div class="navbar-header">
				<button type="button" class="navbar-toggle" data-toggle="collapse"
					data-target="#bs-example-navbar-collapse-1">
					<span class="sr-only">Toggle navigation</span> <span
						class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="./index">Trans For MAN</a>
			</div>

			<!-- 개인 메뉴 -->
			<div class="collapse navbar-collapse"
				id="bs-example-navbar-collapse-1">
				<ul class="nav navbar-nav">
					<!-- 로그인 전 -->
					<c:if test="${login==0 }">
						<li><a href="javascript:main('./loginForm')">로그인</a></li>
						<li><a href="javascript:main('./joinForm')">회원가입</a></li>
					</c:if>
					<!-- 관리자 로그인 -->
					<c:if test="${login==1 }">
						<li><a href="javascript:main('./showMyInfo')">내 정보</a></li>
						<li><a href="javascript:main('./showMemberList')">회원관리</a></li>
						<li><a href="javascript:main('./showStylingList')">스타일링
								관리</a></li>
						<li><a href="javascript:main('./showProductList')">상품 관리</a>
						</li>
						<li><a href="#">게시글 관리</a></li>
						<li><a href="./logout">로그아웃</a></li>
					</c:if>
					<!-- 판매자 로그인 -->
					<c:if test="${login==2 }">
						<li><a href="javascript:main('./showMyInfo')">내 정보</a></li>
						<li><a href="javascript:main('./registerStylingForm')">스타일링
								등록</a></li>
						<li><a href="javascript:main('./registerProductForm')">상품
								등록</a></li>
						<li><a href="javascript:main('./showStylingList')">스타일링
								관리</a></li>
						<li><a href="javascript:main('./showProductList')">상품 관리</a>
						</li>
						<li><a href="#">주문 관리</a></li>
						<li><a href="./logout">로그아웃</a></li>
					</c:if>
					<!-- 일반회원 로그인 -->
					<c:if test="${login==3 }">
						<li><a href="javascript:main('./showMyInfo')">내 정보</a></li>
						<li><a href="javascript:main('./showMyCart')">장바구니</a></li>
						<li><a href="#">구매내역 조회</a></li>
						<li><a href="./logout">로그아웃</a></li>
					</c:if>
				</ul>
			</div>
			<!-- /.navbar-collapse -->
		</div>
		<!-- /.container -->
	</nav>
<!-- 메뉴 -->
			<div class="col-md-3">
				<br />
				<div class="list-group" style="text-align:center;">
					<a href="javascript:styling()" class="list-group-item">스타일링</a>
					<div id="styling_menu" class="list-group">
						<a href="#" onclick="stylingMenu(this)" id="hair1"
							class="list-group-item">- 헤어&메이크업</a> <a href="#"
							onclick="stylingMenu(this)" id="coordi" class="list-group-item">-
							코디</a>
					</div>
					<a href="javascript:shopping()" class="list-group-item">쇼핑몰</a>
					<div id="shopping_menu" class="list-group">
						<a href="#" onclick="shoppingMenu(this)" id="hair2"
							class="list-group-item">- 헤어&메이크업</a> <a href="#"
							onclick="shoppingMenu(this)" id="top" class="list-group-item">-
							상의</a> <a href="#" onclick="shoppingMenu(this)" id="bottoms"
							class="list-group-item">- 하의</a> <a href="#"
							onclick="shoppingMenu(this)" id="outer" class="list-group-item">-
							아우터</a> <a href="#" onclick="shoppingMenu(this)" id="accessory"
							class="list-group-item">- 악세서리&etc</a>
					</div>
					<a href="javascript:main('./insertNotice.jsp')" id="Not"
						class="list-group-item">공지사항</a> <a
						href="javascript:main('./showQnAList.jsp')" id="QnA"
						class="list-group-item">Q&A</a>
				</div>
			</div>
			
			<!-- AJAX부분 -->
			<div id="main" class="col-md-9">

				<div class="row carousel-holder">

					<div class="col-md-12">
						<div id="carousel-example-generic" class="carousel slide"
							data-ride="carousel">
							<ol class="carousel-indicators">
								<li data-target="#carousel-example-generic" data-slide-to="0"
									class="active"></li>
								<li data-target="#carousel-example-generic" data-slide-to="1"></li>
								<li data-target="#carousel-example-generic" data-slide-to="2"></li>
							</ol>
							<div class="carousel-inner">
								<div class="item active">
									<img class="slide-image" src="./images/남자모델.PNG" alt="">
								</div>
								<div class="item">
									<img class="slide-image" src="./images/남자모델2.jpg" alt="">
								</div>
								<div class="item">
									<img class="slide-image" src="./images/남자모델3.jpg" alt="">
								</div>
							</div>
							<a class="left carousel-control" href="#carousel-example-generic"
								data-slide="prev"> <span
								class="glyphicon glyphicon-chevron-left"></span>
							</a> <a class="right carousel-control"
								href="#carousel-example-generic" data-slide="next"> <span
								class="glyphicon glyphicon-chevron-right"></span>
							</a>
						</div>
					</div>

				</div>
			</div>

		</div>

	</div>
	<!-- Page Content -->
	  <section id="portfolio">
        <div class="container-fluid">
            <hr class="sep">
            <div class="row">
            <div id="maina" style="margin-top:40%";>
                <div class="col-lg-4 col-sm-6 wow fadeInUp" data-wow-delay=".3s">
                    <a class="portfolio-box" href="image/portfolio/1.jpg" data-lightbox="image-1" data-title="Your caption">
                        <img src="./images/bluejean3.jpg" class="img-responsive" alt="1">
                        <div class="portfolio-box-caption">
                            <div class="portfolio-box-caption-content">
                                <div class="project-category text-faded" style="text-align:center;">
                                    	남성바지
                                </div>
                                <div class="project-name" style="text-align:center;">
                                  	청바지
                                </div>
                            </div>
                        </div>
                    </a>
                </div>
                <div class="col-lg-4 col-sm-6 wow fadeInUp" data-wow-delay=".3s">
                    <a href="./images/2.jpg" class="portfolio-box" data-lightbox="image-2" data-title="Your caption">
                        <img src="./images/pants6.jpg" class="img-responsive" alt="2">
                        <div class="portfolio-box-caption">
                            <div class="portfolio-box-caption-content">
                                <div class="project-category text-faded" style="text-align:center;">
                               		     남성바지
                                </div>
                                <div class="project-name" style="text-align:center;">
                              		     반바지 
                                </div>
                            </div>
                        </div>
                    </a>
                </div>
                <div class="col-lg-4 col-sm-6 wow fadeInUp" data-wow-delay=".3s">
                    <a href="./images/3.jpg" class="portfolio-box" data-lightbox="image-3" data-title="Your caption">
                        <img src="./images/makeup01.png" class="img-responsive" alt="3">
                        <div class="portfolio-box-caption">
                            <div class="portfolio-box-caption-content" >
                                <div class="project-category text-faded" style="text-align:center;">
                                  		  남성
                                </div>
                                <div class="project-name" style="text-align:center;">
									메이크업
                                </div>
                            </div>
                        </div>
                    </a>
                </div>
                <div class="col-lg-4 col-sm-6 wow fadeInUp" data-wow-delay=".3s">
                    <a href="./images/4.jpg" class="portfolio-box" data-lightbox="image-4" data-title="Your caption">
                        <img src="./images/HOOD.jpg" class="img-responsive" alt="4">
                        <div class="portfolio-box-caption">
                            <div class="portfolio-box-caption-content">
                                <div class="project-category text-faded" style="text-align:center;">
                                    	남성
                                </div>
                                <div class="project-name" style="text-align:center;">
									후드티
	                                </div>
                            </div>
                        </div>
                    </a>
                </div>
                <div class="col-lg-4 col-sm-6 wow fadeInUp" data-wow-delay=".3s">
                    <a href="./images/5.jpg" class="portfolio-box" data-lightbox="image-5" data-title="Your caption">
                        <img src="./images/traing3.jpg" class="img-responsive" alt="5">
                        <div class="portfolio-box-caption">
                            <div class="portfolio-box-caption-content">
                                <div class="project-category text-faded" style="text-align:center;">
                                   	남자
                                </div>
                                <div class="project-name" style="text-align:center;">
                                    	트레이닝복
                                </div>
                            </div>
                        </div>
                    </a>
                </div>
                <div class="col-lg-4 col-sm-6 wow fadeInUp" data-wow-delay=".3s">
                    <a href="./images/6.jpg" class="portfolio-box" data-lightbox="image-6" data-title="Your caption">
                        <img src="./images/hairstyle.jpg" class="img-responsive" alt="6">
                        <div class="portfolio-box-caption">
                            <div class="portfolio-box-caption-content">
                                <div class="project-category text-faded" style="text-align:center;">
										남성
                                </div>
                                <div class="project-name" style="text-align:center;">
									헤어스타일                                    

                                </div>
                            </div>
                        </div>
                    </a>
                    </div>
                </div>
            </div>
        </div>
    </section>

			

	<!-- /.container -->

    <section id="testimonials">
        <div class="container">
            <h2>TransForMan</h2>
            <hr class="light-sep">
            <p>What Clients Say About Us</p>
            <div id="owl-testi" class="owl-carousel owl-theme">
                <div class="item">
                    <div class="quote">
                        <i class="fa fa-quote-left left fa-2x"></i>
                        <h5>I’am amazed, I should say thank you so much for your awesome template. Design is so 
good and neat, every detail has been taken care these team are realy amazing and talented! I will 
work only with <span>Rise</span>.<i class="fa fa-quote-right right fa-2x"></i></h5>

                    </div>
                </div>
                <div class="item">
                    <div class="quote">
                        <i class="fa fa-quote-left left fa-2x"></i>
                        <h5>I’am amazed, I should say thank you so much for your awesome template. Design is so 
good and neat, every detail has been taken care these team are realy amazing and talented! I will 
work only with <span>Rise</span>.<i class="fa fa-quote-right right fa-2x"></i></h5>

                    </div>
                </div>
                <div class="item">
                    <div class="quote">
                        <i class="fa fa-quote-left left fa-2x"></i>
                        <h5>I’am amazed, I should say thank you so much for your awesome template. Design is so 
good and neat, every detail has been taken care these team are realy amazing and talented! I will 
work only with <span>Rise</span>.<i class="fa fa-quote-right right fa-2x"></i></h5>

                    </div>
                </div>
            </div>
        </div>
    </section>

		<!-- Footer -->
		<footer>
        <div class="container">
            <h1>Trans For Man</h1>
            <div class="social">
                <a href=""><i class="fa fa-facebook fa-2x"></i></a>
                <a href="#"><i class="fa fa-twitter fa-2x"></i></a>
                <a href="#"><i class="fa fa-dribbble fa-2x"></i></a>
            </div>
            <h6>&copy; 2017 Rise.Development By YOLO</h6>
        </div>
    </footer>

	<div class="modal fade" id="checkModal" tabindex="-1" role="dialog"
		aria-hidden="true">
		<div class="vertical-alignment-helper">
			<div class="modal-dialog vertical-align-center">
				<div id="checkType" class="modal-content  panel-info">
					<div class="modal-header panel-heading">
						<button type="button" class="close" data-dismiss="modal">
							<span aria-hidden="true">&times;</span> <span class="sr-only">Close</span>
						</button>
						<h4 class="modal-title"></h4>
					</div>
					<div class="modal-body" id="checkMessage"></div>
					<div class="modal-footer">
						<button type="button" class="btn btn-primary" data-dismiss="modal">확인</button>
					</div>
				</div>
			</div>
		</div>
	</div>

</body>

<script type="text/javascript">
var popupX = (window.screen.width / 2) - (200 / 2);
var popupY= (window.screen.height /2) - (300 / 2);
   // 팝업을 띄우기, width 300, height 400, 스크롤바, 툴바, 메뉴바를 모두 숨기는 경우
  
   window.open('banner.jsp', '', 'status=no, height=500, width=600, left='+ popupX + ', top='+ popupY + ', screenX='+ popupX + ', screenY= '+ popupY);
   </script>
<script type="text/javascript">
   // 팝업을 띄우기, width 300, height 400, 스크롤바, 툴바, 메뉴바를 모두 숨기는 경우
   window.open('banner1.jsp', 'popup02', 'width=500, height=600, scrollbars= 0, toolbar=0, menubar=no'); 
	
   
   </script>

<script>
	
		$(function() {
			
			$('#styling_menu').hide();
			$('#shopping_menu').hide();
			
			var msg = '${msg}';
			if(msg == "로그인 필요")
			{
				$('.modal-title').html("로그인 필요");
				$('#checkMessage').html("로그인을 해주세요.");
				$('#checkModal').modal("show");
			}
			else if(msg == "권한 없음")
			{
				$('.modal-title').html("권한 오류");
				$('#checkMessage').html("해당 권한이 없습니다.");
				$('#checkModal').modal("show");
			}
			
			var findInfo = '${findInfoMsg}';
			if(findInfo == "성공")
			{
				$('.modal-title').html("아이디 찾기 성공");
				$('#checkMessage').html("메일을 확인해주세요.");
				$('#checkModal').modal("show");
			}
			else if(findInfo == "실패")
			{
				$('.modal-title').html("아이디 찾기 실패");
				$('#checkMessage').html("입력하신 정보가 존재하지 않습니다..");
				$('#checkModal').modal("show");
			}
			
			var updatePw = '${updatePwMsg}';
			
			if(updatePw == "실패")
			{
				$('.modal-title').html("비밀번호 재설정 실패");
				$('#checkMessage').html("입력하신 정보와 가입한 정보가 일치하지 않습니다.");
				$('#checkModal').modal("show");
			}
			else if(updatePw == "성공")
			{
				$('.modal-title').html("비밀번호 재설정 성공");
				$('#checkMessage').html("로그인 해주세요.");
				$('#checkModal').modal("show");
			}
			
			var updateInfo = '${updateInfoMsg}';
			
			if(updateInfo == "실패")
			{
				$('.modal-title').html("정보 수정 실패");
				$('#checkMessage').html("다시 시도해주세요.");
				$('#checkModal').modal("show");
			}
			else if(updateInfo == "성공")
			{
				$('.modal-title').html("정보 수정 성공");
				$('#checkMessage').html("다시 로그인 해주세요.");
				$('#checkModal').modal("show");
			}
		
		});
	
		function styling()
		{
			$('#styling_menu').toggle();
		}
		
		function stylingMenu(menu)
		{
			console.log(menu.text);
			
			if(menu.text == "- 헤어&메이크업")
			{
				$('#hair1').css("background-color", "#f5f5f5");
				$('#coordi').css("background-color", "white");
				
				location.href = "javascript:main('./stBeauty')";
			}
			else
			{
				$('#coordi').css("background-color", "#f5f5f5");
				$('#hair1').css("background-color", "white");
				
				location.href = "javascript:main('./stCoordi')";
			}
		}
		
		function shopping()
		{
			$('#shopping_menu').toggle();
		}
		
		function shoppingMenu(menu)
		{
			console.log(menu.text);
			
			if(menu.text == "- 헤어&메이크업")
			{
				$('#hair2').css("background-color", "#f5f5f5");
				$('#top').css("background-color", "white");
				$('#bottoms').css("background-color", "white");
				$('#outer').css("background-color", "white");
				$('#accessory').css("background-color", "white");
				
				location.href = "javascript:main('./spBeauty')";
			}
			else if(menu.text == "- 상의")
			{
				$('#hair2').css("background-color", "white");
				$('#top').css("background-color", "#f5f5f5");
				$('#bottoms').css("background-color", "white");
				$('#outer').css("background-color", "white");
				$('#accessory').css("background-color", "white");
				
				location.href = "javascript:main('./spTop')";
			}
			else if(menu.text == "- 하의")
			{
				$('#hair2').css("background-color", "white");
				$('#top').css("background-color", "white");
				$('#bottoms').css("background-color", "#f5f5f5");
				$('#outer').css("background-color", "white");
				$('#accessory').css("background-color", "white");
				
				location.href = "javascript:main('./spBottoms')";
			}
			else if(menu.text == "- 아우터")
			{
				$('#hair2').css("background-color", "white");
				$('#top').css("background-color", "white");
				$('#bottoms').css("background-color", "white");
				$('#outer').css("background-color", "#f5f5f5");
				$('#accessory').css("background-color", "white");
				
				location.href = "javascript:main('./spOuter')";
			}
			else
			{
				$('#hair2').css("background-color", "white");
				$('#top').css("background-color", "white");
				$('#bottoms').css("background-color", "white");
				$('#outer').css("background-color", "white");
				$('#accessory').css("background-color", "#f5f5f5");
				
				location.href = "javascript:main('./spAccessory')";
			}

		}
	
		function main(url, data, data2)
		{
			$.ajax({
				url: url,
				type: 'POST',
				data : {data:data, data2:data2},
				
				success:function(html)
				{
					$('#main').html(html);
					if(url == './updatePwForm' && data != '')
						updatePwForm.userId.value = data;
				},
				
				error:function(error)
				{
					console.log(error);
				}
			});
		}
	</script>
</html>