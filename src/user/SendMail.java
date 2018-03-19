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
	 * @param receiveMailAccount �ռ�������
	 * @param code ������
	 * @throws Exception 
	 */
	public static  void sendMail(String receiveMailAccount,String showToName,String title,String context) throws Exception{
		 String myEmailAccount="18664260260@163.com";
		 String  myEmailPassword="ye102499";
		 String showFromName="�������ǳ�";
		 String myEmailSMTPHost="smtp.163.com";
		// 1. ������������, ���������ʼ��������Ĳ�������
			Properties props = new Properties();                    // ��������
	        props.setProperty("mail.transport.protocol", "smtp");   // ʹ�õ�Э�飨JavaMail�淶Ҫ��
	        props.setProperty("mail.smtp.host", myEmailSMTPHost);   // �����˵������ SMTP ��������ַ
	        props.setProperty("mail.smtp.auth", "true");            // ��Ҫ������֤
	    
	        // 2. �������ô����Ự����, ���ں��ʼ�����������
	        Session session = Session.getInstance(props);
	        session.setDebug(true);                                 // ����Ϊdebugģʽ, ���Բ鿴��ϸ�ķ��� log

	        // 3. ����һ���ʼ�
	        MimeMessage message = createMimeMessage(session, myEmailAccount, receiveMailAccount,showFromName,showToName,title,context);

	        // 4. ���� Session ��ȡ�ʼ��������
	        Transport transport = session.getTransport();
	      
	        transport.connect(myEmailAccount, myEmailPassword);

	        // 6. �����ʼ�, �������е��ռ���ַ, message.getAllRecipients() ��ȡ�������ڴ����ʼ�����ʱ��ӵ������ռ���, ������, ������
	        transport.sendMessage(message, message.getAllRecipients());

	        // 7. �ر�����
	        transport.close();
	}
	/** ����һ���ʼ�
	 * 
     * @param session �ͷ����������ĻỰ
     * @param sendMail ����������
     * @param receiveMail �ռ�������
	 * @param showFromName �������ǳ�
	 * @param showToName �ռ����ǳ�
	 * @param title ����
	 * @param context  ����
	 * @return
	 * @throws Exception
	 */
	 public static  MimeMessage createMimeMessage(Session session, String sendMail, String receiveMail,String showFromName,String showToName,String title,String context) throws Exception {
	        // 1. ����һ���ʼ�
	        MimeMessage message = new MimeMessage(session);

	        // 2. From: ������
	        message.setFrom(new InternetAddress(sendMail, showFromName, "UTF-8"));

	        // 3. To: �ռ��ˣ��������Ӷ���ռ��ˡ����͡����ͣ�
	        message.setRecipient(MimeMessage.RecipientType.TO, new InternetAddress(receiveMail, showToName, "UTF-8"));

	        // 4. Subject: �ʼ����⣨�����й�����ɣ����ⱻ�ʼ�����������Ϊ���ķ������������ʧ�ܣ����޸ı��⣩
	        message.setSubject(title, "UTF-8");

	        // 5. Content: �ʼ����ģ�����ʹ��html��ǩ���������й�����ɣ����ⱻ�ʼ�����������Ϊ���ķ������������ʧ�ܣ����޸ķ������ݣ�
	        message.setContent(context,"text/html;charset=UTF-8");

	        // 6. ���÷���ʱ��
	        message.setSentDate(new Date());

	        // 7. ��������
	        message.saveChanges();

	        return message;
	    }
}
