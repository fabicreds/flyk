package com.tcc.flyk.util;

import org.json.JSONObject;
import org.springframework.stereotype.Component;

import com.tcc.flyk.entity.form.EfetuarLoginForm;

@Component
public class EfetuarLoginUtil {
	
	public EfetuarLoginForm convertJSONToForm(String request) {
		EfetuarLoginForm form = new EfetuarLoginForm();
		try {
			JSONObject jObjt = new JSONObject(request);
			form.setEmail(jObjt.getString("email"));
			form.setSenha(jObjt.getString("senha"));
//			form.setLembrar(jObjt.getBoolean("lembrar"));
			return form;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

		
	}

}
