<%@page import="kr.or.ddit.category.model.CtgrVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
    
<%
    	List<CtgrVO> ctgrList = (List<CtgrVO>) session.getAttribute("ctgrList");
%>        

		<c:set var="ctgrList" value="<%= session.getAttribute("ctgrList") %>"/>
		ctgrList.get(0).getCtgr_name()
		
<!DOCTYPE html>
<html lang="UTF-8">
<head>
  <title>Bootstrap Example</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
   <script src="/js/categoryupdate.js"></script> 
  <style>
    /* Set height of the grid so .sidenav can be 100% (adjust if needed) */
    .row.content {height: 1500px}
     
    /* Set gray background color and 100% height */
    .sidenav {
      background-color: #f1f1f1;
      height: 100%;
    }  
     
    /* Set black background color, white text and some padding */
    footer {
      background-color: #555;
      color: white;
      padding: 15px;
    }
    
    /* On small screens, set height to 'auto' for sidenav and grid */
    @media screen and (max-width: 767px) {
      .sidenav {
        height: auto;
        padding: 15px;
      }
      .row.content {height: auto;} 
    }
    label{
    	width : 100px;
    }
    
  </style>
<script> 

 $(document).ready(function(){
	 var ctgruse = 0;
	 var ctgr_seq1 = 0;
	 var ctgr_use = 0;

	 
	$("#ctgrList tr").on("click",function(){
		ctgruse = $(this).data("ctgruse");
		//alert("ctgruse : " + ctgruse); 
	})
	   
	  
	$("#ctgrList #update").on("click", function(){
		// data-userid
		ctgr_seq1 = $(this).data("ctgrseq1");
		//alert("ctgr_seq1 : " + ctgr_seq1); 
		//alert($("#idx").val());
	    // alert($("select[name=use"+ctgeuse+"]").val());
	    ctgr_use = $("select[name=use"+ctgruse+"]").val();
	    //alert(ctgr_use);
	  	//$('#ctgrfm').submit();
		/* document.location="/member?userid=" + userid; */
		     
		updateCtgr(ctgr_seq1,ctgr_use);

		location.reload();
	})	
}) 
  
		  
/* $(function(){

	var se = document.getElementBuId("used");
	alert(se)
	
	 $("#ctgrList #update").click(function(){
		var email = $("#used option:selected").text();
		alert(email)
	)}; 
});   */  
</script>
</head>


<title>Jsp</title>
<!-- 레이아웃 추가 -->
<%@ include file="../layout/commonLib.jsp" %>
</head>
 
     
<body>   
<!-- 상단 네비게이션바 -->
<%@ include file="../layout/header.jsp" %>
		

<div class="container-fluid">
<div class="row"> 

<!-- 왼쪽 사이드바 -->
<div class="col-sm-3 col-md-2 sidebar">
<%@ include file="../layout/left.jsp" %>

</div>  

<div class="col-sm-8 col-md-8  main">
	<div class="blog-header">
		<h1 class="blog-title">Category</h1>
		<p class="lead blog-description">Jsp / CategoryList.</p>
	</div> 
  
	<div class="col-sm-8 blog-main">
		<div class="blog-post">
					<form id="ctgrfm" action="${pageContext.request.contextPath}/ctgrinsertservlet" method="post">
        <table class="table table-striped">
				 <tr>  
				 	<td><h4>카테고리 번호</h4></td>
				 	<td><h4>카테고리 이름</h4></td>
				 	<td><h4>카테고리 사용여부</h4></td>
				 	<td><h4>생성/삭제</h4></td>
				 </tr>
				 <tr>   
				 	<td><p> 게시판 이름 : </p></td>
				 	<td><input type="text" name="ctgr_name" value=""></td>
				 	<td><p>
				 	<select name="ctgr_use" id="ctgr_use">
			      		<option value="1">사용</option>
			      		<option value="2">미사용</option>
			        </select></p></td>
				 	<td><input type="submit"value="생성"></td>
				 </tr>
				  
				 
				 <tbody id="ctgrList">
				   
				<% 	 
					int ctgrSize = ctgrList.size();
					System.out.println(ctgrSize);
					if(ctgrSize > 0){
						for(int i=0; i<ctgrSize; i++){
				%>			 
					<tr data-ctgruse="<%= i %>">
						<td><%= ctgrList.get(i).getCtgr_seq1() %></td>
						<td><%= ctgrList.get(i).getCtgr_name() %></td>
						
						<td>
						<select name="use<%= i %>" id="used<%= i %>">
							
						
					    <% if(ctgrList.get(i).getCtgr_use() == 1){ %>
						<option>사용</option>
					   <%} else if(ctgrList.get(i).getCtgr_use() == 2){ %>
						<option>미사용</option>
						<%} %>  
						
						 
						<option value="1">사용</option>
			      		<option value="2">미사용</option>
						
						    <%-- <option value=1 <c:if test="${ctgr_use == 1}">selected</c:if>>사용</option>
		                    <option value=2 <c:if test="${ctgr_use == 2}">selected</c:if>>미사용</option>  --%>
		
						</select>
						</td>
						
						<td>
						<input id="update" class="update" type="button" value="수정" data-ctgrseq1="<%= ctgrList.get(i).getCtgr_seq1() %>">
						</td>
						 
					</tr>	  
				  
				  <% 			
						}
					}else{ // 회원정보가 존재하지 않을 경우...
				  %> 
					<tr>
						<td colspan="2">회원정보가 존재하지 않습니다.</td>
					</tr>
				<%	
					}
				%>
				</tbody>
				
			</table>
    		</form>
		</div>
	</div>            
	<!-- /.blog-main -->
</div>	
</div>
	</div>
		
</body>
</html>
