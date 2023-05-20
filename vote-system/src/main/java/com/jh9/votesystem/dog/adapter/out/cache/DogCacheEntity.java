package com.jh9.votesystem.dog.adapter.out.cache;

import java.time.LocalDateTime;
import java.util.Objects;

record DogCacheEntity(
    Long id,
    String name,
    String photoUrl,
    String simpleDescription,
    String detailDescription,
    int thumbs,
    LocalDateTime createdDate
) {

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DogCacheEntity dog)) {
            return false;
        }
        return id.equals(dog.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
