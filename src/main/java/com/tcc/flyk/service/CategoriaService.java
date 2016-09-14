package com.tcc.flyk.service;

import java.util.Date;
import java.util.List;

import com.tcc.flyk.entity.Categoria;
import com.tcc.flyk.persistence.CategoriaDAO;
import com.tcc.flyk.persistence.impl.CategoriaDAOImpl;

public class CategoriaService {

	private CategoriaDAO catDAO = new CategoriaDAOImpl();
	
	//CADASTRO DE CATEGORIA
	public boolean cadastrarNovaCategoria(Categoria cat){
		try {
			if(validaCategoria(cat)){
				cat.setInicio_vigencia(new Date());
				catDAO.cadastrarNovaCategoria(cat);
			}else{
				return false;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	//CONSULTA SE UMA CATEGORIA EXISTE OU NÃO
	public boolean existeCategoria(String nome){
		try {
			Categoria cat = catDAO.consultarCategoriaPorNome(nome);
			if(cat.getNome_categoria()==""){
				return false;
			}else{
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	//VALIDA CATEGORIA PARA CADASTRO
	private boolean validaCategoria(Categoria cat){
		
		//Valida campos nulos
		if(cat.getNome_categoria()==null || cat.getNome_categoria().isEmpty()){
			System.out.println("Nome da categoria não preenchido");
			return false;
		}
		if(cat.getDescricao_categoria()==null || cat.getDescricao_categoria().isEmpty()){
			System.out.println("Descrição da categoria não preenchida");
			return false;
		}
		
		//Valida nome duplicado
		if(existeCategoria(cat.getNome_categoria())){
			System.out.println("Categoria já existe");
			return false;
		}
		
		
		return true;
		
	}
	
	//****************************CONSULTA TODAS AS CATEGORIAS****************************//
	public List<Categoria> consultarTodasCategorias(){
		try{
			final List<Categoria> retorno = catDAO.consultarTodasCategorias();

			if(retorno==null){
				return null;
			}else{
				if(retorno.size()==0){
					return null;
				}else{
					return retorno;
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
}
