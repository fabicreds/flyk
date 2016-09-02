package com.tcc.flyk.util;

import javax.annotation.Resource;

import org.json.JSONObject;
import org.springframework.stereotype.Component;

import com.tcc.flyk.entity.Contrato;

@Component
public class ContratoUtil {

	@Resource
	private ClienteUtil clienteUtil;

	@Resource
	private PrestadorUtil prestadorUtil;

	@Resource
	private AvaliacaoPrestadorUtil avaliacaoPrestadorUtil;

	public JSONObject toJSON(Contrato contrato) {
		JSONObject jObjt = new JSONObject();
		if (contrato.getCliente() != null) {
			jObjt.put("cliente", clienteUtil.toJSON(contrato.getCliente()));
		}
		if (contrato.getPrestador() != null) {
			jObjt.put("prestador", prestadorUtil.toJSON(contrato.getPrestador()));
		}
		if (contrato.getServico() != null) {
			jObjt.put("servico", contrato.getServico().getDescricao());
		}
		if (contrato.getAvaliacaoPrestador() != null) {
			jObjt.put("avaliacaoPrestador", avaliacaoPrestadorUtil.toJSON(contrato.getAvaliacaoPrestador()));
		}
		if (contrato.getDataAvaliacaoServico() != null) {
			jObjt.put("dataAvaliacaoServico", contrato.getDataAvaliacaoServico());
		}
		if (contrato.getAvaliacaoContratante() != 0) {
			jObjt.put("avaliacaoContratante", contrato.getAvaliacaoContratante());
		}
		if (contrato.getCusto() != 0) {
			jObjt.put("custo", contrato.getCusto());
		}
		return jObjt;
	}
}
