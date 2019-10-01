package kr.co.sunnyside.branchinfo.web;

import java.util.List;

import org.apache.commons.math3.exception.NullArgumentException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;

import kr.co.sunnyside.branchinfo.service.LGS_BranchInfoSvc;
import kr.co.sunnyside.branchinfo.service.LGS_BranchInfoVO;
import kr.co.sunnyside.cmn.Message;
import kr.co.sunnyside.cmn.SearchVO;

@Controller
public class LGS_BranchInfoCtrl {
	Logger LOG = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	LGS_BranchInfoSvc branchInfoSvc;	//지점정보 지점id, 지점명
	
	//view
	private final String VIEW_ ="?";
	
	@ResponseBody
	@RequestMapping(value = "brachInfo/do_save.do", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
	public String do_save(LGS_BranchInfoVO branchInfo) {
		LOG.debug("==================================");
		LOG.debug("Controller : do_save_branchInfo");
		LOG.debug("==================================");
		
		if(branchInfo == null) throw new NullArgumentException(); //null
		if(branchInfo.getBranchNm() == null || branchInfo.getBranchNm() == "") throw new IllegalArgumentException();	//지점이름
		
		
		//지점이름만 주면 id는 랜덤키로 자동생성되어 저장
		int flag = branchInfoSvc.do_save(branchInfo);
		
		LOG.debug("==================================");
		LOG.debug("flag : " + flag);
		LOG.debug("==================================");
		
		Message message = new Message();
		if(flag > 0) {
			message.setMsgId("1");
			message.setMsgMsg("성공");
		}else {
			message.setMsgId("0");
			message.setMsgMsg("실패");
		}
		
		Gson gson = new Gson();
		String jsonString = gson.toJson(message);
		
		LOG.debug("==================================");
		LOG.debug("jsonString : " + jsonString);
		LOG.debug("==================================");
		
		return jsonString;
	}
	
	@ResponseBody
	@RequestMapping(value = "brachInfo/do_update.do", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
	public String do_update(LGS_BranchInfoVO branchInfo) {
		LOG.debug("==================================");
		LOG.debug("Controller : do_update_branchInfo");
		LOG.debug("==================================");
		
		if(branchInfo == null) throw new NullArgumentException(); //null
		if(branchInfo.getBranchNm() == null || branchInfo.getBranchNm() == "") throw new IllegalArgumentException();	//지점이름
		if(branchInfo.getBranchId() == null || branchInfo.getBranchId() == "") throw new IllegalArgumentException();	//지점id
		
		int flag = branchInfoSvc.do_update(branchInfo);
		
		LOG.debug("==================================");
		LOG.debug("flag : " + flag);
		LOG.debug("==================================");
		
		Message message = new Message();
		if(flag > 0) {
			message.setMsgId("1");
			message.setMsgMsg("성공");
		}else {
			message.setMsgId("0");
			message.setMsgMsg("실패");
		}
		
		Gson gson = new Gson();
		String jsonString = gson.toJson(message);
		
		LOG.debug("==================================");
		LOG.debug("jsonString : " + jsonString);
		LOG.debug("==================================");
		
		return jsonString;
	}
	
	@ResponseBody
	@RequestMapping(value = "brachInfo/do_delete.do", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
	public String do_delete(LGS_BranchInfoVO branchInfo) {
		LOG.debug("==================================");
		LOG.debug("Controller : do_delete_branchInfo");
		LOG.debug("==================================");
		
		if(branchInfo == null) throw new NullArgumentException(); //null
		if(branchInfo.getBranchId() == null || branchInfo.getBranchId() == "") throw new IllegalArgumentException();	//지점id
		
		int flag = branchInfoSvc.do_delete(branchInfo);
		
		LOG.debug("==================================");
		LOG.debug("flag : " + flag);
		LOG.debug("==================================");
		
		Message message = new Message();
		if(flag > 0) {
			message.setMsgId("1");
			message.setMsgMsg("성공");
		}else {
			message.setMsgId("0");
			message.setMsgMsg("실패");
		}
		
		Gson gson = new Gson();
		String jsonString = gson.toJson(message);
		
		LOG.debug("==================================");
		LOG.debug("jsonString : " + jsonString);
		LOG.debug("==================================");
		
		return jsonString;
	}
	
	@RequestMapping(value = "brachInfo/get_selectOne.do", method = RequestMethod.POST)
	public String get_selectOne(LGS_BranchInfoVO branchInfo, Model model) {
		LOG.debug("==================================");
		LOG.debug("Controller : get_selectOne_branchInfo");
		LOG.debug("==================================");
		
		if(branchInfo == null) throw new NullArgumentException(); //null
		if(branchInfo.getBranchId() == null || branchInfo.getBranchId() == "") throw new IllegalArgumentException();	//지점id
		
		LGS_BranchInfoVO outVO = (LGS_BranchInfoVO) branchInfoSvc.get_selectOne(branchInfo);
		model.addAttribute("vo", outVO);
		
		LOG.debug("==================================");
		LOG.debug("outVO : " + outVO);
		LOG.debug("==================================");
		
		return VIEW_;
	}
	
	@RequestMapping(value = "brachInfo/get_retrieve.do", method = RequestMethod.POST)
	public List<?> get_retrieve(SearchVO search, Model model) {
		LOG.debug("==================================");
		LOG.debug("Controller : get_retrieve_branchInfo");
		LOG.debug("==================================");
		
		if(search.getPageSize() == 0) search.setPageSize(10);
		if(search.getPageNum() == 0) search.setPageNum(1);
				
		List<LGS_BranchInfoVO> list = (List<LGS_BranchInfoVO>) branchInfoSvc.get_retrieve(search);
		model.addAttribute("list", list);
		
		LOG.debug("==================================");
		LOG.debug("list : " + list);
		LOG.debug("==================================");
		
		return list;
	}
}
