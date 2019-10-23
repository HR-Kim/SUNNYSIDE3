package kr.co.sunnyside.login.service.impl;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

import kr.co.sunnyside.cmn.DTO;
import kr.co.sunnyside.cmn.Message;
import kr.co.sunnyside.cmn.StringUtil;
import kr.co.sunnyside.login.service.SJH_LoginSvc;
import kr.co.sunnyside.login.service.SJH_LoginVO;

@Service
public class SJH_LoginSvcImpl implements SJH_LoginSvc{
	
	Logger LOG = LoggerFactory.getLogger(SJH_LoginSvcImpl.class);
	
	@Autowired
	private SJH_LoginDao loginDao;

	@Autowired
	private MailSender mailSender;
	
	
	
	/** 아이디 중복체크 */
	@Override
	public DTO id_check(DTO dto) {
		Message outMsg = new Message();
		//-----------------------------
		//1.ID체크
		//-----------------------------
		int flag = loginDao.id_check(dto);
		if(flag>0) {
			outMsg.setMsgId("10");
			outMsg.setMsgMsg("이미 존재하는 아이디입니다."); //나중에 메세지 프로퍼티로 처리
			return outMsg;
		}else if(flag<1) {
			outMsg.setMsgId("20");
			outMsg.setMsgMsg("사용가능한 아이디 입니다."); //나중에 메세지 프로퍼티로 처리
			return outMsg;
		}
		
		LOG.debug("=================");
		LOG.debug("=outMsg="+outMsg);
		LOG.debug("=================");
		
		return outMsg;
	}
	
	

	/** 아이디 찾기 */
	@Override
	public DTO id_find(DTO dto) {
		return loginDao.id_find(dto);
	}

	/** 비밀번호 찾기 */
	@Override
	public int pw_find(DTO dto) {
		int flag = loginDao.pw_find(dto);
		
		//비밀번호 찾기 성공 시 임시 비밀번호 메일로 전송
		if(flag>0) {
			SJH_LoginVO changedVO = (SJH_LoginVO) loginDao.do_selectOne(dto);
			LOG.debug("ㅋㅋchangedVO: "+changedVO);
			sendPwFindMail(changedVO);
		}
		return flag;
	}
	
	
    // 비번  mail전송
	private void sendPwFindMail(SJH_LoginVO user) {
		try {
			SJH_LoginVO changedVO = (SJH_LoginVO) loginDao.do_selectOne(user);
			
			//보내는 사람
			String host = "smtp.naver.com";
			final String userName = "glwlzkwp";
			final String password = "";
			int port = 465;
			
			
			//받는 사람
			String recipient = user.getEmail();
			//제목
			String title = user.getUserName()+"님 임시 비밀번호가 발송되었습니다.";
			//내용
			String contents = user.getUserName()+"님의 임시 비밀번호는 "+changedVO.getPasswd()+"입니다.\n임시 비밀번호로 로그인 후 회원정보수정에서 비밀번호를 변경해주세요.";
			
			//SMTP 서버 설정
			Properties props = System.getProperties();
			props.put("mail.smtp.host", host);
			props.put("mail.smtp.port", port);
			props.put("mail.smtp.auth", "true");
			props.put("mail.smtp.ssl.enable", "true");
			props.put("mail.smtp.ssl.trust", host);
			
			
			//인증
			Session session = Session.getInstance(props, new Authenticator() {
				String uName = userName;
				String passwd = password;
				
				@Override
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(uName, passwd);
				}
				
			});	
			
			session.setDebug(true);
			
			SimpleMailMessage mimeMessage = new SimpleMailMessage();
			// 보내는 사람
			mimeMessage.setFrom("glwlzkwp@naver.com");
			// 받는사람
			mimeMessage.setTo(recipient);
			// 제목
			mimeMessage.setSubject(title);
			// 내용
			mimeMessage.setText(contents);
			// 전송
			mailSender.send(mimeMessage);
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		LOG.debug("======================");
		LOG.debug("=mail send=");
		LOG.debug("======================");
		
	}
	
	
	@Override
	public DTO do_login(DTO dto) {
		Message outMsg = new Message();
		
		//-----------------------------
		//1.ID체크
		//-----------------------------
		int flag = loginDao.id_check(dto);
		if(flag<1) {
			outMsg.setMsgId("10");
			outMsg.setMsgMsg("아이디를 확인하세요."); //나중에 메세지 프로퍼티로 처리
			return outMsg;
		}
		
		//-----------------------------
		//2.비번체크
		//-----------------------------
		flag = loginDao.passwd_check(dto);
		if(flag<1) {
			outMsg.setMsgId("20");
			outMsg.setMsgMsg("비밀번호를 확인하세요."); //나중에 메세지 프로퍼티로 처리
			return outMsg;
		}
		
		if(flag==1) {
			outMsg.setMsgId("30");
		}
		
		LOG.debug("=================");
		LOG.debug("=outMsg="+outMsg);
		LOG.debug("=================");
		
		return outMsg;
	}


	
	/** 회원가입  */
	@Override
	public int do_save(DTO dto) {
		return loginDao.do_save(dto);
	}
	
	
	/** 단건조회 */
	@Override
	public DTO do_selectOne(DTO dto) {
		return loginDao.do_selectOne(dto);
	}

}
