package com.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity                                                          /* Tells the Spring Security that this is
                                                                            * a WEB security configuration. */
public class WebSecurityManager extends WebSecurityConfigurerAdapter {      /* Extends class which stores method for
                                                                             * direct work with AuthenticationManagerBuilder
                                                                             * class and others.*/
    private final UserDetailsService userDetailsService;

    public WebSecurityManager(@Qualifier("usrService") UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    // Variable for paragraph 3.
//    final private
//    DataSource dataSource;                                                  // Gets dependency of DataSource instance.
//
//    public WebSecurityManager(DataSource dataSource) {
//        this.dataSource = dataSource;
//    }

    @Override
    public void configure(AuthenticationManagerBuilder authenticationManagerBuilder)
            throws Exception {
        // Set your configuration on the authenticationManagerBuilder instance.

        /* Here I have representation of different authentication ways.
        * Uncommitted way which I use is casual authentication through UserDetailsService interface.
        * Ways of authentication represented in this class:
        *
        *
        * 1. Casual authentication: gives us ability to work with different datasource as: TXT.txt,
        * database, etc resources. It is achieved by right way of implementation of loadUserByUsername(String s)
        * method of UsernameNotFoundException interface.
        *
        *
        * 2. INnMemoryAuthentication - hardcode users for authentication.
        *
        *
        * 3. JDBC authentication. We have some variation abilities then using this method.
        * - If we use standard H2 database we can simple get dependency on dataSource as it is done with use of
        * dataSource variable and specify hardcode users with default schema specified.
        * I will mark this example as: JDBC with use of H2 database - 3-rd paragraph first chapter of the comment.
        *
        * - Also if we use another database as MySql, we firstly have to add dependence of this database in out pom
        * file and set connection properties in application.properties file in resource folder of the project.
        * After we have done this Spring will automatically detect dataSource dependence variable as our database source
        * and we can simple put this variable to dataSource() method as source for equation of credentials in process
        * of authentication. Also, if we use another type of schema for authentication and authorization we can simple
        * resolve this problem with use of usersByUsernameQuery() method for detection of the credentials source and
        * authoritiesByUsernameQuery() method for detection of authorities assigned to specific user source.
        * I will mark this example as: JDBC with use of MySQL, Postgres, etc database - 3-rd paragraph second chapter
        * of the comment.
        *
        * Soo far you can see couple of ways for different authentication technologies and ways of realization of them.*/



        // Main authentication realization. 1-st paragraph in a comment.
        authenticationManagerBuilder.userDetailsService(userDetailsService);            /* Sets with what database,
                                                                                        * txt, etc file we want to work.*/




        // InMemoryAuthentication way. 2-nd paragraph in a comment.

//        authenticationManagerBuilder.inMemoryAuthentication()       // Sets method of authentication.
//                .withUser("q")                                       // Sets username for the specific method of authentication.
//                .password("1")                                       // Sets password for the specific method of authentication.
//                .roles("USER")                                       // Sets roll for the specific method of authentication.
//                .and()                                              /* Returns object with the same state as it has been
//         * returned from inMemoryAuthentication() method before.*/
//                .withUser("w")                                       // Sets username for the specific method of authentication.
//                .password("1")                                       // Sets password for the specific method of authentication.
//                .roles("ADMIN")
//                .and()                                              /* Returns object with the same state as it has been.
//         * returned from inMemoryAuthentication() method before.*/
//                .withUser("KAKJE_YA_OHUENEN")                        // Sets username for the specific method of authentication.
//                .password("1")                                       // Sets password for the specific method of authentication.
//                .roles("USER");                                      // Sets roll for the specific method of authentication.



        // JDBC with use of H2 database - 3-rd paragraph first chapter of the comment.

//        authenticationManagerBuilder.jdbcAuthentication()           // Authentication with use of JDBC.
//                .dataSource(dataSource)                             /* Sets with what database we want to work. By next
//                                                                    * lines we can understand that we use H2 database. */
//                .withDefaultSchema()                                // Uses default schema for database DATA.
//                .withUser(                                          // Adds stater user.
//                        User.withUsername("a")
//                        .password("1")
//                        .roles("USER")
//                )
//                .withUser(                                          // Adds starter user
//                        User.withUsername("s")
//                                .password("1")
//                                .roles("ADMIN")
//                );



        /* JDBC with use of MySQL, Postgres, etc database - 3-rd paragraph second chapter
        * of the comment. */

//        authenticationManagerBuilder.jdbcAuthentication()           // Authentication with use of JDBC.
//                .dataSource(dataSource)                             /* Sets with what database we want to work. By next
//                                                                    * lines we can understand that we use not H2 database. */
//                .usersByUsernameQuery("select username, password, enabled " +   /* Says what columns to take from
//                                                                                * specific database table to evaluate them
//                                                                                * with UserDetails credentials.*/
//                        "from usr " +
//                        "where username = ?")
//                .authoritiesByUsernameQuery("select username, authority " +     /* Says what columns to take from
//                                                                                * specific database table to assign an authority. */
//                        "from authorities " +
//                        "where username = ?");
    }



    @Override
    protected void configure(HttpSecurity http)             // Getting access to HttpSecurity instance.
            throws Exception {
        http.authorizeRequests()
                .antMatchers("/admin")          // Sets paths for work with.
                .hasRole("ADMIN")                          /* Lets permission to specific APIs only to
                                                           * people with indicated ROLE. */

                .antMatchers("/user")           // Sets paths for work with.
                .hasAnyRole("ADMIN", "USER")        /* Lets permission to specific APIs only to
                                                           * people with indicated ROLE. */

                .antMatchers("/")               // Sets paths for work with.
                .permitAll()                                /* Lets all people to see this
                                                            * APIs without the specific role. */
                .and()
                .formLogin();
    }



    @Bean
    public PasswordEncoder getPasswordEncoder() {           // Encodes the password.
        return NoOpPasswordEncoder.getInstance();           /* Mode, by witch password is leaved as readable.
                                                            * DON'T USE IN THE ENTERPRISE.*/
    }
}
