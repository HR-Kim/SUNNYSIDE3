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
import kr.co.sunnyside.cmn.StringUtil;
import kr.co.sunnyside.code.service.CodeService;
import kr.co.sunnyside.code.service.CodeVO;
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
	 private final String VIEW_LIST_NM = "boxoffice/boxoffice_list";
	 private final String VIEW_MOVIE_DETAIL  = "movie/movie_detail";
	 
	 /**목록조회 */
	@RequestMapping(value="boxoffice/do_retrieve.do",method = RequestMethod.GET)
	public String get_retrieve(HttpServletRequest req, Model model) {
		
		//목록조회
		List<LHJ_MovieVO> list = (List<LHJ_MovieVO>) this.service.do_retrieve();
		model.addAttribute("list", list);

		return VIEW_LIST_NM;
	}
		
	/**단건조회 */
	@RequestMapping(value="boxoffice/do_selectOne.do",method = RequestMethod.GET)
	public String do_selectOne(LHJ_MovieVO inVO,Model model) {
		LOG.debug("============================");
		LOG.debug("=LHJ_MovieVO="+inVO);
		LOG.debug("============================");
		
		if(null == inVO.getMovieId() || "".equals(inVO.getMovieId())) {
			throw new IllegalArgumentException("Movie ID를 입력 하세요.");
		}
		
		LHJ_MovieVO outVO= (LHJ_MovieVO) this.service.do_selectOne(inVO);
		model.addAttribute("vo", outVO);
		
		return VIEW_MOVIE_DETAIL;
	}
		
	 /**박스오피스 상태값 0으로 초기화(OFF)*/
	 @RequestMapping(value="boxoffice/do_boxofficeOff_update.do",method = RequestMethod.POST
			 ,produces = "application/json;charset=UTF-8")
	 @ResponseBody		
	 public String do_boxofficeOff_update(LHJ_MovieVO inVO) {
		 String gsonStr = "";
		 LOG.debug("============================");
		 LOG.debug("=inVO="+inVO);
		 LOG.debug("============================");
		 
		 int flag = service.do_boxofficeOff_update(inVO);
		 Message  message=new Message();
		 
		 if(flag>0) {
			 message.setMsgId(String.valueOf(flag));
			 message.setMsgMsg("박스오피스 상태값이 0으로 수정 되었습니다.");
		 }else {
			 message.setMsgId(String.valueOf(flag));
			 message.setMsgMsg("박스오피스 상태값 0으로 수정 실패.");			
		 }
		 
		 Gson gson=new Gson();
		 gsonStr = gson.toJson(message);	
		 
		 return gsonStr;	
	 }
	 
	 /**박스오피스 상태값 1으로 초기화(On) */
	@RequestMapping(value="boxoffice/do_boxofficeOn_update.do",method = RequestMethod.POST
	,produces = "application/json;charset=UTF-8")
	@ResponseBody		
	public String do_boxofficeOn_update(LHJ_MovieVO inVO) {
		String gsonStr = "";
		LOG.debug("============================");
		LOG.debug("=inVO="+inVO);
		LOG.debug("============================");
		
		int flag = service.do_boxofficeOn_update(inVO);
		Message  message=new Message();
		
		if(flag>0) {
			message.setMsgId(String.valueOf(flag));
			message.setMsgMsg("박스오피스 상태값이 1로 수정 되었습니다.");
		}else {
			message.setMsgId(String.valueOf(flag));
			message.setMsgMsg("박스오피스 상태값 1로 수정 실패.");			
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
		
	/** 박스오피스 테이블에 movieId, 순위 정보 저장 */
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
