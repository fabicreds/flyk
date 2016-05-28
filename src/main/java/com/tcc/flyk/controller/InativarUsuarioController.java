package com.tcc.flyk.controller;

import javax.annotation.Resource;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tcc.flyk.entity.Usuario;
import com.tcc.flyk.entity.form.BuscarUsuarioInativarForm;
import com.tcc.flyk.service.InativarUsuarioService;
import com.tcc.flyk.util.InativarUsuarioUtil;
import com.tcc.flyk.util.UsuarioUtil;

@Controller
public class InativarUsuarioController {

	@Autowired
	private InativarUsuarioService service;

	@Resource
	private InativarUsuarioUtil util;

	@Resource
	private UsuarioUtil usuarioUtil;
	



	@RequestMapping(value = "/userPageInfos", method = RequestMethod.GET)
	public String iniciarUserPage() {
		return "userPageInfos";
	}

	@RequestMapping(value = "/buscarUsuarios", method = RequestMethod.POST)
	public @ResponseBody String buscarUsuarioInativar(@RequestBody String request) {
		try {
			BuscarUsuarioInativarForm form = util.convertJSONToForm(request);
			Usuario usuario = new Usuario();

			if (form.isCheckAdministrador()) {
				usuario = service.buscarAdministrador(form.getUsuarioBusca());
			} else {
				usuario = service.buscarCliente(form.getUsuarioBusca());
			}

			if (usuario == null) {
				return null;
			} else {
				return usuarioUtil.toJSON(usuario).toString();
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@RequestMapping(value = "/inativarUsuario", method = RequestMethod.POST)
	public @ResponseBody String inativarUsuario(@RequestBody String request) {
		try {
			Usuario usuario = util.convertJSONToUsuario(request);
			boolean atualizado = false;
			if (usuario != null) {
				atualizado = service.atualizarStatusUsuario(usuario);
			}
			if (atualizado) {
				JSONObject jObjt = new JSONObject();
				jObjt.put("retorno", "sucesso");
				jObjt.put("mensagem", "Usuário atualizado com sucesso");
				return jObjt.toString();
			} else {
				JSONObject jObjt = new JSONObject();
				jObjt.put("retorno", "erro");
				jObjt.put("mensagem", "Erro na atualização do usuario!");
				return jObjt.toString();
			}
		} catch (Exception e) {
			JSONObject jObjt = new JSONObject();
			jObjt.put("retorno", "erro");
			jObjt.put("mensagem", "Erro na atualização do usuario!");
			return jObjt.toString();
		}

	}

}
