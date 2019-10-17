package kr.co.sunnyside.login.web;

import java.io.IOException;
import java.util.Locale;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.View;

import com.github.scribejava.core.model.OAuth2AccessToken;
import com.google.gson.Gson;

import kr.co.sunnyside.cmn.DTO;
import kr.co.sunnyside.cmn.Message;
import kr.co.sunnyside.code.service.CodeService;
import kr.co.sunnyside.login.service.SJH_LoginSvc;
import kr.co.sunnyside.login.service.SJH_LoginVO;
import kr.co.sunnyside.login.service.NaverLoginBO;

import org.json.simple.parser.ParseException;
import com.github.scribejava.core.model.OAuth2AccessToken;


@Controller
public class SJH_LoginCtrl {

	Logger LOG = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private LocaleResolver localeResolver;
	
	@Resource(name="downloadView")
	private View download;
	
	@Autowired
	SJH_LoginSvc loginSvc;
	
	@Autowired
	CodeService codeService;
	
	
	//view
	private final String VIEW_JOIN = "login/join";
	private final String VIEW_LOGIN = "login/login";
	private final String VIEW_ID_FIND_LIST = "login/login_id_find_list";
	private final String VIEW_PW_FIND_LIST = "login/login_pw_find_list";
	
	private final String VIEW_ID_FIND_MNG = "login/login_id_find_mng";
	private final String VIEW_PW_FIND_MNG = "login/login_pw_find_mng";
	
	
	
	/* NaverLoginBO */
	private NaverLoginBO naverLoginBO;
	private String apiResult = null;
	
	@Autowired
	private void setNaverLoginBO(NaverLoginBO naverLoginBO) {
		this.naverLoginBO = naverLoginBO;
	}
	
	
	//회원가입 화면call
	@RequestMapping(value="login/join_view.do", method = RequestMethod.GET)
	public String joinView() {
		LOG.debug("========================");
		LOG.debug("=@Controller=View=");
		LOG.debug("========================");
		
		return VIEW_JOIN;
	}
	
	//아이디 찾기 화면call
	@RequestMapping(value="login/id_find_mng_view.do", method = RequestMethod.GET)
	public String IdFindMngView() {
		LOG.debug("========================");
		LOG.debug("=@Controller=View=");
		LOG.debug("========================");
		
		return VIEW_ID_FIND_MNG;
	}
	
	//비밀번호 찾기 화면call
	@RequestMapping(value="login/pw_find_mng_view.do", method = RequestMethod.GET)
	public String pwFindMngView() {
		LOG.debug("========================");
		LOG.debug("=@Controller=View=");
		LOG.debug("========================");
		
		return VIEW_PW_FIND_MNG;
	}
	
	//로그인 화면call
	/** 로그인 첫 화면 요청 메소드 */ //--->이 부분을 메인이랑 연결해야함. 
	@RequestMapping(value = "login/login_view.do", method = { RequestMethod.GET, RequestMethod.POST })
	public String login(Model model, HttpSession session) {
		/* 네이버아이디로 인증 URL을 생성하기 위하여 naverLoginBO클래스의 getAuthorizationUrl메소드 호출 */
		String naverAuthUrl = naverLoginBO.getAuthorizationUrl(session);
		
		//https://nid.naver.com/oauth2.0/authorize?response_type=code&client_id=sE***************&
		//redirect_uri=http%3A%2F%2F211.63.89.90%3A8090%2Flogin_project%2Fcallback&state=e68c269c-5ba9-4c31-85da-54c16c658125
		LOG.debug("네이버:" + naverAuthUrl);
		
		//네이버
		model.addAttribute("url", naverAuthUrl);
		LOG.debug("naverAuthUrl: "+naverAuthUrl);
		
		return VIEW_LOGIN;
	}
	
	
	/** 네이버 로그인 성공시 callback호출 메소드 */
	@RequestMapping(value = "login/callback.do", method = { RequestMethod.GET, RequestMethod.POST })
	public String callback(Model model, @RequestParam String code, @RequestParam String state, HttpSession session) throws IOException, ParseException {
		LOG.debug("callback");
		OAuth2AccessToken oauthToken;
		oauthToken = naverLoginBO.getAccessToken(session, code, state);
		LOG.debug("oauthToken: "+oauthToken);
		
		//1. 로그인 사용자 정보를 읽어온다.
		apiResult = naverLoginBO.getUserProfile(oauthToken); //String형식의 json데이터
		/** apiResult json 구조
		{"resultcode":"00",
		"message":"success",
		"response":{"id":"33666449","nickname":"shinn****","age":"20-29","gender":"M","email":"shinn0608@naver.com","name":"\uc2e0\ubc94\ud638"}}
		**/
		
		LOG.debug("apiResult: "+apiResult);
		
		//2. String형식인 apiResult를 json형태로 바꿈
		JSONParser parser = new JSONParser();
		Object obj = parser.parse(apiResult);
		JSONObject jsonObj = (JSONObject) obj;
		
		//3. 데이터 파싱
		//Top레벨 단계 _response 파싱
		JSONObject response_obj = (JSONObject)jsonObj.get("response");
		//response의 nickname값 파싱
		String userId = (String)response_obj.get("id");
		String userName = (String)response_obj.get("name");
		String email = (String)response_obj.get("email");
		String birth = (String)response_obj.get("birthday");
		
		LOG.debug("========================");
		LOG.debug("userId"+userId);
		LOG.debug("userName"+userName);
		LOG.debug("email"+email);
		LOG.debug("birth"+birth);
		LOG.debug("========================");
		
		
		//4.파싱 닉네임 세션으로 저장
		session.setAttribute("userId",userId); //세션 생성
		
		model.addAttribute("userId",userId);
		model.addAttribute("userName",userName);
		model.addAttribute("email",email);
		
		model.addAttribute("result", apiResult);
		LOG.debug("apiResult: "+apiResult);
		
		
		return "login/add_user_info"; //추가정보 입력하는 페이지로 리턴
	}
	
	
	
	/**
	 * 로그아웃
	 * @param session
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "login/logout.do", method = { RequestMethod.GET, RequestMethod.POST })
	public String logout(HttpSession session)throws IOException {
		LOG.debug("logout");
		session.invalidate();
		return "main/main.jsp";
	}

	

	/**
	 * 로그인 (SSO 설정 안 한 상태)
	 * @param user
	 * @param session
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="login/do_login.do", method = RequestMethod.POST
					,produces = "application/json; charset=UTF-8")
	@ResponseBody		
	public String do_login(SJH_LoginVO user,HttpSession session, HttpServletRequest request, HttpServletResponse response) {
		LOG.debug("1========================");
		LOG.debug("1=user="+user);
		LOG.debug("1========================");
		
		Message msg = (Message) loginSvc.do_login(user);
		LOG.debug("2=========================");
		LOG.debug("2=msg="+msg);
		LOG.debug("2=========================");
		
		//10:ID확인, 20:비번확인
		if(msg.getMsgId().equals("10") || msg.getMsgId().equals("20")) {
			
		}else {
			//데이터 단건조회
			SJH_LoginVO outVO = (SJH_LoginVO) loginSvc.do_selectOne(user);
			outVO.setUserLevel(outVO.getUserLevel());
			LOG.debug("3=========================");
			LOG.debug("3=outVO="+outVO);
			LOG.debug("3=========================");
			Locale locale = new Locale(user.getLang());
			localeResolver.setLocale(request, response, locale);
			
			session.setAttribute("user", outVO);
		}
		
		//JSON
		Gson gson=new Gson();
		String json = gson.toJson(msg);
		LOG.debug("2=========================");
		LOG.debug("=@Controller=json=="+json);
		LOG.debug("2=========================");
		
		return json;		
		
	}
	
	
	/**
	 * 아이디 중복체크
	 * @param user
	 * @param session
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="login/id_check.do", method = RequestMethod.POST
			,produces = "application/json; charset=UTF-8")
	@ResponseBody		
	public String id_check(SJH_LoginVO user) {
		LOG.debug("1========================");
		LOG.debug("1=user="+user);
		LOG.debug("1========================");
		
		Message msg = (Message) loginSvc.id_check(user);

		//json으로 변환
		Gson gson = new Gson();
		String json = gson.toJson(msg);
		LOG.debug("=========================");
		LOG.debug("=@Controller 등록gson=user=="+json);
		LOG.debug("=========================");		
		
		return json;
	
	}
	
	
	
	/**
	 * 아이디 찾기
	 * @param user
	 * @return
	 */
	@RequestMapping(value="login/id_find.do",method = RequestMethod.POST,produces = "application/json; charset=UTF-8")
	public String id_find(SJH_LoginVO user,Model model) {
		LOG.debug("=========================");
		LOG.debug("=@Controller=user="+user);
		LOG.debug("=========================");
		
		if(user.getUserName() == null || "".equals(user.getUserName())) {
			throw new IllegalArgumentException("이름을 입력하세요.");
		}
		
		if(user.getEmail()== null || "".equals(user.getEmail())) {
			throw new IllegalArgumentException("이메일을 입력하세요.");
		}
		
		SJH_LoginVO outVO = (SJH_LoginVO) loginSvc.id_find(user);
		model.addAttribute("vo", outVO);
		
		return VIEW_ID_FIND_LIST; //화면으로 리턴
	}
	
	
	
	
	/**
	 * 비밀번호 찾기
	 * @param user
	 * @return
	 */
	@RequestMapping(value="login/pw_find.do",method = RequestMethod.POST
					,produces = "application/json; charset=UTF-8")
	public String pw_find(SJH_LoginVO user) {
		LOG.debug("1=========================");
		LOG.debug("=@Controller=user=="+user);
		LOG.debug("1=========================");
		
		//validation
		int flag = loginSvc.pw_find(user);
		
		Message message=new Message();
		if(flag>0) {
			message.setMsgId(flag+"");
			message.setMsgMsg(user.getUserName()+"님의 메일로 임시 비밀번호가 전송 되었습니다.");
		}else {
			message.setMsgId(flag+"");
			message.setMsgMsg("비밀번호 찾기 실패.");			
		}
	
		return VIEW_PW_FIND_LIST; //화면으로 리턴		
	}
	
	
	
	
	/**
	 * 회원가입
	 * @param user
	 * @return
	 */
	@RequestMapping(value="login/do_save.do",method = RequestMethod.POST,produces = "application/json; charset=UTF-8")
	@ResponseBody
	public String do_save(SJH_LoginVO user) {
		LOG.debug("========================");
		LOG.debug("=@Controller=user="+user);
		LOG.debug("========================");
		
		//validation
		int flag = loginSvc.do_save(user);
		Message message = new Message();
		if(flag>0) {
			message.setMsgId(flag+"");
			message.setMsgMsg(user.getUserId()+"님 등록되었습니다.");
		}else {
			message.setMsgId(flag+"");
			message.setMsgMsg(user.getUserId()+"님 등록실패.");
		}

		//json으로 변환
		Gson gson = new Gson();
		String json = gson.toJson(message);
		LOG.debug("=========================");
		LOG.debug("=@Controller 등록gson=user=="+json);
		LOG.debug("=========================");		
		
		
		return json;
	}
	
	
	
}
