package com.ecommerce.j3.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .cors()
                .and()
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                .antMatchers("/api/hello").permitAll()

                .antMatchers("/api/accounts").authenticated()
                .antMatchers("/**").authenticated()

                .anyRequest().permitAll()
                .and()
                .formLogin().disable()
        ;
    }
        @Override // ignore check swagger resource
        public void configure(WebSecurity web) {
            web.ignoring().antMatchers("/v2/api-docs", "/swagger-resources/**",
                    "/swagger-ui.html", "/webjars/**", "/swagger/**");

        }

}
/*
    cors(): CorsFilter를 활성화 시키는 작업

    csrf().disable() : 세션을 사용하지 않고 JWT 토큰을 활용하여 진행하고 REST API를 만드는 작업이기때문에 csrf 사용은 disable 처리

    sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS):
        현재 스프링 시큐리티에서 세션을 관리하지 않겠다는 의미
        서버에서 관리되는 세션 없이 클라이언트에서 요청하는 헤더에 token을 담아보낸다면 서버에서 토큰을 확인하여 인증하는 방식을 사용할 것이므로 서버에서 관리되어야할 세션이 필요 없어짐

    authorizeRequests(): 이제부터 인증절차에 대한 설정을 진행함.

    antMatchers(): 특정 URL에 대해서 어떤 방식으로 인증처리할지 결정
        permitAll() : 스프링 시큐리티에서 인증이 되지 않더라도 누구나 사용 가능
        authenticated() : 요청내에 스프링 시큐리티 컨텍스트 내에서 인증이 되어야 api 사용 가능. 인증이 되지 않은 요청은 403(Forbidden)
*/