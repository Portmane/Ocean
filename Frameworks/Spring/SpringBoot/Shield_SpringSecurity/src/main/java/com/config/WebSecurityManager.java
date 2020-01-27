package com.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity                                                          /* Tells the Spring Security that this is
 * a WEB security configuration. */
public class WebSecurityManager extends WebSecurityConfigurerAdapter {      /* Extends class which stores method for
 * direct work with AuthenticationManagerBuilder
 * class and others.*/
    @Override
    public void configure(AuthenticationManagerBuilder authenticationManagerBuilder)
            throws Exception {
        // Set your configuration on the authenticationManagerBuilder instance.
        authenticationManagerBuilder.inMemoryAuthentication()       // Sets method of authentication.
                .withUser("q")                              // Sets username for the specific method of authentication.
                .password("1")                                       // Sets password for the specific method of authentication.
                .roles("USER")                                       // Sets roll for the specific method of authentication.
                .and()                                              /* Returns object with the same state as it has been
         * returned from inMemoryAuthentication() method before.*/
                .withUser("w")                              // Sets username for the specific method of authentication.
                .password("1")                                       // Sets password for the specific method of authentication.
                .roles("ADMIN")
                .and()                                              /* Returns object with the same state as it has been.
         * returned from inMemoryAuthentication() method before.*/
                .withUser("KAKJE_YA_OHUENEN")                              // Sets username for the specific method of authentication.
                .password("1")                                       // Sets password for the specific method of authentication.
                .roles("USER");                                      // Sets roll for the specific method of authentication.
    }


    @Bean
    public PasswordEncoder getPasswordEncoder() {           // Encodes the password.
        return NoOpPasswordEncoder.getInstance();           /* Mode, by witch password is leaved as readable.
         * DON'T USE IN THE ENTERPRISE.*/
    }

    @Override
    protected void configure(HttpSecurity http)
            throws Exception {
        http.authorizeRequests()
                .antMatchers("/admin")           // Sets paths for work with.
                .hasRole("ADMIN")                           /* Lets permission to specific API's only to
                                                            * people with indicated ROLE. */
                .antMatchers("/user")               // Sets paths for work with.
                .hasAnyRole("ADMIN", "USER")            /* Lets permission to specific API's only to
                                                                * people with indicated ROLE. */
                .antMatchers("/")                   // Sets paths for work with.
                .permitAll()                                /* Lets all people to see this
                                                            * API's without the specific role. */
                .antMatchers("/**").hasRole("ADMIN")
                .and()
                .formLogin();
    }
}
