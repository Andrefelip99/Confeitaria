package com.confeitaria.confeitaria;



import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableMethodSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable()) 
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/fluxo-caixa/**", "/lucro-liquido/**").hasRole("ADMIN")
                .requestMatchers("/vendas/**", "/produtos/**", "/custos-variaveis/**", "/despesas/**").hasAnyRole("ADMIN", "FUNCIONARIO")
                .anyRequest().authenticated()
            )
            .httpBasic(Customizer.withDefaults()); 

        return http.build();
    }
@Bean
public UserDetailsService userDetailsService(PasswordEncoder passwordEncoder) {
    UserDetails admin = User.withUsername("admin")
        .password(passwordEncoder.encode("12345")) 
        .roles("ADMIN")
        .build();

    UserDetails funcionario = User.withUsername("func")
        .password(passwordEncoder.encode("12345"))
        .roles("FUNCIONARIO")
        .build();

    return new InMemoryUserDetailsManager(admin, funcionario);
}



@Bean
public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
}


    
}
