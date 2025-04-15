//package config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.web.cors.CorsConfiguration;
//
//@Configuration
//@EnableWebSecurity
//public class SecurityConfig {
//
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http
//                .cors(cors -> cors.configurationSource(request -> {
//                    CorsConfiguration config = new CorsConfiguration();
//                    config.addAllowedOrigin("http://localhost:5174"); // Frontend URL
//                    config.addAllowedMethod("*"); // Allow all HTTP methods
//                    config.addAllowedHeader("*"); // Allow all headers
//                    config.setAllowCredentials(true); // Allow cookies if needed
//                    return config;
//                }))
//                .csrf(csrf -> csrf.disable()) // Disable CSRF for REST APIs
//                .authorizeHttpRequests(auth -> auth
//                        .anyRequest().permitAll() // Allow all requests without authentication
//                );
//
//        return http.build();
//    }
//}

