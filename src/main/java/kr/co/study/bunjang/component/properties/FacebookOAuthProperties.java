package kr.co.study.bunjang.component.properties;

import java.util.Set;

import kr.co.study.bunjang.component.utility.PropertyUtils;

public class FacebookOAuthProperties {
    
    public static final String BASE_URL = "https://www.facebook.com";

    public static final String CLIENT_ID = PropertyUtils.getProperty("spring.security.oauth2.client.registration.facebook.client-id");

    public static final String CLIENT_NAME = PropertyUtils.getProperty("spring.security.oauth2.client.registration.facebook.client-name");

    public static final String CLIENT_SECRET = PropertyUtils.getProperty("spring.security.oauth2.client.registration.facebook.client-secret");

    public static final String CLIENT_AUTHENTICATION_METHOD = PropertyUtils.getProperty("spring.security.oauth2.client.registration.facebook.client-authentication-method");

    public static final String AUTHENTICATION_GRANT_TYPE = PropertyUtils.getProperty("spring.security.oauth2.client.registration.facebook.authorization-grant-type");

    public static final String REDIRECT_URL = PropertyUtils.getProperty("spring.security.oauth2.client.registration.facebook.redirect-uri");

    public static final Set<String> SCOPE = PropertyUtils.getPropertySet("spring.security.oauth2.client.registration.facebook.scope");
}