package com.jh9.lobbysystem.dog.domain;

import java.time.LocalDateTime;
import java.util.Objects;

// 레코드 PK, 이름, 사진, 간단한 설명, 상세 설명과 현재 득표수 정보를 포함해야 한다.
public class Dog {

    private Long id;
    private String name;
    private String photoUrl;
    private String simpleDescription;
    private String detailDescription;
    private int thumbs;
    private LocalDateTime createdDate;

    private Dog(Long id, String name, String photoUrl, String simpleDescription,
        String detailDescription, int thumbs, LocalDateTime createdDate) {
        if (isBlank(name, photoUrl, simpleDescription, detailDescription)) {
            throw new IllegalArgumentException("Data Must Not Blank");
        }
        this.id = id;
        this.name = name;
        this.photoUrl = photoUrl;
        this.simpleDescription = simpleDescription;
        this.detailDescription = detailDescription;
        this.thumbs = thumbs;
        this.createdDate = createdDate;
    }

    private static boolean isBlank(String name, String photoUrl, String simpleDescription,
        String detailDescription) {
        return name.isBlank() || photoUrl.isBlank() || simpleDescription.isBlank()
            || detailDescription.isBlank();
    }

    public static Dog createNew(String name, String photoUrl, String simpleDescription,
        String detailDescription) {
        return new Dog(null, name, photoUrl, simpleDescription, detailDescription, 0, null);
    }

    public static Dog create(Long id, String name, String photoUrl, String simpleDescription,
        String detailDescription, int thumbs, LocalDateTime createdDate) {
        return new Dog(id, name, photoUrl, simpleDescription, detailDescription, thumbs, createdDate);
    }

    public void thumbsUp() {
        thumbs++;
    }

    public void thumbsDown() {
        thumbs--;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public String getSimpleDescription() {
        return simpleDescription;
    }

    public String getDetailDescription() {
        return detailDescription;
    }

    public int getThumbs() {
        return thumbs;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Dog dog)) {
            return false;
        }
        return id.equals(dog.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
