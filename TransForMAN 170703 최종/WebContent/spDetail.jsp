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
	                <h4>&nbsp;&nbsp;${productName }</h4>
	            	<div class="pull-right">
	            		<input type="text" id="countText" name="count" value="1" style="width: 45px;" />
	                	${buttons }
	            	</div>
	            	<div class="pull-right">
	            		<h4>&nbsp;&nbsp;${productPrice } 원</h4>
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
	                <p>${productExplain }</p>
	                ${hashTag }
	            </div>
	        </div>
	        
	        <br />
	        
	        <div class="well">
	        	<form name="spInsertCommentForm" method="post" enctype="multipart/form">
	        		<div id="commentInput" style="margin-bottom: 10px;">
			        	<select id="star" name="star" style="margin-right: 10px;">
			        		<option value="">별점 선택</option>
			        		<option value="1.0">★☆☆☆☆</option>
			        		<option value="2.0">★★☆☆☆</option>
			        		<option value="3.0">★★★☆☆</option>
			        		<option value="4.0">★★★★☆</option>
			        		<option value="5.0">★★★★★</option>
			        	</select>
			        	<input type="text" id="content" name="content" placeholder="후기를 남겨주세요." />
		        	</div>
		        	<div id="commentButton">
		        		<input type="file" id="commentImage" name="imageName" style="width: 250%;"/>
		        		<input type="button" class="btn" id="insertComment" onclick="insertProductComment()" value="등록" />
	        		</div>
	        	</form>
	        	<div id="commentList">
	        		${commentList }
	        	</div>
	        </div>
		</div>
	</body>
	<script>
		$(function() {
			var countMsg = '${countMsg}';
			var count = '${count}';
			
			if(countMsg == '수량 오류')
				alert("해당 상품의 재고는 " + count + "개 입니다.");
		});
		
		function insertMyCart(productId)
		{
			var countText = $('#countText').val();
			location.href = "javascript:main('./insertMyCart', '" + productId + "', '" + countText + "')";
		}
		
		function orderProduct(productId)
		{
			var countText = $('#countText').val();
			location.href = "javascript:main('./orderForm', '" + productId + "', '" + countText + "')";
		}
	
		function updateProductForm(productId)
		{
			location.href = "javascript:main('./updateProductForm', '" + productId + "')";
		}
		
		function deleteProduct1(productId)
		{
				$.ajax({
				type: 'POST',
				url: './deleteProduct',
				data: {productId:productId},
				
				success:function(data)
				{
					$('#showProductList').html(data);
				},
				
				error:function(error)
				{
					console.log(error);
				}
			}); 
		}
		
		function insertProductComment()
		{
			var productName = '${productName }';
			
			var formData = new FormData();
			formData.append("productName", productName);
			formData.append("star", $('#star').val());
			formData.append("content", $('#content').val());
			formData.append("commentImage", $('#commentImage')[0].files[0]);
			
			$.ajax({
				type: 'POST',
				url: './spInsertComment',
				data: formData,
				processData: false,
				contentType: false,
				
				success:function(data)
				{
					$('#commentList').html(data);
				},
				
				error:function(error)
				{
					console.log(error);	
				}
			});
		}
		
		function deleteProductComment(commentId, productId)
		{
			$.ajax({
				type: 'POST',
				url: './spDeleteComment',
				data: {commentId:commentId, productId:productId},
				
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
	</script>	
</html>