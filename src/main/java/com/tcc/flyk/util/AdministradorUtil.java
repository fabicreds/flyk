package com.tcc.flyk.util;

import org.json.JSONObject;
import org.springframework.stereotype.Component;

import com.tcc.flyk.entity.Administrador;

@Component
public class AdministradorUtil {

	public Administrador convertJSON(String adm){
		Administrador admin = new Administrador();
		JSONObject jObjt = new JSONObject(adm);
		try{
			admin.setNome(jObjt.getString("nome"));
			admin.setUsuario(jObjt.getString("usuario"));
			admin.setSenha(jObjt.getString("senha"));
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return admin;
	}
}
