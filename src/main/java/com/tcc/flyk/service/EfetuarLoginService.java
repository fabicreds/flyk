package com.tcc.flyk.service;

import org.springframework.stereotype.Service;

import com.tcc.flyk.entity.Usuario;
import com.tcc.flyk.entity.form.EfetuarLoginForm;
import com.tcc.flyk.persistence.AdministradorDAO;
import com.tcc.flyk.persistence.impl.AdministradorDAOImpl;

@Service
public class EfetuarLoginService {

	private AdministradorDAO admDAO = new AdministradorDAOImpl();

	/**
	 * Retorno
	 * 1 - Usuario autenticado com sucesso
	 * 2 - Usuario inativo
	 * 3 - Não foi encontrado nenhum usuario com o valor informado
	 * 4 - Erro no processamento
	 * 5 - Senha incorreta
	 */
	public int efetuarLogin(EfetuarLoginForm form) {
		try {
			Usuario usuario = new Usuario();
			usuario = admDAO.consultaUsuarioAdministrador(form.getEmail());
			if(usuario==null){
				//não foi encontrado nenhum registro com aquele usuário
				return 3;
			}else {
				if(!usuario.isAtivo()){
					//usuario inativo
					return 2;
				}else{
					if(form.getSenha().equals(usuario.getSenha())){
						//usuario autenticado com sucesso
						return 1;
					}else{
						//senha incorreta
						return 5;
					}
				}
			}

		} catch (Exception e) {
			//erro no processamento
			return 4;
		}

	}

}
