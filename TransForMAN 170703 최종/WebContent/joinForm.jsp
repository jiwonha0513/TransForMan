<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
	<head>
		<style>
		</style>
	</head>
	<body>
		<div><h1>회원가입</h1></div>
		<div id="agreement">
			<fieldset>
				<legend>회원가입 약관</legend>
				귀사는 회원가입, 각종 서비스의 제공을 위해 
				최초 회원가입 당시 아래와 같은 최소한의 개인정보를 필수항목으로 수집하고 있습니다.<br /><br />
				회원가입<br />
				- 필수 사항 : 아이디, 비밀번호, 이름, 성별, 별명, 생년월일, 주소, 휴대폰 번호, 메일주소, 가입인증번호<br /> 
				- 선택 사항 : 사업자 번호<br>
			</fieldset>
		</div><br />
		<p id="agree"><input type="checkbox" id="checkAgree"/>&nbsp;&nbsp;&nbsp;회원가입 약관에 동의합니다.</p>
		
		<br /><hr />
		<div id="join">
			<form name="joinForm" method="post">
				<div class="input"><input type="text" id="userId" name="userId" placeholder="아이디" autofocus /></div>
				<div class="span"><span id="notiId"></span></div>
				<div class="input"><input type="password" id="userPw" name="userPw" placeholder="비밀번호"  /></div>
				<div class="input"><input type="password" id="confirmPw" name="confirmPw" placeholder="비밀번호 확인" /></div>
				<div class="span"><span id="notiPw"></span></div>
				<div class="input"><input type="text" id="userName" name="userName" placeholder="이름" /></div>
				<div class="span"><span id="notiName"></span></div>
				<div class="input"><input type="text" id="userNickname" name="userNickname" placeholder="별명" /></div>
				<div class="span"><span id="notiNick"></span></div>
				<div class="input"><input type="text" id="userBirth" name="userBirth" placeholder="생년월일" /></div>
				<div class="span"><span id="notiBirth">8글자로 입력해주세요. ex)20170612</span></div>
				<div class="input"><input type="text" id="userPostcode" name="userPostcode" placeholder="우편번호"></div>
				<div class="button"><input type="button" class="btn" onclick="execDaumPostcode()" value="우편번호 찾기"></div>
				<div class="input"><input type="text" id="userAddress" name="userAddress" placeholder="주소"></div>
				<div class="input"><input type="text" id="userAddress2" name="userAddress2" placeholder="상세주소"></div>
				<div class="span"><span id="notiAddress"></span></div>
				<div class="input"><input type="text" id="userPhone" name="userPhone" placeholder="핸드폰 번호" /></div>
				<div class="span"><span id="notiPhone">ex)010-0000-0000</span></div>
				<div class="input"><input type="text" id="userEmail" name="userEmail" placeholder="이메일" /></div>
				<div class="span"><span id="notiEmail"></span></div>
				<div class="input"><input type="text" id="userCode" name="userCode" placeholder="인증번호" /></div>
				<div class="button"><input type="button" class="btn" onClick="sendCode()" value="인증번호 발송" /></div> 
				<div class="span"><span id="notiCode"></span></div>
				<div class="input"><input type="text" name="userBusiness" placeholder="사업자 번호" /></div>
				<div class="span"><span id="notiBusiness">판매자만 입력해주세요.</span></div>
				<div class="button"><input type="button" class="btn" onClick="join()" value="회원가입" /></div> 
				<div class="button"><input type="button" class="btn" onClick="toIndex()" value="돌아가기" /></div>
			</form>
		</div>
	</body>
	<script>
		$(function() {
			var msg = '${joinMsg}';
			if(msg == "실패")
				alert("회원가입에 실패했습니다. 다시 시도해주세요.");
		});
	
		function execDaumPostcode() {
	        new daum.Postcode({
	            oncomplete: function(data) {
	                // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.
	
	                // 각 주소의 노출 규칙에 따라 주소를 조합한다.
	                // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
	                var fullAddr = ''; // 최종 주소 변수
	                var extraAddr = ''; // 조합형 주소 변수
	
	                // 사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
	                if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
	                    fullAddr = data.roadAddress;
	
	                } else { // 사용자가 지번 주소를 선택했을 경우(J)
	                    fullAddr = data.jibunAddress;
	                }
	
	                // 사용자가 선택한 주소가 도로명 타입일때 조합한다.
	                if(data.userSelectedType === 'R'){
	                    //법정동명이 있을 경우 추가한다.
	                    if(data.bname !== ''){
	                        extraAddr += data.bname;
	                    }
	                    // 건물명이 있을 경우 추가한다.
	                    if(data.buildingName !== ''){
	                        extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
	                    }
	                    // 조합형주소의 유무에 따라 양쪽에 괄호를 추가하여 최종 주소를 만든다.
	                    fullAddr += (extraAddr !== '' ? ' ('+ extraAddr +')' : '');
	                }
	
	                // 우편번호와 주소 정보를 해당 필드에 넣는다.
	                document.getElementById('userPostcode').value = data.zonecode; //5자리 새우편번호 사용
	                document.getElementById('userAddress').value = fullAddr;
	
	                // 커서를 상세주소 필드로 이동한다.
	                document.getElementById('userAddress2').focus();
	            }
	        }).open();
	    }
		
		function sendCode()
		{
			var flag = 1;
			var email = $('#userEmail').val();
			
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
						$('#notiEmail').html(msg[0]);
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
		
		function join()
		{	
			var agree = $('#checkAgree').is(':checked');
			
			if (agree)
			{
				var id = $('#userId').val();
				var pw = $('#userPw').val();				
				var conPw = $('#confirmPw').val();			
				var name = $('#userName').val();				
				var nickname = $('#userNickname').val();			
				var birth = $('#userBirth').val();			
				var postcode = $('#userPostcode').val();	
				var address = $('#userAddress').val();			
				var address2 = $('#userAddress2').val();	
				var phone = $('#userPhone').val();
				var email = $('#userEmail').val();			
				var code = $('#userCode').val();	
				
				$.ajax({
					type: 'POST',
					url: './checkJoinForm',
					data: {userId:id, userPw:pw, confirmPw:conPw, userName:name, 
						userNickname:nickname, userBirth:birth, userPostcode:postcode,
						userAddress:address, userAddress2:address2, userPhone:phone,
						userEmail:email, userCode:code},
						
					success:function(data)
					{
						var msg = data.split(",");
						
						console.log(msg);
	
						$('#notiId').html(msg[0]);
						$('#notiPw').html(msg[1]);
						$('#notiName').html(msg[2]);
						$('#notiNick').html(msg[3]);
						$('#notiBirth').html(msg[4]);
						$('#notiAddress').html(msg[5]);
						$('#notiPhone').html(msg[6])
						$('#notiEmail').html(msg[7]);
						$('#notiCode').html(msg[8]);
						
						if(msg[9] == '0')
						{
							joinForm.action = "./insertMember";
							joinForm.submit();
						}
					},
					
					error:function(error)
					{
						console.log(error);
					}
				}); 		
			}
			else
				alert("회원가입 약관에 동의해주세요.");
		}
		
		function toIndex()
		{
			joinForm.action = "./index";
			joinForm.submit();
		}
	</script>	
</html>