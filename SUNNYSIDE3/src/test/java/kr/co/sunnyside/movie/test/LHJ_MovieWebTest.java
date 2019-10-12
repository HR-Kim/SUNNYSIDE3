package kr.co.sunnyside.movie.test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.IOException;
import java.text.ParseException;
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
import org.springframework.web.context.WebApplicationContext;

import kr.co.sunnyside.movie.service.LHJ_MovieVO;
import kr.co.sunnyside.movie.service.impl.LHJ_MovieSvcImpl;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations= {"file:src/main/webapp/WEB-INF/spring/**/*.xml"}) 
@FixMethodOrder(MethodSorters.NAME_ASCENDING) //테스트 이름 알파벳 순으로 수행한다.
public class LHJ_MovieWebTest {
	private final static Logger LOG = LoggerFactory.getLogger(LHJ_MovieWebTest.class);
	
	@Autowired
	private WebApplicationContext context;
	
	@Autowired
	private LHJ_MovieSvcImpl movieSvcImpl;
	
	private MockMvc mockMvc;//테스트할 컨텍스트를 지정한 MockMvc를 생성
	
	List<LHJ_MovieVO> list = new ArrayList<LHJ_MovieVO>();
	
	@Before
	public void setUp() throws IOException, ParseException{
		list = Arrays.asList(
				 new LHJ_MovieVO("F48336","조커","","","","2019-10-02","","",0,"","",0.0,0.0,"000","0","01")
				,new LHJ_MovieVO("K20477","가장","","","","2019-10-02","","",0,"","",0.0,0.0,"000","0","02")
				,new LHJ_MovieVO("K21186","퍼펙트맨","","","","2019-10-02","","",0,"","",0.0,0.0,"000","0","03")
				,new LHJ_MovieVO("K18372","장사리 ","","","","2019-9-25","","",0,"","",0.0,0.0,"000","0","04")
				,new LHJ_MovieVO("F48958","소피와","","","","2019-10-02","","",0,"","",0.0,0.0,"000","0","05")
				,new LHJ_MovieVO("F49317","몬스터","","","","2019-10-03","","",0,"","",0.0,0.0,"000","0","06")
				,new LHJ_MovieVO("K21060","양자물리학","","","","2019-09-25","","",0,"","",0.0,0.0,"000","0","07")
				,new LHJ_MovieVO("K21094","나쁜","","","","2019-09-11","","",0,"","",0.0,0.0,"000","0","08")
				,new LHJ_MovieVO("F48214","원스","","","","2019-09-25","","",0,"","",0.0,0.0,"000","0","09")
				,new LHJ_MovieVO("F48401","47미터","","","","2019-08-28","","",0,"","",0.0,0.0,"000","0","10")
		);			
	}	 
	
	@Test
	@Ignore
	public void do_retrieve() throws Exception{
		MockHttpServletRequestBuilder createMessage 
		= MockMvcRequestBuilders.get("/movie/do_retrieve.do").param("pageSize", "10")
															  .param("pageNum", "1")
														      .param("searchDiv", "")
														      .param("searchWord", "");
		  
		ResultActions resultActions =  mockMvc.perform(createMessage)
											  .andExpect(status().isOk()) ;
		String result = resultActions.andDo(print()).andReturn().getResponse().getContentAsString();
		LOG.debug("=============================");
		LOG.debug("=result="+result);
		LOG.debug("=============================");
	}
	
	@Test
	@Ignore
	public void getBean() {
		LOG.debug("======================");
		LOG.debug("context="+context);
		LOG.debug("movieSvcImpl="+movieSvcImpl);
		LOG.debug("======================");
		assertThat(context, is(notNullValue()));
		assertThat(movieSvcImpl, is(notNullValue()));
	}
	
	@After
	public void tearDown() {
		
	}
	
}
