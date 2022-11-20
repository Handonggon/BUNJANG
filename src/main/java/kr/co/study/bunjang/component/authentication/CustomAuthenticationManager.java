package kr.co.study.bunjang.component.authentication;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

import kr.co.study.bunjang.component.utility.ObjUtils;

public class CustomAuthenticationManager implements AuthenticationManager {

	private final List<AuthenticationProvider> authenticationProviders = new ArrayList<AuthenticationProvider>();

	public void setAuthenticationProvider(AuthenticationProvider authenticationProvider) {
		this.authenticationProviders.add(authenticationProvider);
	}

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		if (!ObjUtils.isEmpty(authentication)) {
			for(AuthenticationProvider authenticationProvider : authenticationProviders) {
				if (authenticationProvider.supports(authentication.getClass())) {
					return authenticationProvider.authenticate(authentication);
				}
			}
		}
		return null;
	}
}