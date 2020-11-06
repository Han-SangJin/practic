package kr.or.ddit.fileupload.web;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.fileUpload;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.io.FileInputStream;
import java.io.InputStream;

import org.junit.Test;
import org.springframework.mock.web.MockMultipartFile;

import kr.or.ddit.WebTestConfig;

public class FileUploadUtilTest extends WebTestConfig {

	
	/* WebTestConfig.java 테스트설정 코드 상속받음*/
	
	
	@Test
	public void getviewTest() throws Exception {
		mockMvc.perform(get("/fileupload/view"))				// perform = 목업 실행
				.andExpect(status().isOk())						// idOk = erorr code :  ex)200, 404
				.andExpect(view().name("fileupload/fileupload"));	// name = 페이지이름 가져오기
	}
	
	
	@Test
	public void uploadTest() throws Exception {
//		String name, String originalFilename, String contentType, byte[] content
		
		InputStream is = getClass().getResourceAsStream("/kr/or/ddit/upload/sally.png");
		
//		InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream("kr/or/ddit/upload/sally.png");
		
//		getClass().getResourceAsStream("src/test/resources/kr/or/ddit/upload/sally.png");
		FileInputStream fis = new FileInputStream("D:\\A_TeachingMaterial\\6.JspSpring\\workspace\\spring\\src\\test\\resources\\kr\\or\\ddit\\upload\\sally.png");
		MockMultipartFile file = new MockMultipartFile("file", "sally.png", "image/png", fis); 
		mockMvc.perform(fileUpload("/fileupload/upload")
				.file(file)
				.param("userid", "brown"))
		.andExpect(view().name("fileupload/fileupload"))
		.andExpect(status().isOk());
		
	} 
} 
 