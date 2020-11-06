package kr.or.ddit.member.web;

import javax.annotation.Resource;
import javax.servlet.annotation.MultipartConfig;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.or.ddit.member.model.MemberVo;
import kr.or.ddit.member.service.MemberServiceI;

@MultipartConfig
@Controller
public class ProfileDownloadController {
	
	private static final Logger logger = LoggerFactory.getLogger(ProfileDownloadController.class);
	
	@Resource(name="memberService")
	MemberServiceI memberService;
	
	
	@RequestMapping("/profileDownload")
	public String getView(String userid, Model model) {
		logger.debug("profileDownload-Controller.getView() : " + userid);
		
		MemberVo memberVo = memberService.getMember(userid);
		logger.debug("memberVo memberVo memberVo : " + memberVo);
		
		model.addAttribute("memberVo", memberVo);
		return "DownloadView";	
	}

}
