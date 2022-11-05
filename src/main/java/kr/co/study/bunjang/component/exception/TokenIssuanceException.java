package kr.co.study.bunjang.component.exception;

import java.io.IOException;

import kr.co.study.bunjang.component.utility.MessageUtils;

public class TokenIssuanceException extends IOException {

    public TokenIssuanceException(Throwable cause) {
		super(MessageUtils.getMessage("exception.TokenIssuanceException"), cause);
	}

    public TokenIssuanceException() {
        super(MessageUtils.getMessage("exception.TokenIssuanceException"));
    }
}