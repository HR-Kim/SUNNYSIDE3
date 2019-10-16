package kr.co.sunnyside.coupon.test;

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

import kr.co.sunnyside.coupon.service.SJH_CouponVO;
import kr.co.sunnyside.coupon.service.impl.SJH_CouponDao;
import kr.co.sunnyside.login.service.SJH_LoginVO;
import kr.co.sunnyside.login.service.impl.SJH_LoginDao;
import kr.co.sunnyside.usermypage.service.SJH_MypageVO;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/**/*.xml"})
@FixMethodOrder(MethodSorters.NAME_ASCENDING) //@Test를 NAME ASC 순으로 진행
public class SJH_CouponDaoTest {
	
private final Logger LOG = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private WebApplicationContext context;
	
	@Autowired
	SJH_CouponDao couponDao;
	
	List<SJH_CouponVO> coupons;
	
	
	@Before
	public void setUp() {
		LOG.debug("^^^^^^^^^^^^^^^^^^");
		LOG.debug("setUp()");
		LOG.debug("^^^^^^^^^^^^^^^^^^");
		
		coupons = Arrays.asList(
				 new SJH_CouponVO("쿠폰코드01","회원이름01","3천원 할인 쿠폰","","",3000)
				,new SJH_CouponVO("쿠폰코드02","회원이름01","3천원 할인 쿠폰","","",3000)
				,new SJH_CouponVO("쿠폰코드03","회원이름02","3천원 할인 쿠폰","","",3000)
				,new SJH_CouponVO("쿠폰코드04","회원이름02","3천원 할인 쿠폰","","",3000)
				,new SJH_CouponVO("쿠폰코드05","회원이름03","3천원 할인 쿠폰","","",3000)
			);
	}
	
	
	@After
	public void tearDown() {
		
	}
	
	@Test
	public void getBean() {
		LOG.debug("======================");
		LOG.debug("=context="+context);
		LOG.debug("=couponDao="+couponDao);
		LOG.debug("======================");
		assertThat(context, is(notNullValue()));
		assertThat(couponDao, is(notNullValue()));
		
	}
	
	
	
	
	@Test
	@Ignore
	public void do_save() {
		SJH_CouponVO coupon01 = coupons.get(4);
		int flag = couponDao.do_save(coupon01);
	}
	
	
	
	@Test
	@Ignore
	public void do_delete() {
		SJH_CouponVO coupon01 = coupons.get(0);
		coupon01.setCouponCode("DJXMGphkgptmBaj");
		int flag = couponDao.do_delete(coupon01);
	}
	
	
	
	
}
