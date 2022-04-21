package com.kodilla.ecommercee;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@SpringBootApplication
public class EcommerceeApplication {

    public static void main(String[] args) {
        SpringApplication.run(EcommerceeApplication.class, args);
    }

        @EnableWebSecurity
        @Configuration
        class WebSecurityConfig extends WebSecurityConfigurerAdapter {

            @Override
            protected void configure(HttpSecurity http) throws Exception {
                http.csrf().disable()
                        .addFilterAfter(new JWTAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class)
                        .authorizeRequests()
                        .antMatchers(HttpMethod.POST, "/v1/users/user").permitAll()
                        .antMatchers(HttpMethod.POST, "/v1/carts/new/{userId}").permitAll()
                        .antMatchers(HttpMethod.GET, "/v1/carts/{cartId}").permitAll()
                        .antMatchers(HttpMethod.POST, "/v1/carts/addProduct/{cartId}/{productId}").permitAll()
                        .antMatchers(HttpMethod.DELETE, "/v1/carts/deleteProduct/{cartId}/{productId}").permitAll()
                        .antMatchers(HttpMethod.POST, "/v1/carts/order/{cartId}").permitAll()
                        .anyRequest().authenticated();
            }
        }
    }





