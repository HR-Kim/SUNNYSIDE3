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

import kr.co.sunnyside.cmn.SearchVO;
import kr.co.sunnyside.cmn.StringUtil;
import kr.co.sunnyside.movie.service.LHJ_BoxofficeVO;
import kr.co.sunnyside.movie.service.LHJ_MovieVO;
import kr.co.sunnyside.movie.service.impl.LHJ_MovieDaoImpl;
import kr.co.sunnyside.store.service.SEJ_StroreVO;
import kr.co.sunnyside.store.service.impl.SEJ_StroreDaoImpl;

import kr.co.sunnyside.movie.service.impl.LHJ_MovieParsing;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations= {"file:src/main/webapp/WEB-INF/spring/**/*.xml"}) 
@FixMethodOrder(MethodSorters.NAME_ASCENDING) //테스트 이름 알파벳 순으로 수행한다.
public class LHJ_MovieDaoTest {
	private final static Logger LOG = LoggerFactory.getLogger(LHJ_MovieDaoTest.class);
	
	@Autowired
	private WebApplicationContext context;
	
	@Autowired
	private LHJ_MovieDaoImpl movieDaoImpl;
	
	List<LHJ_MovieVO> list = new ArrayList<LHJ_MovieVO>();

	@Before
	public void setUp() throws IOException, ParseException{
		list = Arrays.asList(
				 new LHJ_MovieVO("F48336","조커","","","","2019-10-02","","",0,"","",0.0,0.0,"000","0","01")
				,new LHJ_MovieVO("K20477","가장","","","","2019-10-02","","",0,"","",0.0,0.0,"000","0","02")
				,new LHJ_MovieVO("K21186","퍼펙트맨","","","","2019-10-02","","",0,"","",0.0,0.0,"000","0","03")
				,new LHJ_MovieVO("K18372","장사리 ","","","","2019-9-25","","",0,"","",0.0,0.0,"000","0","04")
				,new LHJ_MovieVO("F48958","소피와","","","","2019-10-02","","",0,"","",0.0,0.0,"000","0","05")
				,new LHJ_MovieVO("F49317","몬스터","","","","2019-10-03","","",0,"","",0.0,0.0,"000","0","06")
				,new LHJ_MovieVO("K21060","양자물리학","","","","2019-09-25","","",0,"","",0.0,0.0,"000","0","07")
				,new LHJ_MovieVO("K21094","나쁜","","","","2019-09-11","","",0,"","",0.0,0.0,"000","0","08")
				,new LHJ_MovieVO("F48214","원스","","","","2019-09-25","","",0,"","",0.0,0.0,"000","0","09")
				,new LHJ_MovieVO("F48401","47미터","","","","2019-08-28","","",0,"","",0.0,0.0,"000","0","10")
				,new LHJ_MovieVO("A05778","꼼:수행","","","","2019-08-28","","",0,"","",0.0,0.0,"000","0","10")
		);			
	}	 
	
	//이거 테스트 해보기
	@Test
	@Ignore
	public void do_visitorRate_update() {
//		for(LHJ_MovieVO vo:list) {
			int flag = movieDaoImpl.do_visitorRate_update(list.get(2));
			assertThat(1, is(flag));
//		}
	}
	
	/**전체조회*/
	@Test
	@Ignore
	public void do_retrieve() {
		SearchVO search = new SearchVO();
		search.setPageSize(10);
		search.setPageNum(1);
		search.setSearchDiv("10");
		search.setSearchWord("코난");
		
		List<LHJ_MovieVO> list = (List<LHJ_MovieVO>) movieDaoImpl.do_retrieve(search);
//		assertThat(10, is(list.size()));		
	}
	
	
	/**단건조회*/
	@Test
	@Ignore
	public void do_selectOne() {
		movieDaoImpl.do_selectOne(list.get(0));
	}
	
	/** 영화등록 */
	@Test
	@Ignore
	public void do_save() {
		for(LHJ_MovieVO vo:list) {
			int flag = movieDaoImpl.do_save(vo);
			assertThat(1, is(flag));
		}
	}
	
	//데이터베이스에 파싱한 kmdb데이터 삽입.
	//실행금지
	@Test
	@Ignore
	public void do_movie_insert() {
		URL url;
		List<LHJ_MovieVO> list = new ArrayList<LHJ_MovieVO>();				
//		for(int i=0; i<74300; i++) {								
		for(int i=0; i<10; i++) {								
			try {
				url = new URL(LHJ_MovieParsing.KmdbUrl(i));//url
				list=LHJ_MovieParsing.getKmdbData(url);//데이터를 List
				movieDaoImpl.do_save(list.get(0));//담아온 정보를 insert	
			} catch (Exception e) {
				LOG.debug("============================");
				LOG.debug("Exception:"+e.toString());
				LOG.debug("============================");
				continue; 
			}	
		}		
	} 
	
	@Test
	@Ignore
	public void getBean() {
		LOG.debug("======================");
		LOG.debug("context="+context);
		LOG.debug("codeDaoImpl="+movieDaoImpl);
		LOG.debug("======================");
		assertThat(context, is(notNullValue()));
		assertThat(movieDaoImpl, is(notNullValue()));
	}
	
	@After
	public void tearDown() {
		
	}
	
}
