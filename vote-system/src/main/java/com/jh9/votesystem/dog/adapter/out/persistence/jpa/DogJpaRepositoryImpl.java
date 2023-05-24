package com.jh9.votesystem.dog.adapter.out.persistence.jpa;

import static com.jh9.votesystem.dog.adapter.out.persistence.jpa.QDogJpaEntity.dogJpaEntity;

import com.jh9.votesystem.dog.application.port.in.DogSearchCondition;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;

class DogJpaRepositoryImpl implements DogJpaRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    public DogJpaRepositoryImpl(JPAQueryFactory queryFactory) {
        this.queryFactory = queryFactory;
    }

    @Override
    public List<Long> search(DogSearchCondition condition) {
        return queryFactory
            .select(dogJpaEntity.id)
            .from(dogJpaEntity)
            .where(
                useNonOffset(condition.sortKey(), condition.lastValue()),
                eqName(condition.name()),
                gtThumbs(condition.thumbs())
            )
            .orderBy(orderByKey(condition.sortKey(), condition.doAscending()))
            .limit(condition.pageSize())
            .fetch();
    }

    private BooleanExpression useNonOffset(String keyName, Object lastValue) {
        return switch (keyName) {
            case "name" -> ltName(lastValue);
            case "thumbs" -> ltThumbs(lastValue);
            default -> ltDogId(lastValue);
        };
    }

    private BooleanExpression ltDogId(Object dogId) {
        return dogId == null ? null : dogJpaEntity.id.lt(Long.parseLong((String) dogId));
    }

    private BooleanExpression ltName(Object name) {
        return name == null ? null : dogJpaEntity.name.lt((String) name);
    }

    private BooleanExpression ltThumbs(Object thumbs) {
        return thumbs == null ? null : dogJpaEntity.id.lt(Integer.parseInt((String) thumbs));
    }

    private BooleanExpression eqName(String name) {
        return name == null || name.isBlank() ? null : dogJpaEntity.name.eq(name);
    }

    private BooleanExpression gtThumbs(Object thumbs) {
        return thumbs == null ? null : dogJpaEntity.thumbs.gt(Integer.parseInt((String) thumbs));
    }

    private OrderSpecifier<?> orderByKey(String keyName, boolean doAscending) {
        return switch (keyName) {
            case "name" -> sortByName(doAscending);
            case "thumbs" -> sortByThumbs(doAscending);
            default -> dogJpaEntity.id.desc();
        };
    }

    private OrderSpecifier<String> sortByName(boolean doAscending) {
        return doAscending ? dogJpaEntity.name.asc() : dogJpaEntity.name.desc();
    }

    private OrderSpecifier<Integer> sortByThumbs(boolean doAscending) {
        return doAscending ? dogJpaEntity.thumbs.asc() : dogJpaEntity.thumbs.desc();
    }

}
