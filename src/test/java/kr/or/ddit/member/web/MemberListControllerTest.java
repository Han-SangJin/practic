package kr.or.ddit.member.web;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.Test;

import kr.or.ddit.WebTestConfig;

public class MemberListControllerTest extends WebTestConfig {
	
	
	// view 요청 테스트
	@Test
	public void getViewTest() throws Exception {
		mockMvc.perform(get("/memberList/view"))						// perform = 목업 실행
				.andExpect(status().is3xxRedirection())		// is3xxRedirection = 300대 에러 success 시키기
				.andExpect(view().name("redirect:/memberList/process"));	// name = 페이지이름 가져오기
	}
	 
	
	// 멤버 리스트 가져오는지 테스트
	@Test
	public void processSuccessTest() throws Exception {
		mockMvc.perform(post("/memberList/process"))
		.andExpect(status().is(200))							//200 은 정상
		.andExpect(view().name("/member/memberList"))			// url 값 확인
		.andExpect(model().attributeExists("memberList"));		// memberList 이름으로 된 키값이 있는지 확인
	}
	
	
	
	
}
