package kr.co.study.bunjang.mvc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.study.bunjang.mvc.domain.home.model.entity.Category;
import kr.co.study.bunjang.mvc.domain.home.repository.CategoryRepository;

@Service
public class CategoryService {

    @Autowired
    CategoryRepository categoryRepository;

    public List<Category> get() {
        return categoryRepository.findWithOrderby();
    }
}
