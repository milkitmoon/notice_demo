package com.milkit.app.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.StandardPasswordEncoder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;

import com.milkit.app.domain.userinfo.dao.UserInfoDao;
import com.milkit.app.domain.userinfo.service.UserInfoServiceImpl;

@Configuration
@EnableWebSecurity
public class WebSecurityConfigure extends WebSecurityConfigurerAdapter {


	@Autowired
	private UserInfoServiceImpl userInfoService;

/*
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/**").anyRequest();
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http.authorizeRequests().antMatchers("/**").permitAll();
    }
*/
	

	@Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers(
        		"/resources/**",
        		"/uploads/**",
        		"/h2-console/**"
        	);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception{
//        http.authorizeRequests().antMatchers("/**").permitAll();

        http
        .authorizeRequests()
        	.antMatchers("/uploads/**").permitAll()
        	.antMatchers("/h2-console/**").permitAll()
        	.anyRequest().authenticated()
            .and()
        .formLogin()
            .defaultSuccessUrl("/main")
            .permitAll()
            .and()
        .csrf()
        	.ignoringAntMatchers("/**")
        	.and()
        .logout();

        
    }

    
    
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new StandardPasswordEncoder();
//        return new BCryptPasswordEncoder();
    }
    
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userInfoService).passwordEncoder(passwordEncoder());
    }

}