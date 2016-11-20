package com.tcc.flyk.util;

import java.text.SimpleDateFormat;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

import com.tcc.flyk.entity.Categoria;

@Component
public class CategoriaUtil {
	
	private SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
	
	public JSONObject toJSON(Categoria categoria) {
		JSONObject jObjt = new JSONObject();
		if(categoria!=null){
//			id
			if(categoria.getId()!=null){
				jObjt.put("id", categoria.getId());
			}
//			nome_categoria
			if(categoria.getNomeCategoria()!=null){
				jObjt.put("nome", categoria.getNomeCategoria());
			}
//			descricao_categoria
			if(categoria.getDescricaoCategoria()!=null){
				jObjt.put("descricao", categoria.getDescricaoCategoria());
			}
//			inicio_vigencia
			if(categoria.getInicioVigencia()!=null){
				jObjt.put("inicio_vigencia", format.format(categoria.getInicioVigencia()));
			}
			if(categoria.getFimVigencia()!=null){
				jObjt.put("fim_vigencia", format.format(categoria.getFimVigencia()));
			}else{
				jObjt.put("fim_vigencia", "-");
			}
//			status_categoria
			if(categoria.getStatusCategoria()!=null){
				jObjt.put("status", categoria.getStatusCategoria());
			}
		}
		return jObjt;
	}
	
	public JSONArray listaCategoriaJSON(List<Categoria> listaCategorias) {
		JSONArray jObjt = new JSONArray();
		if (listaCategorias != null) {
			for (Categoria categoria : listaCategorias) {
				jObjt.put(toJSON(categoria));
			}
		}
		return jObjt;
	}

}
