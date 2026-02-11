package fr.eni.ludotheque.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
    @Autowired
    private UserDetailsService userDetailsService;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception
    {
        http
                .csrf(csrf ->csrf.disable())
                .authorizeHttpRequests((requests) -> requests
                        //Jeu
                        .requestMatchers(HttpMethod.GET,"/api/jeux").permitAll()
                        //.requestMatchers(HttpMethod.GET, "/api/jeux").hasAnyRole("USER")
                        .requestMatchers(HttpMethod.POST,"/api/jeux").hasRole("EMPLOYE")
                        //Clients
                        .requestMatchers(HttpMethod.GET, "/api/clients").hasRole("EMPLOYE")
                        .requestMatchers(HttpMethod.POST, "/api/clients").hasRole("EMPLOYE")
                        .requestMatchers(HttpMethod.GET, "/api/clients/**").hasRole("EMPLOYE")
                        .requestMatchers(HttpMethod.GET,"api/clients/client/").hasRole("EMPLOYE")
                        .requestMatchers(HttpMethod.PUT,"api/clients/**").hasRole("EMPLOYE")
                        .requestMatchers(HttpMethod.PATCH,"/api/clients/**").hasRole("EMPLOYE")
                        .requestMatchers(HttpMethod.DELETE,"/api/clients/**").hasRole("EMPLOYE")

                        //Login
                        .requestMatchers("/login").permitAll()

                        .anyRequest().authenticated()
                )
                .httpBasic(Customizer.withDefaults())
                .userDetailsService(userDetailsService);
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        //return new BCryptPasswordEncoder();
        return NoOpPasswordEncoder.getInstance(); //sans gestion du chiffrement
        //return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }
}
