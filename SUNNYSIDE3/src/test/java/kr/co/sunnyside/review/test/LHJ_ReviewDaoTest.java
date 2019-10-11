package kr.co.sunnyside.review.test;

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
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import kr.co.sunnyside.cmn.SearchVO;
import kr.co.sunnyside.movie.service.LHJ_MovieVO;
import kr.co.sunnyside.review.service.LHJ_ReviewVO;
import kr.co.sunnyside.review.service.impl.LHJ_ReviewDaoImpl;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations= {"file:src/main/webapp/WEB-INF/spring/**/*.xml"}) 
@FixMethodOrder(MethodSorters.NAME_ASCENDING) //테스트 이름 알파벳 순으로 수행한다.
public class LHJ_ReviewDaoTest {
	private final static Logger LOG = LoggerFactory.getLogger(LHJ_ReviewDaoTest.class);
	
	@Autowired
	private WebApplicationContext context;
	
	@Autowired
	private LHJ_ReviewDaoImpl reviewDaoImpl;
	
	List<LHJ_ReviewVO> list;
	
	@Before
	public void setUp() {
		list = Arrays.asList(
				new LHJ_ReviewVO("movie01", "user01", "contents", 1.5, "2019/10/11")
			   ,new LHJ_ReviewVO("movie02", "user02", "contents", 1.5, "2019/10/11")
			   ,new LHJ_ReviewVO("movie03", "user03", "contents", 1.5, "2019/10/11")
			   ,new LHJ_ReviewVO("movie04", "user04", "contents", 1.5, "2019/10/11")
			   ,new LHJ_ReviewVO("movie05", "user05", "contents", 1.5, "2019/10/11")	
			   ,new LHJ_ReviewVO("movie06", "user06", "contents", 1.5, "2019/10/11")
			   ,new LHJ_ReviewVO("movie07", "user07", "contents", 1.5, "2019/10/11")
			   ,new LHJ_ReviewVO("movie08", "user08", "contents", 1.5, "2019/10/11")
			   ,new LHJ_ReviewVO("movie09", "user09", "contents", 1.5, "2019/10/11")
			   ,new LHJ_ReviewVO("movie10", "user10", "contents", 1.5, "2019/10/11")
			   );
	}
	
	/**전체 테스트*/
	@Test
//	@Ignore
	public void allTest() {
		int flag = 0;
		//데이터 삭제
		for(LHJ_ReviewVO vo : list) {
			reviewDaoImpl.do_delete(vo);			
		}	
		
		//데이터 등록
		for(LHJ_ReviewVO vo : list) {
			flag = reviewDaoImpl.do_save(vo);
			assertThat(1, is(flag));
		}	
		
		//데이터 수정
		LHJ_ReviewVO changeData = list.get(0);
		changeData.setContents(changeData.getContents()+"_U");
		changeData.setVisitorRate(3.3);
		
		flag = reviewDaoImpl.do_update(changeData);
		assertThat(1, is(flag));
		
		//단건조회
		LHJ_ReviewVO vo = (LHJ_ReviewVO) reviewDaoImpl.do_selectOne(list.get(0));
		
		//전체조회
		SearchVO search = new SearchVO();
		search.setPageSize(5);
		search.setPageNum(1);
		
		List<LHJ_MovieVO> list = (List<LHJ_MovieVO>) reviewDaoImpl.do_retrieve(search);
		assertThat(5, is(list.size()));		
	}
	
	/**전체조회*/
	@Test
	@Ignore
	public void do_retrieve() {
		SearchVO search = new SearchVO();
		search.setPageSize(5);
		search.setPageNum(1);
		
		List<LHJ_MovieVO> list = (List<LHJ_MovieVO>) reviewDaoImpl.do_retrieve(search);
		assertThat(5, is(list.size()));		
	}
	
	/**단건조회*/
	@Test
	@Ignore
	public void do_selectOne()  {
		LHJ_ReviewVO vo = (LHJ_ReviewVO) reviewDaoImpl.do_selectOne(list.get(0));
	}
	
	/**리뷰 수정*/
	@Test
	@Ignore
	public void do_update() {
		LHJ_ReviewVO changeData = list.get(0);
		changeData.setContents(changeData.getContents()+"_U");
		changeData.setVisitorRate(3.3);
		
		int flag = reviewDaoImpl.do_update(changeData);
		assertThat(1, is(flag));
	}
	
	/**리뷰 등록*/
	@Test
	@Ignore
	public void do_save() {
		for(LHJ_ReviewVO vo : list) {
			int flag = reviewDaoImpl.do_save(vo);
			assertThat(1, is(flag));
		}		
	}
	
	/**리뷰 삭제*/
	@Test
	@Ignore
	public void do_delete() {
		LOG.debug("=============================");
		LOG.debug("=01. 기존 데이터 삭제=");
		LOG.debug("=============================");
		for(LHJ_ReviewVO vo : list) {
			int flag = reviewDaoImpl.do_delete(vo);			
		}		
	}
	
	@Test
	@Ignore
	public void getBean() {
		LOG.debug("======================");
		LOG.debug("context="+context);
		LOG.debug("reviewDaoImpl="+reviewDaoImpl);
		LOG.debug("======================");
		assertThat(context, is(notNullValue()));
		assertThat(reviewDaoImpl, is(notNullValue()));
	}
	
	@After
	public void tearDown() {
		
	}
	
}
