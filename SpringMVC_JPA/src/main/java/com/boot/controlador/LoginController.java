package com.boot.controlador;

import java.security.Principal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


//clase para personalizar nuestro login
@Controller
public class LoginController { //con esta clase personalizamos nuestro login

	
	//value="error", required=false) String error para obtener el error 
	//es falso ya que no siempre se rekiere que llege al controlador
	@GetMapping("/login")
	public String login(@RequestParam(value="error", required=false) String error,
			@RequestParam(value="logout", required = false) String logout,
			Model model, Principal principal, RedirectAttributes flash) {
		
		if(principal != null) { //para verificar si ha inicializado sesion anteriormente (principal)
			flash.addFlashAttribute("info", "Ya ha inciado sesión anteriormente");
			return "redirect:/listar";
		}
		
		//si viene en la url un error de logueo que nos muestre un mensaje
		if(error != null) {
			model.addAttribute("error", "Error en el login: Nombre de usuario o contraseña incorrecta, por favor vuelva a intentarlo!");
		}
		 //cuando cierre sesion que envie un sms
		if(logout != null) {
			model.addAttribute("success", "Ha cerrado sesión con éxito!");
		}
		
		return "login";
	}
}
