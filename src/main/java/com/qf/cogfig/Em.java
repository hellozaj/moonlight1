package com.qf.cogfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Component
public class Em {
    @Autowired
    JavaMailSender javaMailSender;
    @Value("${spring.mail.username}")
    private String MALL_SENDER;
    //测试发送简单的邮件
    @Autowired
    TemplateEngine templateEngine;
    public void em() throws MessagingException {
        Context context=new Context();
        context.setVariable("id","1");
        String emailTemplate=templateEngine.process("emailTemplate",context);
        MimeMessage mimeMessage=javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper=new MimeMessageHelper(mimeMessage,true);
        mimeMessageHelper.setFrom(MALL_SENDER);
        //mimeMessageHelper.setTo("xingchen1w@163.com");qq自动拦截，无法验证可行性
        mimeMessageHelper.setSubject("测试激活邮件");
        mimeMessageHelper.setText(emailTemplate,true);
        javaMailSender.send(mimeMessage);
    }
}
