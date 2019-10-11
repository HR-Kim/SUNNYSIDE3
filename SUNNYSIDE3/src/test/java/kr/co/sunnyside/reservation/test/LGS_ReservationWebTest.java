package kr.co.sunnyside.reservation.test;

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

import kr.co.sunnyside.branchinfo.service.impl.LGS_BranchInfoDaoImpl;
import kr.co.sunnyside.cmn.SearchVO;
import kr.co.sunnyside.reservation.service.LGS_TicketVO;
import kr.co.sunnyside.reservation.service.impl.LGS_ReservationDaoImpl;
import kr.co.sunnyside.room.service.LGS_RoomVO;
import kr.co.sunnyside.room.service.impl.LGS_RoomDaoImpl;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/**/*.xml"})
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class LGS_ReservationWebTest {
	private final Logger LOG = LoggerFactory.getLogger(this.getClass());
	
	private MockMvc mockMvc;
	
	@Autowired
	private WebApplicationContext context;
	
	@Autowired
	LGS_ReservationDaoImpl reservationDaoImpl;
	
	private List<LGS_TicketVO> list;
	
	@Before
	public void setUp() {
		mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
		list = Arrays.asList(
				new LGS_TicketVO("co1", "b1", "r1", "s1", "u1", "m1", "A1", 0, 1, 10, "2019-09-30 00:00:00", "2019-09-30 00:00:00")
				,new LGS_TicketVO("co2", "b2", "r2", "s2", "u2", "m2", "A2", 1, 0, 20, "2019-09-30 00:00:00", "2019-09-30 00:00:00")
				,new LGS_TicketVO("co3", "b3", "r3", "s3", "u3", "m3", "A3", 0, 1, 30, "2019-09-30 00:00:00", "2019-09-30 00:00:00")
				,new LGS_TicketVO("co4", "b4", "r4", "s4", "u4", "m4", "A4", 1, 0, 40, "2019-09-30 00:00:00", "2019-09-30 00:00:00")
				,new LGS_TicketVO("co5", "b5", "r5", "s5", "u5", "m5", "A5", 0, 1, 50, "2019-09-30 00:00:00", "2019-09-30 00:00:00")
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
		List<LGS_TicketVO> tmp = (List<LGS_TicketVO>) reservationDaoImpl.do_retrieve(search);
			
		for(LGS_TicketVO vo : tmp) {
			do_delete(vo);
		}
		
		LOG.debug("======================================");
		LOG.debug("=02. 단건등록=");
		LOG.debug("======================================");
		for(LGS_TicketVO vo : list) {
			do_save(vo);
		}                          
		LOG.debug("======================================");
		LOG.debug("=03. 목록조회 ");
		LOG.debug("======================================");
		

		List<LGS_TicketVO> list = (List<LGS_TicketVO>) this.get_retrieve(search);

		for(LGS_TicketVO vo : list) {
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
		List<LGS_TicketVO> tmp = (List<LGS_TicketVO>) reservationDaoImpl.do_retrieve(search);
			
		for(LGS_TicketVO vo : tmp) {
			do_delete(vo);
		}
		
		LOG.debug("======================================");
		LOG.debug("=02. 단건등록=");
		LOG.debug("======================================");
	
		do_save(list.get(0));
		
		
		LOG.debug("======================================");
		LOG.debug("=03. 단건조회=");
		LOG.debug("======================================");
		
		List<LGS_TicketVO> tmp2 = (List<LGS_TicketVO>) reservationDaoImpl.do_retrieve(search);

		LOG.debug("======================================");
		LOG.debug("=04. Data수정/Update=");
		LOG.debug("======================================");
		
		//Data수정
		LGS_TicketVO vo = tmp2.get(0);
		vo.setAdultCnt(1);
		
		//Update
		this.do_update(vo);
		
		LOG.debug("======================================");
		LOG.debug("=05. Data 조회/비교=");
		LOG.debug("======================================");		
		LGS_TicketVO vsData = this.get_selectOne(vo);
		
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
		List<LGS_TicketVO> tmp = (List<LGS_TicketVO>) reservationDaoImpl.do_retrieve(search);
			
		for(LGS_TicketVO vo : tmp) {
			do_delete(vo);
		}
		
		LOG.debug("======================================");
		LOG.debug("=02. 단건등록=");
		LOG.debug("======================================");
		for(LGS_TicketVO vo : list) {
			do_save(vo);
		}
		
		
		LOG.debug("======================================");
		LOG.debug("=03. 조회=");
		LOG.debug("======================================");
		List<LGS_TicketVO> tmp2 = (List<LGS_TicketVO>) reservationDaoImpl.do_retrieve(search);
		
		
		
		for(LGS_TicketVO vo : tmp2) {
			LGS_TicketVO outvo = (LGS_TicketVO) reservationDaoImpl.do_selectOne(vo);
			
			LOG.debug("outvo  -> " + outvo);
			LOG.debug("vo  -> " + vo);
			
			checkData(outvo,vo);
		}
	}
	
	private void checkData(LGS_TicketVO org, LGS_TicketVO vs) {
		assertThat(org.getBranchId(), is(vs.getBranchId()));
		assertThat(org.getMovieId(), is(vs.getMovieId()));
		assertThat(org.getTicketCode(), is(vs.getTicketCode()));
		
	}
	
	public List<LGS_TicketVO> get_retrieve(SearchVO vo) throws Exception {
		MockHttpServletRequestBuilder createMessage = 
				MockMvcRequestBuilders.post("/reservation/do_retrieve.do")
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
		
		List<LGS_TicketVO> list = (List<LGS_TicketVO>) modelAndView.getModel().get("ticketList");
		LOG.debug("=====================================");
		
		LOG.debug("=list=" + list);
		LOG.debug("=====================================");		
		
		return list;
	}
	
	public void do_update(LGS_TicketVO vo) throws Exception {
		MockHttpServletRequestBuilder createMessage = 
				MockMvcRequestBuilders.post("/reservation/do_update.do")
				.param("ticketCode", vo.getTicketCode().toString())
				.param("branchId", vo.getBranchId().toString())
				.param("roomId", vo.getRoomId().toString())
				.param("screenId", vo.getScreenId().toString())
				.param("userId", vo.getUserId().toString())
				.param("movieId", vo.getMovieId().toString())
				.param("seatNm", vo.getSeatNm().toString())
				.param("adultCnt", vo.getAdultCnt()+"")
				.param("payState", vo.getPayState()+"")
				.param("cost", vo.getCost()+"")
				.param("payDt", vo.getPayDt())
				.param("ticketDt", vo.getTicketDt());
		
		
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
	
	public void do_save(LGS_TicketVO vo) throws Exception {
		MockHttpServletRequestBuilder createMessage = 
				MockMvcRequestBuilders.post("/reservation/do_save.do")
				.param("branchId", vo.getBranchId().toString())
				.param("roomId", vo.getRoomId().toString())
				.param("screenId", vo.getScreenId().toString())
				.param("userId", vo.getUserId().toString())
				.param("movieId", vo.getMovieId().toString())
				.param("seatNm", vo.getSeatNm().toString())
				.param("adultCnt", vo.getAdultCnt()+"")
				.param("payState", vo.getPayState()+"")
				.param("cost", vo.getCost()+"");
		
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
	
	public LGS_TicketVO get_selectOne(LGS_TicketVO vo) throws Exception {
		MockHttpServletRequestBuilder createMessage = 
				MockMvcRequestBuilders.post("/reservation/do_selectOne.do")
										.param("userId", vo.getUserId().toString());
		
		ResultActions resultAction = mockMvc.perform(createMessage)
										.andExpect(status().isOk());
		
		MvcResult result = mockMvc.perform(createMessage)
                .andExpect(status().isOk())
                .andReturn();

		ModelAndView modelAndView = result.getModelAndView();
		
		LGS_TicketVO outVO = (LGS_TicketVO) modelAndView.getModel().get("TictetVO");
		LOG.debug("=====================================");
		
		LOG.debug("=outVO="+outVO);
		LOG.debug("=====================================");		
		
		return outVO;
	}
	
	public void do_delete(LGS_TicketVO vo) throws Exception {
		//url, param
		MockHttpServletRequestBuilder createMessage = 
				MockMvcRequestBuilders.post("/reservation/do_delete.do")
										.param("ticketCode", vo.getTicketCode().toString());
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
		LOG.debug("reservationDaoImpl = " + reservationDaoImpl);
		LOG.debug("====================");
		
		assertThat(context, is(notNullValue()) );
		assertThat(reservationDaoImpl, is(notNullValue()) );
	}
	
	@After
	public void tearDown() {
		
	}
	
}
