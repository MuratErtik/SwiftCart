package com.project.ecommerce.config;

import java.util.Collection;
import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import jakarta.servlet.http.HttpServletRequest;

@Configuration
@EnableWebSecurity
public class AppConfig {

    //@Bean metotlarını uygulama başlatılırken çalıştırır.

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        /*
         * Aşağıdaki yapılandırma, bir kullanıcının aynı anda yalnızca tek bir
         * oturumunun doğrulanmasını nasıl zorunlu kılabileceğini göstermektedir.
         * Eğer "user" kullanıcı adıyla oturum açmış bir kullanıcı çıkış yapmadan tekrar
         * oturum açmaya çalışırsa, ilk oturum zorla sonlandırılacak
         * ve "/login?expired" URL'sine yönlendirilecektir.
         */
        http.sessionManagement(management -> management.sessionCreationPolicy(
                SessionCreationPolicy.STATELESS)).authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/api/**").authenticated()
                        .requestMatchers("api/products/*/review").permitAll()
                        .anyRequest().permitAll()

        )
                .addFilterBefore(new JwtTokenValidator(), BasicAuthenticationFilter.class)
                .csrf(csrf -> csrf.disable())
                .cors(cors -> cors.configurationSource(corsConfigurationSource()));

        return http.build();
    }

    @Bean
    private CorsConfigurationSource corsConfigurationSource() {
        return new CorsConfigurationSource() {
            @Override
            public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {
                CorsConfiguration cfg = new CorsConfiguration();
                cfg.setAllowedOrigins(Collections.singletonList("*"));
                cfg.setAllowedMethods(Collections.singletonList("*"));
                cfg.setAllowedHeaders(Collections.singletonList("*"));
                cfg.setAllowCredentials(true);
                cfg.setExposedHeaders(Collections.singletonList("Authorization"));
                cfg.setMaxAge(3600L);
                //Tarayıcı bu CORS yapılandırmasını 1 saat boyunca önbelleğe alır.
                return cfg;
            }
        };
    }
}
