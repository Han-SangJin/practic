package kr.or.ddit.category.web;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ch.qos.logback.classic.Logger;
import kr.or.ddit.category.model.CtgrVO;
import kr.or.ddit.category.service.CtgrServiceI;

@RequestMapping("/category")
@Controller
public class CtgrController {
	private static final long serialVersionUID = 1L;
	
	private static final Logger logger = (Logger) LoggerFactory.getLogger(CtgrController.class);
	
	@Autowired	// ioc.xml내용 자동으로 불러옴
	
	@Resource(name="ctgrService")
	CtgrServiceI ctgrService;
	
	
	@RequestMapping(path = "/view", method = RequestMethod.GET)	
	public String getView(HttpSession session, Model model) {
		logger.debug("MemberList-Controller.getView()");
		
		List<CtgrVO> ctgrList = ctgrService.selectAllCtgr();
		session.setAttribute("ctgrList", ctgrList);
		
		return "category/categoryJSP";
	}
	
	
	@RequestMapping(path="/ctgrInsert")							
	public String ctgrInsert(CtgrVO ctgrVo) {   
		
		// 카테고리 정보 등록
		int insertCnt = ctgrService.insertCtgr(ctgrVo);
	
		return "redirect:/category/view";
	}
	
	
	
	@RequestMapping(path="/ctgrUpdate")							
	public String ctgrUpdate(CtgrVO ctgrVo,String ctseq,String ctuse) {   
		
		System.out.println(ctseq);
		System.out.println(ctuse);
		
		//ctgrVo.setCtgr_seq1(Integer.parseInt(ctseq.split(",")[0]));
		//ctgrVo.setCtgr_use(Integer.parseInt(ctuse.split(",")[2]));
		
		// 카테고리 정보 수정
		int updateCnt = ctgrService.updateCtgr(ctgrVo);
		System.out.println("updateCnt : "+ updateCnt);
		return "redirect:/category/view";
	}
	
	
	
	
	
	
}
