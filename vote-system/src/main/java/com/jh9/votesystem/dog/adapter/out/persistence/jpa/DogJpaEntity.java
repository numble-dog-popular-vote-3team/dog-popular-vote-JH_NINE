package com.jh9.votesystem.dog.adapter.out.persistence.jpa;

import com.jh9.votesystem.dog.domain.Dog;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import java.util.Objects;

// 레코드 PK, 이름, 사진, 간단한 설명, 상세 설명과 현재 득표수 정보를 포함해야 한다.
@Table(name = "dog")
@Entity
class DogJpaEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String photoUrl;
    private String simpleDescription;
    private String detailDescription;
    private int thumbs;

    protected DogJpaEntity() {
    }

    private DogJpaEntity(Long id, String name, String photoUrl, String simpleDescription,
        String detailDescription, int thumbs) {
        this.id = id;
        this.name = name;
        this.photoUrl = photoUrl;
        this.simpleDescription = simpleDescription;
        this.detailDescription = detailDescription;
        this.thumbs = thumbs;
    }

    public static DogJpaEntity toEntity(Dog dog) {
        return new DogJpaEntity(dog.getId(), dog.getName(), dog.getPhotoUrl(),
            dog.getSimpleDescription(), dog.getDetailDescription(), dog.getThumbs());
    }

    public Dog toDomain() {
        return Dog.create(id, name, photoUrl, simpleDescription,
            detailDescription, thumbs, getCreatedDate());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DogJpaEntity dog)) {
            return false;
        }
        return id.equals(dog.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
