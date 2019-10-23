package kr.co.sunnyside.cmn;

import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.LocaleResolver;

import kr.co.sunnyside.main.service.LHJ_MainImageVO;
import kr.co.sunnyside.main.service.impl.LHJ_MainSvcImpl;
import kr.co.sunnyside.movie.service.LHJ_MovieVO;
import kr.co.sunnyside.movie.service.impl.LHJ_BoxofficeSvcImpl;

@Controller
public class LocaleChangeController {

	Logger LOG = LoggerFactory.getLogger(LocaleChangeController.class);
	
	@Autowired
	LHJ_MainSvcImpl mainService;
	
	@Autowired
	LHJ_BoxofficeSvcImpl boxofficeService;	 
	
	@Autowired
	private LocaleResolver localeResolver; //SessionLocaleResolver

	public void setLocaleResolver(LocaleResolver localeResolver) {
		this.localeResolver = localeResolver;
	}
	
	
	/**
	 * 로그인 -> 메인 (일반 다국어)
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/change_locale.do",method=RequestMethod.GET)
	public String changeLocale(HttpServletRequest request,HttpServletResponse response, Model model) {
		String language = StringUtil.nvl(request.getParameter("lang"),"ko");
		
		List<LHJ_MainImageVO> bannerList = (List<LHJ_MainImageVO>) this.mainService.do_banner_retrieve();
		model.addAttribute("bannerList", bannerList);
		
		List<LHJ_MovieVO> boxofficeList = (List<LHJ_MovieVO>) this.boxofficeService.do_retrieve_main();
		model.addAttribute("boxofficeList", boxofficeList);
		
		LOG.debug("======================");
		LOG.debug("=language="+language);
		LOG.debug("======================");
		
		Locale locale = new Locale(language);
		localeResolver.setLocale(request, response, locale);
		
		return "main/main";
	}
	
	
	/**
	 * 로그인 -> 로그인 (로그인 다국어)
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/login_change_locale.do",method=RequestMethod.GET)
	@ResponseBody
	public String loginChangeLocale(HttpServletRequest request,HttpServletResponse response) {
		String language = StringUtil.nvl(request.getParameter("lang"),"ko");
		
		LOG.debug("======================");
		LOG.debug("=language="+language);
		LOG.debug("======================");
		
		Locale locale = new Locale(language);
		localeResolver.setLocale(request, response, locale);
		
		return "login/login";
		
	}
	
	
	
}
