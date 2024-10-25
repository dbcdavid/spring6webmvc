package guru.classes.spring6restmvc.controllers;

import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;

import java.time.Instant;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.jwt;

public class SecConstants {
    public static final String USERNAME = "user1";
    public static final String PASSWORD = "password";
    public static final SecurityMockMvcRequestPostProcessors.JwtRequestPostProcessor jwtRequestPostProcessor =
            jwt().jwt(jwt ->{
                jwt.claims(claims -> {
                            //claims.put("scope", "message.read");
                            //claims.put("scope", "message.write");
                        })
                        .subject("messaging-client")
                        .notBefore(Instant.now().minusSeconds(5l));
            });
}
