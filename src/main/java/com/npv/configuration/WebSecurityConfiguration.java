package com.npv.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

import com.npv.service.IAccountService;

@Component
@EnableWebSecurity
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {
	@Autowired
	private IAccountService acService;
	
	@Autowired
	private AuthEntryPointJwt authEntryPoint;
	
	@Override
	protected void configure(AuthenticationManagerBuilder authBuilder) throws Exception {
		authBuilder.userDetailsService(acService).passwordEncoder(new BCryptPasswordEncoder());
	}
	
	@Bean
	public AuthTokenFilter createAuthTokenFiler() {
		return new AuthTokenFilter();
	}
	
	
	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception{
		http
		.cors()
		.and()
		.authorizeRequests()
			.antMatchers("/api/auth/**").permitAll()
			.antMatchers("/api/accounts/**").hasAnyAuthority("ADMIN")
			//.antMatchers("/api/accounts/**").permitAll()
			.antMatchers("/api/departments/**").hasAnyAuthority("ADMIN")
		.anyRequest().authenticated()
		.and()
		.exceptionHandling().authenticationEntryPoint(authEntryPoint)
		.and()
		.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
		.and()
		.httpBasic()
		.and()
		.csrf().disable();
		
		http.addFilterBefore(createAuthTokenFiler(), UsernamePasswordAuthenticationFilter.class);
	}
}

