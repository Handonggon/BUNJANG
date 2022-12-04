package kr.co.study.bunjang.configuration;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import kr.co.study.bunjang.component.authentication.CustomAccessDeniedHandler;
import kr.co.study.bunjang.component.authentication.CustomAuthenticationEntryPoint;
import kr.co.study.bunjang.component.authentication.CustomAuthenticationFailureHandler;
import kr.co.study.bunjang.component.authentication.CustomAuthenticationManager;
import kr.co.study.bunjang.component.authentication.CustomAuthenticationSuccessHandler;
import kr.co.study.bunjang.component.authentication.ShopAuthenticationProvider;
import kr.co.study.bunjang.servlet.filter.ShopAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig {
    
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.httpBasic().and().cors().and().csrf().disable();

        http.headers().frameOptions().deny();

        http.authorizeRequests()
            .requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
            .antMatchers("/error/**").permitAll()
            .antMatchers("/swagger-ui/**").permitAll()
            .antMatchers("/v1/**").permitAll()
            .antMatchers("/**").permitAll();//.authenticated();

        http.formLogin().disable().addFilterAt(shopAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);

        
        http.exceptionHandling().accessDeniedHandler(new CustomAccessDeniedHandler())
            .and()
            .exceptionHandling().authenticationEntryPoint(new CustomAuthenticationEntryPoint());

        return http.build();
    }

    @Autowired
    CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler;

    @Autowired
    CustomAuthenticationFailureHandler customAuthenticationFailureHandler;

    @Autowired
    ShopAuthenticationProvider shopAuthenticationProvider;

    @Bean
    public CustomAuthenticationManager customAuthenticationManager() {
        CustomAuthenticationManager authenticationManager = new CustomAuthenticationManager();
        authenticationManager.setAuthenticationProvider(shopAuthenticationProvider);
        return authenticationManager;
    }

    @Bean
    public ShopAuthenticationFilter shopAuthenticationFilter() {
        ShopAuthenticationFilter filter = new ShopAuthenticationFilter(new AntPathRequestMatcher("/v1/login/shop"));
        filter.setAuthenticationSuccessHandler(customAuthenticationSuccessHandler);
        filter.setAuthenticationFailureHandler(customAuthenticationFailureHandler);
        filter.setAuthenticationManager(customAuthenticationManager());
        return filter;
    }

    //카카오 로그인 필터        /v1/login/kakao

    //페이스북 로그인 필터      /v1/login/facebook

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