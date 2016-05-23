package com.tcc.flyk.persistence;

import java.util.List;

import com.tcc.flyk.entity.Administrador;
import com.tcc.flyk.entity.Usuario;

public interface AdministradorDAO {

	public void inserirNovoAdmisnistrador(Administrador adm);
	
	public void consulta();
	
	public List<Usuario> consultaUsuario(String usuario);

}
