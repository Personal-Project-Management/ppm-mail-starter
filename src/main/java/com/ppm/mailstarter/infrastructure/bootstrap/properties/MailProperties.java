package com.ppm.mailstarter.infrastructure.bootstrap.properties;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@ConfigurationProperties(prefix = "mail")
public class MailProperties {
    String host = "smtp.gmail.com";
    Integer port = 587;
    String username;
    String password;
    Boolean auth = true;
    Boolean startTls = true;
    String platformName;
}
