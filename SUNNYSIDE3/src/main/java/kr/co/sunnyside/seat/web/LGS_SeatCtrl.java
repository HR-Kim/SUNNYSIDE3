package kr.co.sunnyside.seat.web;

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
import kr.co.sunnyside.seat.service.LGS_SeatSvc;
import kr.co.sunnyside.seat.service.LGS_SeatVO;

@Controller
public class LGS_SeatCtrl {
	Logger LOG = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	LGS_SeatSvc seatSvc;				//좌석정보 지점,상영관id, 좌석번호
	
	//view
	private final String VIEW_ ="?";
	
	@ResponseBody
	@RequestMapping(value = "seat/do_update.do", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
	public String do_update(LGS_SeatVO seat) {
		LOG.debug("==================================");
		LOG.debug("Controller : do_updatee_seat");
		LOG.debug("==================================");

		if(seat == null) throw new NullArgumentException(); //null
		if(seat.getSeatNm() == null || seat.getSeatNm() == "") throw new IllegalArgumentException(); //좌석명
		if(seat.getRoomId() == null || seat.getRoomId() == "") throw new IllegalArgumentException(); //상영관ID
		
		int flag = seatSvc.do_update(seat);
		
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
	@RequestMapping(value = "seat/do_save.do", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
	public String do_save_seat(LGS_SeatVO seat) {
		LOG.debug("==================================");
		LOG.debug("Controller : do_save_seat");
		LOG.debug("==================================");
		
		if(seat == null) throw new NullArgumentException();	//null
		if(seat.getBranchId() == null || seat.getBranchId() == "") throw new IllegalArgumentException(); //지점id
		if(seat.getRoomId() == null || seat.getRoomId() == "") throw new IllegalArgumentException(); //상영관id
		if(seat.getSeatY() == null || seat.getSeatY() == "") throw new IllegalArgumentException(); //좌석Y축
		if(seat.getSeatX() == 0) throw new IllegalArgumentException(); //좌석X축
		if(seat.getUseYN() == null || seat.getUseYN() == "") throw new IllegalArgumentException(); //사용유무
		
		
		int flag = seatSvc.do_save(seat);
		
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
	@RequestMapping(value = "seat/do_delete.do", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
	public String do_delete(LGS_SeatVO seat) {
		LOG.debug("==================================");
		LOG.debug("Controller : do_delete_seat");
		LOG.debug("==================================");
		
		if(seat == null) throw new NullArgumentException(); //null
		if(seat.getRoomId() == null || seat.getRoomId() == "") throw new IllegalArgumentException(); //상영관ID
		
		int flag = seatSvc.do_delete(seat);
		
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
	
	@RequestMapping(value = "seat/do_selectOne.do", method = RequestMethod.POST)
	public String do_selectOne(LGS_SeatVO seat, Model model) {
		LOG.debug("==================================");
		LOG.debug("Controller : do_selectOne_seat");
		LOG.debug("==================================");
		
		if(seat == null) throw new NullArgumentException(); //null
		if(seat.getSeatNm() == null || seat.getSeatNm() == "") throw new IllegalArgumentException(); //좌석명
		
		LGS_SeatVO outVO = (LGS_SeatVO) seatSvc.do_selectOne(seat);
		model.addAttribute("seatVO", outVO);
		
		LOG.debug("==================================");
		LOG.debug("outVO : " + outVO);
		LOG.debug("==================================");
		
		return VIEW_;
	}
	
	@ResponseBody
	@RequestMapping(value = "seat/do_retrieve.do", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
	public String do_retrieve(SearchVO search, Model model) {
		LOG.debug("==================================");
		LOG.debug("Controller : do_retrieve_seat");
		LOG.debug("==================================");
		
		LOG.debug("==================================");
		LOG.debug("param : " + search.toString());
		LOG.debug("==================================");
		
		if(search.getPageSize() == 0) search.setPageSize(1000);
		if(search.getPageNum() == 0) search.setPageNum(1);
				
		List<LGS_SeatVO> list = (List<LGS_SeatVO>) seatSvc.do_retrieve(search);
				
		//model.addAttribute("list", list);
		
		LOG.debug("==================================");
		LOG.debug("list : " + list);
		LOG.debug("==================================");
		
		Gson gson=new Gson();
		String gsonStr = gson.toJson(list);		
		
		return gsonStr;
	}

	@ResponseBody
	@RequestMapping(value = "seat/do_update_reservation.do", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
	public String do_update_reservation(String seatArr, String roomId, String useYN) {
		LOG.debug("==================================");
		LOG.debug("Controller : do_updatee_seat_reservation");
		LOG.debug("==================================");

		LOG.debug("==================================");
		LOG.debug("seatArr : " + seatArr);
		LOG.debug("roomId : " + roomId);
		LOG.debug("useYN : " + useYN);
		LOG.debug("==================================");
		
		if(seatArr == null) throw new IllegalArgumentException();
		if(roomId == null || roomId == "") throw new IllegalArgumentException();
		if(useYN == null || useYN == "") throw new IllegalArgumentException();
		
		String[] arr = seatArr.split("%");
		int flag = 0;
		for(int i=1 ; i<arr.length ; i++) {
			LGS_SeatVO vo = new LGS_SeatVO();
			vo.setSeatNm(arr[i]);
			vo.setRoomId(roomId);
			vo.setUseYN(useYN);
		flag += seatSvc.do_update_reservation(vo);
		}
		
		LOG.debug("==================================");
		LOG.debug("length : " + arr.length + "(-1)");
		LOG.debug("flag : " + flag);
		LOG.debug("==================================");
		
		Message message = new Message();
		if(flag >= arr.length-1) {
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
	@RequestMapping(value = "seat/do_save_reservation.do", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
	public String do_save_seat_reservation(LGS_SeatVO seat) {
		LOG.debug("==================================");
		LOG.debug("Controller : do_save_seat_reservation");
		LOG.debug("==================================");
		
		if(seat == null) throw new NullArgumentException();	//null
		if(seat.getRoomId() == null || seat.getRoomId() == "") throw new IllegalArgumentException(); //상영관id
		if(seat.getScreenId() == null || seat.getScreenId() == "") throw new IllegalArgumentException();
		
		int flag = seatSvc.do_save_reservation(seat);
		
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
	@RequestMapping(value = "seat/do_delete_reservation.do", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
	public String do_delete_reservation(LGS_SeatVO seat) {
		LOG.debug("==================================");
		LOG.debug("Controller : do_delete_seat");
		LOG.debug("==================================");
		
		if(seat == null) throw new NullArgumentException(); //null
		if(seat.getScreenId() == null || seat.getScreenId() == "") throw new IllegalArgumentException();
		
		int flag = seatSvc.do_delete_reservation(seat);
		
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
	
	@RequestMapping(value = "seat/do_selectOne_reservation.do", method = RequestMethod.POST)
	public String do_selectOne_reservation(LGS_SeatVO seat, Model model) {
		LOG.debug("==================================");
		LOG.debug("Controller : do_selectOne_seat_reservation");
		LOG.debug("==================================");
		
		if(seat == null) throw new NullArgumentException(); //null
		if(seat.getSeatNm() == null || seat.getSeatNm() == "") throw new IllegalArgumentException(); //좌석명
		if(seat.getScreenId() == null || seat.getScreenId() == "") throw new IllegalArgumentException();
		
		LGS_SeatVO outVO = (LGS_SeatVO) seatSvc.do_selectOne_reservation(seat);
		model.addAttribute("seatVO", outVO);
		
		LOG.debug("==================================");
		LOG.debug("outVO : " + outVO);
		LOG.debug("==================================");
		
		return VIEW_;
	}
	
	@ResponseBody
	@RequestMapping(value = "seat/do_retrieve_reservation.do", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
	public String do_retrieve_reservation(SearchVO search, Model model) {
		LOG.debug("==================================");
		LOG.debug("Controller : do_retrieve_seat_reservation");
		LOG.debug("==================================");
		
		LOG.debug("==================================");
		LOG.debug("param : " + search.toString());
		LOG.debug("==================================");
		
		if(search.getPageSize() == 0) search.setPageSize(1000);
		if(search.getPageNum() == 0) search.setPageNum(1);
				
		List<LGS_SeatVO> list = (List<LGS_SeatVO>) seatSvc.do_retrieve_reservation(search);
				
		//model.addAttribute("list", list);
		
		LOG.debug("==================================");
		LOG.debug("list : " + list);
		LOG.debug("==================================");
		
		Gson gson=new Gson();
		String gsonStr = gson.toJson(list);		
		
		return gsonStr;
	}
}
