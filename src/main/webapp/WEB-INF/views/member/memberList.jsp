<%@page import="kr.or.ddit.member.model.MemberVo"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    
<%
    	List<MemberVo> memberList = (List<MemberVo>) request.getAttribute("memberList");
            	String msg = (String) request.getAttribute("msg");
    %>    
 
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
<!-- 레이아웃 추가 -->
<%@ include file="../layout/commonLib.jsp" %>

<script>
$(document).ready(function(){
	$("#memberList tr").on("click", function(){
		// data-userid
		var userid = $(this).data("userid");
		console.log("userid : " + userid);
		
		document.location=${pageContext.request.contextPath}"/member/view?userid=" + userid;
	})
})
  
</script>

</head>
<body>
<!-- 상단 네비게이션바 -->
<%@ include file="../layout/header.jsp" %>


<div class="container-fluid">
		<div class="row">

 
<!-- 왼쪽 사이드 바 -->			
<div class="col-sm-3 col-md-2 sidebar">
  <%@ include file="../layout/left.jsp" %>
</div><div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
				

<div class="row">
	<div class="col-sm-8 blog-main">
		<h2 class="sub-header">사용자</h2>
		<div class="table-responsive">
			<table class="table table-striped">
				 <tr> 
				 	<td><p>사용자 아이디</p></td>
				 	<td><p>사용자 이름</p></td>
				 	<td><p>사용자 별명</p></td>
				 	<td><p>등록 일시</p></td>
				 </tr>
				 <tbody id="memberList">
				 
				<% 	 
					int memSize = memberList.size();
					if(memSize > 0){
						for(int i=0; i<memSize; i++){
				%>			 
					<tr data-userid="<%= memberList.get(i).getUserid() %>">
					<c:set var="userid" scope="request" value="<%= memberList.get(i).getUserid() %>"/>
						<td><%= memberList.get(i).getUserid() %></td>
						<td><%= memberList.get(i).getUsernm() %></td>
						<td><%= memberList.get(i).getAlias() %></td>
						<%-- <td><%= memberList.get(i).getReg_dt() %></td> --%>
						<td><fmt:formatDate value="<%= memberList.get(i).getReg_dt() %>" pattern="yyyy-MM-dd"/></td>
						<%-- <td><fmt:formatDate value="${member.reg_dt}" pattern="yyyy-MM-dd"/></td> --%>
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
		</div>  
		<a href="${pageContext.request.contextPath}/memberRegist/view" class="btn btn-default pull-right">사용자 등록</a>
		
 		 page : ${pageVo.getpage() }
 		 pages : ${pages }
 		<%-- memeberList : ${memberList} --%>
		<div class="text-center">
			<ul class="pagination">
				<c:forEach var="i" begin="1" end="${pages }">
					<c:choose>
						 
						<c:when test="${i == page}">
							<li class="active"><span>${i}</span></li>
						</c:when>
						<c:otherwise>
							<li><a href="${pageContext.request.contextPath}/memberList?page=${i}">${i}</a></li>
							<%-- <%=request.getContextPath()%>  =  ${pageContext.request.contextPath } --%>
						</c:otherwise>	
					</c:choose>				
				</c:forEach>
			</ul>
		</div>
</div>
	</div>
		</div>
	</div>
</body>
</html>
    

 