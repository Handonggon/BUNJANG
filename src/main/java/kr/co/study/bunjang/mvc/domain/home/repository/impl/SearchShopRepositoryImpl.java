package kr.co.study.bunjang.mvc.domain.home.repository.impl;

import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import com.querydsl.jpa.impl.JPAQueryFactory;

import kr.co.study.bunjang.mvc.domain.home.model.entity.Shop;
import kr.co.study.bunjang.mvc.domain.home.repository.SearchShopRepository;

public class SearchShopRepositoryImpl extends QuerydslRepositorySupport implements SearchShopRepository {

    private final JPAQueryFactory jpaQueryFactory;

    public SearchShopRepositoryImpl(JPAQueryFactory jpaQueryFactory) {
        super(Shop.class);
        this.jpaQueryFactory = jpaQueryFactory;
    }
}