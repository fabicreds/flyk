package com.tcc.flyk.util;

import java.text.SimpleDateFormat;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

import com.tcc.flyk.entity.Conversa;

@Component
public class ConversaUtil {

	private SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
	
	public JSONObject toJSON(Conversa conversa) {
		JSONObject jObjt = new JSONObject();
		if (conversa.getIdUsuario() != null) {
			jObjt.put("idUsuario", conversa.getIdUsuario());
		}
		if (conversa.getflagEnviadoRecebido() != null) {
			jObjt.put("flagEnviadoOuRecebido", conversa.getflagEnviadoRecebido());
		}
		if (conversa.getMsg() != null) {
			jObjt.put("msg", conversa.getMsg());
		}
		if (conversa.getData() != null) {
			jObjt.put("data", format.format(conversa.getData()));
		}
		return jObjt;
	}

	public JSONArray listaMensagensConversaJSON(List<Conversa> listaMensagensConversa) {
		JSONArray jObjt = new JSONArray();
		if (listaMensagensConversa != null) {
			int i = 0;
			for (Conversa conversa : listaMensagensConversa) {
				JSONObject jObjt1 = toJSON(conversa);
				jObjt1.put("conversa", i);
				jObjt.put(jObjt1);
				i++;
			}
		}
		return jObjt;
	}
}
