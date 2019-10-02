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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.View;

import com.google.gson.Gson;

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
	private final String VIEW_NM = "main/main";
	
	
	/**
	 * 로그인
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
		
		Message msg = (Message) loginSvc.idPassCheck(user);
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
