<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style></style>
<script src="https://code.jquery.com/jquery-3.2.1.min.js">
	
</script>
<style>
#main{
margin-top :30;
}
#container{
margin-top:20%;
}
body {
	font-family: 'Nanum Gothic', sans-serif;
}
.cs_btn{
display:block;
clear:both;
width:100%;
margin:30px 0 10px;
font-family:"Nanum Gothic";

}
.cs_btn li a{
display:block;
width:200px;
height:40px;
background:#333;
border:1px solid #111;
border-raidus:5px;
line-height:40px;
text-align:center;
font-size:12px;
color:#fff;
font-weight:700;
float:left;
margin:0 10px 10px 0;
}
table{
width:100%;
}

</style>
</head>
<body>
	<div id="QnA">
		<div id="all">
			<div id="select"></div>
			<form name="search" action="QnAList.action" method="post"
				enctype="multipart/form-data">

				<!-- <select name="keyField"
					style="margin-left: 54%; position: absolute;">
					<option value="name">이름</option>
					<option value="title">제목</option>
					<option value="content">내용</option>
				</select> -->
				<%-- <div id="QnAa"></div>
				      <input type="text" size="3" name="keyWord" placeholder="검색란"
					value="${keyWord }" style="width: 20%; margin-left: 60%"> 
					<input
					type="submit" onClick="check()" name="keyWord"
					style="text-align: center; width: 11%; height:5%;" value="검색"> <input
					type="hidden"  name="page"  value="0"> --%>
				</td>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 
				    
			</form>
		</div>
	</div>
	
	<div id="on">
	<table width="100%" border="0">
		<tbody>
			<tr>
				<td height="35" width="100"
					style="line-height: 35px; cursor: pointer; text-align: left; border-width:5;">
					<a href="javascript:doDisplay();"></a> 
					<b> <font color="#d0a560">01</font> [ID,PW 찾기]
				</b> ID와 PW를 찾으려면 어떻게 해야하나요?<input type="button" onclick="doDisplay()"
					value="▼" style="width: 8%; margin-left: 41%;">
					<div id="myDIV">
						<p>로그인 > 아이디 / 비밀번호 찾기 > 이름,생년월일 작성후 이메일 입력 > ID,PW가 바로 확인
							됩니다.</p>
				</td>
				</div>
			</tr>

			<tr>
				<td height="25"
					style="line-height: 35px; cursor: pointer; text-align: left;">
					<b> <font color="#d0a560">02</font>
						[기타문의]
				</b> 현금영수증은 어떻게 신청하나요?<input type="button" onclick="poDisplay()"
					value="▼" style="width: 8%; margin-left: 49%;">
					<div id="myDIVa">
						<p>현금영수증은 주문하신후 취소,반품하는 경우가생길수 있으므로 배송완료 후에 구매확정이 되면 발행처리가
							가능하며 국세청 홈페이지를 통해 확인하실수있습니다.</p></td>


				</div>
			</tr>
			<tr>
				<td height="25"
					style="line-height: 35px; cursor: pointer; text-align: left;">
					<b> <font color="#d0a560">03</font>
						[기타문의]
				</b> 카드 취소후 확인이 되질 않습니다.<input type="button" onclick="qoDisplay()"
					value="▼" style="width: 8%; margin-left: 48%;">
					<div id="myDIVb">
						<p>취소처리가 완료 되었다고 하더라도 승인사와 카드사 간의 전산 처리 시간으로 인하여 7~10일 후에 
 							카드사 전산에서 확인이 가능합니다.
							단, 신용카드 결제일자에 맞추어 대금이 청구될 수 있으며 이 경우 익월 신용카드 대금청구시 카드사에서 환급처리됩니다.
							궁금하신 사항이나 이해되지 않는부분들은 카드사로 문의를 해주시기 바라며, 이에 대해서 궁금하신 점이 있으시면 
 							상담센터나 1:1문의 게시판을 이용해서 문의 주시기 바랍니다.</p></td>
				</div>
			</tr>
			<tr>
				<td height="25"
					style="line-height: 35px; cursor: pointer; text-align: left;">
					<b> <font color="#d0a560">04</font>
						[환불문의]
				</b> 카드,무통장,휴대폰 결제시 환불은 어떻게 해야하나요?<input type="button" onclick="xoDisplay()"
					value="▼" style="width: 8%; margin-left: 31%; margin-top:-30;">
					<div id="myDIVc">
						<p>카드결제및 실시간계좌이체,휴대폰인증결제는 구매금액에 따라 변경되는 경우가 많이 발생됨에 따라 
						상담센터나 1:1게시판을 이용해주시기 바랍니다. 
<h4>현금결제경우</h4>
현금결제와 같은경우 환불원하시는 상품에대해선 
그 금액 그대로 환불을 해드립니다!! 
<h4>휴대폰인증결제경우</h4>
휴대폰결제건에 대해서 상품을 구매후 환불요청하시면 당월건일경우
승인취소가 지원이되지만 이월된경우엔 휴대폰결제수단 (주)다날 자체에서 지원이 되지 않기때문에 
이용에 다소 불편하실 수 있으니 이점 유의하여 결제해주시기 바랍니다. 
휴대폰결제경우 부분취소기능이 지원되지 않으므로
부분취소요청시엔 수령하신 재화의 물품대금만큼만 따로 결제해주시면 전체취소 처리가 가능 합니다.
<h4>카드결제경우</h4>
기존에는 카드 결제를 해주셨을 경우
되도록이면 실 구매 금액의 재입금 후 
전체 결제 금액을 취소하는 방법으로 부탁드렸는데요^^
시스템 업그레이드로 카드 결제 부분취소가 가능하게 되었습니다
 부분취소시 수수료 부담이 없으시지만 묶음 배송으로 인한 
택배비 환불은 수수료 부담으로 인하여 
되도록이면 적립금으로 양해를 구하고 있습니다^^
-위와 같은처리가 불가능하신분께선 카드사에서 머시따쪽으로 청구하는 3.5%의 수수료와
부가세 10%, (소득세별도) 이상의 수수료를 부담하시는 경우가 생길수있으니 
꼭 참고하시어 주문결제해주시기 바랍니다!! 
<h4>실시간계좌이체경우</h4>
실시간계좌이체에 대해서 결제후 취소요청을하시면 전체취소시 요청시 전체취소가 가능하며
부분취소시 요청시 부분취소된 재화의 물품대금만큼만 따로 환불해드립니다.
</p></td>
				</div>
			</tr>
			<tr>
				<td height="25"
					style="line-height: 35px; cursor: pointer; text-align: left;">
					<b> <font color="#d0a560">05</font>
						[회원문의]
				</b> 회원정보 수정은 어디서하나요?<input type="button" onclick="goDisplay()"
					value="▼" style="width: 8%; margin-left: 51%;">
					<div id="myDIVd">
						<p>로그인 > 내 정보 > 수정 > 입력후 수정 버튼을 누르면 정보가 변경됩니다.</p></td>
					</div>
			</tr>
		</tbody>
		</div>
		<div>
		<tr>
			<td height="25"
			style="line-height:35px;">
			<b><font color="#d0a560"></font>
			 <a href="javascript:main('./many.jsp')">[1]</a>
			</b>
			<b><font color="#d0a560"></font>
			 <a href="javascript:main('./insertNoticea.jsp')">[2]</a>
			</b>
		</td>
		</tr>
		</div>
	</table>
	<div class="button">
		<input type="button" class="btn" onClick="showback()" value="돌아가기"
			style="width: 20%; margin-left: 60%"; />
		</div>
	
</body>
<script>
	$("#myDIV").hide();
	$("#myDIVa").hide();
	$("#myDIVb").hide();
	$("#myDIVc").hide();
	$("#myDIVd").hide();
	
	
	function doDisplay() {
		var con = document.getElementById("myDIV");
		if (con.style.display == 'none') {
			con.style.display = 'block';
		} else {
			con.style.display = 'none';
		}
	}
	function poDisplay() {
		var con = document.getElementById("myDIVa");
		if (con.style.display == 'none') {
			con.style.display = 'block';
		} else {
			con.style.display = 'none';
		}
	}
	function qoDisplay() {
		var con = document.getElementById("myDIVb");
		if (con.style.display == 'none') {
			con.style.display = 'block';
		} else {
			con.style.display = 'none';
		}
	}
	function xoDisplay() {
		var con = document.getElementById("myDIVc");
		if (con.style.display == 'none') {
			con.style.display = 'block';
		} else {
			con.style.display = 'none';
		}
	}
	function goDisplay() {
		var con = document.getElementById("myDIVd");
		if (con.style.display == 'none') {
			con.style.display = 'block';
		} else {
			con.style.display = 'none';
		}
	}

	function pass() {
		location.href = "javascript:main('./showQnADetail.jsp')";

	}
	function check() {
		console.log("${page.pagePerBlock}" + ":" + "${page.nowBlock}" + ":"
				+ "${page.nowPage}")

		if (document.search.keyWord.value == "") {
			alert("검색어를 입력하세요.");
			document.search.keyWord.focus();
			return;
		}
		document.search.submit();
	}
	function blockMoveb() {
		console.log("##" + document.blockmoveb.nowBlock.value);
		document.blockmoveb.submit();
	}
	var xmlHttp;
	var xmlDoc;
	var message;

	function list() {
		document.list.action = "QnAList.action";
		document.list.submit();

	}
	function showback()//돌아가기
	{
		location.href = "javascript:main('./showQnAList.jsp')";
	}
</script>
</html>