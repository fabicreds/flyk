package com.tcc.flyk.util;

import org.json.JSONObject;
import org.springframework.stereotype.Component;

import com.tcc.flyk.entity.Usuario;
import com.tcc.flyk.entity.form.BuscarUsuarioInativarForm;

@Component
public class InativarUsuarioUtil {

	public BuscarUsuarioInativarForm convertJSONToForm(String request) {
		BuscarUsuarioInativarForm form = new BuscarUsuarioInativarForm();
		try {
			JSONObject jObjt = new JSONObject(request);
			form.setUsuarioBusca(jObjt.getString("usuarioBusca"));
			form.setCheckAdministrador(jObjt.getBoolean("checkAdministrador"));
		} catch (Exception e) {
			e.printStackTrace();
		}

		return form;
	}

	public Usuario convertJSONToUsuario(String request) {

		try {
			Usuario usuario = new Usuario();
			JSONObject jObjt = new JSONObject(request);
			usuario.setUsuario(jObjt.getString("usuario"));
			usuario.setTipoCadastro(jObjt.getString("tipoCadastro"));
			usuario.setAtivo(jObjt.getBoolean("ativo"));
			return usuario;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
