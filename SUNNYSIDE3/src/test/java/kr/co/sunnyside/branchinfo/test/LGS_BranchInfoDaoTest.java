package kr.co.sunnyside.branchinfo.test;

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
import kr.co.sunnyside.room.service.LGS_RoomVO;
import kr.co.sunnyside.room.service.impl.LGS_RoomDaoImpl;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/**/*.xml"})
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class LGS_BranchInfoDaoTest {
	private final Logger LOG = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private WebApplicationContext context;
	
	@Autowired
	LGS_BranchInfoDaoImpl branchInfoDaoImpl;
	
	@Autowired
	LGS_RoomDaoImpl roomDaoImpl;
	
	private List<LGS_BranchInfoVO> list;

	@Test
	public void get_retrieve() {
		LOG.debug("===========================================================");
		LOG.debug("get_retrieve");
		LOG.debug("===========================================================");
		
		//테스트전 처리
		SearchVO search0 = new SearchVO();
		search0.setPageNum(1);
		search0.setPageSize(1000);
		List<LGS_BranchInfoVO> tmp = (List<LGS_BranchInfoVO>) branchInfoDaoImpl.get_retrieve(search0);
		if(tmp != null) {
			for(LGS_BranchInfoVO vo : tmp) {
				LGS_BranchInfoVO out = (LGS_BranchInfoVO) branchInfoDaoImpl.get_selectOne(vo);
				branchInfoDaoImpl.do_delete(out);
			}
		}
		
		for(LGS_BranchInfoVO vo : list) {
			int flag = branchInfoDaoImpl.do_save(vo);
		}
		
		SearchVO search = new SearchVO();
		search.setPageNum(1);
		search.setPageSize(10);
		//search.setSearchDiv("20");
		//search.setSearchWord("name2");
		
		List<LGS_BranchInfoVO> getList = (List<LGS_BranchInfoVO>) branchInfoDaoImpl.get_retrieve(search);
		
		LOG.debug("===========================================================");
		for(LGS_BranchInfoVO vo : getList) {
			LOG.debug("vo : " + vo);
		}
		LOG.debug("===========================================================");
		
	}

	@Test
	public void get_selectOne() {
		LOG.debug("===========================================================");
		LOG.debug("get_selectOne");
		LOG.debug("===========================================================");
		
		//테스트전 처리
		SearchVO search = new SearchVO();
		search.setPageNum(1);
		search.setPageSize(1000);
		List<LGS_BranchInfoVO> tmp = (List<LGS_BranchInfoVO>) branchInfoDaoImpl.get_retrieve(search);
		if(tmp != null) {
			for(LGS_BranchInfoVO vo : tmp) {
				LGS_BranchInfoVO out = (LGS_BranchInfoVO) branchInfoDaoImpl.get_selectOne(vo);
				branchInfoDaoImpl.do_delete(out);
			}
		}
		
		for(LGS_BranchInfoVO vo : list) {
			int flag = branchInfoDaoImpl.do_save(vo);
			
			assertThat(1, is(flag));
		}
		
		LGS_BranchInfoVO out = (LGS_BranchInfoVO) branchInfoDaoImpl.get_selectOne(list.get(0));
		LOG.debug("===========================================================");
		LOG.debug("out : "  + out);
		LOG.debug("===========================================================");
	}

	@Test
	public void do_update() {
		LOG.debug("===========================================================");
		LOG.debug("do_update");
		LOG.debug("===========================================================");
		
		//테스트전 처리
		SearchVO search = new SearchVO();
		search.setPageNum(1);
		search.setPageSize(1000);
		List<LGS_BranchInfoVO> tmp = (List<LGS_BranchInfoVO>) branchInfoDaoImpl.get_retrieve(search);
		if(tmp != null) {
			for(LGS_BranchInfoVO vo : tmp) {
				LGS_BranchInfoVO out = (LGS_BranchInfoVO) branchInfoDaoImpl.get_selectOne(vo);
				branchInfoDaoImpl.do_delete(out);
			}
		}
		
		for(LGS_BranchInfoVO vo : list) {
			int flag = branchInfoDaoImpl.do_save(vo);
			assertThat(1, is(flag));
		}

		tmp = (List<LGS_BranchInfoVO>) branchInfoDaoImpl.get_retrieve(search);
		
		LGS_BranchInfoVO updateVO = (LGS_BranchInfoVO) branchInfoDaoImpl.get_selectOne(tmp.get(0));
		updateVO.setBranchNm("updateTEST");
		int flag2 = branchInfoDaoImpl.do_update(updateVO);
		assertThat(1, is(flag2));
		
	}

	@Test
	public void do_delete() {
		LOG.debug("===========================================================");
		LOG.debug("do_delete");
		LOG.debug("===========================================================");
		
		//테스트전 처리
		SearchVO search = new SearchVO();
		search.setPageNum(1);
		search.setPageSize(1000);
		List<LGS_BranchInfoVO> tmp = (List<LGS_BranchInfoVO>) branchInfoDaoImpl.get_retrieve(search);
		if(tmp != null) {
			for(LGS_BranchInfoVO vo : tmp) {
				LGS_BranchInfoVO out = (LGS_BranchInfoVO) branchInfoDaoImpl.get_selectOne(vo);
				int flag = branchInfoDaoImpl.do_delete(out);
				assertThat(1, is(flag));
			}
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
		List<LGS_BranchInfoVO> tmp = (List<LGS_BranchInfoVO>) branchInfoDaoImpl.get_retrieve(search);
		if(tmp != null) {
			for(LGS_BranchInfoVO vo : tmp) {
				LGS_BranchInfoVO out = (LGS_BranchInfoVO) branchInfoDaoImpl.get_selectOne(vo);
				branchInfoDaoImpl.do_delete(out);
			}
		}
		
		
		for(LGS_BranchInfoVO vo : list) {
			LOG.debug("vo = " + vo);
			int flag = branchInfoDaoImpl.do_save(vo);
			
			assertThat(1, is(flag));
		}
		
		
	}	
	
	@Before
	public void getBean() {
		list = Arrays.asList(
							new LGS_BranchInfoVO("testId001", "branch1")
							,new LGS_BranchInfoVO("testId002", "branch2")
							,new LGS_BranchInfoVO("testId003", "branch3")
							,new LGS_BranchInfoVO("testId004", "branch4")
							,new LGS_BranchInfoVO("testId005", "branch5")
							);
		
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
		
		List<LGS_RoomVO> tmp0 = (List<LGS_RoomVO>) roomDaoImpl.get_retrieve(search);
		if(tmp0 != null) {
			for(LGS_RoomVO vo : tmp0) {
				LGS_RoomVO out = (LGS_RoomVO) roomDaoImpl.get_selectOne(vo);
				int flag = roomDaoImpl.do_delete(out);
				assertThat(1, is(flag));
			}
		}
		
		List<LGS_BranchInfoVO> tmp = (List<LGS_BranchInfoVO>) branchInfoDaoImpl.get_retrieve(search);
		if(tmp != null) {
			for(LGS_BranchInfoVO vo : tmp) {
				LGS_BranchInfoVO out = (LGS_BranchInfoVO) branchInfoDaoImpl.get_selectOne(vo);
				int flag = branchInfoDaoImpl.do_delete(out);
				assertThat(1, is(flag));
			}
		}
	}
	
	@After
	public void tearDown() {
		
	}
	
}
