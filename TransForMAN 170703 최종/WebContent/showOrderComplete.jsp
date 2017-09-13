<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
	<head>
		<style>
		</style>
	</head>
	<body>
		<div><h1>주문이 완료되었습니다.</h1></div>
		<form id="showOrderCompleteForm" name="showOrderCompleteForm">
			${productList }
			${orderInfo }
			${paymentInfo }
			
			<br />
			<div class="button"><input type="button" class="btn" onClick="toIndex()" value="돌아가기" /></div>
		</form>
	</body>
	<script>
		function toIndex()
		{
			showOrderCompleteForm.action = "./index";
			showOrderCompleteForm.submit();
		}
	</script>	
</html>