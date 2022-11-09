package kr.co.study.bunjang.mvc.domain.home.repository.impl;

import static kr.co.study.bunjang.mvc.domain.home.model.entity.QCategory.category;

import java.util.List;

import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import com.querydsl.jpa.impl.JPAQueryFactory;

import kr.co.study.bunjang.mvc.domain.home.model.entity.Category;
import kr.co.study.bunjang.mvc.domain.home.repository.SearchCategoryRepository;

public class SearchCategoryRepositoryImpl extends QuerydslRepositorySupport implements SearchCategoryRepository {

    private final JPAQueryFactory jpaQueryFactory;

    public SearchCategoryRepositoryImpl(JPAQueryFactory jpaQueryFactory) {
        super(Category.class);
        this.jpaQueryFactory = jpaQueryFactory;
    }

    @Override
    public List<Category> findWithOrderby() {
		return jpaQueryFactory.select(category)
                              .from(category)
                              .orderBy(category.cateOrder.asc())
                              .fetch();
    }
}