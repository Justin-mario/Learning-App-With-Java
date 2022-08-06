package com.learningapp.security.config;



public class SecurityConstants {
    public static final String SECRET = "IWillNeverGiveUpOnCoding";
    public static final long EXPIRATION_TIME = 108_000_000; // 1 day
    public static final long EXPIRATION_TIME_FOR_REFRESH_TOKEN = 216_000_000; // 2 days
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";
    public static final String ROLES_KEY = "roles";
}
