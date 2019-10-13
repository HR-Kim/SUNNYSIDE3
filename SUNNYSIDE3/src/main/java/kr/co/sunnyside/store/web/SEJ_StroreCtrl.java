package kr.co.sunnyside.store.web;
import static kr.co.sunnyside.cmn.StringUtil.UPLOAD_ROOT;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;


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
import org.springframework.web.servlet.View;

import com.google.gson.Gson;

import kr.co.sunnyside.cmn.Message;
import kr.co.sunnyside.cmn.SearchVO;
import kr.co.sunnyside.cmn.StringUtil;
import kr.co.sunnyside.store.service.SEJ_StroreSvc;
import kr.co.sunnyside.store.service.SEJ_StroreVO;

@Controller
public class SEJ_StroreCtrl {
	  private final Logger LOG = LoggerFactory.getLogger(this.getClass());
	  
	  @Autowired
	  SEJ_StroreSvc storeService;
	  
//	  @Autowired
//	  SEJ_StroreVO store;
	  
	  @Resource(name="downloadView")
	  private View download; 
	  
	   
	  //view
	   private final String VIEW_LIST_NM = "store/store_main";
	   private final String VIEW_POPCORN_LIST_NM = "store/popcorn_list";
	   private final String VIEW_DRINK_LIST_NM = "store/drink_list";
	   private final String VIEW_TICKET_LIST_NM = "store/ticket_list";
	   private final String VIEW_SELECTONE = "store/selectproduct";
	   private final String VIEW_MNG_NM = "store/store_add";
	 
	   
	   @RequestMapping(value = "store/uploadimage.do")
		public String uploadFileView() {
			LOG.debug("======================");
			LOG.debug("=@Controller uploadimage=");
			LOG.debug("======================");
			
			return VIEW_MNG_NM;
		}
	  
	  /**
	   * 상품저장
	   * @param store
	   * @return
	 * @throws Exception 
	 * @throws IOException 
	   */
	  @RequestMapping(value = "store/do_save.do", method =RequestMethod.POST, produces = "application/json;charset=UTF-8" )
	  @ResponseBody
	  public String do_save(MultipartHttpServletRequest mReg) throws IllegalStateException, IOException{
		LOG.debug("==========================");
		LOG.debug("=@Controller do_save=");
		LOG.debug("==========================");
		//Upload 파일 정보: 원본, 저장, 사이즈, 확장자 리스트
		List<SEJ_StroreVO> fileList = new ArrayList<SEJ_StroreVO>();
		
		//입력한 것 꺼내기 
		String productId = StringUtil.nvl(mReg.getParameter("productId"));
		String productName = StringUtil.nvl(mReg.getParameter("productName"));
		String productInfo = StringUtil.nvl(mReg.getParameter("productInfo"));
		String category = mReg.getParameter("category");
		String productCost = mReg.getParameter("productCost");
		 
		LOG.debug("======================");
		LOG.debug("=@Controller productName="+productName);
		LOG.debug("=@Controller productId="+productId);
		LOG.debug("=@Controller productInfo="+productInfo);
		LOG.debug("=@Controller category="+category);
		LOG.debug("=@Controller productCost="+productCost);
		
		//상품이름값이 없을 때
		if(productName.equals("")) {
			throw new ArithmeticException("상품이름을 입력해주세요.");
		}
		LOG.debug("=****@Controller productName="+productName);
	      
		File fileRootDir = new File(UPLOAD_ROOT);
		if(fileRootDir.isDirectory()==false) {//디렉토리 유무 체크
			boolean flag = fileRootDir.mkdirs();
			LOG.debug("=@Controller flag="+flag);
			
		}
		
		//동적으로 년/월 별 디렉토리 생성 : D:\\Product\2019\10
		String yyyy = StringUtil.cureDate("yyyy");
		LOG.debug("=@Controller yyyy="+yyyy);
		String mm = StringUtil.cureDate("MM");
		LOG.debug("=@Controller yyyy="+mm);
		
		//D:\\Product\2019\10
		String datePath = UPLOAD_ROOT +File.separator+yyyy+File.separator+mm;
		LOG.debug("=@Controller datePath="+datePath);
		
		File fileYearMM = new File(datePath);
		if(fileYearMM.isDirectory()==false) {
			boolean flag = fileYearMM.mkdirs();
			LOG.debug("=@Controller fileYearMM flag="+flag);
		}
		int flag  =0;
		Message message=new Message();
		//파일 읽기
		Iterator<String> files = mReg.getFileNames();
		
		while(files.hasNext()) {
			SEJ_StroreVO stroreVO = new SEJ_StroreVO();
			
			String orgFileNM =""; //원본파일 명
			String saveFileNM =""; //저장파일 명
			String ext =""; //확장자
			
			String uploadFileNm = files.next();
			MultipartFile mFile = mReg.getFile(uploadFileNm);
			orgFileNM= mFile.getOriginalFilename();//원본파일 명
			
			if(null ==orgFileNM ||orgFileNM.equals("")) continue; //null값이 아닐때만 실행되도록. 
			LOG.debug("=@Controller uploadFileNm="+uploadFileNm);
			LOG.debug("=@Controller orgFileNM="+orgFileNM);
			
			if(orgFileNM.indexOf(".")>-1) {
				ext = orgFileNM.substring(orgFileNM.indexOf(".")+1);				
			}
			LOG.debug("=@Controller ext="+ext);
			
			//중복이름일 경우 새 이름으로 rename
			File orgFileCheck = new File(datePath, orgFileNM);
			String newFile = orgFileCheck.getAbsolutePath(); 
			if(orgFileCheck.exists()==true) {//파일이 존재하면 rename 파일
				newFile = StringUtil.fileRename(orgFileCheck);
			}
			
			stroreVO.setOrgFileNm(orgFileNM);
			stroreVO.setSaveFileNm(newFile);
			stroreVO.setExt(ext);
			stroreVO.setProductId(productId);
			stroreVO.setProductNm(productName);
			stroreVO.setProductInfo(productInfo);
			stroreVO.setCategory(Integer.parseInt(category));
			stroreVO.setProductCost(Integer.parseInt(productCost));
			fileList.add(stroreVO);
					
			mFile.transferTo(new File(newFile));
			flag = storeService.do_save(stroreVO);
			LOG.debug("flag:"+flag);	
		}
		//json처리를 위한 메시지vo
		
		if(flag>0) {//등록성공
			message.setMsgId(String.valueOf(flag));
			message.setMsgMsg("등록성공");
		
		}else {//등록실패	
			message.setMsgId(String.valueOf(flag));
			message.setMsgMsg("등록실패");			
		}
		
		Gson gson=new Gson();
		
		String gsonStr = gson.toJson(message);
		LOG.debug("gsonStr:"+gsonStr);
		return gsonStr;   
	}
	  
	  /**
	   * 삭제
	   * @param store
	   * @return
	   */
	  @RequestMapping(value="store/do_delete.do", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	  @ResponseBody //이거 없으면 json으로 안나옴
	  public String do_delete(SEJ_StroreVO store) {
	     LOG.debug("===============================");
	     LOG.debug("=store="+store);
	     LOG.debug("===============================");
	     
	  
	     //파일 삭제 // 아직 못함 
	     SEJ_StroreVO stroreVO = new SEJ_StroreVO();
	    // File file = new File(stroreVO.getSaveFileNm());
	     LOG.debug("===============================");
	     LOG.debug("=stroreVO.getSaveFileNm()="+stroreVO.getSaveFileNm());
	     LOG.debug("===============================");
	    // file.delete();
	     
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
			
			return VIEW_SELECTONE;
		}   

		/**
		   * 전체조회
		   * 조회만 get
		   * @param search
		   * @param model
		   * @return
		   */
		  @RequestMapping(value = "store/do_retrieve.do", method = RequestMethod.GET)
		  public String do_retrieve_all(SearchVO search, Model model) {
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
			
							
				List<SEJ_StroreSvc> list = (List<SEJ_StroreSvc>) this.storeService.do_retrieve(search);
				model.addAttribute("list", list);
				
				
				return VIEW_LIST_NM;
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
			
			
			return VIEW_POPCORN_LIST_NM;
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
			
			
			return VIEW_DRINK_LIST_NM;
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
			
			
			return VIEW_TICKET_LIST_NM;
	  }
	  
	 
	  

}			
			

