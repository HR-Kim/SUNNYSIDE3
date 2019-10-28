package kr.co.sunnyside.qna.web;

import java.io.File;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;

import com.google.gson.Gson;

import kr.co.sunnyside.cmn.Message;
import kr.co.sunnyside.cmn.SearchVO;
import kr.co.sunnyside.cmn.StringUtil;
import kr.co.sunnyside.code.service.CodeVO;
import kr.co.sunnyside.notice.service.KYMBranchVO;
import kr.co.sunnyside.notice.service.KYMNoticeVO;
import kr.co.sunnyside.qna.service.KYMQnaSvc;
import kr.co.sunnyside.qna.service.KYMQnaVO;
import kr.co.sunnyside.code.service.CodeService;

@Controller
public class KYMServiceQnaCtrl {
	
	Logger LOG = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	KYMQnaSvc kymQnaSvc;
	
	@Autowired
	CodeService codeService;
	
	@Resource(name = "downloadView")
	private View download;
	
	//view
	private final String VIEW_LIST_NM = "qna/qna_list";
	private final String VIEW_MNG_NM = "qna/qna_mng";
	private final String VIEW_ADMIN_LIST_NM = "qna/qna_list_admin";
	private final String VIEW_ADMIN_MNG_NM = "qna/qna_mng_admin";
	
	/**수정 */
	@RequestMapping(value="qna/do_update.do",method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody		
	public String do_update(KYMQnaVO kymQnaVO) {
		String gsonStr = "";
		LOG.debug("============================");
		LOG.debug("=kymQnaVO="+kymQnaVO);
		LOG.debug("============================");		
		if(null == kymQnaVO.getUserId() || "".equals(kymQnaVO.getUserId().trim())) {
			throw new IllegalArgumentException("ID를 입력 하세요.");
		}
		
		if(null == kymQnaVO.getTitle() || "".equals(kymQnaVO.getTitle().trim())) {
			throw new IllegalArgumentException("제목을 입력 하세요.");
		}
		
		if(null == kymQnaVO.getContents() || "".equals(kymQnaVO.getContents().trim())) {
			throw new IllegalArgumentException("내용을 입력 하세요.");
		}		
		
		int flag = this.kymQnaSvc.do_update(kymQnaVO);
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
	
	@RequestMapping(value="qna/do_update_admin.do",method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody		
	public String do_update_admin(KYMQnaVO kymQnaVO) {
		String gsonStr = "";
		LOG.debug("============================");
		LOG.debug("=kymQnaVO="+kymQnaVO);
		LOG.debug("============================");		
		if(null == kymQnaVO.getUserId() || "".equals(kymQnaVO.getUserId().trim())) {
			throw new IllegalArgumentException("ID를 입력 하세요.");
		}
		
		
		int flag = this.kymQnaSvc.do_updateTwo(kymQnaVO);
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
	@RequestMapping(value = "qna/do_delete.do", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String do_delete(KYMQnaVO kymQnaVO) {
		LOG.debug("================");
		LOG.debug("kymConsultingVO"+kymQnaVO);
		LOG.debug("================");
		
		int flag = this.kymQnaSvc.do_delete(kymQnaVO);
		Message message = new Message();
		
		if(flag>0) {
			message.setMsgId(String.valueOf(flag));
			message.setMsgMsg("삭제 되었습니다.");
			
		}else {
			message.setMsgId(String.valueOf(flag));
			message.setMsgMsg("삭제 실패.");
			
		}
		
		Gson gson = new Gson();
		String gsonStr = gson.toJson(message);
		
		LOG.debug("================");
		LOG.debug("gsonStr"+gsonStr);
		LOG.debug("================");
		
		return gsonStr;
		
	}

	/**저장 */
	@RequestMapping(value="qna/do_save.do",method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody	
	public String do_save(KYMQnaVO kymQnaVO, HttpSession session) {
		LOG.debug("============================");
		LOG.debug("=kymQnaVO="+kymQnaVO);
		LOG.debug("============================");
		
		if(null == kymQnaVO.getTitle() || "".equals(kymQnaVO.getTitle().trim())) {
			throw new IllegalArgumentException("제목을 입력 하세요.");
		}
		
		if(null == kymQnaVO.getContents() || "".equals(kymQnaVO.getContents().trim())) {
			throw new IllegalArgumentException("내용을 입력 하세요.");
		}
		
		int flag = this.kymQnaSvc.do_save(kymQnaVO);
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

	/**단건조회 */
	@RequestMapping(value = "qna/do_selectOne.do", method = RequestMethod.GET)
	public String do_selectOne(KYMQnaVO kymQnaVO, Model model) {
		LOG.debug("================");
		LOG.debug("kymQnaVO"+kymQnaVO);
		LOG.debug("================");
		
		if(null == kymQnaVO.getQnaNum() || "".equals(kymQnaVO.getQnaNum())) {
			throw new IllegalArgumentException("QnaNum를 확인하세요");
			
		}
		
		KYMQnaVO outVO = (KYMQnaVO) this.kymQnaSvc.do_selectOne(kymQnaVO);
		model.addAttribute("vo", outVO);
		
		return VIEW_MNG_NM;
		
	}
	
	@RequestMapping(value = "qna/do_selectOne_admin.do", method = RequestMethod.POST)
	public String do_selectOne_admin(KYMQnaVO kymQnaVO, Model model) {
		LOG.debug("================");
		LOG.debug("kymQnaVO"+kymQnaVO);
		LOG.debug("================");
		
		if(null == kymQnaVO.getUserId() || "".equals(kymQnaVO.getUserId())) {
			throw new IllegalArgumentException("ID를 확인하세요");
			
		}
		
		KYMQnaVO outVO = (KYMQnaVO) this.kymQnaSvc.do_selectOne(kymQnaVO);
		model.addAttribute("vo", outVO);
		
		return VIEW_ADMIN_MNG_NM;
		
	}
	
	/**목록조회 */
	@RequestMapping(value="qna/do_retrieve.do",method = RequestMethod.GET)
	public String do_retrieve(HttpServletRequest req, SearchVO search, Model model) {
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
		
		CodeVO code = new CodeVO();
		code.setCodeId("PAGE_SIZE");
		
		List<CodeVO> listPageSize=(List<CodeVO>) this.codeService.do_retrieve(code);
		model.addAttribute("listPageSize", listPageSize);
		
		//게시판 검색 구분
		code.setCodeTypeId("QNA_SEARCH");
		List<CodeVO> listQnaSearch=(List<CodeVO>) this.kymQnaSvc.do_retrieveTwo(code);		
		model.addAttribute("listQnaSearch", listQnaSearch);
		
		//목록조회
		List<KYMQnaVO> list = (List<KYMQnaVO>) this.kymQnaSvc.do_retrieve(search);
		model.addAttribute("list", list);
		
		//총건수
		int totalCnt = 0;
		if(null != list && list.size()>0) {
			totalCnt = list.get(0).getTotalCnt();
			
		}
		
		model.addAttribute("totalCnt", totalCnt);
		
		return VIEW_LIST_NM;
		
	}
	
	@RequestMapping(value="qna/do_retrieve_admin.do",method = RequestMethod.GET)
	public String do_retrieve_admin(HttpServletRequest req, SearchVO search, Model model) {
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
		
		CodeVO code = new CodeVO();
		code.setCodeId("PAGE_SIZE");
		
		List<CodeVO> listPageSize=(List<CodeVO>) this.codeService.do_retrieve(code);
		model.addAttribute("listPageSize", listPageSize);
		
		//게시판 검색 구분
		code.setCodeTypeId("QNA_SEARCH");
		List<CodeVO> listQnaSearch=(List<CodeVO>) this.kymQnaSvc.do_retrieveTwo(code);		
		model.addAttribute("listQnaSearch", listQnaSearch);
		
		//목록조회
		List<KYMQnaVO> list = (List<KYMQnaVO>) this.kymQnaSvc.do_retrieve(search);
		model.addAttribute("list", list);
		
		//총건수
		int totalCnt = 0;
		if(null != list && list.size()>0) {
			totalCnt = list.get(0).getTotalCnt();
			
		}
		
		model.addAttribute("totalCnt", totalCnt);
		
		return VIEW_ADMIN_LIST_NM;
		
	}
	
	@RequestMapping(value="qna/qna_mng.do", method = RequestMethod.GET)	
	public String qnaRegView(KYMQnaVO kymQnaVO, Model model) {
		
		return VIEW_MNG_NM;

	}
	
	@RequestMapping(value="qna/qna_mng_admin_.do", method = RequestMethod.POST)	
	public String adminqnaRegView(KYMQnaVO kymQnaVO, Model model) {
		
		return VIEW_ADMIN_LIST_NM;

	}
	
}
