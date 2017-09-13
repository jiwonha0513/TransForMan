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
<body>
 <div class="center">
	     <img class="slide-image" src="./images/pp.jpg" alt="">
       </div>
       <br><br>
<div class="button">
		<input type="button" class="btn" onClick="showback()" value="돌아가기"
			style="width: 20%; margin-left: 60%" />
	</div>
</body>
<script>
function showback()//돌아가기
{
	location.href = "javascript:main('./insertNotice.jsp')";
}
</script>
</html>