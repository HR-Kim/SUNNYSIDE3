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
import kr.co.sunnyside.movie.service.impl.LHJ_ScreeningSvcImpl;

@Controller
public class LHJ_ScreeningCtrl {
	private final Logger LOG = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	LHJ_ScreeningSvcImpl service;	 

	@Autowired
	private CodeService codeService;
	 
	//view
	private final String VIEW_LIST_NM = "screening/screening_list";
	private final String VIEW_MOVIE_DETAIL  = "movie/movie_detail";
	 private final String VIEW_SCREEN_UP  = "screening/screeningCtrl/screening_up";
	 private final String VIEW_SCREEN_DOWN  = "screening/screeningCtrl/screening_down";
	
	 /**최신 개봉 삭제 리스트 조회 */
	 @RequestMapping(value="screening/do_screenDown_retrieve.do",method = RequestMethod.GET)
	 public String do_screenDown_retrieve(HttpServletRequest req,SearchVO search, Model model) {
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
		 
		 //게시판 검색 구분
		 code.setCodeId("MOVIE_SEARCH");
		 List<CodeVO> listBoardSearch=(List<CodeVO>) this.codeService.do_retrieve(code);		
		 model.addAttribute("listBoardSearch", listBoardSearch);
		 
		 //목록조회
		 List<LHJ_MovieVO> list = (List<LHJ_MovieVO>) this.service.do_retrieve(search);
		 model.addAttribute("list", list);
		 
		 //총건수
		 int totalCnt = 0;
		 if(null != list && list.size()>0) {
			 totalCnt = list.get(0).getTotalCnt();
		 }
		 model.addAttribute("totalCnt", totalCnt);
		 return VIEW_SCREEN_DOWN;
	 }
	 
	/**최신 개봉 등록 리스트 조회 */
	 @RequestMapping(value="screening/do_screenUp_retrieve.do",method = RequestMethod.GET)
	 public String do_screenUp_retrieve(HttpServletRequest req,SearchVO search, Model model) {
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
	 	
	 	//게시판 검색 구분
	 	code.setCodeId("MOVIE_SEARCH");
	 	List<CodeVO> listBoardSearch=(List<CodeVO>) this.codeService.do_retrieve(code);		
	 	model.addAttribute("listBoardSearch", listBoardSearch);
	 	
	 	//목록조회
	 	List<LHJ_MovieVO> list = (List<LHJ_MovieVO>) this.service.do_screenUp_retrieve(search);
	 	model.addAttribute("list", list);
	 	
	 	//총건수
	 	int totalCnt = 0;
	 	if(null != list && list.size()>0) {
	 		totalCnt = list.get(0).getTotalCnt();
	 	}
	 	model.addAttribute("totalCnt", totalCnt);
	 	return VIEW_SCREEN_UP;
	 }
	 
	 /**목록조회 */
	 @RequestMapping(value="screening/do_retrieve.do",method = RequestMethod.GET)
	 public String do_retrieve(HttpServletRequest req,SearchVO search, Model model) {
		 //param
		 if(search.getPageSize()==0) {
			 search.setPageSize(12);
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
	 @RequestMapping(value="screening/do_selectOne.do",method = RequestMethod.GET)
	 public String do_selectOne(LHJ_MovieVO inVO,Model model) {
		 LOG.debug("============================");
		 LOG.debug("=inVO="+inVO);
		 LOG.debug("============================");

		 if(null == inVO.getMovieId() || "".equals(inVO.getMovieId())) {
			 throw new IllegalArgumentException("Movie ID를 입력 하세요.");
		 }

		 LHJ_MovieVO outVO= (LHJ_MovieVO) this.service.do_selectOne(inVO);
		 model.addAttribute("vo", outVO);
	
		 return VIEW_MOVIE_DETAIL;
	 }
	 
	 /** 최신개봉으로 상태 변경(상영중으로 상태 변경)(010)  */
	 @RequestMapping(value="screening/do_update_screenUp.do",method = RequestMethod.POST
			 ,produces = "application/json;charset=UTF-8")
	 @ResponseBody		
	 public String do_update_screenUp(LHJ_MovieVO inVO) {
		 String gsonStr = "";
		 LOG.debug("============================");
		 LOG.debug("=inVO="+inVO);
		 LOG.debug("============================");		
		 if(null == inVO.getMovieId() || "".equals(inVO.getMovieId().trim())) {
			 throw new IllegalArgumentException("Movie ID를 입력 하세요.");
		 }
		 
		 int flag = this.service.do_update_screenUp(inVO);
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
	 @RequestMapping(value="screening/do_update_screenDown.do",method = RequestMethod.POST
			 ,produces = "application/json;charset=UTF-8")
	 @ResponseBody		
	 public String do_update_screenDown(LHJ_MovieVO inVO) {
		 String gsonStr = "";
		 LOG.debug("============================");
		 LOG.debug("=inVO="+inVO);
		 LOG.debug("============================");		
		 if(null == inVO.getMovieId() || "".equals(inVO.getMovieId().trim())) {
			 throw new IllegalArgumentException("Movie ID를 입력 하세요.");
		 }
		 
		 int flag = this.service.do_update_screenDown(inVO);
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
}
