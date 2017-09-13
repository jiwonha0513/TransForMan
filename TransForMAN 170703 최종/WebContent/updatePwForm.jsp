<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
	<head>
		<style>
		</style>
	</head>
	<body>
		<div id="updatePw">
			<div><h3>비밀번호 재설정</h3></div>
			<form name="updatePwForm" method="post">
				<input type="hidden" name="userId" />
				<div class="input"><input type="password" id="userPw" name="userPw" placeholder="비밀번호"  /></div>
				<div class="input"><input type="password" id="confirmPw" name="confirmPw" placeholder="비밀번호 확인" /></div>
				<div class="span"><span id="notiPw"></span></div>
				<div class="button"><input type="button" class="btn" onClick="updatePw()" value="비밀번호 변경" /></div>
				<div class="button"><input type="button" class="btn" onClick="toIndex()" value="돌아가기" /></div>
			</form>
		</div>
	</body>
	<script>
	
		function updatePw()
		{
			var pw = $('#userPw').val();				
			var conPw = $('#confirmPw').val();
			
			$.ajax({
				type: 'POST',
				url: './checkUpdatePwForm',
				data: {userPw:pw, confirmPw:conPw},
					
				success:function(data)
				{
					msg = data.split(",");
					
					console.log(msg);

					$('#notiPw').html(msg[0]);
					
					console.log(updatePwForm.userId.value);
					
					if(msg[1] == '0')
					{
						updatePwForm.action="./updatePw"
						updatePwForm.submit();
					}
				},
				
				error:function(error)
				{
					console.log(error);
				}
			}); 		
		}
		function toIndex()
		{
			updatePwForm.action = "./index";
			updatePwForm.submit();
		}
	</script>	
</html>