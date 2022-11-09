package kr.co.study.bunjang.mvc.controller.v1.system;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import kr.co.study.bunjang.mvc.dto.ShopDetailsDto;
import kr.co.study.bunjang.mvc.service.ShopService;

@Controller
@RequestMapping("/signup")
public class ShopController {

    @Autowired
    private ShopService shopService;

    @PostMapping
    @ResponseBody
    public ResponseEntity<ShopDetailsDto> save(@RequestBody ShopDetailsDto shopDetailsDto) {
        return ResponseEntity.ok(shopService.save(shopDetailsDto));
    }
    
}
