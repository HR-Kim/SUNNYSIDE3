package kr.co.sunnyside.notice.test;

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
import kr.co.sunnyside.notice.service.KYMNoticeVO;
import kr.co.sunnyside.notice.service.impl.KYMNoticeDaoImpl;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/**/*.xml"})
@FixMethodOrder(MethodSorters.NAME_ASCENDING)//@Test NAME_ASCENDING으로 수행.
public class KYM_ServiceNoticeTest {
	
private final Logger LOG = LoggerFactory.getLogger(this.getClass());	
	
	@Autowired
	private WebApplicationContext context;
	
	@Autowired
	private KYMNoticeDaoImpl kymCenterCenterDaoImpl;
	
	List<KYMNoticeVO> list;
	
	@Before
	public void setUp() {
		list = Arrays.asList(
				 new KYMNoticeVO("공지1","공지 제목1","파일1","파일11","xls","공지게시판1","테스트1","","","","")
				,new KYMNoticeVO("공지2","공지 제목2","파일2","파일22","xls","공지게시판2","테스트2","","","","")
				,new KYMNoticeVO("공지3","공지 제목3","파일3","파일33","xls","공지게시판3","테스트3","","","","")
				,new KYMNoticeVO("공지4","공지 제목4","파일4","파일44","xls","공지게시판4","테스트4","","","","")
				,new KYMNoticeVO("공지5","공지 제목5","파일5","파일55","xls","공지게시판5","테스트5","","","","")
				
		);
		
	}
	
	@Test
	//@Ignore
	public void do_retrieve() {
		//수정할 데이터 검색
		SearchVO search=new SearchVO();
		search.setSearchWord("공지");
		List<KYMNoticeVO> getIdList = (List<KYMNoticeVO>) kymCenterCenterDaoImpl.do_boardIdList(search);
		
		//데이터 삭제
		for(KYMNoticeVO vo:getIdList) {
			kymCenterCenterDaoImpl.do_delete(vo);
		}
		
		//데이터 저장
		for(KYMNoticeVO vo:list) {
			int flag = kymCenterCenterDaoImpl.do_save(vo);
			assertThat(1, is(1));
		}
		
		//데이터 조회
		search.setSearchDiv("10");
		search.setPageSize(10);
		search.setPageNum(1);
		
		List<KYMNoticeVO> list = (List<KYMNoticeVO>) kymCenterCenterDaoImpl.do_retrieve(search);
		assertThat(5, is(list.size()));
		
	}
	
	@Test
	//@Ignore
	public void do_update() {
		//수정할 데이터 검색
		SearchVO search=new SearchVO();
		search.setSearchWord("공지");
		List<KYMNoticeVO> getIdList = (List<KYMNoticeVO>) kymCenterCenterDaoImpl.do_boardIdList(search);
		
		//데이터 삭제
		for(KYMNoticeVO vo:getIdList) {
			kymCenterCenterDaoImpl.do_delete(vo);
		}
		
		//데이터 저장
		for(KYMNoticeVO vo:list) {
			int flag = kymCenterCenterDaoImpl.do_save(vo);
			assertThat(1, is(1));
		}
		
		//데이터 조회
		getIdList = (List<KYMNoticeVO>) kymCenterCenterDaoImpl.do_boardIdList(search);
		assertThat(5, is(getIdList.size()));
		
		//데이터 수정
		KYMNoticeVO kymCenterVO = getIdList.get(0);
		kymCenterVO.setTitle(kymCenterVO.getTitle()+"_U");
		kymCenterVO.setContents(kymCenterVO.getContents()+"_U");
		
		int flag = kymCenterCenterDaoImpl.do_update(kymCenterVO);
		assertThat(flag, is(1));
		
		//데이터 비교
		getIdList = (List<KYMNoticeVO>) kymCenterCenterDaoImpl.do_boardIdList(search);
		checkData(kymCenterVO, getIdList.get(0));
		
	}
	
	@Test
	//@Ignore
	public void addAndGet() {
		SearchVO search=new SearchVO();
		search.setSearchWord("공지");
		List<KYMNoticeVO> getIdList = (List<KYMNoticeVO>) kymCenterCenterDaoImpl.do_boardIdList(search);
			
		for(KYMNoticeVO vo:getIdList) {
			kymCenterCenterDaoImpl.do_delete(vo);
		}

		for(KYMNoticeVO vo:list) {
			int flag = kymCenterCenterDaoImpl.do_save(vo);
			assertThat(1, is(1));
		}		

		getIdList = (List<KYMNoticeVO>) kymCenterCenterDaoImpl.do_boardIdList(search);
		assertThat(5, is(getIdList.size()));
		
		for(int i=0;i<list.size();i++) {
			checkData(list.get(i),getIdList.get(i));
			
		}
		
	}
	
	private void checkData(KYMNoticeVO org, KYMNoticeVO vs) {
		assertThat(org.getTitle(), is(vs.getTitle()));
		assertThat(org.getContents(), is(vs.getContents()));
		
	}
	
	@Test
	//@Ignore
	public void do_save() {
		SearchVO search = new SearchVO();
		search.setSearchWord("공지");
		List<KYMNoticeVO> getIdList = (List<KYMNoticeVO>) kymCenterCenterDaoImpl.do_boardIdList(search);
		
		for(KYMNoticeVO vo : getIdList) {
			kymCenterCenterDaoImpl.do_delete(vo);
		}
		
		for(KYMNoticeVO vo : list) {
			int flag = kymCenterCenterDaoImpl.do_save(vo);
			assertThat(1, is(1));
		}
		
	}
	
	@Test
	@Ignore
	public void do_delete() {
		for(KYMNoticeVO vo : list) {
			kymCenterCenterDaoImpl.do_delete(vo);
			
		}
		
	}
	
	@Test
	public void getBean() {
		LOG.debug("===============");
		LOG.debug("context"+context);
		LOG.debug("boardAttrDaoImpl"+kymCenterCenterDaoImpl);
		LOG.debug("===============");
		
		assertThat(context, is(notNullValue()));
		assertThat(kymCenterCenterDaoImpl, is(notNullValue()));
		
	}
	
	@After
	public void tearDown() {
		
	}

}
