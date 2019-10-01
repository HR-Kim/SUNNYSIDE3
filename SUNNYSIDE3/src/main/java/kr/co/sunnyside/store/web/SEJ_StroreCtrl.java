package kr.co.sunnyside.store.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.co.sunnyside.store.service.SEJ_StroreSvc;

@Controller
public class SEJ_StroreCtrl {
	  private final Logger LOG = LoggerFactory.getLogger(this.getClass());
	  
	  @Autowired
	  SEJ_StroreSvc storeService;
	  
	  @RequestMapping("store/do_save.do")
	  public String do_save() {
		LOG.debug("==========================");
		LOG.debug("=@Controller do_save=");
		LOG.debug("==========================");
		return "store/store_main.jsp";
		  
	  }
}
