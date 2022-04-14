package com.iss.wind.wx.miniapp.util;

import com.iss.wind.client.util.rest.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;

@Slf4j
@Component
public class MailUtils {
    private final Logger LOG = LoggerFactory.getLogger(this.getClass());

    @Resource
    private JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String mailFormUserName;

    public void sendMail(String subject, String content, String toMailArray,String path){
        MimeMessage mimeMessage = null;
        try {
            System.out.println(content);
            mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
            helper.setFrom(this.mailFormUserName,"WIND");
            helper.setTo(toMailArray);
            helper.setSubject(subject);
            helper.addAttachment(subject+".pdf",getFile(path));
            helper.setText(content,true);
            mailSender.send(mimeMessage);
            log.info("邮件发送成功，接受者："+ toMailArray +"等，标题为："+ subject +"。内容为：" + content + "。");
        } catch (Exception e) {
            log.error("fail:",e);
            try {
                mailSender.send(mimeMessage);
                log.info("邮件发送成功，接受者："+ toMailArray +"等，标题为："+ subject +"。内容为：" + content + "。");
            } catch (Exception ex) {
                log.error("邮件发送失败，接受者："+ toMailArray +"等，标题为："+ subject +"。内容为：" + content + "。错误信息：" ,ex);
                throw new BusinessException("请求异常或超时!");
            }
        }
    }

    public static File getFile(String url) throws Exception {
        //对本地文件命名
        String fileName = url.substring(url.lastIndexOf("."),url.length());
        File file = null;

        URL urlfile;
        InputStream inStream = null;
        OutputStream os = null;
        try {
            file = File.createTempFile("net_url", fileName);
            //下载
            urlfile = new URL(url);
            inStream = urlfile.openStream();
            os = new FileOutputStream(file);

            int bytesRead = 0;
            byte[] buffer = new byte[8192];
            while ((bytesRead = inStream.read(buffer, 0, 8192)) != -1) {
                os.write(buffer, 0, bytesRead);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != os) {
                    os.close();
                }
                if (null != inStream) {
                    inStream.close();
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return file;
    }

}
