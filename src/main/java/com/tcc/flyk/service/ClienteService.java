package com.tcc.flyk.service;

import java.util.Date;
import java.util.List;

import com.tcc.flyk.entity.Cliente;
import com.tcc.flyk.entity.Usuario;
import com.tcc.flyk.persistence.ClienteDAO;
import com.tcc.flyk.persistence.impl.ClienteDAOImpl;

public class ClienteService {


	private ClienteDAO cliDAO = new ClienteDAOImpl();
	
	//CADASTRO DE CLIENTE
	//Recebe um objeto cliente de parâmetro, valida o cliente e cadastra o mesmo
	//Retorna uma string vazia em caso de sucesso, ou a mensagem de erro em caso de falha
	public String CadastrarNovoCliente(Cliente cli){
		String retorno = "";
		try {
			String erro = validaCliente(cli);
			if(erro==""){
				//cat.setInicio_vigencia(new Date());
				System.out.println("CLIENTESERVICE: INSERINDO CLIENTE " + cli.toString());
				cliDAO.inserirNovoCliente(cli);
				retorno = "Cadastro realizado com sucesso.";
			}else{
				retorno = erro;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			retorno = "Erro ao cadastrar cliente.";
		}
		return retorno;
	}
	
	//CONSULTA SE UM USUARIO EXISTE OU NAO
	//Verifica se um usuário com o email enviado de parâmetro já existe no banco
	//Retorna verdadeiro caso já exista, e falso caso não
	public boolean ExisteCliente(String email){
		try {
			if(cliDAO.consultaLogin(email)!=null){
				return true;
			}else{
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("CLIENTESERVICE: ERRO AO VERIFICAR SE CLIENTE EXISTE.");
			return false;
		}
	}
	
	//VALIDA CLIENTE PARA CADASTRO
	//Verifica se as informações estão consistentes para inserção no banco
	//Recebe um objeto cliente de parâmetro
	//Retorna uma string com a mensagem de erro caso ocorra, ou uma string vazia caso OK
	private String validaCliente(Cliente cli){
		String retorno = "";
		
		//Valida campos nulos
		if(cli.getNome()==null || cli.getNome().isEmpty()){
			retorno = "Nome do cliente é obrigatório.";
			System.out.println(retorno);
			return retorno;
		}
		
		//Valida email duplicado
		if(ExisteCliente(cli.getEmail())){
			retorno = "Já existe um cadastro para o email " + cli.getEmail() + ".";
			System.out.println(retorno);
			return retorno;
		}
		

		return retorno;
		
	}
	
	/*
	//****************************CONSULTA TODAS AS CATEGORIAS****************************
	public List<Categoria> ConsultarTodasCategorias(){
		try{
			final List<Categoria> retorno = catDAO.ConsultarTodasCategorias();

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
	

	//****************************CONSULTA CATEGORIAS POR PARTE DO NOME****************************
	public List<Categoria> ConsultarCategoriaPorParteDoNome(String nome){
		return catDAO.ConsultarCategoriaPorParteDoNome(nome);
	}
	
	*/
}
