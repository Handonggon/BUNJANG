package kr.co.study.bunjang.mvc.controller.v1.system;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import kr.co.study.bunjang.mvc.domain.home.model.entity.Category;
import kr.co.study.bunjang.mvc.service.CategoryService;

@Api(tags = {"Category Controller"}, description = "카테고리")
@RestController
@RequestMapping("/v1/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @ApiOperation(value = "카테고리 조회", notes = "<big>카테고리 목록</big>을 조회한다.")
    @GetMapping
    public ResponseEntity<List<Category>> get() {
        return ResponseEntity.ok(categoryService.get());
    }
    
}
