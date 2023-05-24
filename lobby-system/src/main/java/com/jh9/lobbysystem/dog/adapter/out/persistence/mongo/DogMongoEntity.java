package com.jh9.lobbysystem.dog.adapter.out.persistence.mongo;

import com.jh9.lobbysystem.dog.domain.Dog;
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

    public Dog toDomain() {
        return new Dog(id, name, photoUrl, simpleDescription, detailDescription, thumbs,
            createdDate);
    }
}
