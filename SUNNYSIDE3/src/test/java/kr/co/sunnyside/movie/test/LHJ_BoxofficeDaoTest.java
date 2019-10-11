package kr.co.sunnyside.movie.test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
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

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import kr.co.sunnyside.cmn.StringUtil;
import kr.co.sunnyside.movie.service.LHJ_BoxofficeVO;
import kr.co.sunnyside.movie.service.LHJ_MovieVO;
import kr.co.sunnyside.movie.service.impl.LHJ_BoxofficeDaoImpl;
import kr.co.sunnyside.store.service.SEJ_StroreVO;
import kr.co.sunnyside.store.service.impl.SEJ_StroreDaoImpl;

import kr.co.sunnyside.movie.service.impl.LHJ_MovieParsing;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations= {"file:src/main/webapp/WEB-INF/spring/**/*.xml"}) 
@FixMethodOrder(MethodSorters.NAME_ASCENDING) //테스트 이름 알파벳 순으로 수행한다.
public class LHJ_BoxofficeDaoTest {
	private final static Logger LOG = LoggerFactory.getLogger(LHJ_BoxofficeDaoTest.class);
	
	@Autowired
	private WebApplicationContext context;
	
	@Autowired
	private LHJ_BoxofficeDaoImpl boxofficeDaoImpl;
	
	List<LHJ_MovieVO> testlist = new ArrayList<LHJ_MovieVO>();

	List<LHJ_MovieVO> kobisList = new ArrayList<LHJ_MovieVO>();
	
	URL url;
	
	@Before
	public void setUp() throws IOException, ParseException{
		testlist = Arrays.asList(
				new LHJ_MovieVO("F48336","조커","","","","2019-10-02","","",0,"","",0.0,0.0,"","","01")
				,new LHJ_MovieVO("K20477","가장","","","","2019-10-02","","",0,"","",0.0,0.0,"","","02")
				,new LHJ_MovieVO("K21186","퍼펙트맨","","","","2019-10-02","","",0,"","",0.0,0.0,"","","03")
				,new LHJ_MovieVO("K18372","장사리 ","","","","2019-9-25","","",0,"","",0.0,0.0,"","","04")
				,new LHJ_MovieVO("F48958","소피와","","","","2019-10-02","","",0,"","",0.0,0.0,"","","05")
				,new LHJ_MovieVO("F49317","몬스터","","","","2019-10-03","","",0,"","",0.0,0.0,"","","06")
				,new LHJ_MovieVO("K21060","양자물리학","","","","2019-09-25","","",0,"","",0.0,0.0,"","","07")
				,new LHJ_MovieVO("K21094","나쁜","","","","2019-09-11","","",0,"","",0.0,0.0,"","","08")
				,new LHJ_MovieVO("F48214","원스","","","","2019-09-25","","",0,"","",0.0,0.0,"","","09")
				,new LHJ_MovieVO("F48401","47미터","","","","2019-08-28","","",0,"","",0.0,0.0,"","","10")
		);		
		
		kobisList = LHJ_MovieParsing.getBoxofficeList();
//		try {
//			url = new URL(LHJ_MovieParsing.kobisUrl());//url
//			kobisList=LHJ_MovieParsing.getKobisData(url);//데이터를 List형태로 반환
//		} catch (Exception e) {
//			LOG.debug("============================");
//			LOG.debug("Exception:"+e.toString());
//			LOG.debug("============================");
//		}
	}
	
	/**전체 테스트*/
	//박스오피스 데이터를 삭제, 등록, 조회, 순위 업데이트 하는 테스트
	@Test
//	@Ignore
	public void allTest() {
		int flag = 0;
		//박스오피스 상태값 0으로 초기화(OFF)
		flag = boxofficeDaoImpl.do_boxofficeOff_update();
		assertThat(flag, is(10)); //10건삭제
		
		//박스오피스 상태값 1으로 초기화(ON)
		for(LHJ_MovieVO vo : kobisList) {
			flag = boxofficeDaoImpl.do_boxofficeOn_update(vo);
			assertThat(flag, is(1));
		}
		
		//박스오피스에 순위정보 delete
		flag = boxofficeDaoImpl.do_delete();
		assertThat(flag, is(10)); //10건삭제
		
		//박스오피스에 순위정보 insert
		for(LHJ_MovieVO vo : kobisList) {
			flag = boxofficeDaoImpl.do_save(vo);
			assertThat(flag, is(1));
		}

		//단건 조회
		LHJ_MovieVO vo = (LHJ_MovieVO) boxofficeDaoImpl.do_selectOne(testlist.get(0));
		
		//전체조회
		List<LHJ_MovieVO> retrieveList = new ArrayList<LHJ_MovieVO>();
		retrieveList = (List<LHJ_MovieVO>) boxofficeDaoImpl.do_retrieve();
		assertThat(10, is(retrieveList.size()));
	}
	
	
	//-----------------------------------------------------------------------------------------------
	
	
	//박스오피스 전체조회
	@Test
	@Ignore
	public void do_retrieve() {
		List<LHJ_MovieVO> list = (List<LHJ_MovieVO>) boxofficeDaoImpl.do_retrieve();
		assertThat(10, is(list.size()));
		
	}
	
	//박스오피스 단건조회
	@Test
	@Ignore
	public void do_selectOne()  {
		LHJ_MovieVO vo = (LHJ_MovieVO) boxofficeDaoImpl.do_selectOne(testlist.get(0));
	}
	
	//박스오피스 상태 업데이트(OFF)
	@Test
	@Ignore
	public void do_boxofficeOff_update()  {
		int flag = boxofficeDaoImpl.do_boxofficeOff_update();
		assertThat(flag, is(10));
	}

	//박스오피스 상태 업데이트(ON)
	@Test
	@Ignore
	public void do_boxofficeOn_update()  {
		for(LHJ_MovieVO vo : kobisList) {
			int flag = boxofficeDaoImpl.do_boxofficeOn_update(vo);
			assertThat(flag, is(1));
		}
	}	
	
	//박스오피스에 순위정보 delete
	@Test
	@Ignore
	public void do_delete()  {
		int flag = boxofficeDaoImpl.do_delete();
		assertThat(flag, is(10)); //10건삭제
	}
	
	//박스오피스에 순위정보 insert
	@Test
	@Ignore
	public void do_save()  {
		for(LHJ_MovieVO vo : kobisList) {
			int flag = boxofficeDaoImpl.do_save(vo);
			assertThat(flag, is(1));
		}
	}
	//박스오피스 순위 업데이트
//	@Test
//	@Ignore
//	public void do_rank_update()  {
//		URL url;		
//		try {
//			int i=0;
//			url = new URL(LHJ_MovieParsing.kobisUrl());//url
//			List<LHJ_BoxofficeVO> list=LHJ_MovieParsing.getKobisData(url);//데이터를 List형태로 반환
//			for(LHJ_BoxofficeVO vo : list) { //받아온 데이터				
//				LHJ_BoxofficeVO idVO = (LHJ_BoxofficeVO) boxofficeDaoImpl.do_selectOne(testlist.get(i)); //movieId를 받아옴
//				String movieId = idVO.getMovieId();//movieId를 변수에 담음
//				list.get(i).setMovieId(movieId);//list(받아온 데이터)에 movieId를 넣음
//				int flag = boxofficeDaoImpl.do_save(vo);//list의 vo를 넘긴다. 여기에는 rank정보와 movieId가 담겨있음
//				assertThat(flag, is(1));
//				i++;
//			}
//		} catch (Exception e) {
//			LOG.debug("============================");
//			LOG.debug("Exception:"+e.toString());
//			LOG.debug("============================");
//		}
//	}
	
//	//박스오피스 저장
//	@Test
//	@Ignore
//	public void do_save()  {
//		for(LHJ_BoxofficeVO vo : testlist) {
//			int flag = boxofficeDaoImpl.do_save(vo);
//			assertThat(flag, is(1));
//		}
//	}
	
	//--------------------------------------------------------------------------------------------------------
	
	//데이터베이스에 파싱한 kobis데이터 삽입.
//	@Test
//	@Ignore
//	public void do_boxoffice_parsing() {
//		URL url;
//		List<LHJ_MovieVO> list = new ArrayList<LHJ_MovieVO>();
//		try {
//			url = new URL(LHJ_MovieParsing.kobisUrl());//url
//			list=LHJ_MovieParsing.getKobisData(url);//데이터를 List형태로 반환
//			for(LHJ_MovieVO vo : list) { //받아온 데이터를 insert
//				int flag = boxofficeDaoImpl.do_save(vo);
//				assertThat(flag, is(1));
//			}
//		} catch (Exception e) {
//			LOG.debug("============================");
//			LOG.debug("Exception:"+e.toString());
//			LOG.debug("============================");
//		}
//	}
	
	
	@Test
	@Ignore
	public void getBean() {
		LOG.debug("======================");
		LOG.debug("context="+context);
		LOG.debug("boxofficeDaoImpl="+boxofficeDaoImpl);
		LOG.debug("======================");
		assertThat(context, is(notNullValue()));
		assertThat(boxofficeDaoImpl, is(notNullValue()));
	}
	
	@After
	public void tearDown() {
		
	}
	
}
