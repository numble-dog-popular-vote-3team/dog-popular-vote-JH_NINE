package com.jh9.lobbysystem.dog.domain;

import java.time.LocalDateTime;

public record Dog(
    Long id,
    String name,
    String photoUrl,
    String simpleDescription,
    String detailDescription,
    int thumbs,
    LocalDateTime createDate
)  {

}
