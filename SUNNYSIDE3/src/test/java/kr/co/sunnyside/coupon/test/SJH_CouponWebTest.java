package kr.co.sunnyside.coupon.test;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import kr.co.sunnyside.coupon.service.SJH_CouponVO;
import kr.co.sunnyside.coupon.service.impl.SJH_CouponDao;
import kr.co.sunnyside.mypage.test.SJH_MypageWebTest;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/root-context.xml"
								  ,"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"})
public class SJH_CouponWebTest {

	private Logger LOG = LoggerFactory.getLogger(SJH_MypageWebTest.class);
	
	
	@Autowired
	private SJH_CouponDao dao;
	
	@Autowired
	WebApplicationContext context;
	List<SJH_CouponVO> coupons;
	
	private MockMvc mockMvc; //리퀘스트 호출할 부분
	
	
	@Before
	public void setUp() {
		LOG.debug("^^^^^^^^^^^^^^^^^^");
		LOG.debug("setUp()");
		LOG.debug("^^^^^^^^^^^^^^^^^^");
		
		coupons = Arrays.asList(
				 new SJH_CouponVO("쿠폰코드01","회원아이디01","3천원 할인 쿠폰","","",3000)
				,new SJH_CouponVO("쿠폰코드02","회원아이디01","3천원 할인 쿠폰","","",3000)
				,new SJH_CouponVO("쿠폰코드03","회원아이디02","3천원 할인 쿠폰","","",3000)
				,new SJH_CouponVO("쿠폰코드04","회원아이디02","3천원 할인 쿠폰","","",3000)
				,new SJH_CouponVO("쿠폰코드05","회원아이디03","3천원 할인 쿠폰","","",3000)
			);
		
		mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
		
		LOG.debug("==============================");
		LOG.debug("=context="+context);
		LOG.debug("=mockMvc="+mockMvc);
		LOG.debug("=dao="+dao);
		LOG.debug("==============================");
		
	}
	
	
	@Test
	//@Ignore
	public void do_save() throws Exception {
		MockHttpServletRequestBuilder createMessage = MockMvcRequestBuilders.post("/coupon/do_save.do")
														.param("userId", "회원아이디01")
														.param("couponNm", "쿠폰이름00")
														.param("usable", "")
														;
		
		ResultActions resultActions = mockMvc.perform(createMessage)
									.andExpect(MockMvcResultMatchers.content().contentType("application/json; charset=UTF-8"))
									;
		
		String result = resultActions.andDo(print())
						.andReturn()
						.getResponse().getContentAsString();
		
		LOG.debug("==============================");
		LOG.debug("=result="+result);
		LOG.debug("==============================");
	}
	
	
	
	
	@Test
	@Ignore
	public void do_delete() throws Exception {
		MockHttpServletRequestBuilder createMessage = MockMvcRequestBuilders.post("/coupon/do_delete.do")
														.param("userId", "회원이름01")
														.param("couponCode", "vSGUMpyrxfguXuV");
		
		ResultActions resultActions = mockMvc.perform(createMessage)
									.andExpect(MockMvcResultMatchers.content().contentType("application/json; charset=UTF-8"))
									;
		
		String result = resultActions.andDo(print())
						.andReturn()
						.getResponse().getContentAsString();
		
		LOG.debug("==============================");
		LOG.debug("=result="+result);
		LOG.debug("==============================");
	}
	
	
	
	
}
