package ru.netology.Spring.Security.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

// класс содержит настройки безопасности
@Configuration
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
public class SecurityConfig {

    // Bean для шифрования паролей
    @Bean
    PasswordEncoder encoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean
    UserDetailsService userDetailsService() {
        // пользователь с ролью "ADMIN"
        UserDetails admin = User.withUsername("admin")
                .password(encoder().encode("qwerty"))
                .roles("READ", "WRITE", "DELETE")
                .build();

        // пользователь с ролью "USER"
        UserDetails user1 = User.withUsername("Sergey")
                .password(encoder().encode("pass"))
                .roles("READ")
                .build();

        //
        UserDetails user2 = User.withUsername("Nikita")
                .password(encoder().encode("qaz"))
                .authorities("WRITE", "DELETE")
                .build();

        return new InMemoryUserDetailsManager(admin, user1, user2);
    }

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/").permitAll() //доступ к главной странице разрешен всем
                        .requestMatchers("/admin/**").hasRole("ADMIN") //доступ к /admin/** только для ADMIN
                        .anyRequest().authenticated() // остальные запросы требуют авторизации
                )
                .formLogin(Customizer.withDefaults()) //стандартная форма логина
                .logout(Customizer.withDefaults()); //стандартная форма логаута
        return http.build();
    }


}
