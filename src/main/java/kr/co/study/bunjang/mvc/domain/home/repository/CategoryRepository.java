package kr.co.study.bunjang.mvc.domain.home.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import kr.co.study.bunjang.mvc.domain.home.model.entity.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, String> {
    
}