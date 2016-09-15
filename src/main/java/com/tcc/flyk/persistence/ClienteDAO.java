package com.tcc.flyk.persistence;

import com.tcc.flyk.entity.Cliente;
import com.tcc.flyk.entity.Usuario;

public interface ClienteDAO {

	public void consulta();
	
	public Usuario consultaLogin(String email);
	
	public Cliente consultaClientePorId(String idCliente);
	
	public boolean atualizarStatusCliente(Usuario usuario);

	public boolean inserirNovoCliente(Cliente cliente);

	void consultaTudo();
	
	public Usuario consultaLoginById(String id);
	
	public Cliente atualizaCliente (String idCliente, Cliente c);

	
}
