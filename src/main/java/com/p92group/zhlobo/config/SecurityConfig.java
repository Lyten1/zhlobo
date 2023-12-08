package com.p92group.zhlobo.config;

import com.p92group.zhlobo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.HttpMethod.POST;

@Configuration
@EnableMethodSecurity
public class SecurityConfig {
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private UserService userService;

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests((authorize) -> authorize
                        .requestMatchers(GET, "/react/**", "/moderator/**", "/login", "/register", "/").permitAll()
                        .requestMatchers(POST, "/react/**"  , "/moderator/**", "/login", "/register", "/").permitAll()
                        .requestMatchers("/client/**").hasRole("CUSTOMER")
                        .requestMatchers("/seller/**").hasRole("SELLER")
                        .anyRequest().authenticated())
                .formLogin(login -> login
                        .loginPage("/login")
                        .loginProcessingUrl("/login/process").permitAll()
                        .defaultSuccessUrl("/login/redirect", true).permitAll())
                .logout(logout -> logout
                        .logoutUrl("/logout")  // Specify the URL for logout
                        .logoutSuccessUrl("/login?logout") // Redirect after successful logout
                        .permitAll()) // Allow all users to access the logout URL
        ;//.httpBasic(Customizer.withDefaults());

        return http.build();
    }


//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http
//                .authorizeRequests()
//                .antMatchers("/public/**").permitAll() // Публичные ресурсы
//                .antMatchers("/private/**").authenticated() // Защищенные ресурсы
//                .and()
//                .formLogin()
//                .loginPage("/login") // Страница входа
//                .defaultSuccessURL("/dashboard") // Страница после успешного входа
//                .permitAll()
//                .and()
//                .logout()
//                .logoutUrl("/logout") // URL для выхода
//                .logoutSuccessUrl("/login?logout") // Страница после выхода
//                .permitAll();
//    }

    @Bean
    public AuthenticationProvider daoauthenticationProvider() {
        System.out.println("Заходим сюда");
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setPasswordEncoder(bCryptPasswordEncoder);
        daoAuthenticationProvider.setUserDetailsService(userService);
        return daoAuthenticationProvider;
    }
}
