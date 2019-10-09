package kr.co.sunnyside.store.test;



import org.slf4j.Logger;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
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
import org.springframework.web.context.WebApplicationContext;

import kr.co.sunnyside.code.service.impl.CodeDaoImpl;
import kr.co.sunnyside.store.service.SEJ_StroreVO;
import kr.co.sunnyside.store.service.impl.SEJ_StroreDaoImpl;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/root-context.xml"
								  ,"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"})
public class SEJ_StoreWebTest {
	private Logger LOG= LoggerFactory.getLogger(SEJ_StoreWebTest.class);
	
	@Autowired
	WebApplicationContext context;
	
	
	@Autowired
	private SEJ_StroreDaoImpl storeDaoImpl;
	
	@Autowired
	private CodeDaoImpl codeDaoImpl;
	
	private MockMvc mockMvc;
	
	List<SEJ_StroreVO> list;
	
	@Before
	public void setUp() {
		//String fileId = StringUtil.cureDate("yyyyMMdd")+""+StringUtil.getUUID();
		
		//LOG.debug(fileId.length()+"");// 번호 등록될 때 아이디랑 뒤에 번호 아무거나 붙여져서 나온다
		list = Arrays.asList(
				new SEJ_StroreVO("20191008-001-000","고소팝콘(L)","옥수수 본연의 맛을 즐길 수 있는 짭짜름한 클래식 고소팝콘!",001,5000,"plainPopcorn.jpg"),
				new SEJ_StroreVO("20191008-001-001","달콤팝콘(L)","달콤한 카라멜 향이 가득한 달콤팝콘을 즐겨보세요!",001,6000,"caramelPopcorn.jpg"),
				new SEJ_StroreVO("20191008-001-002","더블치즈팝콘(L)","치즈매니아들이라면 놓칠 수 없는 바로 그 팝콘!",001,6000,"doublecheesePop.jpg"),
				new SEJ_StroreVO("20191008-001-003","바질어니언팝콘(L)","수많은 매니아를 보유한 바로 그 팝콘! 중독성 200%",001,6000,"onionPop.jpg")
				);
		
		mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
		LOG.debug("================================");
		LOG.debug("=context="+context);
		LOG.debug("=mockMvc="+mockMvc);
		LOG.debug("================================");
	}
	
	/**
	 * 저장
	 */
	@Test
	@Ignore
	 public void do_save() throws Exception{
	    MockHttpServletRequestBuilder createMessage 
	    = MockMvcRequestBuilders.post("/store/do_save.do").param("productNm", "영화 할인권")
	    												  .param("pruductInfo", "영화를 3000원 할인해서 보세요")
	    												  .param("category", "3")
	    												  .param("productCost", "7000")
	    												  .param("img", "ticket.jpg");
	    
	    ResultActions resultActions =  mockMvc.perform(createMessage)
	                                          .andExpect(MockMvcResultMatchers.content().contentType("application/json; charset=UTF-8"))	                              
	    							          .andExpect(MockMvcResultMatchers.jsonPath("$.msgId", is("1")));
	    String result = resultActions.andDo(print()).andReturn().getResponse().getContentAsString();
	    LOG.debug("=============================");
	    LOG.debug("=result="+result);
	    LOG.debug("=============================");
	   }
	
	/**
	* 삭제
	*
	*/
	   @Test
	   @Ignore
	   public void do_delete() throws Exception{
	      MockHttpServletRequestBuilder createMessage 
	      = MockMvcRequestBuilders.post("/store/do_delete.do").param("productId", "20191009-003-004");
	        
	      ResultActions resultActions =  mockMvc.perform(createMessage)
	                                  .andExpect(MockMvcResultMatchers.content().contentType("application/json; charset=UTF-8"))	                              
	      							  .andExpect(MockMvcResultMatchers.jsonPath("$.msgId", is("1")));
	      String result = resultActions.andDo(print()).andReturn().getResponse().getContentAsString();
	      LOG.debug("=============================");
	      LOG.debug("=result="+result);
	      LOG.debug("=============================");
	   }
	   
	   
   /**
	 * 수정
	 */
	@Test
	@Ignore
	public void do_update() throws Exception{
	    MockHttpServletRequestBuilder createMessage 
	    = MockMvcRequestBuilders.post("/store/do_update.do").param("productId", "20191009-003-004")
	    												    .param("productNm", "IMAX영화예매권")
	    												    .param("pruductInfo", "IMAX는 사람이 볼 수 있는 최대 영상이라는 뜻으로, 최고의 몰입감을 선사합니다.")
	    												    .param("productCost", "15000")
	    												    .param("img", "imaxticket.jpg");
	    
	    ResultActions resultActions =  mockMvc.perform(createMessage)
	                                //.andExpect(MockMvcResultMatchers.content().contentType("application/json; charset=UTF-8"))	                              
	    							  .andExpect(MockMvcResultMatchers.jsonPath("$.msgId", is("1")));
	    String result = resultActions.andDo(print()).andReturn().getResponse().getContentAsString();
	    LOG.debug("=============================");
	    LOG.debug("=result="+result);
	    LOG.debug("=============================");
	 }
	
   /**
	 * 단건선택
	 * 조회만 get
	 */
	@Test
	@Ignore
	public void do_selectOne() throws Exception {
		 MockHttpServletRequestBuilder createMessage 
	      = MockMvcRequestBuilders.get("/store/do_selectOne.do").param("productId", "20191008-001-003");
	        
	      ResultActions resultActions =  mockMvc.perform(createMessage)
	    		  						.andExpect(status().is2xxSuccessful());
	      String result = resultActions.andDo(print()).andReturn().getResponse().getContentAsString();
	    		  						
	      LOG.debug("=============================");
	      LOG.debug("=result="+result);
	      LOG.debug("=============================");
	}   

	/**
	 * 팝콘 전체조회
	 * 조회만 get
	 */
	@Test
	@Ignore
	public void do_retrieve_popcorn() throws Exception{
	    MockHttpServletRequestBuilder createMessage 
	    = MockMvcRequestBuilders.get("/store/do_retrieve_popcorn.do") .param("pageSize", "10")
			    												      .param("pageNum", "1");
	                                                                  
	    ResultActions resultActions =  mockMvc.perform(createMessage)                             
	    							  .andExpect(status().isOk());
	    String result = resultActions.andDo(print()).andReturn().getResponse().getContentAsString();
	    LOG.debug("=============================");
	    LOG.debug("=result="+result);
	    LOG.debug("=============================");
	   }
	
	/**
	 * 음료 전체조회
	 * 조회만 get
	 */
	@Test
	@Ignore
	public void do_retrieve_drink() throws Exception{
	    MockHttpServletRequestBuilder createMessage 
	    = MockMvcRequestBuilders.get("/store/do_retrieve_drink.do") .param("pageSize", "10")
			    												      .param("pageNum", "1");
	                                                                  
	    ResultActions resultActions =  mockMvc.perform(createMessage)                             
	    							  .andExpect(status().isOk());
	    String result = resultActions.andDo(print()).andReturn().getResponse().getContentAsString();
	    LOG.debug("=============================");
	    LOG.debug("=result="+result);
	    LOG.debug("=============================");
	   }
	
	/**
	 * 영화예매권 전체조회
	 * 조회만 get
	 */
	@Test
	@Ignore
	public void do_retrieve_movieticket() throws Exception{
	    MockHttpServletRequestBuilder createMessage 
	    = MockMvcRequestBuilders.get("/store/do_retrieve_movieticket.do") .param("pageSize", "10")
			    												          .param("pageNum", "1");
	                                                                  
	    ResultActions resultActions =  mockMvc.perform(createMessage)                             
	    							  .andExpect(status().isOk());
	    String result = resultActions.andDo(print()).andReturn().getResponse().getContentAsString();
	    LOG.debug("=============================");
	    LOG.debug("=result="+result);
	    LOG.debug("=============================");
	   }
	
}