<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
	<head>
		<style>
		</style>
	</head>
	<body>
		<form id="showProductList" name="showProductList">
			${productList}
		</form>
	</body>
	<script>
		function deleteProduct1(productId)
		{
			$.ajax({
				type: 'POST',
				url: './deleteProduct',
				data: {productId:productId},
				
				success:function(data)
				{
					$('#showProductList').html(data);
				},
				
				error:function(error)
				{
					console.log(error);
				}
			});
		}
	</script>	
</html>