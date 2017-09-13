<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<html>
	<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
	<style></style>
	<script src="https://code.jquery.com/jquery-3.2.1.min.js">
</script>
</head>
<style>

/* body{
	font-family: 'Nanum Gothic', sans-serif;
} */
</style>
<!-- <script type="text/javascript">
	function move(url){
		location.href=url;
	} -->

<script>
</script>
<body>
		<form id="showStylingList" name="showStylingList">
			${stylingList}
		</form>
	</body>
<marquee behavior="alternate" scrolldelay="100" direction="right">
Q&A 페이지 입니다.
</marquee>
<!-- <table class="bbs" width="800" height="200" border="2">
<colgroup>
	<col width="50"/>
	<col width="500"/>
	<col width="100"/>
	<col width="50"/>
</colgroup>
<thead>
	<tr>
		<th>번 호</th>
		<th>제 목</th>
		<th>작성자</th>
		<th>날 짜</th>
		<th>조회수</th>
	</tr>
</thead>
<tbody>
	<tr>
		<td align="center">3</td>
		<td><a href="javascript:main('./showQnAlist_View.jsp')" onClick="view3()">상품관련 문의3</a></td>
		<td align="center">qt</td>
		<td align="center">2017/06/29</td>
		<td align="center">1234</td>
	</tr>
	<tr>
		<td align="center">2</td>
		<td><a href="javascript:main('./showQnAlist_View.jsp')" onClick="view2()">상품관려문의2</a></td>
		<td align="center">qt</td>
		<td align="center">2017/06/29</td>
		<td align="center">123</td>
	</tr>
	<tr>
		<td align="center">1</td>
		<td><a href="javascript:main('./showQnAlist_View.jsp')" onClick="view1()">상품관려문의1</a></td>
		<td align="center">qt</td>
		<td align="center">2017/06/29</td>
		<td align="center">123</td>
	</tr>
</tbody>
		<tr>
		<td align="center" colspan="5">1</td>
		</tr>
	</table>
	<div class="button">
		<input type="button" class="btn" onClick="showwrite()" value="글쓰기"
			style="width: 20%; margin-left: 60%" />
	</div>
		<div class="button">
		<input type="button" class="btn" onClick="showdelete()" value="취소"
			style="width: 20%; margin-left: 60%" />
	</div> -->
<!-- </body> -->
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
/* function showwrite(){
	location.href="javascript:main('./insertNotice.jsp')";
}

function showdelete(){
	location.href="javascript:main('./showQnAList.jsp')";
}
function showview3(){
	location.href="javascript:main('./showQnAlist_View.jsp')";
}
function showview2(){
	location.href="javascript:main('./showQnAlist_View.jsp')";
}
function showview1(){
	location.href="javascript:main('./showQnAlist_View.jsp')";
} */
</script>
</html>