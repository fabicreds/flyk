package com.tcc.flyk.util;

import java.text.SimpleDateFormat;
import java.util.List;

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
//			status_categoria
			if(categoria.getStatusCategoria()!=null){
				jObjt.put("status", categoria.getStatusCategoria());
			}
		}
		return jObjt;
	}
	
	public JSONObject listaCategoriaJSON(List<Categoria> listaCategorias) {
		JSONObject jObjt = new JSONObject();
		if (listaCategorias != null) {
			int i = 0;
			for (Categoria categoria : listaCategorias) {
				JSONObject jObjt1 = toJSON(categoria);
				jObjt.put("categoria" + i, jObjt1);
				i++;
			}
		}
		return jObjt;
	}

}
