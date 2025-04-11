/*
package com.fiap.hospitalar.historico.config.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/graphql").hasAnyRole("MEDICO", "ENFERMEIRO") // Apenas médicos e enfermeiros
                        .requestMatchers("/historico/**").hasAnyRole("MEDICO", "ENFERMEIRO") // Acesso ao histórico
                        .requestMatchers("/historico/paciente/**").hasRole("PACIENTE") // Paciente acessa seu próprio histórico
                        .anyRequest().authenticated() // Qualquer outra requisição deve ser autenticada
                )
                .httpBasic(withDefaults()); // Habilita autenticação básica

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(); // Usar BCrypt para codificação de senhas
    }

    @Bean
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("medico").password(passwordEncoder().encode("senhaMedico")).roles("MEDICO")
                .and()
                .withUser("enfermeiro").password(passwordEncoder().encode("senhaEnfermeiro")).roles("ENFERMEIRO")
                .and()
                .withUser("paciente").password(passwordEncoder().encode("senhaPaciente")).roles("PACIENTE");
    }
}*/
