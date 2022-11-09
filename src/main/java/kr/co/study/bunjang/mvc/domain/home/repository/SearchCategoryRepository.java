package kr.co.study.bunjang.mvc.domain.home.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import kr.co.study.bunjang.mvc.domain.home.model.entity.Category;

@Repository
public interface SearchCategoryRepository {

    public List<Category> findWithOrderby();
}