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
	@RequestMapping(value = "seat/do_save.do", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
	public String do_save_seat(LGS_SeatVO seat) {
		LOG.debug("==================================");
		LOG.debug("Controller : do_save_seat");
		LOG.debug("==================================");
		
		if(seat == null) throw new NullArgumentException();	//null
		if(seat.getBranchId() == null || seat.getBranchId() == "") throw new IllegalArgumentException(); //지점id
		if(seat.getRoomId() == null || seat.getRoomId() == "") throw new IllegalArgumentException(); //상영관id
		if(seat.getSeatNum() == 0) throw new IllegalArgumentException(); //좌석넘버
		
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
		String jsonString = gson.toJson(message);
		
		LOG.debug("==================================");
		LOG.debug("jsonString : " + jsonString);
		LOG.debug("==================================");
		
		return jsonString;
	}
	
	@ResponseBody
	@RequestMapping(value = "seat/do_delete.do", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
	public String do_delete(LGS_SeatVO seat) {
		LOG.debug("==================================");
		LOG.debug("Controller : do_delete_seat");
		LOG.debug("==================================");
		
		if(seat == null) throw new NullArgumentException(); //null
		if(seat.getSeatNum() == 0) throw new IllegalArgumentException();	//상영관id
		
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
		String jsonString = gson.toJson(message);
		
		LOG.debug("==================================");
		LOG.debug("jsonString : " + jsonString);
		LOG.debug("==================================");
		
		return jsonString;
	}
	
	@RequestMapping(value = "seat/get_selectOne.do", method = RequestMethod.POST)
	public String get_selectOne(LGS_SeatVO seat, Model model) {
		LOG.debug("==================================");
		LOG.debug("Controller : get_selectOne_seat");
		LOG.debug("==================================");
		
		if(seat == null) throw new NullArgumentException(); //null
		if( seat.getSeatNum() == 0) throw new IllegalArgumentException();	//지점id
		
		LGS_SeatVO outVO = (LGS_SeatVO) seatSvc.get_selectOne(seat);
		model.addAttribute("vo", outVO);
		
		LOG.debug("==================================");
		LOG.debug("outVO : " + outVO);
		LOG.debug("==================================");
		
		return VIEW_;
	}
	
	@RequestMapping(value = "seat/get_retrieve.do", method = RequestMethod.POST)
	public List<?> get_retrieve(SearchVO search, Model model) {
		LOG.debug("==================================");
		LOG.debug("Controller : get_retrieve_seat");
		LOG.debug("==================================");
		
		if(search.getPageSize() == 0) search.setPageSize(10);
		if(search.getPageNum() == 0) search.setPageNum(1);
				
		List<LGS_SeatVO> list = (List<LGS_SeatVO>) seatSvc.get_retrieve(search);
		model.addAttribute("list", list);
		
		LOG.debug("==================================");
		LOG.debug("list : " + list);
		LOG.debug("==================================");
		
		return list;
	}

}
