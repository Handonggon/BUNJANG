package kr.co.study.bunjang.mvc.controller.v1.system;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import kr.co.study.bunjang.mvc.dto.ShopDetailsDto;
import kr.co.study.bunjang.mvc.service.ShopService;


@RestController
@RequestMapping("/v1/shop")
public class ShopController {

    @Autowired
    private ShopService shopService;

    @ApiOperation(value = "회원 가입", notes = "<big>회원가입</big>을 한다.")
    @PostMapping
    public ResponseEntity<ShopDetailsDto> save(@RequestBody @ApiParam(value = "회원 정보", required = true) ShopDetailsDto shopDetailsDto) {
        return ResponseEntity.ok(shopService.save(shopDetailsDto));
    }

    @GetMapping(value="/kakao/callback")
    public @ResponseBody String kakaoCallback(String code) {

        RestTemplate rt = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");
        
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("grant_type", "authorization_code");
        params.add("client_id", "e1d46938686c263ed90f533d37bed141");
		params.add("redirect_uri", "http://localhost:20000/signup/kakao/callback");
		params.add("code", code);

        HttpEntity<MultiValueMap<String, String>> kakaoTokenRequest = new HttpEntity<>(params, headers);

        ResponseEntity<String> response = rt.exchange(
			"https://kauth.kakao.com/oauth/token",
			HttpMethod.POST,
			kakaoTokenRequest,
			String.class
		);

        return "카카오 토큰에 대한 요청 응답 : " + response.getBody(); 
    }
    
}
