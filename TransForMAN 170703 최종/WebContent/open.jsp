<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<html>
	<head>
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
</style>
<script type="text/javascript">
function move(url){
	location.href=url;
}
</script>


<div class="center">
	     <img class="slide-image" src="./images/come.jpg" alt="">
			<tr>
				<td height="35"
					style="line-height: 35px; cursor: pointer; text-align: left; border-width:5;">
					<b> <font color="#d0a560" size="10">환영합니다!!</font> 
				</b> 
						<h3>안녕하세요!!</h3>
<h4>남성의류,패션 쇼핑몰 TransForMan입니다.

TransForMan이 5월 13일 공식 오픈합니다!

오픈기념 무료배송과 사은품 증정 이벤트도 준비하였습니다.
관련내용은 공지사항 게시판을 참고해주세요~^^

TransForMan과 함께 시원한 여름 보내세요!</h4>
						
				</td>
				</tr>
				</div>



	<div class="button">
		<input type="button" class="btn" onClick="showback()" value="돌아가기"
			style="width: 20%; margin-left: 60%" />
	</div>
	
	</form>
	</div>
</body>
<script>
function showback()//돌아가기
{
	location.href = "javascript:main('./insertNotice.jsp')";
}

</script>

</html>