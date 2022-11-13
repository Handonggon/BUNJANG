package kr.co.study.bunjang.mvc.controller.v1.system;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.co.study.bunjang.mvc.domain.home.model.entity.Category;
import kr.co.study.bunjang.mvc.service.CategoryService;

@Controller
@RequestMapping("/v1/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping
    @ResponseBody
    public ResponseEntity<List<Category>> get() {
        return ResponseEntity.ok(categoryService.get());
    }
    
}
