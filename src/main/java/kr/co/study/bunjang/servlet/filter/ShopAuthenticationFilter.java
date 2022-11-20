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

public class ShopAuthenticationFilter extends AbstractAuthenticationProcessingFilter {

    public ShopAuthenticationFilter(RequestMatcher requiresAuthenticationRequestMatcher) {
        super(requiresAuthenticationRequestMatcher);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException, ServletException {
        if (requiresAuthentication(request, response)) {
            //String jwtToken = ObjUtils.objToString(ObjUtils.nvl(HttpUtils.getAuthorization("Bearer"), request.getParameter("JWTRequest")));
            Authentication authResult = null; //getAuthenticationManager().authenticate(new UserAuthenticationToken(jwtToken));
            SecurityContextHolder.getContext().setAuthentication(authResult);
            return authResult;
        }
        return null;
    }
}
