package ee.mihkel.veebipood.config;

import ee.mihkel.veebipood.security.JwtFilter;
import jakarta.servlet.Filter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

@Configuration
public class SecurityConfig {

    @Autowired
    JwtFilter jwtFilter;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
                .cors(cors -> cors.configurationSource(corsConfigurationSource()))
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> auth
    //                .requestMatchers(HttpMethod.GET, "/orders").authenticated()
    //                .requestMatchers(HttpMethod.POST, "/products").authenticated()
    //                .requestMatchers(HttpMethod.DELETE, "/products/*").authenticated()
    //                .requestMatchers(HttpMethod.PUT, "/products").authenticated()
    //                .requestMatchers(HttpMethod.PATCH, "/product-active").authenticated()
                        .requestMatchers(HttpMethod.GET, "/public-products").permitAll()
                        .requestMatchers(HttpMethod.GET, "/categories").permitAll()
                        .requestMatchers(HttpMethod.POST, "/login").permitAll()
                        .requestMatchers(HttpMethod.POST, "/signup").permitAll()
                        .requestMatchers(HttpMethod.GET, "/products/*").permitAll()
                        .requestMatchers(HttpMethod.GET, "/supplier1").permitAll()
                        .requestMatchers(HttpMethod.GET, "/supplier2").permitAll()
                        .requestMatchers(HttpMethod.GET, "/supplier3").permitAll()
                        .requestMatchers(HttpMethod.GET, "/order-status").permitAll()
                        .requestMatchers(HttpMethod.GET, "/email").permitAll()
                        .requestMatchers(HttpMethod.POST, "/products").hasAuthority("ADMIN")
                        .requestMatchers(HttpMethod.POST, "/categories").hasAuthority("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/products").hasAuthority("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/categories").hasAuthority("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/persons").hasAuthority("SUPERADMIN")
                        .requestMatchers(HttpMethod.PATCH, "/person-admin").hasAuthority("SUPERADMIN")
                        .anyRequest().authenticated())
                .addFilterBefore(jwtFilter, BasicAuthenticationFilter.class);
        return http.build();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowedOrigins(List.of("http://localhost:5173")); // RestControllerist võin ära võtta @CrossOrigin
        config.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "PATCH"));
        config.setAllowedHeaders(List.of("Authorization", "Content-Type"));
        config.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);
        return source;
    }
}

// 401 --> nõuab kasutajanime ja parooli
// 403 --> üldisem veateade. tokeni küsimine
