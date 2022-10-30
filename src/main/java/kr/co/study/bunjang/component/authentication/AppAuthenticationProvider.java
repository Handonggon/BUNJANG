package kr.co.study.bunjang.component.authentication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import kr.co.study.bunjang.component.utils.ObjUtils;
import kr.co.study.bunjang.mvc.dto.AppDetailsDto;
import kr.co.study.bunjang.mvc.dto.SsoHistoryDto;
import kr.co.study.bunjang.mvc.enums.Yn;
import kr.co.study.bunjang.mvc.service.AppService;
import kr.co.study.bunjang.mvc.service.SsoHistoryService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class AppAuthenticationProvider implements AuthenticationProvider {

	@Autowired
	private AppService appService;

	@Autowired
	SsoHistoryService ssoHistoryService;

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		String clientId = authentication.getName();
		AppDetailsDto appDetailsDto = (AppDetailsDto) appService.loadUserByUsername(clientId);
		ssoHistoryService.save(SsoHistoryDto.builder()
											.name(appDetailsDto.getSysNm())
											.successYn(Yn.Y)
											.origin(appDetailsDto.getSysNm())
											.destin("Azure")
											.build());
		return new AuthenticationToken(appDetailsDto, "");
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return AuthenticationToken.class.isAssignableFrom(authentication);
	}
}