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

import kr.co.study.bunjang.component.authentication.mobile.MobileAuthenticationToken;
import kr.co.study.bunjang.component.utility.ObjUtils;

public class MobileAuthenticationFilter extends AbstractAuthenticationProcessingFilter {

    private ObjectMapper mapper = new ObjectMapper();

    public MobileAuthenticationFilter(RequestMatcher requiresAuthenticationRequestMatcher) {
        super(requiresAuthenticationRequestMatcher);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException, ServletException {
        if (requiresAuthentication(request, response)) {
            if (!request.getMethod().equals("POST")) {
                throw new AuthenticationServiceException("Authentication method not supported: " + request.getMethod());
            }
            Map<String, Object> body = mapper.readValue(request.getInputStream(), HashMap.class);
            Long shopNo = ObjUtils.objToLong(body.get("shopNo"));
            String credentials = ObjUtils.objToString(body.get("credentials"));
            return getAuthenticationManager().authenticate(new MobileAuthenticationToken(shopNo, credentials));
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
