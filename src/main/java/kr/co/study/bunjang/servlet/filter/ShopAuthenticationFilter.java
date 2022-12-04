package kr.co.study.bunjang.servlet.filter;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.RequestMatcher;

import kr.co.study.bunjang.component.authentication.ShopAuthenticationToken;
import kr.co.study.bunjang.component.utility.ObjUtils;

public class ShopAuthenticationFilter extends AbstractAuthenticationProcessingFilter {

    public ShopAuthenticationFilter(RequestMatcher requiresAuthenticationRequestMatcher) {
        super(requiresAuthenticationRequestMatcher);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException, ServletException {
        if (requiresAuthentication(request, response)) {
            String phoneNumber = ObjUtils.objToString(ObjUtils.nvl(obtainPhoneNumber(request).trim(), ""));
            String credentials = ObjUtils.objToString(ObjUtils.nvl(obtainCredentials(request).trim(), ""));

            Authentication authResult = getAuthenticationManager().authenticate(new ShopAuthenticationToken(phoneNumber, credentials));
            SecurityContextHolder.getContext().setAuthentication(authResult);
            return authResult;
        }
        return null;
    }

	protected String obtainPhoneNumber(HttpServletRequest request) {
		return request.getParameter("phoneNumber");
	}

	protected String obtainCredentials(HttpServletRequest request) {
		return request.getParameter("credentials");
	}
}
