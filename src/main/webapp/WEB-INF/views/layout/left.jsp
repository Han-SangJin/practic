<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!-- 왼쪽 사이드바 -->
<ul class="nav nav-sidebar">
	<li class="active"><a href="<%=request.getContextPath() %>/main.jsp">Main <span class="sr-only">(current)</span></a></li>
	<li class="active"><a href="<%=request.getContextPath() %>/memberList">사용자</a></li>
	<li class="active"><a href="${pageContext.request.contextPath}/selectAllJobs">jobs</a></li>
</ul>  