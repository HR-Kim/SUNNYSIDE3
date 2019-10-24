package kr.co.sunnyside.faq.web;

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
import kr.co.sunnyside.faq.service.LHJ_FaqVO;
import kr.co.sunnyside.faq.service.impl.LHJ_FaqSvcImpl;

@Controller
public class LHJ_FaqCtrl {
	private final Logger LOG = LoggerFactory.getLogger(this.getClass());
	  
	@Autowired
	LHJ_FaqSvcImpl service;	
	 
	//view
	private final String VIEW_LIST_NM = "faq/faq_list";
	
	/** 리뷰 수정 */
	@RequestMapping(value = "faq/do_update.do", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String do_update(LHJ_FaqVO inVO) {
		String gsonStr = "";
		LOG.debug("============================");
 		LOG.debug("=inVO=" + inVO);
		LOG.debug("============================");

		if (null == inVO.getQuestionId() || "".equals(inVO.getQuestionId().trim())) {
			throw new IllegalArgumentException("질문ID를 선택하세요.");
		}
		
		if (null == inVO.getTitle() || "".equals(inVO.getTitle().trim())) {
			throw new IllegalArgumentException("질문을 작성하세요.");
		}
		
		if (null == inVO.getContents() || "".equals(inVO.getContents().trim())) {
			throw new IllegalArgumentException("답변을 작성하세요.");
		}

		int flag = this.service.do_update(inVO);
		Message message = new Message();


		if (flag > 0) {
			message.setMsgId(String.valueOf(flag));
			message.setMsgMsg("수정 되었습니다.");
		} else {
			message.setMsgId(String.valueOf(flag));
			message.setMsgMsg("수정 실패.");
		}

		Gson gson = new Gson();
		gsonStr = gson.toJson(message);

		return gsonStr;
	}
	
	/** 저장 */
	@RequestMapping(value = "faq/do_save.do", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String do_save(LHJ_FaqVO inVO) {
		LOG.debug("============================");
		LOG.debug("=inVO=" + inVO);
		LOG.debug("============================");

		if (null == inVO.getTitle() || "".equals(inVO.getTitle().trim())) {
			throw new IllegalArgumentException("질문을 작성하세요.");
		}
		
		if (null == inVO.getContents() || "".equals(inVO.getContents().trim())) {
			throw new IllegalArgumentException("답변을 작성하세요.");
		}
		
		int flag = this.service.do_save(inVO);

		Message message = new Message();

		if (flag > 0) {
			message.setMsgId(String.valueOf(flag));
			message.setMsgMsg("등록 되었습니다.");
		} else {
			message.setMsgId(String.valueOf(flag));
			message.setMsgMsg("등록 실패.");
		}

		Gson gson = new Gson();
		String gsonStr = gson.toJson(message);

		LOG.debug("============================");
		LOG.debug("=gsonStr=" + gsonStr);
		LOG.debug("============================");

		return gsonStr;
	}
	
	/** 삭제 */
	@RequestMapping(value = "faq/do_delete.do", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String do_delete(LHJ_FaqVO inVO) {
		LOG.debug("============================");
		LOG.debug("=inVO=" + inVO);
		LOG.debug("============================");

		if (null == inVO.getQuestionId() || "".equals(inVO.getQuestionId().trim())) {
			throw new IllegalArgumentException("질문ID를 선택하세요.");
		}
		
		int flag = this.service.do_delete(inVO);
		Message message = new Message();

		if (flag > 0) {
			message.setMsgId(String.valueOf(flag));
			message.setMsgMsg("삭제 되었습니다.");
		} else {
			message.setMsgId(String.valueOf(flag));
			message.setMsgMsg("삭제 실패.");
		}

		Gson gson = new Gson();
		String gsonStr = gson.toJson(message);

		LOG.debug("============================");
		LOG.debug("=gsonStr=" + gsonStr);
		LOG.debug("============================");

		return gsonStr;
	}
	
	/**목록조회 */
	@RequestMapping(value="faq/do_retrieve.do",method = RequestMethod.GET)
	public String do_retrieve(HttpServletRequest req, Model model) {
		
		//목록조회
		List<LHJ_FaqVO> list = (List<LHJ_FaqVO>) this.service.do_retrieve();
		model.addAttribute("list", list);

		return VIEW_LIST_NM;
	}
	 
	
}
