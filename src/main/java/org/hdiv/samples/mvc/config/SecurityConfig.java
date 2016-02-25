package org.hdiv.samples.mvc.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
            .inMemoryAuthentication()
                .withUser("david").password("david").roles("USER","ADMIN").and()
                .withUser("alex").password("alex").roles("USER").and()
                .withUser("tim").password("tim").roles("USER");
	}
    
    @Override
    protected void configure(HttpSecurity http) throws Exception {
    	http
	        .authorizeRequests()
	            .antMatchers("/authenticated/**").hasRole("USER")
	            .anyRequest().permitAll()
	            .and()
	        .formLogin().loginPage("/login.html").permitAll()
	        	.and()
	        .logout().logoutUrl("/logout.html").logoutSuccessUrl("/");
    }
}
