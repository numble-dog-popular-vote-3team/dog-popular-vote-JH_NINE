package com.jh9.cqrsworker.domain;

import java.time.LocalDateTime;
import org.springframework.data.mongodb.core.mapping.Document;

public record Dog(
    Long id,
    String name,
    String photoUrl,
    String simpleDescription,
    String detailDescription,
    int thumbs,
    LocalDateTime createdDate
)  {

}
