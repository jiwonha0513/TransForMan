<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
	<head>
		<style>
		</style>
	</head>
	<body>
		<div class="detail">
	        <div class="detailThumbnail">
	            <img class="img-responsive" src=${imagePath } alt="">
	            <div class="caption-full">
	               <h4>&nbsp;&nbsp;${stylingName }</h4>
	               <div class="pull-right">
	                	${buttons }
	            	</div>
		            <div class="ratings">
		                <p>
		                    <span class="glyphicon glyphicon-star"></span>
		                    <span class="glyphicon glyphicon-star"></span>
		                    <span class="glyphicon glyphicon-star"></span>
		                    <span class="glyphicon glyphicon-star"></span>
		                    <span class="glyphicon glyphicon-star-empty"></span>
		                    4.0 stars
		                </p>
		            </div>
	                ${video }
	                <p>${stylingExplain }</p>
	                ${productList }
	                <br />
	                ${hashTag }
	            </div>
	        </div>
	        
	        <br />
	        
	        <div class="well">
	        	<form name="stInsertCommentForm" method="post" enctype="multipart/form">
	        		<div id="commentInput" style="margin-bottom: 10px;">
			        	<select id="star" name="star" style="margin-right: 10px;">
			        		<option value="">별점 선택</option>
			        		<option value="1.0">★☆☆☆☆</option>
			        		<option value="2.0">★★☆☆☆</option>
			        		<option value="3.0">★★★☆☆</option>
			        		<option value="4.0">★★★★☆</option>
			        		<option value="5.0">★★★★★</option>
			        	</select>
			        	<input type="text" id="content" name="content" placeholder="후기를 남겨주세요."/>
		        	</div>
		        	<div id="commentButton">
		        		<input type="file" id="commentImage" name="imageName" style="width: 250%;"/>
		        		<input type="button" class="btn" id="insertComment" onclick="insertStylingComment()" value="등록" />
	        		</div>
	        	</form>
	        	<div id="commentList">
	        		${commentList }
	        	</div>
	        </div>
		</div>
	</body>
	<script>
		function updateStylingForm(stylingId)
		{
			location.href="javascript:main('./updateStylingForm', '" + stylingId + "')";
		}
		
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
	
		function detail(productId)
		{
			location.href = "javascript:main('./spDetail', '" + productId + "')";
		}
		
		function insertMyCart(productId)
		{
			var countText = 1;
			
			location.href = "javascript:main('./insertMyCart', '" + productId + "', '" + countText + "')";
		}
		
		function orderProduct(productId)
		{
			var countText = 1;
			location.href = "javascript:main('./orderForm', '" + productId + "', '" + countText + "')";
		}
		
		function insertStylingComment()
		{
			var stylingName = '${stylingName }';
			
			var formData = new FormData();
			formData.append("stylingName", stylingName);
			formData.append("star", $('#star').val());
			formData.append("content", $('#content').val());
			formData.append("commentImage", $('#commentImage')[0].files[0]);
			
			$.ajax({
				type: 'POST',
				url: './stInsertComment',
				data: formData,
				processData: false,
				contentType: false,
				
				success:function(data)
				{
					$('#commentList').html(data);
					$('#content').val("");
					$('#commentImage').val("");
				},
				
				error:function(error)
				{
					console.log(error);	
				}
			});
		}
		
		function deleteStylingComment(commentId, stylingId)
		{
			$.ajax({
				type: 'POST',
				url: './stDeleteComment',
				data: {commentId:commentId, stylingId:stylingId},
				
				success:function(data)
				{
					$('#commentList').html(data);
					$('#star').val("");
					$('#content').val("");
					$('#commentImage').val("");
				},
				
				error:function(error)
				{
					console.log(error);
				}
			}); 
		}
	</script>	
</html>