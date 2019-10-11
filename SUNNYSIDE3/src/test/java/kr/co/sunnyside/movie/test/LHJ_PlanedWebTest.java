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
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;

import kr.co.sunnyside.cmn.SearchVO;
import kr.co.sunnyside.movie.service.LHJ_MovieVO;
import kr.co.sunnyside.movie.service.impl.LHJ_PlanedSvcImpl;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations= {"file:src/main/webapp/WEB-INF/spring/**/*.xml"}) 
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class LHJ_PlanedWebTest {
private final static Logger LOG = LoggerFactory.getLogger(LHJ_PlanedWebTest.class);
	
	@Autowired
	private WebApplicationContext context;
	
	@Autowired
	LHJ_PlanedSvcImpl planedSvcImpl;
	
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
		mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
	}	
	
	/**전체 테스트*/
	@Test
	public void allTest() throws Exception{
		LOG.debug("======================================");
		LOG.debug("=기본상태로 상태 변경 (000)=");
		LOG.debug("======================================");
		for(LHJ_MovieVO vo:list) {
			do_update_planedDown(vo);
		}
		
		LOG.debug("======================================");
		LOG.debug("=최신개봉(상영중)으로 상태 변경(010)=");
		LOG.debug("======================================");
		for(LHJ_MovieVO vo:list) {
			do_update_planedToScreen(vo);
		}
		
		LOG.debug("======================================");
		LOG.debug("=개봉예정으로 상태 변경(020)=");
		LOG.debug("======================================");
		for(LHJ_MovieVO vo:list) {
			do_update_planedUp(vo);
		}
		
		LOG.debug("======================================");
		LOG.debug("=단건조회=");
		LOG.debug("======================================");
		LHJ_MovieVO data = this.do_selectOne(list.get(0));
		LOG.debug("======================================");
		LOG.debug("=단건조회="+data);
		LOG.debug("======================================");
		
		LOG.debug("======================================");
		LOG.debug("=목록조회=");
		LOG.debug("======================================");
		
		SearchVO search=new SearchVO();
		search.setPageSize(5);
		search.setPageNum(1);
		
		List<LHJ_MovieVO> list = this.do_retrieve(search);
		
		for(LHJ_MovieVO vo :list) {
			LOG.debug(vo.toString());
		}
		assertThat(5, is(list.size()));
	}
		
	/** 목록조회 */
	@Test
	@Ignore
	public void do_retrieve() throws Exception {
		MockHttpServletRequestBuilder createMessage = MockMvcRequestBuilders.get("/planed/do_retrieve.do")
																			.param("pageSize", "10")
																			.param("pageNum", "1");

		ResultActions resultActions = mockMvc.perform(createMessage)
				 							 .andExpect(status().isOk());
		
		String result = resultActions.andDo(print())
									 .andReturn()
									 .getResponse()
									 .getContentAsString();
		
		LOG.debug("======================================");
		LOG.debug("=result="+result);
		LOG.debug("======================================");	
	}
	
	/** 목록조회 */
	private List<LHJ_MovieVO> do_retrieve(SearchVO inVO) throws Exception {
		MockHttpServletRequestBuilder createMessage = MockMvcRequestBuilders.get("/planed/do_retrieve.do")
																			.param("pageSize", String.valueOf(inVO.getPageSize()))
																			.param("pageNum", String.valueOf(inVO.getPageNum()));

		//url call 결과 return
		MvcResult result = mockMvc.perform(createMessage)
				                     .andExpect(status().isOk())
				                     .andReturn()
				                     ;		
		ModelAndView   modelAndView= result.getModelAndView();
		List<LHJ_MovieVO> list = (List<LHJ_MovieVO>) modelAndView.getModel().get("list");
		
		LOG.debug("=====================================");
		LOG.debug("=list="+list);
		LOG.debug("=====================================");		
		
		return list;
	}
	
	
	/** 단건조회 */
	@Test
	@Ignore
	public void do_selectOne() throws Exception {
		MockHttpServletRequestBuilder createMessage = MockMvcRequestBuilders.get("/planed/do_selectOne.do")
																			.param("movieId", "F48336");
		ResultActions resultActions = mockMvc.perform(createMessage)
											 .andExpect(status().isOk());
		//result
		String result = resultActions.andDo(print())
				 					 .andReturn()
				 					 .getResponse()
				 					 .getContentAsString();
		LOG.debug("======================================");
		LOG.debug("=result="+result);
		LOG.debug("======================================");
	}
	
	 /** 단건조회 */
	 private LHJ_MovieVO do_selectOne(LHJ_MovieVO vo) throws Exception {
		 MockHttpServletRequestBuilder createMessage = MockMvcRequestBuilders.get("/planed/do_selectOne.do")
																			 .param("movieId", vo.getMovieId());

		 //url call 결과 return
		 MvcResult result = mockMvc.perform(createMessage)
				                   .andExpect(status().isOk())
				                   .andReturn();
		
		 //result:return VO 객체로 됨.(결과 출력 안됨.)
		 ModelAndView  modelAndView=result.getModelAndView();
		
		 LHJ_MovieVO outVO = (LHJ_MovieVO) modelAndView.getModel().get("vo");
		 LOG.debug("=====================================");
		
		 LOG.debug("=outVO="+outVO);
		 LOG.debug("=====================================");		
		
		 return outVO;
	 }
	 
	/** 최신개봉으로 상태 변경(상영중으로 상태 변경)(010) */
	@Test
	@Ignore
	public void do_update_planedToScreen() throws Exception {
		MockHttpServletRequestBuilder createMessage = MockMvcRequestBuilders.post("/planed/do_update_planedToScreen.do")
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
	
	/** 최신개봉으로 상태 변경(상영중으로 상태 변경)(010) */
	private void do_update_planedToScreen(LHJ_MovieVO inVO) throws Exception {
		MockHttpServletRequestBuilder createMessage = MockMvcRequestBuilders.post("/planed/do_update_planedToScreen.do")
																			.param("movieId", inVO.getMovieId());
		
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
	
	/** 비 상영상태로 상태 변경(000)*/
	@Test
	@Ignore
	public void do_update_planedDown() throws Exception {
		MockHttpServletRequestBuilder createMessage = MockMvcRequestBuilders.post("/planed/do_update_planedDown.do")
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
	
	/** 비 상영상태로 상태 변경(000)*/
	private void do_update_planedDown(LHJ_MovieVO inVO) throws Exception {
		MockHttpServletRequestBuilder createMessage = MockMvcRequestBuilders.post("/planed/do_update_planedDown.do")
																			.param("movieId", inVO.getMovieId());
		
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
	
	/** 개봉예정으로 상태 변경(020)*/
	@Test
	@Ignore
	public void do_update_planedUp() throws Exception {
		MockHttpServletRequestBuilder createMessage = MockMvcRequestBuilders.post("/planed/do_update_planedUp.do")
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
	
	/** 개봉예정으로 상태 변경(020)*/
	private void do_update_planedUp(LHJ_MovieVO inVO) throws Exception {
		MockHttpServletRequestBuilder createMessage = MockMvcRequestBuilders.post("/planed/do_update_planedUp.do")
																			.param("movieId", inVO.getMovieId());
		
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
