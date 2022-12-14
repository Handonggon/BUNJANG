package kr.co.study.bunjang.servlet.filter;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.RequestMatcher;

import com.fasterxml.jackson.databind.ObjectMapper;

import kr.co.study.bunjang.component.authentication.kakao.KakaoAuthenticationToken;
import kr.co.study.bunjang.component.authentication.mobile.MobileAuthenticationToken;
import kr.co.study.bunjang.component.utility.ObjUtils;

public class KakaoAuthenticationFilter extends AbstractAuthenticationProcessingFilter {

    ObjectMapper mapper = new ObjectMapper();

    public KakaoAuthenticationFilter(RequestMatcher requiresAuthenticationRequestMatcher) {
        super(requiresAuthenticationRequestMatcher);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException, ServletException {
        if (requiresAuthentication(request, response)) {
            String credentials = obtainCredentials(request);
            return getAuthenticationManager().authenticate(new KakaoAuthenticationToken(credentials));
        }
        return null;
    }

	protected String obtainCredentials(HttpServletRequest request) {
		return request.getParameter("code");
	}
}
