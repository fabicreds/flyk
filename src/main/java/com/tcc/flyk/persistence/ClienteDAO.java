package com.tcc.flyk.persistence;

import java.util.List;

import com.tcc.flyk.entity.Usuario;

public interface ClienteDAO {

	public void consulta();
	
	public List<Usuario> consultaUsuario(String usuario);

}
