package kr.co.study.bunjang.configuration;

import java.util.List;
import java.util.Locale;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.resource.PathResourceResolver;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.navercorp.lucy.security.xss.servletfilter.XssEscapeServletFilter;

import kr.co.study.bunjang.servlet.converter.HTMLCharacterEscapes;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

	/* 필터 설정 */
	@Bean
	public FilterRegistrationBean<XssEscapeServletFilter> getXssEscapeServletFilter() {
		FilterRegistrationBean<XssEscapeServletFilter> registration = new FilterRegistrationBean<XssEscapeServletFilter>();
		registration.setFilter(new XssEscapeServletFilter());

		registration.addUrlPatterns("/*");
		registration.setOrder(Ordered.HIGHEST_PRECEDENCE);
		registration.setName("XssEscape Filter");
		return registration;
	}
	
	@Bean
	public FilterRegistrationBean<CharacterEncodingFilter> getCharacterEncodingFilter() {
		FilterRegistrationBean<CharacterEncodingFilter> registration = new FilterRegistrationBean<CharacterEncodingFilter>();
		CharacterEncodingFilter filter = new CharacterEncodingFilter();
		filter.setEncoding("UTF-8");
		filter.setForceEncoding(true);
		registration.setFilter(filter);

		registration.addUrlPatterns("/*");
		registration.setOrder(Ordered.HIGHEST_PRECEDENCE + 1);
		registration.setName("UTF-8 Encoding Filter");
		return registration;
	}


	/* Interceptor 설정 */
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		/** 다국어 처리 인터셉터 */
		registry.addInterceptor(localeChangeInterceptor());

	}

	/* Resource 설정 */
	private static final String[] CLASSPATH_RESOURCE_LOCATIONS = { "classpath:/static/" };

	@Override
	public void addResourceHandlers(final ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/**")
				.addResourceLocations(CLASSPATH_RESOURCE_LOCATIONS)
				.resourceChain(true)
				.addResolver(new PathResourceResolver());
	}

	/* HttpMessageConverter 설정 */
	@Override
	public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
		converters.add(htmlEscapingConverter());
	}

	@Bean
	public HttpMessageConverter<?> htmlEscapingConverter() {
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.getFactory().setCharacterEscapes(new HTMLCharacterEscapes()); // 
		objectMapper.registerModule(new JavaTimeModule());
		objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

		MappingJackson2HttpMessageConverter htmlEscapingConverter = new MappingJackson2HttpMessageConverter();
		htmlEscapingConverter.setObjectMapper(objectMapper);

		return htmlEscapingConverter;
	}

	/* Spring Message 설정 */
	@Bean
	public LocaleResolver localeResolver() {
		// 브라우저의 언어로 인식하도록 기본 설정
		CookieLocaleResolver localeResolver = new CookieLocaleResolver();
		localeResolver.setLanguageTagCompliant(false);
		localeResolver.setDefaultLocale(Locale.KOREA);
		localeResolver.setCookieName("APPLICATION_LOCALE");
		return localeResolver;
	}

	@Bean
	public LocaleChangeInterceptor localeChangeInterceptor() {
	    LocaleChangeInterceptor localeChangeInterceptor = new LocaleChangeInterceptor();
	    localeChangeInterceptor.setParamName("lang");
	    return localeChangeInterceptor;
	}
}