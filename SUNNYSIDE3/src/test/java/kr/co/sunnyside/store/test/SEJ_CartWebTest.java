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
import kr.co.sunnyside.store.service.SEJ_CartVO;
import kr.co.sunnyside.store.service.SEJ_StroreVO;
import kr.co.sunnyside.store.service.impl.SEJ_CartDaoImpl;
import kr.co.sunnyside.store.service.impl.SEJ_StroreDaoImpl;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/root-context.xml"
								  ,"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"})
public class SEJ_CartWebTest {
	private Logger LOG= LoggerFactory.getLogger(SEJ_CartWebTest.class);
	
	@Autowired
	WebApplicationContext context;
	
	@Autowired
	private SEJ_CartDaoImpl cartDaoImpl;
	
	private MockMvc mockMvc;
	
	List<SEJ_CartVO> list;
	
	@Before
	public void setUp() {
		//String fileId = StringUtil.cureDate("yyyyMMdd")+""+StringUtil.getUUID();
		
		//LOG.debug(fileId.length()+"");// 번호 등록될 때 아이디랑 뒤에 번호 아무거나 붙여져서 나온다
		list = Arrays.asList(
				);
		
			mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
			LOG.debug("================================");
			LOG.debug("=context="+context);
			LOG.debug("=mockMvc="+mockMvc);
			LOG.debug("================================");
	}
	
	/**장바구니 저장 */
	@Test
	@Ignore
	 public void do_save() throws Exception{
	    MockHttpServletRequestBuilder createMessage 
	    = MockMvcRequestBuilders.post("/cart/do_save.do") .param("productNm", "기프트카드")
	    												  .param("userId", "admin")
	    												  .param("count", "3")
	    												  .param("productCost", "30000")
	    												  .param("productId", "20191011-003-005");
	    
	    ResultActions resultActions =  mockMvc.perform(createMessage);
	    String result = resultActions.andDo(print()).andReturn().getResponse().getContentAsString();
	    LOG.debug("=============================");
	    LOG.debug("=result="+result);
	    LOG.debug("=============================");
	   }

	/**장바구니 삭제 */
	   @Test
	   @Ignore
	   public void do_delete() throws Exception{
	      MockHttpServletRequestBuilder createMessage 
	      = MockMvcRequestBuilders.post("/cart/do_delete.do").param("cartId", "20191018-002");
	      													        
	      ResultActions resultActions =  mockMvc.perform(createMessage);
	      String result = resultActions.andDo(print()).andReturn().getResponse().getContentAsString();
	      LOG.debug("=============================");
	      LOG.debug("=result="+result);
	      LOG.debug("=============================");
	   }
	   
	   /**장바구니 전체삭제 */
	   @Test
	   @Ignore
	   public void do_deleteAll() throws Exception{
	      MockHttpServletRequestBuilder createMessage 
	      = MockMvcRequestBuilders.post("/cart/do_deleteAll.do");
	      													        
	      ResultActions resultActions =  mockMvc.perform(createMessage);
	      String result = resultActions.andDo(print()).andReturn().getResponse().getContentAsString();
	      LOG.debug("=============================");
	      LOG.debug("=result="+result);
	      LOG.debug("=============================");
	   }
	   
	/**장바구니 수정 */
	@Test
	@Ignore
	public void do_update() throws Exception{
	    MockHttpServletRequestBuilder createMessage 
	    = MockMvcRequestBuilders.post("/cart/do_update.do").param("count", "12")
	    												    .param("productId", "20191011-003-005")
	    												    .param("userId", "admin");
	    ResultActions resultActions =  mockMvc.perform(createMessage);
	    String result = resultActions.andDo(print()).andReturn().getResponse().getContentAsString();
	    LOG.debug("=============================");
	    LOG.debug("=result="+result);
	    LOG.debug("=============================");
	 }
	
	/**장바구니 목록 */
	@Test
	@Ignore
	public void do_retrieve() throws Exception{
	    MockHttpServletRequestBuilder createMessage 
	    = MockMvcRequestBuilders.get("/cart/do_retrieve.do") .param("userId", "admin");
	                                                                  
	    ResultActions resultActions =  mockMvc.perform(createMessage)                             
	    							  .andExpect(status().isOk());
	    String result = resultActions.andDo(print()).andReturn().getResponse().getContentAsString();
	    LOG.debug("=============================");
	    LOG.debug("=result="+result);
	    LOG.debug("=============================");
	   }
	

}