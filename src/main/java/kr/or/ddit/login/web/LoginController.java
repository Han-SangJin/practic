package kr.or.ddit.login.web;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import kr.or.ddit.member.model.MemberVo;
import kr.or.ddit.member.service.MemberServiceI;

// @WebServlet 혹은 web.xml url-mapping을 통해 url 등록

@SessionAttributes("rangers")
@RequestMapping("/login")
@Controller
public class LoginController {
	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
	
	@Autowired	// ioc.xml내용 자동으로 불러옴
	ApplicationContext context;
	
	
	@Resource(name="memberService")
	MemberServiceI memberService;
	
	
	@ModelAttribute("rangers")
	public List<String> rangers(){
		logger.debug("ranger()");
		List<String> rangers = new ArrayList<String>();
		rangers.add("brown");
		rangers.add("sally");
		rangers.add("cony");
		
		return rangers;
	}
	
	
	// localhost/login/view 요청시 처리되는 메소드
	// 요청 메소드가 GET 일때만 처리  method= RequestMethod.GET
	// 요청 메소드 복수개 설정  {RequestMethod.GET, RequestMethod.POST}
	@RequestMapping(path = "/view", method = RequestMethod.GET)	
	public String getView() {
		logger.debug("LoginController.getView()");
		return "login/view";	// 이동할 url 리턴 
	}
	
	
	 
	// 파라미터 이름과 동일한 이름의 메소드 인자를 선언하면 스프링 프레임워크가 자동으로 바인딩 해준다.
	// 값을 담을 수 있는 객체를 메소드 인자로 선언한 경우에도 필드명과 파라미터 명이 동일하면 자동으로 바인딩 처리를 해준다
	// 이때 값을 담는 객체를 스프링 프레임워크에서는 command 객체라고 명명한다.
	// form 으로 전송한 데이터를 받는 객체 = command 객체
	
	
	// Model : view 객체에서 응답을 생성할 때 참조할 데이터를 담는 객체
	//		jsp/servlet 기반의 request 역할을 담당
	@RequestMapping(path="/process", params= {"userid"})							//login/process 로 값이 들어온다	   // 값 jsp에서 가져옴
	public String process(String userid, String pass, MemberVo memberVo, HttpSession session, Model model, 
			@RequestParam(name="email", 
						  required = false, 
						  defaultValue = "brown@line.kr") String user_id) {	// RequestParam 으로 변수 명이 다를때 파라미터를 특정 변수에 할당 할 수 있다
		
		
		//logger로 id, pass 값 띄워줌
		logger.debug("LoginCOntroller.process() {} / {} / {}", userid, pass, memberVo);	
		logger.debug("user_id : {}", user_id);	
		
		// bean Dao에서 멤버 조회
//		MemberServiceI memberService = context.getBean("memberService", MemberServiceI.class); context 를 계속 만들어줘야 해서 의존성생김
		MemberVo dbMember = memberService.getMember(userid);
		logger.debug("dbMember : {}", dbMember);
		
		 
		// db에서 조회한 사용자 정보가 존재하면 ==> main.jsp 로 이동
		// db에서 조회한 사용자 정보가 존쟂하지 않으면 ==> login.jsp 로 이동
		//dbMember.getPass().equals(pass)
			 
		if(dbMember != (null) && memberVo.getPass().equals(dbMember.getPass()) ) {
			// prefix : /WEB-INF/views/
			// surfix : .jsp
			session.setAttribute("S_MEMBER", memberVo);
			
			// jsp/servlet 기반에서 사용한 코드 : request.setAttribute("to_day", new  Date());
			model.addAttribute("to_day", new Date());
			return "main";
				
		}else {
			model.addAttribute("msg","fail");
			return "login/view";
		}
			
	}
	
	
	// localhost/login/unt/xx
	@RequestMapping("/unt/{unt_cd}")
	public String untMain(@PathVariable("unt_cd") String unt_cd) {
		logger.debug("unt_cd : {}", unt_cd);
		return "main";
	}
	
	
	                                                                                                                                                                                                          
	// localhost/login/mavView
	@RequestMapping("/mavView")
	public ModelAndView mavView(@ModelAttribute("rangers") List<String> rangers,
								@ModelAttribute("test") MemberVo memberVo) {
								
		ModelAndView mav = new ModelAndView();
		
		logger.debug("mavView rangers : {}", rangers);
		// view name 설정
		mav.setViewName("main");
		
		mav.getModel().put("msg", "success");
		mav.getModelMap().addAttribute("msg", "fail");
		
		return mav;
	}
	
	
}
