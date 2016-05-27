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
	
	public JSONObject toJSON(Usuario usuario){
		JSONObject jObjt = new JSONObject();
		jObjt.put("nome", usuario.getNome());
		jObjt.put("usuario", usuario.getUsuario());
		jObjt.put("email", usuario.getEmail());
		jObjt.put("tipoCadastro", usuario.getTipoCadastro());
		jObjt.put("ativo", usuario.isAtivo());
		if(usuario.isAtivo()){
			jObjt.put("btn", "Desativar" );
		}else{
			jObjt.put("btn", "Ativar");
		}
		return jObjt;
	}

}
