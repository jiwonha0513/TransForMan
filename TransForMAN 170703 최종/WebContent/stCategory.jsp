<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
	<head>
		<style>
		</style>
	</head>
	<body>
		<form id="searchStylingForm" name="searchStylingForm" method="post">
				<input type="text" id="searchKeyword" name="searchKeyword" placeholder="이름, 내용, 해시태그"/>
				<input type="button" class='btn' id="searchButton" onclick="searchStylingList()" value="검색" />
		</form>
		<form id="stCategory" name="stCategory" style="text-align: center;">
			${stCategory}
		</form>
	</body>
	<script>
		function searchStylingList()
		{
			var keyword = $('#searchKeyword').val();
			
			$.ajax({
				type: 'POST',
				url: "./stSearchStyling",
				data: {searchKeyword:keyword},
				
				success:function(data)
				{
					$('#stCategory').html(data);
				},
				
				error:function(error)
				{
					console.log(error);
				}
			});
		}

		function detail(stylingId)
		{
			location.href = "javascript:main('./stDetail', '" + stylingId + "')";
		}
	</script>	
</html>