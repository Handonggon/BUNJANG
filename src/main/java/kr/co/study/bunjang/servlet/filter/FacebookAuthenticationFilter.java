package kr.co.study.bunjang.servlet.filter;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.RequestMatcher;

import com.fasterxml.jackson.databind.ObjectMapper;

import kr.co.study.bunjang.component.authentication.facebook.FacebookAuthenticationToken;

public class FacebookAuthenticationFilter extends AbstractAuthenticationProcessingFilter {

    ObjectMapper mapper = new ObjectMapper();

    public FacebookAuthenticationFilter(RequestMatcher requiresAuthenticationRequestMatcher) {
        super(requiresAuthenticationRequestMatcher);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException, ServletException {
        if (requiresAuthentication(request, response)) {
            String credentials = obtainCredentials(request);
            return getAuthenticationManager().authenticate(new FacebookAuthenticationToken(credentials));
        }
        return null;
    }

    protected String obtainCredentials(HttpServletRequest request) {
        return request.getParameter("code");
    }
}
