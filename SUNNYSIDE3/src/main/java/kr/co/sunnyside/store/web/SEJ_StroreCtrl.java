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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

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
	  
	  @RequestMapping("store/do_save.do")
	  public ModelAndView do_save(MultipartHttpServletRequest mReg, ModelAndView model)throws IllegalStateException, IOException {
		LOG.debug("==========================");
		LOG.debug("=@Controller do_save=");
		LOG.debug("==========================");
		List<SEJ_StroreVO> fileList = new ArrayList<SEJ_StroreVO>();
		
		File fileRootDir = new File(UPLOAD_ROOT); 
		if(fileRootDir.isDirectory()==false) {//디렉토리 유무 체크
			boolean flag = fileRootDir.mkdirs();
			LOG.debug("=@Controller flag="+flag);
			
		}
		//동적으로 년/월 별 디렉토리 생성 : D:\\HR_FILE\2019\09
		String yyyy = StringUtil.cureDate("yyyy");
		LOG.debug("=@Controller yyyy="+yyyy);
		String mm = StringUtil.cureDate("MM");
		LOG.debug("=@Controller yyyy="+mm);
		
		//D:\\HR_FILE\2019\09 
		String datePath = UPLOAD_ROOT +File.separator+yyyy+File.separator+mm;
		LOG.debug("=@Controller datePath="+datePath);
		
		File fileYearMM = new File(datePath);
		if(fileYearMM.isDirectory()==false) {
			boolean flag = fileYearMM.mkdirs();
			LOG.debug("=@Controller fileYearMM flag="+flag);
		}
		//파일 읽기
				Iterator<String> files = mReg.getFileNames();
				while(files.hasNext()) {
					SEJ_StroreVO fileVO = new SEJ_StroreVO(); 
					
					String orgFileNM =""; //원본파일 명
					String saveFileNM =""; //저장파일 명
					long fileSize =0L; //파일 사이즈
					String ext =""; //확장자
					
					String uploadFileNm = files.next(); //file01,file02,file03 이런식으로 계속 들어옴
					MultipartFile mFile = mReg.getFile(uploadFileNm);
					orgFileNM= mFile.getOriginalFilename(); //원본파일
					
					if(null ==orgFileNM ||orgFileNM.equals("")) continue; //null값이 아닐때만 실행되도록. 
					
					LOG.debug("=@Controller uploadFileNm="+uploadFileNm);
					LOG.debug("=@Controller orgFileNM="+orgFileNM);
					
					fileSize = mFile.getSize(); //파일 사이즈
					if(orgFileNM.indexOf(".")>-1) {
						ext = orgFileNM.substring(orgFileNM.indexOf(".")+1);				
					}
					LOG.debug("=@Controller fileSize="+fileSize);
					LOG.debug("=@Controller ext="+ext);
					
					//중복이름일 경우 새 이름으로 rename
					File orgFileCheck = new File(datePath, orgFileNM);
					
					String newFile = orgFileCheck.getAbsolutePath(); 
					if(orgFileCheck.exists()==true) {//파일이 존재하면 rename 파일
						newFile = StringUtil.fileRename(orgFileCheck);
					}
					fileVO.setOrgFileNM(orgFileNM);
					fileVO.setSaveFileNM(newFile);
					fileVO.setImageSize(fileSize);
					fileVO.setExt(ext);
					fileList.add(fileVO);
					
					mFile.transferTo(new File(newFile));
					
				}
				model.addObject("fileList",fileList);
				
				//파일 rename: README -> README1~9999	
				
				model.setViewName(VIEW_LIST_NM);
				return model;
	}
		  
	  
	  @RequestMapping(value = "store/get_retrieve.do",method = RequestMethod.GET)
	  public String get_retrieve(SearchVO search, Model model) {
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
			code.setCodeId("PAGE_SIZE");
			
			//code 정보조회
			List<CodeVO> codeList = (List<CodeVO>) codeService.get_retrieve(code);
			model.addAttribute("codeList",codeList);
			
			code.setCodeId("USER_SEARCH");
			
			//code 정보조회
			List<CodeVO> codeSearchList = (List<CodeVO>) codeService.get_retrieve(code);
			model.addAttribute("codeSearchList",codeSearchList);
			
			List<SEJ_StroreSvc> list = (List<SEJ_StroreSvc>) this.storeService.get_retrieve(search);
			model.addAttribute("list", list);
			
			
			return VIEW_LIST_NM;
	  }
	  

}			
			

