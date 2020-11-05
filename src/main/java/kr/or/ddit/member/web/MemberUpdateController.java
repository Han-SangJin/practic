package kr.or.ddit.member.web;

import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.Part;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.or.ddit.fileUpload.FileUploadUtil;
import kr.or.ddit.login.web.LoginController;
import kr.or.ddit.member.model.MemberVo;
import kr.or.ddit.member.service.MemberServiceI;

@RequestMapping("/memberUpdate")
@Controller
@MultipartConfig
public class MemberUpdateController {
	
	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
	
	@Autowired	// ioc.xml내용 자동으로 불러옴
	ApplicationContext context;
	
	
	@Resource(name="memberService")
	MemberServiceI memberService;
	
	
	@RequestMapping(path = "/view", method = RequestMethod.GET)	
	public String getView(Model model, String userid) {
		logger.debug("memberUpdate-Controller.getView()");
		logger.debug("Member-userid : {}", userid);
		
		MemberVo memberVo = memberService.getMember(userid);
		logger.debug("Member : {}", memberVo);
		model.addAttribute("memberVo", memberVo);
		
		return "member/memberUpdate";	
	}
	
	
	@RequestMapping(path="/process", method = RequestMethod.POST)							
	public String process(Model model, String userid, String usernm, String alias, String pass, String addr1, String addr2 ,String zipcode) { 
		logger.debug("arameter : {}, {}, {}, {}, {}, {}, {}", userid, usernm, alias, pass, addr1, addr2, zipcode );
		
		MemberVo memberVo = new MemberVo(userid,pass,usernm,alias,addr1,addr2,zipcode);
		int updateCnt = memberService.updateMember(memberVo);
		System.out.println("updateCnt ::: " + updateCnt);
		
		if(updateCnt == 1){
			return "redirect:/member/view?userid="+ userid;
		}else {
			return "member/memberUpdate";	
		}
	}

}
