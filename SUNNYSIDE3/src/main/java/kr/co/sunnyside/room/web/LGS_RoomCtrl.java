package kr.co.sunnyside.room.web;

import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

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
import kr.co.sunnyside.room.service.LGS_RoomSvc;
import kr.co.sunnyside.room.service.LGS_RoomVO;

@Controller
public class LGS_RoomCtrl {
	Logger LOG = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	LGS_RoomSvc roomSvc;	//상영관정보 지점,상영관id, 상영관명, 총좌석, 잔여좌석

	//view
	private final String VIEW_TABLE = "table/theater_table";
	
	/**
	 * 상영관추가
	 */
	@ResponseBody
	@RequestMapping(value = "room/do_save.do", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
	public String do_save_room(LGS_RoomVO room) {
		LOG.debug("==================================");
		LOG.debug("Controller : do_save_room");
		LOG.debug("==================================");
		
		//파라미터검사
		if(room == null) throw new NullArgumentException();
		if(room.getRoomNm() == null || room.getRoomNm() == "") throw new IllegalArgumentException();		//상영관이름
		if(room.getBranchId() == null || room.getBranchId() == "") throw new IllegalArgumentException();	//지점id
		if(room.getTotalSeat() < 0) throw new IllegalArgumentException();	//총좌석
		
		//남은좌석 초기화
		room.setRestSeat(room.getTotalSeat());
		
		int flag = roomSvc.do_save(room);
		
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
	@RequestMapping(value = "room/do_update.do", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
	public String do_update(LGS_RoomVO room) {
		LOG.debug("==================================");
		LOG.debug("Controller : do_update_room");
		LOG.debug("==================================");
		
		//파라미터검사
		if(room == null) throw new NullArgumentException();	//null
		if(room.getRoomId() == null || room.getRoomId() == "") throw new IllegalArgumentException();
		if(room.getTotalSeat() < 0) throw new IllegalArgumentException();	//총좌석
		if(room.getRestSeat() < 0) throw new IllegalArgumentException();	//남은좌석
		
		int flag = roomSvc.do_update(room);
		
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
	@RequestMapping(value = "room/do_delete.do", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
	public String do_delete(LGS_RoomVO room) {
		LOG.debug("==================================");
		LOG.debug("Controller : do_delete_room");
		LOG.debug("==================================");
		
		if(room == null) throw new NullArgumentException();
		if(room.getRoomId() == null || room.getRoomId() == "") throw new IllegalArgumentException();
		
		int flag = roomSvc.do_delete(room);
		
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
	
	@RequestMapping(value = "room/do_selectOne.do", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
	public String do_selectOne(LGS_RoomVO room, Model model) {
		LOG.debug("==================================");
		LOG.debug("Controller : do_selectOne_room");
		LOG.debug("==================================");
		
		if(room == null) throw new NullArgumentException(); //null
		if(room.getRoomNm() == null || room.getRoomNm() == "") throw new IllegalArgumentException();
		
		LGS_RoomVO outVO = (LGS_RoomVO) roomSvc.do_selectOne(room);
		model.addAttribute("roomVO", outVO);
		
		LOG.debug("==================================");
		LOG.debug("roomVO : " + outVO);
		LOG.debug("==================================");
		
		return VIEW_TABLE;
	}
	
	@ResponseBody
	@RequestMapping(value = "room/do_retrieve.do", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
	public String do_retrieve(SearchVO search, Model model) {
		LOG.debug("==================================");
		LOG.debug("Controller : do_retrieve_room");
		LOG.debug("==================================");
		
		if(search.getPageSize() == 0) search.setPageSize(10);
		if(search.getPageNum() == 0) search.setPageNum(1);
				
		List<LGS_RoomVO> roomList = (List<LGS_RoomVO>) roomSvc.do_retrieve(search);
		
		LOG.debug("==================================");
		LOG.debug("roomList : " + roomList);
		LOG.debug("==================================");
		
		Gson gson=new Gson();
		String gsonStr = gson.toJson(roomList);		
		
		return gsonStr;
	}
	
	@ResponseBody
	@RequestMapping(value = "room/do_updateName.do", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
	public String do_updateName(LGS_RoomVO room) {
		LOG.debug("==================================");
		LOG.debug("Controller : do_updateRoomName");
		LOG.debug("==================================");
		
		//파라미터검사
		if(room == null) throw new NullArgumentException();	//null
		if(room.getRoomId() == null || room.getRoomId() == "") throw new IllegalArgumentException();
		if(room.getRoomNm() == null || room.getRoomNm() == "") throw new IllegalArgumentException();

		int flag = roomSvc.do_updateName(room);
		
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
}
