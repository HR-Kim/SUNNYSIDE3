package kr.co.sunnyside.store.test;

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

import kr.co.sunnyside.cmn.StringUtil;
import kr.co.sunnyside.store.service.SEJ_StroreVO;
import kr.co.sunnyside.store.service.impl.SEJ_StroreDaoImpl;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations= {"file:src/main/webapp/WEB-INF/spring/**/*.xml"}) 
@FixMethodOrder(MethodSorters.NAME_ASCENDING) //테스트 이름 알파벳 순으로 수행한다.
public class SEJ_StroreDaoTest {
	private final Logger LOG = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private WebApplicationContext context;
	
	@Autowired
	private SEJ_StroreDaoImpl storeDaoImpl;
	
	List<SEJ_StroreVO> list;
	
	@Before
	public void setUp() {
		//String fileId = StringUtil.cureDate("yyyyMMdd")+""+StringUtil.getUUID();
		
		//LOG.debug(fileId.length()+"");// 번호 등록될 때 아이디랑 뒤에 번호 아무거나 붙여져서 나온다
		list = Arrays.asList(
				new SEJ_StroreVO("20191001-001","고소팝콘(L)","옥수수 본연의 맛을 즐길 수 있는 짭짜름한 클래식 고소팝콘!",001,5000,"J01_사진.jpg","J02_사진.jpg",1000 ,"jpg"),
				new SEJ_StroreVO("20191001-002","달콤팝콘(L)","달콤한 카라멜 향이 가득한 달콤팝콘을 즐겨보세요!",001,6000,"J01_사진.jpg","J02_사진.jpg",2000 ,"jpg"),
				new SEJ_StroreVO("20191001-003","더블치즈팝콘(L)","치즈매니아들이라면 놓칠 수 없는 바로 그 팝콘!",001,6000,"J01_사진.jpg","J02_사진.jpg",3000 ,"jpg"),
				new SEJ_StroreVO("20191001-004","바질어니언팝콘(L)","수많은 매니아를 보유한 바로 그 팝콘! 중독성 200%",001,6000,"J01_사진.jpg","J02_사진.jpg",4000 ,"jpg")
		
				);
	}
	
	@Test
	public void getBean() {
		LOG.debug("======================");
		LOG.debug("context="+context);
		LOG.debug("codeDaoImpl="+storeDaoImpl);
		LOG.debug("======================");
		assertThat(context, is(notNullValue()));
		assertThat(storeDaoImpl, is(notNullValue()));
	}
	
	@Test
	//@Ignore
	public void do_save() {
		for(SEJ_StroreVO vo:list) {
			int flag = storeDaoImpl.do_save(vo);
			assertThat(1, is(flag));
		}
	}
	
	@After
	public void tearDown() {
		
	}
	
	
	
}
