package br.com.drsource.imacbrasil.security;

import io.jsonwebtoken.Jwts;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JWTAuthorizationFilter extends BasicAuthenticationFilter {

    public JWTAuthorizationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain chain) throws IOException, ServletException {

        String token = request.getHeader(SecurityConstants.AUTHENTICATION_HEADER.getValue());
        if (token == null || !token.startsWith(SecurityConstants.TOKEN_PREFIX.getValue())){
            chain.doFilter(request,response);
            return;
        }


    }

    private UsernamePasswordAuthenticationToken getAuthenticationToken(HttpServletRequest request, String token){
        String username = Jwts.parser().setSigningKey(SecurityConstants.SECRET.getValue())
                .parseClaimsJws(token.replace(SecurityConstants.TOKEN_PREFIX.getValue(),""))
                .getBody()
                .getSubject();

        UserDetails userDetails = null;
        return new UsernamePasswordAuthenticationToken(username,null,userDetails.getAuthorities());
    }
}
