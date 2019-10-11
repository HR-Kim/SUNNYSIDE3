package kr.co.sunnyside.review.web;

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
import kr.co.sunnyside.review.service.LHJ_ReviewVO;
import kr.co.sunnyside.review.service.impl.LHJ_ReviewSvcImpl;

@Controller
public class LHJ_ReviewCtrl {
	private final Logger LOG = LoggerFactory.getLogger(this.getClass());
	  
	@Autowired
	LHJ_ReviewSvcImpl service;	 
	
	@Autowired
	private CodeService codeService;
	
	//view
	private final String VIEW_LIST_NM = "review/review_list";
	private final String VIEW_MNG_NM  = "review/review_mng";
	
	/**목록조회 */
	@RequestMapping(value="review/do_retrieve.do",method = RequestMethod.GET)
	public String get_retrieve(HttpServletRequest req,SearchVO search, Model model) {
		//param
		if(search.getPageSize()==0) {
			search.setPageSize(10);
		}
		
		if(search.getPageNum()==0) {
			search.setPageNum(1);
		}		
		
		search.setSearchDiv(StringUtil.nvl(search.getSearchDiv()));
		search.setSearchWord(StringUtil.nvl(search.getSearchWord()));
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
		List<LHJ_ReviewVO> list = (List<LHJ_ReviewVO>) this.service.do_retrieve(search);
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
	@RequestMapping(value="review/do_selectOne.do",method = RequestMethod.GET)
	public String do_selectOne(LHJ_ReviewVO inVO,Model model) {
		LOG.debug("============================");
		LOG.debug("=inVO="+inVO);
		LOG.debug("============================");
		
		if(null == inVO.getMovieId() || "".equals(inVO.getMovieId().trim())) {
			throw new IllegalArgumentException("영화를 선택하세요.");
		}
		
		if(null == inVO.getUserId() || "".equals(inVO.getUserId().trim())) {
			throw new IllegalArgumentException("아이디를 선택해주세요.");
		}
		
		LHJ_ReviewVO outVO= (LHJ_ReviewVO) this.service.do_selectOne(inVO);
		model.addAttribute("vo", outVO);
		
		return VIEW_MNG_NM;
	}
	
	/**리뷰 수정 */
	@RequestMapping(value="review/do_update.do",method = RequestMethod.POST
			,produces = "application/json;charset=UTF-8")
	@ResponseBody		
	public String do_update(LHJ_ReviewVO inVO) {
		String gsonStr = "";
		LOG.debug("============================");
		LOG.debug("=inVO="+inVO);
		LOG.debug("============================");		
		if(null == inVO.getMovieId() || "".equals(inVO.getMovieId().trim())) {
			throw new IllegalArgumentException("영화를 선택하세요.");
		}
		
		if(null == inVO.getUserId() || "".equals(inVO.getUserId().trim())) {
			throw new IllegalArgumentException("로그인 후 이용해주세요.");
		}
		
		if(null == inVO.getContents() || "".equals(inVO.getContents().trim())) {
			throw new IllegalArgumentException("내용을 입력해 주세요.");
		}
		
		if(0.0 == inVO.getVisitorRate()) {
			throw new IllegalArgumentException("평점을 선택해 주세요.");
		}
		
		int flag = this.service.do_update(inVO);
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
	
	/**리뷰삭제 */
	@RequestMapping(value="review/do_delete.do",method = RequestMethod.POST,produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String do_delete(LHJ_ReviewVO inVO) {
		LOG.debug("============================");
		LOG.debug("=inVO="+inVO);
		LOG.debug("============================");
		
		int flag = this.service.do_delete(inVO);
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
	
	/**리뷰저장 */
	@RequestMapping(value="review/do_save.do",method = RequestMethod.POST,produces = "application/json;charset=UTF-8")
	@ResponseBody	
	public String do_save(LHJ_ReviewVO inVO) {
		LOG.debug("============================");
		LOG.debug("=inVO="+inVO);
		LOG.debug("============================");
	
		if(null == inVO.getMovieId() || "".equals(inVO.getMovieId().trim())) {
			throw new IllegalArgumentException("영화를 선택하세요.");
		}
		
		if(null == inVO.getUserId() || "".equals(inVO.getUserId().trim())) {
			throw new IllegalArgumentException("로그인 후 이용해주세요.");
		}
		
		int flag = this.service.do_save(inVO);
		Message  message=new Message();
		
		if(flag>0) {
			message.setMsgId(String.valueOf(flag));
			message.setMsgMsg("등록 되었습니다.");
		}else {
			message.setMsgId(String.valueOf(flag));
			message.setMsgMsg("등록 실패.");			
		}
		
		Gson gson=new Gson();
		String gsonStr = gson.toJson(message);
		
		LOG.debug("============================");
		LOG.debug("=gsonStr="+gsonStr);
		LOG.debug("============================");				
			
		return gsonStr;
	}
}
