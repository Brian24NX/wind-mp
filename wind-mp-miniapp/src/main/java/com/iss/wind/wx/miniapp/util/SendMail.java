package com.iss.wind.wx.miniapp.util;

import com.iss.wind.client.util.rest.BusinessException;
import lombok.extern.slf4j.Slf4j;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Message.RecipientType;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.*;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;
import java.util.Properties;

@Slf4j
public class SendMail {
    // 发件人的 邮箱 和 密码（替换为自己的邮箱和密码）
    // PS: 某些邮箱服务器为了增加邮箱本身密码的安全性，给 SMTP 客户端设置了独立密码（有的邮箱称为“授权码”）,
    //     对于开启了独立密码的邮箱, 这里的邮箱密码必需使用这个独立密码（授权码）。
    public static String myEmailAccount = "cmacgmshipment@163.com";
    public static String myEmailPassword = "HORZFPAXGDZITITL";

    // 发件人邮箱的 SMTP 服务器地址, 必须准确, 不同邮件服务器地址不同, 一般(只是一般, 绝非绝对)格式为: smtp.xxx.com
    // 网易163邮箱的 SMTP 服务器地址为: smtp.126.com
    public static String myEmailSMTPHost = "smtp.163.com";

    // 收件人邮箱（替换为自己知道的有效邮箱）
    public static String receiveMailAccount = "yves163sevy@163.com";

    public static void senEmail(String receiveMailAccount, String path){
        try {
            // 1. 创建参数配置, 用于连接邮件服务器的参数配置
            Properties props = new Properties();                    // 参数配置
            props.setProperty("mail.transport.protocol", "smtp");   // 使用的协议（JavaMail规范要求）
            props.setProperty("mail.smtp.host", myEmailSMTPHost);   // 发件人的邮箱的 SMTP 服务器地址
            props.setProperty("mail.smtp.auth", "true");            // 需要请求认证

            // PS: 某些邮箱服务器要求 SMTP 连接需要使用 SSL 安全认证 (为了提高安全性, 邮箱支持SSL连接, 也可以自己开启),
            //     如果无法连接邮件服务器, 仔细查看控制台打印的 log, 如果有有类似 “连接失败, 要求 SSL 安全连接” 等错误,
            //     取消下面 /* ... */ 之间的注释代码, 开启 SSL 安全连接。
        /*
        // SMTP 服务器的端口 (非 SSL 连接的端口一般默认为 25, 可以不添加, 如果开启了 SSL 连接,
        //                  需要改为对应邮箱的 SMTP 服务器的端口, 具体可查看对应邮箱服务的帮助,
        //                  QQ邮箱的SMTP(SLL)端口为465或587, 其他邮箱自行去查看)
        final String smtpPort = "465";
        props.setProperty("mail.smtp.port", smtpPort);
        props.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.setProperty("mail.smtp.socketFactory.fallback", "false");
        props.setProperty("mail.smtp.socketFactory.port", smtpPort);
        */

            // 2. 根据配置创建会话对象, 用于和邮件服务器交互
            Session session = Session.getInstance(props);
            // 设置为debug模式, 可以查看详细的发送 log
            session.setDebug(true);

            // 3. 创建一封邮件
            //简单邮件
//        MimeMessage message = createEasyMimeMessage(session, myEmailAccount, receiveMailAccount);
            //带图片邮件
//        MimeMessage message = createImageMail(session, myEmailAccount, receiveMailAccount);
            //带附件邮件
            MimeMessage message = createAttachMail(session, myEmailAccount, receiveMailAccount, path);
            //带图片和附件邮件
//        MimeMessage message = createMixedMail(session, myEmailAccount, receiveMailAccount);

            // 4. 根据 Session 获取邮件传输对象
            Transport transport = session.getTransport();

            // 5. 使用 邮箱账号 和 密码 连接邮件服务器, 这里认证的邮箱必须与 message 中的发件人邮箱一致, 否则报错
            //
            //    PS_01: 如果连接服务器失败, 都会在控制台输出相应失败原因的log。
            //    仔细查看失败原因, 有些邮箱服务器会返回错误码或查看错误类型的链接,
            //    根据给出的错误类型到对应邮件服务器的帮助网站上查看具体失败原因。
            //
            //    PS_02: 连接失败的原因通常为以下几点, 仔细检查代码:
            //           (1) 邮箱没有开启 SMTP 服务;
            //           (2) 邮箱密码错误, 例如某些邮箱开启了独立密码;
            //           (3) 邮箱服务器要求必须要使用 SSL 安全连接;
            //           (4) 请求过于频繁或其他原因, 被邮件服务器拒绝服务;
            //           (5) 如果以上几点都确定无误, 到邮件服务器网站查找帮助。
            //
            transport.connect(myEmailAccount, myEmailPassword);

            // 6. 发送邮件, 发到所有的收件地址, message.getAllRecipients() 获取到的是在创建邮件对象时添加的所有收件人, 抄送人, 密送人
            //发送邮件
            transport.sendMessage(message, message.getAllRecipients());

            // 7. 关闭连接
            transport.close();
        }catch (Exception e){
            log.error("senEmail-exception：",e);
            throw new BusinessException("请求异常或超时!");
        }
    }

    /**
     * 创建一封只包含文本的简单邮件
     *
     * @param session     和服务器交互的会话
     * @param sendMail    发件人邮箱
     * @param receiveMail 收件人邮箱
     * @return
     * @throws Exception
     */
    public static MimeMessage createEasyMimeMessage(Session session, String sendMail, String receiveMail) throws Exception {
        // 1. 创建一封邮件
        MimeMessage message = new MimeMessage(session);

        // 2. From: 发件人
        message.setFrom(new InternetAddress(sendMail, "你是谁", "UTF-8"));

        // 3. To: 收件人（可以增加多个收件人、抄送、密送）
        message.setRecipient(MimeMessage.RecipientType.TO, new InternetAddress(receiveMail, "XX用户", "UTF-8"));
        //    To: 增加收件人（可选）
//        message.addRecipient(MimeMessage.RecipientType.TO, new InternetAddress("dd@receive.com", "USER_DD", "UTF-8"));
        //    Cc: 抄送（可选）
//        message.setRecipient(MimeMessage.RecipientType.CC, new InternetAddress("ee@receive.com", "USER_EE", "UTF-8"));
        //    Bcc: 密送（可选）
//        message.setRecipient(MimeMessage.RecipientType.BCC, new InternetAddress("ff@receive.com", "USER_FF", "UTF-8"));

        // 4. Subject: 邮件主题
        message.setSubject("【发票】", "UTF-8");

        // 5. Content: 邮件正文（可以使用html标签）
        message.setContent("尊敬乘客：<br/>感谢您使用开票服务，查看发票及对应的行程单请点击附件。", "text/html;charset=UTF-8");
        // 6. 设置发件时间
        message.setSentDate(new Date());

        // 7. 保存设置
        message.saveChanges();

        return message;
    }

    /**
     * @Method: createImageMail
     * @Description: 生成一封邮件正文带图片的邮件
     *
     * @param session
     * @return
     * @throws Exception
     */
    public static MimeMessage createImageMail(Session session, String sendMail, String receiveMail) throws Exception {
        //创建邮件
        MimeMessage message = new MimeMessage(session);
        // 设置邮件的基本信息
        //发件人
        // 2. From: 发件人
        message.setFrom(new InternetAddress(sendMail, "我是谁", "UTF-8"));

        // 3. To: 收件人（可以增加多个收件人、抄送、密送）
        message.addRecipient(RecipientType.TO, new InternetAddress(receiveMail, "我是谁", "UTF-8"));
        //邮件标题
        //4.设置邮件主题
        message.setSubject("【行程发票】");

        // 准备邮件数据
        // 5. 创建图片"节点"
        MimeBodyPart text = new MimeBodyPart();
        // 准备邮件正文数据
        text.setContent("尊敬的乘客：<br/>感谢您使用开票服务，查看发票及对应的行程单请点击附件。" +
                "<br/><img src='cid:image'>", "text/html;charset=UTF-8");
        // 准备图片数据
        MimeBodyPart image = new MimeBodyPart();
        // 读取本地文件
        DataHandler dh = new DataHandler(new FileDataSource("E:\\文件\\20210628162628.png"));
        // 将图片数据添加到"节点"
        image.setDataHandler(dh);
        // 为"节点"设置一个唯一编号（在文本"节点"将引用该ID）
        image.setContentID("image");
        // 描述数据关系
        // 7. （文本+图片）设置 文本 和 图片"节点"的关系（将 文本 和 图片"节点"合成一个混合"节点"）
        MimeMultipart mm = new MimeMultipart();
        mm.addBodyPart(text);
        mm.addBodyPart(image);
        // 关联关系
        mm.setSubType("related");

        message.setContent(mm);
        message.saveChanges();
        //将创建好的邮件写入到E盘以文件的形式进行保存
//         message.writeTo(new FileOutputStream("E:\\ImageMail.eml"));
        //返回创建好的邮件
        return message;
    }

    /**
     * @Method: createAttachMail
     * @Description: 创建一封带附件的邮件
     *
     * @param session
     * @return
     * @throws Exception
     */
    public static MimeMessage createAttachMail(Session session, String sendMail, String receiveMail, String path) throws Exception {
        //创建邮件
        MimeMessage message = new MimeMessage(session);
        // 设置邮件的基本信息
        //发件人
        // 2. From: 发件人
        message.setFrom(new InternetAddress(sendMail, "CMA", "UTF-8"));

        // 3. To: 收件人（可以增加多个收件人、抄送、密送）
        message.addRecipient(RecipientType.TO, new InternetAddress(receiveMail, receiveMail, "UTF-8"));

        //邮件标题
        message.setSubject("【PDF文件】");

        //创建邮件正文，为了避免邮件正文中文乱码问题，需要使用charset=UTF-8指明字符编码
        MimeBodyPart text = new MimeBodyPart();
        text.setContent("尊敬用户：<br/>感谢您使用本服务，查看航线PDF文件，请点击附件。", "text/html;charset=UTF-8");

        //创建邮件附件
        MimeBodyPart attach = new MimeBodyPart();
        //可访问的url
//        String path = "https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fwww.eimg.top%2Fimages%2F2020%2F03%2F02%2F46d94b77a08474438e094f88637d5f6f.png&refer=http%3A%2F%2Fwww.eimg.top&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=auto?sec=1652270251&t=3aa9b249060345da9209be483835dfbb";
        URL url = new URL(path);
        DataHandler dh = new DataHandler(url);
        attach.setDataHandler(dh);
        // 这里很重要，通过下面的Base64编码的转换可以保证你的中文附件标题名在发送时不会变成乱码
        sun.misc.BASE64Encoder enc = new sun.misc.BASE64Encoder();
        attach.setFileName("=?UTF-8?B?" + enc.encode(dh.getName().getBytes()) + "?=");
//        attach.setFileName(dh.getName());

        //创建容器描述数据关系
        MimeMultipart mp = new MimeMultipart();
        mp.addBodyPart(text);
        mp.addBodyPart(attach);
        mp.setSubType("mixed");

        message.setContent(mp);
        message.saveChanges();
        //将创建的Email写入到E盘存储
//        message.writeTo(new FileOutputStream("E:\\attachMail.eml"));
        //返回生成的邮件
        return message;
    }

    /**
     * @Method: createMixedMail
     * @Description: 生成一封带附件和带图片的邮件
     *
     * @param session
     * @return
     * @throws Exception
     */
    public static MimeMessage createMixedMail(Session session, String sendMail, String receiveMail) throws Exception {
        //创建邮件
        MimeMessage message = new MimeMessage(session);
        // 设置邮件的基本信息
        //发件人
        // 2. From: 发件人
        message.setFrom(new InternetAddress(sendMail, "你是谁", "UTF-8"));

        // 3. To: 收件人（可以增加多个收件人、抄送、密送）
        message.addRecipient(RecipientType.TO, new InternetAddress(receiveMail, "我是谁", "UTF-8"));
        //4.设置邮件主题
        message.setSubject("【发票】");

        //正文
        MimeBodyPart text = new MimeBodyPart();
        text.setContent("尊敬的乘客：<br/>感谢您使用开票服务，查看发票及对应的行程单请点击附件。" +
                "<br/><img src='cid:image'>","text/html;charset=UTF-8");

        //图片
        // 5. 创建图片"节点"
        MimeBodyPart image = new MimeBodyPart();
        // 读取本地文件
        image.setDataHandler(new DataHandler(new FileDataSource("E:\\文件\\20210628162640.png")));
        // 为"节点"设置一个唯一编号（在文本"节点"将引用该ID）
        image.setContentID("image");

        //附件1
        // 创建附件"节点"
        MimeBodyPart attach = new MimeBodyPart();
        // 读取本地文件
        DataHandler dh = new DataHandler(new FileDataSource("E:\\文件\\20210628162640.png"));
//        String path2  = "https://zhidache-mini.oss-cn-shenzhen.aliyuncs.com/vehicle/2021063031747694.pdf";
        //上传网络文件使用
//        DataHandler dh = new DataHandler(new FileDataSource(uploadPath));
//        URL url = new URL(path2);
//        DataHandler dh = new DataHandler(url);
        // 将附件数据添加到"节点"
        attach.setDataHandler(dh);

        // 设置附件的文件名（需要编码）
        attach.setFileName(MimeUtility.encodeText(dh.getName()));
        //上传网络文件使用
//        String fileName = dh.getName().substring(dh.getName().lastIndexOf("/")+1);
//        attach.setFileName(MimeUtility.encodeText(fileName));
        // 这里很重要，通过下面的Base64编码的转换可以保证你的中文附件标题名在发送时不会变成乱码
//        sun.misc.BASE64Encoder enc = new sun.misc.BASE64Encoder();
//        attach.setFileName("=?UTF-8?B?" + enc.encode(dh.getName().getBytes()) + "?=");
//        attach.setFileName(dh.getName());

        //附件2
//        MimeBodyPart attach2 = new MimeBodyPart();
//        DataHandler dh2 = new DataHandler(new FileDataSource("E:\\文件\\20210628162640.png"));
//        attach2.setDataHandler(dh2);
//        attach2.setFileName(MimeUtility.encodeText(dh2.getName()));

        //描述关系:正文和图片
        // 设置（文本+图片）和 附件 的关系（合成一个大的混合"节点" / Multipart ）
        MimeMultipart mp1 = new MimeMultipart();
        mp1.addBodyPart(text);
        mp1.addBodyPart(image);
        // 关联关系
        mp1.setSubType("related");

        //描述关系:正文和附件
        MimeMultipart mp2 = new MimeMultipart();
        // 如果有多个附件，可以创建多个多次添加
        mp2.addBodyPart(attach);
//        mp2.addBodyPart(attach2);

        //代表正文的bodypart
        MimeBodyPart content = new MimeBodyPart();
        content.setContent(mp1);
        mp2.addBodyPart(content);
        // 混合关系
        mp2.setSubType("mixed");

        message.setContent(mp2);
        message.saveChanges();

        //保存报文到本地
//        message.writeTo(new FileOutputStream("E:\\MixedMail.eml"));
        //返回创建好的的邮件
        return message;
    }
}