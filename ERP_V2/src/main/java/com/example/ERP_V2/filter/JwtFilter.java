package com.example.ERP_V2.filter;

import com.example.ERP_V2.Services.AuthenticationService;
import com.example.ERP_V2.enums.RoleEnum;
import com.example.ERP_V2.utils.JwtUtil;
import io.jsonwebtoken.*;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Objects;

@Component
@Slf4j
public class JwtFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private AuthenticationService authenticationService;

    @Value("${jwt.secret}") // Example: Retrieve secret from application.properties
    private String jwtSecret;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String jwtToken = jwtUtil.extractTokenFromRequest(request);
        if (Objects.nonNull(jwtToken)) {
            try {
                String email = jwtUtil.getUsernameFromToken(jwtToken);
                RoleEnum role = jwtUtil.getRoleFromToken(jwtToken);

                Claims claims = extractClaims(jwtToken);

                int id = claims.get("id", Integer.class);

                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = this.authenticationService.getUsernamePasswordAuthenticationToken(id, role);

                if (jwtUtil.validateToken(jwtToken, usernamePasswordAuthenticationToken.getName())) {
                    SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
                }
            } catch (ExpiredJwtException | MalformedJwtException | UnsupportedJwtException
                     | IllegalArgumentException | UsernameNotFoundException ex) {
                log.error("Authentication Error : {}", ex.getMessage());
            }
        }
        filterChain.doFilter(request, response);
    }

    private Claims extractClaims(String token) {
        return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody();
    }

}
