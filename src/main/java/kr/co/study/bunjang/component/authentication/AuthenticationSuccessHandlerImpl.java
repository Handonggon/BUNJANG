package kr.co.study.bunjang.component.authentication;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

import kr.co.study.bunjang.component.utility.JwtUtils;

@Component
public class AuthenticationSuccessHandlerImpl implements AuthenticationSuccessHandler {

    ObjectMapper objectMapper = new ObjectMapper();

    private static final Long TOKEN_VALID_MILLISECOND = 2 * 60 * 60 * 1000L;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
        // TODO 성공 히스토리
        // refreshToken 토큰 발급?

        String accessToken = JwtUtils.createToken(objectMapper.convertValue(authentication.getPrincipal(), Map.class), JwtUtils.SECRET_KEY, TOKEN_VALID_MILLISECOND);

        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setCharacterEncoding("UTF-8");
        response.setHeader(accessToken, accessToken);

        Map<String, Object> responseMap = new HashMap<>();
        responseMap.put("tokenType", "Bearer");
        responseMap.put("accessToken", accessToken);
        // responseMap.put("refreshToken", "");
        responseMap.put("expiresIn", TOKEN_VALID_MILLISECOND);
        new ObjectMapper().writeValue(response.getWriter(), responseMap);
    }
}