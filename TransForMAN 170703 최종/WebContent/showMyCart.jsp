<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
	<head>
		<style>
		</style>
	</head>
	<body>
		<form name="showMyCartForm" style="text-align: center;">
			${myCart }
		</form>
	</body>
	<script>
		$(function() {
			var msg = '${msg}';
			var productId = '${productId}';
			
			if(msg == "수량 오류")
			{
				location.href = "javascript:main('./spDetail', '" + productId + "', '" + msg + "')";
			}
		});
		
		function deleteMyCartItem(productId)
		{
			if(confirm("정말 취소하시겠습니까?") == true)
				location.href = "javascript:main('./deleteMyCartItem', '" + productId + "')";
		}
		
		function orderProduct(productId)
		{
			location.href = "javascript:main('./orderForm', '" + productId + "')";
		}
		
		function toIndex()
		{
			showMyCartForm.action = "./index";
			showMyCartForm.submit();
		}
		
		function orderAllProduct()
		{
			location.href = "javascript:main('./orderForm')";
		}
		
		function deleteMyCartAll()
		{
			if(confirm("정말 취소하시겠습니까?") == true)
				location.href = "javascript:main('./deleteMyCartAll')";
		}
	</script>	
</html>