package kr.co.study.bunjang.component.exception;

import org.springframework.security.core.AuthenticationException;

import kr.co.study.bunjang.component.utility.MessageUtils;

public class AuthenticationEntryPointException extends AuthenticationException {

    public AuthenticationEntryPointException(Throwable cause) {
		super(MessageUtils.getMessage("exception.AuthenticationEntryPoint"), cause);
	}

    public AuthenticationEntryPointException() {
        super(MessageUtils.getMessage("exception.AuthenticationEntryPoint"));
    }
}