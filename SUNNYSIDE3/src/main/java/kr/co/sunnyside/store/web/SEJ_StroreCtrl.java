package kr.co.sunnyside.store.web;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;

import kr.co.sunnyside.cmn.Message;
import kr.co.sunnyside.cmn.SearchVO;
import kr.co.sunnyside.cmn.StringUtil;
import kr.co.sunnyside.code.service.CodeService;
import kr.co.sunnyside.code.service.CodeVO;
import kr.co.sunnyside.store.service.SEJ_StroreSvc;
import kr.co.sunnyside.store.service.SEJ_StroreVO;

@Controller
public class SEJ_StroreCtrl {
	  private final Logger LOG = LoggerFactory.getLogger(this.getClass());
	  
	  @Autowired
	  SEJ_StroreSvc storeService;
	  
	   @Autowired
	   CodeService codeService;
	  //view
	   private final String VIEW_LIST_NM = "store/store_main";
	  
	   private static final String UPLOAD_ROOT = "D:\\HR_FILE"; 
	   
	 //상품등록 페이지  
	  @RequestMapping("store/write.do")
	  public String write() {
		  return "/store/store_add";
	  }
	  
	  @RequestMapping(value = "store/do_save.do", method =RequestMethod.GET, produces = "application/json;charset=UTF-8" )
	  @ResponseBody
	  public String do_save(SEJ_StroreVO store){
		LOG.debug("==========================");
		LOG.debug("=@Controller do_save="+ store);
		LOG.debug("==========================");
		 
		//store 값이 없을 때
	      if(null ==store.getProductNm() || "".equals(store.getProductNm().trim())) {
	    	  throw new IllegalArgumentException("상품 이름을 입력하세요");
	      } 
	      
	      if(0 ==store.getProductCost() || "".equals(store.getPruductInfo().trim())) {
	    	  throw new IllegalArgumentException("상품금액을 입력하세요");
	      }
	      
	      if(null ==store.getPruductInfo() || "".equals(store.getPruductInfo().trim())) {
	    	  throw new IllegalArgumentException("상품정보를 입력하세요");
	      }
	      
	      int flag = this.storeService.do_save(store);
	      Message message = new Message();
	      if(flag>0) { //flag가 1이면 성공
	         message.setMsgId(String.valueOf(flag));
	         message.setMsgMsg("등록되었습니다.");
	      }else {//아니면 실패
	         message.setMsgId(String.valueOf(flag));
	         message.setMsgMsg("등록 실패.");
	      }
	      
	      Gson gson = new Gson();
	      String gsonStr = gson.toJson(message);
	      LOG.debug("===============================");
	      LOG.debug("=gsonStr="+gsonStr);
	      LOG.debug("===============================");
	     
	     return gsonStr;
	}
		  
	  
	  @RequestMapping(value = "store/do_retrieve.do",method = RequestMethod.GET)
	  public String do_retrieve(SearchVO search, Model model) {
		    LOG.debug("1.=====================");
			LOG.debug("1.= param="+search);
			LOG.debug("1.=====================");
			
			//디폴트 값 설정 페이지사이즈:10	
			if(search.getPageSize() ==0) {
				search.setPageSize(10);
			}
			
			//디폴트 값 설정 페이지번호:1
			if(search.getPageNum() ==0) {
				search.setPageNum(1);
			}
			
			search.setSearchDiv(StringUtil.nvl(search.getSearchDiv()));
			search.setSearchWord(StringUtil.nvl(search.getSearchWord()));
			
			LOG.debug("2.=====================");
			LOG.debug("2.=param="+search);
			LOG.debug("2.=====================");
			model.addAttribute("vo", search);
			
			//code
			CodeVO code = new CodeVO();
			code.setCodeId("CATEGORY");
			
			//code 정보조회
			List<CodeVO> codeList = (List<CodeVO>) codeService.do_retrieve(code);
			model.addAttribute("codeList",codeList);
						
			List<SEJ_StroreSvc> list = (List<SEJ_StroreSvc>) this.storeService.do_retrieve(search);
			model.addAttribute("list", list);
			
			
			return VIEW_LIST_NM;
	  }
	  

}			
			

