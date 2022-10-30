package kr.co.study.bunjang.component.utils;

import java.io.IOException;
import java.util.Enumeration;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.ObjectUtils;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@UtilityClass
public class HttpUtils {

	public HttpServletRequest getRequest() {
		return ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
	}

	public HttpSession getSession() {
		return getRequest().getSession();
	}

	public HttpServletResponse getResponse() {
		return ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getResponse();
	}

	private String getUserIp(HttpServletRequest request) {
		String ip = request.getHeader("X-Forwarded-For");

		if (ObjectUtils.isEmpty(ip) || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ObjectUtils.isEmpty(ip) || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ObjectUtils.isEmpty(ip) || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_CLIENT_IP");
		}
		if (ObjectUtils.isEmpty(ip) || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_X_FORWARDED_FOR");
		}
		if (ObjectUtils.isEmpty(ip) || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("X-Real-IP");
		}
		if (ObjectUtils.isEmpty(ip) || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("X-RealIP");
		}
		if (ObjectUtils.isEmpty(ip) || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("REMOTE_ADDR");
		}
		if (ObjectUtils.isEmpty(ip) || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}

	public String getUserIp() {
		return getUserIp(getRequest());
	}

	private String getBaseUrl(HttpServletRequest request) {
		return request.getRequestURL().toString().replace(request.getRequestURI(), "");
	}

	public String getBaseUrl() {
		return getBaseUrl(getRequest());
	}

	public Locale getLocale() {
		return LocaleContextHolder.getLocale();
	}

	private final String AUTHORIZATION = "Authorization";

	private String getAuthorization(HttpServletRequest request, String type) throws IOException {
		Enumeration<String> Authorization = request.getHeaders(AUTHORIZATION);
		while (Authorization.hasMoreElements()) {
			String value = Authorization.nextElement();
			if (value.toLowerCase().startsWith(type.toLowerCase())) {
				return value.substring(type.length()).trim();
			}
		}
		log.info("HttpUtils - getAuthorization: Return Empty");
		return "";
	}

	public String getAuthorization(String type) throws IOException {
		return getAuthorization(getRequest(), type);
	}
}