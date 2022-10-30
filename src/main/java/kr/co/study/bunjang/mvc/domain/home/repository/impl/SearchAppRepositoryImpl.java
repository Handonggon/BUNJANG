package kr.co.study.bunjang.mvc.domain.home.repository.impl;

import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;

import kr.co.study.bunjang.mvc.domain.home.model.App;
import kr.co.study.bunjang.mvc.domain.home.model.QApp;
import kr.co.study.bunjang.mvc.domain.home.repository.SearchAppRepository;
import kr.co.study.bunjang.mvc.dto.search.AppSearchDto;

public class SearchAppRepositoryImpl extends QuerydslRepositorySupport implements SearchAppRepository {

    private final JPAQueryFactory jpaQueryFactory;

    public SearchAppRepositoryImpl(JPAQueryFactory jpaQueryFactory) {
        super(App.class);
        this.jpaQueryFactory = jpaQueryFactory;
    }

    private BooleanBuilder searchBuilder(AppSearchDto appSearchDto) {
        BooleanBuilder builder = new BooleanBuilder();

        return builder;
    }

    @Override
    public App searchForPageBy(final AppSearchDto appSearchDto) {
        return jpaQueryFactory
                .select(QApp.app)
                .from(QApp.app)
                .where(searchBuilder(appSearchDto))
                .fetchOne();
    }
}