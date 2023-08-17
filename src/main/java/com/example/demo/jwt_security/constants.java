package com.example.demo.jwt_security;

import java.security.PrivateKey;

public class constants {

    public static final String SECRET_KEY = "49A3735D3C97D3AD387C653BC7D7149A3735D3C97D3AD387C653BC7D7149A3735D3C97D3AD387C653BC7D71";     //<-- must be in secured in db
    public static final String PRE_TOKEN = "Bearer ";
    public static final String AUTH = "Authentication";
    public static final long ACCESS_TOKEN_VALIDITY_SECONDS = 5*60*60;


}
