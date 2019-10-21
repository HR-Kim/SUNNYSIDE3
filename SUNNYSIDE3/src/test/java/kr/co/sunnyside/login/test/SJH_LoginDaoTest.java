package kr.co.sunnyside.login.test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

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
import kr.co.sunnyside.login.service.impl.SJH_LoginDao;
import kr.co.sunnyside.usermypage.service.SJH_MypageVO;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/**/*.xml"})
@FixMethodOrder(MethodSorters.NAME_ASCENDING) //@Test를 NAME ASC 순으로 진행
public class SJH_LoginDaoTest {
	
	private final Logger LOG = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private WebApplicationContext context;
	
	@Autowired
	SJH_LoginDao loginDao;
	
	List<SJH_LoginVO> users;
	
	
	@Before
	public void setUp() {
		LOG.debug("^^^^^^^^^^^^^^^^^^");
		LOG.debug("setUp()");
		LOG.debug("^^^^^^^^^^^^^^^^^^");
		
		users = Arrays.asList(
				 new SJH_LoginVO("j01_126","1234","회원이름01","glwlzkwp@naver.com","BASIC",0,"01000000001","19990101","",190000,"")
				,new SJH_LoginVO("j02_126","1234","회원이름02","glwlzkwp@naver.com","BASIC",0,"01000000002","19990102","",200000,"") //BASIC -> SILVER
				,new SJH_LoginVO("j03_126","1234","회원이름03","glwlzkwp@naver.com","SILVER",0,"01000000003","19990103","",200000,"")
				,new SJH_LoginVO("j04_126","1234","회원이름04","glwlzkwp@naver.com","SILVER",0,"01000000004","19990104","",400000,"") //SILVER -> GOLD
				,new SJH_LoginVO("j05_126","1234","회원이름05","glwlzkwp@naver.com","BASIC",0,"01000000005","19990105","",500000,"")
				
//				 new SJH_LoginVO("j06_126","1234","회원이름06","glwlzkwp@naver.com","BASIC",0,"01000000001","19990101","",190000,"")
//				,new SJH_LoginVO("j07_126","1234","회원이름07","glwlzkwp@naver.com","BASIC",0,"01000000002","19990102","",200000,"") //BASIC -> SILVER
//				,new SJH_LoginVO("j08_126","1234","회원이름08","glwlzkwp@naver.com","SILVER",0,"01000000003","19990103","",200000,"")
//				,new SJH_LoginVO("j09_126","1234","회원이름09","glwlzkwp@naver.com","SILVER",0,"01000000004","19990104","",400000,"") //SILVER -> GOLD
//				,new SJH_LoginVO("j10_126","1234","회원이름10","glwlzkwp@naver.com","GOLD",0,"01000000005","19990105","",500000,"")
				
			);
	}
	
	
	@After
	public void tearDown() {
		
	}
	
	@Test
	public void getBean() {
		LOG.debug("======================");
		LOG.debug("=context="+context);
		LOG.debug("=loginDao="+loginDao);
		LOG.debug("======================");
		assertThat(context, is(notNullValue()));
		assertThat(loginDao, is(notNullValue()));
		
	}
	
	
	
	
	
	@Test
	@Ignore
	public void pw_find() {
		
		SJH_LoginVO user01 = users.get(0);
		int flag = loginDao.pw_find(user01);
	}
	
	
	@Test
	@Ignore
	public void id_find() {
		SJH_LoginVO user01 = users.get(0);
		SJH_LoginVO vsUser = (SJH_LoginVO) loginDao.id_find(user01);
		assertThat(user01.getUserId(), is(vsUser.getUserId()));
	}
	
	
	@Test
	//@Ignore
	public void do_save() {
//		loginDao.do_save(users.get(4));
//		assertThat(1, is(flag));
		
		for(SJH_LoginVO user:users) {
			loginDao.do_save(user);
		}
		
	}
	
	
	@Test
	@Ignore
	public void do_login() {
		int flag = loginDao.id_check(users.get(0));
		assertThat(1, is(flag));
		
		flag = loginDao.passwd_check(users.get(0));
		assertThat(1, is(flag));
		
		SJH_LoginVO user01 = users.get(0);
		
		SJH_LoginVO vsUser = (SJH_LoginVO) loginDao.do_selectOne(user01);
		
		assertThat(user01.getUserId(), is(vsUser.getUserId()));
		assertThat(user01.getPasswd(), is(vsUser.getPasswd()));
		assertThat(user01.getUserName(), is(vsUser.getUserName()));
		assertThat(user01.getEmail(), is(vsUser.getEmail()));
	}
	
	
	
}
