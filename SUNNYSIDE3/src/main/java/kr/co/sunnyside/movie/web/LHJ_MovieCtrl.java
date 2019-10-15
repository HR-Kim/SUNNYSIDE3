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

import kr.co.sunnyside.cmn.DTO;
import kr.co.sunnyside.cmn.Message;
import kr.co.sunnyside.cmn.SearchVO;
import kr.co.sunnyside.cmn.StringUtil;
import kr.co.sunnyside.code.service.CodeService;
import kr.co.sunnyside.code.service.CodeVO;
import kr.co.sunnyside.main.service.LHJ_MainSvc;
import kr.co.sunnyside.movie.service.LHJ_MovieSvc;
import kr.co.sunnyside.movie.service.LHJ_MovieVO;
import kr.co.sunnyside.movie.service.impl.LHJ_BoxofficeSvcImpl;
import kr.co.sunnyside.movie.service.impl.LHJ_MovieSvcImpl;
import kr.co.sunnyside.review.service.LHJ_ReviewVO;
import kr.co.sunnyside.review.service.impl.LHJ_ReviewSvcImpl;

@Controller
public class LHJ_MovieCtrl {
	 private final Logger LOG = LoggerFactory.getLogger(this.getClass());
	  
	 @Autowired
	 LHJ_MovieSvcImpl service;	 

	 @Autowired
	 LHJ_ReviewSvcImpl reviewService;
	 
	 @Autowired
	 private CodeService codeService;
	 
	 //view
	 private final String VIEW_LIST_NM = "movie/movie_list";
	 private final String VIEW_MOVIE_DETAIL = "movie/movie_detail";
	 
	 /**목록조회 */
	 @RequestMapping(value="movie/do_retrieve.do",method = RequestMethod.GET)
	 public String do_retrieve(HttpServletRequest req,SearchVO search, Model model) {
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
	 	return VIEW_LIST_NM;
	 }

//	 /**단건조회 */
//	 @RequestMapping(value="movie/do_selectOne.do",method = RequestMethod.GET)
//	 public String do_selectOne(LHJ_MovieVO inVO,Model model) {
//		 LOG.debug("============================");
//		 LOG.debug("=inVO="+inVO);
//		 LOG.debug("============================");
//		 
//		 if(null == inVO.getMovieId() || "".equals(inVO.getMovieId())) {
//			 throw new IllegalArgumentException("Movie ID를 입력 하세요.");
//		 }
//		 
//		 LHJ_MovieVO outVO= (LHJ_MovieVO) this.service.do_selectOne(inVO);
//		 model.addAttribute("vo", outVO);
//		 
//		//param
//	 	if(inVO.getPageSize()==0) {
//	 		inVO.setPageSize(10);
//	 	}
//	 	
//	 	if(inVO.getPageNum()==0) {
//	 		inVO.setPageNum(1);
//	 	}		
//	 	
//	 	inVO.setSearchDiv(StringUtil.nvl(inVO.getSearchDiv()));
//	 	inVO.setSearchWord(StringUtil.nvl(inVO.getSearchWord()));
//	 	inVO.setMovieId(inVO.getMovieId());
//	 	model.addAttribute("searchVO", inVO);
//	 	
//	 	//목록조회
//	 	List<LHJ_ReviewVO> list = (List<LHJ_ReviewVO>) this.reviewService.do_retrieve(inVO);
//	 	model.addAttribute("list", list);
//		 
//	 	//총건수
//	 	
//		int totalCnt = 0;
//		if(null != list && list.size()>0) {
//			totalCnt = list.get(0).getTotalCnt();
//		}
//		model.addAttribute("totalCnt", totalCnt);
//	 			
//		 return VIEW_MOVIE_DETAIL;
//	 }
	 
	 /**단건조회 */
	 @RequestMapping(value="movie/do_selectOne.do",method = RequestMethod.GET)
	 public String do_selectOne(LHJ_MovieVO inVO,LHJ_ReviewVO search,Model model) {
		 LOG.debug("============================");
		 LOG.debug("=inVO="+inVO);
		 LOG.debug("============================");
		 
		 if(null == inVO.getMovieId() || "".equals(inVO.getMovieId())) {
			 throw new IllegalArgumentException("Movie ID를 입력 하세요.");
		 }
		 
		 LHJ_MovieVO outVO= (LHJ_MovieVO) this.service.do_selectOne(inVO);
		 model.addAttribute("vo", outVO);
		 
		//param
	 	if(search.getPageSize()==0) {
	 		search.setPageSize(10);
	 	}
	 	
	 	if(search.getPageNum()==0) {
	 		search.setPageNum(1);
	 	}		
	 	
	 	search.setSearchDiv(StringUtil.nvl(search.getSearchDiv()));
	 	search.setSearchWord(StringUtil.nvl(search.getSearchWord()));
	 	search.setMovieId(inVO.getMovieId());
	 	model.addAttribute("searchVO", search);
	 	
	 	//목록조회
	 	List<LHJ_ReviewVO> list = (List<LHJ_ReviewVO>) this.reviewService.do_retrieve(search);
	 	model.addAttribute("list", list);
		 
	 	//총건수
	 	
		int totalCnt = 0;
		if(null != list && list.size()>0) {
			totalCnt = list.get(0).getTotalCnt();
		}
		model.addAttribute("totalCnt", totalCnt);
	 			
		 return VIEW_MOVIE_DETAIL;
	 }
	 
//	 /**단건조회 */
//	 @RequestMapping(value="movie/do_selectOne.do",method = RequestMethod.GET)
//	 public String do_selectOne(LHJ_MovieVO inVO,Model model) {
//		 LOG.debug("============================");
//		 LOG.debug("=inVO="+inVO);
//		 LOG.debug("============================");
//
//		 if(null == inVO.getMovieId() || "".equals(inVO.getMovieId())) {
//			 throw new IllegalArgumentException("Movie ID를 입력 하세요.");
//		 }
//
//		 LHJ_MovieVO outVO= (LHJ_MovieVO) this.service.do_selectOne(inVO);
//		 model.addAttribute("vo", outVO);
//	
//		 return VIEW_MOVIE_DETAIL;
//	 }
	 
		/** 영화정보 저장 */
		@RequestMapping(value = "movie/do_save.do", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
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
