package com.crio.stayEase.config;

import com.crio.stayEase.constants.Role;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

import static com.crio.stayEase.config.PathConstants.*;

@Component
@AllArgsConstructor
public class SecurityConfig {

    private final JwtRequestFilter jwtRequestFilter;
    private final UserDetailsService userDetailsService;
    private final String ADMIN = Role.ADMIN.toString();
    private final String MANAGER = Role.HOTEL_MANAGER.toString();
    private final String CUSTOMER = Role.CUSTOMER.toString();

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(HttpMethod.POST, REGISTER_USER, LOGIN_USER).permitAll()
                        // Get all hotels
                        .requestMatchers(HttpMethod.GET, HOTEL_BASE_PATH).permitAll()
                        // Create Hotel
                        .requestMatchers(HttpMethod.POST, HOTEL_BASE_PATH).hasRole(ADMIN)
                        // Delete Hotel
                        .requestMatchers(HttpMethod.DELETE, HOTEL_BASE_PATH + "/*").hasRole(ADMIN)
                        // Update Hotel
                        .requestMatchers(HttpMethod.PUT, HOTEL_BASE_PATH + "/*").hasRole(MANAGER)
                        // Cancel Booking
                        .requestMatchers(HttpMethod.DELETE, BOOKING_BASE_PATH + "/*").hasRole(MANAGER)
                        // Get Booking by ID
                        .requestMatchers(HttpMethod.GET, BOOKING_BASE_PATH + "/*").hasRole(MANAGER)
                        // Book Room
                        .requestMatchers(HttpMethod.POST, BOOKING_BASE_PATH + "/hotels/*/book").hasRole(CUSTOMER)
                        // All Bookings of a user
                        .requestMatchers(HttpMethod.GET, BOOKING_BASE_PATH + "/customer/*").hasRole(CUSTOMER)
                        .anyRequest().denyAll()
                )
                .exceptionHandling(exceptionHandling -> exceptionHandling
                        .accessDeniedHandler(new CustomAccessDeniedHandler())
                        .authenticationEntryPoint(new CustomAuthenticationEntryPoint())
                )
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Autowired
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
    }

    @Bean
    public BCryptPasswordEncoder getBCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
