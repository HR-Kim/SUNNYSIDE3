package kr.co.sunnyside.movie.test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;

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
	LHJ_BoxofficeSvcImpl boxofficeSvcImpl;
	
	//Test Data
	List<LHJ_MovieVO> list;
	
	List<LHJ_MovieVO> kobisList = new ArrayList<LHJ_MovieVO>();
	
	URL url;
	
	@Before
	public void setUp() {
		list = Arrays.asList(
				new LHJ_MovieVO("F48336","조커","","","","2019-10-02","","",0,"","",0.0,0.0,"","","01")
				,new LHJ_MovieVO("K20477","가장","","","","2019-10-02","","",0,"","",0.0,0.0,"","","02")
				,new LHJ_MovieVO("K21186","퍼펙트맨","","","","2019-10-02","","",0,"","",0.0,0.0,"","","03")
				,new LHJ_MovieVO("K18372","장사리 ","","","","2019-9-25","","",0,"","",0.0,0.0,"","","04")
				,new LHJ_MovieVO("F48958","소피와","","","","2019-10-02","","",0,"","",0.0,0.0,"","","05")
				,new LHJ_MovieVO("F49317","몬스터","","","","2019-10-03","","",0,"","",0.0,0.0,"","","06")
				,new LHJ_MovieVO("K21060","양자물리학","","","","2019-09-25","","",0,"","",0.0,0.0,"","","07")
				,new LHJ_MovieVO("K21094","나쁜","","","","2019-09-11","","",0,"","",0.0,0.0,"","","08")
				,new LHJ_MovieVO("F48214","원스","","","","2019-09-25","","",0,"","",0.0,0.0,"","","09")
				,new LHJ_MovieVO("F48401","47미터","","","","2019-08-28","","",0,"","",0.0,0.0,"","","10")
		);			
		
		kobisList = LHJ_MovieParsing.getBoxofficeList();
		
		mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
	}
	
	/**전체 테스트*/
	@Test
	public void allTest() throws Exception{
		LOG.debug("======================================");
		LOG.debug("=박스오피스 상태 off=");
		LOG.debug("======================================");
		do_boxofficeOff_update();

		LOG.debug("======================================");
		LOG.debug("=박스오피스 상태 on=");
		LOG.debug("======================================");
		for(LHJ_MovieVO vo:kobisList) {
			do_boxofficeOn_update(vo);
		}	

		LOG.debug("======================================");
		LOG.debug("=박스오피스 테이블에 있는 데이터들 delete=");
		LOG.debug("======================================");
		do_delete();
		
		LOG.debug("======================================");
		LOG.debug("=박스오피스 테이블에 데이터 insert=");
		LOG.debug("======================================");
		for(LHJ_MovieVO vo:kobisList) {
			do_save(vo);
		}
		
		LOG.debug("======================================");
		LOG.debug("=단건조회=");
		LOG.debug("======================================");
		LHJ_MovieVO data = this.do_selectOne(list.get(0));
		LOG.debug("======================================");
		LOG.debug("=단건조회결과="+data);
		LOG.debug("======================================");
		
		LOG.debug("======================================");
		LOG.debug("=목록조회=");
		LOG.debug("======================================");
		List<LHJ_MovieVO> list = this.do_retrieve_param();
		
		for(LHJ_MovieVO vo :list) {
			LOG.debug(vo.toString());
		}
	}
	
	@Test
	@Ignore
	public void do_retrieve() throws Exception{
		MockHttpServletRequestBuilder createMessage = MockMvcRequestBuilders.get("/boxoffice/do_retrieve.do");
		  
		ResultActions resultActions =  mockMvc.perform(createMessage)
											  .andExpect(status().isOk()) ;
		String result = resultActions.andDo(print()).andReturn().getResponse().getContentAsString();
		LOG.debug("=============================");
		LOG.debug("=result="+result);
		LOG.debug("=============================");
	}
	
	private List<LHJ_MovieVO> do_retrieve_param() throws Exception {
		MockHttpServletRequestBuilder createMessage = MockMvcRequestBuilders.get("/boxoffice/do_retrieve.do");

		//url call 결과 return
		MvcResult result = mockMvc.perform(createMessage)
				                  .andExpect(status().isOk())
				                  .andReturn();	
		
		ModelAndView   modelAndView= result.getModelAndView();
		List<LHJ_MovieVO> list = (List<LHJ_MovieVO>) modelAndView.getModel().get("list");
		
		LOG.debug("=====================================");
		LOG.debug("=list="+list);
		LOG.debug("=====================================");		
		
		return list;
	}
		
	/**단건조회*/	
	@Test
	@Ignore
	public void do_selectOne() throws Exception{
		MockHttpServletRequestBuilder createMessage = MockMvcRequestBuilders.get("/boxoffice/do_selectOne.do")
																			.param("movieId", "F48336");
		  
		ResultActions resultActions =  mockMvc.perform(createMessage)
									  .andExpect(status().isOk());
		String result = resultActions.andDo(print()).andReturn().getResponse().getContentAsString();
		LOG.debug("=============================");
		LOG.debug("=result="+result);
		LOG.debug("=============================");
	}
	
	/** 단건조회 */
	private LHJ_MovieVO do_selectOne(LHJ_MovieVO vo) throws Exception {
		MockHttpServletRequestBuilder createMessage = MockMvcRequestBuilders.get("/boxoffice/do_selectOne.do")
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
	
	/**박스오피스 상태값 0으로 초기화(OFF)*/
	@Test
	@Ignore
	public void do_boxofficeOff_update() throws Exception {
		MockHttpServletRequestBuilder createMessage = MockMvcRequestBuilders.post("/boxoffice/do_boxofficeOff_update.do");
		
		ResultActions resultActions =mockMvc.perform(createMessage)
				.andExpect(MockMvcResultMatchers.content().contentType("application/json;charset=UTF-8"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.msgId", is("10")));
		
		String result = resultActions.andDo(print())
				.andReturn()
				.getResponse()
				.getContentAsString();
		
		LOG.debug("======================================");
		LOG.debug("=result="+result);
		LOG.debug("======================================");	
	}
	
	/**박스오피스 상태값 1으로 초기화(On) */
	@Test
	@Ignore
	public void do_boxofficeOn_update() throws Exception {
		MockHttpServletRequestBuilder createMessage = MockMvcRequestBuilders.post("/boxoffice/do_boxofficeOn_update.do")
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
	
	/**박스오피스 상태값 1으로 초기화(On) */
	private void do_boxofficeOn_update(LHJ_MovieVO vo) throws Exception {
		MockHttpServletRequestBuilder createMessage = MockMvcRequestBuilders.post("/boxoffice/do_boxofficeOn_update.do")
																			.param("kortitle", vo.getKortitle())
																			.param("relDate", vo.getRelDate());		
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
	
	/** 박스오피스 테이블에 movieId, 순위 정보 저장 */
	@Test
	@Ignore
	public void do_save() throws Exception {
		MockHttpServletRequestBuilder createMessage = MockMvcRequestBuilders.post("/boxoffice/do_save.do")
																			.param("kortitle", "조커")
																			.param("movieRank", "01")
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
	
	/** 박스오피스 테이블에 movieId, 순위 정보 저장 */
	private void do_save(LHJ_MovieVO vo) throws Exception {
		MockHttpServletRequestBuilder createMessage = MockMvcRequestBuilders.post("/boxoffice/do_save.do")
																			.param("kortitle", vo.getKortitle())
																			.param("movieRank", vo.getMovieRank())
																			.param("relDate", vo.getRelDate());
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
                							.andExpect(MockMvcResultMatchers.jsonPath("$.msgId", is("10")));      
		
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
