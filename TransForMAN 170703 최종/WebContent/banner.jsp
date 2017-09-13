<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<html>
	<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">    
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
    	<meta name="viewport" content="width=device-width, initial-scale=1">
    	<meta name="description" content="">
    	<meta name="author" content="">
    	
		<title>Trans For MAN</title>
		
		<link href="css/shop-homepage.css" rel="stylesheet">
		
		<!-- Latest compiled and minified CSS -->
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" 
			integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" 
			crossorigin="anonymous">

		<!-- Optional theme -->
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" 
			integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" 
			crossorigin="anonymous">

		<script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
		<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
		
		<!-- Latest compiled and minified JavaScript -->
		<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" 
			integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" 
			crossorigin="anonymous"></script>
		
		<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
			
	    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
	    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
	    <!--[if lt IE 9]>
	        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
	        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
	    <![endif]-->
	    
<title>Insert title here</title>
	<style></style>
	<script src="https://code.jquery.com/jquery-3.2.1.min.js">
</script>
</head>

<style>
body {
	font-family: 'Nanum Gothic', sans-serif;
}

.cs_btn {
	display: block;
	clear: both;
	width: 100%;
	margin: 30px 0 10px;
	font-family: "Nanum Gothic";
}

.cs_btn li a {
	display: block;
	width: 200px;
	height: 40px;
	background: #333;
	border: 1px solid #111;
	border-raidus: 5px;
	line-height: 40px;
	text-align: center;
	font-size: 12px;
	color: #fff;
	font-weight: 700;
	float: left;
	margin: 0 10px 10px 0;
}

table {
	width: 100%;
}

#content {
	width: 30%;
	margin-left: 70%;
	margin-top: 20;
	padding: 10px;
	background: #393;
}

#rank-list a {
	color: #FFF;
	text-decoration: none;
}

#rank-list a:hover {
	text-decoration: underline;
}

#rank-list {
	overflow: hidden;
	width: 160px;
	height: 20px;
	margin: 0;
}

#rank-list dt {
	display: none;
}

#rank-list dd {
	position: relative;
	margin: 0;
}

#rank-list ol {
	position: absolute;
	top: 0;
	left: 0;
	margin: 0;
	padding: 0;
	list-style-type: none;
}

#rank-list li {
	height: 20px;
	line-height: 20px;
}


</style>
<script type="text/javascript">
	function move(url){
		location.href=url;
	}
</script>
<body>

 <span><font color="#d0a560" style="text-align:center;">실시간 인기 스타일 :</font></span><span class="blinkEle">Best Item!</span>
<div class="row carousel-holder">
                       <div class="col-md-12">
                           <div id="carousel-example-generic" class="carousel slide" data-ride="carousel">
                               <ol class="carousel-indicators">
                                   <li data-target="#carousel-example-generic" data-slide-to="0" class="active"></li>
                                   <li data-target="#carousel-example-generic" data-slide-to="1"></li>
                                   <li data-target="#carousel-example-generic" data-slide-to="2"></li>
                               </ol>
                               <div class="carousel-inner">
                                   <div class="item active">
                                       <img class="slide-image" src="./images/fashion.jpg" alt="">
                                   </div>
                                   <div class="item">
                                       <img class="slide-image" src="./images/traing5.jpg" alt="">
                                   </div>
                                   <div class="item">
                                       <img class="slide-image" src="./images/bluejean.jpg" alt="">
                                   </div>
                               </div>
                               <a class="left carousel-control" href="#carousel-example-generic" data-slide="prev">
                                   <span class="glyphicon glyphicon-chevron-left"></span>
                               </a>
                               <a class="right carousel-control" href="#carousel-example-generic" data-slide="next">
                                   <span class="glyphicon glyphicon-chevron-right"></span>
                               </a>
                           </div>
                       </div>
                   </div>
                   
	<div class="xans-element-xans-pop xans-popup-footer">
	<div style="text-align:center; position:absolute; margin-left:20%; margin-top:24%; font-family:굴림; font-size:15px; padding:3px 5px; color:#ffffff; width:290px; background-color:#313031;">
	<span style="margin-right:20px;">
	"오늘하루 열지 않음"
	<input type="checkbox" value="111 "id="popup_close_check" >
	
	</span>
	<span id="popup_close_btn" style="cursor:pointer;"onclick="closea()">닫기x</span>
	</div>
	</div>
	
</body>
<script>

function closea(){
	 window.close();
}

</script>
<script type="text/javascript">
  setInterval(function(){
    $(".blinkEle").toggle();
  }, 700);
</script>
</html>