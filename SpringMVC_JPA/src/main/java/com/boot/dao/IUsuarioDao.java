package com.boot.dao;

import org.springframework.data.repository.CrudRepository;
import com.boot.modelo.Usuario;

public interface IUsuarioDao extends CrudRepository<Usuario, Long> {

	public Usuario findByUsername(String username);
}
