<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
	<head>
		<style>
		</style>
	</head>
	<body>
		<div><h1>회원 관리</h1></div>
		<div id="showMemberList">
			<form id="searchMemberForm" name="searchMemberForm" method="post">
				<input type="text" id="searchKeyword" name="searchKeyword" placeholder="아이디, 별명, 이메일"/>
				<input type="button" class='btn' id="searchButton" onclick="searchMemberList()" value="검색" />
			</form>
			<form id="updateStatusForm" name="updateStatusForm" method="post">
				${memberList}
			</form>
		</div>
	</body>
	<script>
		function searchMemberList()
		{
			var keyword = $('#searchKeyword').val();
			
			$.ajax({
				type: 'POST',
				url: "./selectMemberList",
				data: {searchKeyword:keyword},
				
				success:function(data)
				{
					$('#updateStatusForm').html(data);
				},
				
				error:function(error)
				{
					console.log(error);
				}
			});
			
		}
		
		function updateStatus(id)
		{
			var blackId = id;			
			
			$.ajax({
				type: 'POST',
				url: "./updateStatus",
				data: {blackId:blackId},
				
				success:function(data)
				{
					$('#updateStatusForm').html(data);
				},
				
				error:function(error)
				{
					console.log(error);
				}
			});
		}
	</script>	
</html>