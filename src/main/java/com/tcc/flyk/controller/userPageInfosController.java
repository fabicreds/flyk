package com.tcc.flyk.controller;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.tcc.flyk.entity.Usuario;
import com.tcc.flyk.service.InativarUsuarioService;

@Controller
public class UserPageInfosController {

	private boolean indicadorAdministrador = false;

	@Autowired
	private InativarUsuarioService service;

	@RequestMapping(value = "/userPageInfos",method = RequestMethod.GET)
	public String iniciarUserPage() {
		return "userPageInfos";
	}

	@RequestMapping(value = "/buscarUsuarios", method =  RequestMethod.POST)
	public String buscarUsuarioInativar(@RequestBody String usuario, ModelMap model) {

		String busca = convertJSON(usuario);
		List<Usuario> listaUsuarios = new ArrayList<Usuario>();

		if (indicadorAdministrador) {
			listaUsuarios = service.buscarAdministrador(busca);
		} else {
			listaUsuarios = service.buscarCliente(busca);
		}

		model.addAttribute("listaUsuarios", listaUsuarios);
		return "userPageInfos";
	}

	private String convertJSON(String usuario) {
		String user = "";
		try {
			JSONObject jObjt = new JSONObject(usuario);
			user = jObjt.getString("usuarioBusca");
			if (jObjt.getBoolean("checkAdministrador")) {
				indicadorAdministrador = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return user;
	}

}
