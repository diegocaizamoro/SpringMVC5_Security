package com.boot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import com.boot.autorizacion.LoginSuccess;


//clase para configuracion de spring security
@Configuration
public class SpringSecurityConfig  extends WebSecurityConfigurerAdapter{
	
	
	//reañlizo una inyeccion de la clase LoginSuccess
	@Autowired
	private LoginSuccess loginSuccess;
	

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		//implemento las rutas publicas que pueden ser accedidas por cualquier usuario
		//.formLogin()==èrmite invocar a un formulario que nosotros implementemos
		http.authorizeRequests().antMatchers("/","/css/**","/js/**","/uploads/**","/imagenes/**","/listar").permitAll()
		.anyRequest().authenticated()
		.and().formLogin().successHandler(loginSuccess)
		.loginPage("/login") //habilito la url del controlador para que se abra en el proyecto como un login.html
		.permitAll().and().logout().permitAll().and().exceptionHandling()
		.accessDeniedPage("/error_403");//carga una pagina web con el mesnaje que no tiene permiso a ese recurso
	}

	
	
}
