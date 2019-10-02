package kr.co.sunnyside.room.test;

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

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/**/*.xml"})
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class LGS_RoomDaoTest {
	private final Logger LOG = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private WebApplicationContext context;
	
	@Autowired
	LGS_RoomDaoImpl roomDaoImpl;
	
	@Autowired
	LGS_BranchInfoDaoImpl branchInfoDaoImpl;
	
	private List<LGS_BranchInfoVO> Blist;
	private List<LGS_RoomVO> Rlist;
	
	@Test
	public void get_retrieve() {
		LOG.debug("===========================================================");
		LOG.debug("get_retrieve");
		LOG.debug("===========================================================");
		
		this.intro();
		
		SearchVO search = new SearchVO();
		search.setPageNum(1);
		search.setPageSize(10);
		//search.setSearchDiv("20");
		//search.setSearchWord("name2");
		
		List<LGS_RoomVO> getList = (List<LGS_RoomVO>) roomDaoImpl.get_retrieve(search);
		
		LOG.debug("===========================================================");
		for(LGS_RoomVO vo : getList) {
			LOG.debug("vo : " + vo);
		}
		LOG.debug("===========================================================");
		
	}
	
	@Test
	public void get_selectOne() {
		LOG.debug("===========================================================");
		LOG.debug("get_selectOne");
		LOG.debug("===========================================================");
		
		this.intro();
		
		SearchVO search = new SearchVO();
		search.setPageNum(1);
		search.setPageSize(1000);
		List<LGS_RoomVO> tmp = (List<LGS_RoomVO>) roomDaoImpl.get_retrieve(search);
		
		LGS_RoomVO out = (LGS_RoomVO) roomDaoImpl.get_selectOne(tmp.get(0));
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
		List<LGS_RoomVO> tmp = (List<LGS_RoomVO>) roomDaoImpl.get_retrieve(search);
		
		LGS_RoomVO updateVO = tmp.get(0);
		updateVO.setRoomNm("testtesttesttest");
		int flag2 = roomDaoImpl.do_update(updateVO);
		assertThat(1, is(flag2));
		
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
		List<LGS_RoomVO> tmp = (List<LGS_RoomVO>) roomDaoImpl.get_retrieve(search);
		if(tmp != null) {
			for(LGS_RoomVO vo : tmp) {
				int flag = roomDaoImpl.do_delete(vo);
				assertThat(1, is(flag));
			}
		}
		
	}

	@Test
	public void do_save() {
		LOG.debug("===========================================================");
		LOG.debug("do_save");
		LOG.debug("===========================================================");
		
		this.intro();	

	}	
	
	@Before
	public void getBean() {
		Blist = Arrays.asList(
				new LGS_BranchInfoVO("", "name1")
				,new LGS_BranchInfoVO("", "name2")
				,new LGS_BranchInfoVO("", "name3")
				,new LGS_BranchInfoVO("", "name4")
				,new LGS_BranchInfoVO("", "name5")
				);
		
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
	
	//테스트전 처리
	public void intro() {
		SearchVO search = new SearchVO();
		search.setPageNum(1);
		search.setPageSize(1000);
		List<LGS_RoomVO> tmp = (List<LGS_RoomVO>) roomDaoImpl.get_retrieve(search);
		if(tmp != null) {
			for(LGS_RoomVO vo : tmp) {
				roomDaoImpl.do_delete(vo);
			}
		}
		
		List<LGS_BranchInfoVO> tmp2 = (List<LGS_BranchInfoVO>) branchInfoDaoImpl.get_retrieve(search);
		if(tmp2 != null) {
			for(LGS_BranchInfoVO vo : tmp2) {
				branchInfoDaoImpl.do_delete(vo);
			}
		}
		
		for(LGS_BranchInfoVO vo : Blist) {
			branchInfoDaoImpl.do_save(vo);
		}
		tmp2 = (List<LGS_BranchInfoVO>) branchInfoDaoImpl.get_retrieve(search);
		Rlist = Arrays.asList(
				new LGS_RoomVO(tmp2.get(0).getBranchId(), "testRoom001", "room1", 100, 100)
				,new LGS_RoomVO(tmp2.get(1).getBranchId(), "testRoom002", "room2", 200, 100)
				,new LGS_RoomVO(tmp2.get(2).getBranchId(), "testRoom003", "room3", 300, 100)
				,new LGS_RoomVO(tmp2.get(3).getBranchId(), "testRoom004", "room4", 400, 100)
				,new LGS_RoomVO(tmp2.get(4).getBranchId(), "testRoom005", "room5", 500, 100)
				);
		for(LGS_RoomVO vo : Rlist) {
			int flag = roomDaoImpl.do_save(vo);
			assertThat(1, is(flag));
		}
	}
}
