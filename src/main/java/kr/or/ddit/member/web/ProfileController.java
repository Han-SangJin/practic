package kr.or.ddit.member.web;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServletResponse;

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

@RequestMapping("/profileImg")
@Controller
@MultipartConfig
public class ProfileController {

	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
	
	@Autowired	// ioc.xml내용 자동으로 불러옴
	ApplicationContext context;
	
	
	@Resource(name="memberService")
	MemberServiceI memberService;
	
	
	@RequestMapping(path = "/view", method = RequestMethod.GET)	
	public String getView(String userid) {
		System.out.println(userid);
		logger.debug("profileImg-Controller.getView()");
		return "redirect:/profileImg/process";	
	}
	
	
	@RequestMapping(path="/process")							
	public void process(Model model, String userid, HttpServletResponse response) throws IOException { 
		
		response.setContentType("image/png");
		System.out.println(userid);
		MemberVo memberVo = memberService.getMember(userid);
		
		// 이미지 파일 realFileName, fileName 둘중 하나만 있으면 오류발생 둘다 null이면 괜찮음
		if(memberVo.getFilename() != null) {
			
			FileInputStream fis = new FileInputStream(memberVo.getFilename());
			
			ServletOutputStream sos = response.getOutputStream();
			
			byte[] buffer = new byte[512];
			
			while(fis.read(buffer) != -1) {
				sos.write(buffer);
			}
			fis.close();
			sos.flush();
			sos.close();
		}
	}	
	
	
}
