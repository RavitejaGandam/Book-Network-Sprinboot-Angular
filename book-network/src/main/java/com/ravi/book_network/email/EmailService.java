package com.ravi.book_network.email;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

import static java.nio.charset.StandardCharsets.UTF_8;
import static org.springframework.mail.javamail.MimeMessageHelper.MULTIPART_MODE_MIXED;

@Service
@RequiredArgsConstructor
public class EmailService {
    private static final Logger logger = LoggerFactory.getLogger(EmailService.class);

    private final JavaMailSender mailSender;
    private final SpringTemplateEngine templateEngine;

    @Async
    public void sendEmail(
            String to,
            String userName,
            EmailTemplateName emailTemplate,
            String confirmationUrl,
            String activationCode,
            String subject
    ) throws MessagingException {
        String templateName;
        if(emailTemplate == null){
            templateName = "confirm-email";
        }
        else{
            templateName = emailTemplate.name();
        }
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(
                mimeMessage,
               MULTIPART_MODE_MIXED,
                UTF_8.name()
        );

        Map<String,Object> properties =  new HashMap<>();
        properties.put("userName",userName);
        properties.put("confirmationUrl", confirmationUrl);
        properties.put("activationCode",activationCode);


        Context context = new Context();
        context.setVariables(properties);

        helper.setFrom("gandamraviteja@gmail.com");
        helper.setTo(to);
        helper.setSubject(subject);

        String template = templateEngine.process(templateName,context);

        helper.setText(template,true);
        mailSender.send(mimeMessage);
//        try {
//            mailSender.send(mimeMessage);
//            logger.info("Email sent successfully to {}", to);
//        } catch (MessagingException e) {
//            logger.error("Failed to send email to {}", to, e);
//            throw e; // Rethrow the exception if needed
//        }
    }
}
