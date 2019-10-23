package kr.co.sunnyside.login.web;

import java.io.IOException;
import java.util.List;
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
import kr.co.sunnyside.cmn.StringUtil;
import kr.co.sunnyside.code.service.CodeService;
import kr.co.sunnyside.login.service.SJH_LoginSvc;
import kr.co.sunnyside.login.service.SJH_LoginVO;
import kr.co.sunnyside.main.service.LHJ_MainImageVO;
import kr.co.sunnyside.main.service.LHJ_MainSvc;
import kr.co.sunnyside.main.service.impl.LHJ_MainSvcImpl;
import kr.co.sunnyside.movie.service.LHJ_BoxofficeSvc;
import kr.co.sunnyside.movie.service.LHJ_MovieVO;
import kr.co.sunnyside.movie.service.impl.LHJ_BoxofficeSvcImpl;
import kr.co.sunnyside.login.service.NaverLoginBO;

import org.json.simple.parser.ParseException;
import com.github.scribejava.core.model.OAuth2AccessToken;


@Controller
public class SJH_LoginCtrl {

	Logger LOG = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	LHJ_MainSvc mainService;
	
	@Autowired
	LHJ_BoxofficeSvc boxofficeService;	 
	
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
	@RequestMapping(value = "login/login_view.do", method = { RequestMethod.GET, RequestMethod.POST })
	public String login(Model model, HttpSession session,HttpServletRequest request,HttpServletResponse response) {
		String naverAuthUrl = naverLoginBO.getAuthorizationUrl(session);
		LOG.debug("네이버:" + naverAuthUrl);
		model.addAttribute("url", naverAuthUrl);
		LOG.debug("naverAuthUrl: "+naverAuthUrl);

		//다국어
		String language = StringUtil.nvl(request.getParameter("lang"),"ko");
		
		LOG.debug("======================");
		LOG.debug("=language="+language);
		LOG.debug("======================");
		
		Locale locale = new Locale(language);
		localeResolver.setLocale(request, response, locale);
		
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
		
		//2. String형식인 apiResult를 json형태로 바꿈
		JSONParser parser = new JSONParser();
		Object obj = parser.parse(apiResult);
		JSONObject jsonObj = (JSONObject) obj;
		
		
		//3. 데이터 파싱
		//Top레벨 단계 _response 파싱
		JSONObject response_obj = (JSONObject)jsonObj.get("response");
		//response의 nickname값 파싱
		String userId = (String) response_obj.get("id");
		String userName = (String)response_obj.get("name");
		String email = (String)response_obj.get("email");

		LOG.debug("========================");
		LOG.debug("apiResult: "+apiResult);
		LOG.debug("response_obj: "+response_obj);
		LOG.debug("userId: "+userId);
		LOG.debug("userName: "+userName);
		LOG.debug("email: "+email);
		LOG.debug("========================");
		
		
		//4.파싱 닉네임 세션으로 저장
		SJH_LoginVO user = new SJH_LoginVO();
		user.setUserId(userId);
		user.setUserName(userName);
		user.setEmail(email);
		user.setFlag("1");
		
		session.setAttribute("user",user); //세션 생성
		model.addAttribute("userId",userId);
		model.addAttribute("userName",userName);
		model.addAttribute("email",email);
		model.addAttribute("result", apiResult);

		//추가정보 입력했는지 확인
		Message message = (Message) loginSvc.id_check(user);
		if(message.getMsgId().equals("20")) {
			return "login/add_user_info";
		}
		
		return "main/main";
	}
	
	
	
	/**
	 * 로그아웃
	 * @param session
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "login/logout.do", method = { RequestMethod.GET, RequestMethod.POST })
	public String logout(HttpSession session, Model model)throws IOException {
		LOG.debug("logout");
		session.invalidate();
		
		List<LHJ_MainImageVO> bannerList = (List<LHJ_MainImageVO>) this.mainService.do_banner_retrieve();
		model.addAttribute("bannerList", bannerList);
		
		List<LHJ_MovieVO> boxofficeList = (List<LHJ_MovieVO>) this.boxofficeService.do_retrieve_main();
		model.addAttribute("boxofficeList", boxofficeList);
		
		return "main/main";
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
		
		
		Message message = (Message) loginSvc.id_check(user);
		
		if(message.getMsgId().equals("10")) {
			message.setMsgId("10");
			message.setMsgMsg("이미 존재하는 아이디입니다.");
		}else {
			message.setMsgId("20");
			message.setMsgMsg("사용 가능한 아이디입니다.");			
		}
		
		//json으로 변환
		Gson gson = new Gson();
		String json = gson.toJson(message);
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
			message.setMsgMsg(user.getUserName()+"님 등록되었습니다.");
		}else {
			message.setMsgId(flag+"");
			message.setMsgMsg(user.getUserName()+"님 등록실패.");
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
