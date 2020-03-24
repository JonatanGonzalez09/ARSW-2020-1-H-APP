package edu.eci.arsw.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import edu.eci.arsw.persistence.UserPersistence;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	
	private UserPrincipalDetailsService userPrincipalDetailsService;
	private UserPersistence userPersistence;

    public SecurityConfiguration(UserPrincipalDetailsService userPrincipalDetailsService, UserPersistence userPersistence) {
        this.userPrincipalDetailsService = userPrincipalDetailsService;
        this.userPersistence = userPersistence;
    }
    
    @Override
    protected void configure(AuthenticationManagerBuilder auth) {
        auth.authenticationProvider(authenticationProvider());
    }
    
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
        		.csrf().disable()
        		.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        		.and()
        		.addFilter(new JwtAuthenticationFilter(authenticationManager()))
        		.addFilter(new JwtAuthorizationFilter(authenticationManager(),  this.userPersistence))
                .authorizeRequests()
                .antMatchers("/index.html").permitAll()
                .antMatchers("/login").permitAll()
                .antMatchers("/profile/**").authenticated()
                .antMatchers("/assistant-nurse/**").hasAnyRole("ASSISTANT","MANAGER")
                .antMatchers("/admin/**").hasRole("ADMIN")
                .antMatchers("/test/**").authenticated()
                .antMatchers("/nurse/**").hasRole("MANAGER")
                .and()
                .httpBasic();
    }
    
    @Bean
    DaoAuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        daoAuthenticationProvider.setUserDetailsService(this.userPrincipalDetailsService);
        return daoAuthenticationProvider;
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
