package com.boot.controlador;

import java.security.Principal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


//Clase para personalizar nuestro login
@Controller
public class LoginControlador {

	
	
	//al iniciar sesion o cerrarla el formulario de login se conectara a este metodo
	//y podemos obtener la sesion o datos del usuario que se conecte a nuestro sistema
	@GetMapping("/login")
	public String login(@RequestParam(value = "error",required = false) String error,
			@RequestParam(value = "logout",required = false )  String logout,Model model,
			Principal principal, RedirectAttributes flash)  {
		
		if(principal!=null) { //para verificar si ha iniciado sesion anteriormente
			flash.addFlashAttribute("info", "Ya ha iniciado sesion anteriormente!");
			return "redirect:/listar"; 
		}
		
		if(error!=null) {
			model.addAttribute("error", "Error en el login: Nombre de usuario o contraseña incorrecta");
		}
		
		//cuando cierre la sesion envie algun mensaje
		if(logout!=null) {
			model.addAttribute("success", "Ha cerrado sesion con exito!");
		}
		return "login";
		
	}
	
}
