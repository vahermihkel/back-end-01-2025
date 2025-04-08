package ee.mihkel.veebipood.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class JwtFilter extends BasicAuthenticationFilter {

    public JwtFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
//        System.out.println(request.getMethod());
//        System.out.println(request.getPathInfo());
//        System.out.println(request.getRequestURI());
//        System.out.println(response.getStatus());
//        System.out.println(response.getHeaderNames());
        String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String token = authHeader.replace("Bearer ", "");

            String superSecretKey = "7shkEZCBEW2ufZvrCiijn_o2eOMAzJaQX88Ej9TMm_s";

            SecretKey secretKey = Keys.hmacShaKeyFor(Decoders.BASE64URL.decode(superSecretKey));

            Claims payLoad = Jwts
                    .parser()
                    .verifyWith(secretKey)
                    .build()
                    .parseSignedClaims(token)
                    .getPayload();

            Long id = Long.parseLong(payLoad.get("id").toString());
            String email = payLoad.get("email").toString();
            String role = payLoad.get("role").toString();
            List<GrantedAuthority> authorities = new ArrayList<>();
            GrantedAuthority authority = new SimpleGrantedAuthority(role);
            authorities.add(authority);
            if (role.equals("SUPERADMIN")) {
                GrantedAuthority adminAuthority = new SimpleGrantedAuthority("ADMIN");
                authorities.add(adminAuthority);
            }

            // CUSTOMER
            // ADMIN -> POST products, categories, DELETE products, categories
            // SUPERADMIN -> GET persons, PATCH person-admin

            Authentication authentication = new UsernamePasswordAuthenticationToken(id, email,authorities);
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        super.doFilterInternal(request, response, chain);
    }
}
