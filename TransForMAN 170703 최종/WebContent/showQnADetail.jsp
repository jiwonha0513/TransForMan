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
<body>

<h1>Q&A</h1>
<div class="button" style="border:1px dashed float:left;"><pre>
		Q & A
		
		Q: <textarea name="content" rows="10" cols="100" style="width:60%; height:40%"/></textarea> 
		
		A: <textarea name="content" rows="10" cols="100" style="width:60%; height:40%"/></textarea>
	</pre>
	<div class="button">
		<input type="button" class="btn" onClick="showcom()" value="확인"
			style="width: 20%; margin-left: 40%" />
	</div>
	</div>
</body>
<script>

function showcom(){
	location.href="javascript:main('./showQnAList.jsp')";
}
</script>
</html>