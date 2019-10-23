package kr.co.sunnyside.notice.web;

import static kr.co.sunnyside.cmn.StringUtil.UPLOAD_ROOT;

import java.io.File;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;

import com.google.gson.Gson;

import kr.co.sunnyside.cmn.Message;
import kr.co.sunnyside.cmn.SearchVO;
import kr.co.sunnyside.cmn.StringUtil;
import kr.co.sunnyside.code.service.CodeVO;
import kr.co.sunnyside.code.service.CodeService;
import kr.co.sunnyside.notice.service.KYMBranchVO;
import kr.co.sunnyside.notice.service.KYMNoticeSvc;
import kr.co.sunnyside.notice.service.KYMNoticeVO;

@Controller
public class KYMServiceNoticeCtrl {
	
	Logger LOG = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	KYMNoticeSvc kymNoticeSvc;
	
	@Autowired
	CodeService codeService;
	
	@Resource(name = "downloadView")
	private View download;
	
	//view
	private final String VIEW_LIST_NM = "notice/notice_list";
	private final String VIEW_MNG_NM = "notice/notice_mng";
	
	/**수정 */
	@RequestMapping(value="notice/do_update.do",method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody		
	public String do_update(KYMNoticeVO kymNoticeVO) {
		String gsonStr = "";
		LOG.debug("============================");
		LOG.debug("=kymNoticeVO="+kymNoticeVO);
		LOG.debug("============================");		
		if(null == kymNoticeVO.getNoticeId() || "".equals(kymNoticeVO.getNoticeId().trim())) {
			throw new IllegalArgumentException("ID를 입력 하세요.");
		}
		
		if(null == kymNoticeVO.getTitle() || "".equals(kymNoticeVO.getTitle().trim())) {
			throw new IllegalArgumentException("제목을 입력 하세요.");
		}
		
		if(null == kymNoticeVO.getContents() || "".equals(kymNoticeVO.getContents().trim())) {
			throw new IllegalArgumentException("내용을 입력 하세요.");
		}		
		
		int flag = this.kymNoticeSvc.do_update(kymNoticeVO);
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
	@RequestMapping(value = "notice/do_delete.do", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String do_delete(KYMNoticeVO kymNoticeVO) {
		LOG.debug("================");
		LOG.debug("kymNoticeVO"+kymNoticeVO);
		LOG.debug("================");
		
		int flag = this.kymNoticeSvc.do_delete(kymNoticeVO);
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
	@RequestMapping(value="notice/do_save.do",method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody	
	public String do_save(KYMNoticeVO kymNoticeVO, HttpSession session) {
		LOG.debug("============================");
		LOG.debug("=kymNoticeVO="+kymNoticeVO);
		LOG.debug("============================");
		
		if(null == kymNoticeVO.getTitle() || "".equals(kymNoticeVO.getTitle().trim())) {
			throw new IllegalArgumentException("제목을 입력 하세요.");
		}
		
		if(null == kymNoticeVO.getContents() || "".equals(kymNoticeVO.getContents().trim())) {
			throw new IllegalArgumentException("내용을 입력 하세요.");
		}
		
		int flag = this.kymNoticeSvc.do_save(kymNoticeVO);
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
	@RequestMapping(value = "notice/do_selectOne.do", method = RequestMethod.GET)
	public String get_selectOne(KYMNoticeVO kymNoticeVO, Model model) {
		LOG.debug("================");
		LOG.debug("kymNoticeVO"+kymNoticeVO);
		LOG.debug("================");
		
		if(null == kymNoticeVO.getNoticeId() || "".equals(kymNoticeVO.getNoticeId())) {
			throw new IllegalArgumentException("ID를 확인하세요");
			
		}
		
		KYMNoticeVO outVO = (KYMNoticeVO) this.kymNoticeSvc.do_selectOne(kymNoticeVO);
		model.addAttribute("vo", outVO);
		
		List<KYMBranchVO> branchlist = (List<KYMBranchVO>) this.kymNoticeSvc.do_retrieveFour(kymNoticeVO);
		model.addAttribute("Branchlist", branchlist);
		
		KYMNoticeVO vo = (KYMNoticeVO) this.kymNoticeSvc.do_selectOne(kymNoticeVO);
		String branchSNm = vo.getBranchSNm();
		model.addAttribute("branchSNm", branchSNm);
		
		return VIEW_MNG_NM;
		
	}
	
	/**목록조회 */
	@RequestMapping(value="notice/do_retrieve.do",method = RequestMethod.GET)
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
		code.setCodeTypeId("NOTICE_SEARCH");
		List<CodeVO> listnoticeSearch=(List<CodeVO>) this.kymNoticeSvc.do_retrieveTwo(code);		
		model.addAttribute("listNoticeSearch", listnoticeSearch);
		
		//목록조회
		List<KYMNoticeVO> list = (List<KYMNoticeVO>) this.kymNoticeSvc.do_retrieve(search);
		model.addAttribute("list", list);
		
		//총건수
		int totalCnt = 0;
		if(null != list && list.size()>0) {
			totalCnt = list.get(0).getTotalCnt();
			
		}
		
		model.addAttribute("totalCnt", totalCnt);
		
		return VIEW_LIST_NM;
		
	}
	
	@RequestMapping(value="notice/notice_mng.do", method = RequestMethod.GET)	
	public String noticeRegView(KYMNoticeVO kymNoticeVO, Model model) {

		List<KYMBranchVO> branchlist = (List<KYMBranchVO>) this.kymNoticeSvc.do_retrieveFour(kymNoticeVO);
		
		model.addAttribute("Branchlist", branchlist);
		
		return VIEW_MNG_NM;

	}
	
}
