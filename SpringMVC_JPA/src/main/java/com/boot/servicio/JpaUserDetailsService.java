package com.boot.servicio;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Transactional;

import com.boot.dao.IUsuarioDao;
import com.boot.modelo.Rol;
import com.boot.modelo.Usuario;



public class JpaUserDetailsService implements UserDetailsService{
	
	protected final Log logger = LogFactory.getLog(this.getClass());

	
	//realizamos la inyeccion de dependencia de la interfazb IUsuarioDao
	@Autowired
	private IUsuarioDao iUsuarioDao;
	
	
     //carga un usuario por el username 
	@Override
	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		//obtenemos el usuario de la base de datos
		Usuario usuario=iUsuarioDao.findByUsername(username);
		
		if(usuario==null) {
			logger.error("Error en el login no existe el usuario: " + username + " en el sistema");
			throw new UsernameNotFoundException("Username: "+ username + "no existe en el sistema");
		}
		//lista para almacenar los roles tipo GrantedAuthority
		List<GrantedAuthority>  roles= new ArrayList<GrantedAuthority>();
		
		
		//recorro el usuario para obtener los roles
		for(Rol role: usuario.getRoles()) {
			logger.info("Role: "+ role.getAuthority());
			roles.add(new SimpleGrantedAuthority(role.getAuthority()));
		}
		//valida si el usuario tiene roles asignados
		if(roles.isEmpty()) {
			logger.error("Error en el login usuario: " + username + " no tiene roles asignados");
			throw new UsernameNotFoundException("Error en el login del usuario: "+ username + "no tiene roles asignados");

			
		}
		
		return new User(usuario.getUsername(), usuario.getPassword(), usuario.isHabilitado(),true,true,true,roles);
	}

}
