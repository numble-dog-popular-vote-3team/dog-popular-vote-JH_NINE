package com.jh9.votesystem.dog.adapter.out.cache;

import com.jh9.votesystem.dog.domain.Dog;
import java.io.Serializable;
import java.time.LocalDateTime;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

@RedisHash("dog")
class DogRedisEntity implements Serializable {

    @Id
    private Long id;
    private String name;
    private String photoUrl;
    private String simpleDescription;
    private String detailDescription;
    private int thumbs;
    private LocalDateTime createdDate;

    private DogRedisEntity(Long id, String name, String photoUrl,
        String simpleDescription,
        String detailDescription, int thumbs, LocalDateTime createdDate) {
        this.id = id;
        this.name = name;
        this.photoUrl = photoUrl;
        this.simpleDescription = simpleDescription;
        this.detailDescription = detailDescription;
        this.thumbs = thumbs;
        this.createdDate = createdDate;
    }

    public static DogRedisEntity toEntity(Dog dog) {
        return new DogRedisEntity(dog.getId(), dog.getName(),
            dog.getPhotoUrl(),
            dog.getSimpleDescription(), dog.getDetailDescription(), dog.getThumbs(),
            dog.getCreatedDate());
    }

    public Dog toDomain() {
        return Dog.create(id, name, photoUrl, simpleDescription, detailDescription, thumbs,
            createdDate);
    }


}
