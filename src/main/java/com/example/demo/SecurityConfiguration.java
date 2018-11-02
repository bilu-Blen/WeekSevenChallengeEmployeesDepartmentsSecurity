package com.example.demo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter{
    @Bean
    BCryptPasswordEncoder encoder(){
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http.authorizeRequests()
                .antMatchers("/", "css/**").permitAll()
                .antMatchers("/api/**").hasAnyAuthority("APIUSER")
                .antMatchers("/json/**", "/h2/**").hasAnyAuthority("ADMIN")
                .antMatchers("/**").hasAnyAuthority("USER", "ADMIN", "APIUSER")
                .and().formLogin()
                .and().logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout"));
        http.headers().frameOptions().disable().and().csrf().disable();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception{
        auth.inMemoryAuthentication().withUser("user").password(encoder().encode("password")).authorities("USER")
                .and().withUser("admin").password(encoder().encode("password")).authorities("ADMIN")
                .and().withUser("apiuser").password(encoder().encode("password")).authorities("APIUSER")
                .and().passwordEncoder(encoder());
    }
}
