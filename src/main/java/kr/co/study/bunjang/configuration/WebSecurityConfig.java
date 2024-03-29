package kr.co.study.bunjang.configuration;

import java.util.Arrays;

import kr.co.study.bunjang.component.authentication.facebook.FacebookAuthenticationProvider;
import kr.co.study.bunjang.servlet.filter.FacebookAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import kr.co.study.bunjang.component.authentication.AccessDeniedHandlerImpl;
import kr.co.study.bunjang.component.authentication.AuthenticationEntryPointImpl;
import kr.co.study.bunjang.component.authentication.AuthenticationFailureHandlerImpl;
import kr.co.study.bunjang.component.authentication.AuthenticationManagerImpl;
import kr.co.study.bunjang.component.authentication.AuthenticationSuccessHandlerImpl;
import kr.co.study.bunjang.component.authentication.kakao.KakaoAuthenticationProvider;
import kr.co.study.bunjang.component.authentication.mobile.MobileAuthenticationProvider;
import kr.co.study.bunjang.servlet.filter.KakaoAuthenticationFilter;
import kr.co.study.bunjang.servlet.filter.MobileAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig {
    
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.httpBasic().disable()
            .cors().disable()
            .csrf().disable();

        http.headers().frameOptions().deny();

        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http.authorizeRequests()
            .requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
            .antMatchers("/error/**").permitAll()
            .antMatchers("/swagger-ui/**").permitAll()
            .antMatchers("/v1/**").permitAll()
            .antMatchers("/**").permitAll();//.authenticated();

        http.formLogin().disable().addFilterAt(mobileAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class)
            .addFilterAt(kakaoAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class)
                .addFilterAt(facebookAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);

        http.exceptionHandling()
            .accessDeniedHandler(new AccessDeniedHandlerImpl())
            .authenticationEntryPoint(new AuthenticationEntryPointImpl());

        return http.build();
    }

    @Autowired
    AuthenticationSuccessHandlerImpl authenticationSuccessHandlerImpl;

    @Autowired
    AuthenticationFailureHandlerImpl authenticationFailureHandlerImpl;

    @Autowired
    MobileAuthenticationProvider mobileAuthenticationProvider;

    @Autowired
    KakaoAuthenticationProvider kakaoAuthenticationProvider;

    @Autowired
    FacebookAuthenticationProvider facebookAuthenticationProvider;

    @Bean
    public AuthenticationManagerImpl authenticationManager() {
        AuthenticationManagerImpl authenticationManager = new AuthenticationManagerImpl();
        authenticationManager.setAuthenticationProvider(mobileAuthenticationProvider);
        authenticationManager.setAuthenticationProvider(kakaoAuthenticationProvider);
        authenticationManager.setAuthenticationProvider(facebookAuthenticationProvider);
        return authenticationManager;
    }

    @Bean
    public MobileAuthenticationFilter mobileAuthenticationFilter() {
        MobileAuthenticationFilter filter = new MobileAuthenticationFilter(new AntPathRequestMatcher("/v1/login/mobile"));
        filter.setAuthenticationSuccessHandler(authenticationSuccessHandlerImpl);
        filter.setAuthenticationFailureHandler(authenticationFailureHandlerImpl);
        filter.setAuthenticationManager(authenticationManager());
        return filter;
    }

    //카카오 로그인 필터        /v1/login/kakao
    @Bean
    public KakaoAuthenticationFilter kakaoAuthenticationFilter() {
        KakaoAuthenticationFilter filter = new KakaoAuthenticationFilter(new AntPathRequestMatcher("/v1/login/kakao"));
        filter.setAuthenticationSuccessHandler(authenticationSuccessHandlerImpl);
        filter.setAuthenticationFailureHandler(authenticationFailureHandlerImpl);
        filter.setAuthenticationManager(authenticationManager());
        return filter;
    }

    //페이스북 로그인 필터      /v1/login/facebook

    @Bean
    public FacebookAuthenticationFilter facebookAuthenticationFilter() {
        FacebookAuthenticationFilter filter = new FacebookAuthenticationFilter(new AntPathRequestMatcher("/v1/login/facebook"));
        filter.setAuthenticationSuccessHandler(authenticationSuccessHandlerImpl);
        filter.setAuthenticationFailureHandler(authenticationFailureHandlerImpl);
        filter.setAuthenticationManager(authenticationManager());
        return filter;
    }

    //네이버 로그인 필터        /v1/login/naver

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.addAllowedOrigin("*");
        configuration.setAllowedMethods(Arrays.asList("HEAD", "GET", "POST", "PUT", "DELETE"));
        configuration.addAllowedHeader("*");
        configuration.setAllowCredentials(true);
        configuration.setMaxAge(3600L);
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}