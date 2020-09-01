package com.my.service.impl;

import java.io.File;
import java.util.Map;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import com.my.service.MailService;

@Component
public class MailServiceImpl implements MailService {
	private static final Logger logger = LoggerFactory.getLogger(MailServiceImpl.class);

	@Autowired
	private JavaMailSender mailSender;

	private static final String SENDER = "yeal136@163.com";

	@Override
	public void sendSimpleMail(String to, String subject, String content) {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom(SENDER);
		message.setTo(to);
		message.setSubject(subject);
		message.setText(content);
		try {
			mailSender.send(message);
		} catch (Exception e) {
			logger.error("发送简单邮件时发生异常!", e);
		}

	}

	@Override
	public void sendHtmlMail(String to, String subject, String content) {
		MimeMessage message = mailSender.createMimeMessage();
		try {
			// true表示需要创建一个multipart message
			MimeMessageHelper helper = new MimeMessageHelper(message, true);
			helper.setFrom(SENDER);
			helper.setTo(to);
			helper.setSubject(subject);
			helper.setText(content, true);
			mailSender.send(message);
		} catch (MessagingException e) {
			logger.error("发送MimeMessge时发生异常！", e);
		}

	}

	@Override
	public void sendAttachmentsMail(String to, String subject, String content, String filePath) {
		MimeMessage message = mailSender.createMimeMessage();
		try {
			// true表示需要创建一个multipart message
			MimeMessageHelper helper = new MimeMessageHelper(message, true);
			helper.setFrom(SENDER);
			helper.setTo(to);
			helper.setSubject(subject);
			helper.setText(content, true);

			FileSystemResource file = new FileSystemResource(new File(filePath));
			String fileName = file.getFilename();
			helper.addAttachment(fileName, file);

			mailSender.send(message);
		} catch (MessagingException e) {
			logger.error("发送带附件的MimeMessge时发生异常！", e);
		}

	}

	@Override
	public void sendInlineResourceMail(String to, String subject, String content, String rscPath, String rscId) {
		MimeMessage message = mailSender.createMimeMessage();

		try {
			MimeMessageHelper helper = new MimeMessageHelper(message, true);
			helper.setFrom(SENDER);
			helper.setTo(to);
			helper.setSubject(subject);
			helper.setText(content, true);

			FileSystemResource res = new FileSystemResource(new File(rscPath));
			helper.addInline(rscId, res);

			mailSender.send(message);
			logger.info("嵌入静态资源的邮件已经发送。");
		} catch (MessagingException e) {
			logger.error("发送嵌入静态资源的邮件时发生异常！", e);
		}
	}

	@Override
	public void sendMimeMessge(String to, String subject, String content, Map<String, String> rscIdMap) {
		MimeMessage message = mailSender.createMimeMessage();
		try {
			// true表示需要创建一个multipart message
			MimeMessageHelper helper = new MimeMessageHelper(message, true);
			helper.setFrom(SENDER);
			helper.setTo(to);
			helper.setSubject(subject);
			helper.setText(content, true);

			for (Map.Entry<String, String> entry : rscIdMap.entrySet()) {
				FileSystemResource file = new FileSystemResource(new File(entry.getValue()));
				helper.addInline(entry.getKey(), file);
			}
			mailSender.send(message);
		} catch (MessagingException e) {
			logger.error("发送带静态文件的MimeMessge时发生异常！", e);
		}
	}

}
