package kr.or.ddit.board.login;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.or.ddit.board.service.BoardServiceI;
import kr.or.ddit.member.model.MemberVo;
import kr.or.ddit.member.service.MemberService;
import kr.or.ddit.member.service.MemberServiceI;

// @WebServlet 혹은 web.xml url-mapping을 통해 url 등록

@RequestMapping("/login")
@Controller
public class LoginController {
	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
	
	@Autowired	// ioc.xml내용 자동으로 불러옴
	ApplicationContext context;
	
	// localhost/login/view 요청시 처리되는 메소드
	@RequestMapping("/view.do")
	public String getView() {
		
		logger.debug("LoginController.getView()");
		return "login/view";
	}

	
	 
	// 파라미터 이름과 동일한 이름의 메소드 인자를 선언하면 스프링 프레임워크가 자동으로 바인딩 해준다.
	// 값을 담을 수 있는 객체를 메소드 인자로 선언한 경우에도 필드명과 파라미터 명이 동일하면 자동으로 바인딩 처리를 해준다
	// 이때 값을 담는 객체를 스프링 프레임워크에서는 command 객체라고 명명한다.
	
	@RequestMapping("/process")		//login/process 로 값이 들어오면		
	public String process(String userid, String pass, MemberVo memberVo) {	// 값 jsp에서 가져옴
		//logger로 id, pass 값 띄워줌
		logger.debug("LoginCOntroller.process() {} / {} / {}", userid, pass, memberVo);	
		
		
		
		
		MemberServiceI memberService = context.getBean("memberService", MemberServiceI.class);
		MemberVo dbMember = memberService.getMember(userid);
		
		logger.debug("dbMember : {}", dbMember);
		
		return "login/view";	// login/view 페이지로 이동
	}
	
	

	
}
