package kr.or.ddit.member.controller;

import java.io.IOException;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.or.ddit.fileUpload.FileUploadUtil;
import kr.or.ddit.member.model.MemberVo;
import kr.or.ddit.member.service.MemberService;
import kr.or.ddit.member.service.MemberServiceI;

/**
 * Servlet implementation class MemberRegistServlet
 */
@WebServlet("/memberRegist2")
@MultipartConfig	
public class MemberRegistServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private static final Logger logger = LoggerFactory.getLogger(MemberRegistServlet.class);   
	
    private MemberServiceI memberService;
    
    @Override
    public void init() throws ServletException {
    	memberService = new MemberService();
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/member/memberRegist.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String userid = request.getParameter("userid");
		String usernm = request.getParameter("usernm");
		String alias = request.getParameter("alias");
		String pass = request.getParameter("pass");
		String addr1 = request.getParameter("addr1");
		String addr2 = request.getParameter("addr2");
		String zipcode = request.getParameter("zipcode");
		
		logger.debug("arameter : {}, {}, {}, {}, {}, {}, {}",
				userid, usernm, alias, pass, addr1, addr2, zipcode );
			 
		Part profile = request.getPart("realFilename");;
		logger.debug("file : {}", profile.getHeader("Content-Disposition"));
		String realFilename = FileUploadUtil.getFilename(profile.getHeader("Content-Disposition"));
		String ext = (".").concat(FileUploadUtil.getExtension(realFilename));
		String fileName = UUID.randomUUID().toString();
		String filePath = "";
		
		if(profile.getSize() > 0) {
			filePath = "D:\\attachfile\\" + fileName + ext;
			profile.write(filePath);
		}
		 
		// 사용자 정보 등록
		MemberVo memberVo = new MemberVo(userid,pass,usernm,alias,addr1,addr2,zipcode,filePath,realFilename);
		
		int insertCnt = memberService.insertMember(memberVo);
			
		 
		/*
		   Forward 는 Web Container 차원에서 페이지의 이동만 존재합니다. 
		   실제로 웹 브라우저는 다른 페이지로 이동했음을 알 수 없습니다. 
		   그렇기 때문에 웹 브라우저에는 최초에 호출한 URL이 표시되고, 이동한 페이지의 URL 정보는 확인할 수 없습니다. 
		   또한 현재 실행중인 페이지와 forward에 의해 호출될 페이지는 Request 객체와 Response 객체를 공유합니다.
		   (페이지만 이동)
		  
		   Redirect 는 Web Container로 명령이 들어오면, 웹 브라우저에게 다른 페이지로 이동하라고 명령을 내립니다. 그러면 웹
		   브라우저는 URL을 지시된 주소로 바꾸고 해당 주소로 이동합니다. 다른 웹 컨테이너에 있는 주소로 이동하며 새로운 페이지에서는
		   Request와 Response객체가 새롭게 생성됩니다.
		   (새로요청)
		 */
		
		// 1건이 입력되었을때 : 정상 - memberList 페이지로 이동
		if(insertCnt == 1){
//			request.getRequestDispatcher("/memberList").forward(request, response);
			response.sendRedirect(request.getContextPath() + "/memberList");
		}
			
		// 1건이 아닐때 : 비정상 - 사용자가 데이터를 다시 업력할 수 있도록 등록페이지로 이동
		
		else {
			doGet(request,response);
		}
		
	}
}
