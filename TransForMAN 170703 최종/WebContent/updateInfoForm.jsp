<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
	<head>
		<style>
		</style>
	</head>
	<body>
		<div><h1>회원정보 수정</h1></div>
		<div id="updateInfo">
			<form name="updateInfoForm" method="post">
				<div class="input"><input type="text" id="userId" name="userId" value='${userId }' readonly /></div>
				<div class="input"><input type="password" id="userPw" name="userPw" placeholder="비밀번호"  /></div>
				<div class="input"><input type="password" id="confirmPw" name="confirmPw" placeholder="비밀번호 확인" /></div>
				<div class="span"><span id="notiPw"></span></div>
				<div class="input"><input type="text" id="userBirth" name="userBirth" value='${userBirth }' /></div>
				<div class="span"><span id="notiBirth">8글자로 입력해주세요. ex)20170612</span></div>
				<div class="input"><input type="text" id="userPostcode" name="userPostcode" value='${userPostcode }'></div>
				<div class="button"><input type="button" class="btn" onclick="execDaumPostcode()" value="우편번호 찾기"></div>
				<div class="input"><input type="text" id="userAddress" name="userAddress" value='${userAddress }'></div>
				<div class="input"><input type="text" id="userAddress2" name="userAddress2" value='${userAddress2 }'></div>
				<div class="span"><span id="notiAddress"></span></div>
				<div class="input"><input type="text" id="userPhone" name="userPhone" value='${userPhone }' /></div>
				<div class="span"><span id="notiPhone">ex)010-0000-0000</span></div>
				<div class="button"><input type="button" class="btn" onClick="updateInfo()" value="수정" /></div>
				<div class="button"><input type="button" class="btn" onClick="checkDeleteMember()" value="회원탈퇴" /></div>
				<div class="button"><input type="button" class="btn" onClick="toIndex()" value="돌아가기" /></div>
						
				<div class="modal fade" id="checkModal" tabindex="-1" role="dialog" aria-hidden="true">
					<div class="vertical-alignment-helper">
						<div class="modal-dialog vertical-align-center">
							<div id="checkType" class="modal-content  panel-info">
								<div class="modal-header panel-heading">
									<button type="button" class="close" data-dismiss="modal">
										<span aria-hidden="true">&times;</span>
										<span class="sr-only">Close</span>
									</button>
									<h4 class="modal-title">
										정말 탈퇴하시겠습니까?
									</h4>
								</div>
								<div class="modal-body" id="checkMessage">
									<input type="password" id="deletePw" name="deletePw" placeholder="현재 비밀번호를 입력하세요."/>
									<div class="span"><span id="notiDeletePw"></span></div>
								</div>
								<div class="modal-footer">
									<input type="button" class="btn btn-primary" onclick="deleteMember()" value="확인" />
								</div>
							</div>
						</div>
					</div>
				</div>
			</form>
		</div>
	</body>
	<script>
		$(function() {
			var msg = '${updateInfoMsg}';
			if(msg == "실패")
				alert("정보수정에 실패했습니다. 다시 시도해주세요.");
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
		
		function updateInfo()
		{
			var pw = $('#userPw').val();				
			var conPw = $('#confirmPw').val();			
			var birth = $('#userBirth').val();			
			var postcode = $('#userPostcode').val();	
			var address = $('#userAddress').val();			
			var address2 = $('#userAddress2').val();	
			var phone = $('#userPhone').val();
			var msg = {};
			
			$.ajax({
				type: 'POST',
				url: './checkUpdateInfoForm',
				data: {userPw:pw, confirmPw:conPw, userBirth:birth, userPostcode:postcode,
					userAddress:address, userAddress2:address2, userPhone:phone},
					
				success:function(data)
				{
					
					msg = data.split(",");
					
					console.log(msg);

					$('#notiPw').html(msg[0]);
					$('#notiBirth').html(msg[1]);
					$('#notiAddress').html(msg[2]);
					$('#notiPhone').html(msg[3]);
					
					if(msg[4] == '0')
					{
						updateInfoForm.action = "./updateInfo";
						updateInfoForm.submit();
					}
				},
				
				error:function(error)
				{
					console.log(error);
				}
			});
		}
		
		function checkDeleteMember()
		{
			$('#checkModal').modal("show");
 		}
		
		function deleteMember()
		{
			
			var deletePw = $('#deletePw').val();
			
			console.log(deletePw);
			
			$.ajax({
				type: 'POST',
				url: './checkDeleteMember',
				data: {deletePw:deletePw},
					
				success:function(data)
				{
					
					msg = data.split(",");
					
					console.log(msg);

					$('#notiDeletePw').html(msg[0]);
					
					if(msg[1] == '0')
					{
						$('#checkModal').modal("hide");
						updateInfoForm.action = "./deleteMember";
						updateInfoForm.submit();
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
			updateInfoForm.action = "./index";
			updateInfoForm.submit();
		}
	</script>	
</html>