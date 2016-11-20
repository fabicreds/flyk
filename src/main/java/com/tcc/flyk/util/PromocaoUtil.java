package com.tcc.flyk.util;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

import com.tcc.flyk.entity.Preco;
import com.tcc.flyk.entity.Promocao;

@Component
public class PromocaoUtil {

	private SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

	public JSONObject toJSON(Promocao promocao) {
		JSONObject json = new JSONObject();
		if (promocao.getId() != null) {
			json.put("id", promocao.getId());
		}
		if (promocao.getNomePromocao() != null) {
			json.put("nome", promocao.getNomePromocao());
		}
		if (promocao.getDescricao() != null) {
			json.put("descricao", promocao.getDescricao());
		}
		if (promocao.getStatus() != null) {
			json.put("status", promocao.getStatus());
		}
		if (promocao.getDataInicio() != null) {
			json.put("dataInicio", format.format(promocao.getDataInicio()));
		}
		if (promocao.getDataFim() != null) {
			json.put("dataFim", format.format(promocao.getDataFim()));
		}
		if (promocao.getListaPreco() != null) {
			JSONArray jsonPrecos = new JSONArray();
			int i=0;
			for(Preco preco:promocao.getListaPreco()){
				JSONObject precoJson = new JSONObject();
				precoJson.put("i", i);
				if(preco.getValor()!=null){
					precoJson.put("preco", preco.getValor());
				}
				if(preco.getCategoria()!=null){
					precoJson.put("categoria", preco.getCategoria());
				}
				jsonPrecos.put(precoJson);
				i++;
			}
			json.put("precos", jsonPrecos);
		}
		return json;
	}
	
	public Promocao convertJSON(String prom) {
		Promocao promo = new Promocao();
		JSONObject jObjt = new JSONObject(prom);
		ArrayList<Preco> arrayPrecoObj = new ArrayList<Preco>();

		try {

			SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
			Date dataInicio = formato.parse(jObjt.getString("dataini"));
			Date dataFim = formato.parse(jObjt.getString("datafim"));
			promo.setNomePromocao(jObjt.getString("nomeprom"));
			promo.setDescricao(jObjt.getString("descrprom"));
			promo.setDataFim(dataFim);
			promo.setDataInicio(dataInicio);

			JSONObject arrayPreco = new JSONObject(prom);

			String cat = arrayPreco.getString("listJson");

			JSONArray jsonArr = new JSONArray(cat);
			JSONObject jsonObj = new JSONObject();

			for (int i = 0; i < jsonArr.length(); i++) {
				jsonObj = jsonArr.getJSONObject(i);
				Preco p = new Preco();
				p.setCategoria(jsonObj.getString("nome"));
				Double val = Double.parseDouble(jsonObj.getString("valorpromocional"));
				p.setValor(val);
				arrayPrecoObj.add(p);
			}

			promo.setListaPreco(arrayPrecoObj);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return promo;
	}
	
	public JSONArray listaPromocaoJSON(List<Promocao> listaPromocao) {
		JSONArray jObjt = new JSONArray();
		if (listaPromocao != null) {
			for (Promocao prom : listaPromocao) {
				jObjt.put(toJSON(prom));
			}
		}
		return jObjt;
	}
}
