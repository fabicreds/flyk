package com.tcc.flyk.util;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

import com.tcc.flyk.entity.Usuario;

@Component
public class UsuarioUtil {

	public String convertListToString(List<Usuario> listaUsuarios) {
		JSONArray listaJSON = new JSONArray();
		for (Usuario usuario : listaUsuarios) {
			listaJSON.put(toJSON(usuario));
		}
		return listaJSON.toString();

	}

	public JSONObject toJSON(Usuario usuario) {
		JSONObject jObjt = new JSONObject();
		if (usuario.getNome() != null) {
			jObjt.put("nome", usuario.getNome());
		}
		if (usuario.getUsuario() != null) {
			jObjt.put("usuario", usuario.getUsuario());
		}
		if (usuario.getEmail() != null) {
			jObjt.put("email", usuario.getEmail());
			String[] email = usuario.getEmail().split("@");
			jObjt.put("usuario",  email[0]);
		}
		if (usuario.getTipoCadastro() != null) {
			jObjt.put("tipoCadastro", usuario.getTipoCadastro().getCodigo());
			jObjt.put("tipoCadastroDescricao", usuario.getTipoCadastro().getDescricao());
		}
		jObjt.put("ativo", usuario.isAtivo());
		if (usuario.isAtivo()) {
			jObjt.put("btn", "Desativar");
		} else {
			jObjt.put("btn", "Ativar");
		}
		if (usuario.getFotoPerfil() != null) {
			jObjt.put("fotoPerfil", usuario.getFotoPerfil());
		}
		return jObjt;
	}

	public JSONArray listaUsuarioJSON(List<Usuario> listaUsuarios) {
		JSONArray jObjt = new JSONArray();
		if (listaUsuarios != null) {
			for (Usuario usuario : listaUsuarios) {
				jObjt.put(toJSON(usuario));
			}
		}
		return jObjt;
	}
}
