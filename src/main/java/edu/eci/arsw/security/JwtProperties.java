package edu.eci.arsw.security;

public class JwtProperties {
    public static final String SECRET = "SecretForHappApplication";
    public static final int EXPIRATION_TIME = 864_000_000; // 10 days
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";
}
