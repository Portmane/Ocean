package com.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity                                                          /* Tells the Spring Security that this is
                                                                            * a WEB security configuration. */
public class WebSecurityManager extends WebSecurityConfigurerAdapter {      /* Extends class which stores method for
                                                                            * direct work with AuthenticationManagerBuilder
                                                                            * class.*/
    @Override
    public void configure(AuthenticationManagerBuilder authenticationManagerBuilder)
            throws Exception {
        // Set your configuration on the authenticationManagerBuilder instance.
        authenticationManagerBuilder.inMemoryAuthentication()       // Set method of authentication.
                .withUser("q")                              // Sets username for the specific method of authentication.
                .password("1")                                       // Sets password for the specific method of authentication.
                .roles("USER");                                      // Sets roll for the specific method of authentication.
    }


    @Bean
    public PasswordEncoder getPasswordEncoder() {           // Encodes the password.
        return NoOpPasswordEncoder.getInstance();           /* Mode, by witch password is leaved as readable.
                                                            * DON'T USE IN THE ENTERPRISE.*/
    }
}
