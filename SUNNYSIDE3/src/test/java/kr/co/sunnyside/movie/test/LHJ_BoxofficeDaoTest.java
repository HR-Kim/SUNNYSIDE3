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
	
	List<LHJ_BoxofficeVO> kobisList = new ArrayList<LHJ_BoxofficeVO>();

	@Before
	public void setUp() throws IOException, ParseException{
		kobisList = Arrays.asList(
				 new LHJ_BoxofficeVO("K21186","조커","","","","2019-10-02","","",0,"","",0.0,0.0,"1")
				,new LHJ_BoxofficeVO("F48336","가장","","","","2019-10-02","","",0,"","",0.0,0.0,"2")
				,new LHJ_BoxofficeVO("K20477","퍼펙트맨","","","","2019-10-02","","",0,"","",0.0,0.0,"3")
				,new LHJ_BoxofficeVO("K18372","장사리 ","","","","2019-9-25","","",0,"","",0.0,0.0,"4")
				,new LHJ_BoxofficeVO("F48958","소피와","","","","2019-10-02","","",0,"","",0.0,0.0,"5")
				,new LHJ_BoxofficeVO("F49317","몬스터","","","","2019-10-03","","",0,"","",0.0,0.0,"6")
				,new LHJ_BoxofficeVO("K21060","양자물리학","","","","2019-09-25","","",0,"","",0.0,0.0,"7")
				,new LHJ_BoxofficeVO("F48214","나쁜","","","","2019-09-11","","",0,"","",0.0,0.0,"8")
				,new LHJ_BoxofficeVO("K21094","원스","","","","2019-09-25","","",0,"","",0.0,0.0,"9")
				,new LHJ_BoxofficeVO("F48401","47미터","","","","2019-08-28","","",0,"","",0.0,0.0,"10")
		);		
	}
	
	@Test
	public void addAndGet() {
		//삭제
		int flag = boxofficeDaoImpl.do_delete();
		assertThat(flag, is(10)); //10건삭제
		
		//등록
		URL url;
		List<LHJ_BoxofficeVO> list = new ArrayList<LHJ_BoxofficeVO>();
		try {
			url = new URL(LHJ_MovieParsing.kobisUrl());//url
			list=LHJ_MovieParsing.getKobisData(url);//데이터를 List형태로 반환
			for(LHJ_BoxofficeVO vo : list) { //받아온 데이터를 insert
				flag = boxofficeDaoImpl.do_save(vo);
				assertThat(flag, is(1));
			}
		} catch (Exception e) {
			LOG.debug("============================");
			LOG.debug("Exception:"+e.toString());
			LOG.debug("============================");
		}
		
		//순위 업데이트
		try {
			int i=0;
			url = new URL(LHJ_MovieParsing.kobisUrl());//url
			list=LHJ_MovieParsing.getKobisData(url);//데이터를 List형태로 반환
			for(LHJ_BoxofficeVO vo : list) { //받아온 데이터				
				LHJ_BoxofficeVO idVO = (LHJ_BoxofficeVO) boxofficeDaoImpl.get_selectOne(kobisList.get(i)); //movieId를 받아옴
				String movieId = idVO.getMovieId();//movieId를 변수에 담음
				list.get(i).setMovieId(movieId);//list(받아온 데이터)에 movieId를 넣음
				flag = boxofficeDaoImpl.do_rank_update(vo);//list의 vo를 넘긴다. 여기에는 rank정보와 movieId가 담겨있음
				assertThat(flag, is(1));
				i++;
			}
		} catch (Exception e) {
			LOG.debug("============================");
			LOG.debug("Exception:"+e.toString());
			LOG.debug("============================");
		}
		
		//전체조회
		list = (List<LHJ_BoxofficeVO>) boxofficeDaoImpl.get_retrieve();
		assertThat(10, is(list.size()));
	}
	
	
	//-----------------------------------------------------------------------------------------------
	
	
	//박스오피스 전체조회
	@Test
	@Ignore
	public void get_retrieve() {
		List<LHJ_BoxofficeVO> list = (List<LHJ_BoxofficeVO>) boxofficeDaoImpl.get_retrieve();
		assertThat(10, is(list.size()));
		
	}
	
	//박스오피스 순위 업데이트
	@Test
	@Ignore
	public void do_rank_update()  {
		URL url;		
		try {
			int i=0;
			url = new URL(LHJ_MovieParsing.kobisUrl());//url
			List<LHJ_BoxofficeVO> list=LHJ_MovieParsing.getKobisData(url);//데이터를 List형태로 반환
			for(LHJ_BoxofficeVO vo : list) { //받아온 데이터				
				LHJ_BoxofficeVO idVO = (LHJ_BoxofficeVO) boxofficeDaoImpl.get_selectOne(kobisList.get(i)); //movieId를 받아옴
				String movieId = idVO.getMovieId();//movieId를 변수에 담음
				list.get(i).setMovieId(movieId);//list(받아온 데이터)에 movieId를 넣음
				int flag = boxofficeDaoImpl.do_rank_update(vo);//list의 vo를 넘긴다. 여기에는 rank정보와 movieId가 담겨있음
				assertThat(flag, is(1));
				i++;
			}
		} catch (Exception e) {
			LOG.debug("============================");
			LOG.debug("Exception:"+e.toString());
			LOG.debug("============================");
		}
	}
	
	//박스오피스 단건조회
	@Test
	@Ignore
	public void do_boxoffice_selectOne()  {
		LHJ_BoxofficeVO vo = (LHJ_BoxofficeVO) boxofficeDaoImpl.get_selectOne(kobisList.get(0));
		String movieId = vo.getMovieId();
		LOG.debug("============================");
		LOG.debug("=movieId="+movieId);
		LOG.debug("============================");
	}
	
	//박스오피스 삭제
	@Test
	@Ignore
	public void do_boxoffice_delete()  {
		int flag = boxofficeDaoImpl.do_delete();
		assertThat(flag, is(10)); //10건삭제
	}
	
	//박스오피스 저장
	@Test
	@Ignore
	public void do_boxoffice_insert()  {
		for(LHJ_BoxofficeVO vo : kobisList) {
			int flag = boxofficeDaoImpl.do_save(vo);
			assertThat(flag, is(1));
		}
	}
	
	//--------------------------------------------------------------------------------------------------------
	
	//데이터베이스에 파싱한 kobis데이터 삽입.
	@Test
	@Ignore
	public void do_boxoffice_parsing() {
		URL url;
		List<LHJ_BoxofficeVO> list = new ArrayList<LHJ_BoxofficeVO>();
		try {
			url = new URL(LHJ_MovieParsing.kobisUrl());//url
			list=LHJ_MovieParsing.getKobisData(url);//데이터를 List형태로 반환
			for(LHJ_BoxofficeVO vo : list) { //받아온 데이터를 insert
				int flag = boxofficeDaoImpl.do_save(vo);
				assertThat(flag, is(1));
			}
		} catch (Exception e) {
			LOG.debug("============================");
			LOG.debug("Exception:"+e.toString());
			LOG.debug("============================");
		}
	}
	
	
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
