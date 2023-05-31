//package com.project.workmanagemantSystem.utils;
//
//import com.project.workmanagemantSystem.domain.User;
//import lombok.RequiredArgsConstructor;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.MessageSource;
//import org.springframework.mail.MailException;
//import org.springframework.mail.javamail.JavaMailSender;
//import org.springframework.mail.javamail.MimeMessageHelper;
//import org.springframework.scheduling.annotation.Async;
//import org.springframework.stereotype.Service;
//import org.thymeleaf.context.Context;
//import org.thymeleaf.spring5.SpringTemplateEngine;
//
//
//import javax.mail.MessagingException;
//import javax.mail.internet.MimeMessage;
//import java.nio.charset.StandardCharsets;
//import java.util.Locale;
//
//@Service
//@RequiredArgsConstructor
//public class MailService {
//    @Autowired
//    private static final String USER = "user";
//    private  SpringTemplateEngine templateEngine;
//    private  MessageSource messageSource;
//    private static final String BASE_URL = "baseUrl";
//    private  JavaMailSender javaMailSender;
//
//
//    @Async
//    public void sendEmail(String to, String subject, String content, boolean isMultipart, boolean isHtml) throws javax.mail.MessagingException {
//
//
//        // Prepare message using a Spring helper
//        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
//        try {
//            MimeMessageHelper message = new MimeMessageHelper(mimeMessage, isMultipart, StandardCharsets.UTF_8.name());
//            message.setTo(to);
//            message.setFrom("ashimgotame06@gmail.com");
//            message.setSubject(subject);
//            message.setText(content, isHtml);
//            javaMailSender.send(mimeMessage);
//        } catch (MailException | javax.mail.MessagingException e) {
//            throw e;
//        }
//    }
//    @Async
//    public void sendEmailFromTemplate(User user, String templateName, String titleKey) throws MessagingException {
//        if (user.getEmail() == null) {
//            return;
//        }
//        Locale locale = Locale.forLanguageTag("en");
//        Context context = new Context(locale);
//        context.setVariable(USER, user);
//        context.setVariable(BASE_URL, "http://localhost:3000");
//        String content = templateEngine.process(templateName, context);
//        String subject = messageSource.getMessage(titleKey, null, locale);
//        sendEmail(user.getEmail(), subject, content, false, true);
//    }
//
//    @Async
//    public void sendActivationEmail(User user) throws MessagingException {
//        sendEmailFromTemplate(user, "templates/mail/activationEmail", "verify user");
//    }
//
//
//}
