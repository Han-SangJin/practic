package kr.or.ddit.member.web;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.fileUpload;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.io.InputStream;

import org.junit.Test;
import org.springframework.mock.web.MockMultipartFile;

import kr.or.ddit.WebTestConfig;

public class MemberUpdateControllerTest extends WebTestConfig {

	
	// view 요청및 멤버객체 반환 테스트
	@Test
	public void getViewTest() throws Exception {
		mockMvc.perform(get("/memberUpdate/view")
						.param("userid", "noylit"))
		.andExpect(status().isOk())
		.andExpect(model().attributeExists("memberVo"))
		.andExpect(view().name("member/memberUpdate"));
	}

	
// UPDATE users SET usernm = #{usernm}, pass = #{pass},  alias = #{alias},  addr1 = #{addr1}, addr2 = #{addr2}, zipcode = #{zipcode}
// WHERE userid = #{userid}
	// 멤버 정보 업데이트 테스트(성공)
	@Test
	public void processSuccessTest() throws Exception {
		InputStream is = getClass().getResourceAsStream("/kr/or/ddit/upload/sally.png");
		MockMultipartFile file = new MockMultipartFile("realFilename", "sally.png", "image/png", is);
		mockMvc.perform(fileUpload("/memberUpdate/process")
						.file(file)
						.param("usernm", "한상진 테스트")
						.param("pass", "123")
						.param("alias", "테스트 성공")
						.param("addr1", "대전시 대덕구 송촌동")
						.param("addr2", "483-15")
						.param("zipcode", "3322")
						.param("userid", "noylit"))
		.andExpect(status().is(302))
		.andExpect(view().name("redirect:/member/view?userid=noylit"));	// redirect 나 forward는 resolver가 붙지 않기 때문에 url 앞에 / 를 빼먹지 말고 붙여줘야 페이지를 찾을수 있음
	}
	
	
	// 멤버 정보 업데이트 테스트(실패)
	@Test
	public void processFailTest() throws Exception {
		InputStream is = getClass().getResourceAsStream("/kr/or/ddit/upload/sally.png");
		MockMultipartFile file = new MockMultipartFile("realFilename", "sally.png", "image/png", is);
		mockMvc.perform(fileUpload("/memberUpdate/process")
						.file(file)
						.param("usernm", "한상진 테스트")
						.param("pass", "123")
						.param("alias", "테스트 실패")
						.param("addr1", "대전시 대덕구 송촌동")
						.param("addr2", "483-15")
						.param("zipcode", "3322")
						.param("userid", "noylit1"))
		.andExpect(status().is(200))
		.andExpect(view().name("member/memberUpdate"));
	}
		
}
