package kr.or.ddit.member.web;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.Test;

import kr.or.ddit.WebTestConfig;

public class MemberControllerTest extends WebTestConfig {
	// 테스트 대상 클래스 : LoginController
	// 						--> MemberService
	//							--> MemberRepository
	// LoginController 스프링 빈을 생성하기 위해서는 MemverService, MemberRepository 스프링 빈이 필요
	// 즉 service, repository 빈을 스캔하는 설정화 controller 를 스캔하는 설정 두개가 필요
	
	// 스프링 프레임워크 컨트롤러 테스트 시나리오
	//1. 웹기반의 스프링 컨테이너를 구성후
	//2. dispatcherServlet 역할을 하는 객체를 생성
	//3. dispatcherServlet 역할을 하는 객체를 통해 url, 파라미터 등을 첨부하여 요청전송
	//4. 응답이 원허는 형태로 나오는지 체크(viewName, model에 담긴 속성)
	
	
	/* WebTestConfig.java 로 테스트설정 코드 넣어둠*/
	
	
	/* MockMvc 기능 */
	
	// get(), post() : get/post 요청
	// param(파라미터명, 파라미터값) : 요청시 보낼 파라미터
	// Expect 예상값
	
	// status() : 스프링 프레임 워크에 의해 요청이 처리되고 생성된 응답 코드
	// view() :  스프링 프레임 워크에 의해 요청이 처리되는 과정에서 반환된 viewName
	// model() : 컨트롤러에서 설정한 속성값을 담는 객체
	// request() : 요청 객체 얻어오기
	
	
	// view 요청 테스트
	@Test
	public void getViewTest() throws Exception {
		mockMvc.perform(get("/member/view"))				// perform = 목업 실행
				.andExpect(status().isOk())				// idOk = erorr code :  ex)200, 404
				.andExpect(view().name("forward:/member/process"));	// name = 페이지이름 가져오기
	}
	 
	
	// 아이디 값으로 멤베vo 요청 
	@Test
	public void processSuccessTest() throws Exception {
		mockMvc.perform(post("/member/process")
						.param("userid","cony"))			// 전달할 파라미터
		.andExpect(status().is(200))	//200 은 정상
		.andExpect(view().name("member/member"))			// url 값 확인
		.andExpect(model().attributeExists("memberVo"));	// memberVo 이름으로 된 키값이 있는지 확인
	}
	
}
