package com.tcc.flyk.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
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

	private ClienteDAO cliDAO = new ClienteDAOImpl();

	private PrestadorDAO prestadorDAO = new PrestadorDAOImpl();

	public JSONObject efetuaBusca(String chaveBusca) {

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

}
