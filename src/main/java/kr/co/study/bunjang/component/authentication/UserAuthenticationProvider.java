package kr.co.study.bunjang.component.authentication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import kr.co.study.bunjang.mvc.dto.SsoHistoryDto;
import kr.co.study.bunjang.mvc.dto.UserDetailsDto;
import kr.co.study.bunjang.mvc.enums.Yn;
import kr.co.study.bunjang.mvc.service.SsoHistoryService;
import kr.co.study.bunjang.mvc.service.UserService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class UserAuthenticationProvider implements AuthenticationProvider {

	@Autowired
	UserService userService;

	@Autowired
	SsoHistoryService ssoHistoryService;

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		String userId = authentication.getName();
		UserDetailsDto userDetailsDto = (UserDetailsDto) userService.loadUserByUsername(userId);
		ssoHistoryService.save(SsoHistoryDto.builder()
											.name(userDetailsDto.getUsername()) //TODO
											.successYn(Yn.Y)
											.origin("Azure")
											.destin(userDetailsDto.getUsername())
											.build());
		return new AuthenticationToken(userDetailsDto, "");
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return AuthenticationToken.class.isAssignableFrom(authentication);
	}
}