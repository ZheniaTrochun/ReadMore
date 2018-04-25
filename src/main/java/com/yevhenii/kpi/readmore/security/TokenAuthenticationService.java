package com.yevhenii.kpi.readmore.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Collections;
import java.util.Date;
import java.util.Objects;

@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
public class TokenAuthenticationService {

    private final long expiration;
    private final String secret;
    private final String prefix;
    private final String header;

    private static TokenAuthenticationService instance;

    @Autowired
    public TokenAuthenticationService(
            @Value("${security.jwt.expiration}") long expiration,
            @Value("${security.jwt.secret}") String secret,
            @Value("${security.jwt.prefix}") String prefix,
            @Value("${security.jwt.header}") String header) {

        this.expiration = expiration;
        this.secret = secret;
        this.prefix = prefix;
        this.header = header;

        instance = this;
    }

    public static TokenAuthenticationService getInstance() {

        return instance;
    }

    public void addAuthentication(HttpServletResponse response, String username) {
        String jwt = Jwts.builder()
                .setSubject(username)
                .setExpiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();

        response.addHeader(header, String.format("%s %s", prefix, jwt));
    }

    public Authentication getAuthentication(HttpServletRequest request) {
        String token = request.getHeader(header);

        if (Objects.isNull(token))
            return null;

        String username = Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token.replace(prefix, ""))
                .getBody()
                .getSubject();

        return username != null ?
                new UsernamePasswordAuthenticationToken(username, null, Collections.emptyList()) :
                null;
    }
}
