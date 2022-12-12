package kr.co.study.bunjang.component.authentication;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class AuthenticationFailureHandlerImpl implements AuthenticationFailureHandler {

	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
		// TODO 실패 히스토리

		if (exception instanceof AuthenticationServiceException) {

		}

		if (exception instanceof BadCredentialsException) {

		}

		if (exception instanceof DisabledException) {

		}

		if (exception instanceof InsufficientAuthenticationException) {

		}

		if (exception instanceof UsernameNotFoundException) {

		}

        Map<String, Object> responseMap = new HashMap<>();
        responseMap.put("error", exception.getClass().getName());
        responseMap.put("error_description", exception.getMessage());
        responseMap.put("timestamp", LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        new ObjectMapper().writeValue(response.getWriter(), responseMap);
	}   
}
