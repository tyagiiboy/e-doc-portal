package com.edoc.backend.filters;

import com.edoc.backend.jwtutils.JwtUtils;
import com.edoc.backend.services.CustomUserDetails;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@Component
@SuppressWarnings("unused")
public class JWTRequestFilter extends OncePerRequestFilter {

  @Autowired
  private JwtUtils utils;
  @Autowired
  private UserDetailsService userDetailsService;

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
      throws ServletException, IOException {
    log.info("in once per request filter");
    // get authorization header n check if not null n starting with Bearer
    String header = request.getHeader("Authorization");
    System.out.println(header);
    if (header != null && header.startsWith("Bearer ")) {
      // Bearer token present --> extract n validate it
      String token = header.substring(7);
      if (utils.validateJwtToken(token)) {
        // valid token --> extract user name(email) from the token
        String userName = utils.getUserNameFromJwtToken(token);
        System.out.println("username: " + userName);
        if (userName != null && SecurityContextHolder.getContext().getAuthentication() == null) {
          // load user details from UserDetailsService
          CustomUserDetails userDetails = (CustomUserDetails) userDetailsService.loadUserByUsername(userName);
          System.out.println("in filter: role: " + userDetails.getUser().getRole());
          //		System.out.println("granted auths "+ utils.getAuthoritiesFromJwtToken(token));
          // create Authentication object , wrapping user details lifted from DB
          UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
              userDetails, null, userDetails.getAuthorities());
          // set details in authentication object. Above ctor sets authentication flag to
          // true => user already authenticated , so that other filters in the chain
          // should not attempt auth again
//					Save this authentication token in the sec ctx.
          SecurityContextHolder.getContext().setAuthentication(authentication);
        } else
          log.info("user name null or authentication already set , username {}", userName);

      }
    } else
      log.error("Request header DOES NOT contain a Bearer Token");
    // pass the request to the next filter in the chain
    filterChain.doFilter(request, response);

  }

}
