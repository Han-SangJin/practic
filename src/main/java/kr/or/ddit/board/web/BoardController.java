package kr.or.ddit.board.web;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import ch.qos.logback.classic.Logger;
import kr.or.ddit.board.model.BoardVO;
import kr.or.ddit.board.service.BoardServiceI;
import kr.or.ddit.category.web.CtgrController;
import kr.or.ddit.common.model.PageVO;

@RequestMapping("/board")
@Controller
public class BoardController {
private static final long serialVersionUID = 1L;
	
	private static final Logger logger = (Logger) LoggerFactory.getLogger(CtgrController.class);
	
	@Autowired	// ioc.xml내용 자동으로 불러옴
	
	@Resource(name="boardService")
	BoardServiceI  boardService;
	
	
	
	@RequestMapping(path = "/boardselectall", method = RequestMethod.GET)	
	public String boardSelectAll(HttpSession session, Model model, BoardVO boardVo, int ctgr_seq1,
									@RequestParam(name="page", required = true, defaultValue = "1") String page_str,
									@RequestParam(name="pageSize", required = true, defaultValue = "10") String pageSize_str){

		logger.debug("Board-Controller.boardselectall()");
		PageVO pageVo = new PageVO();
		pageVo.setPage(Integer.parseInt(page_str));
		pageVo.setPageSize(Integer.parseInt(pageSize_str));
		pageVo.setCtgr_seq1(ctgr_seq1);
		session.setAttribute("ctgr_seq1", ctgr_seq1);
		Map<String, Object> map = boardService.selectBoardPageList(pageVo);

		logger.debug("boardselectall() - map : " + map);
		session.setAttribute("selectAllBoard", map.get("selectAllBoard"));
		model.addAttribute("pages", map.get("pages"));
		model.addAttribute("page", Integer.parseInt(page_str));
		model.addAttribute("pageSize", Integer.parseInt(pageSize_str));
		
		return "board/selectAllBoard";
	}
	
	
	
	@RequestMapping(path = "/selectBoard", method = RequestMethod.GET)	
	public String selectBoard(BoardVO boardVo, Model model, int board_seq1) {
		
		boardVo = boardService.selectBoard(board_seq1);
		System.out.println(boardVo.getBoard_title());
		model.addAttribute("boardVo", boardVo);
		
		return "board/selectBoard";
	}
	
	
	
}
