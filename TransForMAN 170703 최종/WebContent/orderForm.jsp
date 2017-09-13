<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
	<head>
		<style>
		</style>
	</head>
	<body>
		<div><h1>결제 페이지</h1></div>
		${productList }
		<form id="orderForm" name="orderForm">
			<input type="hidden" name="count" value="${count }" />
			<input type="hidden" name="productId" value="${productId }" />
			<br />
			<div class="input"><input type="text" name="name" placeholder="배송받을 이름" /></div>
			<div class="span"><span id="notiName"></span></div>
			<div class="input"><input type="text" name="phone" placeholder="연락처" /></div>
			<div class="span"><span id="notiPhone">ex)010-0000-0000</span></div>
			<div class="input"><input type="text" id="postcode" name="postcode" placeholder="우편번호"></div>
			<div class="button"><input type="button" class="btn" onclick="execDaumPostcode()" value="우편번호 찾기"></div>
			<div class="input"><input type="text" id="address" name="address" placeholder="주소"></div>
			<div class="input"><input type="text" id="address2" name="address2" placeholder="상세주소"></div>
			<div class="span"><span id="notiAddress"></span></div>
			<div>
				결제 수단
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				: 
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<a href="#" onclick="paymentMenu(this)">카드</a>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<a href="#" onclick="paymentMenu(this)">핸드폰</a>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<a href="#" onclick="paymentMenu(this)">무통장 입금</a>
			</div>
			<div id="selectCard">
				<select name="cardSelect">
					<option value="">카드사</option>
					<option value="우리카드">우리카드</option>
					<option value="삼성카드">삼성카드</option>
					<option value="롯데카드">롯데카드</option>
					<option value="농협카드">농협카드</option>
					<option value="현대카드">현대카드</option>
					<option value="BC카드">BC카드</option>
					<option value="신한카드">신한카드</option>
					<option value="국민카드">국민카드</option>
					<option value="하나카드">하나카드</option>
				</select>
				<input type="text" name="cardNumber" class="orderInput" style="width: 75%;" placeholder="카드 번호를 입력하세요.">
			</div>
			<div id="selectPhone">
				<select name="phoneSelect">
					<option value="">통신사</option>
					<option value="SKT">SKT</option>
					<option value="KT">KT</option>
					<option value="LG U+">LG U+</option>
				</select>
				<input type="text" name="phoneNumber" class="orderInput" style="width: 79%;" placeholder="핸드폰 번호를 입력하세요.">
			</div>
			<div id="selectBank">
				<select id="bankSelect" name="bankSelect" onchange="bankBookNumberDisplay()">
					<option value="">은행</option>
					<option value="우리은행">우리은행</option>
					<option value="기업은행">기업은행</option>
					<option value="부산은행">부산은행</option>
					<option value="농협중앙회">농협중앙회</option>
					<option value="수협">수협</option>
					<option value="한국씨티은행">한국씨티은행</option>
					<option value="신한은행">신한은행</option>
					<option value="국민은행">국민은행</option>
					<option value="하나은행">하나은행</option>
				</select>
				<input type="text" name="bankBookNumber" style="width: 68%;" class="orderInput" value="가상 계좌 번호가 표시됩니다." readonly>
			</div>
			<div class="span"><span id="notiPayment"></span></div>
			<div class="button"><input type="button" class="btn" onclick="payment()" value="결제"></div>
			<div class="button"><input type="button" class="btn" onclick="toIndex()" value="돌아가기"></div>
		</form>
	</body>
	<script>
		$(function() {
			$('#selectCard').hide();
			$('#selectPhone').hide();
			$('#selectBank').hide();
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
	                document.getElementById('postcode').value = data.zonecode; //5자리 새우편번호 사용
	                document.getElementById('address').value = fullAddr;
	
	                // 커서를 상세주소 필드로 이동한다.
	                document.getElementById('address2').focus();
	            }
	        }).open();
	    }
		
		function paymentMenu(menu)
		{
			if(menu.text == '카드')
			{
				$('#selectCard').toggle();
				$('#selectPhone').hide();
				$('#selectBank').hide();
			}
			else if(menu.text == '핸드폰')
			{
				$('#selectCard').hide();
				$('#selectPhone').toggle();
				$('#selectBank').hide();
			}
			else if(menu.text == '무통장 입금')
			{
				$('#selectCard').hide();
				$('#selectPhone').hide();
				$('#selectBank').toggle();
			}
		}
		
		function bankBookNumberDisplay() 
		{
			var bank = $('#bankSelect').val();
			
			switch (bank) 
			{
			case '우리은행':
				orderForm.bankBookNumber.value = '1002-358-473892';
				break;
				
			case '기업은행':
				orderForm.bankBookNumber.value = '113-05746-02-073';
				break;
				
			case '부산은행':
				orderForm.bankBookNumber.value = '112-2026-1928-06';
				break;
				
			case '농협중앙회':
				orderForm.bankBookNumber.value = '302-0366-9948-51';
				break;
				
			case '수협':
				orderForm.bankBookNumber.value = '1020-8210-9611';
				break;
				
			case '한국씨티은행':
				orderForm.bankBookNumber.value = '357-04299-269-01';
				break;
				
			case '신한은행':
				orderForm.bankBookNumber.value = '110-38-874302';
				break;
				
			case '국민은행':
				orderForm.bankBookNumber.value = '023-53-4709-132';
				break;
				
			case '하나은행':
				orderForm.bankBookNumber.value = '512-395842-87532';
				break;
			}

			return true;
		}
		
		function payment()
		{
			$.ajax({
				type: 'POST',
				url: './checkOrderForm',
				data: $('#orderForm').serialize(),
				
				success:function(data)
				{
					var msg = data.split(",");
					
					$('#notiName').html(msg[0]);
					$('#notiPhone').html(msg[1]);
					$('#notiAddress').html(msg[2]);
					$('#notiPayment').html(msg[3]);
					
					if(msg[4] == '0')
					{
						insertMyOrder();
						/* orderForm.action = "./insertMyOrder";	
						orderForm.submit(); */
					}
				}
			});
		}
		
		function insertMyOrder()
		{
			$.ajax({
				type: 'POST',
				url: './insertMyOrder',
				data: $('#orderForm').serialize(),
				
				success:function(data)
				{
					location.href="javascript:main('./showOrderComplete', '" + data + "')";
				}
			});
		}
		
		function toIndex()
		{
			insertStylingForm.action = "./index";
			insertStylingForm.submit();
		}
	</script>	
</html>