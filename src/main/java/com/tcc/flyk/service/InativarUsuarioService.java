package com.tcc.flyk.service;

import org.springframework.stereotype.Service;

import com.tcc.flyk.entity.Usuario;
import com.tcc.flyk.entity.enumerator.TipoCadastroEnum;
import com.tcc.flyk.persistence.AdministradorDAO;
import com.tcc.flyk.persistence.ClienteDAO;
import com.tcc.flyk.persistence.impl.AdministradorDAOImpl;
import com.tcc.flyk.persistence.impl.ClienteDAOImpl;

@Service
public class InativarUsuarioService {

	private AdministradorDAO admDAO = new AdministradorDAOImpl();
	private ClienteDAO clienteDAO = new ClienteDAOImpl();

	public Usuario buscarCliente(String cliente) {
		Usuario usuario = new Usuario();
		try {
			usuario = clienteDAO.consultaUsuario(cliente);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return usuario;
	}

	public Usuario buscarAdministrador(String adm) {
		Usuario usuario= new Usuario();
		try {
			usuario = admDAO.consultaUsuarioAdministrador(adm);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return usuario;
	}

	public boolean atualizarStatusUsuario(Usuario usuario) {
		try {
			//atualizando para o novo status
			if(usuario.isAtivo()){
				usuario.setAtivo(false);
			}else{
				usuario.setAtivo(true);
			}
			if(usuario.getTipoCadastro() == TipoCadastroEnum.ADMINISTRADOR){
				return admDAO.atualizarStatusAdministrador(usuario);
			}else{
				return clienteDAO.atualizarStatusCliente(usuario);
			}
		} catch (Exception e) {
			return false;
		}
	}
}
