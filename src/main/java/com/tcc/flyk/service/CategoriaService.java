package com.tcc.flyk.service;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.tcc.flyk.entity.Categoria;
import com.tcc.flyk.persistence.CategoriaDAO;
import com.tcc.flyk.persistence.impl.CategoriaDAOImpl;

@Service
public class CategoriaService {

	private CategoriaDAO catDAO = new CategoriaDAOImpl();

	// CADASTRO DE CATEGORIA
	public boolean cadastrarNovaCategoria(Categoria cat) {
		try {
			if (validaCategoria(cat)) {
				cat.setInicioVigencia(new Date());
				catDAO.cadastrarNovaCategoria(cat);
			} else {
				return false;
			}

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	// CONSULTA SE UMA CATEGORIA EXISTE OU N�O
	public boolean existeCategoria(String nome) {
		try {
			Categoria cat = catDAO.consultarCategoriaPorNome(nome);
			if (cat.getNomeCategoria() == "" || cat.getStatusCategoria().equals("I")) {
				return false;
			} else {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	// VALIDA CATEGORIA PARA CADASTRO
	private boolean validaCategoria(Categoria cat) {

		// Valida campos nulos
		if (cat.getNomeCategoria() == null || cat.getNomeCategoria().isEmpty()) {
			System.out.println("Nome da categoria n�o preenchido");
			return false;
		}
		if (cat.getDescricaoCategoria() == null || cat.getDescricaoCategoria().isEmpty()) {
			System.out.println("Descri��o da categoria n�o preenchida");
			return false;
		}

		// Valida nome duplicado
		if (existeCategoria(cat.getNomeCategoria())) {
			System.out.println("Categoria j� existe");
			return false;
		}

		return true;

	}

	// ****************************CONSULTA TODAS AS
	// CATEGORIAS****************************//
	public List<Categoria> consultarTodasCategoriasAtivas() {
		try {
			final List<Categoria> retorno = catDAO.consultarTodasCategoriasAtivas();

			if (retorno == null) {
				return null;
			} else {
				if (retorno.size() == 0) {
					return null;
				} else {
					return retorno;
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public List<Categoria> atualizarCategoria(String id, int acao) {

		catDAO.atualizarStatusCategoria(id, acao);

		return catDAO.consultarTodasCategorias();

	}
	
	public List<Categoria> consultarTodasCategorias() {
		try {
			final List<Categoria> retorno = catDAO.consultarTodasCategorias();

			return retorno;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
