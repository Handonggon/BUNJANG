package kr.co.study.bunjang.component.authentication.Facebook;

import kr.co.study.bunjang.component.authentication.kakao.KakaoAuthenticationToken;
import kr.co.study.bunjang.component.authentication.kakao.KakaoProfile;
import kr.co.study.bunjang.component.util.CommonMap;
import kr.co.study.bunjang.component.utility.ObjUtils;
import kr.co.study.bunjang.mvc.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;

@Component
public class FacebookAuthenticationProvider implements AuthenticationProvider {

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

		// String credentials = ObjUtils.objToString(authentication.getCredentials());
		// if (!userDetails.getPassword().equals(credentials)) {
		// 	throw new BadCredentialsException("인증번호 오류");
		// }

		// if (!userDetails.isEnabled()) {
		// 	throw new DisabledException("인증 되지 않은 업체");
		// }

		return new KakaoAuthenticationToken(null, credentials);
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return FacebookAuthenticationToken.class.isAssignableFrom(authentication);
	}
}
