package com.jh9.votesystem.dog.adapter.out.persistence.jpa;

import static com.jh9.votesystem.dog.adapter.out.persistence.jpa.QDogJpaEntity.dogJpaEntity;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;

class DogJpaRepositoryImpl implements DogJpaRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    public DogJpaRepositoryImpl(JPAQueryFactory queryFactory) {
        this.queryFactory = queryFactory;
    }

    @Override
    public List<DogJpaEntity> search(Long lastId, int pageSize) {
        return queryFactory
            .select(dogJpaEntity)
            .from(dogJpaEntity)
            .where(
                ltDogId(lastId)
            )
            .orderBy(dogJpaEntity.id.desc())
            .limit(pageSize)
            .fetch();
    }

    private BooleanExpression ltDogId(Long dogId) {
        if (dogId == null) {
            return null;
        }

        return dogJpaEntity.id.lt(dogId);
    }
}
