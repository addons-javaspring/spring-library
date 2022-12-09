package com.fazz.library.config.security;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.fazz.library.config.jwt.JwtUtil;
import com.fazz.library.service.auth.UserDetailsServiceImpl;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class AuthFilter extends OncePerRequestFilter {
  @Autowired
  private JwtUtil jwtUtil;

  @Autowired
  private UserDetailsServiceImpl userDetailsServiceImpl;

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
      throws ServletException, IOException {
    // TODO Auto-generated method stub
    String headerAuth = request.getHeader("Authorization");

    // check if header is null or not
    if (headerAuth != null && headerAuth.startsWith("Bearer ")) {
      String jwtToken = headerAuth.substring(7);

      if (jwtToken != null && jwtUtil.validateToken(jwtToken)) {
        String username = jwtUtil.getUsernameFromToken(jwtToken);

        // dibentuk userdetailsnya
        UserDetails userDetails = userDetailsServiceImpl.loadUserByUsername(username);

        // authenticate the user
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails,
            userDetails.getPassword(), userDetails.getAuthorities());
        authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
      }
    }

    response.setHeader("Access-Control-Allow-Origin", "*");
    response.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT. DELETE. OPTIONS");
    response.setHeader("Access-Control-Allow-Headers", "Authorization, Content-Type, Cache-Control");
    response.setHeader("Access-Control-Max-Age", "3600");

    if ("OPTIONS".equalsIgnoreCase(request.getMethod())) {
      response.setStatus(200);
    } else {
      filterChain.doFilter(request, response);
    }

  }

}
