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

				<%-- <select name="keyField"
					style="margin-left: 54%; position: absolute;">
					<option value="name">이름</option>
					<option value="title">제목</option>
					<option value="content">내용</option>
				</select>
				<div id="QnAa"></div>
				      <input type="text" size="3" name="keyWord" placeholder="검색란"
					value="${keyWord }" style="width: 20%; margin-left: 60%"> 
					<input
					type="submit" onClick="check()" name="keyWord"
					style="text-align: center; width: 11%; height:5%;" value="검색"> <input
					type="hidden"  name="page"  value="0">
				</td> --%>
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
				<td height="35"
					style="line-height: 35px; cursor: pointer; text-align: left; border-width:5;">
					<a href="javascript:doDisplay();"></a> 
					<b> <font color="#d0a560">06</font> [결제 관련]
				</b> 입금금액을 더 했어요(또는 적게했어요.)<input type="button" onclick="doDisplay()"
					value="▼" style="width: 8%; margin-left: 43%;">
					<div id="myDIV">
						<h4>주문금액보다 더 많이 입금하신 경우</h4>
						1.초과된 금액이 5천원 미만일 경우 적립금으로 자동 적립됩니다.
						단, 5천원 미만이라도 계좌로 환급받길 원하시는 경우 계좌번호와 성함을 정확히 기재후에 
						Q&A 전화번호로 전화주시기 바랍니다.
						
						초과된 금액이 5천원 이상일 경우에는 고객센터에서 고객님께 연락후 차액을 송금해 드리고 있습니다.
						<h4>주문 금액보다 적게 입금하신 경우</h4>
						주문금액보다 적게 입금하신 경우에는 입금확인이 되질 않습니다.
						부족금액을 입금하셔야만 입금확인이 됩니다.
						
				</td>
				</div>
				</tr>
				</tr>
			<tr>
				<td height="25"
					style="line-height: 35px; cursor: pointer; text-align: left;">
					<b> <font color="#d0a560">07</font>
						[배송 관련]
				</b> 배송 기간이 궁금합니다.<input type="button" onclick="poDisplay()"
					value="▼" style="width: 8%; margin-left: 57%;">
					<div id="myDIVa">
						<p>주문후 입금이 완료되시면 , 바로 제품 준비에 들어갑니다.
						금일 오후 9시까지 주문 들어온 제품들은, 다음날 발송준비에 들어갑니다.
						
						상품준비는 결제완료일로부터 최소 1일부터 ~3일정도가 소요됩니다.(단,수제화는 7일정도)
						공휴일 및 주말에 주문하신경우 제품준비기간이 조금 더 소요될 수 있음을 말씀드립니다.
						
						간혹, 주문 폭주제품이거나 제작중인 제품들은 TransForMan Q&A에서
						고객님께 직접 연락을 드리도록 하겠습니다.
						(전화 부재시 문자로 지연 사실을 알려드립니다.)
						</p></td>
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