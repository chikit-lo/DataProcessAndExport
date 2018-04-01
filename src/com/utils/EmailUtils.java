package com.utils;

import org.apache.commons.mail.EmailAttachment;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;

public class EmailUtils {
	public static void sendEmail(String[] receivers, String[] fileName, String receiverName) {
		HtmlEmail email = new HtmlEmail();
		email.setHostName(EmailProperties.HOSTNAME);
		email.setAuthentication(EmailProperties.EMAILACCOUNT, EmailProperties.EMAILPASSWORD);
		email.setCharset("UTF-8");
		try {
			email.addTo(receivers);
			email.setFrom(EmailProperties.EMAILACCOUNT, "景兴信息管理部");
			email.setSubject(DateUtils.getYearMonth("yyyyMM", -1) + "报表自动导出");
			
			StringBuilder sb = new StringBuilder();
			sb.append("Dear " + receiverName + ":\n");
			sb.append("\t" + DateUtils.getYearMonth("M", -1) + "月报表数据导出如下:\n");
			for (String reportName : fileName) {
				sb.append("\t" + reportName + "\n");
			}
			sb.append("\t请注意查收附件，谢谢！");
			email.setMsg(sb.toString());
			
			for (String file : fileName) {
				EmailAttachment attachment = new EmailAttachment();
				attachment.setPath("D:/" + file);
				attachment.setDisposition(EmailAttachment.ATTACHMENT);
				attachment.setDescription(file.substring(0, file.indexOf(".")));
				email.attach(attachment);
			}
			email.send();
			System.out.println("邮件发送成功!收件人:" + receiverName);
		} catch (EmailException e) {
			e.printStackTrace();
		}
	}
}