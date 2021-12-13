package com.example.menu.oauth2;

import com.example.menu.entities.UserManager;
import com.example.menu.entities.UserManagerMember;
import com.example.menu.service.UserDetailServiceImpl;
import com.example.menu.util.EncoderUtil;
import io.jsonwebtoken.*;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Base64;
import java.util.Date;

@Log4j2
@Component
public class TokenService {
    @Value("${jwt.secret}")
    private String secretKey;

    @Value("${jwt.monthValidity}")
    private int monthValidity;

    @Autowired
    private UserDetailServiceImpl userDetailService;

    @Autowired
    private EncoderUtil encoderUtil;

    @Autowired
    private UserDetailsService customUserDetailsService;

    @PostConstruct
    protected void init() {
        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
    }

    public String createToken(UserManager userManager) {
        LocalDateTime today = LocalDateTime.now();
        LocalDateTime sixMonthDay = today.plusMonths(monthValidity);
        Date validity = Date.from(sixMonthDay.atZone(ZoneId.systemDefault()).toInstant());
        return Jwts.builder().setSubject(userManager.getUserName())
                .claim("NAME", userManager.getUserName()).signWith(SignatureAlgorithm.HS512, secretKey).setExpiration(validity)
                .setIssuer("nsc").compact();
    }

    public String createToken2(UserManagerMember userManagerMember) { // TEST
        LocalDateTime today = LocalDateTime.now(); // Test
        LocalDateTime sixMonthDay = today.plusMonths(monthValidity); // TEST
        Date validity = Date.from(sixMonthDay.atZone(ZoneId.systemDefault()).toInstant()); // TEST
        return Jwts.builder().setSubject(userManagerMember.getMemberName()) // TEST
                .claim("NAME", userManagerMember.getMemberName()).signWith(SignatureAlgorithm.HS512, secretKey).setExpiration(validity)
                .setIssuer("nsc").compact();
    } // TEST

    public String getUserId(String token) {
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().getSubject();
    }

    public Authentication getAuthentication(String token) {
        UserDetails userDetails = customUserDetailsService.loadUserByUsername(getUserId(token));
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    public boolean validateToken(String token) {
        try {
            Jws<Claims> claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
            return (claims.getBody().getExpiration().before(new Date())) ? false : true;
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }

    public String getTokenValue(String authHeaderValue) {
        return (StringUtils.hasText(authHeaderValue) && authHeaderValue.startsWith("Bearer ")) ? authHeaderValue.substring(7, authHeaderValue.length()) : null;
    }

}

