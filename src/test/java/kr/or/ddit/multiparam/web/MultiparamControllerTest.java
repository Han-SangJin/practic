package kr.or.ddit.multiparam.web;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import org.junit.Test;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.web.servlet.ModelAndView;

import kr.or.ddit.WebTestConfig;

public class MultiparamControllerTest extends WebTestConfig{

	 
	@Test
	public void MultiparamViewTest() throws Exception {
		MvcResult result = mockMvc.perform(get("/multi/view")
									.param("userid", "brown", "sally", "cony"))
				.andDo(print())
				.andReturn();
		 
		ModelAndView mav = result.getModelAndView();
		assertEquals("multi/view", mav.getViewName());
	}
   
}