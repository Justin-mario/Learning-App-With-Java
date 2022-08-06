package com.learningapp.security.config;

import com.learningapp.security.authuser.AuthUserImpl;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.SignatureException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.annotation.Resource;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;

import static com.learningapp.security.config.SecurityConstants.HEADER_STRING;
import static com.learningapp.security.config.SecurityConstants.TOKEN_PREFIX;

@Slf4j
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

        @Resource(name = "authUser")
        private AuthUserImpl applicationUserService;

        TokenProvider jwtTokenUtil;

        @Override
        protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException
        {
            String header = request.getHeader ( HEADER_STRING );
            String username = null;
            String authToken = null;
            if (header != null && header.startsWith ( TOKEN_PREFIX )) {
                authToken = header.replace ( TOKEN_PREFIX, "" );
                try {
                    username = jwtTokenUtil.getUsernameFromToken ( authToken );
                    log.info ( authToken + "from token" );
                } catch (IllegalArgumentException e) {
                    logger.error ( "An error occurred while fetching Username from Token", e );
                } catch (ExpiredJwtException e) {
                    logger.warn ( "The token has expired", e );
                } catch (SignatureException e) {
                    logger.error ( "Authentication Failed. Username or Password not valid." );
                }
            } else {
                logger.warn ( "Couldn't find bearer string, header will be ignored" );
            }
            if (username != null && SecurityContextHolder.getContext ().getAuthentication () == null) {

                UserDetails userDetails = applicationUserService.loadUserByUsername ( username );
                log.info ( Arrays.toString ( userDetails.getAuthorities ().toArray () ) + "  we loaded from db" );
                if (jwtTokenUtil.validateToken ( authToken, userDetails )) {
                    UsernamePasswordAuthenticationToken authentication = jwtTokenUtil.getAuthenticationToken ( authToken, SecurityContextHolder.getContext ().getAuthentication (), userDetails );
                    authentication.setDetails ( new WebAuthenticationDetailsSource ().buildDetails ( request ) );
                    logger.info ( "authenticated user " + username + ", setting security context" );
                    SecurityContextHolder.getContext ().setAuthentication ( authentication );

                }
            }

            chain.doFilter ( request, response );
        }

}
