package br.com.drsource.imacbrasil.security.jwt;

public class JWTConstants {

    static final String SECRET = "IMAC@BRASIL!";
    static final String TOKEN_PREFIX = "Bearer ";
    static final String HEADER_STRING = "Authorization";
    public static final String SIGN_UP_URL = "/users/sign-up";
    static final long EXPIRATION_TIME = 86400000L;
}
