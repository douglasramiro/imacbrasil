package br.com.drsource.imacbrasil.security;

public enum SecurityConstants {
    SECRET("ImacBrasil"),
    TOKEN_PREFIX("Bearer "),
    AUTHENTICATION_HEADER("Authorization"),
    SIGNUP_URL("/users/sign-up"),
    EXPIRATION_TIME(86400000l)

    ;


    private String value;
    private Long longValue;

    SecurityConstants(String value) {
        this.value = value;
    }

    SecurityConstants(Long longValue) {
        this.longValue = longValue;
    }

    public String getValue() {
        return value;
    }

    public Long getLongValue(){
        return longValue;
    }


}
