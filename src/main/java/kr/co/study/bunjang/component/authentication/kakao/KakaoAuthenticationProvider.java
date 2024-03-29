package kr.co.study.bunjang.component.authentication.kakao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;

import kr.co.study.bunjang.component.util.CommonMap;
import kr.co.study.bunjang.component.utility.ObjUtils;
import kr.co.study.bunjang.mvc.dto.ShopDto;
import kr.co.study.bunjang.mvc.service.ShopService;

@Component
public class KakaoAuthenticationProvider implements AuthenticationProvider {

	@Autowired
    private ShopService shopService;

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		// String shopNo = authentication.getName();

		// UserDetails userDetails = shopService.loadUserByUsername(shopNo);

		String credentials = ObjUtils.objToString(authentication.getCredentials());

		WebClient webClient = WebClient.builder()
			.baseUrl("https://kauth.kakao.com")
			.exchangeStrategies(ExchangeStrategies.builder()
                .codecs(configurer -> configurer.defaultCodecs().maxInMemorySize(-1))
				.build())
            .build();

			// 아래 respones.putAll(webClient.post()에서 사용됨
		// MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		// 	params.add("grant_type", "authorization_code");
		// 	params.add("client_id", "e1d46938686c263ed90f533d37bed141");
		// 	params.add("redirect_uri", "http://localhost:20000/signup/kakao/callback");
		// 	params.add("code", ObjUtils.objToString(authentication.getCredentials()));

		CommonMap respones = new CommonMap();


		respones.putAll(webClient.post()
                .uri(uriBuilder->uriBuilder.path("/oauth/token").build())
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
				.body(BodyInserters.fromFormData("grant_type", "authorization_code")
                            .with("client_id", "e1d46938686c263ed90f533d37bed141")
							.with("redirect_uri", "http://localhost:20000/v1/login/kakao")
							.with("code", credentials)
         		)
                .exchangeToMono(response->{
                    return response.bodyToMono(CommonMap.class);
                }).block());

		KakaoProfile respones2 = webClient.post()
				.uri(uriBuilder->uriBuilder.path("/v2/user/me").build())
				.header(HttpHeaders.AUTHORIZATION, "Bearer " + respones.getString("access_token"))
				.contentType(MediaType.APPLICATION_FORM_URLENCODED)
				
                .exchangeToMono(response->{
                    return response.bodyToMono(KakaoProfile.class);
                }).block();

		// 추가된 부분
		UserDetails userDetails = null;

		try {
			userDetails = shopService.loadUserByEmail(respones2.getKakao_account().getEmail());
		} catch (Exception e) {
			ShopDto kakaoUser = ShopDto.builder()
					.email(respones2.getKakao_account().getEmail())
					.userNm(respones2.getKakao_account().getProfile().getNickname())
					.phoneNumber(respones2.getKakao_account().getPhone_number())
					.build();

			shopService.signUp(kakaoUser);
			userDetails = shopService.loadUserByEmail(respones2.getKakao_account().getEmail());
		}

		return new KakaoAuthenticationToken(userDetails, credentials);
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return KakaoAuthenticationToken.class.isAssignableFrom(authentication);
	}
}
