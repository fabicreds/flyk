package com.tcc.flyk.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.json.JSONObject;
import org.springframework.stereotype.Service;

import com.tcc.flyk.entity.Cliente;
import com.tcc.flyk.entity.Prestador;
import com.tcc.flyk.entity.Usuario;
import com.tcc.flyk.entity.enumerator.TipoCadastroEnum;
import com.tcc.flyk.persistence.ClienteDAO;
import com.tcc.flyk.persistence.PrestadorDAO;
import com.tcc.flyk.persistence.impl.ClienteDAOImpl;
import com.tcc.flyk.persistence.impl.PrestadorDAOImpl;
import com.tcc.flyk.util.ClienteUtil;
import com.tcc.flyk.util.PrestadorUtil;

@Service
public class SearchService {

	@Resource
	public ClienteUtil cUtil = new ClienteUtil();

	@Resource
	public PrestadorUtil pUtil = new PrestadorUtil();
	
	private ProfileService profileService = new ProfileService();

	private ClienteDAO cliDAO = new ClienteDAOImpl();

	private PrestadorDAO prestadorDAO = new PrestadorDAOImpl();
	
	
	/*
	 * Metodos de busca, reescrito com assinaturas diferentes para busca de clientes e de prestadores (servico)
	 * Busca por cliente - parametro Nome
	 * Busca por servico - parametro Lista de categorias, nomePrestador
	 */
	
	public JSONObject efetuaBusca(List<String> idCategorias, int qtdMinimaEstrelas, String nomePrestador) {
		
		JSONObject jsonListaCli = new JSONObject();
		JSONObject jsonCli = new JSONObject();

		List<Prestador> listaPrestador = new ArrayList<Prestador>();
		listaPrestador=prestadorDAO.buscaServico(idCategorias, qtdMinimaEstrelas, nomePrestador);
		
		if (listaPrestador == null) {

			jsonListaCli.put("retornoVazio", "Nenhum cliente encontrado.");

		} else {
		for (int i = 0; i < listaPrestador.size(); i++) {
			Prestador prestador = new Prestador();
			prestador = listaPrestador.get(i);
			profileService.buscarListaServicos(prestador);
			
			jsonCli.put("prestador" + i, pUtil.toJSON(prestador));
		}
		}
		
		return jsonListaCli.put("listaClientes", jsonCli);

	}
	

	public JSONObject efetuaBusca(String chaveBusca) {
		/*
		 * COMENTAR BEM O METODO ABAIXO
		 */
		
		

		JSONObject jsonCli = new JSONObject();

		List<Usuario> listaUsuario = new ArrayList<Usuario>();

		listaUsuario = cliDAO.consultaUsuarioPorParteDoNome(chaveBusca);

		JSONObject jsonListaCli = new JSONObject();

		if (listaUsuario == null) {

			jsonListaCli.put("retornoVazio", "Nenhum cliente encontrado.");

		} else {

			for (int i = 0; i < listaUsuario.size(); i++) {

				Usuario usuario = new Usuario();

				usuario = listaUsuario.get(i);

				if (usuario.getTipoCadastro() == TipoCadastroEnum.CLIENTE) {
					Cliente cliente = new Cliente();

					cliente.setId(usuario.getId());

					cliente = cliDAO.consultaClientePorId(cliente.getId());

					jsonCli.put("cliente" + i, cUtil.toJSON(cliente));
				} else {

					Prestador prestador = new Prestador();

					prestador.setId(usuario.getId());

					prestador = prestadorDAO.consultaPrestadorPorId(prestador.getId());

					jsonCli.put("cliente" + i, pUtil.toJSON(prestador));

				}

				// System.out.println(cUtil.toJSON(cliente).toString());

				// jsonArrayCli.put( i, jsonCli);

			}

			/*
			 * List<Cliente> listaCli =new ArrayList<Cliente>();
			 * 
			 * listaCli=cliDAO.consultaClientePorParteDoNome(chaveBusca);
			 * 
			 * 
			 * for (int i=0; i < listaCli.size(); i++) {
			 * 
			 * 
			 * 
			 * Cliente cliente = new Cliente();
			 * 
			 * cliente = listaCli.get(i);
			 * 
			 * 
			 * jsonCli.put("cliente" + i ,cUtil.toJSON(cliente));
			 * 
			 * //System.out.println(cUtil.toJSON(cliente).toString());
			 * 
			 * //jsonArrayCli.put( i, jsonCli);
			 * 
			 * 
			 * 
			 * }
			 * 
			 */

		}

		return jsonListaCli.put("listaClientes", jsonCli);

	}
	
	public JSONObject buscaServico(List<String> idCategorias, int qtdMinimaEstrelas, String nomePrestador, String cidadeDoCliente){
		
		JSONObject JSONPrestadores = new JSONObject();
		JSONObject listaJSONPrestadores = new JSONObject();
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
		//return prestadores;
		
		for (int i = 0; i < prestadores.size(); i++) {
			Prestador prestador = new Prestador();
			prestador = prestadores.get(i);
			profileService.buscarListaServicos(prestador);
			
			JSONPrestadores.put("prestador" + i, pUtil.toJSON(prestador));
		}
		
		
		return listaJSONPrestadores.put("listaClientes", JSONPrestadores);
	}



}
