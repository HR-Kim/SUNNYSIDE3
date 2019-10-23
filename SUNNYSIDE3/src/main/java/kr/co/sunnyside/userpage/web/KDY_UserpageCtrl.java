package kr.co.sunnyside.userpage.web;

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
import kr.co.sunnyside.phototicket.service.SEJ_PhotoTicketSvc;
import kr.co.sunnyside.phototicket.service.SEJ_PhotoTicketVO;
import kr.co.sunnyside.reservation.service.LGS_TicketVO;
import kr.co.sunnyside.userpage.service.KDY_CouponVO;
import kr.co.sunnyside.userpage.service.KDY_MoviehistoryVO;
import kr.co.sunnyside.userpage.service.KDY_QnalistVO;
import kr.co.sunnyside.userpage.service.KDY_UserinfoVO;
import kr.co.sunnyside.userpage.service.KDY_UserpageSvc;
import kr.co.sunnyside.userpage.service.listSearchVO;

@Controller
public class KDY_UserpageCtrl {
	Logger LOG = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	KDY_UserpageSvc userpageSvc;
	
	@Autowired
	SEJ_PhotoTicketSvc SPT;
	
	//view
	private static final String VIEW_NAME   ="userpage/userpage";
	
	
	
	@RequestMapping(value = "userpage/do_tiketHistory.do", method = RequestMethod.POST ,produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String do_tiketHistory(listSearchVO search) {
		LOG.debug("==================================");
		LOG.debug("Controller : tiketHistory");
		LOG.debug("==================================");
		search.setUser_id("u5");
		if(search.getPageSize() == 0) search.setPageSize(10);
		if(search.getPageNum() == 0) search.setPageNum(1);
		
		List<KDY_MoviehistoryVO> list = (List<KDY_MoviehistoryVO>) userpageSvc.do_retrieve(search);
		
		
		LOG.debug("==================================");
		LOG.debug("list : " + list);
		LOG.debug("==================================");
		
		Gson gson=new Gson();
		String gsonStr = gson.toJson(list);
		return gsonStr;
		
	}
	@RequestMapping(value = "userpage/do_delete.do", method = RequestMethod.POST)
	public String do_delete(KDY_MoviehistoryVO moviehistoryVO) {
		LOG.debug("==================================");
		LOG.debug("Controller : do_delete_screenInfo");
		LOG.debug("==================================");
		
		if(moviehistoryVO == null) throw new NullArgumentException(); //null
		
		int flag = userpageSvc.do_delete(moviehistoryVO);
		
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
	@RequestMapping(value = "userpage/do_movieHistory.do", method = RequestMethod.POST ,produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String do_movieHistory(SEJ_PhotoTicketVO inVO) {
		LOG.debug("==================================");
		LOG.debug("Controller : do_movieHistory");
		LOG.debug("==================================");
		inVO.setUser_id("u5");
		List<SEJ_PhotoTicketVO> couponList = (List<SEJ_PhotoTicketVO>) SPT.do_retrieve_user(inVO);
		
		
		LOG.debug("==================================");
		LOG.debug("couponList" + couponList);
		LOG.debug("==================================");
		
		Gson gson=new Gson();
		String gsonStr = gson.toJson(couponList);
		return gsonStr;
		
	}
	
	@RequestMapping(value = "userpage/do_coupon_retrieve.do", method = RequestMethod.POST ,produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String do_coupon_retrieve(listSearchVO search) {
		LOG.debug("==================================");
		LOG.debug("Controller : do_coupon_retrieve");
		LOG.debug("==================================");
		search.setUser_id("j05_126");
		if(search.getPageSize() == 0) search.setPageSize(10);
		if(search.getPageNum() == 0) search.setPageNum(1);
		
		List<KDY_CouponVO> list = (List<KDY_CouponVO>) userpageSvc.do_coupon_retrieve(search);
		
		
		LOG.debug("==================================");
		LOG.debug("list : " + list);
		LOG.debug("==================================");
		
		Gson gson=new Gson();
		String gsonStr = gson.toJson(list);
		return gsonStr;
		
	}
	@RequestMapping(value = "userpage/do_membership.do", method = RequestMethod.POST ,produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String do_membership(listSearchVO search) {
		LOG.debug("==================================");
		LOG.debug("Controller : do_coupon_retrieve");
		LOG.debug("==================================");
		search.setUser_id("j02_126");
		
		List<KDY_UserinfoVO> list = (List<KDY_UserinfoVO>) userpageSvc.do_membership(search);
		
		
		LOG.debug("==================================");
		LOG.debug("list : " + list);
		LOG.debug("==================================");
		
		Gson gson=new Gson();
		String gsonStr = gson.toJson(list);
		return gsonStr;
		
	}
	
	@RequestMapping(value = "userpage/do_qnaList.do", method = RequestMethod.POST ,produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String do_qnaList(listSearchVO search) {
		LOG.debug("==================================");
		LOG.debug("Controller : do_coupon_retrieve");
		LOG.debug("==================================");
		search.setUser_id("370");
		if(search.getPageSize() == 0) search.setPageSize(10);
		if(search.getPageNum() == 0) search.setPageNum(1);
		List<KDY_QnalistVO> list = (List<KDY_QnalistVO>) userpageSvc.do_qnaList(search);
	
		
		LOG.debug("==================================");
		LOG.debug("list : " + list);
		LOG.debug("==================================");
		
		Gson gson=new Gson();
		String gsonStr = gson.toJson(list);
		return gsonStr;
		
	}
	
	
}