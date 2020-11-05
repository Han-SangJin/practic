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
	 
	
	//#{userid}, #{usernm}, #{pass}, SYSDATE, #{alias}, #{addr1}, #{addr2}, #{zipcode}, #{filename}, #{realFilename})
	// 멤버 등록 테스트(성공)
	@Test
	public void processSuccessTest() throws Exception {
//		FileInputStream fis = new FileInputStream("D:\\A_TeachingMaterial\\6.JspSpring\\workspace\\spring\\src\\test\\resources\\kr\\or\\ddit\\upload\\sally.png");
		InputStream is = getClass().getResourceAsStream("/kr/or/ddit/upload/sally.png");
		MockMultipartFile file = new MockMultipartFile("realFilename", "sally.png", "image/png", is);
		mockMvc.perform(fileUpload("/memberRegist/process")
						.file(file)
						.param("userid","id테스트2")
						.param("usernm", "usernm테스트")
						.param("pass", "123")
						.param("alias", "alias테스트")
						.param("addr1", "addr1테스트")
						.param("addr2", "addr2테스트") 
						.param("zipcode", "11"))					// 디비 접속까지 뜨는데 insert값이 0으로 나오던 이유  	
		.andExpect(status().is(302))				//200 insert 실패 // 302 insert 성공 / 성공테스트 하려면 302로 바꿔서 실행
		.andExpect(view().name("redirect:/memberList/process"));	// 200으로 바꿔서 실행 해도 insert 값이 0 나와서 실패 조건문으로 가기때문에 페이지 에러발생
	}																// 테스트 통과하려면 insert 성공하는 수밖에없음..
	//오라클에서 zipcode의 데이터 크기가 5byte 였는데 범위가 넘어가는 숫자를 넣어서 안되는것 이였고	
	// insert 부분에 try ctach 예외처리가 되있어 junit 이나 콘솔창에도 에러가 뜨지 않았던것	
	
	 
	// 멤버 등록 테스트(실패)
	@Test
	public void processFailTest() throws Exception {
		InputStream is = getClass().getResourceAsStream("/kr/or/ddit/upload/sally.png");
		MockMultipartFile file = new MockMultipartFile("realFilename", "sally.png", "image/png", is);
		mockMvc.perform(fileUpload("/memberRegist/process")
						.file(file)
						.param("userid","noylit")
						.param("usernm", "테스트")
						.param("pass", "123")
						.param("alias", "테스트")
						.param("addr1", "테스트")
						.param("addr2", "테스트")
						.param("zipcode", "테스트"))
		.andExpect(status().is(200))						//200 은 정상
		.andExpect(view().name("member/memberRegist"));
	}
	
	

}
