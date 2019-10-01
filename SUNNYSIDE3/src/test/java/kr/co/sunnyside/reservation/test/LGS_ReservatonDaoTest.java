package kr.co.sunnyside.reservation.test;

import static org.hamcrest.CoreMatchers.is;
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

import kr.co.sunnyside.branchinfo.service.LGS_BranchInfoSvc;
import kr.co.sunnyside.branchinfo.service.LGS_BranchInfoVO;
import kr.co.sunnyside.branchinfo.service.impl.LGS_BranchInfoDaoImpl;
import kr.co.sunnyside.cmn.SearchVO;
import kr.co.sunnyside.reservation.service.LGS_TicketVO;
import kr.co.sunnyside.reservation.service.impl.LGS_ReservationDaoImpl;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/**/*.xml"})
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class LGS_ReservatonDaoTest {
	private final Logger LOG = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private WebApplicationContext context;
	
	@Autowired
	LGS_ReservationDaoImpl reservationDaoImpl;
	
	private List<LGS_TicketVO> list;
	

	@Test
	public void get_retrieve() {
		LOG.debug("===========================================================");
		LOG.debug("get_retrieve");
		LOG.debug("===========================================================");
		
		SearchVO search = new SearchVO();
		search.setPageNum(1);
		search.setPageSize(1000);
		List<LGS_TicketVO> tmp = (List<LGS_TicketVO>) reservationDaoImpl.get_retrieve(search);
		for(LGS_TicketVO vo : tmp) {
			int flag = reservationDaoImpl.do_delete(vo);
		}
		
		for(LGS_TicketVO vo : list) {
			LOG.debug("vo = " + vo);
			int flag = reservationDaoImpl.do_save(vo);
		}
		
		search.setPageNum(1);
		search.setPageSize(1000);
		//search.setSearchDiv("10");
		//search.setSearchWord("");
		
		List<LGS_TicketVO> getList = (List<LGS_TicketVO>) reservationDaoImpl.get_retrieve(search);
		
		LOG.debug("===========================================================");
		for(LGS_TicketVO vo : getList) {
			LOG.debug("vo : " + vo);
		}
		LOG.debug("===========================================================");
		
	}
	
	@Test
	public void get_selectOne() {
		LOG.debug("===========================================================");
		LOG.debug("get_selectOne");
		LOG.debug("===========================================================");
		
		SearchVO search = new SearchVO();
		search.setPageNum(1);
		search.setPageSize(1000);
		List<LGS_TicketVO> tmp = (List<LGS_TicketVO>) reservationDaoImpl.get_retrieve(search);
		for(LGS_TicketVO vo : tmp) {
			int flag = reservationDaoImpl.do_delete(vo);
		}
		
		for(LGS_TicketVO vo : list) {
			LOG.debug("vo = " + vo);
			int flag = reservationDaoImpl.do_save(vo);
		}
		
		LGS_TicketVO out = (LGS_TicketVO) reservationDaoImpl.get_selectOne(list.get(0));
		assertThat(out, is(notNullValue()));
	}

	@Test
	public void do_update() {
		LOG.debug("===========================================================");
		LOG.debug("do_update");
		LOG.debug("===========================================================");

		SearchVO search = new SearchVO();
		search.setPageNum(1);
		search.setPageSize(1000);
		List<LGS_TicketVO> tmp = (List<LGS_TicketVO>) reservationDaoImpl.get_retrieve(search);
		for(LGS_TicketVO vo : tmp) {
			int flag = reservationDaoImpl.do_delete(vo);
		}
		
		for(LGS_TicketVO vo : list) {
			LOG.debug("vo = " + vo);
			int flag = reservationDaoImpl.do_save(vo);
		}
		
		tmp = (List<LGS_TicketVO>) reservationDaoImpl.get_retrieve(search);
		LGS_TicketVO updateVO = tmp.get(0);
		updateVO.setPayDt("2019-09-30 11:11:11");
		int flag2 = reservationDaoImpl.do_update(updateVO);
		assertThat(1, is(flag2));
	}
	

	@Test
	public void do_delete() {
		LOG.debug("===========================================================");
		LOG.debug("do_delete");
		LOG.debug("===========================================================");
		
		SearchVO search = new SearchVO();
		search.setPageNum(1);
		search.setPageSize(1000);
		List<LGS_TicketVO> tmp = (List<LGS_TicketVO>) reservationDaoImpl.get_retrieve(search);
		for(LGS_TicketVO vo : tmp) {
			int flag = reservationDaoImpl.do_delete(vo);
			assertThat(1, is(flag));
		}
		
	}

	@Test
	public void do_save() {
		LOG.debug("===========================================================");
		LOG.debug("do_save");
		LOG.debug("===========================================================");
		
		//테스트전 처리
		SearchVO search = new SearchVO();
		search.setPageNum(1);
		search.setPageSize(1000);
		List<LGS_TicketVO> tmp = (List<LGS_TicketVO>) reservationDaoImpl.get_retrieve(search);
		for(LGS_TicketVO vo : tmp) {
			int flag = reservationDaoImpl.do_delete(vo);
		}
		
		
		for(LGS_TicketVO vo : list) {
			LOG.debug("vo = " + vo);
			int flag = reservationDaoImpl.do_save(vo);
			
			assertThat(1, is(flag));
		}
		
		
	}	
	
	@Before
	public void getBean() {
		list = Arrays.asList(
				new LGS_TicketVO("co1", "b1", "r1", "s1", "u1", "m1", 100, 0, 1, 10, "", "2019-09-30 00:00:00")
				,new LGS_TicketVO("co2", "b2", "r2", "s2", "u2", "m2", 101, 1, 0, 20, "", "2019-09-30 00:00:00")
				,new LGS_TicketVO("co3", "b3", "r3", "s3", "u3", "m3", 102, 0, 1, 30, "", "2019-09-30 00:00:00")
				,new LGS_TicketVO("co4", "b4", "r4", "s4", "u4", "m4", 103, 1, 0, 40, "", "2019-09-30 00:00:00")
				,new LGS_TicketVO("co5", "b5", "r5", "s5", "u5", "m5", 104, 0, 1, 50, "", "2019-09-30 00:00:00")
							);
		
		LOG.debug("====================");
		LOG.debug("context = " + context);
		LOG.debug("reservationDaoImpl = " + reservationDaoImpl);
		LOG.debug("====================");
		
		assertThat(context, is(notNullValue()) );
		assertThat(reservationDaoImpl, is(notNullValue()) );
		
		//테스트전 처리
		SearchVO search = new SearchVO();
		search.setPageNum(1);
		search.setPageSize(1000);
		List<LGS_TicketVO> tmp = (List<LGS_TicketVO>) reservationDaoImpl.get_retrieve(search);
		for(LGS_TicketVO vo : tmp) {
			int flag = reservationDaoImpl.do_delete(vo);
		}
	}
	
	@After
	public void tearDown() {
		
	}
}
