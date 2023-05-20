package com.jh9.votesystem.dog.adapter.out.persistence.jpa;

import java.util.List;

interface DogJpaRepositoryCustom {

    List<DogJpaEntity> search(Long lastId, int pageSize);
}
