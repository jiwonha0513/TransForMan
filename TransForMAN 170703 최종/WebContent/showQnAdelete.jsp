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
<script type="text/javascript">
	function move(url) {
		location.href=url;
	}
	function showDeleteCheck() {
		alert("삭제 되었습니다.");
		var form = document.showQnADeleteForm;
		return true;
	}

	function showDeleteCheck(){
		alert("삭제 되었습니다.");
		location.href="javascript:main('./showQnALista.jsp')";
		
	}
	function showDelete(){
		location.href="javascript:main('./showQnALista.jsp')";
		
	}
</script>
<style>
body {
	font-family: 'Nanum Gothic', sans-serif;
}
</style>
<body>
<table width=50% cellspacing=0 cellpadding=3>
 		<tr>
			<td bgcolor=#dcdcdc height=21 align=center>
			작성자의 비밀번호를 입력해 주세요.</td>
		</tr>
	</table>
	
	<table width=70% cellspacing=0 cellpadding=2>
		<form name="showQnADeleteForm" method="post" 
		onsubmit="return showDeleteCheck();" ></form>
 	
    <tr> 
     	<td align=center>  
	  	<input type=password name="password" size=17 maxlength=15>
	 	</td> 
    </tr>
    <tr>
		<td align=center>
		<input type="submit" value="삭제완료" onclick="showDeleteCheck();">
		<input type=button value="뒤로" onClick="showDelete()">
		</td>
	</tr> 
	</table>
	
</body>
<script>
</script>
</html>