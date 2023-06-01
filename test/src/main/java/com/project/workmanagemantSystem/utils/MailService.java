package com.project.workmanagemantSystem.utils;

import com.project.workmanagemantSystem.domain.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.nio.charset.StandardCharsets;
import java.util.Locale;

@Service
public class MailService {

    @Value("${spring.mail.username}")
    private String username;

    @Value("http://localhost:3000")
    private String url;

    private JavaMailSender mailSender;
    private TemplateEngine templateEngine;
    private  MessageSource messageSource;

    @Autowired
    public MailService(JavaMailSender mailSender, TemplateEngine templateEngine, MessageSource messageSource) {
        this.mailSender = mailSender;
        this.templateEngine = templateEngine;
        this.messageSource = messageSource;
    }

    @Async
    public void sendEmail(String to, String subject, String content, boolean isMultipart, boolean isHtml) throws MessagingException {

        // Prepare message using a Spring helper
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        try {
            MimeMessageHelper message = new MimeMessageHelper(mimeMessage, isMultipart, StandardCharsets.UTF_8.name());
            message.setTo(to);
            message.setFrom(username);
            message.setSubject(subject);
            message.setText(content, isHtml);
            mailSender.send(mimeMessage);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        } catch (MailException e) {
            throw new RuntimeException(e);
        }
    }

    @Async
    public void sendOTPToClient(Client user, String templateName, String titleKey) throws MessagingException {
        Locale locale = Locale.forLanguageTag("en");
        Context context = new Context(locale);
        context.setVariable("USER", user);
        context.setVariable("baseUrl", url);
        String content = templateEngine.process(templateName, context);
        String subject = messageSource.getMessage(titleKey, null, locale);
        sendEmail(user.getEmail(), subject, content, false, true);
    }

    @Async
    public void sendOtpVerificationEmail(Client user) throws MessagingException {
        sendOTPToClient(user, "mail/activationEmail", "OTP.verification");
    }

}