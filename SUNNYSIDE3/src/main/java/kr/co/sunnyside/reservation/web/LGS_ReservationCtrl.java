package kr.co.sunnyside.reservation.web;

import java.util.List;

import org.apache.commons.math3.exception.NullArgumentException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;

import kr.co.sunnyside.cmn.Message;
import kr.co.sunnyside.cmn.SearchVO;
import kr.co.sunnyside.reservation.service.LGS_ReservationSvc;
import kr.co.sunnyside.reservation.service.LGS_TicketVO;

public class LGS_ReservationCtrl {
Logger LOG = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	LGS_ReservationSvc reservationSvc;
	
	//view
	private final String VIEW_ ="?";
	
	@ResponseBody
	@RequestMapping(value = "reservation/do_save.do", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
	public String do_save(LGS_TicketVO ticketVO) {
		LOG.debug("==================================");
		LOG.debug("Controller : do_save_reservation");
		LOG.debug("==================================");
		
		//파라미터검사
		if(ticketVO == null) throw new NullArgumentException(); //null		
		if(ticketVO.getBranchId() == "" || ticketVO.getBranchId() == null) throw new IllegalArgumentException();
		if(ticketVO.getRoomId() == "" || ticketVO.getRoomId() == null) throw new IllegalArgumentException();
		if(ticketVO.getScreenId() == "" || ticketVO.getScreenId() == null) throw new IllegalArgumentException();
		if(ticketVO.getUserId() == "" || ticketVO.getUserId() == null) throw new IllegalArgumentException();
		if(ticketVO.getMovieId() == "" || ticketVO.getMovieId() == null) throw new IllegalArgumentException();
		if(ticketVO.getSeatNum() == 0) throw new IllegalArgumentException();
		if(ticketVO.getAdultCnt() < 0 || ticketVO.getAdultCnt() > 1) throw new IllegalArgumentException();
		if(ticketVO.getPayState() < 0 || ticketVO.getPayState() > 1) throw new IllegalArgumentException();
		if(ticketVO.getCost() < 0 ) throw new IllegalArgumentException();
		if(ticketVO.getPayDt() == "" || ticketVO.getPayDt() == null) throw new IllegalArgumentException();
		if(ticketVO.getTicketDt() == "" || ticketVO.getTicketDt() == null) throw new IllegalArgumentException();
		
		
		int flag = reservationSvc.do_save(ticketVO);
		
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
	@RequestMapping(value = "reservation/do_update.do", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
	public String do_update(LGS_TicketVO ticketVO) {
		LOG.debug("==================================");
		LOG.debug("Controller : do_update_screenInfo");
		LOG.debug("==================================");
		
		//파라미터검사
		if(ticketVO.getBranchId() == "" || ticketVO.getBranchId() == null) throw new IllegalArgumentException();
		if(ticketVO.getRoomId() == "" || ticketVO.getRoomId() == null) throw new IllegalArgumentException();
		if(ticketVO.getScreenId() == "" || ticketVO.getScreenId() == null) throw new IllegalArgumentException();
		if(ticketVO.getUserId() == "" || ticketVO.getUserId() == null) throw new IllegalArgumentException();
		if(ticketVO.getMovieId() == "" || ticketVO.getMovieId() == null) throw new IllegalArgumentException();
		if(ticketVO.getSeatNum() == 0) throw new IllegalArgumentException();
		if(ticketVO.getAdultCnt() < 0 || ticketVO.getAdultCnt() > 1) throw new IllegalArgumentException();
		if(ticketVO.getPayState() < 0 || ticketVO.getPayState() > 1) throw new IllegalArgumentException();
		if(ticketVO.getCost() < 0 ) throw new IllegalArgumentException();
		if(ticketVO.getPayDt() == "" || ticketVO.getPayDt() == null) throw new IllegalArgumentException();
		if(ticketVO.getTicketDt() == "" || ticketVO.getTicketDt() == null) throw new IllegalArgumentException();
		
		int flag = reservationSvc.do_update(ticketVO);
		
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
	@RequestMapping(value = "reservation/do_delete.do", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
	public String do_delete(LGS_TicketVO ticketVO) {
		LOG.debug("==================================");
		LOG.debug("Controller : do_delete_screenInfo");
		LOG.debug("==================================");
		
		if(ticketVO == null) throw new NullArgumentException(); //null
		if(ticketVO.getTicketCode() == null || ticketVO.getTicketCode() == "") throw new IllegalArgumentException();

		int flag = reservationSvc.do_delete(ticketVO);
		
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
	
	@RequestMapping(value = "reservationSvc/get_selectOne.do", method = RequestMethod.POST)
	public String get_selectOne(LGS_TicketVO ticketVO, Model model) {
		LOG.debug("==================================");
		LOG.debug("Controller : get_selectOne_screenInfo");
		LOG.debug("==================================");
		
		if(ticketVO == null) throw new NullArgumentException(); //null
		if(ticketVO.getTicketCode() == null || ticketVO.getTicketCode() == "") throw new IllegalArgumentException();
		
		LGS_TicketVO outVO = (LGS_TicketVO) reservationSvc.get_selectOne(ticketVO);
		model.addAttribute("vo", outVO);
		
		LOG.debug("==================================");
		LOG.debug("outVO : " + outVO);
		LOG.debug("==================================");
		
		return VIEW_;
	}
	
	@RequestMapping(value = "reservationSvc/get_retrieve.do", method = RequestMethod.POST)
	public List<?> get_retrieve(SearchVO search, Model model) {
		LOG.debug("==================================");
		LOG.debug("Controller : get_retrieve_screenInfo");
		LOG.debug("==================================");
		
		if(search.getPageSize() == 0) search.setPageSize(10);
		if(search.getPageNum() == 0) search.setPageNum(1);
		
		List<LGS_TicketVO> list = (List<LGS_TicketVO>) reservationSvc.get_retrieve(search);
		model.addAttribute("list", list);
		
		LOG.debug("==================================");
		LOG.debug("list : " + list);
		LOG.debug("==================================");
		
		return list;
	}
}