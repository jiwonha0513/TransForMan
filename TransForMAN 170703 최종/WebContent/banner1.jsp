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

		<img class="slide-image" src="./images/pop.jpg" alt="">
	</div>
	
	<div class="xans-element-xans-pop xans-popup-footer">
	<div style="text-align:center; position:absolute; margin-left:20%; margin-top:24%; font-family:굴림; font-size:15px; padding:3px 5px; color:#ffffff; width:290px; background-color:#313031;">
	<span style="margin-right:20px;">
	"오늘하루 열지 않음"
	<input type="checkbox" value="111 "id="popup_close_check" >
	
	</span>
	<span id="popup_close_btn" style="cursor:pointer;"onclick="closea()">닫기x</span>
	</div>
	</div>
</body>
<script>

function closea(){
	 window.close();
	   
}
</script>
</html>