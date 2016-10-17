package com.tcc.flyk.util;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.annotation.Resource;

import org.json.JSONObject;
import org.springframework.stereotype.Component;

import com.tcc.flyk.entity.Compromisso;

@Component
public class CompromissoUtil {

	@Resource
	private ContratoUtil contratoUtil;

	private SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
	private SimpleDateFormat formatSimples = new SimpleDateFormat("dd/MM/yyyy");

	public JSONObject toJSON(Compromisso compromisso) {
		JSONObject jObjt = new JSONObject();
		if (compromisso.getContrato() != null) {
			jObjt.put("contrato", contratoUtil.toJSON(compromisso.getContrato()));
		}
		jObjt.put("indicadorFerias", compromisso.isIndicadorFerias());
		if (compromisso.getDataInicio() != null) {
			jObjt.put("dataInicio", format.format(compromisso.getDataInicio()));
		}
		if (compromisso.getDataFim() != null) {
			jObjt.put("dataFim", format.format(compromisso.getDataFim()));
		}
		if (compromisso.getStatus() != null) {
			jObjt.put("status", compromisso.getStatus().getCodigo());
			jObjt.put("statusDescricao", compromisso.getStatus().getDescricao());
		}
		return jObjt;
	}

	@SuppressWarnings("deprecation")
	public Compromisso cadastroToCompromisso(JSONObject json) {
		Compromisso compromisso = new Compromisso();

		// DADOS DO CONTRATO DO COMPROMISSO
		if (!(json.isNull("idCliente") && json.isNull("idPrestador") && json.isNull("idCategoriaServico"))) {
			compromisso.setContrato(contratoUtil.cadastroToContrato(json));
		}

		try {
			if (!json.isNull("dataInicio") && !json.isNull("hora_inicio") && !json.isNull("minuto_inicio")) {
				Date dataInicio = new Date();
				dataInicio = formatSimples.parse(json.getString("dataInicio"));
				dataInicio.setHours(json.getInt("hora_inicio"));
				dataInicio.setMinutes(json.getInt("minuto_inicio"));
				compromisso.setDataInicio(dataInicio);
			}
		} catch (Exception e) {
			compromisso.setDataInicio(null);
		}

		try {
			if (!json.isNull("dataFim") && !json.isNull("hora_fim") && !json.isNull("minuto_fim")) {
				Date dataFim = new Date();
				dataFim = formatSimples.parse(json.getString("dataFim"));
				dataFim.setHours(json.getInt("hora_fim"));
				dataFim.setMinutes(json.getInt("minuto_fim"));
				compromisso.setDataFim(dataFim);
			}
		} catch (Exception e) {
			compromisso.setDataFim(null);
		}
		return compromisso;
	}

	public Compromisso toCompromisso(JSONObject json) {
		Compromisso compromisso = new Compromisso();
		if (!json.isNull("contrato")) {
			compromisso.setContrato(contratoUtil.toContrato((JSONObject) json.get("contrato")));
		}
		if (!json.isNull("dataInicio")) {
			try {
				compromisso.setDataInicio(format.parse(json.getString("dataInicio")));
			} catch (Exception e) {
				compromisso.setDataInicio(null);
			}
		}
		if (!json.isNull("dataFim")) {
			try {
				compromisso.setDataFim(format.parse(json.getString("dataFim")));
			} catch (Exception e) {
				compromisso.setDataFim(null);
			}
		}
		if (!json.isNull("indicadorFerias")) {
			compromisso.setIndicadorFerias(json.getBoolean("indicadorFerias"));
		}
		if (!json.isNull("status")) {
			compromisso.setStatus(json.getInt("status"));
		}
		return compromisso;
	}
}
