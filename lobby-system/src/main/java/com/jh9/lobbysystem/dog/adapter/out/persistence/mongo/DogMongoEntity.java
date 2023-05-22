package com.jh9.lobbysystem.dog.adapter.out.persistence.mongo;

import com.jh9.lobbysystem.dog.domain.Dog;
import java.time.LocalDateTime;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collation = "dog")
public class DogMongoEntity {

    @Id
    private Long id;
    private String name;
    private String photoUrl;
    private String simpleDescription;
    private String detailDescription;
    private int thumbs;
    private LocalDateTime createdDate;

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

    public Dog toDomain() {
        return Dog.create(id, name, photoUrl, simpleDescription, detailDescription, thumbs,
            createdDate);
    }
}
