package kr.co.study.bunjang.component.authentication;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

import kr.co.study.bunjang.component.utility.ObjUtils;

public class CustomAuthenticationManager implements AuthenticationManager {

	private AuthenticationProvider authenticationProvider;

	public CustomAuthenticationManager(AuthenticationProvider authenticationProvider) {
		this.authenticationProvider = authenticationProvider;
	}

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		if (!ObjUtils.isEmpty(authentication)) {
			if (authenticationProvider.supports(authentication.getClass())) {
				return authenticationProvider.authenticate(authentication);
			}
		}
		return null;
	}
}