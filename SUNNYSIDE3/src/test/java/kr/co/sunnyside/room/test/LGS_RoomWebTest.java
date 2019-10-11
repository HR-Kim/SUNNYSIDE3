package kr.co.sunnyside.room.test;

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

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import kr.co.sunnyside.branchinfo.service.LGS_BranchInfoVO;
import kr.co.sunnyside.branchinfo.service.impl.LGS_BranchInfoDaoImpl;
import kr.co.sunnyside.cmn.SearchVO;
import kr.co.sunnyside.room.service.LGS_RoomVO;
import kr.co.sunnyside.room.service.impl.LGS_RoomDaoImpl;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/**/*.xml"})
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class LGS_RoomWebTest {
	private final Logger LOG = LoggerFactory.getLogger(this.getClass());
	
	private MockMvc mockMvc;
	
	@Autowired
	private WebApplicationContext context;
	
	@Autowired
	LGS_RoomDaoImpl roomDaoImpl;
	
	@Autowired
	LGS_BranchInfoDaoImpl branchInfoDaoImpl;
	
	private List<LGS_BranchInfoVO> Blist;
	private List<LGS_RoomVO> Rlist;
	
	@Before
	public void setUp() {
		mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
		Blist = Arrays.asList(
				new LGS_BranchInfoVO("", "branch1")
				,new LGS_BranchInfoVO("", "branch2")
				,new LGS_BranchInfoVO("", "branch3")
				,new LGS_BranchInfoVO("", "branch4")
				,new LGS_BranchInfoVO("", "branch5")
				);
	}
	
	@Test
	public void do_retrieve() throws Exception{
		LOG.debug("======================================");
		LOG.debug("=01. 기존 데이터 삭제=");
		LOG.debug("======================================");
		this.intro();
		
		LOG.debug("======================================");
		LOG.debug("=02. 단건등록=");
		LOG.debug("======================================");
		for(LGS_RoomVO vo : Rlist) {
			do_save(vo);
		}
		LOG.debug("======================================");
		LOG.debug("=03. 목록조회 ");
		LOG.debug("======================================");
		
		SearchVO search = new SearchVO();
		search.setPageNum(1);
		search.setPageSize(1000);
		String jsonString =  this.get_retrieve(search);
		
		JsonParser parser = new JsonParser();
		JsonElement a = (JsonElement)parser.parse(jsonString);
		LOG.debug("->>" +a.toString());
		JsonArray jArr =a.getAsJsonArray();

		
		for(int i=0 ; i< jArr.size(); i++) {
			LOG.debug(jArr.get(i) + "");
		}
		
		assertThat(5, is(jArr.size()));
		
	}
	
	@Test
	public void update() throws Exception {
		LOG.debug("======================================");
		LOG.debug("=01. 기존 데이터 삭제=");
		LOG.debug("======================================");
		this.intro();
		
		LOG.debug("======================================");
		LOG.debug("=02. 단건등록=");
		LOG.debug("======================================");

		do_save(Rlist.get(0));
		
		LOG.debug("======================================");
		LOG.debug("=03. 단건조회=");
		LOG.debug("======================================");
		
		LGS_RoomVO vo = (LGS_RoomVO) roomDaoImpl.do_selectOne(Rlist.get(0));

		LOG.debug("======================================");
		LOG.debug("=04. Data수정/Update=");
		LOG.debug("======================================");
		
		//Data수정
		vo.setTotalSeat(90+vo.getTotalSeat());
		
		//Update
		this.do_update(vo);
		
		LOG.debug("======================================");
		LOG.debug("=05. Data 조회/비교=");
		LOG.debug("======================================");		
		LGS_RoomVO vsData = this.get_selectOne(vo);
		
		checkData(vo, vsData);
		
	}
	
	@Test
	public void addAndGet() throws Exception {
		LOG.debug("======================================");
		LOG.debug("=01. 기존 데이터 삭제=");
		LOG.debug("======================================");
		this.intro();
		
		LOG.debug("======================================");
		LOG.debug("=02. 단건등록=");
		LOG.debug("======================================");
		for(LGS_RoomVO vo : Rlist) {
			do_save(vo);
		}
		
		
		LOG.debug("======================================");
		LOG.debug("=03. 조회=");
		LOG.debug("======================================");
		SearchVO search = new SearchVO();
		search.setPageNum(1);
		search.setPageSize(1000);
		List<LGS_RoomVO> tmp = (List<LGS_RoomVO>) roomDaoImpl.do_retrieve(search);
		
		
		
		for(LGS_RoomVO vo : tmp) {
			LGS_RoomVO outvo = (LGS_RoomVO) roomDaoImpl.do_selectOne(vo);
			checkData(outvo,vo);
		}
	}
	
	private void checkData(LGS_RoomVO org, LGS_RoomVO vs) {
		assertThat(org.getBranchId(), is(vs.getBranchId()));
		assertThat(org.getRoomId(), is(vs.getRoomId()));
		assertThat(org.getRestSeat(), is(vs.getRestSeat()));
		
		
	}
	
	public String get_retrieve(SearchVO vo) throws Exception {
		MockHttpServletRequestBuilder createMessage = 
				MockMvcRequestBuilders.post("/room/do_retrieve.do")
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
	
	public void do_update(LGS_RoomVO vo) throws Exception {
		MockHttpServletRequestBuilder createMessage = 
				MockMvcRequestBuilders.post("/room/do_update.do")
				.param("roomId", vo.getRoomId().toString())
				.param("totalSeat", vo.getTotalSeat()+"")
				.param("restSeat", vo.getRestSeat()+"");
		
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
	
	public void do_save(LGS_RoomVO vo) throws Exception {
		MockHttpServletRequestBuilder createMessage = 
				MockMvcRequestBuilders.post("/room/do_save.do")
				.param("branchId", vo.getBranchId().toString())
				.param("roomNm", vo.getRoomNm().toString())
				.param("totalSeat", vo.getTotalSeat()+"")
				.param("restSeat", vo.getRestSeat()+"");
		
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
	
	public LGS_RoomVO get_selectOne(LGS_RoomVO vo) throws Exception {
		MockHttpServletRequestBuilder createMessage = 
				MockMvcRequestBuilders.post("/room/do_selectOne.do")
										.param("roomNm", vo.getRoomNm().toString());
		
		ResultActions resultAction = mockMvc.perform(createMessage)
										.andExpect(status().isOk());
		
		MvcResult result = mockMvc.perform(createMessage)
                .andExpect(status().isOk())
                .andReturn();

		ModelAndView modelAndView = result.getModelAndView();
		
		LGS_RoomVO outVO = (LGS_RoomVO) modelAndView.getModel().get("roomVO");
		LOG.debug("=====================================");
		
		LOG.debug("=outVO="+outVO);
		LOG.debug("=====================================");		
		
		return outVO;
	}
	
	public void do_delete(LGS_RoomVO vo) throws Exception {
		//url, param
		MockHttpServletRequestBuilder createMessage = 
				MockMvcRequestBuilders.post("/room/do_delete.do")
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
		LOG.debug("roomDaoImpl = " + roomDaoImpl);
		LOG.debug("====================");
		
		assertThat(context, is(notNullValue()) );
		assertThat(roomDaoImpl, is(notNullValue()) );
	}
	
	@After
	public void tearDown() {
		
	}
	
	public void intro() {
		SearchVO search = new SearchVO();
		search.setPageNum(1);
		search.setPageSize(1000);
		List<LGS_RoomVO> tmp = (List<LGS_RoomVO>) roomDaoImpl.do_retrieve(search);
		if(tmp != null) {
			for(LGS_RoomVO vo : tmp) {
				roomDaoImpl.do_delete(vo);
			}
		}
		
		List<LGS_BranchInfoVO> tmp2 = (List<LGS_BranchInfoVO>) branchInfoDaoImpl.do_retrieve(search);
		if(tmp2 != null) {
			for(LGS_BranchInfoVO vo : tmp2) {
				branchInfoDaoImpl.do_delete(vo);
			}
		}
		
		for(LGS_BranchInfoVO vo : Blist) {
			branchInfoDaoImpl.do_save(vo);
		}
		tmp2 = (List<LGS_BranchInfoVO>) branchInfoDaoImpl.do_retrieve(search);
		Rlist = Arrays.asList(
				new LGS_RoomVO(tmp2.get(0).getBranchId(), "testRoom001", "room1", 100, 100)
				,new LGS_RoomVO(tmp2.get(1).getBranchId(), "testRoom002", "room2", 200, 100)
				,new LGS_RoomVO(tmp2.get(2).getBranchId(), "testRoom003", "room3", 300, 100)
				,new LGS_RoomVO(tmp2.get(3).getBranchId(), "testRoom004", "room4", 400, 100)
				,new LGS_RoomVO(tmp2.get(4).getBranchId(), "testRoom005", "room5", 500, 100)
				);
	}
}
