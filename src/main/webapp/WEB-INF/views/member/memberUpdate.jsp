<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
    
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
<meta name="description" content="">
<meta name="author" content="">
<link rel="icon" href="../../favicon.ico">

<title>Jsp</title>

<%@ include file="../layout/commonLib.jsp" %> 
<script src="https://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script>
$(document).ready(function(){
	$('#zipcodeBtn').on('click', function(){
	    new daum.Postcode({
	        oncomplete: function(data) {
		        console.log(data)
		        
		        $('#addr1').val(data.roadAddress);
		        $('#zipcode').val(data.zonecode);
	            // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분입니다.
	            // 예제를 참고하여 다양한 활용법을 확인해 보세요.
	        }	
	    }).open();
	})

	
	$('#upBtn').on('click',function(){
		// client side - validation
		// server side - validation
		// validation 로직은 일단 생략
		
		$('#frmUp').submit();
	})
	/* initData() */
})
</script>	

</head>

<body>

	<%@ include file="../layout/header.jsp" %>
	
	<div class="container-fluid">
		<div class="row">
		
			<div class="col-sm-3 col-md-2 sidebar">
				<%@ include file="../layout/left.jsp" %>
			</div>
			
			<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">


				<form id="frmUp" class="form-horizontal" role="form" action="${cp }/memberUpdate/process" method="POST" enctype="multipart/form-data">
					<!-- <div class="form-group">
						<label for="userNm" class="col-sm-2 control-label">사용자 아이디</label>
						<div class="col-sm-10">
							<input type="text" class="form-control" id="userId" name="userId"
								placeholder="사용자 아이디">
						</div>
					</div> -->
					
					
					<div class="form-group">
						<label for="userNm" class="col-sm-2 control-label">사용자 사진</label>
						<div class="col-sm-10">
							<%--  <img src="${cp}/profile/${memberVo.filename}"/> --%>
							
							<img src="${cp}/profileImg/process?userid=${memberVo.userid}"/><br>
							
							<%-- <input type="file" name="realFilename">${memberVo.filename}<br> --%>
							<%-- ${cp}/profileImg?userid=${memberVo.userid} --%>
						</div> 
					</div>
					
					<div class="form-group">
						<label for="userNm" class="col-sm-2 control-label">사용자 아이디</label>
						<div class="col-sm-10">
							<input type="text" name="userid" value="${memberVo.userid}" READONLY>
						</div>
					</div>
			
					<div class="form-group">
						<label for="userNm" class="col-sm-2 control-label">사용자 이름</label>
						<div class="col-sm-10">
							<input type="text" name="usernm" value="${memberVo.usernm}">
						</div>
					</div>
					
					<div class="form-group">
						<label for="userNm" class="col-sm-2 control-label">별명</label>
						<div class="col-sm-10">
							<input type="text" name="alias" value="${memberVo.alias}">
						</div>
					</div>
					
					<div class="form-group">
						<label for="pass" class="col-sm-2 control-label">Password</label>
						<div class="col-sm-10">
							<input type="text" name="pass" value="${memberVo.pass}">
						</div>
					</div>

					<div class="form-group">
						<label for="addr1" class="col-sm-2 control-label">주소1</label>
						<div class="col-sm-10">
							<input id="addr1" type="text" name="addr1" value="${memberVo.addr1}" READONLY>
							<button id="zipcodeBtn" type="button" class="btn btn-default">우편번호 찾기</button>
						</div>
					</div>
					
					<div class="form-group">
						<label for="addr2" class="col-sm-2 control-label">주소2</label>
						<div class="col-sm-10">
							<input type="text" name="addr2" value="${memberVo.addr2}">
						</div>
					</div>
					
					<div class="form-group">
						<label for="zipcode" class="col-sm-2 control-label">우편번호</label>
						<div class="col-sm-10">
							<input id="zipcode" type="text" name="zipcode" value="${memberVo.zipcode}" READONLY >
						</div>
					</div>
					
					<div class="form-group">
						<div class="col-sm-10">
							
							<button id="upBtn" class="btn btn-default" >수정 완료</button>
						</div>
					</div> 
					
				</form>
			</div>
		</div> 
	</div>
</body>
</html>
    