<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
	<head>
		<style>
		</style>
	</head>
	<body>
		<div><h1>로그인</h1></div>
		<div id="login">
			<form name="loginForm" method="post">
				<div class="input"><input type="text" placeholder="아이디" id="userId" name="userId" autofocus /></div>
				<div class="span"><span id="notiId"></span></div>
				<div class="input"><input type="password" placeholder="비밀번호" id="userPw" name="userPw" /> </div>
				<div class="span"><span id="notiPw"></span></div>
				<div class="span"><span id="notiCheck"></span></div>
				<div class="button"><input type="button"  class="btn" onClick="login()" value="로그인" /></div>
				<div class="button"><input type="button"  class="btn" onClick="join()" value="회원가입" /></div>
				<div class="button"><input type="button"  class="btn" onClick="findInfo()" value="아이디 / 비밀번호 찾기" /></div>
			</form>
		</div>
	</body>
	<script>
		function login()
		{
			var id = $('#userId').val();
			var pw = $('#userPw').val();			
			
			$.ajax({
				type: 'POST',
				url: './checkLoginForm',
				data: {userId:id, userPw:pw},
				
				success:function(data)
				{
					msg = data.split(",");
					
					console.log(msg);

					$('#notiId').html(msg[0]);
					$('#notiPw').html(msg[1]);
					
					if(msg[2] == 0)
					{
						loginForm.action = "./login";
						loginForm.submit();
					}
					else
					{
						$('#notiCheck').html(msg[3]);	
					}
				},
				
				error:function(error)
				{
					console.log(error);
				}
			});
		}
		
		function join()
		{
			location.href="javascript:main('./joinForm')";
		}
		
		function findInfo()
		{
			location.href="javascript:main('./findInfoForm')";
		}
	</script>	
</html>