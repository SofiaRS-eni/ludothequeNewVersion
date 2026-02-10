package fr.eni.ludotheque.security;

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
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception
    {
        http
                .csrf(csrf ->csrf.disable())
                .authorizeHttpRequests((requests) -> requests
                        //Jeu
                        .requestMatchers(HttpMethod.GET,"/api/jeux").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/jeux").hasAnyRole("USER")

                        //Clients
                        .requestMatchers(HttpMethod.GET, "/api/clients").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.POST, "/api/clients").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/api/clients/{id}").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET,"api/clients/client/").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT,"api/clients/{id}").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PATCH,"/api/clients/{id}").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE,"/api/clients/{id}").hasRole("ADMIN")

                        //Login
                        .requestMatchers("/login").permitAll()

                        .anyRequest().authenticated()
                )
                .httpBasic(Customizer.withDefaults());
        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails user =
                User.withDefaultPasswordEncoder()
                        .username("user")
                        .password("password")
                        .roles("USER")
                        .build();

        UserDetails admin =
                User.withDefaultPasswordEncoder()
                        .username("admin")
                        .password("admin")
                        .roles("ADMIN")
                        .build();

        return new InMemoryUserDetailsManager(user,admin);
    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        //return new BCryptPasswordEncoder();
        //return NoOpPasswordEncoder.getInstance(); //sans gestion du chiffrement
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }
}
