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
	
	public Endereco toEndero(JSONObject json){
		Endereco endereco = new Endereco();
		if (!json.isNull("cep")) {
			endereco.setCep(json.getString("cep"));
		}
		if (!json.isNull("logradouro")){
			endereco.setLogradouro(json.getString("logradouro"));
		}
		if (!json.isNull("numero")){
			endereco.setNumero(json.getInt("numero"));
		}
		if (!json.isNull("comp")){
			endereco.setComplemento(json.getString("comp"));
		}
		if (!json.isNull("bairro")){
			endereco.setBairro(json.getString("bairro"));
		}
		if (!json.isNull("cidade")){
			endereco.setCidade(json.getString("cidade"));
		}
		if (!json.isNull("estado")){
			endereco.setEstado(json.getString("estado"));
		}
		return endereco;
	}

}
