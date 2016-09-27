package com.tcc.flyk.service;

import javax.annotation.Resource;

import org.json.JSONObject;
import org.springframework.stereotype.Service;

import com.tcc.flyk.entity.Cliente;
import com.tcc.flyk.persistence.ClienteDAO;
import com.tcc.flyk.persistence.impl.ClienteDAOImpl;
import com.tcc.flyk.util.ClienteUtil;

@Service
public class AtualizaPerfilService {

	private ClienteDAO cliDAO = new ClienteDAOImpl();

	@Resource
	private ClienteUtil clienteUtil;

	public Cliente atualizaPerfil(String id, JSONObject jsonCli) {

		Cliente JSONToCli = clienteUtil.toCliente(jsonCli);
		Cliente c = new Cliente();
		c = cliDAO.atualizaCliente(id, JSONToCli);

		return cliDAO.atualizaCliente(id, JSONToCli);
	}
}
