package com.transforman.service;

import java.util.Date;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.transforman.service.MyAuthentication;

public class MailService 
{
	public void sendCode(String email, int code)
	{
		Properties p = System.getProperties();
		p.put("mail.smtp.starttls.enable", "true");     // gmail은 무조건 true 고정
		p.put("mail.smtp.host", "smtp.gmail.com");      // smtp 서버 주소
		p.put("mail.smtp.auth","true");                 // gmail은 무조건 true 고정
		p.put("mail.smtp.port", "587");                 // gmail 포트

		Authenticator auth = new MyAuthentication();

		//session 생성 및  MimeMessage생성
		Session session = Session.getDefaultInstance(p, auth);
		MimeMessage msg = new MimeMessage(session);

		try{
			//편지보낸시간
			msg.setSentDate(new Date());

			InternetAddress from = new InternetAddress() ;


			from = new InternetAddress("test<test@gmail.com>");

			// 이메일 발신자
			msg.setFrom(from);


			// 이메일 수신자
			InternetAddress to = new InternetAddress(email);
			msg.setRecipient(Message.RecipientType.TO, to);

			// 이메일 제목
			msg.setSubject("Trans For MAN : 이메일 인증", "UTF-8");

			// 이메일 내용
			msg.setText(code + " : 인증번호를 입력해주세요.", "UTF-8");

			// 이메일 헤더
			msg.setHeader("content-Type", "text/html");

			//메일보내기
			javax.mail.Transport.send(msg);

		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void sendId(String email, String id)
	{
		Properties p = System.getProperties();
		p.put("mail.smtp.starttls.enable", "true");     // gmail은 무조건 true 고정
		p.put("mail.smtp.host", "smtp.gmail.com");      // smtp 서버 주소
		p.put("mail.smtp.auth","true");                 // gmail은 무조건 true 고정
		p.put("mail.smtp.port", "587");                 // gmail 포트

		Authenticator auth = new MyAuthentication();

		//session 생성 및  MimeMessage생성
		Session session = Session.getDefaultInstance(p, auth);
		MimeMessage msg = new MimeMessage(session);

		try{
			//편지보낸시간
			msg.setSentDate(new Date());

			InternetAddress from = new InternetAddress() ;


			from = new InternetAddress("test<test@gmail.com>");

			// 이메일 발신자
			msg.setFrom(from);


			// 이메일 수신자
			InternetAddress to = new InternetAddress(email);
			msg.setRecipient(Message.RecipientType.TO, to);

			// 이메일 제목
			msg.setSubject("Trans For MAN : 아이디 찾기", "UTF-8");

			// 이메일 내용
			msg.setText("당신의 아이디는 " + id + "입니다.", "UTF-8");

			// 이메일 헤더
			msg.setHeader("content-Type", "text/html");

			//메일보내기
			javax.mail.Transport.send(msg);

		}catch (Exception e) {
			e.printStackTrace();
		}
	}
}

class MyAuthentication extends Authenticator {

	PasswordAuthentication pa;


	public MyAuthentication(){

		String id = "emailtest20170314@gmail.com";       // 구글 ID
		String pw = "test20170314";          // 구글 비밀번호

		// ID와 비밀번호를 입력한다.
		pa = new PasswordAuthentication(id, pw);

	}

	// 시스템에서 사용하는 인증정보
	public PasswordAuthentication getPasswordAuthentication() {
		return pa;
	}
}