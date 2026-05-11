package com.ppm.mailstarter.application.usecase;

import com.ppm.mailstarter.domain.entity.MailContent;
import com.ppm.mailstarter.domain.service.MailService;
import com.ppm.mailstarter.infrastructure.bootstrap.properties.MailProperties;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.UnsupportedEncodingException;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class MailUseCase implements MailService {
    JavaMailSender sender;
    MailProperties properties;

    @Override
    public void sendMail(MailContent mailContent) {
        try {
            MimeMessage message = sender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
            helper.setSubject(mailContent.subject());
            helper.setFrom(new InternetAddress(properties.getUsername(), properties.getPlatformName()));
            helper.setTo(mailContent.to());
            if(StringUtils.hasText(mailContent.cc())) {
                helper.setCc(mailContent.cc());
            }
            helper.setText(mailContent.content(), mailContent.isHtml());
            sender.send(message);
        } catch (MessagingException | UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }
}
