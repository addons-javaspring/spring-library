package com.fazz.library.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fazz.library.service.auth.UserDetailsServiceImpl;

@SuppressWarnings("deprecation")
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig {
  @Autowired
  private AuthEntryPoint authEntryPoint;

  @Autowired
  private UserDetailsServiceImpl userDetailsService;

  @Bean
  public AuthFilter jwtFilter() {
    return new AuthFilter();
  }

  @Bean
  public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
      throws Exception {
    // TODO Auto-generated method stub
    return authenticationConfiguration.getAuthenticationManager();
  }

  @Bean
  public DaoAuthenticationProvider authenticationProvider() {
    DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();

    authenticationProvider.setUserDetailsService(userDetailsService);
    authenticationProvider.setPasswordEncoder(passwordEncoder());

    return authenticationProvider;
  }

  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    // TODO Auto-generated method stub
    // Enable CORS and disable CSRF
    http = http.cors().and().csrf().disable();

    // Session Management
    http = http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and();

    // Handling Exception Authentication Entry Point
    http = http.exceptionHandling().authenticationEntryPoint(authEntryPoint).and();

    // Endpoints Permition
    http.authorizeRequests().requestMatchers("/").permitAll()
        .requestMatchers(HttpMethod.POST, "/auth/**").permitAll()
        .anyRequest().authenticated();

    // Set authentication provider
    http.authenticationProvider(authenticationProvider());

    // Set authentication filter
    http.addFilterBefore(jwtFilter(), UsernamePasswordAuthenticationFilter.class);

    return http.build();
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }
}
