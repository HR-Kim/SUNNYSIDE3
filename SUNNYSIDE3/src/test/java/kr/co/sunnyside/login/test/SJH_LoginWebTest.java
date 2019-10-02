package kr.co.sunnyside.login.test;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import kr.co.sunnyside.login.service.SJH_LoginVO;
import kr.co.sunnyside.login.service.impl.SJH_LoginDao;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/root-context.xml"
								  ,"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"})
public class SJH_LoginWebTest {
	private Logger LOG = LoggerFactory.getLogger(SJH_LoginWebTest.class);
	
	@Autowired
	private SJH_LoginDao dao;
	
	@Autowired
	WebApplicationContext context;
	List<SJH_LoginVO> users;
	
	private MockMvc mockMvc; //리퀘스트 호출할 부분
	
	@Before
	public void setUp() {
		LOG.debug("^^^^^^^^^^^^^^^^^^");
		LOG.debug("setUp()");
		LOG.debug("^^^^^^^^^^^^^^^^^^");
		
		users = Arrays.asList(
				 new SJH_LoginVO("j01_126","1234","회원이름01","이메일01@naver.com","BASIC",0,"010-0000-0001","19/09/01","")
				,new SJH_LoginVO("j02_126","1234","회원이름02","이메일02@naver.com","BASIC",0,"010-0000-0002","19/09/02","")
				,new SJH_LoginVO("j03_126","1234","회원이름03","이메일03@naver.com","SILVER",0,"010-0000-0003","19/09/03","")
				,new SJH_LoginVO("j04_126","1234","회원이름04","이메일04@naver.com","SILVER",0,"010-0000-0004","19/09/04","")
				,new SJH_LoginVO("j05_126","1234","회원이름05","이메일05@naver.com","GOLD",0,"010-0000-00005","19/09/05","")
				);
		
		mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
		
		LOG.debug("==============================");
		LOG.debug("=context="+context);
		LOG.debug("=mockMvc="+mockMvc);
		LOG.debug("=dao="+dao);
		LOG.debug("==============================");
		
	}
	
	
	@Test
	//@Ignore
	public void do_login() throws Exception {
		SJH_LoginVO user = users.get(0);
		MockHttpServletRequestBuilder createMessage = MockMvcRequestBuilders.post("/login/do_login.do")
																			  .param("user_id", user.getUserId())
																			  .param("passwd", user.getPasswd())
																			  ;

		ResultActions resultActions =mockMvc.perform(createMessage)
											.andExpect(status().isOk())
											//.andExpect(forwardedUrl("/main/main.jsp"))
											;
									
		String result =	resultActions.andDo(print())
									.andReturn()
									.getResponse().getContentAsString();
		
		LOG.debug("===============================");
		LOG.debug("=result="+result);
		LOG.debug("===============================");	
	}
	
	
	
	
	
	@After
	public void tearDown() {
		LOG.debug("^^^^^^^^^^^^^^^^^^");
		LOG.debug("tearDown()");
		LOG.debug("^^^^^^^^^^^^^^^^^^");
	}
	
	
	
}
