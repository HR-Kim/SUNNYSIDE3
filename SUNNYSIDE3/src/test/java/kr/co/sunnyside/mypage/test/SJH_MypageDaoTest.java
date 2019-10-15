package kr.co.sunnyside.mypage.test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

import java.sql.SQLException;
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
import org.springframework.web.context.WebApplicationContext;

import kr.co.sunnyside.login.service.SJH_LoginVO;
import kr.co.sunnyside.usermypage.service.SJH_MypageVO;
import kr.co.sunnyside.usermypage.service.impl.SJH_MypageDao;
import kr.co.sunnyside.usermypage.service.impl.SJH_MypageSvcImpl;


@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/**/*.xml"})
@FixMethodOrder(MethodSorters.NAME_ASCENDING) //@Test를 NAME ASC 순으로 진행
public class SJH_MypageDaoTest {

	private final Logger LOG = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private WebApplicationContext context;
	
	@Autowired
	SJH_MypageDao mypageDao;
	
	@Autowired
	SJH_MypageSvcImpl mypageSvc;
	
	List<SJH_MypageVO> users;
	
	
	@Before
	public void setUp() {
		LOG.debug("^^^^^^^^^^^^^^^^^^");
		LOG.debug("setUp()");
		LOG.debug("^^^^^^^^^^^^^^^^^^");
		
		users = Arrays.asList(
					 new SJH_MypageVO("j01_126","1234","회원이름01","이메일01@naver.com","BASIC",0,"010-0000-0001","19/09/01","",190000)
					,new SJH_MypageVO("j02_126","1234","회원이름02","이메일02@naver.com","BASIC",0,"010-0000-0002","19/09/02","",200000) //BASIC -> SILVER
					,new SJH_MypageVO("j03_126","1234","회원이름03","이메일03@naver.com","SILVER",0,"010-0000-0003","19/09/03","",200000)
					,new SJH_MypageVO("j04_126","1234","회원이름04","이메일04@naver.com","SILVER",0,"010-0000-0004","19/09/04","",400000) //SILVER -> GOLD
					,new SJH_MypageVO("j05_126","1234","회원이름05","이메일05@naver.com","GOLD",0,"010-0000-0005","19/09/05","",400000)
				);
	}
	
	
	@After
	public void tearDown() {
		
	}
	
	@Test
	public void getBean() {
		LOG.debug("======================");
		LOG.debug("=context="+context);
		LOG.debug("=mypageDao="+mypageDao);
		LOG.debug("======================");
		assertThat(context, is(notNullValue()));
		assertThat(mypageDao, is(notNullValue()));
		
	}
	
	
	
	
	
	@Test
	//@Ignore
	public void tx_levelUpdate() throws SQLException {
		
		try {
			mypageSvc.tx_upgradeLevels();
		}catch(RuntimeException t) {
			LOG.debug("======================================");
			LOG.debug("=RuntimeException="+t.getMessage());
			LOG.debug("======================================");
		}
		
		//트랜잭션 처리 시 베이직으로 Rollback
		assertThat(users.get(0).getUserLevel(), is("BASIC"));
		
	}
	
	
	@Test
	@Ignore
	public void do_getAll() {
		List<SJH_MypageVO> list = (List<SJH_MypageVO>) mypageDao.do_getAll();
		assertThat(4, is(list.size()));
	}	
	
	
	
	@Test
	@Ignore
	public void do_update() {
		SJH_MypageVO user01 = users.get(0);
		
		user01.setPasswd("수정이름");
		user01.setUserName("수정비번");
		user01.setCellphone("수정폰번호");
		user01.setEmail("수정이메일");
		
		mypageDao.do_update(user01);
	}
	
		
	@Test
	@Ignore
	public void do_selectOne() {
		SJH_MypageVO user01 = users.get(1);
		SJH_MypageVO outVO = (SJH_MypageVO) mypageDao.do_selectOne(user01);
		assertThat(user01.getUserId(), is(outVO.getUserId()));
	}
	
	
	@Test
	@Ignore
	public void do_delete() {
		SJH_MypageVO user01 = users.get(0);
		int flag = mypageDao.do_delete(user01);
	}
	
	
}
