package com.boot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import com.boot.autorizacion.LoginSuccess;
import com.boot.servicio.JpaUserDetailsService;


//clase para configuracion de spring security
@EnableGlobalMethodSecurity(securedEnabled = true,prePostEnabled = true) //habilitar seguridades con anotaciones en los controladores
@Configuration
public class SpringSecurityConfig  extends WebSecurityConfigurerAdapter{
	
	
	//reañlizo una inyeccion de la clase LoginSuccess
	@Autowired
	private LoginSuccess loginSuccess;
	
	@Autowired
	private JpaUserDetailsService userDetailsService;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		//implemento las rutas publicas que pueden ser accedidas por cualquier usuario
		//.formLogin()==èrmite invocar a un formulario que nosotros implementemos
		http.authorizeRequests().antMatchers("/css/**","/js/**","/uploads/**","/imagenes/**").permitAll()
		.anyRequest().authenticated()
		.and().formLogin().successHandler(loginSuccess)
		.loginPage("/login") //habilito la url del controlador para que se abra en el proyecto como un login.html
		.permitAll().and().logout().permitAll().and().exceptionHandling()
		.accessDeniedPage("/error_403");//carga una pagina web con el mesnaje que no tiene permiso a ese recurso
	}

	//para registrar a los usuarios
		@Autowired
		public void configurerGlobal(AuthenticationManagerBuilder build) throws Exception
		{
			//para trabajar con JPA
			build.userDetailsService(userDetailsService)
			.passwordEncoder(passwordEncoder);

		}
	
}
