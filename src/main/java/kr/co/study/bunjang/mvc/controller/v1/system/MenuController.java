package kr.co.study.bunjang.mvc.controller.v1.system;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kr.co.study.bunjang.mvc.dto.MenuDto;
import kr.co.study.bunjang.mvc.service.MenuService;
import kr.co.study.bunjang.mvc.vo.Yn;

@RestController
@RequestMapping("/v1/system/menu")
public class MenuController {
    
    @Autowired
	private MenuService menuService;

    @GetMapping
	public ResponseEntity<List<MenuDto>> find(@RequestParam(required=false) Long menuNo) {
		return ResponseEntity.ok(menuService.findAll());
	}

    @PutMapping
	public ResponseEntity<MenuDto> update(@RequestBody(required = false) MenuDto userDto) {
		return ResponseEntity.ok(menuService.update(1L, "test"));
	}

	@PostMapping
	public ResponseEntity<MenuDto> save(@RequestBody(required = false) MenuDto userDto) {
		return ResponseEntity.ok(menuService.save(MenuDto.builder()
                                                         .menuNm("테스트")
                                                         .menuLevel(1)
                                                         .menuOrder(0)
                                                         .useYn(Yn.N)
                                                         .build()));
    }
}