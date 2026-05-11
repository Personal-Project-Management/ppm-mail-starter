package com.ppm.mailstarter.domain.entity;

import lombok.Builder;

@Builder
public record MailContent(
        String subject,
        String to,
        String cc,
        String content,
        Boolean isHtml
) {
}
