package kr.co.study.bunjang.mvc.domain.home.repository.impl;

import static kr.co.study.bunjang.mvc.domain.home.model.entity.QCategory.category;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.querydsl.jpa.impl.JPAQueryFactory;

import kr.co.study.bunjang.mvc.domain.home.model.entity.Category;
import kr.co.study.bunjang.mvc.domain.home.repository.SearchCategoryRepository;

public class SearchCategoryRepositoryImpl implements SearchCategoryRepository {

    @Autowired
    private JPAQueryFactory queryFactory;

    @Override
    public List<Category> findWithOrderby() {
		return queryFactory.select(category)
                           .from(category)
                           .orderBy(category.cateOrder.asc())
                           .fetch();
    }
}