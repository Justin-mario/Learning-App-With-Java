package com.learningapp.security.config;

import com.learningapp.data.data_enum.Role;
import com.learningapp.data.entity.LearningParty;
import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

import static com.learningapp.security.config.SecurityConstants.*;

@Component
@Slf4j
public class TokenProvider implements Serializable {

    public String getUsernameFromToken(String token) {
        return getClaimFromToken(token, Claims::getSubject);
    }

    public Date getExpirationDateFromToken(String token) {
        return getClaimFromToken(token, Claims::getExpiration);
    }

    public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }

    private Claims getAllClaimsFromToken(String token) {
        return Jwts.parser()
                .setSigningKey(SECRET)
                .parseClaimsJws(token)
                .getBody();
    }

    private Boolean isTokenExpired(String token) {
        final Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }

    public String generateToken(Authentication authentication, LearningParty user) {
        return getToken(authentication, user, EXPIRATION_TIME);
    }

    public String generateRefreshToken(Authentication authentication, LearningParty user) {
        return getToken(authentication, user, EXPIRATION_TIME_FOR_REFRESH_TOKEN);
    }

    private String getToken(Authentication authentication, LearningParty user, long expirationTimeForRefreshToken) {
        String userRole = "";
        Map<Long, Role> userRoleMap = new HashMap<> ();
        user.getRoles().forEach(role ->userRoleMap.put(role.getId(),role.getRole()));

        if (userRoleMap.containsValue(Role.INSTRUCTOR)){
            userRole = "instructor";
        }
        else if(userRoleMap.containsValue( Role.ADMIN)){
            userRole = "admin";
        }
        else if(userRoleMap.containsValue( Role.SUPER_ADMIN)){
            userRole = "superAdmin";
        }

        else if(userRoleMap.containsValue( Role.STUDENT)){
            userRole = "student";
        }

        String authorities = authentication.getAuthorities().stream()
                .map( GrantedAuthority::getAuthority)
                .collect( Collectors.joining(","));

        Map<String, Object> claims = new HashMap<>();
        claims.put("id",user.getId());
        claims.put("email",user.getEmail());
        claims.put("roles",user.getRoles());
        claims.put("isEnabled",user.getIsEnabled ());
        claims.put("userRole",userRole);

        return Jwts.builder()
                .setSubject(authentication.getName())
                .claim(ROLES_KEY, authorities)
                .setClaims(claims)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + expirationTimeForRefreshToken))
                .signWith( SignatureAlgorithm.HS256, SECRET)
                .setSubject(user.getEmail())
                .setIssuer(authentication.getName())
                .compact();
    }

    public Boolean validateToken(String token, UserDetails userDetails) {
        final String username = getUsernameFromToken(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    public UsernamePasswordAuthenticationToken getAuthenticationToken(final String token, final Authentication existingAuth, final UserDetails userDetails) {
        final JwtParser jwtParser = Jwts.parser().setSigningKey(SECRET);

        final Jws<Claims> claimsJws = jwtParser.parseClaimsJws(token);

        final Claims claims = claimsJws.getBody();


        final Collection<? extends GrantedAuthority> authorities = userDetails.getAuthorities();
        log.info( Arrays.stream( authorities.toArray () ).map( Object::toString ).collect(Collectors.toList()) +" the authority");
        return new UsernamePasswordAuthenticationToken(userDetails, "", authorities);
//                Arrays.stream(claims.get(ROLES_KEY).toString().split(","))
//                        .map(SimpleGrantedAuthority::new)
//                        .collect(Collectors.toList());
//        log.info( Arrays.stream(authorities.stream().toArray()).map( f -> f.toString()).collect(Collectors.toList()) +" the authority");
//        return new UsernamePasswordAuthenticationToken(userDetails, "", authorities);
    }

}
