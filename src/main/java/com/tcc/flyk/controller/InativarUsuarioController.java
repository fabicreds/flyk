package com.tcc.flyk.controller;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tcc.flyk.entity.Usuario;
import com.tcc.flyk.service.InativarUsuarioService;

@Controller
public class InativarUsuarioController {

	private boolean indicadorAdministrador = false;

	@Autowired
	private InativarUsuarioService service;

	@RequestMapping(value = "/userPageInfos", method = RequestMethod.GET)
	public String iniciarUserPage() {
		return "userPageInfos";
	}

	@RequestMapping(value = "/buscarUsuarios", method = RequestMethod.POST)
	public @ResponseBody String buscarUsuarioInativar(@RequestBody String usuario) {

		String busca = convertJSON(usuario);
		List<Usuario> listaUsuarios = new ArrayList<Usuario>();

		if (indicadorAdministrador) {
			listaUsuarios = service.buscarAdministrador(busca);
		} else {
			listaUsuarios = service.buscarCliente(busca);
		}

		return convertListToString(listaUsuarios);
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

	private String convertListToString(List<Usuario> listaUsuarios) {
		JSONArray listaJSON = new JSONArray();
		for (Usuario usuario : listaUsuarios) {
			listaJSON.put(usuario.toJSON());
		}
		return listaJSON.toString();

	}

}
