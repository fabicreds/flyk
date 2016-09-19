package com.tcc.flyk.service;

import java.util.List;

import javax.annotation.Resource;

import org.json.JSONObject;
import org.springframework.stereotype.Service;

import com.tcc.flyk.entity.Categoria;
import com.tcc.flyk.entity.Cliente;
import com.tcc.flyk.persistence.ClienteDAO;
import com.tcc.flyk.persistence.impl.CategoriaDAOImpl;
import com.tcc.flyk.persistence.impl.ClienteDAOImpl;
import com.tcc.flyk.util.CategoriaUtil;

@Service
public class ClienteService {

	private ClienteDAO cliDAO = new ClienteDAOImpl();

	private CategoriaDAOImpl categoriaDAO = new CategoriaDAOImpl();

	@Resource
	private CategoriaUtil categoriaUtil;

	// CADASTRO DE CLIENTE
	// Recebe um objeto cliente de par�metro, valida o cliente e cadastra o
	// mesmo
	// Retorna uma string vazia em caso de sucesso, ou a mensagem de erro em
	// caso de falha
	public String cadastrarNovoCliente(Cliente cli) {
		String retorno = "";
		try {
			String erro = validaCliente(cli);
			if (erro == "") {
				cliDAO.inserirNovoCliente(cli);
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

	// CONSULTA SE UM USUARIO EXISTE OU NAO
	// Verifica se um usu�rio com o email enviado de par�metro j� existe no
	// banco
	// Retorna verdadeiro caso j� exista, e falso caso n�o
	public boolean existeCliente(String email) {
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

	// VALIDA CLIENTE PARA CADASTRO
	// Verifica se as informa��es est�o consistentes para inser��o no banco
	// Recebe um objeto cliente de par�metro
	// Retorna uma string com a mensagem de erro caso ocorra, ou uma string
	// vazia caso OK
	private String validaCliente(Cliente cli) {
		String retorno = "";

		// Valida campos nulos
		if (cli.getNome() == null || cli.getNome().isEmpty()) {
			retorno = "Nome do cliente � obrigat�rio.";
			System.out.println(retorno);
			return retorno;
		}

		// Valida email duplicado
		if (existeCliente(cli.getEmail())) {
			retorno = "Já existe um cadastro para o email " + cli.getEmail() + ".";
			System.out.println(retorno);
			return retorno;
		}

		return retorno;

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
