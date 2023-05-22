package com.jh9.votesystem.dog.adapter.out.persistence.jpa;

import static com.jh9.votesystem.dog.adapter.out.persistence.jpa.QDogJpaEntity.dogJpaEntity;

import com.jh9.votesystem.dog.application.port.in.DogSearchCondition;
import com.querydsl.core.types.Expression;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;

class DogJpaRepositoryImpl implements DogJpaRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    public DogJpaRepositoryImpl(JPAQueryFactory queryFactory) {
        this.queryFactory = queryFactory;
    }

    @Override
    public List<DogJpaEntity> search(DogSearchCondition condition) {
        return queryFactory
            .select(dogJpaEntity)
            .from(dogJpaEntity)
            .where(
                ltDogId(condition.getLastId())
            )
            .orderBy(dogJpaEntity.id.desc())
            .limit(condition.getLastId())
            .fetch();
    }

    private BooleanExpression ltDogId(Long dogId) {
        if (dogId == null) {
            return null;
        }

        return dogJpaEntity.id.lt(dogId);
    }
}
