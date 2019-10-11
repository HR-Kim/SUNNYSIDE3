package kr.co.sunnyside.screeninfo.test;

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

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import kr.co.sunnyside.branchinfo.service.LGS_BranchInfoVO;
import kr.co.sunnyside.branchinfo.service.impl.LGS_BranchInfoDaoImpl;
import kr.co.sunnyside.cmn.SearchVO;
import kr.co.sunnyside.room.service.LGS_RoomVO;
import kr.co.sunnyside.room.service.impl.LGS_RoomDaoImpl;
import kr.co.sunnyside.screeninfo.service.LGS_ScreenInfoVO;
import kr.co.sunnyside.screeninfo.service.impl.LGS_ScreenInfoDaoImpl;
import kr.co.sunnyside.seat.service.LGS_SeatVO;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/**/*.xml"})
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class LGS_ScreenInfoWebTest {
	private final Logger LOG = LoggerFactory.getLogger(this.getClass());
	
	private MockMvc mockMvc;
	
	@Autowired
	private WebApplicationContext context;
	
	@Autowired
	LGS_ScreenInfoDaoImpl screenInfoDaoImpl;
	
	@Autowired
	LGS_RoomDaoImpl roomDaoImpl;
	
	@Autowired
	LGS_BranchInfoDaoImpl branchInfoDaoImpl;
	
	private List<LGS_ScreenInfoVO> list;
	
	@Before
	public void setUp() {
		mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
		start();
		
	}
	
	@Test
	public void do_retrieve() throws Exception{
		LOG.debug("======================================");
		LOG.debug("=01. 기존 데이터 찾고/삭제=");
		LOG.debug("======================================");
		
		this.intro();
		
		SearchVO search = new SearchVO();
		search.setPageNum(1);
		search.setPageSize(1000);
		String jsonString0 =  this.get_retrieve(search);
		JsonParser parser0 = new JsonParser();
		JsonElement a0 = (JsonElement)parser0.parse(jsonString0);
		JsonArray jArr0 =a0.getAsJsonArray();
		for(int i=0 ; i<jArr0.size() ; i++) {
			LGS_ScreenInfoVO vo = new LGS_ScreenInfoVO();
			vo.setScreenId(jArr0.get(i).getAsJsonObject().get("screenId").getAsString());	
			do_delete(vo);
		}
		
		LOG.debug("======================================");
		LOG.debug("=02. 단건등록=");
		LOG.debug("======================================");
		for(LGS_ScreenInfoVO vo : list) {
			do_save(vo);
		}                       
		LOG.debug("======================================");
		LOG.debug("=03. 목록조회 ");
		LOG.debug("======================================");
		

		search.setPageNum(1);
		search.setPageSize(1000);
		String jsonString =  this.get_retrieve(search);
		JsonParser parser = new JsonParser();
		JsonElement a = (JsonElement)parser.parse(jsonString);
		JsonArray jArr =a.getAsJsonArray();
		
		assertThat(5, is(jArr.size()));
		
	}
	
	@Test
	public void update() throws Exception {
		LOG.debug("======================================");
		LOG.debug("=01. 기존 데이터 찾고/삭제=");
		LOG.debug("======================================");
		
		this.intro();
		
		SearchVO search = new SearchVO();
		search.setPageNum(1);
		search.setPageSize(1000);
		String jsonString0 =  this.get_retrieve(search);
		JsonParser parser0 = new JsonParser();
		JsonElement a0 = (JsonElement)parser0.parse(jsonString0);
		JsonArray jArr0 =a0.getAsJsonArray();
		for(int i=0 ; i<jArr0.size() ; i++) {
			LGS_ScreenInfoVO vo = new LGS_ScreenInfoVO();
			vo.setScreenId(jArr0.get(i).getAsJsonObject().get("screenId").getAsString());	
			do_delete(vo);
		}
		
		LOG.debug("======================================");
		LOG.debug("=02. 단건등록=");
		LOG.debug("======================================");
	
		do_save(list.get(0));
		
		
		LOG.debug("======================================");
		LOG.debug("=03. 단건조회=");
		LOG.debug("======================================");
		
		List<LGS_ScreenInfoVO> tmp2 = (List<LGS_ScreenInfoVO>) screenInfoDaoImpl.do_retrieve(search);

		LOG.debug("======================================");
		LOG.debug("=04. Data수정/Update=");
		LOG.debug("======================================");
		
		//Data수정
		LGS_ScreenInfoVO vo = tmp2.get(0);
		vo.setAdultCost(9999+vo.getAdultCost());
		
		//Update
		this.do_update(vo);
		
		LOG.debug("======================================");
		LOG.debug("=05. Data 조회/비교=");
		LOG.debug("======================================");		
		LGS_ScreenInfoVO vsData = this.get_selectOne(vo);
		
		checkData(vo, vsData);
		
	}
	
	@Test
	public void addAndGet() throws Exception {
		LOG.debug("======================================");
		LOG.debug("=01. 기존 데이터 삭제=");
		LOG.debug("======================================");
		this.intro();
		
		SearchVO search = new SearchVO();
		search.setPageNum(1);
		search.setPageSize(1000);
		String jsonString0 =  this.get_retrieve(search);
		JsonParser parser0 = new JsonParser();
		JsonElement a0 = (JsonElement)parser0.parse(jsonString0);
		JsonArray jArr0 =a0.getAsJsonArray();
		for(int i=0 ; i<jArr0.size() ; i++) {
			LGS_ScreenInfoVO vo = new LGS_ScreenInfoVO();
			vo.setScreenId(jArr0.get(i).getAsJsonObject().get("screenId").getAsString());	
			do_delete(vo);
		}
		
		LOG.debug("======================================");
		LOG.debug("=02. 단건등록=");
		LOG.debug("======================================");
		for(LGS_ScreenInfoVO vo : list) {
			do_save(vo);
		}
		
		
		LOG.debug("======================================");
		LOG.debug("=03. 조회=");
		LOG.debug("======================================");
		search.setPageNum(1);
		search.setPageSize(1000);
		String jsonString =  this.get_retrieve(search);
		JsonParser parser = new JsonParser();
		JsonElement a = (JsonElement)parser0.parse(jsonString0);
		JsonArray jArr =a0.getAsJsonArray();
		
		for(int i=0 ; i<jArr0.size() ; i++) {
			LGS_ScreenInfoVO vo = new LGS_ScreenInfoVO();
			vo.setScreenId(jArr0.get(i).getAsJsonObject().get("screenId").getAsString());	
			LGS_ScreenInfoVO outvo = (LGS_ScreenInfoVO) this.get_selectOne(vo);
			checkData(outvo,vo);
		}
		
	
	}
	
	private void checkData(LGS_ScreenInfoVO org, LGS_ScreenInfoVO vs) {
		assertThat(org.getBranchId(), is(vs.getBranchId()));
		assertThat(org.getScreenId(), is(vs.getScreenId()));
		
	}
	
	public String get_retrieve(SearchVO vo) throws Exception {
		MockHttpServletRequestBuilder createMessage = 
				MockMvcRequestBuilders.post("/screenInfo/do_retrieve.do")
										.param("searchDiv", vo.getSearchDiv())
										.param("searchWord", vo.getSearchWord())
										.param("pageSize", String.valueOf(vo.getPageSize()))
										.param("pageNum", String.valueOf(vo.getPageNum()));
		
		ResultActions resultAction = mockMvc.perform(createMessage)
										.andExpect(status().isOk());
		
		String result = resultAction.andDo(print())
				.andReturn()
				.getResponse()
				.getContentAsString();
		LOG.debug("=====================================");
		
		LOG.debug("=result=" + result);
		LOG.debug("=====================================");		

		return result;
	}
	
	public void do_update(LGS_ScreenInfoVO vo) throws Exception {
		MockHttpServletRequestBuilder createMessage = 
				MockMvcRequestBuilders.post("/screenInfo/do_update.do")
				.param("screenId", vo.getScreenId().toString())
				.param("roomId", vo.getRoomId().toString())
				.param("branchId", vo.getBranchId().toString())
				.param("movieId", vo.getMovieId().toString())
				.param("startTime", vo.getStartTime().toString())
				.param("endTime", vo.getEndTime().toString())
				.param("screenDt", vo.getScreenDt().toString())
				.param("adultCost", vo.getAdultCost()+"")
				.param("studentCost", vo.getStudentCost()+"")
				.param("epside", vo.getEpisode()+"");
		
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
	
	public void do_save(LGS_ScreenInfoVO vo) throws Exception {
		MockHttpServletRequestBuilder createMessage = 
				MockMvcRequestBuilders.post("/screenInfo/do_save.do")
				.param("roomId", vo.getRoomId().toString())
				.param("branchId", vo.getBranchId().toString())
				.param("movieId", vo.getMovieId().toString())
				.param("startTime", vo.getStartTime().toString())
				.param("endTime", vo.getEndTime().toString())
				.param("screenDt", vo.getScreenDt().toString())
				.param("adultCost", vo.getAdultCost()+"")
				.param("studentCost", vo.getStudentCost()+"")
				.param("epside", vo.getEpisode()+"");
		
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
	
	public LGS_ScreenInfoVO get_selectOne(LGS_ScreenInfoVO vo) throws Exception {
		MockHttpServletRequestBuilder createMessage = 
				MockMvcRequestBuilders.post("/screenInfo/do_selectOne.do")
					.param("screenId", vo.getScreenId().toString());
		
		ResultActions resultAction = mockMvc.perform(createMessage)
										.andExpect(status().isOk());
		
		MvcResult result = mockMvc.perform(createMessage)
                .andExpect(status().isOk())
                .andReturn();

		ModelAndView modelAndView = result.getModelAndView();
		
		LGS_ScreenInfoVO outVO = (LGS_ScreenInfoVO) modelAndView.getModel().get("screenVO");
		LOG.debug("=====================================");
		
		LOG.debug("=outVO="+outVO);
		LOG.debug("=====================================");		
		
		return outVO;
	}
	
	public void do_delete(LGS_ScreenInfoVO vo) throws Exception {
		//url, param
		MockHttpServletRequestBuilder createMessage = 
				MockMvcRequestBuilders.post("/screenInfo/do_delete.do")
										.param("screenId", vo.getScreenId().toString());
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
		LOG.debug("ScreenInfoDaoImpl = " + screenInfoDaoImpl);
		LOG.debug("====================");
		
		assertThat(context, is(notNullValue()) );
		assertThat(screenInfoDaoImpl, is(notNullValue()) );
	}
	
	@After
	public void tearDown() {
		
	}
	
	public void start() {

		List<LGS_BranchInfoVO> Blist = Arrays.asList(
				new LGS_BranchInfoVO("testId001", "name1")
				,new LGS_BranchInfoVO("testId002", "name2")
				,new LGS_BranchInfoVO("testId003", "name3")
				,new LGS_BranchInfoVO("testId004", "name4")
				,new LGS_BranchInfoVO("testId005", "name5")
				);

		SearchVO search = new SearchVO();
		search.setPageNum(1);
		search.setPageSize(1000);
		
		List<LGS_RoomVO> tmp2 = (List<LGS_RoomVO>) roomDaoImpl.do_retrieve(search);
		if(tmp2 != null) {
			for(LGS_RoomVO vo : tmp2) {
				roomDaoImpl.do_delete(vo);
			}
		}
		
		List<LGS_BranchInfoVO> tmp = (List<LGS_BranchInfoVO>) branchInfoDaoImpl.do_retrieve(search);
		if(tmp != null) {
			for(LGS_BranchInfoVO vo : tmp) {
				branchInfoDaoImpl.do_delete(vo);
			}
		}
		for(LGS_BranchInfoVO vo : Blist) {
			branchInfoDaoImpl.do_save(vo);
		}
		tmp = (List<LGS_BranchInfoVO>) branchInfoDaoImpl.do_retrieve(search);
		List<LGS_RoomVO> Rlist = Arrays.asList(
				new LGS_RoomVO(tmp.get(0).getBranchId(), "testRoom001", "room1", 100, 100)
				,new LGS_RoomVO(tmp.get(1).getBranchId(), "testRoom002", "room2", 200, 100)
				,new LGS_RoomVO(tmp.get(2).getBranchId(), "testRoom003", "room3", 300, 100)
				,new LGS_RoomVO(tmp.get(3).getBranchId(), "testRoom004", "room4", 400, 100)
				,new LGS_RoomVO(tmp.get(4).getBranchId(), "testRoom005", "room5", 500, 100)
				);
		
		
		for(LGS_RoomVO vo : Rlist) {
			roomDaoImpl.do_save(vo);
		}
		
	}
	
public void intro() {
		
		SearchVO search = new SearchVO();
		search.setPageNum(1);
		search.setPageSize(1000);
		List<LGS_ScreenInfoVO> tmp = (List<LGS_ScreenInfoVO>) screenInfoDaoImpl.do_retrieve(search);
		
		if(tmp != null) {
			for(LGS_ScreenInfoVO vo : tmp) {
				screenInfoDaoImpl.do_delete(vo);
			}
		}
		
		List<LGS_RoomVO> tmp2 = (List<LGS_RoomVO>) roomDaoImpl.do_retrieve(search);
		list = Arrays.asList(
				new LGS_ScreenInfoVO("", tmp2.get(0).getRoomId(), "b1", "m1", "19/09/30", "19/09/30", "19/09/30", 100,10,1)
				,new LGS_ScreenInfoVO("", tmp2.get(1).getRoomId(), "b2", "m2", "19/09/30", "19/09/30", "19/09/30", 200,20,2)
				,new LGS_ScreenInfoVO("", tmp2.get(2).getRoomId(), "b3", "m3", "19/09/30", "19/09/30", "19/09/30", 300,30,3)
				,new LGS_ScreenInfoVO("", tmp2.get(3).getRoomId(), "b4", "m4", "19/09/30", "19/09/30", "19/09/30", 400,40,4)
				,new LGS_ScreenInfoVO("", tmp2.get(4).getRoomId(), "b5", "m5", "19/09/30", "19/09/30", "19/09/30", 500,50,5)
				);
	}
}
