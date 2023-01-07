package kr.co.study.bunjang.component.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;


public class OAuthKakaoProperties {
    
    public static final String BASE_URL = "https://kauth.kakao.com";

    @Bean
    @ConfigurationProperties(prefix = "spring.security.oauth2.client.")
    public OAuthRegistration item() {
        return new OAuthRegistration();
    }
}