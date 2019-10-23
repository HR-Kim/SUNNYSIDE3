package kr.co.sunnyside.file.web;

import java.io.File;

import static kr.co.sunnyside.cmn.StringUtil.UPLOAD_ROOT;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

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
import org.springframework.web.servlet.View;

import com.google.gson.Gson;

import kr.co.sunnyside.cmn.Message;
import kr.co.sunnyside.cmn.StringUtil;
import kr.co.sunnyside.file.service.FileService;
import kr.co.sunnyside.file.service.FileVO;

@Controller
public class FileController {

	Logger LOG = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private FileService fileService;
	
	private static final String VIEW_NAME   = "file/file";
	
	@Resource(name="downloadView")
	private View download;
	
	@RequestMapping(value="file/do_delete.do",method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String do_delete(kr.co.sunnyside.file.service.File inVO) {
		LOG.debug("=============================");
		LOG.debug("=inVO=="+inVO);
		LOG.debug("=============================");
		
		//------------------------------------------
		//1. db에서 파일 삭제:fileId,num
		//------------------------------------------
		int flag = fileService.do_delete(inVO);
		LOG.debug("=============================");
		LOG.debug("=1 flag=="+flag);
		LOG.debug("=============================");
		
		Message msg=new Message();
		msg.setMsgId(String.valueOf(flag));
		//파일 삭제 성공 
		if(flag>0) {
			
			//------------------------------------------
			//2. 물리적 파일 삭제: saveFileNm
			//------------------------------------------
			File delFile=new File(inVO.getSaveFileNm());
			boolean delFlag = delFile.delete();
			LOG.debug("=============================");
			LOG.debug("=2 .delFlag=="+delFlag);
			LOG.debug("=============================");
			
			msg.setMsgMsg("삭제 되었습니다.");
		}else {
			msg.setMsgMsg("삭제 실패");
		}
		
		Gson gson=new Gson();
		String gsonStr= gson.toJson(msg);
		
		LOG.debug("=============================");
		LOG.debug("=3 .gsonStr=="+gsonStr);
		LOG.debug("=============================");
		
		return gsonStr;
		
	}
	
	@RequestMapping(value="file/download.do",method = RequestMethod.POST)
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
	@RequestMapping(value="file/uploadfileview.do")
	public String uploadFileView() {
		LOG.debug("===============================");
		LOG.debug("=@Controller uploadFileView=");
		LOG.debug("===============================");
		return VIEW_NAME;
	}
	
	@RequestMapping(value="file/do_retrieve.do",method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody	
	public String do_fileList(kr.co.sunnyside.file.service.File inVO) {
		LOG.debug("=============================");
		LOG.debug("=inVO=="+inVO);
		LOG.debug("=============================");
		
		List<kr.co.sunnyside.file.service.File> fileList = (List<kr.co.sunnyside.file.service.File>) fileService.do_retrieve(inVO);
		LOG.debug("=============================");
		LOG.debug("=fileList=="+fileList);
		LOG.debug("=============================");
		
		Gson gson=new Gson();
		String json = gson.toJson(fileList);
		LOG.debug("=============================");
		LOG.debug("=json=="+json);
		LOG.debug("=============================");		
		
		
		return json;
	}
	
	//ModelAndView : Model + View
	@RequestMapping(value="file/do_save.do",method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody	
	public String do_save(MultipartHttpServletRequest mReg) throws IllegalStateException, IOException {
		LOG.debug("===============================");
		LOG.debug("=@Controller do_save=");
		LOG.debug("===============================");
		//Upload파일 정보: 원본,저장,사이즈,확장자 List
		List<kr.co.sunnyside.file.service.File> fileList = new ArrayList<kr.co.sunnyside.file.service.File>();
		
		String workDiv = StringUtil.nvl(mReg.getParameter("work_div"));
		String fileId = StringUtil.nvl(mReg.getParameter("attrFileId"));

		LOG.debug("=@Controller workDiv="+workDiv);
		LOG.debug("=@Controller fileId="+fileId);
		
		//01.동적으로 UPLOAD_ROOT 디렉토리 생성
		File  fileRootDir = new File(UPLOAD_ROOT);
		if(fileRootDir.isDirectory() ==false) {  
			boolean flag = fileRootDir.mkdirs();
			LOG.debug("=@Controller flag="+flag);
		}
		
		//02.년월 디렉토리 생성:D:\\HR_FILE\2019\10
		String yyyy = StringUtil.cureDate("yyyy");
		LOG.debug("=@Controller yyyy="+yyyy);
		String mm = StringUtil.cureDate("MM");
		LOG.debug("=@Controller mm="+mm);
		String datePath = UPLOAD_ROOT+File.separator+yyyy+File.separator+mm;
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
			kr.co.sunnyside.file.service.File fileVO = new kr.co.sunnyside.file.service.File();
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
			
			//-----------------------------------------------
			//-FileId 존재 유무로 Key생성 유무 판단.
			//-----------------------------------------------
			//FileId 없는 경우
			if(fileId.equals("0") || fileId.length()!=40) {
				String yyyymmdd = StringUtil.cureDate("yyyyMMdd");
				String fileIdKey= yyyymmdd+StringUtil.getUUID();
				LOG.debug("yyyymmdd:"+yyyymmdd);
				LOG.debug("fileIdKey:"+fileIdKey);
				fileVO.setFileId(fileIdKey);
				fileVO.setNum(1);
				fileId = fileIdKey;
			//fileID가 있는 경우.	
			}else {
				
				fileVO.setFileId(fileId);
				//max num
				int maxNum = this.fileService.num_max_plus_one(fileVO);
				LOG.debug("maxNum:"+maxNum);
				fileVO.setNum(maxNum);
			}
			
			fileVO.setOrgFileNm(orgFileNm);
			fileVO.setSaveFileNm(newFile);
			fileVO.setfSize(fileSize);
			fileVO.setExt(ext);
			fileList.add(fileVO);
			mFile.transferTo(new File(newFile));
			
			flag = fileService.do_save(fileVO);
			LOG.debug("flag:"+flag);
		}

		//등록성공
		if(flag>0) {
			message.setMsgId(String.valueOf(flag));
			message.setMsgMsg(fileId);
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
	
}
