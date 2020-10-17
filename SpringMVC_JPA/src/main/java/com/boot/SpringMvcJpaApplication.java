package com.boot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.boot.servicio.ISubidaDeArchivosService;

@SpringBootApplication
public class SpringMvcJpaApplication  implements CommandLineRunner{
	
	@Autowired
	ISubidaDeArchivosService iSubidaDeArchivosService;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	public static void main(String[] args) {
		SpringApplication.run(SpringMvcJpaApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		//elimina y crea la casrpeta de recursos
		iSubidaDeArchivosService.deleteAll();
		iSubidaDeArchivosService.init();
		
		String clave="12345";
		
		for(int i=0; i<2; i++) {
			String bcrypPassword=passwordEncoder.encode(clave);
			System.out.print(bcrypPassword +"..se..");
		}
		
		
	}

}
