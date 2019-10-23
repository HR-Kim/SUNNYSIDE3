package kr.co.sunnyside.main.web;

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

import com.google.gson.Gson;

import static kr.co.sunnyside.cmn.StringUtil.MAIN_IMAGE_ROOT;

import kr.co.sunnyside.cmn.Message;
import kr.co.sunnyside.cmn.StringUtil;
import kr.co.sunnyside.code.service.CodeService;
import kr.co.sunnyside.code.service.CodeVO;
import kr.co.sunnyside.file.service.FileVO;
import kr.co.sunnyside.main.service.LHJ_MainImageVO;
import kr.co.sunnyside.main.service.impl.LHJ_MainSvcImpl;
import kr.co.sunnyside.movie.service.LHJ_MovieVO;
import kr.co.sunnyside.movie.service.impl.LHJ_BoxofficeSvcImpl;
import kr.co.sunnyside.movie.service.impl.LHJ_ScreeningSvcImpl;
import kr.co.sunnyside.notice.service.KYMNoticeVO;

@Controller
public class LHJ_MainCtrl {
	private final Logger LOG = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	LHJ_MainSvcImpl service;
	
	@Autowired
	LHJ_BoxofficeSvcImpl boxofficeService;	 

	@Autowired
	private CodeService codeService;
	 
	//view
	private final String VIEW_MAIN_NM = "main/main";
	private final String VIEW_BANNER_LIST = "main/banner/main_banner_edit";
	
	
	/**베너이미지 삭제*/
	@RequestMapping(value="main/do_image_delete.do",method = RequestMethod.POST,produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String do_image_delete(LHJ_MainImageVO inVO) {
		int flag = this.service.do_image_delete(inVO);
		
		Message  message=new Message();
		
		if(flag>0) {
			message.setMsgId(String.valueOf(flag));
			message.setMsgMsg("삭제 되었습니다.");
		}else {
			message.setMsgId(String.valueOf(flag));
			message.setMsgMsg("삭제 실패.");			
		}
		
		Gson gson=new Gson();
		String gsonStr = gson.toJson(message);
		
		LOG.debug("============================");
		LOG.debug("=gsonStr="+gsonStr);
		LOG.debug("============================");		
		
		return gsonStr;
	}	
	
	/**베너이미지 전체 조회*/
	@RequestMapping(value="main/do_banner_retrieve.do",method = RequestMethod.GET)
	public String do_banner_retrieve(LHJ_MovieVO inVO,Model model) {
		
		List<LHJ_MainImageVO> bannerList = (List<LHJ_MainImageVO>) this.service.do_banner_retrieve();
		model.addAttribute("bannerList", bannerList);

		return VIEW_BANNER_LIST;
	}
		
	/**베너이미지 저장*/
	@RequestMapping(value="main/do_image_save.do",method = RequestMethod.POST,produces = "application/json;charset=UTF-8")
	@ResponseBody	
	public String do_image_save(MultipartHttpServletRequest mReg) throws IllegalStateException, IOException {
		LOG.debug("===============================");
		LOG.debug("=@Controller do_image_save=");
		LOG.debug("===============================");
		//Upload파일 정보: 원본,저장,사이즈,확장자 List
		List<LHJ_MainImageVO> fileList = new ArrayList<LHJ_MainImageVO>();
		
		
		//01.동적으로 MAIN_IMAGE_ROOT 디렉토리 생성
		File  fileRootDir = new File(MAIN_IMAGE_ROOT);
		if(fileRootDir.isDirectory() ==false) {  
			boolean flag = fileRootDir.mkdirs();
			LOG.debug("=@Controller flag="+flag);
		}
		
		//02.년월 디렉토리 생성:D:\\HR_FILE\2019\10
		String yyyy = StringUtil.cureDate("yyyy");
		LOG.debug("=@Controller yyyy="+yyyy);
		String mm = StringUtil.cureDate("MM");
		LOG.debug("=@Controller mm="+mm);
		String datePath = MAIN_IMAGE_ROOT+File.separator+yyyy+File.separator+mm;
		LOG.debug("=@Controller datePath="+datePath);
		
		File  fileYearMM = new File(datePath);  
		
		if(fileYearMM.isDirectory()==false) {
			boolean flag = fileYearMM.mkdirs();  
			LOG.debug("=@Controller fileYearMM flag="+flag);
		}
		
		int flag  =0;
		Message message=new Message();
		
		//01.파일 Read      
		Iterator<String> files = mReg.getFileNames();
		while(files.hasNext()) {
			LHJ_MainImageVO fileVO=new LHJ_MainImageVO();
			String orgFileNm  = "";//원본파일명
			String saveFileNm = "";//저장파일명
			long   fileSize   = 0L;//파일사이즈
			String ext        = "";//확장자
			
			String uploadFileNm = files.next();//file01
			MultipartFile mFile = mReg.getFile(uploadFileNm);
			orgFileNm = mFile.getOriginalFilename();
			//file선택이 않되면 continue
			if(null==orgFileNm || orgFileNm.equals(""))continue;
			
			
			LOG.debug("=@Controller uploadFileNm="+uploadFileNm);
			LOG.debug("=@Controller orgFileNm="+orgFileNm);
			fileSize = mFile.getSize();//file size byte
			
			if(orgFileNm.indexOf(".")>-1) {
				ext = orgFileNm.substring(orgFileNm.lastIndexOf(".")+1);
			}
			LOG.debug("=@Controller fileSize="+fileSize);
			LOG.debug("=@Controller ext="+ext);
			File orgFileCheck = new File(datePath,orgFileNm);
			
			String newFile = orgFileCheck.getAbsolutePath();
			//04.파일 rename: README -> README1~9999
			if(orgFileCheck.exists()==true) {
				newFile = StringUtil.fileRename(orgFileCheck);
			}
			
			String saveImgNm = ".."+newFile.substring(newFile.indexOf("\\resources")).replace("\\", "/");
			
			fileVO.setOrgImgNm(orgFileNm);
			fileVO.setSaveImgNm(saveImgNm);
			fileVO.setImgSize(fileSize);
			fileVO.setExt(ext);
			fileList.add(fileVO);
			mFile.transferTo(new File(newFile));
			
			flag = service.do_image_save(fileVO);
			LOG.debug("flag:"+flag);
		}

		//등록성공
		if(flag>0) {
			message.setMsgId(String.valueOf(flag));
			message.setMsgMsg("등록성공.");	
		//등록실패	
		}else {
			message.setMsgId(String.valueOf(flag));
			message.setMsgMsg("등록실패.");	
		}
		Gson gson=new Gson();
		
		String gsonStr = gson.toJson(message);
		LOG.debug("gsonStr:"+gsonStr);
		return gsonStr;
	}
	
	/**목록조회 */
	@RequestMapping(value="main/main.do",method = RequestMethod.GET)
	public String do_retrieve(LHJ_MovieVO inVO,Model model) {

		
		List<LHJ_MainImageVO> bannerList = (List<LHJ_MainImageVO>) this.service.do_banner_retrieve();
		model.addAttribute("bannerList", bannerList);
		
		List<LHJ_MovieVO> boxofficeList = (List<LHJ_MovieVO>) this.boxofficeService.do_retrieve_main();
		model.addAttribute("boxofficeList", boxofficeList);
		
		List<KYMNoticeVO> noticeList = (List<KYMNoticeVO>) this.service.do_notice_retrieve();
		model.addAttribute("noticeList", noticeList);

		return VIEW_MAIN_NM;
	}
}
