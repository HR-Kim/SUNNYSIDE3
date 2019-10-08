package kr.co.sunnyside;


import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.notNull;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
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

import kr.co.sunnyside.code.service.CodeVO;
import kr.co.sunnyside.code.service.impl.CodeDaoImpl;



@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations= {"file:src/main/webapp/WEB-INF/spring/**/*.xml"})
@FixMethodOrder(MethodSorters.NAME_ASCENDING) //테스트 이름 알파벳 순으로 수행한다.
public class CodeDaoTest {
	private final Logger LOG = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private WebApplicationContext context;
	
	@Autowired
	private CodeDaoImpl codeDaoImpl;
	
	private CodeVO code;
	
	@Before
	public void setUp() {
		code = new CodeVO();
		
	}
	
	@Test
	public void getBean() {
		LOG.debug("======================");
		LOG.debug("context="+context);
		LOG.debug("codeDaoImpl="+codeDaoImpl);
		LOG.debug("======================");
		assertThat(context, is(notNullValue()));
		assertThat(codeDaoImpl, is(notNullValue()));
	}
	
	@Test
	public void do_retrieve() {
		code.setCodeId("PAGE_SIZE");
		List<CodeVO> list =  (List<CodeVO>) this.codeDaoImpl.get_retrieve(code);
		LOG.debug("======================");
		for(CodeVO vo :list) {
			LOG.debug(vo.toString());
		}
		LOG.debug("======================");
		
		assertThat(5, is(list.size()));
	}
	
	@After
	public void tearDown() {
		
	}
	
	
}
