package kr.co.sunnyside.movie.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;

import kr.co.sunnyside.cmn.Message;
import kr.co.sunnyside.cmn.SearchVO;
import kr.co.sunnyside.code.service.CodeService;
import kr.co.sunnyside.code.service.CodeVO;
import kr.co.sunnyside.movie.service.LHJ_MovieVO;
import kr.co.sunnyside.movie.service.impl.LHJ_PlanedSvcImpl;

@Controller
public class LHJ_PlanedCtrl {
	 private final Logger LOG = LoggerFactory.getLogger(this.getClass());
	  
	 @Autowired
	 LHJ_PlanedSvcImpl service;	 

	 @Autowired
	 private CodeService codeService;
	 
	 //view
	 private final String VIEW_LIST_NM = "planed/planed_list";
	 private final String VIEW_MNG_NM  = "planed/planed_mng";
	 
	 /**목록조회 */
	 @RequestMapping(value="planed/do_retrieve.do",method = RequestMethod.GET)
	 public String do_retrieve(HttpServletRequest req,SearchVO search, Model model) {
		 //param
		 if(search.getPageSize()==0) {
			 search.setPageSize(10);
		 }
	
		 if(search.getPageNum()==0) {
			 search.setPageNum(1);
		 }		

		 model.addAttribute("vo", search);
	
		 LOG.debug("============================");
		 LOG.debug("=search="+search);
		 LOG.debug("============================");		
	
		 CodeVO code=new CodeVO();
		 //페이지사이즈
		 code.setCodeId("PAGE_SIZE");
	
		 List<CodeVO> listPageSize=(List<CodeVO>) this.codeService.do_retrieve(code);
		 model.addAttribute("listPageSize", listPageSize);
	
		 //목록조회
		 List<LHJ_MovieVO> list = (List<LHJ_MovieVO>) this.service.do_retrieve(search);
		 model.addAttribute("list", list);
	
		 //총건수
		 int totalCnt = 0;
		 if(null != list && list.size()>0) {
			 totalCnt = list.get(0).getTotalCnt();
		 }
		 model.addAttribute("totalCnt", totalCnt);
		 return VIEW_LIST_NM;
	 }
		
	 /**단건조회 */
	 @RequestMapping(value="planed/do_selectOne.do",method = RequestMethod.GET)
	 public String do_selectOne(LHJ_MovieVO inVO,Model model) {
		 LOG.debug("============================");
		 LOG.debug("=inVO="+inVO);
		 LOG.debug("============================");

		 if(null == inVO.getMovieId() || "".equals(inVO.getMovieId())) {
			 throw new IllegalArgumentException("Movie ID를 입력 하세요.");
		 }

		 LHJ_MovieVO outVO= (LHJ_MovieVO) this.service.do_selectOne(inVO);
		 model.addAttribute("vo", outVO);
	
		 return VIEW_MNG_NM;
	 }
		
	 /** 최신개봉으로 상태 변경(상영중으로 상태 변경)(010)  */
	 @RequestMapping(value="planed/do_update_planedToScreen.do",method = RequestMethod.POST
			 ,produces = "application/json;charset=UTF-8")
	 @ResponseBody		
	 public String do_update_planedToScreen(LHJ_MovieVO inVO) {
		 String gsonStr = "";
		 LOG.debug("============================");
		 LOG.debug("=inVO="+inVO);
		 LOG.debug("============================");		
		 if(null == inVO.getMovieId() || "".equals(inVO.getMovieId().trim())) {
			 throw new IllegalArgumentException("Movie ID를 입력 하세요.");
		 }
		 
		 int flag = this.service.do_update_planedToScreen(inVO);
		 Message  message=new Message();
		 
		 if(flag>0) {
			 message.setMsgId(String.valueOf(flag));
			 message.setMsgMsg("최신개봉으로 상태 변경되었습니다.");
		 }else {
			 message.setMsgId(String.valueOf(flag));
			 message.setMsgMsg("최신개봉으로 상태 변경을 실패했습니다.");			
		 }
		 
		 Gson gson=new Gson();
		 gsonStr = gson.toJson(message);			
		 
		 return gsonStr;		
	 }
	 
	 /** 비 상영상태로 상태 변경(000)  */
	 @RequestMapping(value="planed/do_update_planedDown.do",method = RequestMethod.POST
			 ,produces = "application/json;charset=UTF-8")
	 @ResponseBody		
	 public String do_update_planedDown(LHJ_MovieVO inVO) {
		 String gsonStr = "";
		 LOG.debug("============================");
		 LOG.debug("=inVO="+inVO);
		 LOG.debug("============================");		
		 if(null == inVO.getMovieId() || "".equals(inVO.getMovieId().trim())) {
			 throw new IllegalArgumentException("Movie ID를 입력 하세요.");
		 }
		 
		 int flag = this.service.do_update_planedDown(inVO);
		 Message  message=new Message();
		 
		 if(flag>0) {
			 message.setMsgId(String.valueOf(flag));
			 message.setMsgMsg("비 상영상태로 상태 변경되었습니다.");
		 }else {
			 message.setMsgId(String.valueOf(flag));
			 message.setMsgMsg("비 상영상태로 상태 변경을 실패했습니다.");			
		 }
		 
		 Gson gson=new Gson();
		 gsonStr = gson.toJson(message);			
		 
		 return gsonStr;		
	 }
	 
	 /** 개봉예정으로 상태 변경(020)  */
	 @RequestMapping(value="planed/do_update_planedUp.do",method = RequestMethod.POST
			 ,produces = "application/json;charset=UTF-8")
	 @ResponseBody		
	 public String do_update_planedUp(LHJ_MovieVO inVO) {
		 String gsonStr = "";
		 LOG.debug("============================");
		 LOG.debug("=inVO="+inVO);
		 LOG.debug("============================");		
		if(null == inVO.getMovieId() || "".equals(inVO.getMovieId().trim())) {
			throw new IllegalArgumentException("Movie ID를 입력 하세요.");
		}
		
		int flag = this.service.do_update_planedUp(inVO);
		Message  message=new Message();
		
		if(flag>0) {
			message.setMsgId(String.valueOf(flag));
			message.setMsgMsg("개봉예정으로 상태 변경되었습니다.");
		}else {
			message.setMsgId(String.valueOf(flag));
			message.setMsgMsg("개봉예정으로 상태 변경을 실패했습니다.");			
		}
		
		Gson gson=new Gson();
		gsonStr = gson.toJson(message);			
		
		return gsonStr;		
	}
	 
}
