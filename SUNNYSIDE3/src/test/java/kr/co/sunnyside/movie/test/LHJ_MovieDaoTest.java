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
	
//	List<LHJ_MovieVO> kmdbList = new ArrayList<LHJ_MovieVO>();
	List<LHJ_MovieVO> list = new ArrayList<LHJ_MovieVO>();

	@Before
	public void setUp() throws IOException, ParseException{
		list = Arrays.asList(
				 new LHJ_MovieVO("000001","영화이름01","mn01","전체관람가","판타지","2019-10-01","감독01","배우01",90,"내용01","",0.0,0.0,"000","0","01")
				,new LHJ_MovieVO("000002","영화이름02","mn02","전체관람가","판타지","2019-10-02","감독02","배우02",90,"내용02","",0.0,0.0,"000","0","02")
				,new LHJ_MovieVO("000003","영화이름03","mn03","전체관람가","판타지","2019-10-03","감독03","배우03",90,"내용03","",0.0,0.0,"000","0","03")
				,new LHJ_MovieVO("000004","영화이름04","mn04","전체관람가","판타지","2019-10-04","감독04","배우04",90,"내용04","",0.0,0.0,"000","0","04")
				,new LHJ_MovieVO("000005","영화이름05","mn05","전체관람가","판타지","2019-10-05","감독05","배우05",90,"내용05","",0.0,0.0,"000","0","05")		
		);		
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
