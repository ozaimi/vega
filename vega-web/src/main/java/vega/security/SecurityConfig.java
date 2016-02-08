package vega.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
//@EnableWebMvcSecurity

public class SecurityConfig extends WebSecurityConfigurerAdapter  {




    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http
                .addFilterBefore(new AuthenticationFilter(), BasicAuthenticationFilter.class)
                .antMatcher("/**")
                .authenticationProvider(new VegaAuthenticationProvider())
                .authorizeRequests().anyRequest().fullyAuthenticated()
               .and()
               .anonymous().disable();

       /* http
                .authorizeRequests()
                .antMatchers("/resources/**").permitAll()
                .antMatchers("/login").permitAll();*/

    }

    @Override
    public void configure(WebSecurity webSecurity) throws Exception {

       webSecurity
                .ignoring()
                // All of Spring Security will ignore the requests
                .antMatchers("/MarketData/getAll");
    }



}