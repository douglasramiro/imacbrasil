package br.com.drsource.imacbrasil.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private AuthenticationManager authenticationManager;

    public JWTAuthenticationFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        try {
            Credential credential = new ObjectMapper().readValue(request.getInputStream(),Credential.class);
            return this.authenticationManager
                        .authenticate(new UsernamePasswordAuthenticationToken(credential.getUsername(), credential.getPassword()));

        } catch (IOException e) {
             throw new RuntimeException(e);
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request,
                                            HttpServletResponse response,
                                            FilterChain chain,
                                            Authentication authResult) throws IOException, ServletException {

        String username = ((User)authResult).getUsername();
        String token = Jwts
                .builder()
                .setSubject(username)
                .setExpiration(new Date(System.currentTimeMillis() + SecurityConstants.EXPIRATION_TIME.getLongValue()))
                .signWith(SignatureAlgorithm.HS512,SecurityConstants.SECRET.getValue())
                .compact();

        response.addHeader(SecurityConstants.AUTHENTICATION_HEADER.getValue(),SecurityConstants.TOKEN_PREFIX.getValue()+ token);
        response.getWriter().print(SecurityConstants.AUTHENTICATION_HEADER.getValue()+": "+SecurityConstants.TOKEN_PREFIX.getValue()+ token);



    }
}
