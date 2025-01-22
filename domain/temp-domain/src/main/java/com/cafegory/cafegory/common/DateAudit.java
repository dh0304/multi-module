package com.cafegory.cafegory.common;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class DateAudit {

    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;
}
