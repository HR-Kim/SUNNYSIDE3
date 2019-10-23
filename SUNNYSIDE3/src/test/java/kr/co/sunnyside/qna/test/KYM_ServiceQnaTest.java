package kr.co.sunnyside.qna.test;

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

import kr.co.sunnyside.cmn.SearchVO;
import kr.co.sunnyside.qna.service.KYMQnaVO;
import kr.co.sunnyside.qna.service.impl.KYMQnaDaoImpl;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/**/*.xml"})
@FixMethodOrder(MethodSorters.NAME_ASCENDING)//@Test NAME_ASCENDING으로 수행.
public class KYM_ServiceQnaTest {
	
private final Logger LOG = LoggerFactory.getLogger(this.getClass());	
	
	@Autowired
	private WebApplicationContext context;
	
	@Autowired
	private KYMQnaDaoImpl kymQnaDaoImpl;
	
	List<KYMQnaVO> list;
	
	@Before
	public void setUp() {
		list = Arrays.asList(
					 new KYMQnaVO("테스트1","1","테스트1","테스트11","","2019/10/16","파일1","파일11","xls",10000,"테스트111","2019/10/17")
					,new KYMQnaVO("테스트2","2","테스트2","테스트22","","2019/10/16","파일2","파일2","xls",20000,"테스트222","2019/10/17")
					,new KYMQnaVO("테스트3","3","테스트3","테스트33","","2019/10/16","파일3","파일3","xls",30000,"테스트333","2019/10/17")
					,new KYMQnaVO("테스트4","4","테스트4","테스트44","","2019/10/16","파일4","파일4","xls",40000,"테스트444","2019/10/17")
					,new KYMQnaVO("테스트5","5","테스트5","테스트55","","2019/10/16","파일5","파일5","xls",50000,"테스트555","2019/10/17")
						
			);
		
	}
	
	@Test
	//@Ignore
	public void get_retrieve() {
		//수정할 데이터 검색
		SearchVO search=new SearchVO();
		search.setSearchWord("테스트");
		List<KYMQnaVO> getIdList = (List<KYMQnaVO>) kymQnaDaoImpl.do_boardIdList(search);
		
		//데이터 삭제
		for(KYMQnaVO vo:getIdList) {
			kymQnaDaoImpl.do_delete(vo);
		}
		
		//데이터 저장
		for(KYMQnaVO vo:list) {
			int flag = kymQnaDaoImpl.do_save(vo);
			assertThat(1, is(1));
		}
		
		//데이터 조회
		search.setSearchDiv("10");
		search.setPageSize(10);
		search.setPageNum(1);
		
		List<KYMQnaVO> list = (List<KYMQnaVO>) kymQnaDaoImpl.do_retrieve(search);
		assertThat(5, is(list.size()));
		
	}
	
	@Test
	//@Ignore
	public void do_update() {
		//수정할 데이터 검색
		SearchVO search=new SearchVO();
		search.setSearchWord("테스트");
		List<KYMQnaVO> getIdList = (List<KYMQnaVO>) kymQnaDaoImpl.do_boardIdList(search);
		
		//데이터 삭제
		for(KYMQnaVO vo:getIdList) {
			kymQnaDaoImpl.do_delete(vo);
		}
		
		//데이터 저장
		for(KYMQnaVO vo:list) {
			int flag = kymQnaDaoImpl.do_save(vo);
			assertThat(1, is(1));
		}
		
		//데이터 조회
		getIdList = (List<KYMQnaVO>) kymQnaDaoImpl.do_boardIdList(search);
		assertThat(5, is(getIdList.size()));
		
		//데이터 수정
		KYMQnaVO kymQnaVO = getIdList.get(0);
		kymQnaVO.setTitle(kymQnaVO.getTitle()+"_U");
		kymQnaVO.setContents(kymQnaVO.getContents()+"_U");
		
		int flag = kymQnaDaoImpl.do_update(kymQnaVO);
		assertThat(flag, is(1));
		
		//데이터 비교
		getIdList = (List<KYMQnaVO>) kymQnaDaoImpl.do_boardIdList(search);
		checkData(kymQnaVO, getIdList.get(0));
		
	}
	
	@Test
	//@Ignore
	public void addAndGet() {
		SearchVO search=new SearchVO();
		search.setSearchWord("테스트");
		List<KYMQnaVO> getIdList = (List<KYMQnaVO>) kymQnaDaoImpl.do_boardIdList(search);
			
		for(KYMQnaVO vo:getIdList) {
			kymQnaDaoImpl.do_delete(vo);
		}

		for(KYMQnaVO vo:list) {
			int flag = kymQnaDaoImpl.do_save(vo);
			assertThat(1, is(1));
		}		

		getIdList = (List<KYMQnaVO>) kymQnaDaoImpl.do_boardIdList(search);
		assertThat(5, is(getIdList.size()));
		
		for(int i=0;i<list.size();i++) {
			checkData(list.get(i),getIdList.get(i));
			
		}
		
	}
	
	private void checkData(KYMQnaVO org, KYMQnaVO vs) {
		assertThat(org.getTitle(), is(vs.getTitle()));
		assertThat(org.getContents(), is(vs.getContents()));
		
	}
	
	@Test
	//@Ignore
	public void do_save() {
		SearchVO search = new SearchVO();
		search.setSearchWord("테스트");
		List<KYMQnaVO> getIdList = (List<KYMQnaVO>) kymQnaDaoImpl.do_boardIdList(search);
		
		for(KYMQnaVO vo : getIdList) {
			kymQnaDaoImpl.do_delete(vo);
		}
		
		for(KYMQnaVO vo : list) {
			int flag = kymQnaDaoImpl.do_save(vo);
			assertThat(1, is(1));
		}
		
	}
	
	@Test
	@Ignore
	public void do_delete() {
		for(KYMQnaVO vo : list) {
			kymQnaDaoImpl.do_delete(vo);
			
		}
		
	}
	
	@Test
	public void getBean() {
		LOG.debug("===============");
		LOG.debug("context"+context);
		LOG.debug("boardAttrDaoImpl"+kymQnaDaoImpl);
		LOG.debug("===============");
		
		assertThat(context, is(notNullValue()));
		assertThat(kymQnaDaoImpl, is(notNullValue()));
		
	}
	
	@After
	public void tearDown() {
		
	}

}
