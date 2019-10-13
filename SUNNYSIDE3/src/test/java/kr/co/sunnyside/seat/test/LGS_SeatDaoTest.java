package kr.co.sunnyside.seat.test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.isNotNull;
import static org.mockito.Matchers.notNull;

import java.util.Arrays;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.mockito.internal.matchers.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.context.WebApplicationContext;

import kr.co.sunnyside.branchinfo.service.LGS_BranchInfoVO;
import kr.co.sunnyside.branchinfo.service.impl.LGS_BranchInfoDaoImpl;
import kr.co.sunnyside.cmn.SearchVO;
import kr.co.sunnyside.room.service.LGS_RoomVO;
import kr.co.sunnyside.room.service.impl.LGS_RoomDaoImpl;
import kr.co.sunnyside.seat.service.LGS_SeatVO;
import kr.co.sunnyside.seat.service.impl.LGS_SeatDaoImpl;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/**/*.xml"})
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class LGS_SeatDaoTest {
	private final Logger LOG = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private WebApplicationContext context;
	
	@Autowired
	LGS_SeatDaoImpl seatDaoImpl;
	
	private List<LGS_SeatVO> list;
	

	@Test
	public void do_retrieve() {
		LOG.debug("===========================================================");
		LOG.debug("do_retrieve");
		LOG.debug("===========================================================");

		deleteAll();
		
		
		for(LGS_SeatVO vo : list) {
			LOG.debug("vo = " + vo);
			int flag = seatDaoImpl.do_save(vo);
			assertThat(1, is(flag));
		}
		
		SearchVO search = new SearchVO();
		search.setPageNum(1);
		search.setPageSize(10);
		//search.setSearchDiv("20");
		//search.setSearchWord("name2");
		
		List<LGS_SeatVO> getList = (List<LGS_SeatVO>) seatDaoImpl.do_retrieve(search);
		
		LOG.debug("===========================================================");
		for(LGS_SeatVO vo : getList) {
			LOG.debug("vo : " + vo);
		}
		LOG.debug("===========================================================");
		
	}

	@Test
	public void do_selectOne() {
		LOG.debug("===========================================================");
		LOG.debug("do_selectOne");
		LOG.debug("===========================================================");

		deleteAll();
		
		
		for(LGS_SeatVO vo : list) {
			LOG.debug("vo = " + vo);
			int flag = seatDaoImpl.do_save(vo);
			assertThat(1, is(flag));
		}
		
		LGS_SeatVO out = (LGS_SeatVO) seatDaoImpl.do_selectOne(list.get(2));
		LOG.debug("===========================================================");
		LOG.debug("out : "  + out);
		LOG.debug("===========================================================");
	}

	@Test
	public void do_update() {
		LOG.debug("===========================================================");
		LOG.debug("do_update");
		LOG.debug("===========================================================");
		
		deleteAll();
		
		
		for(LGS_SeatVO vo : list) {
			LOG.debug("vo = " + vo);
			int flag = seatDaoImpl.do_save(vo);
			assertThat(1, is(flag));
		}
		
		LGS_SeatVO updateVO = list.get(0);
		updateVO.setSeatNm("B24");
		updateVO.setSeatY("B");
		updateVO.setSeatX(24);
		
		int flag2 = seatDaoImpl.do_update(updateVO);
		assertThat(1, is(flag2));
		
		seatDaoImpl.do_delete(updateVO);
		
	}
	
	@Test
	public void do_delete() {
		LOG.debug("===========================================================");
		LOG.debug("do_delete");
		LOG.debug("===========================================================");
		
		deleteAll();
		
		
		for(LGS_SeatVO vo : list) {
			LOG.debug("vo = " + vo);
			int flag = seatDaoImpl.do_save(vo);
			assertThat(1, is(flag));
		}
		
		for(LGS_SeatVO vo : list) {
			LOG.debug("vo = " + vo);
			int flag = seatDaoImpl.do_delete(vo);
			assertThat(1, is(flag));
		}
		
	}

	@Test
	public void do_save() {
		LOG.debug("===========================================================");
		LOG.debug("do_save");
		LOG.debug("===========================================================");


		deleteAll();
		
		for(LGS_SeatVO vo : list) {
			LOG.debug("vo = " + vo);
			int flag = seatDaoImpl.do_save(vo);
			
			assertThat(1, is(flag));
		}
		
		
	}	
	
	@Before
	public void getBean() {
		list = Arrays.asList(
				new LGS_SeatVO("b01", "r01", "A1", "A", 1, "0")
				,new LGS_SeatVO("b02", "r02", "A2", "A", 2,"0")
				,new LGS_SeatVO("b03", "r03", "A3", "A", 3, "0")
				,new LGS_SeatVO("b04", "r04", "A4", "A", 4, "0")
				,new LGS_SeatVO("b05", "r05", "A5", "A", 5, "0")
				,new LGS_SeatVO("b06", "r06", "B1", "B", 1, "0")
				,new LGS_SeatVO("b07", "r07", "B2", "B", 2, "0")
				,new LGS_SeatVO("b08", "r08", "B3", "B", 3, "0")
				,new LGS_SeatVO("b09", "r09", "B4", "B", 4, "0")
				,new LGS_SeatVO("b10", "r10", "B5", "B", 5, "0")
	);
		
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
	
	public void deleteAll() {
		SearchVO search = new SearchVO();
		search.setPageNum(1);
		search.setPageSize(10000);
		List<LGS_SeatVO> list = (List<LGS_SeatVO>) seatDaoImpl.do_retrieve(search);
		for(LGS_SeatVO vo : list) {
			int flag = seatDaoImpl.do_delete(vo);
			assertThat(flag, not(0) );
		}
	}
}
