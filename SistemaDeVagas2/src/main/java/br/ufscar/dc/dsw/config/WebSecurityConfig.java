package br.ufscar.dc.dsw.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import br.ufscar.dc.dsw.security.UsuarioDetailsServiceImpl;
import br.ufscar.dc.dsw.security.CustomAuthenticationSuccessHandler;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

	@Bean
	public UserDetailsService userDetailsService() {
		return new UsuarioDetailsServiceImpl();
	}

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
		authProvider.setUserDetailsService(userDetailsService());
		authProvider.setPasswordEncoder(passwordEncoder());

		return authProvider;
	}

	@Bean
	public AuthenticationSuccessHandler customAuthSuccessHandler() {
		return new CustomAuthenticationSuccessHandler();
	}
	

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http
				.authorizeHttpRequests((authz) -> authz
						.requestMatchers("/error", "/login/**", "/js/**", "/home").permitAll()
						.requestMatchers("/css/**", "/image/**", "/webjars/**").permitAll()
						.requestMatchers("/empresas/vagas/**", "/empresas/inscricoes/**",  "/empresas/resultado/**").hasRole("EMPRESA")
						.requestMatchers("/empresas/**", "/profissionais/**", "/usuarios/**").hasRole("ADMIN")
						.requestMatchers("/profissionais/**").hasRole("PROFISSIONAL")
						
						.anyRequest().authenticated())
				.formLogin((form) -> form
						.loginPage("/login")
						.successHandler(customAuthSuccessHandler()) 
						.permitAll())
				.logout((logout) -> logout
						.logoutSuccessUrl("/").permitAll());

		return http.build();
	}
}
