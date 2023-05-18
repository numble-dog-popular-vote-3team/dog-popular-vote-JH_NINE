package com.jh9.votesystem.dog.domain;

import com.jh9.votesystem.utils.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.util.Objects;

// 레코드 PK, 이름, 사진, 간단한 설명, 상세 설명과 현재 득표수 정보를 포함해야 한다.
@Entity
public class Dog extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String photoUrl;
    private String simpleDescription;
    private String detailDescription;
    private int thumbs;

    protected Dog() {
    }

    private Dog(Long id, String name, String photoUrl, String simpleDescription,
        String detailDescription, int thumbs) {
        if (isBlank(name, photoUrl, simpleDescription, detailDescription)) {
            throw new IllegalArgumentException("Data Must Not Blank");
        }
        this.id = id;
        this.name = name;
        this.photoUrl = photoUrl;
        this.simpleDescription = simpleDescription;
        this.detailDescription = detailDescription;
        this.thumbs = thumbs;
    }

    private static boolean isBlank(String name, String photoUrl, String simpleDescription,
        String detailDescription) {
        return name.isBlank() || photoUrl.isBlank() || simpleDescription.isBlank()
            || detailDescription.isBlank();
    }

    public static Dog createNewCandidate(String name, String photoUrl, String simpleDescription,
        String detailDescription) {
        return new Dog(null, name, photoUrl, simpleDescription, detailDescription, 0);
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
