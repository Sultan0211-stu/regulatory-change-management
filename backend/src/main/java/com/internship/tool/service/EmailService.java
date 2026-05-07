package com.internship.tool.service;

import com.internship.tool.entity.RegulatoryChange;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmailService {

    private final JavaMailSender mailSender;
    private final SpringTemplateEngine templateEngine;

    @Async
    public void sendChangeNotification(String toEmail, String recipientName,
                                        RegulatoryChange change, String action) {
        try {
            Context ctx = new Context();
            ctx.setVariable("recipientName", recipientName);
            ctx.setVariable("action", action);
            ctx.setVariable("changeTitle", change.getTitle());
            ctx.setVariable("status", change.getStatus());
            ctx.setVariable("effectiveDate", change.getEffectiveDate());

            String html = templateEngine.process("notification", ctx);

            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
            helper.setTo(toEmail);
            helper.setSubject("Regulatory Change: " + change.getTitle());
            helper.setText(html, true);
            mailSender.send(message);
        } catch (Exception e) {
            log.error("Failed to send email to {}: {}", toEmail, e.getMessage());
        }
    }
}
