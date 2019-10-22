package kr.co.sunnyside.coupon.web;

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
import kr.co.sunnyside.coupon.service.SJH_CouponSvc;
import kr.co.sunnyside.coupon.service.SJH_CouponVO;
import kr.co.sunnyside.usermypage.service.SJH_MypageVO;

@Controller
public class SJH_CouponCtrl {

	Logger LOG = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	SJH_CouponSvc couponSvc;
	
	
	
	
	
	//단건조회
	@RequestMapping(value="coupon/do_selectOne.do",method = RequestMethod.POST,produces = "application/json; charset=UTF-8")
	@ResponseBody
	public String do_selectOne(SJH_CouponVO inVO,Model model) {
		LOG.debug("=========================");
		LOG.debug("=@Controller=inVO=="+inVO);
		LOG.debug("=========================");
		
		if(inVO.getUserId() == null || "".equals(inVO.getUserId())) {
			throw new IllegalArgumentException("아이디를 입력하세요.");
		}
		
		SJH_CouponVO outVO = (SJH_CouponVO) couponSvc.do_selectOne(inVO);

		Gson gson=new Gson();
		String json = gson.toJson(outVO);
		LOG.debug("=========================");
		LOG.debug("=@Controller gson=user=="+json);
		LOG.debug("=========================");		
		
		return json;
	}
	
	
	
	//쿠폰발급
	@RequestMapping(value="coupon/do_save.do",method = RequestMethod.POST,produces = "application/json; charset=UTF-8")
	@ResponseBody
	public String do_save(SJH_CouponVO inVO) {
		LOG.debug("========================");
		LOG.debug("=@Controller=inVO="+inVO);
		LOG.debug("========================");
		
		//flag>0 성공, 실패
		int flag = couponSvc.do_save(inVO);
		Message message = new Message();
		if(flag>0) {
			message.setMsgId(flag+"");
			message.setMsgMsg("쿠폰이 등록되었습니다.");
		}else {
			message.setMsgId(flag+"");
			message.setMsgMsg("쿠폰등록실패.");
		}
		
		//json으로 변환
		Gson gson = new Gson();
		String json = gson.toJson(message);
		LOG.debug("=========================");
		LOG.debug("=@Controller 쿠폰등록gson=user="+json);
		LOG.debug("=========================");		
		
		return json;
	}
	
	
	//쿠폰삭제
	@RequestMapping(value="coupon/do_delete.do",method = RequestMethod.POST,produces = "application/json; charset=UTF-8")
	@ResponseBody
	public String do_delete(SJH_CouponVO inVO) {
		LOG.debug("========================");
		LOG.debug("=@Controller=inVO="+inVO);
		LOG.debug("========================");
		
		//flag>0 성공, 실패
		int flag = couponSvc.do_delete(inVO);
		Message message = new Message();
		if(flag>0) {
			message.setMsgId(flag+"");
			message.setMsgMsg("쿠폰이 삭제되었습니다.");
		}else {
			message.setMsgId(flag+"");
			message.setMsgMsg("쿠폰삭제실패.");
		}
		
		//json으로 변환
		Gson gson = new Gson();
		String json = gson.toJson(message);
		LOG.debug("=========================");
		LOG.debug("=@Controller 쿠폰삭제gson=user="+json);
		LOG.debug("=========================");		
		
		return json;
	}
	
	
}
