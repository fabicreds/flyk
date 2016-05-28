package com.tcc.flyk.persistence;

import com.tcc.flyk.entity.Administrador;
import com.tcc.flyk.entity.Usuario;

public interface AdministradorDAO {

	public void inserirNovoAdmisnistrador(Administrador adm);
	
	public Usuario consultaUsuarioAdministrador(String usuario);
	
	public Administrador consultaAdministradorPorUsuario(String usuario);
	
	public boolean atualizarStatusAdministrador(Usuario usuario);

}
