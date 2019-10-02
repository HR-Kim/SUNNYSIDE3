package kr.co.sunnyside.store.web;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.co.sunnyside.cmn.SearchVO;
import kr.co.sunnyside.cmn.StringUtil;
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
	  
//	  @RequestMapping(value = "store/get_retrieve.do",method = RequestMethod.GET)
//	  public String get_retrieve(SearchVO search, Model model) {
//		    LOG.debug("1.=====================");
//			LOG.debug("1.= param="+search);
//			LOG.debug("1.=====================");
//			
//			//디폴트 값 설정 페이지사이즈:10	
//			if(search.getPageSize() ==0) {
//				search.setPageSize(10);
//			}
//			
//			//디폴트 값 설정 페이지번호:1
//			if(search.getPageNum() ==0) {
//				search.setPageNum(1);
//			}
//			
//			search.setSearchDiv(StringUtil.nvl(search.getSearchDiv()));
//			search.setSearchWord(StringUtil.nvl(search.getSearchWord()));
//			
//			LOG.debug("2.=====================");
//			LOG.debug("2.=param="+search);
//			LOG.debug("2.=====================");
//			model.addAttribute("vo", search);
//			
//			//code
//			Code code = new Code();
//			code.setCodeId("PAGE_SIZE");
//			
//			//code 정보조회
//			List<Code> codeList = (List<Code>) codeService.get_retrieve(code);
//			model.addAttribute("codeList",codeList);
//			
//			code.setCodeId("USER_SEARCH");
//			
//			//code 정보조회
//			List<Code> codeSearchList = (List<Code>) codeService.get_retrieve(code);
//			model.addAttribute("codeSearchList",codeSearchList);
//			
//			List<User> list = (List<User>) this.userService.get_retrieve(search);
//			model.addAttribute("list", list);
//			
//			//총건수
//			int totalCnt =0;
//			if(null!=list &&list.size()>0) {
//				totalCnt= list.get(0).getTotalCnt();
//			}
//			model.addAttribute("totalCnt", totalCnt);
//			
//			return VIEW_NM;
//	  }
	  

}			
			

