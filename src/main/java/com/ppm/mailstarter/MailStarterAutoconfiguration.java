package com.ppm.mailstarter;

import com.ppm.mailstarter.application.usecase.MailUseCase;
import com.ppm.mailstarter.domain.service.MailService;
import com.ppm.mailstarter.infrastructure.bootstrap.properties.MailProperties;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

@AutoConfiguration
@EnableConfigurationProperties(MailProperties.class)
public class MailStarterAutoconfiguration {

    @Bean
    public JavaMailSender javaMailSender(MailProperties mailProperties) {
        JavaMailSenderImpl sender = new JavaMailSenderImpl();
        sender.setHost(mailProperties.getHost());
        sender.setPort(mailProperties.getPort());
        sender.setUsername(mailProperties.getUsername());
        sender.setPassword(mailProperties.getPassword());
        Properties properties = new Properties();
        properties.put("mail.smtp.auth", mailProperties.getAuth());
        properties.put("mail.smtp.starttls.enable", mailProperties.getStartTls());
        sender.setJavaMailProperties(properties);

        return sender;
    }

    @Bean
    public MailService mailService(JavaMailSender sender, MailProperties mailProperties) {
        return new MailUseCase(sender, mailProperties);
    }
}
