package com.example.springBootEcommerce.config;

import com.okta.spring.boot.oauth.Okta;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
@Configuration
public class SeccurityConfiguration extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests().antMatchers("api/orders/**").authenticated().and().oauth2ResourceServer().jwt();
        http.cors();
        http.csrf().disable();
        Okta.configureResourceServer401ResponseBody(http);


    }
}