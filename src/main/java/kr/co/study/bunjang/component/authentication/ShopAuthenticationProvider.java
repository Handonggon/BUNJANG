package kr.co.study.bunjang.component.authentication;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

@Component
public class ShopAuthenticationProvider implements AuthenticationProvider {

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		String jwtToken = authentication.getName();

		// AD 인증 토큰 수정

		return new ShopAuthenticationToken(null, null);
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return ShopAuthenticationToken.class.isAssignableFrom(authentication);
	}
}
