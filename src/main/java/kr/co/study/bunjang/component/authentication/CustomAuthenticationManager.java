package kr.co.study.bunjang.component.authentication;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CustomAuthenticationManager implements AuthenticationManager {

	private AuthenticationProvider authenticationProvider;

	public CustomAuthenticationManager(AuthenticationProvider authenticationProvider) {
		log.debug("CustomAuthenticationManager - CustomAuthenticationManager: init");
		this.authenticationProvider = authenticationProvider;
	}

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		return authenticationProvider.authenticate(authentication);
	}
}