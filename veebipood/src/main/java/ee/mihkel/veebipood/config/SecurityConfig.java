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

@Configuration
public class SecurityConfig {

    @Autowired
    JwtFilter jwtFilter;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
                .cors().and().headers().xssProtection().disable().and()
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
                        .anyRequest().authenticated())
                .addFilterBefore(jwtFilter, BasicAuthenticationFilter.class);
        return http.build();
    }
}

// 401 --> nõuab kasutajanime ja parooli
// 403 --> üldisem veateade. tokeni küsimine
