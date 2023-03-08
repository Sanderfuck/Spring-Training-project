package com.nixs.config;

import com.nixs.security.CustomUserDetailsService;
import com.nixs.security.LoginHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final CustomUserDetailsService customUserDetailService;
    private final PasswordEncoder passwordEncoder;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/api/**")
                .permitAll()
        .and()
                .formLogin()
                .loginPage("/login")
                .failureForwardUrl("/login?error")
                .successHandler(new LoginHandler())
        .and()
                .logout()
                .invalidateHttpSession(true)
                .deleteCookies()
                .clearAuthentication(true)
                .logoutSuccessUrl("/login?logout")
        .and()
                .authorizeRequests()
                .antMatchers("/", "/registration")
                .permitAll()
                .antMatchers("/user-home").hasAuthority("USER")
                .antMatchers("/admin-home", "/add-user", "/delete").hasAuthority("ADMIN")
        .and().csrf().disable();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(customUserDetailService).passwordEncoder(passwordEncoder);
    }
}
