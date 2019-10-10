package kr.co.sunnyside.usermypage.service.impl;

import java.sql.SQLException;
import java.util.List;
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
import kr.co.sunnyside.login.service.impl.SJH_LoginDao;
import kr.co.sunnyside.login.service.impl.SJH_LoginSvcImpl;
import kr.co.sunnyside.usermypage.service.SJH_MypageSvc;
import kr.co.sunnyside.usermypage.service.SJH_MypageVO;

@Service
public class SJH_MypageSvcImpl implements SJH_MypageSvc {

	Logger LOG = LoggerFactory.getLogger(SJH_LoginSvcImpl.class);
	
	@Autowired
	private SJH_MypageDao mypageDao;
	
	@Autowired
	private MailSender mailSender;
	
	/** 등업기준 */
	public static final int MIN_PAY_FOR_SILVER = 200000;
	public static final int MIN_PAY_FOR_GOLD = 400000;	
	
	
	
	/** 회원 자동 등업  */
	public void tx_upgradeLevels() throws SQLException {
		List<SJH_MypageVO> users =  (List<SJH_MypageVO>) mypageDao.do_getAll();
		for(SJH_MypageVO user : users) {
			if(canUpgradeLevel(user)==true) {
				upgradeLevel(user);
			}
		}
	}
	
	
	//등업 대상자 파악 : true
	private boolean canUpgradeLevel(SJH_MypageVO user) {
		String currLevel = user.getUserLevel();
		
		switch(currLevel) {
			case "BASIC" : return (user.getTotalPay() >= MIN_PAY_FOR_SILVER);
			case "SILVER" : return (user.getTotalPay() >= MIN_PAY_FOR_GOLD);
			case "GOLD" : return false;
			default : throw new IllegalArgumentException("Unknown Level:"+currLevel);
		}
	}
	
	
	//등업하고 메일 보내기
	protected void upgradeLevel(SJH_MypageVO user) throws SQLException {
		user.upgradeLevel(user); //VO부분에 기능을 만듦
		mypageDao.do_levelUpdate(user);
		
		sendUpgradeMail(user); //mail send 
	}
	
	
	
	/**
	 * 등업 사용자에게 mail전송
	 */
	private void sendUpgradeMail(SJH_MypageVO user) {
		try {
			
			//보내는 사람
			String host = "smtp.naver.com";
			final String userName = "사용자아이디";
			final String password = "사용자비번";
			int port = 465;
			
			
			//받는 사람
			String recipient = user.getEmail();
			//제목
			String title = user.getUserName()+"님의 등급이 변경되었습니다.(SUNNYSIDE THEATER)";
			//내용
			String contents = user.getUserId()+"님의 등급이 "+user.getUserLevel()+"로 변경되었습니다.";
			
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
	public int do_update(DTO dto) {
		return mypageDao.do_update(dto);
	}

	@Override
	public int do_delete(DTO dto) {
		return mypageDao.do_delete(dto);
	}

	@Override
	public DTO do_selectOne(DTO dto) {
		return mypageDao.do_selectOne(dto);
	}


}
