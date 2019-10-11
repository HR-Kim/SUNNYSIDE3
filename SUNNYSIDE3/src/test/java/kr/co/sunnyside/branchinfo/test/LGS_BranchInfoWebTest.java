package kr.co.sunnyside.branchinfo.test;

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

import kr.co.sunnyside.branchinfo.service.LGS_BranchInfoVO;
import kr.co.sunnyside.branchinfo.service.impl.LGS_BranchInfoDaoImpl;
import kr.co.sunnyside.cmn.SearchVO;
import kr.co.sunnyside.room.service.LGS_RoomVO;
import kr.co.sunnyside.room.service.impl.LGS_RoomDaoImpl;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/**/*.xml"})
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class LGS_BranchInfoWebTest {
	private final Logger LOG = LoggerFactory.getLogger(this.getClass());
	
	private MockMvc mockMvc;
	
	@Autowired
	private WebApplicationContext context;
	
	@Autowired
	LGS_BranchInfoDaoImpl branchInfoDaoImpl;
	
	@Autowired
	LGS_RoomDaoImpl roomDaoImpl;
	
	private List<LGS_BranchInfoVO> list;
	
	@Before
	public void setUp() {
		mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
		list = Arrays.asList(
				new LGS_BranchInfoVO("testId001", "branch1")
				,new LGS_BranchInfoVO("testId002", "branch2")
				,new LGS_BranchInfoVO("testId003", "branch3")
				,new LGS_BranchInfoVO("testId004", "branch4")
				,new LGS_BranchInfoVO("testId005", "branch5")
				);
		
	}
	
	@Test
	public void do_retrieve() throws Exception{
		LOG.debug("======================================");
		LOG.debug("=01. 기존 데이터 찾고/삭제=");
		LOG.debug("======================================");
		
		SearchVO search = new SearchVO();
		search.setPageNum(1);
		search.setPageSize(1000);
		List<LGS_BranchInfoVO> tmp = (List<LGS_BranchInfoVO>) branchInfoDaoImpl.do_retrieve(search);
			
		for(LGS_BranchInfoVO vo : tmp) {
			do_delete(vo);
		}
		
		LOG.debug("======================================");
		LOG.debug("=02. 단건등록=");
		LOG.debug("======================================");
		for(LGS_BranchInfoVO vo : list) {
			do_save(vo);
		}                          
		LOG.debug("======================================");
		LOG.debug("=03. 목록조회 ");
		LOG.debug("======================================");
		

		List<LGS_BranchInfoVO> list = (List<LGS_BranchInfoVO>) this.get_retrieve(search);

		for(LGS_BranchInfoVO vo : list) {
			LOG.debug(vo.toString());
		}
		
		assertThat(5, is(list.size()));
		
	}
	
	@Test
	public void update() throws Exception {
		LOG.debug("======================================");
		LOG.debug("=01. 기존 데이터 찾고/삭제=");
		LOG.debug("======================================");
		
		SearchVO search = new SearchVO();
		search.setPageNum(1);
		search.setPageSize(1000);
		List<LGS_BranchInfoVO> tmp = (List<LGS_BranchInfoVO>) branchInfoDaoImpl.do_retrieve(search);
			
		for(LGS_BranchInfoVO vo : tmp) {
			do_delete(vo);
		}
		
		LOG.debug("======================================");
		LOG.debug("=02. 단건등록=");
		LOG.debug("======================================");
	
		do_save(list.get(0));
		
		
		LOG.debug("======================================");
		LOG.debug("=03. 단건조회=");
		LOG.debug("======================================");
		
		List<LGS_BranchInfoVO> tmp2 = (List<LGS_BranchInfoVO>) branchInfoDaoImpl.do_retrieve(search);

		LOG.debug("======================================");
		LOG.debug("=04. Data수정/Update=");
		LOG.debug("======================================");
		
		//Data수정
		LGS_BranchInfoVO vo = tmp2.get(0);
		vo.setBranchNm("U_"+vo.getBranchNm());
		
		//Update
		this.do_update(vo);
		
		LOG.debug("======================================");
		LOG.debug("=05. Data 조회/비교=");
		LOG.debug("======================================");		
		LGS_BranchInfoVO vsData = this.get_selectOne(vo);
		
		checkData(vo, vsData);
		
	}
	
	@Test
	public void addAndGet() throws Exception {
		LOG.debug("======================================");
		LOG.debug("=01. 기존 데이터 삭제=");
		LOG.debug("======================================");
		SearchVO search = new SearchVO();
		search.setPageNum(1);
		search.setPageSize(1000);
		List<LGS_BranchInfoVO> tmp = (List<LGS_BranchInfoVO>) branchInfoDaoImpl.do_retrieve(search);
			
		for(LGS_BranchInfoVO vo : tmp) {
			do_delete(vo);
		}
		
		LOG.debug("======================================");
		LOG.debug("=02. 단건등록=");
		LOG.debug("======================================");
		for(LGS_BranchInfoVO vo : list) {
			do_save(vo);
		}
		
		
		LOG.debug("======================================");
		LOG.debug("=03. 조회=");
		LOG.debug("======================================");
		List<LGS_BranchInfoVO> tmp2 = (List<LGS_BranchInfoVO>) branchInfoDaoImpl.do_retrieve(search);
		
		
		
		for(LGS_BranchInfoVO vo : tmp2) {
			LGS_BranchInfoVO outvo = (LGS_BranchInfoVO) branchInfoDaoImpl.do_selectOne(vo);
			checkData(outvo,vo);
		}
	}
	
	private void checkData(LGS_BranchInfoVO org, LGS_BranchInfoVO vs) {
		assertThat(org.getBranchId(), is(vs.getBranchId()));
		assertThat(org.getBranchNm(), is(vs.getBranchNm()));
	}
	
	public List<LGS_BranchInfoVO> get_retrieve(SearchVO vo) throws Exception {
		MockHttpServletRequestBuilder createMessage = 
				MockMvcRequestBuilders.get("/branchInfo/do_retrieve.do")
										.param("searchDiv", vo.getSearchDiv())
										.param("searchWord", vo.getSearchWord())
										.param("pageSize", String.valueOf(vo.getPageSize()))
										.param("pageNum", String.valueOf(vo.getPageNum()));
		
		ResultActions resultAction = mockMvc.perform(createMessage)
										.andExpect(status().isOk());
		
		MvcResult result = mockMvc.perform(createMessage)
                .andExpect(status().isOk())
                .andReturn()
                ;

		ModelAndView modelAndView = result.getModelAndView();
		
		List<LGS_BranchInfoVO> list = (List<LGS_BranchInfoVO>) modelAndView.getModel().get("branchList");
		LOG.debug("=====================================");
		
		LOG.debug("=list=" + list);
		LOG.debug("=====================================");		
		
		return list;
	}
	
	public void do_update(LGS_BranchInfoVO vo) throws Exception {
		MockHttpServletRequestBuilder createMessage = 
				MockMvcRequestBuilders.post("/branchInfo/do_update.do")
				.param("branchNm", vo.getBranchNm().toString())
				.param("branchId", vo.getBranchId().toString());
		
		ResultActions resultAction = mockMvc.perform(createMessage)
										.andExpect(MockMvcResultMatchers.content().contentType("application/json; charset=UTF-8"))
										.andExpect(MockMvcResultMatchers.jsonPath("$.msgId", is("1")));
	
		String result = resultAction.andDo(print())
										.andReturn()
										.getResponse()
										.getContentAsString();
		
		LOG.debug("=====================================================");
		LOG.debug("return = " + result);
		LOG.debug("=====================================================");
		
	}
	
	public void do_save(LGS_BranchInfoVO vo) throws Exception {
		MockHttpServletRequestBuilder createMessage = 
				MockMvcRequestBuilders.post("/branchInfo/do_save.do")
				.param("branchNm", vo.getBranchNm().toString());
		
		ResultActions resultAction = mockMvc.perform(createMessage)
										.andExpect(MockMvcResultMatchers.content().contentType("application/json; charset=UTF-8"))
										.andExpect(MockMvcResultMatchers.jsonPath("$.msgId", is("1")));
	
		String result = resultAction.andDo(print())
										.andReturn()
										.getResponse()
										.getContentAsString();
		
		LOG.debug("=====================================================");
		LOG.debug("return = " + result);
		LOG.debug("=====================================================");
	}
	
	public LGS_BranchInfoVO get_selectOne(LGS_BranchInfoVO vo) throws Exception {
		MockHttpServletRequestBuilder createMessage = 
				MockMvcRequestBuilders.post("/branchInfo/do_selectOne.do")
										.param("branchNm", vo.getBranchNm().toString());
		
		ResultActions resultAction = mockMvc.perform(createMessage)
										.andExpect(status().isOk());
		
		MvcResult result = mockMvc.perform(createMessage)
                .andExpect(status().isOk())
                .andReturn();

		ModelAndView modelAndView = result.getModelAndView();
		
		LGS_BranchInfoVO outVO = (LGS_BranchInfoVO) modelAndView.getModel().get("vo");
		LOG.debug("=====================================");
		
		LOG.debug("=outVO="+outVO);
		LOG.debug("=====================================");		
		
		return outVO;
	}
	
	public void do_delete(LGS_BranchInfoVO vo) throws Exception {
		//url, param
		MockHttpServletRequestBuilder createMessage = 
				MockMvcRequestBuilders.post("/branchInfo/do_delete.do")
										.param("branchId", vo.getBranchId().toString());
		ResultActions resultAction = mockMvc.perform(createMessage)
										.andExpect(MockMvcResultMatchers.content().contentType("application/json; charset=UTF-8"))
										.andExpect(MockMvcResultMatchers.jsonPath("$.msgId", is("1")));
	
		String result = resultAction.andDo(print())
										.andReturn()
										.getResponse()
										.getContentAsString();
		
		LOG.debug("=====================================================");
		LOG.debug("return = " + result);
		LOG.debug("=====================================================");
	}
	
	@Test
	public void getBean() {
		LOG.debug("====================");
		LOG.debug("context = " + context);
		LOG.debug("branchInfoDaoImpl = " + branchInfoDaoImpl);
		LOG.debug("====================");
		
		assertThat(context, is(notNullValue()) );
		assertThat(branchInfoDaoImpl, is(notNullValue()) );
		
		//테스트전 처리
		SearchVO search = new SearchVO();
		search.setPageNum(1);
		
		search.setPageSize(1000);
		
		List<LGS_RoomVO> tmp0 = (List<LGS_RoomVO>) roomDaoImpl.do_retrieve(search);
		if(tmp0 != null) {
			for(LGS_RoomVO vo : tmp0) {
				LGS_RoomVO out = (LGS_RoomVO) roomDaoImpl.do_selectOne(vo);
				int flag = roomDaoImpl.do_delete(out);
				assertThat(1, is(flag));
			}
		}
		
		List<LGS_BranchInfoVO> tmp = (List<LGS_BranchInfoVO>) branchInfoDaoImpl.do_retrieve(search);
		if(tmp != null) {
			for(LGS_BranchInfoVO vo : tmp) {
				LGS_BranchInfoVO out = (LGS_BranchInfoVO) branchInfoDaoImpl.do_selectOne(vo);
				int flag = branchInfoDaoImpl.do_delete(out);
				assertThat(1, is(flag));
			}
		}
	}
	
	@After
	public void tearDown() {
		
	}
	
}
