package kr.co.sunnyside.movie.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;

import kr.co.sunnyside.cmn.Message;
import kr.co.sunnyside.code.service.CodeService;
import kr.co.sunnyside.movie.service.LHJ_BoxofficeVO;
import kr.co.sunnyside.movie.service.LHJ_MovieVO;
import kr.co.sunnyside.movie.service.impl.LHJ_BoxofficeSvcImpl;

@Controller
public class LHJ_BoxofficeCtrl {
	 private final Logger LOG = LoggerFactory.getLogger(this.getClass());
	  
	 @Autowired
	 LHJ_BoxofficeSvcImpl service;	 

	 @Autowired
	 private CodeService codeService;
	 
	 //view
	 private final String VIEW_LIST_NM = "boxoffice/boxoffice";
	 
	 /**수정 */
	@RequestMapping(value="boxoffice/do_rank_update.do",method = RequestMethod.POST
	,produces = "application/json;charset=UTF-8")
	@ResponseBody		
	public String do_rank_update(LHJ_MovieVO inVO) {
		String gsonStr = "";
		LOG.debug("============================");
		LOG.debug("=inVO="+inVO);
		LOG.debug("============================");
		
		int flag = service.do_save(inVO);
		Message  message=new Message();
		
		if(flag>0) {
			message.setMsgId(String.valueOf(flag));
			message.setMsgMsg("수정 되었습니다.");
		}else {
			message.setMsgId(String.valueOf(flag));
			message.setMsgMsg("수정 실패.");			
		}
		
		Gson gson=new Gson();
		gsonStr = gson.toJson(message);	
		
		return gsonStr;	
	}
	
	 /**삭제 */
	@RequestMapping(value="boxoffice/do_delete.do",method = RequestMethod.POST
													,produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String do_delete() {
		int flag = this.service.do_delete();
		
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
		
	/** 저장 */
	@RequestMapping(value = "boxoffice/do_save.do", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String do_save(LHJ_MovieVO inVO) {
		LOG.debug("===============================");
		LOG.debug("=inVO=" + inVO);
		LOG.debug("===============================");
		
		int flag = this.service.do_save(inVO);
		
		Message message = new Message();
		if (flag > 0) { // flag가 1이면 성공
			message.setMsgId(String.valueOf(flag));
			message.setMsgMsg("등록되었습니다.");
		} else {// 아니면 실패
			message.setMsgId(String.valueOf(flag));
			message.setMsgMsg("등록 실패.");
		}
		
		Gson gson = new Gson();
		String gsonStr = gson.toJson(message);
		LOG.debug("===============================");
		LOG.debug("=gsonStr=" + gsonStr);
		LOG.debug("===============================");
		
		return gsonStr;
	}
}
