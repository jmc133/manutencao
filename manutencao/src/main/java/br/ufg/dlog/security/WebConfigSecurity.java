package br.ufg.dlog.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;



@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class WebConfigSecurity {
	
	@Bean // Configura as solicitações de acesso por Http
	public SecurityFilterChain configure(HttpSecurity http) throws Exception {
		http
		.csrf(csrf-> csrf.disable())// Desativa as configurações padrão de memória.
		.authorizeHttpRequests((authorize) -> authorize // Pertimi restringir acessos
		.antMatchers("/login").permitAll() // Qualquer usuário acessa a pagina inicial
		.antMatchers(HttpMethod.GET, "/ajuda").permitAll() 
		.antMatchers(HttpMethod.GET, "/logincadastro").permitAll()
		.antMatchers("/salvaruserempresa").permitAll()
		.antMatchers("/verorcamentos").permitAll()
		.antMatchers("/orcar").hasAnyRole("PUBLIC","EMPRESA")
		.antMatchers(HttpMethod.GET, "/index2").hasAnyRole("ADMIN","USER","MOTORISTA","SECRETARIO","PUBLIC", "EMPRESA")
    	.antMatchers(HttpMethod.GET, "/movimentosespecificos").hasAnyRole("ADMIN")
		.antMatchers(HttpMethod.GET, "/relatoriomovimentacao").hasAnyRole("ADMIN")
		.antMatchers(HttpMethod.GET, "/agenda").hasAnyRole("ADMIN","SECRETARIO")
		.antMatchers(HttpMethod.GET, "/relatorioagenda").hasAnyRole("ADMIN","SECRETARIO")
		.antMatchers(HttpMethod.GET, "/solicitacao").hasAnyRole("ADMIN","SECRETARIO")
		.antMatchers(HttpMethod.GET, "/reserva").hasAnyRole("ADMIN","SECRETARIO")
		.antMatchers(HttpMethod.GET, "/movimento").hasAnyRole("ADMIN","SECRETARIO","MOTORISTA")
		.antMatchers(HttpMethod.GET, "/reservaspessoas").hasAnyRole("ADMIN","SECRETARIO")
		.antMatchers(HttpMethod.GET, "/movimentodiario").hasAnyRole("ADMIN","SECRETARIO")
		.antMatchers("/pessoas").hasAnyRole("ADMIN")
		.antMatchers(HttpMethod.GET, "/usuario").hasAnyRole("ADMIN")
		.antMatchers(HttpMethod.GET, "/orgao").hasAnyRole("ADMIN")
		.antMatchers(HttpMethod.GET, "/listaescala").hasAnyRole("ADMIN","MOTORISTA","USER","SECRETARIO")
		.antMatchers(HttpMethod.GET, "/iniciamovimento").hasAnyRole("MOTORISTA","ADMIN")
		.antMatchers(HttpMethod.GET, "/veiculo").hasAnyRole("ADMIN","SECRETARIO")
		.antMatchers(HttpMethod.POST, "/veiculo").hasAnyRole("ADMIN","SECRETARIO")
		.antMatchers(HttpMethod.GET, "/caracteristicaveiculo").hasAnyRole("ADMIN","SECRETARIO")
		.antMatchers(HttpMethod.GET, "/inseremovimento").hasAnyRole("ADMIN","SECRETARIO")
		.antMatchers(HttpMethod.GET, "/solicitacao").hasAnyRole("ADMIN","SECRETARIO")
		.antMatchers( "/principal").hasAnyRole("ADMIN","SECRETARIO")
		.antMatchers( "/ordermservico").hasAnyRole("ADMIN","SECRETARIO")
		.antMatchers(HttpMethod.POST, "/salvaritens").hasAnyRole("ADMIN","SECRETARIO")
		.antMatchers(HttpMethod.GET, "/salvaritens").hasAnyRole("ADMIN","SECRETARIO")
		.antMatchers(HttpMethod.GET,"/pesquisaros").hasAnyRole("ADMIN","SECRETARIO")
		.antMatchers("/salvarordem").hasAnyRole("ADMIN","SECRETARIO")
		.antMatchers("/ospesquisa").hasAnyRole("ADMIN","SECRETARIO")
		.antMatchers("/osvpesquisa").hasAnyRole("ADMIN","SECRETARIO")
		.anyRequest().authenticated()
		)
		.formLogin((formLogin) ->
		formLogin
			.permitAll()
			.loginPage("/login")
			.defaultSuccessUrl("/index2")
			.failureUrl("/login?error=true")
	    )
		.logout((logout) ->
		logout.deleteCookies("remove")
			.invalidateHttpSession(false)
			.logoutUrl("/login")
			.logoutSuccessUrl("/login")
			.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
	    );
       
		
		return http.build();
		
	}
	
	
	@Bean
    public AuthenticationManager authenticationManager(HttpSecurity http,
    		                                           PasswordEncoder passwordEncoder1,
    		                                           AuthorizationService usuario)throws Exception{
       
    	return http.getSharedObject(AuthenticationManagerBuilder.class)
    			.userDetailsService(usuario)
    			.passwordEncoder(passwordEncoder1)
    			.and()
    			.build();
    }
	@Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
	
	
	@Bean // Ignora URL especificas
	public WebSecurityCustomizer webSecurityCustomizer() {
		return (web) -> web.ignoring().antMatchers("/materialize/**");
	}

}
