package kr.co.sunnyside.mypage.test;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import java.util.Arrays;
import java.util.List;

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
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.WebApplicationContext;

import kr.co.sunnyside.usermypage.service.SJH_MypageVO;
import kr.co.sunnyside.usermypage.service.impl.SJH_MypageDao;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/root-context.xml"
								  ,"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"})
public class SJH_MypageWebTest {

	private Logger LOG = LoggerFactory.getLogger(SJH_MypageWebTest.class);
	
	@Autowired
	private SJH_MypageDao dao;
	
	@Autowired
	WebApplicationContext context;
	List<SJH_MypageVO> users;
	
	private MockMvc mockMvc; //리퀘스트 호출할 부분
	
	
	@Before
	public void setUp() {
		LOG.debug("^^^^^^^^^^^^^^^^^^");
		LOG.debug("setUp()");
		LOG.debug("^^^^^^^^^^^^^^^^^^");
		
		users = Arrays.asList(
					 new SJH_MypageVO("j01_126","1234","회원이름01","이메일01@naver.com","BASIC",0,"010-0000-0001","19/09/01","",190000)
					,new SJH_MypageVO("j02_126","1234","회원이름02","이메일02@naver.com","BASIC",0,"010-0000-0002","19/09/02","",200000) //BASIC -> SILVER
					,new SJH_MypageVO("j03_126","1234","회원이름03","이메일03@naver.com","SILVER",0,"010-0000-0003","19/09/03","",200000)
					,new SJH_MypageVO("j04_126","1234","회원이름04","이메일04@naver.com","SILVER",0,"010-0000-0004","19/09/04","",400000) //SILVER -> GOLD
					,new SJH_MypageVO("j05_126","1234","회원이름05","이메일05@naver.com","GOLD",0,"010-0000-0005","19/09/05","",400000)
				);
		
		mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
		
		LOG.debug("==============================");
		LOG.debug("=context="+context);
		LOG.debug("=mockMvc="+mockMvc);
		LOG.debug("=dao="+dao);
		LOG.debug("==============================");
		
	}
	
	
	
	
	@Test
	@Ignore
	public void tx_upgradeLevels() throws Exception {
		MockHttpServletRequestBuilder createMessage = MockMvcRequestBuilders.post("/usermypage/tx_upgradeLevels.do");
				  
		ResultActions resultActions =mockMvc.perform(createMessage);
		
		String result =	resultActions.andDo(print())
				.andReturn()
				.getResponse().getContentAsString();

		LOG.debug("===============================");
		LOG.debug("=result="+result);
		LOG.debug("===============================");	
	
	}
	
	
	
	
	@Test
	@Ignore
	public void do_update() throws Exception {
		MockHttpServletRequestBuilder createMessage = MockMvcRequestBuilders.post("/usermypage/do_update.do")
													  .param("userId", "j01_126")
													  .param("passwd", "비번수정")
													  .param("userName", "이름수정")
													  .param("cellphone", "번호수정")
													  .param("email", "메일수정")
													  ;
				  
		ResultActions resultActions =mockMvc.perform(createMessage)
									.andExpect(MockMvcResultMatchers.content().contentType("application/json; charset=UTF-8")) //컨텐트타입 검증. 이렇게 나올거야라는 예상.
									;
		
		String result =	resultActions.andDo(print())
				.andReturn()
				.getResponse().getContentAsString();

		LOG.debug("===============================");
		LOG.debug("=result="+result);
		LOG.debug("===============================");	
	
	}
	
	
	
	@Test
	@Ignore
	public void do_selectOne() throws Exception {
		MockHttpServletRequestBuilder createMessage = MockMvcRequestBuilders.post("/usermypage/do_selectOne.do")
													  .param("userId", "j01_126")
													  ;
		
		ResultActions resultActions = mockMvc.perform(createMessage)
									.andExpect(MockMvcResultMatchers.content().contentType("application/json; charset=UTF-8")) //컨텐트타입 검증. 이렇게 나올거야라는 예상.
									.andExpect(MockMvcResultMatchers.jsonPath("$.userName", is("회원이름01"))) //json 수행테스트
									;
		
		String result = resultActions.andDo(print())
						.andReturn()
						.getResponse().getContentAsString();
		
		LOG.debug("==============================");
		LOG.debug("=result="+result);
		LOG.debug("==============================");
		
	}
	
	
	
	@Test
	@Ignore
	public void do_delete() throws Exception {
		MockHttpServletRequestBuilder createMessage = MockMvcRequestBuilders.post("/usermypage/do_delete.do")
														.param("userId", "j01_126")
														.param("passwd", "1234");
		
		ResultActions resultActions = mockMvc.perform(createMessage)
									.andExpect(MockMvcResultMatchers.content().contentType("application/json; charset=UTF-8"))
									;
		
		String result = resultActions.andDo(print())
						.andReturn()
						.getResponse().getContentAsString();
		
		LOG.debug("==============================");
		LOG.debug("=result="+result);
		LOG.debug("==============================");
	}
	
	
	
}
