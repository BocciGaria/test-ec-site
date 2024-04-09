package com.boccigaria.testecsite.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebSecurity
public class MvcConfig implements WebMvcConfigurer {

    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/home").setViewName("home");
        registry.addViewController("/").setViewName("home");
        registry.addViewController("/login").setViewName("login");
    }

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(
                requests -> requests.requestMatchers(
                    "/error",
                    "/test",
                    "/catalog",
                    "/catalog/**",
                    "/cart",
                    "/cart/**",
                    "/order",
                    "/order/**"
                ).permitAll().anyRequest().authenticated()
            )
            .formLogin(form -> form.loginPage("/login").permitAll())
            .logout(logout -> logout.permitAll());
        return http.build();
    }

    @Bean
    UserDetailsService userDetailsService() {
        UserDetails user =
            User.withUsername("user")
                .password("{bcrypt}$2a$10$T0fY3vkw2VPCiTSSDJ2OzOxTKL5RAx3guhZ097bgYiGhCbrE4djtS")
                .roles("USER")
                .build();
        return new InMemoryUserDetailsManager(user);
    }
}
