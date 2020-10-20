package com.boot;

import java.util.Locale;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

@Configuration
public class MvcConfig implements WebMvcConfigurer{
	
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/error_403").setViewName("error_403");
	}
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		WebMvcConfigurer.super.addResourceHandlers(registry);
		
	}
	
	//1.- primero construimos un localResolver
	@Bean
	public LocaleResolver localeResolver()  {
		//guardamos el cambio de lenguaje en una session de usuario http.
		SessionLocaleResolver localResolver= new SessionLocaleResolver();
		localResolver.setDefaultLocale(new Locale("es", "ES"));  //es=codigo lenguaje, ES=codigo pais
		 return localResolver;
	}
	
	//2.- para cambiar el locale cada vez que el usuario cambie el lenguaje 
	//un interceptor entra a trabajar
	@Bean
	public LocaleChangeInterceptor localeChangeInterceptor() {
		LocaleChangeInterceptor localeInterceptor=new LocaleChangeInterceptor();
		localeInterceptor.setParamName("lang");  //pasamos como parametro el lang en una url
		return localeInterceptor;
		
	}
	//3.- registro del interceptor
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(localeChangeInterceptor());
	}
	
	
	
	
	
	
	
	
	

}
