package com.tcc.flyk.persistence;

import java.util.List;
import com.tcc.flyk.entity.Categoria;

public interface CategoriaDAO {

	public boolean cadastrarNovaCategoria(Categoria cat);
	
	public Categoria consultarCategoriaPorNome(String nome);
	
	public List<Categoria> consultarCategoriaPorParteDoNome(String nome);
	
	public List<Categoria> consultarTodasCategoriasAtivas();
	
	public Categoria consultarCategoriaPorId(String id);
	
	public boolean atualizarStatusCategoria(String id, int acao);
	
	public List<Categoria> consultarTodasCategorias();
}

