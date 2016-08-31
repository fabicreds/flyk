package com.tcc.flyk.service;


import javax.annotation.Resource;

import org.json.JSONObject;
import org.springframework.stereotype.Service;

import com.tcc.flyk.entity.Cliente;
import com.tcc.flyk.entity.Usuario;
import com.tcc.flyk.entity.enumerator.TipoCadastroEnum;
import com.tcc.flyk.entity.form.EfetuarLoginForm;
import com.tcc.flyk.persistence.AdministradorDAO;
import com.tcc.flyk.persistence.impl.AdministradorDAOImpl;
import com.tcc.flyk.persistence.impl.ClienteDAOImpl;
import com.tcc.flyk.util.ClienteUtil;

@Service
public class EfetuarLoginService {

	private AdministradorDAO admDAO = new AdministradorDAOImpl();
	private ClienteDAOImpl cliDAO = new ClienteDAOImpl();

	@Resource
	private ClienteUtil util;
	
	public String efetuarLogin(EfetuarLoginForm form) {
		try {

			Usuario usuario = new Usuario();
			
         // testeCliente();
			usuario = admDAO.consultaUsuarioAdministrador(form.getEmail());

			if (usuario == null) {
				usuario = cliDAO.consultaLogin(form.getEmail());

				if (usuario == null)
					return mensagemErro("Usuário ou senha incorreto!");
			}

			if (!usuario.isAtivo()) {
				// usuario inativo
				return mensagemErro("Usuário inativo!");
			} else {
				if (form.getSenha().equals(usuario.getSenha())) {
					// usuario autenticado com sucesso
					if(usuario.getTipoCadastro() == TipoCadastroEnum.CLIENTE){
						Cliente cliente = new Cliente();
						cliente = cliDAO.consultaClientePorId(usuario.getId());
						return mensagemSucesso(cliente);
					}
					return mensagemSucesso(usuario);
				} else {
					// senha incorreta
					return mensagemErro("Usuário ou senha incorreto!");
				}
			}

		} catch (Exception e) {
			// erro no processamento
			return mensagemErro("Erro no processamento!" + e.fillInStackTrace() + e.getCause() + e.getLocalizedMessage()
					+ e.getMessage());
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
		jObjt.put("tipoCadastroDescricao" , usuario.getTipoCadastro().getDescricao());
		jObjt.put("id", usuario.getId());
		return jObjt.toString();
	}
	
	private String mensagemSucesso(Cliente cliente) {
		JSONObject jObjt = new JSONObject();
		jObjt.put("retorno", "sucesso");
		jObjt.put("usuario", cliente.getEmail());
		jObjt.put("tipoCadastro", cliente.getTipoCadastro().getCodigo() );
		jObjt.put("tipoCadastroDescricao" , cliente.getTipoCadastro().getDescricao());
		
		jObjt.put("cliente" , util.toJSON(cliente));
		
		return jObjt.toString();
	}

}
