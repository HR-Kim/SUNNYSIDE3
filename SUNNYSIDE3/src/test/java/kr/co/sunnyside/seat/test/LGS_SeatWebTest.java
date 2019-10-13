package kr.co.sunnyside.seat.test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
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

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import kr.co.sunnyside.branchinfo.service.LGS_BranchInfoVO;
import kr.co.sunnyside.branchinfo.service.impl.LGS_BranchInfoDaoImpl;
import kr.co.sunnyside.cmn.SearchVO;

import kr.co.sunnyside.seat.service.LGS_SeatVO;
import kr.co.sunnyside.seat.service.impl.LGS_SeatDaoImpl;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/**/*.xml"})
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class LGS_SeatWebTest {
	private final Logger LOG = LoggerFactory.getLogger(this.getClass());
	
	private MockMvc mockMvc;
	
	@Autowired
	private WebApplicationContext context;
	
	@Autowired
	LGS_SeatDaoImpl seatDaoImpl;
	
	private List<LGS_SeatVO> list;
	
	@Before
	public void setUp() {
		mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
		list = Arrays.asList(
				new LGS_SeatVO("b01", "r0", "A1", "A", 1, "0")
				,new LGS_SeatVO("b02", "r0", "A2", "A", 2,"0")
				,new LGS_SeatVO("b03", "r0", "A3", "A", 3, "0")
				,new LGS_SeatVO("b04", "r0", "A4", "A", 4, "0")
				,new LGS_SeatVO("b05", "r0", "A5", "A", 5, "0")
				,new LGS_SeatVO("b06", "r0", "B1", "B", 1, "0")
				,new LGS_SeatVO("b07", "r0", "B2", "B", 2, "0")
				,new LGS_SeatVO("b08", "r0", "B3", "B", 3, "0")
				,new LGS_SeatVO("b09", "r0", "B4", "B", 4, "0")
				,new LGS_SeatVO("b10", "r0", "B5", "B", 5, "0")
				);
	}

	@Test
	public void do_retrieve() throws Exception{
		LOG.debug("======================================");
		LOG.debug("=01. 기존 데이터 삭제=");
		LOG.debug("======================================");
		SearchVO search = new SearchVO();
		search.setPageNum(1);
		search.setPageSize(1000);
		String jsonString0 =  this.get_retrieve(search);
		JsonParser parser0 = new JsonParser();
		JsonElement a0 = (JsonElement)parser0.parse(jsonString0);
		JsonArray jArr0 =a0.getAsJsonArray();
		if(jArr0.size() > 0) {
		LGS_SeatVO svo = new LGS_SeatVO();
		svo.setRoomId(jArr0.get(0).getAsJsonObject().get("roomId").getAsString());	
		do_delete(svo);
		}
		
		LOG.debug("======================================");
		LOG.debug("=02. 단건등록=");
		LOG.debug("======================================");
		for(LGS_SeatVO vo : list) {
			do_save(vo);
		}
		LOG.debug("======================================");
		LOG.debug("=03. 목록조회 ");
		LOG.debug("======================================");
		
		String jsonString =  this.get_retrieve(search);
		JsonParser parser = new JsonParser();
		JsonElement a = (JsonElement)parser.parse(jsonString);
		JsonArray jArr =a.getAsJsonArray();

		
		for(int i=0 ; i< jArr.size(); i++) {
			LOG.debug(jArr.get(i) + "");
		}
		
		assertThat(10, is(jArr.size()));
		
	}

	@Test
	public void update() throws Exception {
		LOG.debug("======================================");
		LOG.debug("=01. 기존 데이터 삭제=");
		LOG.debug("======================================");
		SearchVO search = new SearchVO();
		search.setPageNum(1);
		search.setPageSize(1000);
		String jsonString0 =  this.get_retrieve(search);
		JsonParser parser0 = new JsonParser();
		JsonElement a0 = (JsonElement)parser0.parse(jsonString0);
		JsonArray jArr0 =a0.getAsJsonArray();
		if(jArr0.size() > 0) {
		LGS_SeatVO svo = new LGS_SeatVO();
		svo.setRoomId(jArr0.get(0).getAsJsonObject().get("roomId").getAsString());	
		do_delete(svo);
		}
		
		LOG.debug("======================================");
		LOG.debug("=02. 단건등록=");
		LOG.debug("======================================");

		do_save(list.get(0));
		
		LOG.debug("======================================");
		LOG.debug("=03. 단건조회=");
		LOG.debug("======================================");
		
		LGS_SeatVO vo = (LGS_SeatVO) seatDaoImpl.do_selectOne(list.get(0));

		LOG.debug("======================================");
		LOG.debug("=04. Data수정/Update=");
		LOG.debug("======================================");
		
		//Data수정
		vo.setUseYN("0");
		
		//Update
		this.do_update(vo);
		
		LOG.debug("======================================");
		LOG.debug("=05. Data 조회/비교=");
		LOG.debug("======================================");		
		LGS_SeatVO vsData = this.get_selectOne(vo);
		
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
		String jsonString0 =  this.get_retrieve(search);
		JsonParser parser0 = new JsonParser();
		JsonElement a0 = (JsonElement)parser0.parse(jsonString0);
		JsonArray jArr0 =a0.getAsJsonArray();
		if(jArr0.size() > 0) {
		LGS_SeatVO svo = new LGS_SeatVO();
		svo.setRoomId(jArr0.get(0).getAsJsonObject().get("roomId").getAsString());	
		do_delete(svo);
		}
		
		LOG.debug("======================================");
		LOG.debug("=02. 단건등록=");
		LOG.debug("======================================");
		for(LGS_SeatVO vo : list) {
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
			LGS_SeatVO vo = new LGS_SeatVO();
			vo.setSeatNm(jArr0.get(i).getAsJsonObject().get("seatNm").getAsString());
			LGS_SeatVO outvo = (LGS_SeatVO) seatDaoImpl.do_selectOne(vo);
		
		}
	}
	
	private void checkData(LGS_SeatVO org, LGS_SeatVO vs) {
		assertThat(org.getBranchId(), is(vs.getBranchId()));
		assertThat(org.getRoomId(), is(vs.getRoomId()));
		assertThat(org.getSeatNm(), is(vs.getSeatNm()));
		
		
		
	}
	
	public String get_retrieve(SearchVO vo) throws Exception {
		MockHttpServletRequestBuilder createMessage = 
				MockMvcRequestBuilders.post("/seat/do_retrieve.do")
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
	
	public void do_update(LGS_SeatVO vo) throws Exception {
		MockHttpServletRequestBuilder createMessage = 
				MockMvcRequestBuilders.post("/seat/do_update.do")
				.param("roomId", vo.getRoomId().toString())
				.param("seatNm", vo.getSeatNm())
				.param("useYN", vo.getUseYN());
		
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
	
	public void do_save(LGS_SeatVO vo) throws Exception {
		MockHttpServletRequestBuilder createMessage = 
				MockMvcRequestBuilders.post("/seat/do_save.do")
				.param("branchId", vo.getBranchId().toString())
				.param("roomId", vo.getRoomId().toString())
				.param("seatY", vo.getSeatY())
				.param("seatX", vo.getSeatX()+"")
				.param("useYN", vo.getUseYN());
		
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
	
	public LGS_SeatVO get_selectOne(LGS_SeatVO vo) throws Exception {
		MockHttpServletRequestBuilder createMessage = 
				MockMvcRequestBuilders.post("/seat/do_selectOne.do")
										.param("seatNm", vo.getSeatNm().toString());
		
		ResultActions resultAction = mockMvc.perform(createMessage)
										.andExpect(status().isOk());
		
		MvcResult result = mockMvc.perform(createMessage)
                .andExpect(status().isOk())
                .andReturn();

		ModelAndView modelAndView = result.getModelAndView();
		
		LGS_SeatVO outVO = (LGS_SeatVO) modelAndView.getModel().get("seatVO");
		LOG.debug("=====================================");
		
		LOG.debug("=outVO="+outVO);
		LOG.debug("=====================================");		
		
		return outVO;
	}
	
	public void do_delete(LGS_SeatVO vo) throws Exception {
		//url, param
		MockHttpServletRequestBuilder createMessage = 
				MockMvcRequestBuilders.post("/seat/do_delete.do")
										.param("roomId", vo.getRoomId().toString());
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
		LOG.debug("seatDaoImpl = " + seatDaoImpl);
		LOG.debug("====================");
		
		assertThat(context, is(notNullValue()) );
		assertThat(seatDaoImpl, is(notNullValue()) );
	}
	
	@After
	public void tearDown() {
		
	}

	
}