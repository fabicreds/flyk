package com.tcc.flyk.persistence;

import java.util.List;

import com.tcc.flyk.entity.Compromisso;
import com.tcc.flyk.entity.Conversa;
import com.tcc.flyk.entity.Prestador;
import com.tcc.flyk.entity.Usuario;

public interface PrestadorDAO {

	public Usuario consultaLogin(String email);
	
	public Prestador consultaPrestadorPorId(String idPrestador);

	public boolean inserirNovoPrestador(Prestador prestador);

	void consultaTudo();
	
	public List<Prestador> buscaServico(List<String> idCategorias, int qtdMinimaEstrelas, String nomePrestador);

	List<Compromisso> consultarListaContratosServicosPrestadosById(String id);

	boolean atualizarListaContratosServicosPrestadosById(String idPrestador,
			List<Compromisso> listaContratosServicosPrestados);

	Prestador atualizaPrestador(String idPrestador, Prestador prestador);
	
	public boolean atualizarPrestadoresReocomendadosById(String idPrestadorRecomendador, List<String> idPrestadores);
	
	public boolean atualizarRecomendacaoRecebidas(String idUsuario, List<String> listarecomendacoesRecebidas);
	
	public List<String> consultarRecomendacoesRecebidasById(String id);

	public boolean atualizarListaConversa(String idUsuario, List<Conversa> listaConversa);

	public List<Conversa> consultarListaConversa(String idUsuario);
}
