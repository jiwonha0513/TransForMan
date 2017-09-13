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
<body>
	
	  <div class="center">
	     <img class="slide-image" src="./images/Notice2.jpg" alt="">
       </div>
       <br><br>
       <div class="cs_btn"><ul style=float:left;>
       <li>
       <a href="javascript:main('./many.jsp')">FAQ 자주찾는 질문</a>
       </li>
       <br>
       <!-- <li style=float:left;>
       <a href="javascript:main('./insertNotice.jsp')" onClick="insertNotice()">1:1문의</a>
       </li> -->
        </ul>
       </div>
	<div class="button">
		<input type="button" class="btn" onClick="showback()" value="돌아가기"
			style="width: 20%; margin-left: 60%" />
	</div>
	<!-- <div class="button">
		<input type="button" class="btn" onClick="showwrite()" value="쓰기"
			style="width: 20%; margin-left: 60%" />
	</div> -->
	</form>
	</div>
</body>




</script>
<script>
	function showback()//돌아가기
	{
		location.href = "./index";
	}
	/* function insertNotice() {
		location.href = "javascript:main('./insertNotice.jsp')";
	} */
	
	
</script>
</html>