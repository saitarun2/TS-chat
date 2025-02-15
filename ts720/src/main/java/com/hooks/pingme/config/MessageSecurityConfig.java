package com.hooks.pingme.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class MessageSecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity security) throws Exception{

        return security.csrf(customize-> customize.disable())
                .authorizeHttpRequests(request -> request.anyRequest().permitAll()).build();
//                .formLogin(Customizer.withDefaults())
//                .httpBasic(Customizer.withDefaults()).build();


    }




}
