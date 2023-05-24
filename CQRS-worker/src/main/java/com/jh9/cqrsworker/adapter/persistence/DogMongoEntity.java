package com.jh9.cqrsworker.adapter.persistence;

import com.jh9.cqrsworker.domain.Dog;
import java.time.LocalDateTime;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "dog_view")
public class DogMongoEntity {

    @Id
    private Long id;
    private String name;
    private String photoUrl;
    private String simpleDescription;
    private String detailDescription;
    private int thumbs;
    private LocalDateTime createdDate;

    protected DogMongoEntity() {
    }

    private DogMongoEntity(Long id, String name, String photoUrl, String simpleDescription,
        String detailDescription, int thumbs, LocalDateTime createdDate) {
        this.id = id;
        this.name = name;
        this.photoUrl = photoUrl;
        this.simpleDescription = simpleDescription;
        this.detailDescription = detailDescription;
        this.thumbs = thumbs;
        this.createdDate = createdDate;
    }

    public static DogMongoEntity toEntity(Dog dog) {
        return new DogMongoEntity(dog.id(), dog.name(), dog.photoUrl(), dog.simpleDescription(),
            dog.detailDescription(), dog.thumbs(), dog.createdDate());
    }

    public Dog toDomain() {
        return new Dog(id, name, photoUrl, simpleDescription, detailDescription, thumbs,
            createdDate);
    }
}
