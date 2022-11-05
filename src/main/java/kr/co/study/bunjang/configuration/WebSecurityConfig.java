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
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import kr.co.study.bunjang.component.authentication.CustomAccessDeniedHandler;
import kr.co.study.bunjang.component.authentication.CustomAuthenticationEntryPoint;
import kr.co.study.bunjang.component.authentication.CustomAuthenticationFailureHandler;
import kr.co.study.bunjang.component.authentication.CustomAuthenticationSuccessHandler;
import kr.co.study.bunjang.servlet.filter.TokenValidationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig {

    private static final RequestMatcher LOGIN_REQUEST_MATCHER = new AntPathRequestMatcher("/v1/login");

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.httpBasic().and().cors().and().csrf().disable();

        http.headers().frameOptions().deny();

        http.authorizeRequests()
            .requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
            .antMatchers("/login", "/v1/login").permitAll()
            .antMatchers("/error/**").permitAll()
            .antMatchers("/swagger-ui/**").permitAll()
            .antMatchers("/v1/**").permitAll()
            .antMatchers("/**").permitAll();//.authenticated();

        //http.formLogin().disable().addFilterAt(ssoAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
        //http.addFilterBefore(tokenValidationFilter(), KakaoAuthenticationFilter.class);
        
        http.exceptionHandling().accessDeniedHandler(new CustomAccessDeniedHandler())
            .and()
            .exceptionHandling().authenticationEntryPoint(new CustomAuthenticationEntryPoint());

        return http.build();
    }

    @Autowired
    CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler;

    @Autowired
    CustomAuthenticationFailureHandler customAuthenticationFailureHandler;

    @Bean
    public TokenValidationFilter tokenValidationFilter() {
        TokenValidationFilter filter = new TokenValidationFilter(new AntPathRequestMatcher("/*"));
        return filter;
    }

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