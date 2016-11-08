package com.tcc.flyk.persistence;

import java.util.List;

import com.tcc.flyk.entity.Amizade;
import com.tcc.flyk.entity.Cliente;
import com.tcc.flyk.entity.Compromisso;
import com.tcc.flyk.entity.Conversa;
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

	public List<Amizade> consultarAmigosById(String id);
	
	public boolean atualizarListaAmigos(String  idUsuario, List<Amizade> listaAmigos);
	
	public List<Cliente> consultaClientePorParteDoNome(String nomeCliente);//Este método funciona, mas não usaremos
	
	public List<Usuario> consultaUsuarioPorParteDoNome(String nomeUsuario);

	public List<Compromisso> consultarListaServicosContratadosById(String id);
	
	public boolean atualizarListaServicosContratadosById(String idCliente, List<Compromisso> lista);
	
	public List<String> consultarRecomendacoesDadasById(String id) ;
	
	public boolean atualizarRecomendacaoDadas(String idUsuario, List<String> listarecomendacoesDadas);
	
	public boolean atualizarListaConversa(String idUsuario, List<Conversa> listaConversa);
	
	public List<Conversa> consultarListaConversa(String idUsuario);
	
	public List<Conversa> consultarListaConversaAmigo(String idUsuario, String idAmigo);
	
}

