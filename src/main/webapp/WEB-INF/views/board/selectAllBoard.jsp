<%@page import="kr.or.ddit.board.model.BoardVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>    

<%
    	List<BoardVO> selectAllBoard = (List<BoardVO>) request.getAttribute("selectAllBoard");
    %>    
 
	<c:set var="selectAllBoard" value="<%= selectAllBoard %>"/>
	<c:set var="boardsize" value="<%= selectAllBoard.size() %>"/>
	<fmt:formatNumber value="${boardsize}" type="number" var="numberType" />
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<title>Insert title here</title>
</head>
<style>
	#title td {
		font-size:24px;
	}
	#cont td{
		font-size:20px;
	} 
	.title {
		width : 600px;
	}
	.date {
		width : 200px;
	}
	.id {
		width : 150px;
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
				
				
				<a href="/boardinsertservlet?ctgr_seq1=${ctgr_seq1}&parent_seq1=0"><input type="button" value="새로운 글 등록"></a>
	 
	int boardsize : ${ selectAllBoard.size() }
 	page : ${page}
 	pages : ${pages}
 	ctgr_seq1 : ${ctgr_seq1}
 	
 	<c:set var="ctgr_seq1" value="${ctgr_seq1}" scope="session"/>
 	

 	 
	<br><br>

	<table border="1">
			<tr id=title>
				<td width="100px">번호</td>
				<!-- <td>부모 시퀸스값</td> -->
				<td class="title">제목</td>
				<!-- <td>내용</td> -->
				<td>작성일</td>
				<!-- <td>원글의 시퀸스</td> -->
				<td>아이디</td>
				<!-- <td>카테고리 번호</td> -->
				<!-- <td>삭제여부</td> -->
				<td></td>
			</tr>
		
		<c:choose>
			<c:when test="${boardsize > 0}">
				<c:forEach var="i" begin="0" end="${boardsize-1}">
					<tr id="cont">
						<c:choose>
							<c:when test="${ selectAllBoard.get(i).getBoard_del() == 2 }">
								<td>${ selectAllBoard.get(i).getBoard_seq1() }</td> 
								<%-- <td>${ selectAllBoard.get(i).getParent_seq1() }</td> --%>
								<td class="title"> 삭제된 게시물 입니다.</td>
								<%-- <td>${ selectAllBoard.get(i).getBoard_cont() }</td> --%>
								<td class="date"> </td>
								<%-- <td>${ selectAllBoard.get(i).getBoard_dep() }</td> --%>
								<td class="id"> </td>
								<%-- <td>${ selectAllBoard.get(i).getCtgr_seq1() }</td> --%>
								<%-- <td>${ selectAllBoard.get(i).getBoard_del() }</td> --%>
							</c:when>
							<c:otherwise>
								<td>${ selectAllBoard.get(i).getBoard_seq1() }</td>
								<%-- <td>${ selectAllBoard.get(i).getParent_seq1() }</td> --%>
								<%-- <td><a href="/boardselectservlet?board_seq1=${selectAllBoard.get(i).getBoard_seq1()}&ctgr_seq1=${selectAllBoard.get(i).getCtgr_seq1()}">${selectAllBoard.get(i).getBoard_title()}</a></td>  --%>
								<td class="title"><a href="/reviewselectallservlet?board_seq1=${selectAllBoard.get(i).getBoard_seq1()}">${selectAllBoard.get(i).getBoard_title()}</a></td>
								<%-- <td>${ selectAllBoard.get(i).getBoard_cont() }</td> --%>
								<td class="date">${ selectAllBoard.get(i).getBoard_date() }</td>
								<%-- <td>${ selectAllBoard.get(i).getBoard_dep() }</td> --%>
								<td class="id">${ selectAllBoard.get(i).getMem_id() }</td>
								<%-- <td>${ selectAllBoard.get(i).getCtgr_seq1() }</td> --%>
								<%-- <td>${ selectAllBoard.get(i).getBoard_del() }</td> --%>
							</c:otherwise>	
						</c:choose>
					</tr>
				</c:forEach> 
			</c:when>
			<c:otherwise>
				글 목록이 존재하지 않습니다.
			</c:otherwise>
		</c:choose>
	</table>
	
	<div class="text-center">
		<ul class="pagination">
			<c:choose>
				<c:when test="${1 < page}">
					<li><a href="/boardselectallservlet?page=1&ctgr_seq1=${ctgr_seq1}&pageSize_str=${pages}">◀◀</a></li>
				</c:when>
				<c:otherwise>
					<li><a href="/boardselectallservlet?page=${page}&ctgr_seq1=${ctgr_seq1}&pageSize_str=${pages}">◀◀</a></li>
				</c:otherwise>
			</c:choose>	
		
			<c:choose>
				<c:when test="${1 < page}">
					<li><a href="/boardselectallservlet?page=${page-1}&ctgr_seq1=${ctgr_seq1}&pageSize_str=${pages}">◀</a></li>
				</c:when>
				<c:otherwise>
					<li><a href="/boardselectallservlet?page=${page}&ctgr_seq1=${ctgr_seq1}&pageSize_str=${pages}">◀</a></li>
				</c:otherwise>
			</c:choose>	
			
				<c:forEach var="i" begin="1" end="${pages }">
					<c:choose>
						<c:when test="${i == page}">
							<li class="active"><span>${i}</span></li>
						</c:when>
						<c:otherwise>
							<li><a href="/boardselectallservlet?page=${i}&ctgr_seq1=${ctgr_seq1}&pageSize_str=${pages}">${i}</a></li>
						</c:otherwise>
							  
					</c:choose>				
				</c:forEach>
			
			<c:choose>
				<c:when test="${page < pages}">
					<li><a href="/boardselectallservlet?page=${page+1}&ctgr_seq1=${ctgr_seq1}&pageSize_str=${pages}">▶</a></li>
				</c:when>
				<c:otherwise>
					<li><a href="/boardselectallservlet?page=${page}&ctgr_seq1=${ctgr_seq1}&pageSize_str=${pages}">▶</a></li>
				</c:otherwise>
			</c:choose>	
			
			<c:choose>
				<c:when test="${page < pages}">
					<li><a href="/boardselectallservlet?page=${pages}&ctgr_seq1=${ctgr_seq1}&pageSize_str=${pages}">▶▶</a></li>
				</c:when>
				<c:otherwise>
					<li><a href="/boardselectallservlet?page=${page}&ctgr_seq1=${ctgr_seq1}&pageSize_str=${pages}">▶▶</a></li>
				</c:otherwise>
			</c:choose>	
		
			
		</ul>
	</div>
		</div>
	</div>            
	<!-- /.blog-main -->
</div>	
</div>
	</div>
		
</body>
</html>