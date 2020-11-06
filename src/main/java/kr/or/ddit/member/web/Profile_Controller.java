package kr.or.ddit.member.web;

import javax.annotation.Resource;
import javax.servlet.annotation.MultipartConfig;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.or.ddit.member.model.MemberVo;
import kr.or.ddit.member.service.MemberServiceI;

@MultipartConfig
@Controller
public class Profile_Controller {
				/* 수업중 한것 */
	@Resource(name="memberService")
	private MemberServiceI memberService;
	
	
	@RequestMapping("/profileImgView")
	public String profileImgView(String userid, Model model) {	// public ProfileImgView profileImgView(String userid, Model model)
		//응답으로 생성하려고 하는 것 : 이미지 파일을 읽어서 output stream 객체에 쓰는 것
		MemberVo memberVo = memberService.getMember(userid);
		model.addAttribute("filepath", memberVo.getFilename());
		
		return "profileImgView"; 									// return new ProfileImgView();
		// filepath 를 가지고
		// view package -> profileImgView.java 로 이동
				
		// application-context.xml 에 bean 객체 등록
	}
}
