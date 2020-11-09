package kr.or.ddit.category.web;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.ServletException;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ch.qos.logback.classic.Logger;
import kr.or.ddit.category.model.CtgrVO;
import kr.or.ddit.category.service.CtgrService;
import kr.or.ddit.category.service.CtgrServiceI;
import kr.or.ddit.login.web.LoginController;
import kr.or.ddit.member.model.MemberVo;
import kr.or.ddit.member.service.MemberServiceI;

@RequestMapping("/ctgrList")
@Controller
public class CtgrListController {
	private static final long serialVersionUID = 1L;
	
	private static final Logger logger = (Logger) LoggerFactory.getLogger(CtgrListController.class);
	
	@Autowired	// ioc.xml내용 자동으로 불러옴
	
	@Resource(name="ctgrService")
	CtgrServiceI ctgrService;
	
	
	@RequestMapping(path = "/view", method = RequestMethod.GET)	
	public String getView(Model model) {
		logger.debug("MemberList-Controller.getView()");
		
		List<CtgrVO> ctgrList = ctgrService.selectAllCtgr();
		model.addAttribute("ctgrList", ctgrList);
		return "category/ctgrinsert";
	}
	
	
	
	@RequestMapping(path="/process")							
	public String process(Model model, String ctgr_name, int ctgr_use) { 
			    
		// 카테고리 정보 등록
		CtgrVO ctgrVo = new CtgrVO(ctgr_name, ctgr_use);
		int insertCnt = ctgrService.insertCtgr(ctgrVo);

		
		if(insertCnt == 1){
			List<CtgrVO> ctgrList = ctgrService.selectAllCtgr();
			model.addAttribute("ctgrList", ctgrList);
			return "/category/ctgrinsert";
		}else {
			return "ctgrList/view";
		}
		
		
	}
}
