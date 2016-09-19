package com.tcc.flyk.util;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;
import org.springframework.stereotype.Component;

import com.tcc.flyk.entity.Telefone;
import com.tcc.flyk.entity.enumerator.OperadoraEnum;

@Component
public class TelefoneUtil {

	public List<Telefone> jsonToListaTelefone(List<JSONObject> telefones) {
		List<Telefone> listaTelefone = new ArrayList<Telefone>();
		if (telefones != null) {
			for (JSONObject telefone : telefones) {
				Telefone tel = new Telefone();
				if (!telefone.isNull("number") ) {
					tel.setDdd(Integer.valueOf(telefone.getString("number").substring(1, 3)));
					tel.setNumero(telefone.getString("number").substring(4).replace("-", ""));
				}
				if (!telefone.isNull("categoria")) {
					JSONObject categoriaJSON = (JSONObject) telefone.get("categoria");
					tel.setCategoriaTelefone(categoriaJSON.getInt("id"));
				}
				if (!telefone.isNull("operadora")) {
					JSONObject operadoraJSON = (JSONObject) telefone.get("operadora");
					tel.setOperadora(operadoraJSON.getInt("id"));
				}
				listaTelefone.add(tel);
			}
		}
		return listaTelefone;
	}

	public JSONObject listaTelefoneJSON(List<Telefone> listaTelefone) {
		JSONObject jObjt = new JSONObject();
		if (listaTelefone != null) {
			int i = 0;
			for (Telefone tel : listaTelefone) {
				JSONObject jObjt1 = new JSONObject();
				if (tel.getDdd() != 0) {
					jObjt1.put("ddd", tel.getDdd());
				}
				if (tel.getNumero() != null) {
					jObjt1.put("numero", tel.getNumero());
				}
				if (tel.getCategoriaTelefone() != null) {
					jObjt1.put("categoria", tel.getCategoriaTelefone().getCodigo());
					jObjt1.put("categoriaDescricao", tel.getCategoriaTelefone().getDescricao());
				}
				if (tel.getOperadora() != null) {
					jObjt1.put("operadora", tel.getOperadora().getCodigo());
					jObjt1.put("operadoraDescricao", tel.getOperadora().getDescricao());
				} else {
					jObjt1.put("operadora", OperadoraEnum.OUTROS.getCodigo());
					jObjt1.put("operadoraDescricao", OperadoraEnum.OUTROS.getDescricao());
				}
				jObjt.put("telefone" + i, jObjt1);
				i++;
			}
		}
		return jObjt;
	}

}
