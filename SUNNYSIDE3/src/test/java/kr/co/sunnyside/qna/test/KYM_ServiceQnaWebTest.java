package kr.co.sunnyside.qna.test;

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
import kr.co.sunnyside.qna.service.KYMQnaVO;
import kr.co.sunnyside.qna.service.impl.KYMQnaDaoImpl;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/root-context.xml"
								 , "file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"})
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class KYM_ServiceQnaWebTest {
	
	private Logger LOG = LoggerFactory.getLogger(KYM_ServiceQnaWebTest.class);
	
	@Autowired
	WebApplicationContext context;
		
	List<KYMQnaVO> list;
		
	private MockMvc mockMvc;
		
	@Autowired
	private KYMQnaDaoImpl kymQna;
		
	@Before
	public void setUp() {
		list = Arrays.asList(
				 new KYMQnaVO("테스트1","1","테스트1","테스트11","1","2019/10/16","파일1","파일11","xls",10000,"테스트111","2019/10/17")
				,new KYMQnaVO("테스트2","2","테스트2","테스트22","1","2019/10/16","파일2","파일2","xls",20000,"테스트222","2019/10/17")
				,new KYMQnaVO("테스트3","3","테스트","테스트33","1","2019/10/16","파일3","파일3","xls",30000,"테스트333","2019/10/17")
				,new KYMQnaVO("테스트4","4","테스트","테스트44","1","2019/10/16","파일4","파일4","xls",40000,"테스트444","2019/10/17")
				,new KYMQnaVO("테스트5","5","테스트5","테스트55","1","2019/10/16","파일5","파일5","xls",50000,"테스트555","2019/10/17")
					
			);
			
		mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
			
	}
	
	@Test
	public void addAndGet() throws Exception {
		LOG.debug("======================================");
		LOG.debug("=01. 기존 데이터 삭제=");
		LOG.debug("======================================");
		SearchVO search=new SearchVO();
		search.setSearchWord("공지1");
		List<KYMQnaVO> getIdList = (List<KYMQnaVO>) kymQna.do_boardIdList(search);
			
		for(KYMQnaVO vo:getIdList) {
			do_delete(vo);
		}
		
		LOG.debug("======================================");
		LOG.debug("=02. 단건등록=");
		LOG.debug("======================================");
		for(KYMQnaVO vo:list) {
			do_save(vo);
		}
		
		
		LOG.debug("======================================");
		LOG.debug("=03. 조회=");
		LOG.debug("======================================");
		getIdList = (List<KYMQnaVO>) kymQna.do_boardIdList(search);
		
		
		for(KYMQnaVO vo:getIdList) {
			KYMQnaVO vsVO = do_selectOne(vo);
			checkData(vsVO,vo);
		}
	}
	
	private void checkData(KYMQnaVO org, KYMQnaVO vs) {
		assertThat(org.getTitle(), is(vs.getTitle()));
		assertThat(org.getContents(), is(vs.getContents()));

	}
	
	@Test
	//@Ignore
	public void do_retrieve() throws Exception {
		//url,param,post/get
		MockHttpServletRequestBuilder createMessage = 
				MockMvcRequestBuilders.get("/qna/do_retrieve.do")
				.param("searchDiv", "10")
				.param("searchWord", "공지1")
				.param("pageSize", "10")
				.param("pageNum", "1");
				
		//url call 결과 return
		ResultActions resultActions = mockMvc.perform(createMessage)
				                     .andExpect(status().isOk());		
		
		String result = resultActions.andDo(print())
				.andReturn()
				.getResponse().getContentAsString();
		
		LOG.debug("=====================================");
		LOG.debug("=result="+result);
		LOG.debug("=====================================");			
	}
			
	
	/**수정 */
	@Test
	@Ignore
	public void do_update() throws Exception {
		//url,param
		MockHttpServletRequestBuilder createMessage = 
				MockMvcRequestBuilders.post("/qna/do_update.do")
				.param("userId", "공지1")
				.param("title", "공지제목1 테스트.")
		        .param("contents", "공지내용1 테스트.");
		//url call 결과 return
		ResultActions resultActions = mockMvc.perform(createMessage)
				.andExpect(MockMvcResultMatchers.content().contentType("application/json;charset=UTF-8"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.msgId", is("1")));		
		
		String result = resultActions.andDo(print())
				.andReturn()
				.getResponse().getContentAsString();
		
		LOG.debug("=====================================");
		LOG.debug("=result="+result);
		LOG.debug("=====================================");			
		
	}
	
	private void do_save(KYMQnaVO vo) throws Exception {
		//url,param
		MockHttpServletRequestBuilder createMessage = 
				MockMvcRequestBuilders.post("/qna/do_save.do")
				.param("userId", vo.getUserId())
				.param("qnaNum", vo.getQnaNum())
				.param("title", vo.getTitle())
				.param("contents", vo.getContents())
				.param("status", vo.getStatus())
				.param("regDt", vo.getReDt())
				.param("orgFileNm", vo.getOrgFileNm())
				.param("saveFileNm", vo.getSaveFileNm())
				.param("ext", vo.getExt())
				.param("reContents", vo.getReContents())
				.param("reDt", vo.getReDt());
				
		ResultActions resultActions = mockMvc.perform(createMessage)
				.andExpect(MockMvcResultMatchers.content().contentType("application/json;charset=UTF-8"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.msgId", is("1")));		
		String result = resultActions.andDo(print())
				.andReturn()
				.getResponse().getContentAsString();
		
		LOG.debug("=====================================");
		LOG.debug("=result="+result);
		LOG.debug("=====================================");				
	}
	
	/**저장 
	 * @throws Exception */
	@Test
	@Ignore
	public void do_save() throws Exception {
		//url,param
		MockHttpServletRequestBuilder createMessage = 
				MockMvcRequestBuilders.post("/qna/do_save.do")
				.param("title", "공지1.")
		        .param("contents", "공지내용1 테스트.");
				
		ResultActions resultActions = mockMvc.perform(createMessage)
				.andExpect(MockMvcResultMatchers.content().contentType("application/json;charset=UTF-8"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.msgId", is("1")));		
		String result = resultActions.andDo(print())
				.andReturn()
				.getResponse().getContentAsString();
		
		LOG.debug("=====================================");
		LOG.debug("=result="+result);
		LOG.debug("=====================================");				
	}
			
	private KYMQnaVO do_selectOne(KYMQnaVO vo) throws Exception {

		//url,param
		MockHttpServletRequestBuilder createMessage = 
				MockMvcRequestBuilders.get("/qna/do_selectOne.do")
				.param("userId", vo.getUserId());	
		//url call 결과 return
		MvcResult result = mockMvc.perform(createMessage)
				                   .andExpect(status().isOk())
				                   .andReturn()
				                   ;
		
		//result:return VO 객체로 됨.(결과 출력 않됨.)
		ModelAndView  modelAndView=result.getModelAndView();
		
		KYMQnaVO outVO = (KYMQnaVO) modelAndView.getModel().get("vo");
		LOG.debug("=====================================");
		
		LOG.debug("=outVO="+outVO);
		LOG.debug("=====================================");		
		
		return outVO;
	}
	
	
	@Test
	//@Ignore
	public void do_selectOne() throws Exception {
		//url,param
		MockHttpServletRequestBuilder createMessage = 
				MockMvcRequestBuilders.get("/qna/do_selectOne.do")
				.param("userId", "공지1");	
		//url call 결과 return
		ResultActions resultActions = mockMvc.perform(createMessage)
				                     .andExpect(status().isOk());
		
		//result:return VO 객체로 됨.(결과 출력 않됨.)
		String result = resultActions.andDo(print())
				.andReturn()
				.getResponse().getContentAsString();
		
		LOG.debug("=====================================");
		LOG.debug("=result="+result);
		LOG.debug("=====================================");		
	}
	
			
	/**
	 * Data삭제:
	 * @param user
	 * @throws Exception 
	 */
	private void do_delete(KYMQnaVO user) throws Exception {
		//url,param
		MockHttpServletRequestBuilder createMessage = 
				MockMvcRequestBuilders.post("/qna/do_delete.do")
				.param("userId", user.getUserId());
				 
		ResultActions resultActions = mockMvc.perform(createMessage)
				.andExpect(MockMvcResultMatchers.content().contentType("application/json;charset=UTF-8"));
		
		
		String result = resultActions.andDo(print())
				.andReturn()
				.getResponse().getContentAsString();
		
		LOG.debug("=====================================");
		LOG.debug("=result="+result);
		LOG.debug("=====================================");		
	}
	/**삭제 
	 * @throws Exception */
	@Test
	@Ignore
	public void do_delete() throws Exception {
		//url,param
		MockHttpServletRequestBuilder createMessage = 
				MockMvcRequestBuilders.post("/qna/do_delete.do")
				.param("userId", "공지1");
				 
		ResultActions resultActions = mockMvc.perform(createMessage)
				.andExpect(MockMvcResultMatchers.content().contentType("application/json;charset=UTF-8"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.msgId", is("1")));
		
		
		String result = resultActions.andDo(print())
				.andReturn()
				.getResponse().getContentAsString();
		
		LOG.debug("=====================================");
		LOG.debug("=result="+result);
		LOG.debug("=====================================");
				        
	}
	
	//junit의 테스트 메소드 작성방법
	//@Test public void 메소드이름(파라메터 사용금지){ }
	@Test
	public void getBean() {
		LOG.debug("====================");
		LOG.debug("=context="+context);
		LOG.debug("=mockMvc="+mockMvc);
		LOG.debug("====================");
		assertThat(context,  is(notNullValue()));
		assertThat(mockMvc,  is(notNullValue()));
	}
	
	@After
	public void tearDown() {
	
	}

}
