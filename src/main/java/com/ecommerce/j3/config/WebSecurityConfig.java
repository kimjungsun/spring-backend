//package com.ecommerce.j3.config;
//
//import com.ecommerce.j3.config.security.JwtAuthenticationFilter;
//import com.ecommerce.j3.config.security.JwtTokenProvider;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.builders.WebSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.crypto.factory.PasswordEncoderFactories;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//
//@Configuration
//@EnableWebSecurity
//public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
//
//    private final JwtTokenProvider jwtTokenProvider;
//
//    public WebSecurityConfig(JwtTokenProvider jwtTokenProvider) {
//        this.jwtTokenProvider = jwtTokenProvider;
//    }
//
//    /* Swagger UI 접속이 가능토록 예외 등록 */
//    @Override
//    public void configure(WebSecurity web) {
//        web.ignoring().antMatchers("/v2/api-docs", "/swagger-resources/**",
//                "/swagger-ui.html", "/webjars/**", "/swagger/**");
//    }
//    /* Swagger UI 접속이 가능토록 예외 등록 */
//
//    // 암호화에 필요한 PasswordEncoder 를 Bean 등록합니다.
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
//    }
//
//    // authenticationManager를 Bean 등록합니다.
//    @Bean
//    @Override
//    public AuthenticationManager authenticationManagerBean() throws Exception {
//        return super.authenticationManagerBean();
//    }
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http
//                .httpBasic().disable() // rest api 만을 고려하여 기본 설정은 해제하겠습니다.
//                .csrf().disable() // csrf 보안 토큰 disable처리.
//                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS) // 토큰 기반 인증이므로 세션 역시 사용하지 않습니다.
//                .and()
//                .authorizeRequests() // 요청에 대한 사용권한 체크
//                .antMatchers("/api/accounts/**").hasRole("ADMIN")
////                .antMatchers("/user/**").hasRole("USER")
//                .anyRequest().permitAll() // 그외 나머지 요청은 누구나 접근 가능
//                .and()
//                .addFilterBefore(new JwtAuthenticationFilter(jwtTokenProvider),
//                        UsernamePasswordAuthenticationFilter.class);
//        // JwtAuthenticationFilter를 UsernamePasswordAuthenticationFilter 전에 넣는다
//    }
//}
//
////    @Override
////    protected void configure(HttpSecurity http) throws Exception {
////        http
////                .cors()
////                .and()
////                .csrf().disable()
////                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
////                .and()
////                .authorizeRequests()
////                .antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
////                .antMatchers("/api/hello").permitAll()
////
////                .antMatchers("/api/accounts").authenticated()
////                .antMatchers("/**").authenticated()
////
////                .anyRequest().permitAll()
////                .and()
////                .formLogin().disable()
////        ;
////    }
//
///*
//    cors(): CorsFilter를 활성화 시키는 작업
//
//    csrf().disable() : 세션을 사용하지 않고 JWT 토큰을 활용하여 진행하고 REST API를 만드는 작업이기때문에 csrf 사용은 disable 처리
//
//    sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS):
//        현재 스프링 시큐리티에서 세션을 관리하지 않겠다는 의미
//        서버에서 관리되는 세션 없이 클라이언트에서 요청하는 헤더에 token을 담아보낸다면 서버에서 토큰을 확인하여 인증하는 방식을 사용할 것이므로 서버에서 관리되어야할 세션이 필요 없어짐
//
//    authorizeRequests(): 이제부터 인증절차에 대한 설정을 진행함.
//
//    antMatchers(): 특정 URL에 대해서 어떤 방식으로 인증처리할지 결정
//        permitAll() : 스프링 시큐리티에서 인증이 되지 않더라도 누구나 사용 가능
//        authenticated() : 요청내에 스프링 시큐리티 컨텍스트 내에서 인증이 되어야 api 사용 가능. 인증이 되지 않은 요청은 403(Forbidden)
//*/