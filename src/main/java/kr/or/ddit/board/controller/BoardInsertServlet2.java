//package kr.or.ddit.board.controller;
//
//import java.io.IOException;
//import java.util.UUID;
//
//import javax.servlet.ServletException;
//import javax.servlet.annotation.MultipartConfig;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.Part;
//
//import kr.or.ddit.attachfile.model.AttachVO;
//import kr.or.ddit.attachfile.service.AttachService;
//import kr.or.ddit.attachfile.service.AttachServiceI;
//import kr.or.ddit.board.model.BoardVO;
//import kr.or.ddit.board.service.BoardService;
//import kr.or.ddit.board.service.BoardServiceI;
//
//@WebServlet("/boardinsertservlet2")
//@MultipartConfig
//public class BoardInsertServlet2 extends HttpServlet {
//	
//	private static final long serialVersionUID = 1L;
//	private BoardServiceI boardService;
//	private AttachServiceI reviewService;
//	  
//	
// 	@Override
//	public void init() throws ServletException {
//		boardService = new BoardService();
//		reviewService = new AttachService();
//	}
//    
// 	
// 	@Override
//	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		request.getRequestDispatcher("/board/insertBoard.jsp").forward(request, response);
//	}
//
//	/*
//	  int board_seq1; // board_seq1 게시판 시퀸스 						sys
//	 * int parent_seq1; // parent_seq1 답글 달때 부모 시퀸스값  			새로글작성 할때 null, 답글달때 board_seq1 값 가져오기
//	 * String board_title; // board_title 제목 
//	 * String board_cont; // board_cont 내용 
//	  Date board_date; // board_date 작성일 							sys	
//	  int board_dep; // board_dep 답글 작성한 부모 시퀸스  4까지 가능	새로작성시는 본인 게시판 시퀸스 / 답글 작성시 - 답글1 = 1,  답글의 답글 =2, 답글의 답글의 답글 =3				
//	  int board_del; // BOARD_DEL 게시판 삭제여부 						1 사용  2 삭제
//	  String mem_id; // mem_id 작성자 아이디 							세션값 가져오기
//	  int ctgr_seq1; // ctgr_seq1 카테고리 번호							세션값 가져오기
//	 
//	 * getBoard_seq1() getParent_seq1() getBoard_title() getBoard_cont()
//	 * getBoard_date() getBoard_dep() getMem_id() getCtgr_seq1() getBoard_del()
//	 */
//	
//	@Override
//	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		request.setCharacterEncoding("utf-8");
//		
//		// 게시판 정보 가져오기
//		String board_title = request.getParameter("board_title");
//		System.out.println(board_title);
//		String board_cont = request.getParameter("board_cont");
//		System.out.println(board_cont);
//		String mem_id = request.getParameter("mem_id");
//		System.out.println(mem_id);
//		int ctgr_seq1 = Integer.parseInt(request.getParameter("ctgr_seq1"));
//		System.out.println(ctgr_seq1);
//			  
//		
//		// 파일정보 가져오기		//file_name		//file_real_name
//		String file_real_name0 = request.getParameter("file_real_name");
//		System.out.println("file_real_name : " + file_real_name0);
//		
//		Part profile = request.getPart("file_real_name");;
//		String file_real_name = FileUploadUtil.getFilename(profile.getHeader("Content-Disposition"));
//		String ext = (".").concat(FileUploadUtil.getExtension(file_real_name));
//		String fileName = UUID.randomUUID().toString();
//		String file_name = "";
//		
//		
//		
//		
//		
//		
//		if(profile.getSize() > 0) {
//			file_name = "D:\\attachfile\\" + fileName + ext;
//			profile.write(file_name);
//		}	
//		System.out.println("file_name : " + file_name);
//		System.out.println("realFilename : " + file_real_name);
//		
//		
//		// 게시판 정보 등록
//		BoardVO boardVo = new BoardVO(board_title,board_cont,mem_id,ctgr_seq1);
//		
//		int insertCnt = boardService.insertBoard(boardVo);
//		System.out.println("insertCnt : " + insertCnt);
//		
//		
//		// 파일 정보 등록
//		AttachVO attachVo = new AttachVO(file_name,file_real_name);
//		
//		int fileCnt = reviewService.insertAttach(attachVo);
//		System.out.println("fileCnt : " + fileCnt);
//		
//		
//		// 둘다 가져왔을때 페이지 이동
//		if(insertCnt == 1 && fileCnt == 1){
//			request.getRequestDispatcher("/boardselectallservlet").forward(request, response);
//		}else {
//			doGet(request,response);
//		}
//	}
//}
