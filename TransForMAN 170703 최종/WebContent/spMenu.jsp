<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">    
    	
		<title>Insert title here</title>

		<script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>

		<style>
		</style>
	</head>
	<body>
		<form id="searchProductForm" name="searchProductForm" method="post">
				<input type="text" id="searchKeyword" name="searchKeyword" placeholder="이름, 내용, 해시태그"/>
				<input type="button" class='btn' id="searchButton" onclick="searchProductList()" value="검색" />
		</form>
		<form id="spMenu" name="spMenu">
			${spMenu}
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
					$('#spMenu').html(data);
				},
				
				error:function(error)
				{
					console.log(error);
				}
			});
			
		}
	
		function category(url, menuId, categoryId)
		{
			location.href = "javascript:main('" + url + "')";
		}

		function detail(productId)
		{
			location.href = "javascript:main('./spDetail', '" + productId + "')";
		}
	</script>	
</html>