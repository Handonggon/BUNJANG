package kr.co.study.bunjang.component.authentication.kakao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import kr.co.study.bunjang.component.utility.ObjUtils;
import kr.co.study.bunjang.mvc.service.ShopService;

@Component
public class KakaoAuthenticationProvider implements AuthenticationProvider {

	@Autowired
    private ShopService shopService;

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		String shopNo = authentication.getName();

		UserDetails userDetails = shopService.loadUserByUsername(shopNo);

		String credentials = ObjUtils.objToString(authentication.getCredentials());
		if (!userDetails.getPassword().equals(credentials)) {
			throw new BadCredentialsException("인증번호 오류");
		}

		if (!userDetails.isEnabled()) {
			throw new DisabledException("인증 되지 않은 업체");
		}

		return new KakaoAuthenticationToken(userDetails, credentials);
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return KakaoAuthenticationToken.class.isAssignableFrom(authentication);
	}
}
