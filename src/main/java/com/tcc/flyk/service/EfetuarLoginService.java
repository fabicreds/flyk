package com.tcc.flyk.service;

import org.json.JSONObject;
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
	 * 3 - N√£o foi encontrado nenhum usuario com o valor informado
	 * 4 - Erro no processamento
	 * 5 - Senha incorreta
	 */
	public String efetuarLogin(EfetuarLoginForm form) {
		try {
			Usuario usuario = new Usuario();
			usuario = admDAO.consultaUsuarioAdministrador(form.getEmail());
			if(usuario==null){
				//n√£o foi encontrado nenhum registro com aquele usu√°rio
				return mensagemErro("Usu·rio ou senha incorreto!");
			}else {
				if(!usuario.isAtivo()){
					//usuario inativo
					return mensagemErro("Usu·rio inativo!");
				}else{
					if(form.getSenha().equals(usuario.getSenha())){
						//usuario autenticado com sucesso
						return mensagemSucesso(usuario);
					}else{
						//senha incorreta
						return mensagemErro("Usu·rio ou senha incorreto!");
					}
				}
			}

		} catch (Exception e) {
			//erro no processamento
			return mensagemErro("Erro no processamento!");
		}

	}
	
	private String mensagemErro(String mensagem) {
		JSONObject jObjt = new JSONObject();
		jObjt.put("retorno", "erro");
		jObjt.put("mensagem", mensagem);
		return jObjt.toString();
	}

	private String mensagemSucesso(Usuario usuario) {
		JSONObject jObjt = new JSONObject();
		jObjt.put("retorno", "sucesso");
		jObjt.put("usuario", usuario.getUsuario());
		jObjt.put("tipoCadastro", usuario.getTipoCadastro().getCodigo());
		return jObjt.toString();
	}

}
