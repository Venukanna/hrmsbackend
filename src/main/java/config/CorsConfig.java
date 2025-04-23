import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig {
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/api/**")  // Allow /api/** endpoints
                        .allowedOrigins("https://hrmanagementsystement.netlify.app") // Netlify domain
                        .allowedMethods("GET", "POST", "PUT", "DELETE") // Allow GET, POST, PUT, DELETE methods
                        .allowedHeaders("*") // Allow all headers
                        .allowCredentials(true) // Allow cookies, authorization headers
                        .maxAge(3600); // Cache preflight response for 1 hour
            }
        };
    }
}
