package kr.co.sunnyside.store.web;


import java.io.File;
import java.io.IOException;
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

import com.google.gson.Gson;

import kr.co.sunnyside.cmn.Message;
import kr.co.sunnyside.cmn.SearchVO;
import kr.co.sunnyside.cmn.StringUtil;
import kr.co.sunnyside.store.image.UploadImage;
import kr.co.sunnyside.store.service.SEJ_StroreSvc;
import kr.co.sunnyside.store.service.SEJ_StroreVO;

@Controller
public class SEJ_StroreCtrl {
	  private final Logger LOG = LoggerFactory.getLogger(this.getClass());
	  
	  @Autowired
	  SEJ_StroreSvc storeService;
	  
	  @Autowired
	  UploadImage uploadImage;
	  
	  //업로드할 곳
	  public static final String UPLOAD_ROOT = "D:\\Product";
	   
	  //view
	   private final String VIEW_LIST_NM = "store/store_main";
	   private final String VIEW_MNG_NM = "store/store_add";
	 
	  
	  /**
	   * 상품저장
	   * @param store
	   * @return
	 * @throws Exception 
	 * @throws IOException 
	   */
	  @RequestMapping(value = "store/do_save.do", method =RequestMethod.POST, produces = "application/json;charset=UTF-8" )
	  @ResponseBody
	  public String do_save(SEJ_StroreVO store, MultipartFile file) throws IOException, Exception{
		LOG.debug("==========================");
		LOG.debug("=@Controller do_save="+ store);
		LOG.debug("==========================");
		
		String imgPath = uploadImage.calcPath(UPLOAD_ROOT);
		String fileName = null;

		if(file != null) {
		 fileName =  uploadImage.fileUpload(UPLOAD_ROOT, file.getOriginalFilename(), file.getBytes(), imgPath); 
		} else {
		 fileName = UPLOAD_ROOT + File.separator + "images" + File.separator + "none.png";
		}

		store.setImg(imgPath + File.separator + fileName);
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
	  
//	  /**
//	   * 상품저장
//	   * @param store
//	   * @return
//	   */
//	  @RequestMapping(value = "store/do_save.do", method =RequestMethod.POST, produces = "application/json;charset=UTF-8" )
//	  @ResponseBody
//	  public String do_save(SEJ_StroreVO store){
//		LOG.debug("==========================");
//		LOG.debug("=@Controller do_save="+ store);
//		LOG.debug("==========================");
//		 
//		//store 값이 없을 때
//	      if(null ==store.getProductNm() || "".equals(store.getProductNm().trim())) {
//	    	  throw new IllegalArgumentException("상품 이름을 입력하세요");
//	      } 
//	      
//	      if(0 ==store.getProductCost() || "".equals(store.getPruductInfo().trim())) {
//	    	  throw new IllegalArgumentException("상품금액을 입력하세요");
//	      }
//	      
//	      if(null ==store.getPruductInfo() || "".equals(store.getPruductInfo().trim())) {
//	    	  throw new IllegalArgumentException("상품정보를 입력하세요");
//	      }
//	      
//	      int flag = this.storeService.do_save(store);
//	      Message message = new Message();
//	      if(flag>0) { //flag가 1이면 성공
//	         message.setMsgId(String.valueOf(flag));
//	         message.setMsgMsg("등록되었습니다.");
//	      }else {//아니면 실패
//	         message.setMsgId(String.valueOf(flag));
//	         message.setMsgMsg("등록 실패.");
//	      }
//	      
//	      Gson gson = new Gson();
//	      String gsonStr = gson.toJson(message);
//	      LOG.debug("===============================");
//	      LOG.debug("=gsonStr="+gsonStr);
//	      LOG.debug("===============================");
//	     
//	     return gsonStr;
//	}
	  /**
	   * 삭제
	   * @param store
	   * @return
	   */
	  @RequestMapping(value="store/do_delete.do", method = RequestMethod.POST, 
              produces = "application/json;charset=UTF-8")
	  @ResponseBody //이거 없으면 json으로 안나옴
	  public String do_delete(SEJ_StroreVO store) {
	     LOG.debug("===============================");
	     LOG.debug("=board="+store);
	     LOG.debug("===============================");
	     
	     int flag = this.storeService.do_delete(store);
	     Message message = new Message();
	     if(flag>0) { //flag가 1이면 성공
	        message.setMsgId(String.valueOf(flag));
	        message.setMsgMsg("삭제되었습니다.");
	     }else {//아니면 실패
	        message.setMsgId(String.valueOf(flag));
	        message.setMsgMsg("삭제 실패.");
	     }
	     
	     Gson gson = new Gson();
	     String gsonStr = gson.toJson(message);
	     LOG.debug("===============================");
	     LOG.debug("=gsonStr="+gsonStr);
	     LOG.debug("===============================");
	     
	     return gsonStr;
	  }
	  
	  /**
	    * 수정
	    * @param store
	    * @return
	    */
	   @RequestMapping(value="store/do_update.do", method = RequestMethod.POST, 
              produces = "application/json;charset=UTF-8")
	   @ResponseBody //이거 없으면 json으로 안나옴
		public String do_update(SEJ_StroreVO store) {
		   
		   LOG.debug("===============================");
		   LOG.debug("=store="+store);
		   LOG.debug("===============================");
		   
		   //board 값이 없을 때
		   if(null ==store.getProductNm() || "".equals(store.getProductNm().trim())) {
		   	  throw new IllegalArgumentException("상품이름을 입력하세요");
		   } 
		   
		   if(0 ==store.getProductCost()) {
		   	  throw new IllegalArgumentException("상품금액을 입력하세요");
		   }
		   
		  int flag = this.storeService.do_update(store);
		  Message message = new Message();
		  if(flag>0) { //flag가 1이면 성공
		         message.setMsgId(String.valueOf(flag));
		         message.setMsgMsg("수정되었습니다.");
		      }else {//아니면 실패
		         message.setMsgId(String.valueOf(flag));
		         message.setMsgMsg("수정 실패.");
		      }
		      
		      Gson gson = new Gson();
		      String gsonStr = gson.toJson(message);
		      LOG.debug("===============================");
		      LOG.debug("=gsonStr="+gsonStr);
		      LOG.debug("===============================");
		  
		      return gsonStr;
		}
	  
		  
	  /**
	    * 단건조회
	    * 조회만 get
	    * @param store
	    * @param model
	    * @return
	    */
		@RequestMapping(value="store/do_selectOne.do", method = RequestMethod.GET)
		public String get_selectOne(SEJ_StroreVO store,Model model) {
		      LOG.debug("===============================");
		      LOG.debug("=store="+store);
		      LOG.debug("===============================");

		     SEJ_StroreVO outVO = (SEJ_StroreVO) this.storeService.do_selectOne(store);
		     model.addAttribute("vo",outVO);
			
			return VIEW_MNG_NM;
		}   
	  
	  /**
	   * 팝콘 전체조회
	   * 조회만 get
	   * @param search
	   * @param model
	   * @return
	   */
	  @RequestMapping(value = "store/do_retrieve_popcorn.do", method = RequestMethod.GET)
	  public String do_retrieve_popcorn(SearchVO search, Model model) {
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
			
			LOG.debug("2.=====================");
			LOG.debug("2.=param="+search);
			LOG.debug("2.=====================");
			model.addAttribute("vo", search);
		
						
			List<SEJ_StroreSvc> list = (List<SEJ_StroreSvc>) this.storeService.do_retrieve_popcorn(search);
			model.addAttribute("list", list);
			
			
			return VIEW_LIST_NM;
	  }
	  
	  /**
	   * 음료 전체조회
	   * 조회만 get
	   * @param search
	   * @param model
	   * @return
	   */
	  @RequestMapping(value = "store/do_retrieve_drink.do", method = RequestMethod.GET)
	  public String do_retrieve_drink(SearchVO search, Model model) {
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
			
			LOG.debug("2.=====================");
			LOG.debug("2.=param="+search);
			LOG.debug("2.=====================");
			model.addAttribute("vo", search);
		
						
			List<SEJ_StroreSvc> list = (List<SEJ_StroreSvc>) this.storeService.do_retrieve_drink(search);
			model.addAttribute("list", list);
			
			
			return VIEW_LIST_NM;
	  }
	  
	  /**
	   * 영화예매권 전체조회
	   * 조회만 get
	   * @param search
	   * @param model
	   * @return
	   */
	  @RequestMapping(value = "store/do_retrieve_movieticket.do", method = RequestMethod.GET)
	  public String do_retrieve_movieticket(SearchVO search, Model model) {
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
			
			LOG.debug("2.=====================");
			LOG.debug("2.=param="+search);
			LOG.debug("2.=====================");
			model.addAttribute("vo", search);
		
						
			List<SEJ_StroreSvc> list = (List<SEJ_StroreSvc>) this.storeService.do_retrieve_movieticket(search);
			model.addAttribute("list", list);
			
			
			return VIEW_LIST_NM;
	  }
	  

}			
			

