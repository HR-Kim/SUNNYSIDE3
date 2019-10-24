package kr.co.sunnyside.faq.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.google.gson.Gson;

import kr.co.sunnyside.cmn.Message;
import kr.co.sunnyside.cmn.SearchVO;
import kr.co.sunnyside.cmn.StringUtil;
import kr.co.sunnyside.code.service.CodeVO;
import kr.co.sunnyside.faq.service.impl.LHJ_FaqSvcImpl;
import kr.co.sunnyside.movie.service.LHJ_MovieVO;
import kr.co.sunnyside.review.service.LHJ_ReviewVO;

@Controller
public class LHJ_FaqCtrl {
	 private final Logger LOG = LoggerFactory.getLogger(this.getClass());
	  
	 @Autowired
	 LHJ_FaqSvcImpl service;	
	 
	 //view
	 private final String VIEW_LIST_NM = "faq/faq_list";
	 
	
}
