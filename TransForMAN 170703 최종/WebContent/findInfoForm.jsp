<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
	<head>
		<style>
		</style>
	</head>
	<body>
		<div id="findInfo">
			<div><h3>아이디 찾기</h3></div>
			<form name="selectIdForm" method="post">
				<div class="input"><input type="text" id="userName" name="userName" placeholder="이름" autofocus /></div>
				<div class="span"><span id="notiName"></span></div>
				<div class="input"><input type="text" id="userBirth" name="userBirth" placeholder="생년월일" /></div>
				<div class="span"><span id="notiBirth">8글자로 입력해주세요. ex)20170612</span></div>
				<div class="input"><input type="text" id="userEmail1" name="userEmail" placeholder="이메일" /></div>
				<div class="span"><span id="notiEmail1"></span></div>
				<div class="button"><input type="button" class="btn" onClick="selectId()" value="아이디 찾기" /></div>
				<div class="button"><input type="button" class="btn" onClick="toIndex()" value="돌아가기" /></div>
			</form>
			
			<br /><hr /><br />
			
			<div><h3>비밀번호 찾기</h3></div>
			<form name="findPwForm" method="post">
				<div class="input"><input type="text" placeholder="아이디" id="userId" name="userId" /></div>
				<div class="span"><span id="notiId"></span></div>
				<div class="input"><input type="text" id="userEmail2" name="userEmail" placeholder="이메일" /></div>
				<div class="span"><span id="notiEmail2"></span></div>
				<div class="input"><input type="text" id="userCode" name="userCode" placeholder="인증번호" /></div>
				<div class="span"><span id="notiCode"></span></div>
				<div class="button"><input type="button" class="btn" onClick="sendCode()" value="인증번호 발송" /></div> 
				<div class="button"><input type="button" class="btn" onClick="updatePw()" value="비밀번호 찾기" /></div>
				<div class="button"><input type="button" class="btn" onClick="toIndex()" value="돌아가기" /></div>
			</form>
		</div>
	</body>
	<script>
		function sendCode()
		{
			var flag = 2;
			var email = $('#userEmail2').val();
			
			if(email!='')
			{
				$.ajax({
					type: 'POST',
					url: './sendCode',
					data: {flag:flag, userEmail:email},
					
					success:function(data)
					{
						var msg = data.split(",");
						console.log(data);
						$('#notiEmail2').html(msg[0]);
						$('#notiCode').html(msg[1]);
					},
					
					error:function(error)
					{
						console.log(error);
					}
				}); 
			}
			else
			{
				$('#notiEmail').html("이메일을 입력해주세요");
			}
		}
		
		function selectId()
		{
			var flag = 1;
			
			var name = $('#userName').val();				
			var birth = $('#userBirth').val();			
			var email = $('#userEmail1').val();
			
			$.ajax({
				type: 'POST',
				url: './checkFindInfoForm',
				data: {flag:flag, userName:name, userBirth:birth, userEmail:email},	
				
				success:function(data)
				{
					msg = data.split(",");
					
					console.log(msg);

					$('#notiName').html(msg[0]);
					$('#notiBirth').html(msg[1]);
					$('#notiEmail1').html(msg[2]);
					
					if(msg[3] == '0')
					{
						selectIdForm.action = "./selectId";
						selectIdForm.submit();
					}
				},
				
				error:function(error)
				{
					console.log(error);
				}
			});
			
		}
		
		function updatePw()
		{
			var flag = 2;
			
			var id = $('#userId').val();				
			var email = $('#userEmail2').val();	
			var code = $('#userCode').val();
			
			$.ajax({
				type: 'POST',
				url: './checkFindInfoForm',
				data: {flag:flag, userId:id, userEmail:email, userCode:code},	
				
				success:function(data)
				{
					msg = data.split(",");
					
					console.log(msg);

					$('#notiId').html(msg[0]);
					$('#notiEmail2').html(msg[1]);
					$('#notiCode').html(msg[2]);
					
					if(msg[3] == '0')
					{
						location.href="javascript:main('./updatePwForm', '" + id +"')";
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
			selectIdForm.action = "./index";
			selectIdForm.submit();
			findPwForm.action = "./index";
			findPwForm.submit();
		}
	</script>	
</html>