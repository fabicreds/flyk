package com.tcc.flyk.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.tcc.flyk.entity.Usuario;
import com.tcc.flyk.persistence.AdministradorDAO;
import com.tcc.flyk.persistence.ClienteDAO;
import com.tcc.flyk.persistence.impl.AdministradorDAOImpl;
import com.tcc.flyk.persistence.impl.ClienteDAOImpl;

@Service
public class InativarUsuarioService {

	private AdministradorDAO admDAO = new AdministradorDAOImpl();
	private ClienteDAO clienteDAO = new ClienteDAOImpl();

	public List<Usuario> buscarCliente(String cliente) {
		List<Usuario> listaUsuarios = new ArrayList<Usuario>();
		try {
			listaUsuarios = clienteDAO.consultaUsuario(cliente);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listaUsuarios;
	}

	public List<Usuario> buscarAdministrador(String adm) {
		List<Usuario> listaUsuarios = new ArrayList<Usuario>();
		try {
			listaUsuarios = admDAO.consultaNomeUsuarioAdministrador(adm);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listaUsuarios;
	}

	public boolean atualizarStatusUsuario(Usuario usuario) {
		try {
			return admDAO.atualizarStatusUsuario(usuario);
		} catch (Exception e) {
			return false;
		}
	}
}
