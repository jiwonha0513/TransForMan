<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
	<head>
		<style>
		</style>
	</head>
	<body>
		<form id="searchProductForm" name="searchProductForm" method="post">
				<input type="text" id="searchKeyword" name="searchKeyword" placeholder="이름, 내용, 해시태그"/>
				<input type="button" class='btn' id="searchButton" onclick="searchProductList()" value="검색" />
		</form>
		<form id="spCategory" name="spCategory" style="text-align: center;">
			${spCategory}
		</form>
	</body>
	<script>
		function searchProductList()
		{
			var keyword = $('#searchKeyword').val();
			
			$.ajax({
				type: 'POST',
				url: "./spSearchProduct",
				data: {searchKeyword:keyword},
				
				success:function(data)
				{
					$('#spCategory').html(data);
				},
				
				error:function(error)
				{
					console.log(error);
				}
			});
			
		}
	
		function detail(productId)
		{
			location.href = "javascript:main('./spDetail', '" + productId + "')";
		}
	</script>	
</html>