package kr.or.ddit;

import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringJUnit4ClassRunner.class)	// 상속 
@ContextConfiguration(locations = 
	{"classpath:kr/or/ddit/config/spring/root-context.xml",
	 "classpath:kr/or/ddit/config/spring/application-context.xml"})
@WebAppConfiguration	// 스프링 컨테이너를 웹기반에서 동작하는 컨테이너로 생성하는 옵션@(Controller, @RequestMapping)
@Ignore
public class WebTestConfig {
	
	@Autowired
	private WebApplicationContext context;
	
	
	//dispatcherServlet 역할을 하는 객체
	protected MockMvc mockMvc;	// dispatcher servlet 역할을 하는 객체
	
	
	/*
		@Before(setup) ==> @Test ==> @After(tearDown)
	 */
	
	@Before
	public void setup() {
		mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
	}														// build가 mock 객체를 호출해줌
	
	
	@Ignore
	@Test
	public void test() {
		fail("Not yet implemented");
	}
	
	
}
