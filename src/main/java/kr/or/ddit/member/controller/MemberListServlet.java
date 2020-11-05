package kr.or.ddit.member.controller;

import java.io.IOException;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.common.model.PageVo;
import kr.or.ddit.member.service.MemberService;
import kr.or.ddit.member.service.MemberServiceI;

@WebServlet("/memberList1")
public class MemberListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	 
	private MemberServiceI memberService;
	
	@Override
	public void init() throws ServletException {
		memberService = new MemberService();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//page
		String msg = request.getParameter("msg") == null ? "" : request.getParameter("msg");
		String page_str = request.getParameter("page");
		int page = page_str == null ? 1 : Integer.parseInt(page_str);
		request.setAttribute("page", page);
		
		//pageSize
		String pageSize_str = request.getParameter("pageSize");
		System.out.println("pageSize_str : " + pageSize_str);
		int pageSize = pageSize_str == null ? 5 : Integer.parseInt(pageSize_str);
		request.setAttribute("pageSize", pageSize);
		
		// pageVo : page, pageSize
		PageVo pageVo = new PageVo(page, pageSize);
//		pageVo.setPage(page);
		
		
		// request.setAttribute("memeberList", memberService.selectAllMember());
//		memberService.selectMemberPageList(page) ==> List<MemberVo> ==> Map<String, Object>
		
		
		// 3. 결과를 화면(브라우져)에 출력하기
		Map<String, Object> map = memberService.selectMemberPageList(pageVo);
		request.setAttribute("msg", msg);
		request.setAttribute("memberList", map.get("memberList"));
		request.setAttribute("pages", map.get("pages"));
		
		request.getRequestDispatcher("/member/memberList.jsp").forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}



/*
//3. 전체글갯수 구하는 메세소드 호출하기
//한페이지당 출력 글 갯수 5개 또는 10개 
int perlist = 5;
int totalcount = service.getTotalCount();
	  
// 전체 페이지 수
int totalpage = (int)(Math.ceil((double) totalcount / perlist)); // 전체글갯수 / 페이지당 글갯수  = 페이지수 
int perblock = 2;	// 한페이지에 표현되는 페이지 수   	1-> [1][2] 2->[1][2]   3->[3][4]  4->[3][4] 			  1페이지 1~3  2페이지 4~6   3페이지 7~9
int startpage = ((cpage-1) / perblock * perblock) + 1;
int endpage = startpage + perblock -1;
if(endpage > totalpage) endpage = totalpage;
  
// cpage값에 따라서 start와 end 값 구하기 1페이지 1~5 2페이지 6~10 3페이지 11~15
// map에 설정하기
int start = (cpage-1) * perlist + 1;
int end = start + perlist -1;
if(end > totalcount) end = totalcount;


Map<String, Integer> map = new HashMap<>();
map.put("start", start);
map.put("end", end);

//4. 페이지별 리스트 메소드 호출하기
List<ReviewVO> bookreviewList = service.selectPage(map);
 
//5. request에 결과 저장하기
request.setAttribute("listvalue", bookreviewList);
request.setAttribute("spage", startpage);
request.setAttribute("epage", endpage);
request.setAttribute("tpage", totalpage);
request.setAttribute("cpage", cpage);*/