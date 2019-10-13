package kr.co.sunnyside.screeninfo.test;

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

import kr.co.sunnyside.branchinfo.service.LGS_BranchInfoVO;
import kr.co.sunnyside.branchinfo.service.impl.LGS_BranchInfoDaoImpl;
import kr.co.sunnyside.cmn.SearchVO;
import kr.co.sunnyside.room.service.LGS_RoomVO;
import kr.co.sunnyside.room.service.impl.LGS_RoomDaoImpl;
import kr.co.sunnyside.screeninfo.service.LGS_ScreenInfoVO;
import kr.co.sunnyside.screeninfo.service.impl.LGS_ScreenInfoDaoImpl;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/**/*.xml"})
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class LGS_ScreenInfoDaoTest {
	private final Logger LOG = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private WebApplicationContext context;
	
	@Autowired
	LGS_ScreenInfoDaoImpl screenInfoDaoImpl;
	
	@Autowired
	LGS_RoomDaoImpl roomDaoImpl;
	
	@Autowired
	LGS_BranchInfoDaoImpl branchInfoDaoImpl;
	
	
	private List<LGS_ScreenInfoVO> list;

	@Test
	public void do_retrieve() {
		LOG.debug("===========================================================");
		LOG.debug("do_retrieve");
		LOG.debug("===========================================================");

		this.intro();
		
		SearchVO search = new SearchVO();
		search.setPageNum(1);
		search.setPageSize(10);
		//search.setSearchDiv("20");
		//search.setSearchWord("name2");
		
		List<LGS_ScreenInfoVO> getList = (List<LGS_ScreenInfoVO>) screenInfoDaoImpl.do_retrieve(search);
		
		LOG.debug("===========================================================");
		for(LGS_ScreenInfoVO vo : getList) {
			LOG.debug("vo : " + vo);
		}
		LOG.debug("===========================================================");
		
	}

	@Test
	public void do_selectOne() {
		LOG.debug("===========================================================");
		LOG.debug("do_selectOne");
		LOG.debug("===========================================================");
		
		this.intro();
		
		SearchVO search = new SearchVO();
		search.setPageNum(1);
		search.setPageSize(1000);
		List<LGS_ScreenInfoVO> tmp = (List<LGS_ScreenInfoVO>) screenInfoDaoImpl.do_retrieve(search);
		
		LGS_ScreenInfoVO out = (LGS_ScreenInfoVO) screenInfoDaoImpl.do_selectOne(tmp.get(0));
		LOG.debug("===========================================================");
		LOG.debug("out : "  + out);
		LOG.debug("===========================================================");
	}
	
	@Test
	public void do_update() {
		LOG.debug("===========================================================");
		LOG.debug("do_update");
		LOG.debug("===========================================================");
		
		this.intro();
		
		SearchVO search = new SearchVO();
		search.setPageNum(1);
		search.setPageSize(1000);
		List<LGS_ScreenInfoVO> tmp = (List<LGS_ScreenInfoVO>) screenInfoDaoImpl.do_retrieve(search);
		
		LGS_ScreenInfoVO updateVO = tmp.get(0);
		updateVO.setAdultCost(990000000);
		int flag = screenInfoDaoImpl.do_update(updateVO);
		assertThat(1, is(flag));
		
	}

	@Test
	public void do_delete() {
		LOG.debug("===========================================================");
		LOG.debug("do_delete");
		LOG.debug("===========================================================");
		
		this.intro();
		
		SearchVO search = new SearchVO();
		search.setPageNum(1);
		search.setPageSize(1000);
		List<LGS_ScreenInfoVO> tmp = (List<LGS_ScreenInfoVO>) screenInfoDaoImpl.do_retrieve(search);
		
		for(LGS_ScreenInfoVO vo : tmp) {
			LOG.debug("vo = " + vo);
			int flag = screenInfoDaoImpl.do_delete(vo);
			
			assertThat(1, is(flag));
		}
		
	}

	@Test
	public void do_save() {
		LOG.debug("===========================================================");
		LOG.debug("do_save");
		LOG.debug("===========================================================");
		
		this.intro();
		
		for(LGS_ScreenInfoVO vo : list) {
			LOG.debug("vo = " + vo);
			int flag = screenInfoDaoImpl.do_save(vo);
			
			assertThat(1, is(flag));
		}
		
	}	
	
	@Before
	public void getBean() {
		
		LOG.debug("====================");
		LOG.debug("context = " + context);
		LOG.debug("ScreenInfoDaoImpl = " + screenInfoDaoImpl);
		LOG.debug("====================");
		
		assertThat(context, is(notNullValue()) );
		assertThat(screenInfoDaoImpl, is(notNullValue()) );
		
		this.start();
	}
	
	@After
	public void tearDown() {
		
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
				new LGS_ScreenInfoVO("", tmp2.get(0).getRoomId(), "b1", "m1", "19/09/30", "19/09/30", "19/09/30", 100,10,1,"","",0,0)
				,new LGS_ScreenInfoVO("", tmp2.get(1).getRoomId(), "b2", "m2", "19/09/30", "19/09/30", "19/09/30", 200,20,2,"","",0,0)
				,new LGS_ScreenInfoVO("", tmp2.get(2).getRoomId(), "b3", "m3", "19/09/30", "19/09/30", "19/09/30", 300,30,3,"","",0,0)
				,new LGS_ScreenInfoVO("", tmp2.get(3).getRoomId(), "b4", "m4", "19/09/30", "19/09/30", "19/09/30", 400,40,4,"","",0,0)
				,new LGS_ScreenInfoVO("", tmp2.get(4).getRoomId(), "b5", "m5", "19/09/30", "19/09/30", "19/09/30", 500,50,5,"","",0,0)
				);
		
		
		for(LGS_ScreenInfoVO vo : list) {
			screenInfoDaoImpl.do_save(vo);
		}
		
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
}
