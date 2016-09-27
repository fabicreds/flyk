package com.tcc.flyk.service;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.tcc.flyk.entity.Cliente;
import com.tcc.flyk.entity.Endereco;
import com.tcc.flyk.entity.Privacidade;
import com.tcc.flyk.entity.Telefone;
import com.tcc.flyk.entity.Usuario;
import com.tcc.flyk.entity.enumerator.CategoriaTelefoneEnum;
import com.tcc.flyk.entity.enumerator.OperadoraEnum;
import com.tcc.flyk.entity.enumerator.TipoCadastroEnum;
import com.tcc.flyk.entity.form.EfetuarLoginForm;
import com.tcc.flyk.persistence.AdministradorDAO;
import com.tcc.flyk.persistence.impl.AdministradorDAOImpl;
import com.tcc.flyk.persistence.impl.ClienteDAOImpl;

@Service
public class EfetuarLoginService {

	private AdministradorDAO admDAO = new AdministradorDAOImpl();
	private ClienteDAOImpl cliDAO = new ClienteDAOImpl();

	@Autowired
	private ProfilePageService profilePageService;

	public String efetuarLogin(EfetuarLoginForm form) {
		try {
			

			Usuario usuario = new Usuario();

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
					if (usuario.getTipoCadastro() != TipoCadastroEnum.ADMINISTRADOR) {
						return profilePageService.montarDadosPerfil(usuario.getId(), usuario.getTipoCadastro());
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
		jObjt.put("tipoCadastroDescricao", usuario.getTipoCadastro().getDescricao());
		jObjt.put("idUsuario", usuario.getId());
		return jObjt.toString();
	}

}
