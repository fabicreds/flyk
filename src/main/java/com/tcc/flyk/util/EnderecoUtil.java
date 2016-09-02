package com.tcc.flyk.util;

import org.json.JSONObject;
import org.springframework.stereotype.Component;

import com.tcc.flyk.entity.Endereco;

@Component
public class EnderecoUtil {

	public JSONObject toJSON(Endereco end) {
		JSONObject jObjt = new JSONObject();
		if (end.getLogradouro() != null) {
			jObjt.put("logradouro", end.getLogradouro());
		}
		if (end.getBairro() != null) {
			jObjt.put("bairro", end.getBairro());
		}
		jObjt.put("numero", end.getNumero());
		if (end.getComplemento() != null) {
			jObjt.put("complemento", end.getComplemento());
		}
		if (end.getCep() != null) {
			jObjt.put("cep", end.getCep());
		}
		if (end.getCidade() != null) {
			jObjt.put("cidade", end.getCidade());
		}
		if (end.getEstado() != null) {
			jObjt.put("estado", end.getEstado());
		}
		return jObjt;
	}

}
