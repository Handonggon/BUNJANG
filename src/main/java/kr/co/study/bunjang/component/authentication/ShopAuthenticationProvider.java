package kr.co.study.bunjang.component.authentication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import kr.co.study.bunjang.component.utility.ObjUtils;
import kr.co.study.bunjang.mvc.service.ShopService;

@Component
public class ShopAuthenticationProvider implements AuthenticationProvider {

	@Autowired
    private ShopService shopService;

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		String phoneNumber = authentication.getName();

		// TODO 탈퇴 후 경과일 확인, 로그인 횟수 확인
		// if (false) {
		// 	throw new InsufficientAuthenticationException(MessageUtils.getMessage(""));
		// }

		UserDetails userDetails = shopService.loadUserByUsername(phoneNumber);

		String credentials = ObjUtils.objToString(authentication.getCredentials());
		if (!userDetails.getPassword().equals(credentials)) {
			throw new BadCredentialsException("인증번호 오류");
			// throw new BadCredentialsException(MessageUtils.getMessage(""));
		}

		if (!userDetails.isEnabled()) {
			throw new BadCredentialsException("인증 되지 않은 업체");
			// throw new DisabledException(MessageUtils.getMessage(""));
		}

		return new ShopAuthenticationToken(userDetails, credentials);
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return ShopAuthenticationToken.class.isAssignableFrom(authentication);
	}
}
