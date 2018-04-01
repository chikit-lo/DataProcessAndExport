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
			email.setFrom(EmailProperties.EMAILACCOUNT, "������Ϣ����");
			email.setSubject(DateUtils.getYearMonth("yyyyMM", -1) + "�����Զ�����");
			
			StringBuilder sb = new StringBuilder();
			sb.append("Dear " + receiverName + ":\n");
			sb.append("\t" + DateUtils.getYearMonth("M", -1) + "�±������ݵ�������:\n");
			for (String reportName : fileName) {
				sb.append("\t" + reportName + "\n");
			}
			sb.append("\t��ע����ո�����лл��");
			email.setMsg(sb.toString());
			
			for (String file : fileName) {
				EmailAttachment attachment = new EmailAttachment();
				attachment.setPath("D:/" + file);
				attachment.setDisposition(EmailAttachment.ATTACHMENT);
				attachment.setDescription(file.substring(0, file.indexOf(".")));
				email.attach(attachment);
			}
			email.send();
			System.out.println("�ʼ����ͳɹ�!�ռ���:" + receiverName);
		} catch (EmailException e) {
			e.printStackTrace();
		}
	}
}