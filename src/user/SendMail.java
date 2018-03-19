package user;

import java.util.Date;
import java.util.Properties;

import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SendMail {
	/**
	 * 
	 * @param receiveMailAccount 收件人邮箱
	 * @param code 激活码
	 * @throws Exception 
	 */
	public static  void sendMail(String receiveMailAccount,String showToName,String title,String context) throws Exception{
		 String myEmailAccount="18664260260@163.com";
		 String  myEmailPassword="ye102499";
		 String showFromName="发件人昵称";
		 String myEmailSMTPHost="smtp.163.com";
		// 1. 创建参数配置, 用于连接邮件服务器的参数配置
			Properties props = new Properties();                    // 参数配置
	        props.setProperty("mail.transport.protocol", "smtp");   // 使用的协议（JavaMail规范要求）
	        props.setProperty("mail.smtp.host", myEmailSMTPHost);   // 发件人的邮箱的 SMTP 服务器地址
	        props.setProperty("mail.smtp.auth", "true");            // 需要请求认证
	    
	        // 2. 根据配置创建会话对象, 用于和邮件服务器交互
	        Session session = Session.getInstance(props);
	        session.setDebug(true);                                 // 设置为debug模式, 可以查看详细的发送 log

	        // 3. 创建一封邮件
	        MimeMessage message = createMimeMessage(session, myEmailAccount, receiveMailAccount,showFromName,showToName,title,context);

	        // 4. 根据 Session 获取邮件传输对象
	        Transport transport = session.getTransport();
	      
	        transport.connect(myEmailAccount, myEmailPassword);

	        // 6. 发送邮件, 发到所有的收件地址, message.getAllRecipients() 获取到的是在创建邮件对象时添加的所有收件人, 抄送人, 密送人
	        transport.sendMessage(message, message.getAllRecipients());

	        // 7. 关闭连接
	        transport.close();
	}
	/** 创建一封邮件
	 * 
     * @param session 和服务器交互的会话
     * @param sendMail 发件人邮箱
     * @param receiveMail 收件人邮箱
	 * @param showFromName 发件人昵称
	 * @param showToName 收件人昵称
	 * @param title 主题
	 * @param context  正文
	 * @return
	 * @throws Exception
	 */
	 public static  MimeMessage createMimeMessage(Session session, String sendMail, String receiveMail,String showFromName,String showToName,String title,String context) throws Exception {
	        // 1. 创建一封邮件
	        MimeMessage message = new MimeMessage(session);

	        // 2. From: 发件人
	        message.setFrom(new InternetAddress(sendMail, showFromName, "UTF-8"));

	        // 3. To: 收件人（可以增加多个收件人、抄送、密送）
	        message.setRecipient(MimeMessage.RecipientType.TO, new InternetAddress(receiveMail, showToName, "UTF-8"));

	        // 4. Subject: 邮件主题（标题有广告嫌疑，避免被邮件服务器误认为是滥发广告以至返回失败，请修改标题）
	        message.setSubject(title, "UTF-8");

	        // 5. Content: 邮件正文（可以使用html标签）（内容有广告嫌疑，避免被邮件服务器误认为是滥发广告以至返回失败，请修改发送内容）
	        message.setContent(context,"text/html;charset=UTF-8");

	        // 6. 设置发件时间
	        message.setSentDate(new Date());

	        // 7. 保存设置
	        message.saveChanges();

	        return message;
	    }
}
