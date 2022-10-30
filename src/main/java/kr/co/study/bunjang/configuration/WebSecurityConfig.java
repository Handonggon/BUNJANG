package kr.co.study.bunjang.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

import kr.co.study.bunjang.component.authentication.CustomAccessDeniedHandler;
import kr.co.study.bunjang.component.authentication.CustomAuthenticationEntryPoint;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.httpBasic().and().cors().and().csrf().disable();

        http.headers().frameOptions().deny();

        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http.authorizeRequests().anyRequest().permitAll();

        //http.addFilterBefore(azureOAuthAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
        //http.addFilterBefore(azureSamlAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);

        http.exceptionHandling().accessDeniedHandler(new CustomAccessDeniedHandler())
            .and()
            .exceptionHandling().authenticationEntryPoint(new CustomAuthenticationEntryPoint());

        return http.build();
    }
    /*
    @Autowired
    AppAuthenticationProvider appAuthenticationProvider;

    @Autowired
    UserAuthenticationProvider userAuthenticationProvider;

    @Bean
    public AzureOAuthAuthenticationFilter azureOAuthAuthenticationFilter() {
        AzureOAuthAuthenticationFilter filter = new AzureOAuthAuthenticationFilter(new AntPathRequestMatcher("/azure/**"));
        filter.setAuthenticationManager(new CustomAuthenticationManager(appAuthenticationProvider));
        return filter;
    }

    @Bean
    public AzureSamlAuthenticationFilter azureSamlAuthenticationFilter() {
        AzureSamlAuthenticationFilter filter = new AzureSamlAuthenticationFilter(new AntPathRequestMatcher("/app/**"));
        filter.setAuthenticationManager(new CustomAuthenticationManager(userAuthenticationProvider));
        return filter;
    }
    */
}