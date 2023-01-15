package kr.co.study.bunjang.component.properties;

import java.util.Set;

import kr.co.study.bunjang.component.utility.PropertyUtils;

public class KakaoOAuthProperties {
    
    public static final String BASE_URL = "https://kauth.kakao.com";

    public static final String CLIENT_ID = PropertyUtils.getProperty("spring.security.oauth2.client.registration.kakao.client-id");

    public static final String CLIENT_NAME = PropertyUtils.getProperty("spring.security.oauth2.client.registration.kakao.client-name");

    public static final String CLIENT_SECRET = PropertyUtils.getProperty("spring.security.oauth2.client.registration.kakao.client-secret");

    public static final String CLIENT_AUTHENTICATION_METHOD = PropertyUtils.getProperty("spring.security.oauth2.client.registration.kakao.client-authentication-method");

    public static final String AUTHENTICATION_GRANT_TYPE = PropertyUtils.getProperty("spring.security.oauth2.client.registration.kakao.authorization-grant-type");

    public static final String REDIRECT_URL = PropertyUtils.getProperty("spring.security.oauth2.client.registration.kakao.redirect-uri");

    public static final Set<String> SCOPE = PropertyUtils.getPropertySet("spring.security.oauth2.client.registration.kakao.scope");
}