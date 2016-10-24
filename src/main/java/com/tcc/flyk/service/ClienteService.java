package com.tcc.flyk.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.tcc.flyk.entity.Cliente;
import com.tcc.flyk.entity.Prestador;
import com.tcc.flyk.entity.Usuario;
import com.tcc.flyk.persistence.ClienteDAO;
import com.tcc.flyk.persistence.PrestadorDAO;
import com.tcc.flyk.persistence.impl.ClienteDAOImpl;
import com.tcc.flyk.persistence.impl.PrestadorDAOImpl;

@Service
public class ClienteService {

	private ClienteDAO cliDAO = new ClienteDAOImpl();

	// CADASTRO DE CLIENTE
	// Recebe um objeto cliente de parï¿½metro, valida o cliente e cadastra o
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
	// Verifica se um usuï¿½rio com o email enviado de parï¿½metro jï¿½ existe
	// no
	// banco
	// Retorna verdadeiro caso jï¿½ exista, e falso caso nï¿½o
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
	// Verifica se as informaï¿½ï¿½es estï¿½o consistentes para inserï¿½ï¿½o no
	// banco
	// Recebe um objeto cliente de parï¿½metro
	// Retorna uma string com a mensagem de erro caso ocorra, ou uma string
	// vazia caso OK
	private String validaCliente(Cliente cli) {
		String retorno = "";

		// Valida campos nulos
		if (cli.getNome() == null || cli.getNome().isEmpty()) {
			retorno = "Nome do cliente é obrigatório.";
			System.out.println(retorno);
			return retorno;
		}

		// Valida campos nulos
		if (cli.getEmail() == null || cli.getEmail().isEmpty()) {
			retorno = "Email do cliente é obrigatório.";
			System.out.println(retorno);
			return retorno;
		} else {
			String[] usuario = cli.getEmail().split("@");
			cli.setUsuario(usuario[0]);
		}

		// Valida email duplicado
		if (existeCliente(cli.getEmail())) {
			retorno = "Já existe um cadastro para o email " + cli.getEmail() + ".";
			System.out.println(retorno);
			return retorno;
		}

		return retorno;

	}
	
	public List<Cliente> consultaClientePorParteDoNome(String nomeCliente){
		List<Cliente> listaClientes = new ArrayList<Cliente>(); //Instancia o retorno
		
		listaClientes = cliDAO.consultaClientePorParteDoNome(nomeCliente);
		
		return listaClientes;
	}
	
	public List<Usuario> consultaUsuarioPorParteDoNome(String nomeUsuario){
		List<Usuario> listaUsuario = new ArrayList<Usuario>(); //Instancia o retorno
		
		listaUsuario = cliDAO.consultaUsuarioPorParteDoNome(nomeUsuario);
		
		return listaUsuario;
	}

	//Adiciona um prestador na lista de recomendacoes do cliente
	//Retorna uma string vazia em caso de sucesso, e uma msg de erro em caso de falha
	public String recomendaPrestador(String idCliente, String idPrestador){
		//Busca a lista de prestadores que o cliente já recomendou
		List<String> listaPrestadoresRecomendadosPeloCliente = new ArrayList<String>();
		Cliente cli = new Cliente();
		cli = cliDAO.consultaClientePorId(idCliente);
		if(cli.getListaPrestadoresRecomendados()!=null){
			for(int i=0;i<cli.getListaPrestadoresRecomendados().size();i++){
				//Verifica se este cliente já recomendou este prestador
				if(cli.getListaPrestadoresRecomendados().get(i).getId()==idPrestador){
					return "Prestador ja foi recomendado!";
				}
				listaPrestadoresRecomendadosPeloCliente.add(cli.getListaPrestadoresRecomendados().get(i).getId());
			}
		}
		
		//Adiciona o novo prestador na lista
		listaPrestadoresRecomendadosPeloCliente.add(idPrestador);
		
		//Atualiza a lista do cliente
		if(cliDAO.atualizarPrestadoresReocomendadosById(idCliente, listaPrestadoresRecomendadosPeloCliente)){
			//Busca a lista de clientes que já recomendaram o prestador
			PrestadorDAO preDAO = new PrestadorDAOImpl();
			List<String> listaRecomendacoesRecebidasPeloPrestador = new ArrayList<String>();
			Prestador prestador = new Prestador();
			prestador = preDAO.consultaPrestadorPorId(idPrestador);
			if(prestador.getListaRecomendacoesRecebidas()!=null){
				for(int i=0;i<prestador.getListaRecomendacoesRecebidas().size();i++){
					//Verifica se este prestador já recebeu recomendacao deste cliente
					//obs: se entrar neste if, o banco está quebrado!!
					if(prestador.getListaRecomendacoesRecebidas().get(i).getId()==idCliente){
						return "Prestador ja foi recomendado! Entre em contato com o administrador do sistema!";
					}
					listaRecomendacoesRecebidasPeloPrestador.add(prestador.getListaRecomendacoesRecebidas().get(i).getId());
				}
			}
			
			//Adiciona o cliente na lista
			listaRecomendacoesRecebidasPeloPrestador.add(idCliente);
			
			//Atualiza a lista do prestador
			preDAO.atualizarRecomendacoesRecebidasById(idPrestador, listaRecomendacoesRecebidasPeloPrestador);
			
			return "";
		}else{
			return "Erro na operacao.";
		}
	}
}
