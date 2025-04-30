/*
Name: Daniela Luna
Purpose: The class overrides Spring Security’s default behavior.
By default, Spring Security:
- Locks down everything
- Requires full authentication for every endpoint
- Enables CSRF protection (which can break APIs without setup)
So without a SecurityConfig, routes like /api/login or /api/register would return 401/403 or reject requests due to CSRF.
*/

package csc450.BackEnd;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable) // For now, disable CSRF for frontend dev
                .cors(Customizer.withDefaults())        // Enable CORS
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/register", "/api/login").permitAll()
                        .anyRequest().authenticated()
                )
                .httpBasic(AbstractHttpConfigurer::disable) // 🔥 disables HTTP Basic Auth
                .formLogin(AbstractHttpConfigurer::disable); // 🔥 disables form-based login

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}


