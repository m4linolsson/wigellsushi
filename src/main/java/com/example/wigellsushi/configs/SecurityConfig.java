package com.example.wigellsushi.configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests((auth) ->
                        auth.requestMatchers("/test").permitAll()
                                .anyRequest().permitAll());


        http
                .formLogin(Customizer.withDefaults())   //inloggningsformulär via webbläsaren
                .httpBasic(Customizer.withDefaults())   //via postman
                .logout((logout) -> logout.logoutSuccessUrl("/welcome")); //för att sätta upp vart man kommer om man loggar ut

        return http.build();

    }


    @Bean
    public UserDetailsService userDetailsService() {
//        UserDetails user = User.withDefaultPasswordEncoder()
        UserDetails user = User.withUsername("anna")
//                .username("anna")
                .password("")
                .roles("")
                .build();

//        UserDetails admin = User.withDefaultPasswordEncoder()
//                .username("")
//                .password("")
//                .roles("")
//                .build();

        return new InMemoryUserDetailsManager(user/*, admin*/);

    }
}