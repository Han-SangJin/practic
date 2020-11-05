package kr.or.ddit.mvc.redirect.web;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@RequestMapping("/redirect")
@Controller
public class RedirectController {
	
	private static final Logger logger = LoggerFactory.getLogger(RedirectController.class);
	// localhost/redirect/process ==> login/login/view
	// 요청시 /login/view 로 리다이렉트 처리
	@RequestMapping("process")
	public String process(Model model, HttpSession session, RedirectAttributes ra) {
		logger.debug("RedirectController.process()");
		
		model.addAttribute("msg","hello, world");	// 전달안됨
		session.setAttribute("msg_s", "hello, world_session");	// 전달 가능
		// login/view 페이지 뿐만 아니라 어느화면에서 요청해도 값이 저장되어 있기 때문에 삭제를 할 필요가 있음
		// 받는 페이지에서 삭제요청을 하면 1회성으로 세션이 삭제되어서, 받는 페이지에서 새로고침 하면 사라짐
		
		
		// 리다이렉트 된 페이지에서 일회에 한해 사용할 수 있는 속성
		ra.addFlashAttribute("msg_ra","hello, world_ra");	//set Attribute
		// 마찬가지로 1회성 으로 받는 페이지 에서 새로고침 하면 삭제됨
		
		// url 주소에 ?msg_ra_attr=brown 이 추가되서 나옴
		ra.addAttribute("msg_ra_attr", "brown");			// set parameter
		
		// 리다이렉트시 모델 객체에 추가된 속성을 리다이렉트 주소의 파라미터로 추가한다(get)
		// "redirect:/login/view ==> /login/view?msg=hello, world
		
		return "redirect:/login/view";
	}
	
	
	
	
	
}
