package kr.or.ddit.member.web;

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
 
@RequestMapping("/member")
@Controller
public class MemberController {
	
	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
	
	@Autowired	// ioc.xml내용 자동으로 불러옴
	ApplicationContext context;
	
	 
	@Resource(name="memberService")
	MemberServiceI memberService;
	
	
	@RequestMapping(path = "/view", method = RequestMethod.GET)	
	public String getView() {
		logger.debug("Member-Controller.getView()");
		return "forward:/member/process";	
	}
	
	
	@RequestMapping(path="/process")							
	public String process(Model model, String userid) { 
		
		logger.debug("Member-userid : {}", userid);
		MemberVo memberVo = memberService.getMember(userid);
		logger.debug("Member : {}", memberVo);
		
		model.addAttribute("memberVo", memberVo);
		//forward:/WEB-INF/views/   .jsp
		return "member/member";	
	}
}
