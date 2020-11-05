package kr.or.ddit.member.web;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.fileUpload;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.io.FileInputStream;
import java.io.InputStream;

import org.junit.Test;
import org.springframework.mock.web.MockMultipartFile;

import kr.or.ddit.WebTestConfig;

public class MemberRegistControllerTest extends WebTestConfig {


	// view 요청 테스트
	@Test
	public void getViewTest() throws Exception {
		mockMvc.perform(get("/memberRegist/view"))				// perform = 목업 실행
				.andExpect(status().isOk())							// 200 성공
				.andExpect(view().name("member/memberRegist"));	// name = 페이지이름 가져오기
	}
	 
	
	// 멤버 등록 테스트(성공)
	@Test
	public void processSuccessTest() throws Exception {
//		FileInputStream fis = new FileInputStream("D:\\A_TeachingMaterial\\6.JspSpring\\workspace\\spring\\src\\test\\resources\\kr\\or\\ddit\\upload\\sally.png");
		InputStream is = getClass().getResourceAsStream("/kr/or/ddit/upload/sally.png");
		MockMultipartFile file = new MockMultipartFile("realFilename", "sally.png", "image/png", is);
		mockMvc.perform(fileUpload("/memberRegist/process")
						.file(file)
						.param("userid","테스트id2")
						.param("usernm", "테스트")
						.param("pass", "123")
						.param("alias", "테스트")
						.param("addr1", "테스트")
						.param("addr2", "테스트")
						.param("zipcode", "테스트"))
		.andExpect(status().is(200))	//200 은 정상
		.andExpect(redirectedUrl("/memberList/process"));
	}
	
	
	
	
	
	
	
	
	
	
	
//	// 멤버 등록 테스트(실패)
//	@Test
//	public void processFailTest() throws Exception {
//		MvcResult result =  mockMvc.perform(post("/login/process")
//											.param("userid","brown")
//											.param("pass", "brownPassFail")).andReturn(); // 비밀번호 다르게 설정
//																			//andReturn() 리턴값 : MvcResult
//		
//		
//		ModelAndView mav = result.getModelAndView();	// model 객체와 view 객체를 관리해주는 객체
//														// model 요청 생성
//														// view 응답을 표현해주는 기능
//		
//		assertEquals("login/view", mav.getViewName());
//		assertEquals("fail", mav.getModel().get("msg"));
//	}
	
	

}
