package kr.co.sunnyside.theater.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class SJH_TheaterCtrl {

	Logger LOG = LoggerFactory.getLogger(this.getClass());
	
	//홍대 영화관정보 화면call
	@RequestMapping(value="theater/hongdae_info.do", method = RequestMethod.GET)
	public String hongdaeInfo() {
		LOG.debug("========================");
		LOG.debug("=@Controller=View=");
		LOG.debug("========================");
		return "theater/hongdae_info";
	}
	
	//홍대 약도 화면call
	@RequestMapping(value="theater/hongdae_api.do", method = RequestMethod.GET)
	public String hongdaeApi() {
		LOG.debug("========================");
		LOG.debug("=@Controller=View=");
		LOG.debug("========================");
		return "theater/hongdae_api";
	}
	
	//강남 영화관정보 화면call
	@RequestMapping(value="theater/kangnam_info.do", method = RequestMethod.GET)
	public String kangnamInfo() {
		LOG.debug("========================");
		LOG.debug("=@Controller=View=");
		LOG.debug("========================");
		return "theater/kangnam_info";
	}
	
	//강남 약도 화면call
	@RequestMapping(value="theater/kangnam_api.do", method = RequestMethod.GET)
	public String kangnamApi() {
		LOG.debug("========================");
		LOG.debug("=@Controller=View=");
		LOG.debug("========================");
		return "theater/kangnam_api";
	}
	
	
	
	
}
