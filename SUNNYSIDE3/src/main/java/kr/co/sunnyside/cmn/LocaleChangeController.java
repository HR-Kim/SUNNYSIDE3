package kr.co.sunnyside.cmn;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.LocaleResolver;

@Controller
public class LocaleChangeController {

	Logger LOG = LoggerFactory.getLogger(LocaleChangeController.class);
	
	@Autowired
	private LocaleResolver localeResolver; //SessionLocaleResolver

	public void setLocaleResolver(LocaleResolver localeResolver) {
		this.localeResolver = localeResolver;
	}
	
	@RequestMapping(value="/change_locale.do",method=RequestMethod.GET)
	public String changeLocale(HttpServletRequest request,HttpServletResponse response) {
		String language = StringUtil.nvl(request.getParameter("lang"),"ko");
		
		LOG.debug("======================");
		LOG.debug("=language="+language);
		LOG.debug("======================");
		
		Locale locale = new Locale(language);
		localeResolver.setLocale(request, response, locale);
		
		return "main/main";
	}
}
