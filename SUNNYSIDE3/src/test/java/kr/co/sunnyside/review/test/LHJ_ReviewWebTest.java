package kr.co.sunnyside.review.test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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
import kr.co.sunnyside.review.service.LHJ_ReviewVO;
import kr.co.sunnyside.review.service.impl.LHJ_ReviewSvcImpl;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations= {"file:src/main/webapp/WEB-INF/spring/**/*.xml"}) 
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class LHJ_ReviewWebTest {
private final static Logger LOG = LoggerFactory.getLogger(LHJ_ReviewWebTest.class);
	
	@Autowired
	private WebApplicationContext context;
	
	@Autowired
	LHJ_ReviewSvcImpl reviewSvcImpl;
	
	private MockMvc mockMvc;//테스트할 컨텍스트를 지정한 MockMvc를 생성
	
	List<LHJ_ReviewVO> list;
	
	@Before
	public void setUp() {
		list = Arrays.asList(
				new LHJ_ReviewVO("movie01", "user01", "contents", 1.5, "2019/10/11")
			   ,new LHJ_ReviewVO("movie02", "user02", "contents", 1.5, "2019/10/11")
			   ,new LHJ_ReviewVO("movie03", "user03", "contents", 1.5, "2019/10/11")
			   ,new LHJ_ReviewVO("movie04", "user04", "contents", 1.5, "2019/10/11")
			   ,new LHJ_ReviewVO("movie05", "user05", "contents", 1.5, "2019/10/11")	
			   ,new LHJ_ReviewVO("movie06", "user06", "contents", 1.5, "2019/10/11")
			   ,new LHJ_ReviewVO("movie07", "user07", "contents", 1.5, "2019/10/11")
			   ,new LHJ_ReviewVO("movie08", "user08", "contents", 1.5, "2019/10/11")
			   ,new LHJ_ReviewVO("movie09", "user09", "contents", 1.5, "2019/10/11")
			   ,new LHJ_ReviewVO("movie10", "user10", "contents", 1.5, "2019/10/11")
			   );
		
		mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
	}
	
	/**전체 테스트*/
	@Test
	public void allTest() throws Exception{
		LOG.debug("======================================");
		LOG.debug("=리뷰 삭제=");
		LOG.debug("======================================");
		for(LHJ_ReviewVO vo:list) {
			do_delete(vo);
		}
		
		LOG.debug("======================================");
		LOG.debug("=리뷰 등록=");
		LOG.debug("======================================");
		for(LHJ_ReviewVO vo:list) {
			do_save(vo);
		}
		
		LOG.debug("======================================");
		LOG.debug("=리뷰 수정=");
		LOG.debug("======================================");
		LHJ_ReviewVO changeData = list.get(0);
		changeData.setContents(changeData.getContents()+"_U");
		changeData.setVisitorRate(3.3);
		
		this.do_update(changeData);
		
		LOG.debug("======================================");
		LOG.debug("=단건조회=");
		LOG.debug("======================================");
		LHJ_ReviewVO data = this.do_selectOne(list.get(0));
		LOG.debug("======================================");
		LOG.debug("=단건조회="+data);
		LOG.debug("======================================");
		
		LOG.debug("======================================");
		LOG.debug("=목록조회=");
		LOG.debug("======================================");
		
		SearchVO search=new SearchVO();
		search.setPageSize(5);
		search.setPageNum(1);
		
		List<LHJ_ReviewVO> list = this.do_retrieve(search);
		
		for(LHJ_ReviewVO vo :list) {
			LOG.debug(vo.toString());
		}
		assertThat(5, is(list.size()));
	}
	
	/** 목록조회 */
	@Test
	@Ignore
	public void do_retrieve() throws Exception {
		MockHttpServletRequestBuilder createMessage = MockMvcRequestBuilders.get("/review/do_retrieve.do")
																			.param("pageSize", "5")
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
	private List<LHJ_ReviewVO> do_retrieve(SearchVO inVO) throws Exception {
		MockHttpServletRequestBuilder createMessage = MockMvcRequestBuilders.get("/review/do_retrieve.do")
																			.param("pageSize", String.valueOf(inVO.getPageSize()))
																			.param("pageNum", String.valueOf(inVO.getPageNum()));

		//url call 결과 return
		MvcResult result = mockMvc.perform(createMessage)
				                     .andExpect(status().isOk())
				                     .andReturn()
				                     ;		
		ModelAndView   modelAndView= result.getModelAndView();
		List<LHJ_ReviewVO> list = (List<LHJ_ReviewVO>) modelAndView.getModel().get("list");
		
		LOG.debug("=====================================");
		LOG.debug("=list="+list);
		LOG.debug("=====================================");		
		
		return list;
	}
	
	
	/** 단건조회 */
	@Test
	@Ignore
	public void do_selectOne() throws Exception {
		MockHttpServletRequestBuilder createMessage = MockMvcRequestBuilders.get("/review/do_selectOne.do")
																			.param("movieId", "movie01")
																			.param("userId", "user02");
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
	private LHJ_ReviewVO do_selectOne(LHJ_ReviewVO vo) throws Exception {
	 MockHttpServletRequestBuilder createMessage = MockMvcRequestBuilders.get("/review/do_selectOne.do")
																	 	 .param("movieId", vo.getMovieId())
																		 .param("userId", vo.getUserId());
	 //url call 결과 return
	 MvcResult result = mockMvc.perform(createMessage)
			                   .andExpect(status().isOk())
			                   .andReturn();

	 //result:return VO 객체로 됨.(결과 출력 안됨.)
	 ModelAndView  modelAndView=result.getModelAndView();

	 LHJ_ReviewVO outVO = (LHJ_ReviewVO) modelAndView.getModel().get("vo");
	 LOG.debug("=====================================");

	 LOG.debug("=outVO="+outVO);
	 LOG.debug("=====================================");		

	 return outVO;
	}
	 
	
	/**리뷰수정*/
	@Test
	@Ignore
	public void do_update() throws Exception {
		MockHttpServletRequestBuilder createMessage = MockMvcRequestBuilders.post("/review/do_update.do")
																			.param("movieId", "movie01")
																			.param("userId", "user02")
																			.param("contents", "contents_u")
																			.param("visitorRate", "3.2");
		
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
	
	private void do_update(LHJ_ReviewVO vo) throws Exception {
		MockHttpServletRequestBuilder createMessage = MockMvcRequestBuilders.post("/review/do_update.do")
																			.param("movieId", vo.getMovieId())
																			.param("userId", vo.getUserId())
																			.param("contents", vo.getContents())
																			.param("visitorRate", Double.toString(vo.getVisitorRate()));
		
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
	
	/**리뷰삭제*/
	@Test
	@Ignore
	public void do_delete() throws Exception {
		MockHttpServletRequestBuilder createMessage = MockMvcRequestBuilders.post("/review/do_delete.do")
																			.param("movieId", "movie01")
																			.param("userId", "user02");
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
	
	/**리뷰삭제*/
	private void do_delete(LHJ_ReviewVO vo) throws Exception{
		MockHttpServletRequestBuilder createMessage = MockMvcRequestBuilders.post("/review/do_delete.do")
																			.param("movieId", vo.getMovieId())
																			.param("userId", vo.getUserId());
		  
		ResultActions resultActions =  mockMvc.perform(createMessage)
				                      .andExpect(MockMvcResultMatchers.content().contentType("application/json; charset=UTF-8"));
		String result = resultActions.andDo(print()).andReturn().getResponse().getContentAsString();
		LOG.debug("=============================");
		LOG.debug("=result="+result);
		LOG.debug("=============================");
	}
		
	/**리뷰저장*/
	@Test
	@Ignore
	public void do_save() throws Exception {
		MockHttpServletRequestBuilder createMessage = MockMvcRequestBuilders.post("/review/do_save.do")
																			.param("movieId", "movie01")
																			.param("userId", "user02")
																			.param("contents", "contents")
																			.param("visitorRate", "1.5");
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

	/**리뷰저장*/
	private void do_save(LHJ_ReviewVO vo) throws Exception {
		MockHttpServletRequestBuilder createMessage = MockMvcRequestBuilders.post("/review/do_save.do")
																			.param("movieId", vo.getMovieId())
																			.param("userId", vo.getUserId())
																			.param("contents", vo.getContents())
																			.param("visitorRate", Double.toString(vo.getVisitorRate()));
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
