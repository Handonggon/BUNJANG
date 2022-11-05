package kr.co.study.bunjang.servlet.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import kr.co.study.bunjang.component.utility.HttpUtils;
import kr.co.study.bunjang.component.utility.ObjUtils;

public class TokenValidationFilter extends OncePerRequestFilter {

    private RequestMatcher requiresAuthenticationRequestMatcher;

    public TokenValidationFilter(RequestMatcher requiresAuthenticationRequestMatcher) {
        this.requiresAuthenticationRequestMatcher = requiresAuthenticationRequestMatcher;
    }

    protected boolean requiresAuthentication(HttpServletRequest request, HttpServletResponse response) {
        if (this.requiresAuthenticationRequestMatcher.matches(request)) {
            return true;
        }
        return false;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // TODO 토큰 유효성 확인 및 세션 재발급, API 권한 체크(?)
        
        if (requiresAuthentication(request, response)) {
            String jwtToken = ObjUtils.objToString(ObjUtils.nvl(HttpUtils.getAuthorization("Bearer"), request.getParameter("JWTRequest")));
            if (ObjUtils.isEmpty(SecurityContextHolder.getContext().getAuthentication())) {
                


            } else {
                Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
                if (authentication.getCredentials().equals(jwtToken)) {

                }
            }
        }
        filterChain.doFilter(request, response);
    }
}
