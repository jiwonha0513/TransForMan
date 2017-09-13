<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
	<head>
		<style>
		</style>
	</head>
	<body>
		<form id="showStylingList" name="showStylingList">
			${stylingList}
		</form>
	</body>
	<script>
		function deleteStyling1(stylingId)
		{
 			$.ajax({
				type: 'POST',
				url: './deleteStyling',
				data: {stylingId:stylingId},
				
				success:function(data)
				{
					$('#showStylingList').html(data);
				},
				
				error:function(error)
				{
					console.log(error);
				}
			}); 
		}
	</script>	
</html>