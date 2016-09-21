package com.tcc.flyk.service;

import java.util.List;

import javax.annotation.Resource;

import org.json.JSONObject;
import org.springframework.stereotype.Service;

import com.tcc.flyk.entity.Categoria;
import com.tcc.flyk.entity.Prestador;
import com.tcc.flyk.persistence.ClienteDAO;
import com.tcc.flyk.persistence.PrestadorDAO;
import com.tcc.flyk.persistence.impl.CategoriaDAOImpl;
import com.tcc.flyk.persistence.impl.ClienteDAOImpl;
import com.tcc.flyk.persistence.impl.PrestadorDAOImpl;
import com.tcc.flyk.util.CategoriaUtil;

@Service
public class PrestadorService {
	
	private CategoriaDAOImpl categoriaDAO = new CategoriaDAOImpl();

	@Resource
	private CategoriaUtil categoriaUtil;
	
	private ClienteDAO cliDAO = new ClienteDAOImpl();
	
	private PrestadorDAO prestadorDAO = new PrestadorDAOImpl();

	public String cadastrarNovoPrestador(Prestador prestador) {
		String retorno = "";
		try {
			String erro = validaPrestador(prestador);
			if (erro == "") {
				prestadorDAO.inserirNovoPrestador(prestador);
				retorno = null;
			} else {
				retorno = erro;
			}

		} catch (Exception e) {
			e.printStackTrace();
			retorno = "Erro ao cadastrar cliente.";
		}
		return retorno;
	}
	
	private String validaPrestador(Prestador prestador) {
		String retorno = "";

		// Valida campos nulos
		if (prestador.getNome() == null || prestador.getNome().isEmpty()) {
			retorno = "Nome do cliente é obrigatório.";
			System.out.println(retorno);
			return retorno;
		}
		
		if (prestador.getEmail() == null || prestador.getEmail().isEmpty()) {
			retorno = "Email do cliente é obrigatório.";
			System.out.println(retorno);
			return retorno;
		} else {
			String[] usuario = prestador.getEmail().split("@");
			prestador.setUsuario(usuario[0]);
		}

		// Valida email duplicado
		if (existePrestador(prestador.getEmail())) {
			retorno = "Já existe um cadastro para o email " + prestador.getEmail() + ".";
			System.out.println(retorno);
			return retorno;
		}
		
		//Valida se estÃ¡ sendo cadastrado algum serviÃ§o
		if(prestador.getListaCategoriaServicosPrestados()== null || prestador.getListaCategoriaServicosPrestados().isEmpty()){
			retorno = "Para perfil de prestador, é obrigatório o cadastro de pelo menos um serviço.";
			System.out.println(retorno);
			return retorno;
		}

		return retorno;

	}
	
	public boolean existePrestador(String email) {
		try {
			if (cliDAO.consultaLogin(email) != null) {
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("CLIENTESERVICE: ERRO AO VERIFICAR SE CLIENTE EXISTE.");
			return false;
		}
	}
	
	public JSONObject consultaCategoriaCadastradasCadastro() {
		JSONObject jsonRetorno = new JSONObject();
		List<Categoria> listaCategoriasCadastradas = categoriaDAO.consultarTodasCategorias();
		if (listaCategoriasCadastradas != null) {
			jsonRetorno = categoriaUtil.listaCategoriaJSON(listaCategoriasCadastradas);
		}
		return jsonRetorno;
	}

}
