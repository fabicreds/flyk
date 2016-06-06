package com.tcc.flyk.service;

import java.util.Date;

import com.tcc.flyk.entity.Categoria;
import com.tcc.flyk.persistence.AdministradorDAO;
import com.tcc.flyk.persistence.CategoriaDAO;
import com.tcc.flyk.persistence.impl.AdministradorDAOImpl;

public class CategoriaService {

	private CategoriaDAO catDAO = new CategoriaDAO();
	
	
	public boolean CadastrarNovaCategoria(Categoria cat){
		try {
			if(validaCategoria(cat)){
				cat.setInicio_vigencia(new Date());
				catDAO.CadastrarNovaCategoria(cat);
			}else{
				return false;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	private boolean validaCategoria(Categoria cat){
		if(cat.getNome_categoria()==null || cat.getNome_categoria().isEmpty()){
			System.out.println("Nome da categoria não preenchido");
			return false;
		}
		if(cat.getDescricao_categoria()==null || cat.getDescricao_categoria().isEmpty()){
			System.out.println("Descrição da categoria não preenchida");
			return false;
		}
		return true;
		
	}
}
