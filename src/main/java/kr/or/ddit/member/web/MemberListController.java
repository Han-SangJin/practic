package kr.or.ddit.member.web;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.or.ddit.login.web.LoginController;
import kr.or.ddit.member.model.MemberVo;
import kr.or.ddit.member.service.MemberServiceI;

@RequestMapping("/memberList")
@Controller
public class MemberListController {
	
	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
	
	@Autowired	// ioc.xml내용 자동으로 불러옴
	ApplicationContext context;
	
	
	@Resource(name="memberService")
	MemberServiceI memberService;
	
	
	@RequestMapping(path = "/view", method = RequestMethod.GET)	
	public String getView() {
		logger.debug("MemberList-Controller.getView()");
		return "redirect:/memberList/process";	
	}
	
//	public String process(String page_str, @RequestParam(name="page", required = true, defaultValue = "1") int page
//			, String pageSize_str, @RequestParam(name="pageSize", required = true, defaultValue = "5") int pageSize
//			, Model model) { 
	
	
	@RequestMapping(path="/process")							
	public String process(Model model) { 
		
		List<MemberVo> memberList = memberService.selectAllMember();
		
		model.addAttribute("memberList", memberList);

		return "/member/memberList";	
			
	}

}
