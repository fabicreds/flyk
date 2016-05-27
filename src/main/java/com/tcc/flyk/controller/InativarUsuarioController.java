package com.tcc.flyk.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tcc.flyk.entity.Usuario;
import com.tcc.flyk.entity.enumerator.TipoCadastroEnum;
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
	

	@RequestMapping(value = "/modalAlertInactiveUser", method = RequestMethod.GET)
	public String abrirModalAlert() {
		return "modalAlertInactiveUser";
	}


	@RequestMapping(value = "/userPageInfos", method = RequestMethod.GET)
	public String iniciarUserPage() {
		return "userPageInfos";
	}

	@RequestMapping(value = "/buscarUsuarios", method = RequestMethod.POST)
	public @ResponseBody String buscarUsuarioInativar(@RequestBody String request) {
		try {
			BuscarUsuarioInativarForm form = util.convertJSONToForm(request);
			List<Usuario> listaUsuarios = new ArrayList<Usuario>();

			if (form.isCheckAdministrador()) {
				listaUsuarios = service.buscarAdministrador(form.getUsuarioBusca());
			} else {
				listaUsuarios = service.buscarCliente(form.getUsuarioBusca());
			}
			
			if (listaUsuarios.isEmpty()) {
				return null;
			} else {
				return usuarioUtil.convertListToString(listaUsuarios);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@RequestMapping(value = "/inativarUsuario", method = RequestMethod.POST)
	public @ResponseBody String inativarUsuario(@RequestBody String request) {
		try{
			Usuario usuario = util.convertJSONToUsuario(request);
			boolean atualizado = false;
			if(usuario.getTipoCadastro() == TipoCadastroEnum.ADMINISTRADOR){
				atualizado = service.atualizarStatusUsuario(usuario);
			}
			if(atualizado){
				JSONObject jObjt = new JSONObject();
				jObjt.put("retorno", "sucesso");
				jObjt.put("mensagem", "Usuário atualizado com sucesso");
				return jObjt.toString();
			}else{
				JSONObject jObjt = new JSONObject();
				jObjt.put("retorno", "erro");
				jObjt.put("mensagem", "Erro na atualização do usuario!");
				return jObjt.toString();
			}
		}catch(Exception e){
			return null;
		}
		
	}

}
