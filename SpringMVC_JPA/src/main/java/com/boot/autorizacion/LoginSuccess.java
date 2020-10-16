package com.boot.autorizacion;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.FlashMap;
import org.springframework.web.servlet.support.SessionFlashMapManager;


//clase para implementar los mensajes de exito o error al iniciar la sesion en el proyecto
@Component
public class LoginSuccess extends SimpleUrlAuthenticationSuccessHandler{

	protected final Log logger = LogFactory.getLog(this.getClass());

	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
	    
		//administrador parecido al map
		SessionFlashMapManager flashMapManager =new SessionFlashMapManager();
		//authentication.getName()= accedo al nombre del usuario que inicia sesion
		FlashMap  flashMap =new FlashMap();
		flashMap.put("success", "Hola "+ authentication.getName()+ "Haz iniciado sesion con exito!");
		
		//registramos en el manager
		flashMapManager.saveOutputFlashMap(flashMap, request, response);
		
		if(authentication!=null) {
			logger.info("El usuario "+ authentication.getName() + "ha iniciado sesion con exito");
		}
		
		super.onAuthenticationSuccess(request, response, authentication);
		
	}
	
	
	
	

}
