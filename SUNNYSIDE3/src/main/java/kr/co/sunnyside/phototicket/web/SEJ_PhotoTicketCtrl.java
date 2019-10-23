package kr.co.sunnyside.phototicket.web;

import java.io.File;

import static kr.co.sunnyside.cmn.StringUtil_N.IMAGE_ROOT;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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
import org.springframework.web.servlet.View;


import kr.co.sunnyside.cmn.StringUtil;
import kr.co.sunnyside.cmn.StringUtil_N;
import kr.co.sunnyside.phototicket.service.SEJ_FileVO;
import kr.co.sunnyside.phototicket.service.SEJ_MovieHistoryVO;
import kr.co.sunnyside.phototicket.service.SEJ_PhotoTicketSvc;
import kr.co.sunnyside.phototicket.service.SEJ_PhotoTicketVO;

@Controller
public class SEJ_PhotoTicketCtrl {

	Logger LOG = LoggerFactory.getLogger(this.getClass());
	
	private static final String VIEW_NAME   = "phototicket/phototicket";
	
	@Autowired
	SEJ_PhotoTicketSvc SPT;
	
	@Resource(name="downloadView")
	private View download;
	
	@RequestMapping(value="phototicket/do_retrieve.do",method=RequestMethod.GET)
	public String do_retrieve(HttpServletRequest req,Model model,SEJ_PhotoTicketVO inVO,HttpSession session) {
		
		inVO.setUser_id("u5");
		List<SEJ_PhotoTicketVO> moiveList = (List<SEJ_PhotoTicketVO>) SPT.do_retrieve(inVO);
		
		model.addAttribute("moiveList", moiveList);
//		for(SEJ_PhotoTicketVO vo:list) {
//			SPT.do_insert(vo);
//		}
		
		
		
		return VIEW_NAME;
		
		
	}
	
	
	@RequestMapping(value="phototicket/download.do",method = RequestMethod.POST)
	public ModelAndView download(HttpServletRequest req, ModelAndView mView) {
		//----------------------------------------------------
        //			download.do
        //	file.jsp  ->  FileController.java
        //       				-download()  -> View(downloadView) 
		//		                 		 -> DownloadView.java
		//		                 		 	-renderMergedOutputModel()
		//		                 		 	-setDownloadFileName
		//		                 		 	-downloadFile
		//----------------------------------------------------
		
		
		String orgFileNm  = req.getParameter("orgFileNm");// 원본파일명
		String saveFileNm = req.getParameter("saveFileNm");// 저장파일명 
		LOG.debug("===============================");
		LOG.debug("=@Controller orgFileNm="+orgFileNm);
		LOG.debug("=@Controller saveFileNm="+saveFileNm);
		LOG.debug("===============================");		
		// File downloadFile= (File) model.get("downloadFile");
		// String orgFileNm = (String) model.get("orgFileNm");
		mView.setView(download);
		
		File  downloadFile=new File(saveFileNm);
		mView.addObject("downloadFile", downloadFile);
		mView.addObject("orgFileNm", orgFileNm);
		
		return mView;
	}
	
	//http://localhost:8080/ehr/file/uploadfileview.do
	@RequestMapping(value="image/uploadfileview.do")
	public String uploadFileView() {
		LOG.debug("===============================");
		LOG.debug("=@Controller uploadFileView=");
		LOG.debug("===============================");
		return VIEW_NAME;
	}
	
	   
	
	
	//ModelAndView : Model + View
	@RequestMapping(value="phototicket/do_save.do",method = RequestMethod.POST)
	public ModelAndView do_save(MultipartHttpServletRequest mReg 
			   , ModelAndView model,SEJ_PhotoTicketVO inVO) throws IllegalStateException, IOException {
		String selected= (String) mReg.getParameter("selected");
		inVO.setUser_id("u5");
		inVO.setTicket_code(selected);
		
		SEJ_PhotoTicketVO selectMovie = (SEJ_PhotoTicketVO) SPT.do_selectOne(inVO);
		
		
		LOG.debug("===============================");
		LOG.debug("=@Controller do_save=");
		LOG.debug("===============================");
		//Upload파일 정보: 원본,저장,사이즈,확장자 List
		
		
		
		
		//01.동적으로 UPLOAD_ROOT 디렉토리 생성

		File  fileRootDir =new File(IMAGE_ROOT);

		
		
		
		if(fileRootDir.isDirectory() ==false) {  
			boolean flag = fileRootDir.mkdirs();
			LOG.debug("=@Controller flag="+flag);
		}
		
		
		
		if(fileRootDir.isDirectory()==false) {
			boolean flag = fileRootDir.mkdirs();  
			LOG.debug("=@Controller fileYearMM flag="+flag);
		}
		
		//01.파일 Read      
		Iterator<String> files = mReg.getFileNames();
		while(files.hasNext()) {
			SEJ_PhotoTicketVO imageVO=new SEJ_PhotoTicketVO();
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
				ext = orgFileNm.substring(orgFileNm.indexOf(".")+1);
			}
			LOG.debug("=@Controller fileSize="+fileSize);
			LOG.debug("=@Controller ext="+ext);
			File orgFileCheck = new File(IMAGE_ROOT,orgFileNm);
			
			String newFile = orgFileCheck.getAbsolutePath();
			//04.파일 rename: README -> README1~9999
			SEJ_FileVO outVO=null;
			if(orgFileCheck.exists()==true) {
				outVO = StringUtil_N.fileRename(orgFileCheck);
				newFile=outVO.getF();
			}
			selectMovie.setOrg_img_nm(orgFileNm);
			selectMovie.setSave_img_nm(newFile);
			selectMovie.setImg_size(fileSize);
			selectMovie.setExt(ext);
			if(outVO==null) {
				selectMovie.setThisFileNm(orgFileNm);
			}
			else {
				selectMovie.setThisFileNm(outVO.getBody()+outVO.getCount()+"."+ext);
			}
			
			
			mFile.transferTo(new File(newFile));
		
		}

		
		model.addObject("selectMovie",selectMovie);
		model.setViewName(VIEW_NAME);
		SPT.do_insert(selectMovie);
		List<SEJ_PhotoTicketVO> moiveList = (List<SEJ_PhotoTicketVO>) SPT.do_retrieve(inVO);
		model.addObject("moiveList", moiveList);
		return model;
	}

	private void StringUtil(String uPLOAD_ROOT) {
		// TODO Auto-generated method stub
		
	}
	
}
