package com.example.demorest.config;

import com.example.demorest.filters.JWTAuthFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(
        prePostEnabled = true,
        securedEnabled = true,
        jsr250Enabled = true
)
public class SecurityConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http, JWTAuthFilter authFilter) throws Exception {
        http.csrf().disable(); // we disable this to avoid csrf errors
        http.addFilterBefore(authFilter, UsernamePasswordAuthenticationFilter.class);
        http.sessionManagement().sessionCreationPolicy((SessionCreationPolicy.STATELESS));
        http.authorizeRequests()
//                .antMatchers("/security/test/all").permitAll()
//                .antMatchers("/security/test/nobody").denyAll()
//                .antMatchers("/security/test/connected").authenticated()
//                .antMatchers("/security/test/not-connected").anonymous()
//                .antMatchers("/security/test/role/user").hasRole("USER")
//                .antMatchers("/security/test/role/admin").hasRole("ADMIN")
//                .antMatchers("/security/test/role/any").hasAnyRole("USER", "ADMIN")
//                .antMatchers("/security/test/authority/read").hasAuthority("ROLE_USER")
//                .antMatchers("/security/test/authority/any").hasAnyAuthority("ROLE_USER", "READ", "WRITE")
                .antMatchers("/swagger-ui/**").permitAll()
                .antMatchers("/v3/**").permitAll()
//                .antMatchers("/reservations/check-date").permitAll()
//                .antMatchers(HttpMethod.DELETE, "/**").hasRole("ADMIN")
//                .antMatchers("/user/**").permitAll()
                .anyRequest().permitAll();
        return http.build();
    }

    // LES DROITS:
    // - permitAll : tous les visiteurs (connect??s ou pas)
    // - denyAll : personne
    // - authenticated : connect??
    // - anonymous : pas connect??
    // - hasRole : poss??de le r??le particulier (un r??le est une autorit?? commencant par ROLE_)
    // - hasAnyRole : poss??de au moins un des r??les mentionn??s
    // - hasAuthority : poss??de l'authorit?? particulier
    // - hasAnyAuthorities : poss??de au moins une des authorit??s mentionn??s
    // - not(): methode avant un droit donn??e pour un chemin pour obtenir l'oppos??
    // ROLES POSSIBLES:
    // - ADMIN
    // - USER
    // AUTHORITES POSSIBLES:
    // - RECUPERER
    // - MODIFIER
    // LIAISONS:
    // - ADMIN: RECUPERER et MODIFIER et ROLE_ADMIN
    // - USER: RECUPERER et ROLE_USER
    // je peux utiliser:
    // - ? : joker pour de 0 ?? 1 caract??re
    // - * : joker pour un segment de 0 ?? N caract??res
    // - **: joker pour de 0 ?? N segments
    // - {pathVar:regex}: pattern regex pour un segment

    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }
}