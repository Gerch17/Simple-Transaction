package ru.gerch.transaction.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


import javax.sql.DataSource;

@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private DataSource dataSource;
    @Autowired
    public PasswordEncoder passwordEncoder;

    @Bean
    public PasswordEncoder getPasswordEncoder(){
        return new BCryptPasswordEncoder(8);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication()
                .dataSource(dataSource)
                .usersByUsernameQuery(
                        "select user_name, password, '1' from users " +
                                "where user_name=?")
                .authoritiesByUsernameQuery(
                        "select user_name, user_role from users " +
                                "where user_name=?" +
                                "and is_blocked=false").passwordEncoder(passwordEncoder);
        auth.inMemoryAuthentication().withUser("user").password("user").roles("USER");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/transaction").hasAnyRole("USER")
                .antMatchers("/").permitAll()
                .antMatchers("/logout").permitAll()
                .and()
                .formLogin(form -> {
                    try {
                        form
                                .loginPage("/login")
                                .defaultSuccessUrl("/home")
                                .failureUrl("/login?error=true")
                        .loginPage("/login")
                        .permitAll()
                        .and()
                        .logout()
                        .logoutSuccessUrl("/")
                        .permitAll();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });

    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web
                .ignoring()
                .antMatchers("/h2-console/**");
    }
}