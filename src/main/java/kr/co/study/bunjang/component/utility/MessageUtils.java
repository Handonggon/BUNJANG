package kr.co.study.bunjang.component.utility;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class MessageUtils {
    
    private static MessageSource messageSource;
	
	@Autowired
	public void setMessageSource(MessageSource messageSource) {
		MessageUtils.messageSource = messageSource;
	}

    public static String getMessage(String code, @Nullable Object[] args) throws NoSuchMessageException {
		return messageSource.getMessage(code, args, HttpUtils.getLocale());
	}

    public static String getMessage(String code) throws NoSuchMessageException {
		return MessageUtils.getMessage(code, null);
	}
}