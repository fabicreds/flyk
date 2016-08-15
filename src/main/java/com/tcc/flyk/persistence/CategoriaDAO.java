package com.tcc.flyk.persistence;

import java.util.List;
import com.tcc.flyk.entity.Categoria;

public interface CategoriaDAO {

	public boolean CadastrarNovaCategoria(Categoria cat);
	
	public Categoria ConsultarCategoriaPorNome(String nome);
	
	public List<Categoria> ConsultarCategoriaPorParteDoNome(String nome);
	
	public List<Categoria> ConsultarTodasCategorias();
	
	public Categoria ConsultarCategoriaPorId(String id);
	
	public void consultaTudo();

}

