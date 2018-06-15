package io.agileintelligence.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import io.agileintelligence.model.ApplicationUser;
import io.agileintelligence.model.JWTLoginSuccessResponse;
import io.agileintelligence.repository.ApplicationUserRepository;
import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jdk.nashorn.internal.parser.JSONParser;
import netscape.javascript.JSObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static io.agileintelligence.config.SecurityConstants.*;



public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {


    private AuthenticationManager authenticationManager;

    public JWTAuthenticationFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest req, HttpServletResponse res) throws AuthenticationException {
        try {
            ApplicationUser creds = new ObjectMapper().readValue(req.getInputStream(), ApplicationUser.class);

            return authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            creds.getEmail(),
                            creds.getPassword(),
                            new ArrayList<>()
                    )
            );

        }catch (IOException e){
            System.out.println("caught at the JWT filter");
            res.setStatus(400);
            throw  new RuntimeException(e);
        }
    }

    @Override
    public final void successfulAuthentication(HttpServletRequest req,
                                                      HttpServletResponse res,
                                                      FilterChain chain, Authentication auth) throws IOException, ServletException {


        Map<String, Object> claims = new HashMap<>();
        claims.put("id", ((ApplicationUser)auth.getPrincipal()).getId());
        claims.put("name", ((ApplicationUser)auth.getPrincipal()).getName());
        claims.put("avatar", ((ApplicationUser)auth.getPrincipal()).getAvatar());

        String token = Jwts.builder()
                .setSubject(((ApplicationUser) auth.getPrincipal()).getEmail()+((ApplicationUser) auth.getPrincipal()).getId()+((ApplicationUser) auth.getPrincipal()).getAvatar())
                .setClaims(claims)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+ EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS256, SECRET.getBytes())
                .compact();

        res.addHeader(HEADER_STRING, TOKEN_PREFIX +token);
        res.setContentType(token);
        String jsonToken = TOKEN_PREFIX+token;

        JWTLoginSuccessResponse success = new JWTLoginSuccessResponse(true, jsonToken);


        /**
         * https://stackoverflow.com/questions/19500332/spring-security-and-json-authentication
         * https://github.com/google/gson
         */
        String json = new Gson().toJson(success);
        res.setContentType("application/json");
        res.getWriter().print(json);
        res.setStatus(200);

        //new ResponseEntity<String>(token,HttpStatus.CREATED);

    }



}
