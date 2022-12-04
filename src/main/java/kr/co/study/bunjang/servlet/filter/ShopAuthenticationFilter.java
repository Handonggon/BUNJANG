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
            Long shopNo = ObjUtils.objToLong(ObjUtils.nvl(obtainShopNo(request).trim(), 0));
            String credentials = ObjUtils.objToString(ObjUtils.nvl(obtainCredentials(request).trim(), ""));

            Authentication authResult = getAuthenticationManager().authenticate(new ShopAuthenticationToken(shopNo, credentials));
            SecurityContextHolder.getContext().setAuthentication(authResult);
            return authResult;
        }
        return null;
    }

	protected String obtainShopNo(HttpServletRequest request) {
		return request.getParameter("shopNo");
	}

	protected String obtainCredentials(HttpServletRequest request) {
		return request.getParameter("credentials");
	}
}
