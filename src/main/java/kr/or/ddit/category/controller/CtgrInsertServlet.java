package kr.or.ddit.category.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.or.ddit.category.model.CtgrVO;
import kr.or.ddit.category.service.CtgrService;
import kr.or.ddit.category.service.CtgrServiceI;

@WebServlet("/ctgrinsertservlet")
public class CtgrInsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
//	private static final Logger logger = LoggerFactory.getLogger(CtgrMake.class); 
	private CtgrServiceI ctgrService;
	
	@Override
	public void init() throws ServletException {
		ctgrService = new CtgrService();
	}
	 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("ctgrList");
		
		List<CtgrVO> ctgrList = ctgrService.selectAllCtgr();
		request.setAttribute("ctgrList", ctgrList);
		request.getRequestDispatcher("/category/ctgrinsert.jsp").forward(request, response);
	}
	
	  
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("ctgrPost");
		request.setCharacterEncoding("utf-8");
//		int ctgr_seq1 = Integer.parseInt(request.getParameter("ctgr_seq1"));
		String ctgr_name = request.getParameter("ctgr_name");
		int ctgr_use = Integer.parseInt(request.getParameter("ctgr_use"));
		
		
//		logger.debug("arameter : {}, {}", ctgr_name, ctgr_use);
			    
		// 사용자 정보 등록
		CtgrVO ctgrVo = new CtgrVO(ctgr_name, ctgr_use);
		int insertCnt = ctgrService.insertCtgr(ctgrVo);
		
		
		// 1건이 입력되었을때 : 정상 - memberList 페이지로 이동
		if(insertCnt == 1){
			List<CtgrVO> ctgrList = ctgrService.selectAllCtgr();
			request.setAttribute("ctgrList", ctgrList);
			
			request.getRequestDispatcher("/category/ctgrinsert.jsp").forward(request, response);
//			response.sendRedirect(request.getContextPath() + "/ctgrmake");
		}
			
		// 1건이 아닐때 : 비정상 - 사용자가 데이터를 다시 업력할 수 있도록 등록페이지로 이동
		
		else {
			doGet(request,response);
		}
		
		
	}

}
