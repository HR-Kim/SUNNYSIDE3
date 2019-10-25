package kr.co.sunnyside.movie.service.impl;

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
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import kr.co.sunnyside.movie.service.LHJ_MovieVO;

public class LHJ_MovieParsing {
	private final static Logger LOG = LoggerFactory.getLogger(LHJ_MovieParsing.class);
	
	//특정 영화명, 날짜로 영화정보 받아오기
	public static List<LHJ_MovieVO> getMovieSearchList(String title, String date) {
		List<LHJ_MovieVO> list = new ArrayList<LHJ_MovieVO>();
		
//		title = title.replace(" ", "%20");
		
		title = title.substring(0, title.indexOf(" "));
		try {
			URL url = new URL(KmdbUrlTitle(title, date));//url
			list=getKmdbData(url);//데이터를 List형태로 반환
			LOG.debug("==여기~~~=====================");
			LOG.debug("url:"+url);
			LOG.debug("list:"+list);
			LOG.debug("============================");
		} catch (Exception e) {
			LOG.debug("============================");
			LOG.debug("Exception:"+e.toString());
			LOG.debug("============================");
		}
		
		
		return list;
	}
	
	public static List<LHJ_MovieVO> getBoxofficeList() {
		List<LHJ_MovieVO> kobisList = new ArrayList<LHJ_MovieVO>();
		try {
			URL url = new URL(LHJ_MovieParsing.kobisUrl());//url
			kobisList=LHJ_MovieParsing.getKobisData(url);//데이터를 List형태로 반환
		} catch (Exception e) {
			LOG.debug("============================");
			LOG.debug("Exception:"+e.toString());
			LOG.debug("============================");
		}
		return kobisList;
	}
	
	//kobis파싱 url 설정
	public static String kobisUrl() throws IOException, ParseException{
		Calendar cal = new GregorianCalendar();
		cal.add(Calendar.DATE, -1);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String yesterdayDate = sdf.format(cal.getTime());
		
		/*URL*/ 
		StringBuilder urlBuilder = new StringBuilder("http://www.kobis.or.kr/kobisopenapi/webservice/rest/boxoffice/searchDailyBoxOfficeList.json"); 
		/*key(발급받은키 값)*/ 
		urlBuilder.append("?" + URLEncoder.encode("key","UTF-8") + "=" + "5d8a4695de3453f20f2dfa2e34dad196");
		/*targetDt(조회하고자 하는 날짜를 yyyymmdd 형식으로 입력)*/ 
		urlBuilder.append("&" + URLEncoder.encode("targetDt","UTF-8") + "=" + yesterdayDate);
		/*itemPerPage(결과 ROW 의 개수를 지정)*/ 
		urlBuilder.append("&" + URLEncoder.encode("itemPerPage","UTF-8") + "=" + "10");
		

		return urlBuilder.toString();
	}
	
	//kobis데이터 파싱
	public static List<LHJ_MovieVO> getKobisData(URL url) throws ParseException {
		List<LHJ_MovieVO> dataList =  new ArrayList<LHJ_MovieVO>();
		BufferedReader rd	   = null;
		HttpURLConnection conn = null;
		JsonParser jsonParser  = new JsonParser();
		
		try{
			//커넥션 생성
			conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Content-type", "application/json");
			
			//데이터 읽어오기
			StringBuilder sb = new StringBuilder();
			String line;
			rd = new BufferedReader(new InputStreamReader(conn.getInputStream())); 
			
			//null이 아니면 한 줄을 읽어서 sb에 덧붙인다.
			while ((line = rd.readLine()) != null) { 
				sb.append(line); 
			} 
			
			//읽어 온 데이터를 Json오브젝트로 만든다.
			JsonObject jsonObject = (JsonObject) jsonParser.parse(sb.toString());			
			
			//"boxOfficeResult"를 오브젝트로 만든다.
			JsonObject resultObject = (JsonObject) jsonObject.get("boxOfficeResult");
			
			JsonArray dailyArray = (JsonArray) resultObject.get("dailyBoxOfficeList");
			
			//반복문으로 정보 꺼내기. dailyArray의 길이(10개)만큼
			for(int i=0; i<dailyArray.size(); i++){	
				JsonObject dailyObj = (JsonObject) dailyArray.get(i);		
				
				String rank = dailyObj.get("rank").getAsString();
				if(rank.length()<2) {
					rank = "0"+rank;
				}
				String movieNm = dailyObj.get("movieNm").getAsString();
//				movieNm = movieNm.split(" ")[0].trim();
				String openDt = dailyObj.get("openDt").getAsString();
				openDt = openDt.replace("-", "").trim();				
				
				LHJ_MovieVO vo = new LHJ_MovieVO();
				vo.setKortitle(movieNm);
				vo.setRelDate(openDt);
				vo.setMovieRank(rank);
				
				
				dataList.add(vo);
			}
			
		}catch(IOException e){
			LOG.debug("====================================");
			LOG.debug("IOException:"+e.getMessage());
			LOG.debug("====================================");
		}finally{
			//자원 반납
			try {
				rd.close();
				conn.disconnect();
			} catch (IOException e) {
				LOG.debug("====================================");
				LOG.debug("IOException:"+e.getMessage());
				LOG.debug("====================================");
			} 
		}
		return dataList;
	}
	
	
	
	//Kmdb 파싱 url 설정 : 제목으로 검색하기
	public static String KmdbUrlTitle(String title, String date) throws IOException, ParseException{
		/*URL*/ 
		StringBuilder urlBuilder = new StringBuilder("http://api.koreafilm.or.kr/openapi-data2/wisenut/search_api/search_json.jsp"); 
		/*collection(검색 대상 컬렉션 지정)*/ 
		urlBuilder.append("?" + URLEncoder.encode("collection","UTF-8") + "=" + "kmdb_new");
		/*Service Key(API 서비스 인증키	)*/ 
		urlBuilder.append("&" + URLEncoder.encode("ServiceKey","UTF-8") + "=" + "3PHZT44D63RX801282VJ");
		/*detail(상세정보 출력여부)*/ 
		urlBuilder.append("&" + URLEncoder.encode("detail","UTF-8") + "=" + "Y");
		/*title(영화명)*/ 
		urlBuilder.append("&" + URLEncoder.encode("title","UTF-8") + "=\"" + title +"\"");
//		/*releaseDts(개봉일 시작)*/ 
//		urlBuilder.append("&" + URLEncoder.encode("releaseDts","UTF-8") + "=" + date);
//		/*releaseDte(개봉일 종료)*/ 
//		urlBuilder.append("&" + URLEncoder.encode("releaseDte","UTF-8") + "=" + date);
		
		return urlBuilder.toString();
	}
	
	//Kmdb 파싱 url 설정
	public static String KmdbUrl(int i) throws IOException, ParseException{
		/*URL*/ 
		StringBuilder urlBuilder = new StringBuilder("http://api.koreafilm.or.kr/openapi-data2/wisenut/search_api/search_json.jsp"); 
		/*collection(검색 대상 컬렉션 지정)*/ 
		urlBuilder.append("?" + URLEncoder.encode("collection","UTF-8") + "=" + "kmdb_new");
		/*Service Key(API 서비스 인증키	)*/ 
		urlBuilder.append("&" + URLEncoder.encode("ServiceKey","UTF-8") + "=" + "3PHZT44D63RX801282VJ");
		/*detail(상세정보 출력여부)*/ 
		urlBuilder.append("&" + URLEncoder.encode("detail","UTF-8") + "=" + "Y");
		/*startCount(검색 결과 시작 번호)*/ 
		urlBuilder.append("&" + URLEncoder.encode("startCount","UTF-8") + "=" + i);

		return urlBuilder.toString();
	}
		
	//Kmdb 데이터 파싱
	public static List<LHJ_MovieVO> getKmdbData(URL url) throws ParseException, MalformedURLException {
		List<LHJ_MovieVO> dataList =  new ArrayList<LHJ_MovieVO>();
		BufferedReader rd	   = null;
		HttpURLConnection conn = null;
		JsonParser jsonParser  = new JsonParser();
		
		try{
			//커넥션 생성
			conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Content-type", "application/json");
			
			//데이터 읽어오기
			StringBuilder sb = new StringBuilder();
			String line;
			rd = new BufferedReader(new InputStreamReader(conn.getInputStream())); 
			
			//null이 아니면 한 줄을 읽어서 sb에 덧붙인다.
			while ((line = rd.readLine()) != null) { 
				sb.append(line); 
			} 
			
			//읽어 온 데이터를 Json오브젝트로 만든다.
			JsonObject jsonObject = (JsonObject) jsonParser.parse(sb.toString());
			
			//"data"를 파싱
			JsonArray dataArray = (JsonArray) jsonObject.get("Data");
		
			//반복문으로 모든 정보 꺼내기. n개의 영화의 정보
			for(int i=0; i<dataArray.size(); i++){
				JsonObject dataObj = (JsonObject) dataArray.get(i);
				JsonArray resultArray = dataObj.get("Result").getAsJsonArray();
				
				for(int j=0; j<resultArray.size(); j++){
					//파싱한 데이터를 담을 vo 생성
					LHJ_MovieVO vo = new LHJ_MovieVO();
					
					//"result"를 파싱
					JsonObject resultObj = (JsonObject) resultArray.get(j);					
					String movieId = resultObj.get("DOCID").getAsString();			//movieId				
					String title = resultObj.get("title").getAsString();			//제목	
					title = deleteHsHe(title);
					
					String titleEng = resultObj.get("titleEng").getAsString();		//영제
					
					JsonArray director = resultObj.get("director").getAsJsonArray();
					String directorNm = arrayToString(director, "directorNm");		//감독이름
					
					JsonArray actor = resultObj.get("actor").getAsJsonArray();			
					String actorNm = arrayToString(actor, "actorNm");				//배우이름	
					
					String plot = resultObj.get("plot").getAsString();				//줄거리	
					String runtimeStr = resultObj.get("runtime").getAsString();
					int runtime = 0;
					if(runtimeStr != null && !runtimeStr.equals("")) {
						runtime = Integer.parseInt(runtimeStr);	//상영시간	
					}else {
						
					}
					
					JsonArray rating = resultObj.get("rating").getAsJsonArray();
					JsonObject ratingObj = (JsonObject) rating.get(0);
					String ratingGrade = ratingObj.get("ratingGrade").getAsString();//관람등급	
					String releaseDate = ratingObj.get("releaseDate").getAsString();//개봉일자
					
					String genre = resultObj.get("genre").getAsString();			//장르
					String posters = resultObj.get("posters").getAsString();		//포스터이미지	
					if(posters.contains("|")) { //포스터가 여러장일 경우 메인 포스터(첫번째 포스터)만 가져온다.
						posters = posters.substring(0,  posters.indexOf('|'));	
					}
					
					//vo에 가져온 정보들을 set하기
					vo.setMovieId(movieId);
					vo.setKortitle(title);
					vo.setEngtitle(titleEng);				
					vo.setDirector(directorNm);	
					vo.setCast(actorNm);				
					vo.setSynopsis(plot);
					vo.setRunningTime(runtime);
					vo.setLimitage(ratingGrade);
					vo.setGenre(genre);
					vo.setRelDate(releaseDate);
					vo.setPoster(posters);
					
					//예고편, 전문가 평점, 필름타입
						
					//vo를 리스트에 추가
					dataList.add(vo);
				}
			}
			
		}catch(IOException e){
			LOG.debug("====================================");
			LOG.debug("IOException:"+e.getMessage());
			LOG.debug("====================================");
		}finally{
			//자원 반납
			try {
				rd.close();
				conn.disconnect();
			} catch (IOException e) {
				LOG.debug("====================================");
				LOG.debug("IOException:"+e.getMessage());
				LOG.debug("====================================");
			} 
		}
		return dataList;
	}
	
	//Kmdb : 영화에 부제가 있는 경우 !HS, !HE가 삽입되므로 해당 부분을 없애고 다중 공백을 하나의 공백으로 치환하는 메소드
	public static String deleteHsHe(String str) {
		str = str.replace("!HS", "");
		str = str.replace("!HE", "");
		str = str.trim().replaceAll(" +", " ");
		return str;
	}
	
	//Kmdb : 배열 안의 배열을 새 오브젝트로 만들어 파싱해야 하는데, 기능이 중복되므로 메소드로 만듬
	public static String arrayToString (JsonArray jsonArray, String objNm) {
		String str = "";
		for(int i=0; i<jsonArray.size(); i++){
			JsonObject directorObj = (JsonObject) jsonArray.get(i);
			if(i<jsonArray.size()-1) {
				str = str + directorObj.get(objNm).getAsString() +",";
			}else {
				str = str + directorObj.get(objNm).getAsString();
			}
		}
		return str;
	}

}
