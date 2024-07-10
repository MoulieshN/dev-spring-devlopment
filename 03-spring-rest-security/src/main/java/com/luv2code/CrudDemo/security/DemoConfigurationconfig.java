package com.luv2code.CrudDemo.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
public class DemoConfigurationconfig {


    //  The JdbcUserDetailsManager will use the default table names (users and authorities) and default SQL queries to interact with the database.
    //  If your database schema does not match these defaults, you will encounter errors.

    // The JdbcUserDetailsManager is a UserDetailsService that uses a JDBC DataSource to load user information.
    // Now, the rest security will allow the user to access the api based on the roles which are fetched from the database
    @Bean
    public UserDetailsManager userDetailsManager(DataSource dataSource){
//        return new JdbcUserDetailsManager(dataSource); --> this is for default users and authorities table

        JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager(dataSource);

        // define the queries to use to fetch the user by username and the authorities for the user
        jdbcUserDetailsManager.setUsersByUsernameQuery("select user_id, pwd, active from members where user_id = ?");

        jdbcUserDetailsManager.setAuthoritiesByUsernameQuery("select user_id, role from roles where user_id = ?");

        return jdbcUserDetailsManager;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        // This way we can secure the API endpoints based on the roles
           http.authorizeHttpRequests(configure ->
                   configure
                           .requestMatchers(HttpMethod.GET, "/api/employees").hasRole("EMPLOYEE")
                           .requestMatchers(HttpMethod.GET, "/api/employees/**").hasRole("EMPLOYEE")
                   );

         http.httpBasic(Customizer.withDefaults());

         // disable CSRF
        // Not required for stateless APIs
         http.csrf(AbstractHttpConfigurer::disable);

         return http.build();
    }

    /*
    @Bean
    public InMemoryUserDetailsManager inMemoryUserDetailsManager() {

        //Class NoOpPasswordEncoder
        //
        //Useful for testing where working with plain text passwords may be preferred.

        // Now no one can use the api unless the user is authenticated
        // The user is authenticated by the username and password
        // Spring security will take care of the rest

        // {noop} is a prefix that tells Spring that the password is not encoded and stores it as a plain text

        UserDetails user1 = User.builder()
                .username("john")
                .password("{noop}test123")
                .roles("EMPLOYEE")
                .build();

        UserDetails user2 = User.builder()
                .username("mary")
                .password("{noop}test123")
                .roles("MANAGER")
                .build();

        UserDetails user3 = User.builder()
                .username("susan")
                .password("{noop}test123")
                .roles("ADMIN", "MANAGER")
                .build();


        // InMemoryUserDetailsManager is a simple in-memory implementation of UserDetailsService that allows you to create users and roles in memory.
        return new InMemoryUserDetailsManager(user1, user2, user3);
    }
     */
}
