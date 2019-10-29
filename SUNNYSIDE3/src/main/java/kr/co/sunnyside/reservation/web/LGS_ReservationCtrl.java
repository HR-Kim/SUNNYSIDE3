package kr.co.sunnyside.reservation.web;

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
import kr.co.sunnyside.reservation.service.LGS_ReservationSvc;
import kr.co.sunnyside.reservation.service.LGS_TicketVO;
import kr.co.sunnyside.screeninfo.service.LGS_ScreenInfoVO;
import kr.co.sunnyside.seat.service.LGS_SeatVO;

@Controller
public class LGS_ReservationCtrl {
	Logger LOG = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	LGS_ReservationSvc reservationSvc;
	
	//view
	private final String VIEW_PAY ="reservation/reservation_pay";
	private final String VIEW_RESULT ="reservation/reservation_result";
	
	@ResponseBody
	@RequestMapping(value = "reservation/do_save.do", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
	public String do_save(LGS_TicketVO ticketVO, String seatNmArr) {
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
		if(ticketVO.getAdultCnt() < 0 || ticketVO.getAdultCnt() > 1) throw new IllegalArgumentException();
		if(ticketVO.getPayState() < 0 || ticketVO.getPayState() > 1) throw new IllegalArgumentException();
		if(ticketVO.getCost() < 0 ) throw new IllegalArgumentException();
		
		String[] arr = seatNmArr.split("%");
		int flag = 0;
		for(int i=1 ; i<arr.length ; i++) {
			LGS_TicketVO vo = ticketVO;
			vo.setSeatNm(arr[i]);
			flag += reservationSvc.do_save(vo);
		}
		
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
		String jsonStr = gson.toJson(message);
		
		LOG.debug("==================================");
		LOG.debug("jsonStr : " + jsonStr);
		LOG.debug("==================================");
		
		return jsonStr;
	}
	
	@ResponseBody
	@RequestMapping(value = "reservation/do_update.do", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
	public String do_update(LGS_TicketVO ticketVO) {
		LOG.debug("==================================");
		LOG.debug("Controller : do_update_screenInfo");
		LOG.debug("==================================");
		
		//파라미터검사
		if(ticketVO.getTicketCode() == "" || ticketVO.getTicketCode() == null) throw new IllegalArgumentException();
		if(ticketVO.getBranchId() == "" || ticketVO.getBranchId() == null) throw new IllegalArgumentException();
		if(ticketVO.getRoomId() == "" || ticketVO.getRoomId() == null) throw new IllegalArgumentException();
		if(ticketVO.getScreenId() == "" || ticketVO.getScreenId() == null) throw new IllegalArgumentException();
		if(ticketVO.getUserId() == "" || ticketVO.getUserId() == null) throw new IllegalArgumentException();
		if(ticketVO.getMovieId() == "" || ticketVO.getMovieId() == null) throw new IllegalArgumentException();
		if(ticketVO.getSeatNm() == "" || ticketVO.getSeatNm() == null) throw new IllegalArgumentException();
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
		String jsonStr = gson.toJson(message);
		
		LOG.debug("==================================");
		LOG.debug("jsonStr : " + jsonStr);
		LOG.debug("==================================");
		
		return jsonStr;
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
		String jsonStr = gson.toJson(message);
		
		LOG.debug("==================================");
		LOG.debug("jsonStr : " + jsonStr);
		LOG.debug("==================================");
		
		return jsonStr;
	}
	
	@ResponseBody
	@RequestMapping(value = "reservation/do_selectOne.do", method = RequestMethod.POST)
	public String do_selectOne(LGS_TicketVO ticketVO, Model model) {
		LOG.debug("==================================");
		LOG.debug("Controller : do_selectOne_screenInfo");
		LOG.debug("==================================");
		
		if(ticketVO == null) throw new NullArgumentException(); //null
		if(ticketVO.getSeatNm() == null || ticketVO.getSeatNm() == "") throw new IllegalArgumentException();
		if(ticketVO.getScreenId() == null || ticketVO.getScreenId() == "") throw new IllegalArgumentException();
		
		LGS_TicketVO outVO = (LGS_TicketVO) reservationSvc.do_selectOne(ticketVO);

		LOG.debug("==================================");
		LOG.debug("outVO : " + outVO);
		LOG.debug("==================================");
		
		Gson gson = new Gson();
		String jsonStr = gson.toJson(outVO);
		
		LOG.debug("==================================");
		LOG.debug("jsonStr : " + jsonStr);
		LOG.debug("==================================");
		
		return jsonStr;
	}
	
	@RequestMapping(value = "reservation/do_retrieve.do", method = RequestMethod.POST)
	public String do_retrieve(SearchVO search, Model model) {
		LOG.debug("==================================");
		LOG.debug("Controller : do_retrieve_screenInfo");
		LOG.debug("==================================");
		
		if(search.getPageSize() == 0) search.setPageSize(10);
		if(search.getPageNum() == 0) search.setPageNum(1);
		
		List<LGS_TicketVO> list = (List<LGS_TicketVO>) reservationSvc.do_retrieve(search);
		model.addAttribute("ticketList", list);
		
		LOG.debug("==================================");
		LOG.debug("list : " + list);
		LOG.debug("==================================");
		
		return "";
	}
	
	@RequestMapping(value = "reservation/do_pay.do", method = RequestMethod.POST)
	public String do_payPage(LGS_TicketVO vo, String seatInfo, Model model) {
		LOG.debug("==================================");
		LOG.debug("Controller : do_payPage");
		LOG.debug("==================================");

		model.addAttribute("vo", vo);
		model.addAttribute("seatInfo", seatInfo);
		
		LOG.debug("==================================");
		LOG.debug("vo : " + vo);
		LOG.debug("seatInfo : " + seatInfo);
		LOG.debug("==================================");
		
		return VIEW_PAY;
	}
	
	@RequestMapping(value = "reservation/reservation_result.do", method = RequestMethod.POST)
	public String do_payResultPage(LGS_ScreenInfoVO vo, String cost, String seatData, Model model) {
		LOG.debug("==================================");
		LOG.debug("Controller : reservation_result.do");
		LOG.debug("==================================");
		
		model.addAttribute("cost", cost);
		model.addAttribute("vo", vo);
		model.addAttribute("seatData", seatData);
		
		LOG.debug("==================================");
		LOG.debug("vo : " + vo);
		LOG.debug("cost : " + cost);
		LOG.debug("seatData : " + seatData);
		LOG.debug("==================================");
		
		return VIEW_RESULT;
	}

	@ResponseBody
	@RequestMapping(value = "reservation/do_selectOne_result.do", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
	public String do_selectOne_result(LGS_TicketVO ticketVO) {
		LOG.debug("==================================");
		LOG.debug("Controller : do_selectOne_result");
		LOG.debug("==================================");

		LGS_TicketVO outVO = (LGS_TicketVO) reservationSvc.do_selectOne_result(ticketVO);
		
		LOG.debug("==================================");
		LOG.debug("outVO" + outVO);
		LOG.debug("==================================");
		
		Gson gson = new Gson();
		String jsonStr = gson.toJson(outVO);
		
		LOG.debug("==================================");
		LOG.debug("jsonStr : " + jsonStr);
		LOG.debug("==================================");
		
		return jsonStr;
	}
	
	@ResponseBody
	@RequestMapping(value = "reservation/do_retrieve_seatRealTime.do", method = RequestMethod.POST)
	public String do_retrieve_seatRealTime(SearchVO search, Model model) {
		LOG.debug("==================================");
		LOG.debug("Controller : do_retrieve_screenInfo");
		LOG.debug("==================================");
		
		if(search.getPageSize() == 0) search.setPageSize(10);
		if(search.getPageNum() == 0) search.setPageNum(1);
		
		List<LGS_SeatVO> list = (List<LGS_SeatVO>) reservationSvc.do_retrieve_seatRealTime(search);
		
		Gson gson = new Gson();
		String jsonStr = gson.toJson(list);
		
		LOG.debug("==================================");
		LOG.debug("list : " + list);
		LOG.debug("==================================");
		
		return jsonStr;
	}
}