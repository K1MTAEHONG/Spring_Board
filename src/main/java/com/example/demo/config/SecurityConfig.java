package com.example.demo.config;


import com.example.demo.enums.UserRole;
import com.example.demo.handler.WebAuthenticationEntryPoint;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity      //스프링 시큐리티를 활성화 하고 모든 요청에 영향을 끼친다
@EnableMethodSecurity(prePostEnabled = true)
//@EnableMethodSecurity(prePostEnabled = true , securedEnabled = true)

public class SecurityConfig {               //스프링 시큐리티에 대한 설정을 이곳에서 관리한다.

    @Bean               //필요한 객체 들을 관리 하는 것  빈을 달아놓으면 filterChain으로 한다
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests((authorizeHttpRequests) -> authorizeHttpRequests
                        .requestMatchers(new AntPathRequestMatcher("/**")).permitAll())
                .csrf((csrf) -> csrf
                        .ignoringRequestMatchers(new AntPathRequestMatcher("/**")))
                .formLogin((formLogin) -> formLogin/*로그인 Url 생성 */
                        .loginPage("/users/login")
                        .usernameParameter("userId")
                        .passwordParameter("password")
                        .defaultSuccessUrl("/"))
                .logout((logout) -> logout
                        .logoutRequestMatcher(new AntPathRequestMatcher("/users/logout"))
                        .logoutSuccessUrl("/")
                        .invalidateHttpSession(true))

        ;

        return http.build();
    }
    @Bean
    AuthenticationEntryPoint authenticationEntryPoint() {
        return new WebAuthenticationEntryPoint();
    }

    @Bean
    PasswordEncoder accessDeniedHandler(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


}
