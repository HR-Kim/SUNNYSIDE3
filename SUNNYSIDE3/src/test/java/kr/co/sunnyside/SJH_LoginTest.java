package kr.co.sunnyside;

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

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/**/*.xml"})
@FixMethodOrder(MethodSorters.NAME_ASCENDING) //@Test를 NAME ASC 순으로 진행
public class SJH_LoginTest {
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
				new SJH_LoginVO("j01_126","1234","회원이름01","이메일01@naver.com")
				,new SJH_LoginVO("j02_126","1234","회원이름02","이메일02@naver.com")
				,new SJH_LoginVO("j03_126","1234","회원이름03","이메일03@naver.com")
				,new SJH_LoginVO("j04_126","1234","회원이름04","이메일04@naver.com")
				,new SJH_LoginVO("j05_126","1234","회원이름05","이메일05@naver.com")
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
	public void do_login() {
		int flag = loginDao.id_check(users.get(0));
		assertThat(1, is(flag));
		
		flag = loginDao.passwd_check(users.get(0));
		assertThat(1, is(flag));
		
		SJH_LoginVO user01 = users.get(0);
		
		SJH_LoginVO vsUser = (SJH_LoginVO) loginDao.get_selectOne(user01);
		
		assertThat(user01.getUserId(), is(vsUser.getUserId()));
		assertThat(user01.getPasswd(), is(vsUser.getPasswd()));
		assertThat(user01.getUserName(), is(vsUser.getUserName()));
		assertThat(user01.getEmail(), is(vsUser.getEmail()));
		
	}
	
	
	
}
