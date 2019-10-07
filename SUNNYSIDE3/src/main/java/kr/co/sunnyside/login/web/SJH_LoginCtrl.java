package kr.co.sunnyside.login.web;

import java.util.Locale;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.View;

import com.google.gson.Gson;

import kr.co.sunnyside.cmn.DTO;
import kr.co.sunnyside.cmn.Message;
import kr.co.sunnyside.code.service.CodeService;
import kr.co.sunnyside.login.service.SJH_LoginSvc;
import kr.co.sunnyside.login.service.SJH_LoginVO;


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
	
	
	//View
	//private final String VIEW_NM = "main/main";
	
	
	
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
	
	
	
	/**
	 * 아이디 찾기
	 * @param user
	 * @return
	 */
	@RequestMapping(value="login/id_find.do",method = RequestMethod.POST,produces = "application/json; charset=UTF-8")
	@ResponseBody
	public String id_find(SJH_LoginVO user) {
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

		Gson gson=new Gson();
		String json = gson.toJson(outVO);
		LOG.debug("=========================");
		LOG.debug("=@Controller gson=user="+json);
		LOG.debug("=========================");		
		
		return json;
	}
	
	
	
	/**
	 * 비밀번호 찾기
	 * @param user
	 * @return
	 */
	@RequestMapping(value="login/pw_find.do",method = RequestMethod.POST
					,produces = "application/json; charset=UTF-8")
	@ResponseBody		
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
	
		//JSON
		Gson gson=new Gson();
		String json = gson.toJson(message);
		LOG.debug("2=========================");
		LOG.debug("=@Controller=json=="+json);
		LOG.debug("2=========================");
		return json;		
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
			SJH_LoginVO outVO = (SJH_LoginVO) loginSvc.get_selectOne(user);
			outVO.setUserLevel(outVO.getUserLevel());
			LOG.debug("3=========================");
			LOG.debug("3=outVO="+outVO);
			LOG.debug("3=========================");
//			Locale locale = new Locale(user.getLang());
//			localeResolver.setLocale(request, response, locale);
			
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
	
	
	
	
	
}
