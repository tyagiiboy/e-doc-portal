package com.edoc.backend.config;

import com.edoc.backend.filters.JWTRequestFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.servlet.http.HttpServletResponse;

@EnableWebSecurity
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
@SuppressWarnings("unused")
public class WebSecurityConfig {

  @Autowired
  private JWTRequestFilter filter;

  @Bean
  public PasswordEncoder encoder() {
//    return new BCryptPasswordEncoder(10);
    return PasswordEncoderFactories.createDelegatingPasswordEncoder();
  }

  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http.cors()
        .and()
        .csrf()
        .disable()
        .exceptionHandling()
        .authenticationEntryPoint(
            (request, response, ex) -> response
                .sendError(HttpServletResponse.SC_UNAUTHORIZED, ex.getMessage())
        )
        .and()
        .authorizeRequests()
        // for sign in , sign up , swagger : permit all
        .antMatchers("/auth/**", "/swagger*/**", "/v*/api-docs/**").permitAll()
        // only required for JS clnts (react / angular) : for the pre flight requests
        .antMatchers(HttpMethod.OPTIONS).permitAll()
//        .antMatchers("/**").hasRole("ADMIN")
//        .antMatchers("/school/**").hasRole("SCHOOL")
//        .antMatchers("/student/**").hasRole("STUDENT")
        .antMatchers("/**").permitAll()
        .anyRequest().authenticated()
        .and()
        .sessionManagement()
        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        .and()
        .addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);
    return http.build();
  }

  // configure auth mgr bean : to be used in SignInSignUp REST controller
  @Bean
  public AuthenticationManager authenticatonMgr(AuthenticationConfiguration config) throws Exception {
    return config.getAuthenticationManager();
  }

  //for global CORS enabling
  @Bean
  public WebMvcConfigurer corsConfigurer() {
    return new WebMvcConfigurer() {
      @Override
      public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**").allowedOrigins("http://localhost:3000").allowedMethods("*");
      }
    };
  }

}
