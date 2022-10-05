package cl.bci.config;

import cl.bci.business.filter.JWTAuthenticationFilter;
import cl.bci.business.filter.JWTAuthorizationFilter;
import cl.bci.business.service.JWTService;
import cl.bci.business.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;
import java.util.Collections;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private JWTService jwtService;

	private static final String[] AUTH_PROJECT_LIST = {
		"/", "/css/**", "*/js/**", "/images/**",
	};

	private static final String[] AUTH_SWAGGER_LIST = {
		"/v2/api-docs",
		"/configuration/ui",
		"/swagger-resources/**",
		"/configuration/security",
		"/swagger-ui.html",
		"/webjars/**"
	};

	@Override
    protected void configure(HttpSecurity httpSecurity) throws Exception
    {
		httpSecurity 
		.authorizeRequests()
			.antMatchers(AUTH_PROJECT_LIST).permitAll()   // Project resource
			.antMatchers(AUTH_SWAGGER_LIST).permitAll()   // Swagger UI resource
			.antMatchers("/h2-console/**").permitAll()    // -- console H2
			.antMatchers("/**").authenticated() // others need auth
        .and().headers().frameOptions().sameOrigin()
        .and().csrf().disable()
		.addFilter(new JWTAuthenticationFilter(authenticationManager(), jwtService))
        .addFilter(new JWTAuthorizationFilter(authenticationManager(), jwtService))
        .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        .and().cors().configurationSource(corsConfigurationSource());
    }

	@Bean
	public CorsConfigurationSource corsConfigurationSource() {
		CorsConfiguration config = new CorsConfiguration();
		config.setAllowedOrigins(Collections.singletonList("http://localhost:4200"));
		config.setAllowedHeaders(Arrays.asList("*"));
		config.setAllowedMethods(Arrays.asList("POST", "PUT", "DELETE", "OPTIONS"));
		config.setAllowCredentials(true);
		config.applyPermitDefaultValues();
		
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", config);
		return source;
	}
	
	@Autowired
	public void configurerGlobal(AuthenticationManagerBuilder build) throws Exception
	{
		build.userDetailsService(userService)
		.passwordEncoder(passwordEncoder);
	}

}
