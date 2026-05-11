package com.ppm.mailstarter.domain.service;

import com.ppm.mailstarter.domain.entity.MailContent;

public interface MailService {
    void sendMail(MailContent mailContent);
}
