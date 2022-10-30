package kr.co.study.bunjang.mvc.domain.home.repository.impl;

import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import com.querydsl.jpa.impl.JPAQueryFactory;

import kr.co.study.bunjang.mvc.domain.home.model.User;
import kr.co.study.bunjang.mvc.domain.home.repository.SearchUserRepository;

public class SearchUserRepositoryImpl extends QuerydslRepositorySupport implements SearchUserRepository {

    private final JPAQueryFactory jpaQueryFactory;

    public SearchUserRepositoryImpl(JPAQueryFactory jpaQueryFactory) {
        super(User.class);
        this.jpaQueryFactory = jpaQueryFactory;
    }
}