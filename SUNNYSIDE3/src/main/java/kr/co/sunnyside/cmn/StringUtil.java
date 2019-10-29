package kr.co.sunnyside.cmn;

import static kr.co.sunnyside.cmn.StringUtil.UPLOAD_ROOT;

import java.io.File;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.co.sunnyside.code.service.CodeVO;
import kr.co.sunnyside.notice.service.KYMBranchVO;
import kr.co.sunnyside.store.service.SEJ_CartVO;

public class StringUtil {
	private static Logger LOG = LoggerFactory.getLogger(StringUtil.class);

	// File Root 디렉토리
	public static final String UPLOAD_ROOT = "/usr/local/apache-tomcat-8.5.34/webapps/SUNNYSIDE3/resources/image"; //aws 사용했을 때 상품사진 저장 위치
	public static final String MAIN_IMAGE_ROOT = "/usr/local/apache-tomcat-8.5.34/webapps/SUNNYSIDE3/resources/img/banner";
	//public static final String UPLOAD_ROOT = "C:\\Users\\sist\\git\\SUNNYSIDE3\\SUNNYSIDE3\\src\\main\\webapp\\resources\\image";
//	public static final String MAIN_IMAGE_ROOT = "C:\\Users\\sist\\git\\SUNNYSIDE3\\SUNNYSIDE3\\src\\main\\webapp\\resources\\img\\banner";

	//payCode 자르기 메소드 (스토어)
	public static List<String> CutpayCode(SEJ_CartVO inVO) {
		int CutNumber = 15;  //결제코드 반복 수
		String payCode = inVO.getPayCode(); 
		LOG.debug("1.= source=" + payCode);
		
		int length = payCode.length(); //결제코드 길이
		LOG.debug("1.= length=" + length);
	  
		List<String> list = new ArrayList<String>();  //데이터길이(48) / 자르는갯수(16) + 5 
		LOG.debug("1.= list=" + list); 
		
		for(int i = 0; i <length; i+= CutNumber){
			if(i + CutNumber < length){
				   list.add(payCode.substring(i, i + CutNumber));
					LOG.debug("2.= list=" + list); 
			}else{
				   list.add(payCode.substring(i)); // 해당위치부터 나머지
					LOG.debug("2-1.= list=" + list); 
			}			
		}

		for(int i = 0; i < list.size(); i++){
			
			payCode =list.get(i);
			inVO.setPayCode(payCode);
			System.out.println(inVO);
	 		//--------------------------------
			//-1. 결제완료 테이블에 저장+카트 테이블 삭제
			//--------------------------------				
			System.out.println(list.get(i));
		}
		return list;
	}
	
	
	//ProductId 자르기 메소드 (스토어)
	public static List<String> CutProductId(SEJ_CartVO inVO) { //20191023-002-041   20191022-001-021
		int ProductIdCutNumber = 16; 
		String productId = inVO.getProductId();  
		LOG.debug("1.= source=" + productId);
		
		int ProductIdLength = productId.length();
		LOG.debug("1.= ProductIdLength=" + ProductIdLength);
      
		List<String> ProductIdList = new ArrayList<String>();  //데이터길이(48) / 자르는갯수(16) + 5 
		LOG.debug("1.= ProductIdList=" + ProductIdList); 
		
		for(int i = 0; i <ProductIdLength; i+= ProductIdCutNumber){
			if(i + ProductIdCutNumber < ProductIdLength){
				ProductIdList.add(productId.substring(i, i + ProductIdCutNumber));
					LOG.debug("2.= list=" + ProductIdList); 
			}else{
				ProductIdList.add(productId.substring(i)); // 해당위치부터 나머지
					LOG.debug("2-1.= list=" + ProductIdList); 
			}			
		}

		for(int i = 0; i < ProductIdList.size(); i++){			
			productId =ProductIdList.get(i);
			inVO.setProductId(productId);
			System.out.println(inVO);
	}
		return ProductIdList;
		
	}

	
	//count 자르기 메소드 (스토어)
	public static List<String> Cutcount(SEJ_CartVO inVO) {
		String count =inVO.getStrCount(); 
		String[] words = count.split("개");
		LOG.debug("1.= count=" + count);
		LOG.debug("1.= words=" + words);
      
		List<String> countList = new ArrayList<String>(); 
		LOG.debug("1.= countList=" + countList); 
		
		for(String wo : words){
			countList.add(wo);	
			LOG.debug("1.= wo=" + wo); 
		}

		for(int i = 0; i < countList.size(); i++){			
			count =countList.get(i);
			inVO.setStrCount(count);
			LOG.debug("1.= inVO=" + inVO); 
		}
		return countList;	
	}
	
	
	
	
	
	
	/**
	 * D:\\HR_FILE\2019\09
	 */
	public static String dynamicDir() {
		// 01.동적으로 UPLOAD_ROOT 디렉토리 생성
		File fileRootDir = new File(UPLOAD_ROOT);
		if (fileRootDir.isDirectory() == false) {
			boolean flag = fileRootDir.mkdirs();
			LOG.debug("=flag=" + flag);
		}

		// 02.년월 디렉토리 생성:D:\\HR_FILE\2019\09
		String yyyy = StringUtil.cureDate("yyyy");
		LOG.debug("=yyyy=" + yyyy);
		String mm = StringUtil.cureDate("MM");
		LOG.debug("=mm=" + mm);
		String datePath = UPLOAD_ROOT + File.separator + yyyy + File.separator + mm;
		LOG.debug("=datePath=" + datePath);

		File fileYearMM = new File(datePath);

		if (fileYearMM.isDirectory() == false) {
			boolean flag = fileYearMM.mkdirs();
			LOG.debug("=fileYearMM flag=" + flag);
		}
		
		return datePath;
	}

	/**
	 * 파일 Rename
	 * 
	 * @param f
	 * @return 파일 rename명 cloude.jpg->cloude1~9999.jpg
	 */
	public static String fileRename(File f) {
		String retFileNm = "";
		// 01.파일 존재 Check
		if (!f.exists()) {
			retFileNm = f.getAbsolutePath();
			return retFileNm;
		}

		// 02.파일 있으면: rename
		// cloude + 확장자
		String name = f.getName();// cloude.jpg
		String body = null;// cloude
		String ext = null;// jpg
		int dot = name.lastIndexOf(".");
		if (dot != -1) {
			body = name.substring(0, dot);
			ext = name.substring(dot);// .jpg
		}

		// 03.반복문 처리
		int count = 0;
		while (f.exists() && count < 99999) {
			count++;
			retFileNm = body + count + ext;
			f = new File(f.getParent(), retFileNm);
		}

		return f.getAbsolutePath();
	}
	
	/**
	 * 파일 Rename
	 * /usr/local/apache-tomcat-8.5.34/webapps/SUNNYSIDE3/resources/image" =>../resources/image  
	 * 
	 * @param f
	 * @return 파일 rename명 cloude.jpg->cloude1~9999.jpg
	 */
	public static String changePath(String str) {
		str = ".."+str.substring(str.indexOf("/resources"));
		//str = ".."+str.substring(str.indexOf("\\resources")).replace("\\", "/");
		
		return str;
	}
	
	/**
	 * 파일 Rename
	 * ../resources/image   => /usr/local/apache-tomcat-8.5.34/webapps/SUNNYSIDE3/resources/image
	 * @param f
	 * @return 파일 rename명 cloude.jpg->cloude1~9999.jpg
	 */
	public static String backPath(String str) {
		str = "/usr/local/apache-tomcat-8.5.34/webapps/SUNNYSIDE3"+str.substring(str.indexOf("/resources"));
		// str = "C:\\Users\\sist\\git\\SUNNYSIDE3\\SUNNYSIDE3\\src\\main\\webapp"+str.substring(str.indexOf("/resources")).replace("/", "\\");
		return str;
	}
	

	public static String getUUID() {
		UUID uuid = UUID.randomUUID();
		String retUUID = uuid.toString().replace("-", "");
		LOG.debug("===========================");
		LOG.debug("retUUID=\n" + retUUID);
		LOG.debug("retUUID length=\n" + retUUID.length());
		LOG.debug("===========================");
		return retUUID;
	}

	/**
	 * yyyy -> 2019 MM -> 10
	 * 
	 * @param format
	 * @return
	 */
	public static String cureDate(String format) {
		if (null == format || format.equals(""))
			format = "yyyyMMdd";
		SimpleDateFormat formatter = new SimpleDateFormat(format);

		return formatter.format(new Date());
	}

	/**
	 * 현제 날자를 8자리로 반환 한다
	 * 
	 * @return yyyyMMdd
	 */
	public static String getNowDate() {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
		return formatter.format(Calendar.getInstance().getTime());
	}

	/**
	 * 
	 * @Method Name : renderPaing
	 * @작성일 : 2019. 7. 24.
	 * @작성자 : SIST
	 * @변경이력 : 최초작성
	 * @Method 설명 :
	 * @param maxNum:총글수
	 * @param currPageNo:      현재페이지
	 * @param rowPerPage:한페이지에 보여질 글수
	 * @param bottomCount:바닥에  보여질 페이지수
	 * @param url:호출url
	 * @param scriptName:호출    javascript
	 * @return
	 */
	public static String renderPaing(int maxNum, int currPageNo, int rowPerPage, int bottomCount, String url,
			String scriptName) {
		/*
		 * 총글수 : 21 현재페이지 1 총글수 0 바닥에 보여질 페이지수 10 한페이지에 보여질 글수 10 호출url 호출 javascript <<
		 * < 1 2 3 4 5 6 7 8 9 10 > >> 총글수 : 21,1
		 */
		int maxPageNo = ((maxNum - 1) / rowPerPage) + 1;// 총페이지
		int startPaeNo = ((currPageNo - 1) / bottomCount) * bottomCount + 1;
		int endPageNo = ((currPageNo - 1) / bottomCount + 1) * bottomCount;
		int nowBlockNo = ((currPageNo - 1) / bottomCount) + 1;
		int maxBlockNo = ((maxNum - 1) / bottomCount) + 1;

		int inx = 0;
		StringBuilder html = new StringBuilder();
		if (currPageNo > maxPageNo) {
			return "";
		}

		// <table><tr><td></td></tr></table>
		html.append("<table border=\"0\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\"     >\n");
		html.append("<tr> \n");
		html.append("<td align=\"center\"> \n");
		html.append("<ul class=\"pagination\"> \n");
		// paging-------------------
		// << &laquo; 왼쪽 꺾인 괄호
		if (nowBlockNo > 1 && nowBlockNo <= maxBlockNo) {
			html.append("<li  class=\"page-item\"><a class=\"page-link\" href=\"javascript:" + scriptName + "('" + url
					+ "',1);\"  > ");
			html.append("&laquo; ");
			html.append("</a></li> \n");
		}
		// <
		if (startPaeNo > bottomCount) {
			html.append("<li  class=\"page-item\"><a class=\"page-link\" href=\"javascript:" + scriptName + "('" + url
					+ "'," + (startPaeNo - 1) + ");\"  > ");
			html.append("< ");
			html.append("</a></li> \n");
		}
		// 1 2 .... 10
		for (inx = startPaeNo; inx <= maxPageNo && inx <= endPageNo; inx++) {
			if (inx == currPageNo) {// 현재 page
				html.append("<li  class=\"disabled\" 	>");
				html.append("<a  href=\"javascript:#\"  > ");
				html.append(inx);
				html.append("</a> \n");
				html.append("</li>");
			} else {
				html.append("<li  class=\"page-item\">");
				html.append("<a class=\"page-link\" href=\"javascript:" + scriptName + "('" + url + "'," + inx
						+ ");\"  > ");
				html.append(inx);
				html.append("</a> \n");
				html.append("</li>");
			}

		}
		// >
		if (maxPageNo >= inx) {
			html.append("<li  class=\"page-item\">");
			html.append("<a  class=\"page-link\" href=\"javascript:" + scriptName + "('" + url + "',"
					+ ((nowBlockNo * bottomCount) + 1) + ");\"  > ");
			html.append("> ");
			html.append("</a> \n");
			html.append("</li>");

		}
		// >> &raquo; 오른쪽 꺾인 괄호
		if (maxPageNo >= inx) {
			html.append("<li  class=\"page-item\">");
			html.append("<a class=\"page-link\" href=\"javascript:" + scriptName + "('" + url + "'," + maxPageNo
					+ ");\"  > ");
			html.append("&raquo; ");
			html.append("</a> \n");
			html.append("</li>");
		}
		html.append("</ul> \n");
		// --paging-----------------
		html.append("</td> \n");
		html.append("</tr> \n");
		html.append("</table>");

		// LOG.debug("===========================");
		// LOG.debug("html.toString()=\n"+html.toString());
		// LOG.debug("===========================");
		return html.toString();
	}

	/**
	 * 
	 * @Method Name : makeSelectBox
	 * @작성일 : 2019. 7. 22.
	 * @작성자 : SIST
	 * @변경이력 : 최초작성
	 * @Method 설명 :
	 * @param list:       select에 필용한 코드정보
	 * @param selectBoxNm : <select name="lvl" id="lvl">
	 * @param selectedNm  : <option selected
	 * @param allYN       : 전체 표시
	 * @return : <select name="lvl" id="lvl"> <option value="">전체</option> <option
	 *         value="1" selected>일반사용자</option> <option value="9">관리자</option>
	 *         </select>
	 */
	public static String makeSelectBox(List<CodeVO> list, String selectBoxNm, String selectedNm, boolean allYN) {
		StringBuilder sb = new StringBuilder();
		// <select name="lvl" id="lvl">
		sb.append("<select class=\"form-control input-sm\" name='" + selectBoxNm + "' id='" + selectBoxNm + "' > \n");

		// <option value="1" selected>팝콘</option>
		for (CodeVO dto : list) {
			CodeVO vo = dto;
			sb.append("\t<option value='" + vo.getCodeId() + "' ");
			if (selectedNm.equals(vo.getCodeId())) {
				sb.append("selected='selected' ");
			}

			sb.append(">");
			sb.append(vo.getCodeNm());
			sb.append("</option>\n");
		}
		sb.append("</select> \n");
		LOG.debug("------------------------");
		LOG.debug(sb.toString());
		LOG.debug("------------------------");
		return sb.toString();
	}
	
	public static String makeBranchBox(List<KYMBranchVO> list, String selectBoxNm, String selectedNm, boolean allYN, boolean disable) {
		StringBuilder sb = new StringBuilder();
		// <select name="lvl" id="lvl">
		if(disable == true) {
			sb.append("<select disabled=\"disabled\" class=\"form-control input-sm\" name='" + selectBoxNm + "' id='" + selectBoxNm + "' > \n");
		}else {
			sb.append("<select  class=\"form-control input-sm\" name='" + selectBoxNm + "' id='" + selectBoxNm + "' > \n");
		}
		// 전체
		if (allYN == true) {
			sb.append("<option value=''>전체</option> \n");
		}

		// <option value="1" selected>일반사용자</option>
		for (KYMBranchVO dto : list) {
			KYMBranchVO vo = dto;
			sb.append("\t<option value='" + vo.getbranchSNm() + "' ");
			if (selectedNm.equals(vo.getbranchSNm())) {
				sb.append("selected='selected' ");
			}

			sb.append(">");
			sb.append(vo.getbranchSNm());
			sb.append("</option>\n");
		}
		sb.append("</select> \n");
		LOG.debug("------------------------");
		LOG.debug(sb.toString());
		LOG.debug("------------------------");
		return sb.toString();
	}
	
	/**
	 * 
	 * @Method Name : makeSelectBox
	 * @작성일 : 2019. 7. 22.
	 * @작성자 : SIST
	 * @변경이력 : 최초작성
	 * @Method 설명 :
	 * @param list:       select에 필용한 코드정보
	 * @param selectBoxNm : <select name="lvl" id="lvl">
	 * @param selectedNm  : <option selected
	 * @param allYN       : 전체 표시
	 * @return : <select name="lvl" id="lvl"> <option value="">전체</option> <option
	 *         value="1" selected>일반사용자</option> <option value="9">관리자</option>
	 *         </select>
	 */
	public static String productMakeSelectBox(List<CodeVO> list, String selectBoxNm, String selectedNm, boolean allYN) {
		StringBuilder sb = new StringBuilder();
		// <select name="lvl" id="lvl">
		sb.append("<select disabled=\"disabled\"  class=\"form-control input-sm\" name='" + selectBoxNm + "' id='" + selectBoxNm + "' > \n");

		// <option value="1" selected>팝콘</option>
		for (CodeVO dto : list) {
			CodeVO vo = dto;
			sb.append("\t<option value='" + vo.getCodeId() + "' ");
			if (selectedNm.equals(vo.getCodeId())) {
				sb.append("selected='selected' ");
				sb.append("disabled='disabled' ");
			}

			sb.append(">");
			sb.append(vo.getCodeNm());
			sb.append("</option>\n");
		}
		sb.append("</select> \n");
		LOG.debug("------------------------");
		LOG.debug(sb.toString());
		LOG.debug("------------------------");
		return sb.toString();
	}

	public static String nvl(String val) {
		return nvl(val, "");
	}

	public static String nvl(String val, String rep) {
		if (val == null || ("").equals(val)) {
			val = rep;
		}
		return val;
	}
	
	/**
	 * 
	 * @Method Name : productMakeSelectBox
	 * @작성일 : 2019. 7. 22.
	 * @작성자 : SIST
	 * @변경이력 : 최초작성
	 * @Method 설명 :
	 * @param list:       select에 필용한 코드정보
	 * @param selectBoxNm : <select name="lvl" id="lvl">
	 * @param selectedNm  : <option selected
	 * @param allYN       : 전체 표시
	 * @return : <select name="lvl" id="lvl"> <option value="">전체</option> <option
	 *         value="1" selected>일반사용자</option> <option value="9">관리자</option>
	 *         </select>
	 */
	public static String productMakeSelectBox(List<CodeVO> list, String selectBoxNm, String selectedNm) {
		StringBuilder sb = new StringBuilder();
		// <select name="lvl" id="lvl">
		sb.append("<select  class=\"form-control input-sm\" name='" + selectBoxNm + "' id='" + selectBoxNm + "' > \n");

		// <option value="001" selected>팝콘</option>
		for (CodeVO dto : list) {
			CodeVO vo = dto;
			sb.append("\t<option value='" + vo.getCodeId() + "' ");
			if (selectedNm.equals(vo.getCodeId())) {
				sb.append("selected='selected' ");
			}

			sb.append(">");
			sb.append(vo.getCodeNm());
			sb.append("</option>\n");
		}
		sb.append("</select> \n");
		LOG.debug("------------------------");
		LOG.debug(sb.toString());
		LOG.debug("------------------------");
		return sb.toString();
	}


	/**
	 * 개행문자를 <br/>
	 * 로 바꾼다.<br>
	 *
	 * @param content
	 * @return String
	 */
	public static String nl2br(String content) {
		// content = translate(content, "\n", "<br />\n");
		content = translate(content, "\r", "<br />\n");
		return content;
	}

	public static String translate(String content, String pattern, String replace) {
		if (content == null)
			return "";
		int s = 0;
		int e = 0;
		StringBuffer result = new StringBuffer();
		while ((e = content.indexOf(pattern, s)) >= 0) {
			result.append(content.substring(s, e));
			result.append(replace);
			s = e + pattern.length();
		}
		result.append(content.substring(s));
		return result.toString();
	}

	/**
	 * 문자열을 입력받아서, 3자리 단위마다 구분자처리한다.
	 *
	 * @Method Name : makeMoneyType
	 * @param moneyString
	 * @return
	 */
	public static String makeMoneyType(int money) {
		DecimalFormat df = (DecimalFormat) NumberFormat.getInstance();
		df = new DecimalFormat("##,###.##");
		return df.format(money);
	}

	/**
	 * 문자열을 입력받아서, 3자리 단위마다 구분자처리한다.
	 *
	 * @Method Name : makeMoneyType
	 * @param moneyString
	 * @return
	 */
	public static String makeMoneyType(double money) {
		DecimalFormat df = (DecimalFormat) NumberFormat.getInstance();
		df = new DecimalFormat("##,###.##");
		return df.format(money);
	}

	/**
	 * String으로 받은 문자열을 가격 ,를 찍어서 변환 해주는 유틸
	 * </pre>
	 * 
	 * @Method Name : makeMoneyType
	 * @param money
	 * @return
	 */
	public static String makeMoneyType(String money) {
		String newMoney = money;
		if (newMoney == null || newMoney.isEmpty()) {
			newMoney = "0";
		}
		DecimalFormat df = (DecimalFormat) NumberFormat.getInstance();
		df = new DecimalFormat("##,###.##");
		return df.format(Float.parseFloat(newMoney));
	}

	public static String escapeHtml(String html) {
		return StringEscapeUtils.escapeHtml4(html);
	}
	
	/**
	 * 좌석생성
	 * A부터 max_y열까지 max개의 좌석버튼 생성
	 * 현재 max_y = O, max = 28 고정
	 * setSetting = 0(일반) or 1(좌석설정용)
	 * */
	public static String makeSeatButton(int setSetting) {
		//<button class="seatY a.. ..z" disabled="disabled">a.. ..z</button><button data-y=".." data-x=".." disable="..." ">1...</button>
		
		//String max_y_axis, String max_x_SeatNum
		//char max_y = max_y_axis.charAt(0); 
		//int max = Integer.valueOf(max_x_SeatNum);
		
		char max_y = 'O';
		int max = 28;
		
		StringBuilder sb = new StringBuilder();
		
		for(int Y='A' ; Y <= max_y ; Y++) {
			sb.append("<button class='seatY " + (char)Y + "' disabled='disabled'>" + (char)Y);
			for(int i=1 ; i <= max ; i++) {
				if(setSetting == 0)
					sb.append("<button data-nowSelect='0' data-y="+(char)Y+" data-x="+i+" class='" + (char)Y + " " + "_" + i + "' disabled='disabled'></button>");
				else
					sb.append("<button data-setseat='false' data-use='1' data-y="+(char)Y+" data-x="+i+" class='" + (char)Y + " " + "_" + i + "'></button>");
			}
			if(Y != max_y)
				sb.append("<br/>");
		}
		return sb.toString();
	}
	
	public static String col1Substring(String str) {
		if(str.contains("전체")) {
			str = str.substring(0, 2);
		}else if(str != null && !str.equals("")) {
			str = str.substring(0, 3);
		}
		return str;
	}
	
	public static String col3Substring(String str) {
		if(str.length()>16) {
			return str.substring(0, 15)+"...";
		}else {
			return str;
		}		 
	}
	
	public static String col4Substring(String str) {
		if(str.length()>25) {
			return str.substring(0, 22)+"...";
		}else {
			return str;
		}		 
	}
	
	public static String castSubstring(String str) {
		if(str.length()>50) {
			return str.substring(0, 50)+"...";
		}else {
			return str;
		}		 
	}
}
