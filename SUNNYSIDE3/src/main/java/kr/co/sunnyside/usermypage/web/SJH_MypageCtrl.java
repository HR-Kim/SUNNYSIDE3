package kr.co.sunnyside.usermypage.web;

import java.sql.SQLException;

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
import kr.co.sunnyside.usermypage.service.SJH_MypageSvc;
import kr.co.sunnyside.usermypage.service.SJH_MypageVO;

@Controller
public class SJH_MypageCtrl {

	Logger LOG = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	SJH_MypageSvc mypageSvc;
	
	
	//view
	private final String VIEW_WITHDRAW = "usermypage/user_mypage_withdraw";
	private final String VIEW_UPDATE = "usermypage/user_mapage_mng";

	
	
	//임시테스트화면
	@RequestMapping(value="usermypage/tmp_update_view.do", method = RequestMethod.GET)
	public String tmpUpdateView() {
		LOG.debug("========================");
		LOG.debug("=@Controller=View=");
		LOG.debug("========================");
		return "usermypage/tmp_update";
	}
	
	
	//회원정보수정 화면call
	@RequestMapping(value="usermypage/update_view.do", method = RequestMethod.GET)
	public String updateView() {
		LOG.debug("========================");
		LOG.debug("=@Controller=View=");
		LOG.debug("========================");
		return VIEW_UPDATE;
	}
	
	
	//삭제 화면call
	@RequestMapping(value="usermypage/withdraw_view.do", method = RequestMethod.GET)
	public String withdrawView() {
		LOG.debug("========================");
		LOG.debug("=@Controller=View=");
		LOG.debug("========================");
		
		return VIEW_WITHDRAW;
	}
	
	
	
	//자동 등업
	@RequestMapping(value="usermypage/tx_upgradeLevels.do",method = {RequestMethod.GET, RequestMethod.POST}
			,produces = "application/json; charset=UTF-8")
	public void tx_upgradeLevels() throws SQLException {
		LOG.debug("1=========================");
		LOG.debug("=@Controller=자동등업=");
		LOG.debug("1=========================");
		
		mypageSvc.tx_upgradeLevels();
		
		LOG.debug("1=========================");
		LOG.debug("=@Controller=자동등업 확인=");
		LOG.debug("1=========================");
		
	}
	
	
	//수정
	@RequestMapping(value="usermypage/do_update.do",method = RequestMethod.POST
			,produces = "application/json; charset=UTF-8")
	@ResponseBody		
	public String do_update(SJH_MypageVO inVO) {
		LOG.debug("1=========================");
		LOG.debug("=@Controller=user=="+inVO);
		LOG.debug("1=========================");
		
		//validation
		int flag = mypageSvc.do_update(inVO);
		Message message=new Message();
		if(flag>0) {
			message.setMsgId(flag+"");
			message.setMsgMsg(inVO.getUserId()+"님 수정 되었습니다.");
		}else {
			message.setMsgId(flag+"");
			message.setMsgMsg(inVO.getUserId()+"님 수정 실패.");			
		}
	
		//JSON
		Gson gson=new Gson();
		String json = gson.toJson(message);
		LOG.debug("2=========================");
		LOG.debug("=@Controller=json=="+json);
		LOG.debug("2=========================");
		return json;		
	}
	
	
	
	//단건조회
	@RequestMapping(value="usermypage/do_selectOne.do",method = RequestMethod.POST,produces = "application/json; charset=UTF-8")
	public String do_selectOne(SJH_MypageVO inVO,Model model) {
		LOG.debug("=========================");
		LOG.debug("=@Controller=user=="+inVO);
		LOG.debug("=========================");
		
		if(inVO.getUserId() == null || "".equals(inVO.getUserId())) {
			throw new IllegalArgumentException("아이디를 입력하세요.");
		}
		
		SJH_MypageVO outVO = (SJH_MypageVO) mypageSvc.do_selectOne(inVO);
		model.addAttribute("user", outVO);
		
		
		Gson gson=new Gson();
		String json = gson.toJson(outVO);
		LOG.debug("=========================");
		LOG.debug("=@Controller gson=user=="+json);
		LOG.debug("=========================");		
		
		return VIEW_UPDATE;
	}
	
	
	
	//삭제
	@RequestMapping(value="usermypage/do_delete.do",method = RequestMethod.POST,produces = "application/json; charset=UTF-8")
	@ResponseBody
	public String do_delete(SJH_MypageVO inVO) {
		LOG.debug("========================");
		LOG.debug("=@Controller=user="+inVO);
		LOG.debug("========================");
		
		//flag>0 성공, 실패
		int flag = mypageSvc.do_delete(inVO);
		Message message = new Message();
		if(flag>0) {
			message.setMsgId(flag+"");
			message.setMsgMsg("탈퇴되었습니다.");
		}else {
			message.setMsgId(flag+"");
			message.setMsgMsg("탈퇴실패.");
		}
		
		//json으로 변환
		Gson gson = new Gson();
		String json = gson.toJson(message);
		LOG.debug("=========================");
		LOG.debug("=@Controller 탈퇴gson=user="+json);
		LOG.debug("=========================");		
		
		return json;
	}
	
	
	
	
	
	
	
}
