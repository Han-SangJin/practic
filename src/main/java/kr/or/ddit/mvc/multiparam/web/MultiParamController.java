package kr.or.ddit.mvc.multiparam.web;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.or.ddit.board.model.Rangers;

@RequestMapping("/multi") 	// url 주소
@Controller					// bean 이 찾을수 있게 하는 어노테이션
public class MultiParamController {

	private static final Logger logger = LoggerFactory.getLogger(MultiParamController.class);
	
	//  http://local/multi/view
	// 복수개의 파라미터를 전송할 수 있는 화면을 요청
	@RequestMapping("/view")
	public String view() {
		return "multi/view";	// /WEB-INF/views/multi/view.jsp
	}
	
	
	// 복수개의 파라미터 전송을 처리하는 메소드
	// userid 라는 이름의 파라미터로 복수개의 값이 전달됨
	@RequestMapping("/submit")
	public String submit(@RequestParam("userid") List<String> userList, String[] userid, Rangers rangers) {
		logger.debug("userList : {}", userList);
		logger.debug("userArr : {}", (Object)userid);
		logger.debug("rangers : {}", rangers);
		
//		for(String user : userid) {
//			logger.debug("userid : {}", user);
//		}
		return "multi/view";
	}
	
	
}
