package com.shty.collect.test;

import java.util.ArrayList;
import java.util.List;

import com.shty.collect.mail.MailSender;

public class MailTest {

	public static void main(String[] args) {

		MailSender mailSender = new MailSender("rose20141015@126.com", "rose2014");
		try {

			List<String> attachment = new ArrayList<>();

			attachment.add("E:\\mail.png");
			attachment.add("E:\\sd.jpg");

			String mailText = "这是文本信息!";

			mailSender.send("rose20141015@126.com", "邮件自动发送系统", MailSender.getMimeMultipart(attachment, mailText));
			System.out.println("邮件发送成功");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}