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

import kr.co.sunnyside.store.service.SEJ_CartVO;
import kr.co.sunnyside.store.service.impl.SEJ_CartDaoImpl;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations= {"file:src/main/webapp/WEB-INF/spring/**/*.xml"}) 
@FixMethodOrder(MethodSorters.NAME_ASCENDING) //테스트 이름 알파벳 순으로 수행한다.
public class SEJ_CartDaoTest {
	private final Logger LOG = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private WebApplicationContext context;
	
	@Autowired
	private SEJ_CartDaoImpl cartDaoImpl;
	
	List<SEJ_CartVO> list;
	
	@Before
	public void setUp() {
		
		list = Arrays.asList(
				new SEJ_CartVO("20191018-001","고소팝콘(L)","admin",1,5000,5000,"20191011-001-000","","",""),
				new SEJ_CartVO("20191018-002","아메리카노(ICE)","admin",2,4000,8000,"20191011-002-009","","",""),
				new SEJ_CartVO("20191018-003","3D관람권","admin",1,12000,12000,"20191013-003-010","","",""),
				new SEJ_CartVO("20191018-004","달콤팝콘(M)","admin",1,5500,5500,"20191013-001-018","","","")
				);
	}
	
	@Test
	public void getBean() {
		LOG.debug("======================");
		LOG.debug("context="+context);
		LOG.debug("codeDaoImpl="+cartDaoImpl);
		LOG.debug("======================");
		assertThat(context, is(notNullValue()));
		assertThat(cartDaoImpl, is(notNullValue()));
	}

	/**장바구니 추가*/
	@Test
	@Ignore
	public void do_save() {
		for(SEJ_CartVO vo:list) {
			int flag = cartDaoImpl.do_save(vo);
			assertThat(1, is(flag));
		}
	}
	
	/**장바구니 삭제 */
	@Test
	@Ignore
	public void do_delete() {
		cartDaoImpl.do_delete(list.get(0));
	}
	
	/**장바구니 전체삭제 */
	@Test
	@Ignore
	public void do_deleteAll() {
		cartDaoImpl.do_deleteAll();
	}
	
	/**장바구니 수정 ===========> 정지하는 이유? */
	@Test
	@Ignore
	public void do_update() {
				
		SEJ_CartVO store = list.get(1);
		store.setCount(2);
		
		int flag = cartDaoImpl.do_update(store);
		assertThat(flag, is(1));
	}
	
	
	/**장바구니 목록 */
	@Test
	@Ignore
	public void do_retrieve() {
		SEJ_CartVO store = new SEJ_CartVO();		
		store.setUserId("admin");
		
		List<SEJ_CartVO> list = (List<SEJ_CartVO>) cartDaoImpl.do_retrieve(store);
		assertThat(3, is(list.size()));		
	}

    
	/**장바구니 금액 총합계 */                                
	@Test                                       
	@Ignore                                     
	public void do_selectOne(){                 
		cartDaoImpl.do_selectOne(list.get(1)); 
	    
	}                                           
	
	
	

	
	@After
	public void tearDown() {
		
	}
	
	
	
}
