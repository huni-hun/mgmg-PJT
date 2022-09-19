package com.ssafy.mgmgproject.common.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.*;
import com.ssafy.mgmgproject.api.service.RedisService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;


@Component
@RequiredArgsConstructor
public class JwtTokenUtil {

    private static String secretKey;

    @Value("${jwt.secret}")
    private void setSecretKey(String secretKey){
        this.secretKey=secretKey;
    }

    private static long expirationAccessTime=24 * 60 * 60 * 1000L;
    private static long expirationRefreshTime=30 * 24 * 60 * 60 * 1000L;
    private final RedisService redisService;

    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";
    public static final String ISSUER = "ssafy.com";

    public static JWTVerifier getVerifier() {
        return JWT
                .require(Algorithm.HMAC512(secretKey.getBytes()))
                .withIssuer(ISSUER)
                .build();
    }

    public boolean checkRefreshToken(String userId, String refreshToken){
        String redisData = redisService.getData(refreshToken);
        if(redisData!=null && redisData.equals(userId)){
            redisService.deleteData(userId);
            return true;
        }
        return false;
    }

    public String createAccessToken(String userId) {
        return getToken(userId, expirationAccessTime);
    }

    public String createRefreshToken(String userId) {
        String refreshToken = getToken(userId, expirationRefreshTime);
        redisService.setDataExpire(refreshToken, userId, expirationRefreshTime);
        return refreshToken;
    }

    private static String getToken(String userId, long tokenInvalidTime) {
        Date expires = JwtTokenUtil.getTokenExpiration(tokenInvalidTime);
        return JWT.create()
                .withSubject(userId)
                .withExpiresAt(expires)
                .withIssuer(ISSUER)
                .withIssuedAt(Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant()))
                .sign(Algorithm.HMAC512(secretKey.getBytes()));
    }

    public static Date getTokenExpiration(long expirationTime) {
        Date now = new Date();
        return new Date(now.getTime() + expirationTime);
    }

    public static void handleError(String token) {
        JWTVerifier verifier = JWT
                .require(Algorithm.HMAC512(secretKey.getBytes()))
                .withIssuer(ISSUER)
                .build();

        try {
            verifier.verify(token.replace(TOKEN_PREFIX, ""));
        } catch (AlgorithmMismatchException ex) {
            throw ex;
        } catch (InvalidClaimException ex) {
            throw ex;
        } catch (SignatureGenerationException ex) {
            throw ex;
        } catch (SignatureVerificationException ex) {
            throw ex;
        } catch (TokenExpiredException ex) {
            throw ex;
        } catch (JWTCreationException ex) {
            throw ex;
        } catch (JWTDecodeException ex) {
            throw ex;
        } catch (JWTVerificationException ex) {
            throw ex;
        } catch (Exception ex) {
            throw ex;
        }
    }
}