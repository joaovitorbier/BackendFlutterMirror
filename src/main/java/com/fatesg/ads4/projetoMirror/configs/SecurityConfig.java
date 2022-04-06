package com.fatesg.ads4.projetoMirror.configs;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.fatesg.ads4.projetoMirror.security.JWTAuthenticationFilter;
import com.fatesg.ads4.projetoMirror.security.JWTAuthorizationFilter;
import com.fatesg.ads4.projetoMirror.security.JWTUtil;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private Environment env;
	
	@Autowired
	private JWTUtil jwtUtil;
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	//Endpoint que qualquer um acessa para qualquer operação
	private static final String[] PUBLIC_MATCHERS = {
			
			"/h2-console/**",
			"/**"
			
			
	};
	
	//Quais são os endpoints que fazem GET qualquer um pode acessar mesmo deslogado
	private static final String[] PUBLIC_MATCHERS_GET = {
			
			"/motivos/**",
			"/estados/**",
			"/pessoas/**"
			
	};
	
	//Quais são os endpoints de fazem POST qualquer um pode acessar mesmo deslogado
	private static final String[] PUBLIC_MATCHERS_POST = {
			
			"/**",
			"/login"
			
	};
	
	
	//antMatchers(HttpMethod.GET PUBLIC_MATCHERS_GET).permitAll().
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		if(Arrays.asList(env.getActiveProfiles()).contains("test")) {
			http.headers().frameOptions().disable();
		}
		
		http.cors().and().csrf().disable();
		http.authorizeRequests().
		antMatchers(HttpMethod.GET, PUBLIC_MATCHERS_GET).permitAll().
		antMatchers(HttpMethod.POST, PUBLIC_MATCHERS_POST).permitAll().
		antMatchers(PUBLIC_MATCHERS).permitAll().
		anyRequest().authenticated();
		http.addFilter(new JWTAuthenticationFilter(authenticationManager(),jwtUtil));
		http.addFilter(new JWTAuthorizationFilter(authenticationManager(),jwtUtil,userDetailsService));
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		
	}
	
	public void configure(AuthenticationManagerBuilder auth) throws Exception{
		
		auth.userDetailsService(userDetailsService).passwordEncoder(bCrypPasswordEncoder());
		
	}
	
	@Bean
	CorsConfigurationSource corsConfigurationSource() {
		
		CorsConfiguration configuration = new CorsConfiguration().applyPermitDefaultValues();
		
		configuration.setAllowedMethods(Arrays.asList("POST","GET","PUT","DELETE","OPTIONS"));
		
		final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", configuration);
		return source;
		
	}
	
	@Bean
	public BCryptPasswordEncoder bCrypPasswordEncoder() {
		
		return new BCryptPasswordEncoder();
		
	}
	
}
