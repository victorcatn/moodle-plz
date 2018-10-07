package co.edu.unal.vsacode.moodleplz.config;

import co.edu.unal.vsacode.moodleplz.services.MongoStaffMemberDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/")
@CrossOrigin(origins = "*", maxAge = 3600, allowedHeaders = {"x-auth-token", "x-requested-with", "x-xsrf-token",
        "Authorization"})

@Configuration
@EnableConfigurationProperties
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    MongoStaffMemberDetailsService staffMemberDetailsService;

    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http
            .cors().and()
            .csrf().disable()
            .authorizeRequests().antMatchers("/login").permitAll().anyRequest().authenticated()
            .and().httpBasic()
            .and().sessionManagement().disable();
        //http.cors().and().authorizeRequests().anyRequest().authenticated();
        /*http
                .httpBasic()
                .and()
                .authorizeRequests()
                .antMatchers("/index.html", "/", "/home", "/login").permitAll()
                .anyRequest().authenticated()
                .and().csrf()
                .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse());*/
    }

    @Override
    protected void configure(AuthenticationManagerBuilder builder) throws Exception{
        builder.userDetailsService(staffMemberDetailsService);
    }

    /*@Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }*/
}

