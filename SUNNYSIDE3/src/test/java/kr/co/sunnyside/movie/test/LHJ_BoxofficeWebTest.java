package kr.co.sunnyside.movie.test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
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
import org.springframework.web.context.WebApplicationContext;

import kr.co.sunnyside.movie.service.LHJ_BoxofficeVO;
import kr.co.sunnyside.movie.service.LHJ_MovieVO;
import kr.co.sunnyside.movie.service.impl.LHJ_BoxofficeSvcImpl;
import kr.co.sunnyside.movie.service.impl.LHJ_MovieParsing;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations= {"file:src/main/webapp/WEB-INF/spring/**/*.xml"}) 
@FixMethodOrder(MethodSorters.NAME_ASCENDING) //테스트 이름 알파벳 순으로 수행한다.
public class LHJ_BoxofficeWebTest {
	private final static Logger LOG = LoggerFactory.getLogger(LHJ_BoxofficeWebTest.class);
	
	@Autowired
	private WebApplicationContext context;
	
	private MockMvc mockMvc;//테스트할 컨텍스트를 지정한 MockMvc를 생성
	
	@Autowired
	LHJ_BoxofficeSvcImpl boxofficeDaoImpl;
	
	//Test Data
	List<LHJ_BoxofficeVO> list;
	
	List<LHJ_BoxofficeVO> kobisList = new ArrayList<LHJ_BoxofficeVO>();
	
	URL url;
	
	@Before
	public void setUp() {
		list = Arrays.asList(
				 new LHJ_BoxofficeVO("F48336","조커","","","","2019-10-02","","",0,"","",0.0,0.0,"1")
				,new LHJ_BoxofficeVO("K20477","가장","","","","2019-10-02","","",0,"","",0.0,0.0,"2")
				,new LHJ_BoxofficeVO("K21186","퍼펙트맨","","","","2019-10-02","","",0,"","",0.0,0.0,"3")
				,new LHJ_BoxofficeVO("K18372","장사리 ","","","","2019-9-25","","",0,"","",0.0,0.0,"4")
				,new LHJ_BoxofficeVO("F48958","소피와","","","","2019-10-02","","",0,"","",0.0,0.0,"5")
				,new LHJ_BoxofficeVO("F49317","몬스터","","","","2019-10-03","","",0,"","",0.0,0.0,"6")
				,new LHJ_BoxofficeVO("K21060","양자물리학","","","","2019-09-25","","",0,"","",0.0,0.0,"7")
				,new LHJ_BoxofficeVO("K21094","나쁜","","","","2019-09-11","","",0,"","",0.0,0.0,"8")
				,new LHJ_BoxofficeVO("F48214","원스","","","","2019-09-25","","",0,"","",0.0,0.0,"9")
				,new LHJ_BoxofficeVO("F48401","47미터","","","","2019-08-28","","",0,"","",0.0,0.0,"10")
		);			
		
		try {
			url = new URL(LHJ_MovieParsing.kobisUrl());//url
			kobisList=LHJ_MovieParsing.getKobisData(url);//데이터를 List형태로 반환
		} catch (Exception e) {
			LOG.debug("============================");
			LOG.debug("Exception:"+e.toString());
			LOG.debug("============================");
		}
		
		mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
	}
	
	/**수정 */
	@Test
//	@Ignore
	public void do_update() throws Exception {
		MockHttpServletRequestBuilder createMessage = MockMvcRequestBuilders.post("/boxoffice/do_rank_update.do")
																			.param("movieRank", "01")
																			.param("movieId", "F48336");
		
		ResultActions resultActions =mockMvc.perform(createMessage)
				.andExpect(MockMvcResultMatchers.content().contentType("application/json;charset=UTF-8"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.msgId", is("1")));
		
		String result = resultActions.andDo(print())
				 .andReturn()
				 .getResponse()
				 .getContentAsString();

		LOG.debug("======================================");
		LOG.debug("=result="+result);
		LOG.debug("======================================");	
	}
	
	/** 저장 */
	@Test
	@Ignore
	public void do_save() throws Exception {
		MockHttpServletRequestBuilder createMessage = MockMvcRequestBuilders.post("/boxoffice/do_save.do")
																			.param("kortitle", "조커")
																			.param("relDate", "2019-10-02");
		ResultActions resultActions =mockMvc.perform(createMessage)
				.andExpect(MockMvcResultMatchers.content().contentType("application/json;charset=UTF-8"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.msgId", is("1")));
		
		String result = resultActions.andDo(print())
				 .andReturn()
				 .getResponse()
				 .getContentAsString();

		LOG.debug("======================================");
		LOG.debug("=result="+result);
		LOG.debug("======================================");	
	}
	
	/**삭제*/
	@Test
	@Ignore
	public void do_delete() throws Exception {
		MockHttpServletRequestBuilder createMessage = MockMvcRequestBuilders.post("/boxoffice/do_delete.do");
		ResultActions resultActions =mockMvc.perform(createMessage)
                							.andExpect(MockMvcResultMatchers.content().contentType("application/json;charset=UTF-8"))
                							.andExpect(MockMvcResultMatchers.jsonPath("$.msgId", is("1")));      
		
		String result = resultActions.andDo(print())
									 .andReturn()
									 .getResponse()
									 .getContentAsString();
		
		LOG.debug("======================================");
		LOG.debug("=result="+result);
		LOG.debug("======================================");					
	}
	
	@Test
	@Ignore
	public void getBean() {
		LOG.debug("=======================");
		LOG.debug("=context="+context);
		LOG.debug("=mockMvc="+mockMvc);
		LOG.debug("=======================");
		assertThat(context, is(notNullValue()));
		assertThat(mockMvc, is(notNullValue()));
	}
	
	@After
	public void tearDown() {
		
	}
	
}
