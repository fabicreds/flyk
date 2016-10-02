package com.tcc.flyk.persistence;

import java.util.List;

import com.tcc.flyk.entity.Compromisso;
import com.tcc.flyk.entity.Prestador;
import com.tcc.flyk.entity.Usuario;

public interface PrestadorDAO {

	public Usuario consultaLogin(String email);
	
	public Prestador consultaPrestadorPorId(String idPrestador);

	public boolean inserirNovoPrestador(Prestador prestador);

	void consultaTudo();
	
	public List<Compromisso> consultarListaContratosServicosPrestadosById(String id);
	
	public boolean atualizarListaContratosServicosPrestadosById(String idPrestador, List<Compromisso> listaContratosServicosPrestados);
}
