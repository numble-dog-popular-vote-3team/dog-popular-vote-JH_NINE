package com.jh9.votesystem.dog.adapter.out.persistence.jpa;

import com.jh9.votesystem.dog.application.port.in.DogSearchCondition;
import java.util.List;

interface DogJpaRepositoryCustom {

    List<Long> search(DogSearchCondition condition);
}
