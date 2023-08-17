package com.example.demo.jwt_security.utils;

import com.example.demo.jwt_security.entities.AppUser;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import com.example.demo.jwt_security.constants;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;

@Service
public class JwtService { //is not important to know how it's implemented

    public String extractUsername(String token){
        return extractOneClaim(token,Claims::getSubject);
    }

    private Key getSignKey() {
        byte[] a = constants.SECRET_KEY.getBytes(StandardCharsets.UTF_8);
        return Keys.hmacShaKeyFor(a);
        //return Keys.secretKeyFor(SignatureAlgorithm.HS256);//HS256 needs secret key size >=256
    }


    public String generate_token(Map<String , Object> extraClaims , UserDetails userDetails){
        return Jwts.builder()
                .setClaims(extraClaims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis()+50*60*600000))
                .signWith(SignatureAlgorithm.HS256 ,getSignKey())
                .compact();
    }

    public String generate_token(UserDetails userDetails){
        return generate_token(new HashMap<>(),userDetails);
    }

    public Claims extractAllClaims(String token){
        return Jwts.parser()
                .setSigningKey(getSignKey())
                .parseClaimsJws(token).getBody();
    }
    //     funcition<T,R> converting T to R by method called apply
    public <T> T extractOneClaim(String token, Function<Claims,T> claimsResolver){
    Claims claims= extractAllClaims(token);
    return claimsResolver.apply(claims);
    }

    public boolean isValidToken(String token, AppUser user){
        return extractUsername(token).equals(user.getUsername()) && isNonExpired(token);
    }

    public boolean isNonExpired(String token){
    return extractExpirationDate(token).after(new Date());
    }

    public Date extractExpirationDate(String token){
        return extractOneClaim(token,Claims::getExpiration);
    }

}

//
//public String extractUsername(String token){
//    return extractOneClaim(token,Claims::getSubject);
//}
//
//    private Key getSignKey(){
//        byte[] keyBytes=Decoders.BASE64.decode(SECRET_KEY); //convert the secret key to array of bytes
//        return Keys.hmacShaKeyFor(keyBytes); //convert bytes to secret key of type key
//    }
//
//    public String generate_token(Map<String , Object> extraClaims , UserDetails userDetails){
//        return Jwts.
//                builder(). //create an instance of Token   and give it some data (username,date , key)
//                        setClaims(extraClaims).
//                setSubject(userDetails.getUsername()).
//                setIssuedAt(new Date(System.currentTimeMillis())).
//                setExpiration(new Date(System.currentTimeMillis()+1000*60*24)).
//                signWith(getSignKey(), SignatureAlgorithm.HS256).
//                compact();
//    }
//
//
//    public String generate_token(UserDetails userDetails){
//        return (new HashMap<>(),userDetails);
//    }
//
//    public Claims extractAllClaims(String token){
//        return Jwts.
//                parserBuilder().          //parser y3ni converter (from token string to claim JSON)
//                        setSigningKey(getSignKey())
//                .build()
//                .parseClaimsJws(token)    //parse y3ni 7wel -> (string token -> Json)
//                .getBody();
//    }
//    //     funcition<T,R> converting T to R by method called apply
//    public <T> T extractOneClaim(String token, Function<Claims,T> claimsResolver){
//        final Claims claims =extractAllClaims(token); // get all claims
//        return claimsResolver.apply(claims);
//    }
//
//    public boolean isValidToken(String token, AppUser user){
//        return extractUsername(token).equals(user.username)&& isNonExpired(token);
//    }
//
//    public boolean isNonExpired(String token){
//        return extractExpirationDate(token).after(new Date());
//    }
//
//    public Date extractExpirationDate(String token){
//        return extractOneClaim(token,Claims::getExpiration);
//    }

