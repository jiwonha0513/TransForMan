<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
	<head>
		<style>
		</style>
	</head>
	<body>
		<div><h1>내 정보 </h1></div>
		${myInfo }
		<br />
		<div id="showMyInfo">
			<form name="showMyInfo">
				${button }
			</form>
		</div>
	</body>
	<script>
		function updateInfo()
		{
			location.href="javascript:main('./updateInfoForm')";
		}
	</script>	
</html>