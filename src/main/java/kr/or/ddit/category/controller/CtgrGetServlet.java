package kr.or.ddit.category.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.category.model.CtgrVO;
import kr.or.ddit.category.service.CtgrService;
import kr.or.ddit.category.service.CtgrServiceI;

/**
 * Servlet implementation class CtgrGetServlet
 */
@WebServlet("/ctgrgetservlet")
public class CtgrGetServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private CtgrServiceI ctgeService;   
	
	@Override
	public void init() throws ServletException {
		ctgeService = new CtgrService();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/* 
		   userid 파라미터 받기 
		   service 객체 준비 - 호출
		   화면담당 jsp 로 위임
		 */
		int ctgr_seq1 = Integer.parseInt(request.getParameter("ctgr_seq1"));
		CtgrVO ctgrVo = ctgeService.getCtgr(ctgr_seq1);
		
		request.setAttribute("ctgrVo", ctgrVo);
		request.getRequestDispatcher("/ctgr/ctgrget.jsp").forward(request, response);
	}
	 
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
