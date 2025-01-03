package com.Ecommerce.LibraryStore.security.filter;

import com.Ecommerce.LibraryStore.model.LocalUser;
import com.Ecommerce.LibraryStore.model.dao.LocalUserDAO;
import com.Ecommerce.LibraryStore.service.JWTService;
import com.auth0.jwt.exceptions.JWTDecodeException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;

@Component
public class JWTRequestFilter extends OncePerRequestFilter {
    private JWTService jwtService;
    private LocalUserDAO localUserDAO;
    public JWTRequestFilter(JWTService jwtService, LocalUserDAO localUserDAO) {
        this.jwtService = jwtService;
        this.localUserDAO = localUserDAO;
        ;
    }


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String tokenHeader = request.getHeader("Authorization");
        if (tokenHeader != null && tokenHeader.startsWith("Bearer ")) {
           String token = tokenHeader.substring(7);
           try {
               String username = jwtService.getUsername(token);
               Optional<LocalUser> oplUser = localUserDAO.findByUsernameIgnoreCase(username);
           if (oplUser.isPresent()) {
               LocalUser user = oplUser.get();
               UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(user,null,new ArrayList());
               authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
               SecurityContextHolder.getContext().setAuthentication(authentication);
           }
           }
           catch (JWTDecodeException ex){

           }
        }
        filterChain.doFilter(request, response);
    }
}
