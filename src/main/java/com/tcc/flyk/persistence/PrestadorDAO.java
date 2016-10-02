package com.tcc.flyk.persistence;

import com.tcc.flyk.entity.Cliente;
import com.tcc.flyk.entity.Prestador;
import com.tcc.flyk.entity.Usuario;

public interface PrestadorDAO {

	public Usuario consultaLogin(String email);
	
	public Prestador consultaPrestadorPorId(String idPrestador);

	public boolean inserirNovoPrestador(Prestador prestador);
	
	public Prestador atualizaPrestador(String idPrestador, Prestador prestador);


	void consultaTudo();
	
}
