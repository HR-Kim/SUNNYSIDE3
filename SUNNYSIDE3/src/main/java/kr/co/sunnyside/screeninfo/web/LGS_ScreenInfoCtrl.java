package kr.co.sunnyside.screeninfo.web;

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

import kr.co.sunnyside.cmn.Message;
import kr.co.sunnyside.cmn.SearchVO;
import kr.co.sunnyside.screeninfo.service.LGS_ScreenInfoSvc;
import kr.co.sunnyside.screeninfo.service.LGS_ScreenInfoVO;

@Controller
public class LGS_ScreenInfoCtrl {
	private final Logger LOG = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	LGS_ScreenInfoSvc screenInfoSvc;
	
	//view
	private final String VIEW_ = "?";
	
	@ResponseBody
	@RequestMapping(value = "screenInfo/do_save.do", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
	public String do_save(LGS_ScreenInfoVO screenInfo) {
		LOG.debug("==================================");
		LOG.debug("Controller : do_save_screenInfo");
		LOG.debug("==================================");
		
		//파라미터검사
		if(screenInfo == null) throw new NullArgumentException(); //null
		if(screenInfo.getRoomId() == null || screenInfo.getMovieId() == "") throw new IllegalArgumentException();
		if(screenInfo.getBranchId() == null || screenInfo.getBranchId() == "") throw new IllegalArgumentException();
		if(screenInfo.getMovieId() == null || screenInfo.getMovieId() == "") throw new IllegalArgumentException();
		if(screenInfo.getStartTime()== null || screenInfo.getStartTime() == "") throw new IllegalArgumentException();
		if(screenInfo.getScreenDt() == null || screenInfo.getScreenDt() == "") throw new IllegalArgumentException();
		if(screenInfo.getStudentCost() < 0) throw new IllegalArgumentException();
		if(screenInfo.getAdultCost() < 0) throw new IllegalArgumentException();
		if(screenInfo.getEpisode() < 0) throw new IllegalArgumentException();
		
		int flag = screenInfoSvc.do_save(screenInfo);
		
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
	@RequestMapping(value = "screenInfo/do_update.do", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
	public String do_update(LGS_ScreenInfoVO screenInfo) {
		LOG.debug("==================================");
		LOG.debug("Controller : do_update_screenInfo");
		LOG.debug("==================================");
		
		//파라미터검사
		if(screenInfo == null) throw new NullArgumentException(); //null
		if(screenInfo.getScreenId() == null || screenInfo.getScreenId() == "") throw new IllegalArgumentException();
		if(screenInfo.getRoomId() == null || screenInfo.getMovieId() == "") throw new IllegalArgumentException();
		if(screenInfo.getBranchId() == null || screenInfo.getBranchId() == "") throw new IllegalArgumentException();
		if(screenInfo.getMovieId() == null || screenInfo.getMovieId() == "") throw new IllegalArgumentException();
		if(screenInfo.getStartTime()== null || screenInfo.getStartTime() == "") throw new IllegalArgumentException();
		if(screenInfo.getScreenDt() == null || screenInfo.getScreenDt() == "") throw new IllegalArgumentException();
		if(screenInfo.getStudentCost() < 0) throw new IllegalArgumentException();
		if(screenInfo.getAdultCost() < 0) throw new IllegalArgumentException();
		if(screenInfo.getEpisode() < 0) throw new IllegalArgumentException();
		
		int flag = screenInfoSvc.do_update(screenInfo);
		
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
	@RequestMapping(value = "screenInfo/do_delete.do", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
	public String do_delete(LGS_ScreenInfoVO screenInfo) {
		LOG.debug("==================================");
		LOG.debug("Controller : do_delete_screenInfo");
		LOG.debug("==================================");
		
		if(screenInfo == null) throw new NullArgumentException(); //null
		if(screenInfo.getScreenId() == null || screenInfo.getScreenId() == "") throw new IllegalArgumentException();

		int flag = screenInfoSvc.do_delete(screenInfo);
		
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
	
	@RequestMapping(value = "screenInfo/do_selectOne.do", method = RequestMethod.POST)
	public String do_selectOne(LGS_ScreenInfoVO screenInfo, Model model) {
		LOG.debug("==================================");
		LOG.debug("Controller : do_selectOne_screenInfo");
		LOG.debug("==================================");
		
		if(screenInfo == null) throw new NullArgumentException(); //null
		if(screenInfo.getScreenId() == null || screenInfo.getScreenId() == "") throw new IllegalArgumentException();
		
		LGS_ScreenInfoVO outVO = (LGS_ScreenInfoVO) screenInfoSvc.do_selectOne(screenInfo);
		model.addAttribute("vo", outVO);
		
		LOG.debug("==================================");
		LOG.debug("outVO : " + outVO);
		LOG.debug("==================================");
		
		return VIEW_;
	}
	
	@RequestMapping(value = "screenInfo/do_retrieve.do", method = RequestMethod.POST)
	public List<?> do_retrieve(SearchVO search, Model model) {
		LOG.debug("==================================");
		LOG.debug("Controller : do_retrieve_screenInfo");
		LOG.debug("==================================");
		
		if(search.getPageSize() == 0) search.setPageSize(10);
		if(search.getPageNum() == 0) search.setPageNum(1);
		
		List<LGS_ScreenInfoVO> list = (List<LGS_ScreenInfoVO>) screenInfoSvc.do_retrieve(search);
		model.addAttribute("list", list);
		
		LOG.debug("==================================");
		LOG.debug("list : " + list);
		LOG.debug("==================================");
		
		return list;
	}
}