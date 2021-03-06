<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <meta name="description" content="">
    <meta name="author" content="">
    <!-- <link rel="icon" href="../../favicon.ico"> -->
	
    <title>Signin Template for Bootstrap</title>
	
    <!-- Bootstrap core CSS -->
    <link href="${pageContext.request.contextPath }/css/bootstrap.min.css" rel="stylesheet">
	
    <!-- Custom styles for this template -->
    <link href="${pageContext.request.contextPath }/css/signin.css" rel="stylesheet">
	
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath }/js/js.cookie-2.2.1.min.js"></script>
	
	  
	<script>   
		$(function(){ 
			// remember me cookie 확인
			if(Cookies.get("REMEMBERME")=="Y"){
				$("input[type=checkbox]").prop("checked",true);
				//$("input[type=checkbox]").attr("checked","checked");
				$("#inputEmail").val(Cookies.get("USERNM"))
				//console.log("체크");
			} 
      
				   
			// sign in b버튼이 클릭 되엇을때 이벤크 핸들러
			$("button").on('click',function(){
				console.log("button_click");
			
				if($("input[type=checkbox]").prop("checked") == true){
					Cookies.set("REMEMBERME","Y");
					Cookies.set("USERNM", $("#inputEmail").val());
				}else{
					Cookies.remove("REMEMBERME");
					Cookies.remove("USERNM");
				}
 
				// submit
				$("form").submit();
				
			})
			

			
		})
		
			
		/* function getCookieValues(cookieName){
			var cookieString = document.cookie.split("; ")
			var usernm = cookies[0].split9("=")
			
			console.log(usernm[0])
			console.log(usernm[1])
		} */
 
		
		function getCookieValues(cookieName){
			
			var cookieString = document.cookie.split("; ")
			for(var i=0; i< cookies.length; i++){
				var cookie = cookies[i];
				var cookieArr = cookie.splie("=");

				if(cookieName == cookieArr[0]){
					return cookieArr[1];
				}
			}
			// 원하는 쿠키가 없는 경우
			return "";
		}	

 
		// 쿠키 날짜 설정
		function setCookie(cookieName, cookieValue, expires){
			//"USERNM=brown; path=/; expries=Wed, 07 Oct 2020 00:38:35 GMT;"
			var today = new Date();
			// 현재 날짜에서 미래로  + expires 만큼 한 날짜 구하기
			today.setDate( today.getDate() + expires );
			
			document.cookie = cookieName + "=" + cookieValue + "; path=/; expires=" + today.toGMTString();
			console.log(document.cookie);
		}
		
		
		// 해당 쿠키의 expires 속성을 과거 날짜로 변경
		function deleteCookie(cookieName){
			setCookie(cookieName, "", -1);	
		}

		// window.onload = function(){
		/* 	$('#remember').on('checked', function(){
		
				if(Cookies.get("REMEMBERME")=='Y'){
					$('#remember').attr('checked','checked');
					console.log("체크");
				}else{ 
					  
				}
			}) */
			
			
		//} 
	</script>
  </head>
   
  
	    
  <body>
	msg : ${msg } <br>
	msg_s : ${msg_s } <br>
	<c:remove var="msg_s" scope="session"/>		<!-- msg_s 세션삭제 -->
	
	msg_ra : ${msg_ra }
	
    <div class="container"> 
	
      <form id="frm" class="form-signin" action="${pageContext.request.contextPath}/login/process" method="post">
        <h2 class="form-signin-heading">Please sign in</h2>
        <label for="inputEmail" class="sr-only">Email address</label>
        <input type="email" id="inputEmail" name="userid" class="form-control" placeholder="Email address" required autofocus value="brown">
        <label for="inputPassword" class="sr-only">Password</label>
        <input type="password" id="inputPassword" name="pass" class="form-control" placeholder="Password" required value="brownPass">
        <div class="checkbox">
          <label>
            <input type="checkbox" value="" id="remember"> Remember me
          </label>
        </div>
        <button class="btn btn-lg btn-primary btn-block" type="button">Sign in</button>
      </form>

    </div> <!-- /container -->
    
  </body>
  		
<!-- <script>
		getCookieValue("USERID");
		getCookieValue("REMEMBERME");
		getCookieValue("NOTEXISTS_COOKIE");
		
	function getCookieValue(cookieName){
		var cookies = document.cookie.split("; ")
		var usernm = cookies[0].split("=")

		console.log(usernm[1])
		
	}
</script>   -->
</html>

    