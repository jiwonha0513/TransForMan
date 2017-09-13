<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<html>
<head>
<link href="stylesheet.css" media="screen" rel="stylesheet"
	type="text/css" />
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.6.4/jquery.min.js"
	type="text/javascript"></script>
<script src="application.js" type="text/javascript"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style></style>
<script src="https://code.jquery.com/jquery-3.2.1.min.js">
	
</script>

</head>
<style>
body {
	font-family: 'Nanum Gothic', sans-serif;
}

.cs_btn {
	display: block;
	clear: both;
	width: 100%;
	margin: 30px 0 10px;
	font-family: "Nanum Gothic";
}

.cs_btn li a {
	display: block;
	width: 200px;
	height: 40px;
	background: #333;
	border: 1px solid #111;
	border-raidus: 5px;
	line-height: 40px;
	text-align: center;
	font-size: 12px;
	color: #fff;
	font-weight: 700;
	float: left;
	margin: 0 10px 10px 0;
}

table {
	width: 100%;
}

#content {
	width: 30%;
	margin-left: 70%;
	margin-top: 20;
	padding: 10px;
	background: #393;
}

#rank-list a {
	color: #FFF;
	text-decoration: none;
}

#rank-list a:hover {
	text-decoration: underline;
}

#rank-list {
	overflow: hidden;
	width: 160px;
	height: 20px;
	margin: 0;
}

#rank-list dt {
	display: none;
}

#rank-list dd {
	position: relative;
	margin: 0;
}

#rank-list ol {
	position: absolute;
	top: 0;
	left: 0;
	margin: 0;
	padding: 0;
	list-style-type: none;
}

#rank-list li {
	height: 20px;
	line-height: 20px;
}
</style>
<script type="text/javascript">
	function move(url){
		location.href=url;
	}
</script>
<body>

	<div class="center">

		<img class="slide-image" src="./images/Notice.jpg" alt="">
	</div>
	<div id="content">
		<dl id="rank-list">
			<dt>실시간 급상승 검색어</dt>
			<dd>
				<ol>
					<li><a href="javascript:main('./open.jsp')">1순위 : 공식오픈!!</a></li>
					<li><a href="javascript:main('./many.jsp')">2순위: 회원 문의</a></li>
					<li><a href="javascript:main('./.jsp')">3순위 : 올해 추석배송!</a></li>
					<li><a href="javascript:main('./.jsp')" onclick="stylingMenu(this)">4순위 : 스타일링 헤어!</a></li>
					<li><a href="javascript:main('./.jsp')" onclick="shoppingMenu(this)">5순위 : 쇼핑몰 상의!</a></li>

				</ol>
			</dd>
		</dl>
	</div>
	<br>
	<br>
	<div id="on">
		<table border="0px">
			<tr>
				<td height="35" width="100"
					style="line-height: 35px; cursor: pointer; text-align: left; border-width: 5;">
					<a href="javascript:main('./open.jsp')" id="pass"> <b> <font
							color="#d0a560">01</font> [공지사항]
					</b><span class="blinkEle"> TransForMan 공식오픈!!</span>
				</a>

				</td>
			</tr>
			<tr>
				<td height="25"
					style="line-height: 35px; cursor: pointer; text-align: left;">
					<a href="javascript:main('./insertNotice1.jsp')" id="pass"> <b>
							<font color="#d0a560">02</font> [공지사항]
					</b> 5월 휴무알림!!
				</a>

				</td>
			</tr>

			<tr>
				<td height="25"
					style="line-height: 35px; cursor: pointer; text-align: left;">
					<b> <font color="#d0a560">03</font> [공지사항]
				</b> 올해 추석 배송!!<input type="button" onclick="goDisplay()" value="▼"
					style="width: 8%; margin-left: 65%;">
					<div id="myDIVd">
						<p>민족 대명절 추석을 맞이하여 TransForMan도 휴무에 들어갑니다 10월 2일 밤 12:00
							주문건(입금완료) 까지 3일 배송출고되시구요 그 이후 주문 상품은 주문 순차에 따라 10월10일부터 순차 출고되십니다

							+ 부득이하게 9일 주문하셔도 물품에 따라 추석 이후에 받으시는 상품이 있을수 있어요ㅠㅠ 이점은 따로 개별적으로 연락
							드릴께요 TransForMan가족들도, 거래처도, 택배사도 누군가의 가족이잖아요 명절엔 다들 쉬어야죠 :) 배송지연
							양해 부탁드려요~~!! 다들 즐거운 설명절 보내셔서 힐링합시다 :) 항상감사해요! 10월 10일에 만나요~!!!!</p>
				</td>
				</div>
				</a>

			</tr>
		</table>
		<div class="button">
			<input type="button" class="btn" onClick="showback()" value="돌아가기"
				style="width: 20%; margin-left: 60%" />
		</div>
</body>



<script type="text/javascript">
  setInterval(function(){
    $(".blinkEle").toggle();
  }, 800);
</script>

<script>

	function showback()//돌아가기
	{
		location.href = "./index";
	}
	/* function insertNotice() {
		location.href = "javascript:main('./insertNotice.jsp')";
	} */
	$(function() {
	    var count = $('#rank-list li').length;
	    var height = $('#rank-list li').height();

	    function step(index) {
	        $('#rank-list ol').delay(2000).animate({
	            top: -height * index,
	        }, 500, function() {
	            step((index + 1) % count);
	        });
	    }

	    step(1);
	});
	$("#myDIVd").hide();
	function goDisplay() {
		var con = document.getElementById("myDIVd");
		if (con.style.display == 'none') {
			con.style.display = 'block';
		} else {
			con.style.display = 'none';
		}
	}

	
</script>
</html>