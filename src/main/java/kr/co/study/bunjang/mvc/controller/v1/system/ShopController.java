package kr.co.study.bunjang.mvc.controller.v1.system;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import kr.co.study.bunjang.mvc.dto.ShopDto;
import kr.co.study.bunjang.mvc.service.ShopService;


@Api(tags = {"Shop Controller"}, description = "상점")
@RestController
@RequestMapping("/v1/shop")
public class ShopController {

    @Autowired
    private ShopService shopService;

    @ApiOperation(value = "회원 가입", notes = "<big>회원가입</big>을 한다.")
    @PostMapping("/signup")
    public ResponseEntity<ShopDto> signUp(@RequestBody @ApiParam(value = "회원 정보", required = true) ShopDto shopDto) {
        return ResponseEntity.ok(shopService.signUp(shopDto));
    }

    @ApiOperation(value = "회원 정보 수정", notes = "<big>회원 정보 수정</big>을 한다.")
    @PutMapping
    public ResponseEntity<ShopDto> update(@RequestBody @ApiParam(value = "회원 정보 수정", required = true) ShopDto shopDto) {
        return ResponseEntity.ok(shopService.update(shopDto));
    }
}
