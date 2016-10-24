package com.tcc.flyk.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.json.JSONObject;
import org.springframework.stereotype.Service;

import com.tcc.flyk.entity.Categoria;
import com.tcc.flyk.entity.Cliente;
import com.tcc.flyk.entity.Prestador;
import com.tcc.flyk.entity.enumerator.TipoCadastroEnum;
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

	public List<Prestador> consultaPrestadorPorParteDoNome(String nomePresador){

		List<Prestador> listaPrestador = new ArrayList<Prestador>(); //Instancia o retorno
		
		//listaPrestador = prestadorDAO.consultaPrestadorPorParteDoNome(nomePresador);
		
		return listaPrestador;
	}
	
	public List<Prestador> buscaServico(List<String> idCategorias, int qtdMinimaEstrelas, String nomePrestador, String cidadeDoCliente){
		List<Prestador> prestadores = new ArrayList<Prestador>();
		List<Prestador> prestadores1 = new ArrayList<Prestador>();//1° Prestadores premium da mesma cidade do cliente
		List<Prestador> prestadores2 = new ArrayList<Prestador>();//2° Prestadores normais da mesma cidade do cliente
		List<Prestador> prestadores3 = new ArrayList<Prestador>();//3° Prestadores premium de outras cidades
		List<Prestador> prestadores4 = new ArrayList<Prestador>();//4° Prestadores normais de outras cidades
		List<Prestador> resultado = new ArrayList<Prestador>();//lista de retornará para o controller
		
		//Busca os prestadores que se encaixam nos filtros
		PrestadorDAO preDAO = new PrestadorDAOImpl();
		prestadores = preDAO.buscaServico(idCategorias, qtdMinimaEstrelas, nomePrestador);
		
		//Ordena a lista com as seguintes prioridades
		//1° Prestadores premium da mesma cidade do cliente
		//2° Prestadores normais da mesma cidade do cliente
		//3° Prestadores premium de outras cidades
		//4° Prestadores normais de outras cidades
		for(int i=0;i<prestadores.size();i++){//varre a lista de prestadores que retornou do DAO
			//1° Prestadores premium da mesma cidade do cliente 
			if(prestadores.get(i).getTipoCadastro()==TipoCadastroEnum.PREMIUM && prestadores.get(i).getEndereco().getCidade().toUpperCase()==cidadeDoCliente.toUpperCase()){
				prestadores1.add(prestadores.get(i));
			}
			//2° Prestadores normais da mesma cidade do cliente
			if(prestadores.get(i).getTipoCadastro()==TipoCadastroEnum.PRESTADOR && prestadores.get(i).getEndereco().getCidade().toUpperCase()==cidadeDoCliente.toUpperCase()){
				prestadores2.add(prestadores.get(i));
			}
			//3° Prestadores premium de outras cidades
			if(prestadores.get(i).getTipoCadastro()==TipoCadastroEnum.PREMIUM && prestadores.get(i).getEndereco().getCidade().toUpperCase()!=cidadeDoCliente.toUpperCase()){
				prestadores3.add(prestadores.get(i));
			}
			//4° Prestadores normais de outras cidades
			if(prestadores.get(i).getTipoCadastro()==TipoCadastroEnum.PRESTADOR && prestadores.get(i).getEndereco().getCidade().toUpperCase()!=cidadeDoCliente.toUpperCase()){
				prestadores4.add(prestadores.get(i));
			}
		}

		
		//Depois de preencher todas as listas com a devida prioridade, preenche a lista resultado na ordem correta(pronta para exibir na tela)
		//1° Prestadores premium da mesma cidade do cliente
		for(int i=0;i<prestadores1.size();i++){
			resultado.add(prestadores1.get(i));
		}
		//2° Prestadores normais da mesma cidade do cliente
		for(int i=0;i<prestadores2.size();i++){
			resultado.add(prestadores2.get(i));
		}
		//3° Prestadores premium de outras cidades
		for(int i=0;i<prestadores3.size();i++){
			resultado.add(prestadores3.get(i));
		}
		//4° Prestadores normais de outras cidades
		for(int i=0;i<prestadores4.size();i++){
			resultado.add(prestadores4.get(i));
		}
		return prestadores;
	}
	

	//Adiciona um prestador na lista de recomendacoes dadas do prestador
	//Retorna uma string vazia em caso de sucesso, e uma msg de erro em caso de falha
	public String recomendaPrestador(String idPrestadorRecomendador, String idPrestadorRecomendado){
		//Busca a lista de prestadores que o cliente já recomendou
		List<String> listaPrestadoresRecomendadosPeloPrestador = new ArrayList<String>();
		Prestador prestadorRecomendador = new Prestador();
		prestadorRecomendador = prestadorDAO.consultaPrestadorPorId(idPrestadorRecomendador);
		if(prestadorRecomendador.getListaPrestadoresRecomendados()!=null){
			for(int i=0;i<prestadorRecomendador.getListaPrestadoresRecomendados().size();i++){
				//Verifica se este prestador já recomendou este prestador
				if(prestadorRecomendador.getListaPrestadoresRecomendados().get(i).getId()==idPrestadorRecomendado){
					return "Prestador ja foi recomendado!";
				}
				listaPrestadoresRecomendadosPeloPrestador.add(prestadorRecomendador.getListaPrestadoresRecomendados().get(i).getId());
			}
		}
		
		//Adiciona o novo prestador na lista
		listaPrestadoresRecomendadosPeloPrestador.add(idPrestadorRecomendado);
		
		//Atualiza a lista do prestador recomendador
		if(prestadorDAO.atualizarPrestadoresReocomendadosById(idPrestadorRecomendador, listaPrestadoresRecomendadosPeloPrestador)){
			//Busca a lista de usuarios que já recomendaram o prestador
			List<String> listaRecomendacoesRecebidasPeloPrestador = new ArrayList<String>();
			Prestador prestadorRecomendado = new Prestador();
			prestadorRecomendado = prestadorDAO.consultaPrestadorPorId(idPrestadorRecomendado);
			if(prestadorRecomendado.getListaRecomendacoesRecebidas()!=null){
				for(int i=0;i<prestadorRecomendado.getListaRecomendacoesRecebidas().size();i++){
					//Verifica se este prestador já recebeu recomendacao deste prestador
					//obs: se entrar neste if, o banco está quebrado!!
					if(prestadorRecomendado.getListaRecomendacoesRecebidas().get(i).getId()==idPrestadorRecomendador){
						return "Prestador ja foi recomendado! Entre em contato com o administrador do sistema!";
					}
					listaRecomendacoesRecebidasPeloPrestador.add(prestadorRecomendado.getListaRecomendacoesRecebidas().get(i).getId());
				}
			}
			
			//Adiciona o cliente na lista
			listaRecomendacoesRecebidasPeloPrestador.add(idPrestadorRecomendador);
			
			//Atualiza a lista do prestador
			prestadorDAO.atualizarRecomendacoesRecebidasById(idPrestadorRecomendado, listaRecomendacoesRecebidasPeloPrestador);
			
			return "";
		}else{
			return "Erro na operacao.";
		}
	}
}
