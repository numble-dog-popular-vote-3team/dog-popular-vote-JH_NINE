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
                ltDogId(condition.getLastId()),
                eqName(condition.getName()),
                gtThumbs(condition.getThumbs())
            )
            .orderBy(orderBy(condition.getSortKey(), condition.doAscending()))
            .limit(condition.getLastId())
            .fetch();
    }


    private BooleanExpression ltDogId(Long dogId) {
        return dogId == null ? null : dogJpaEntity.id.lt(dogId);
    }

    private BooleanExpression eqName(String name) {
        return name == null || name.isBlank() ? null : dogJpaEntity.name.eq(name);
    }

    private BooleanExpression gtThumbs(int thumbs) {
        return thumbs == 0 ? null : dogJpaEntity.thumbs.gt(thumbs);
    }

    private OrderSpecifier<?> orderBy(String keyName, boolean doAscending) {
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
