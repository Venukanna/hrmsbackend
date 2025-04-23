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
                registry.addMapping("/api/**")  // Apply CORS policy to all /api/** endpoints
                        .allowedOrigins("https://hrmanagementsystement.netlify.app")  // Allow requests from your frontend
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")  // Allow specific HTTP methods
                        .allowedHeaders("*")  // Allow all headers
                        .allowCredentials(true)  // Allow sending credentials (cookies, authorization headers)
                        .maxAge(3600);  // Cache preflight response for 1 hour
            }
        };
    }
}
