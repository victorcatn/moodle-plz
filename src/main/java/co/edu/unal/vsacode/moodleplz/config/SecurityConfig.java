package co.edu.unal.vsacode.moodleplz.config;

import co.edu.unal.vsacode.moodleplz.services.MongoStaffMemberDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

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
            .authorizeRequests().anyRequest().authenticated()
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
}

