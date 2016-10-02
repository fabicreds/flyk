package com.tcc.flyk.util;

import java.text.SimpleDateFormat;

import javax.annotation.Resource;

import org.json.JSONObject;
import org.springframework.stereotype.Component;

import com.tcc.flyk.entity.Categoria;
import com.tcc.flyk.entity.Cliente;
import com.tcc.flyk.entity.Contrato;
import com.tcc.flyk.entity.Prestador;

@Component
public class ContratoUtil {

	@Resource
	private ClienteUtil clienteUtil;

	@Resource
	private PrestadorUtil prestadorUtil;

	@Resource
	private AvaliacaoPrestadorUtil avaliacaoPrestadorUtil;

	@Resource
	private CategoriaUtil categoriaUtil;

	private SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

	public JSONObject toJSON(Contrato contrato) {
		JSONObject jObjt = new JSONObject();
		if (contrato.getCliente() != null) {
			jObjt.put("cliente", clienteUtil.toJSON(contrato.getCliente()));
		}
		if (contrato.getPrestador() != null) {
			jObjt.put("prestador", prestadorUtil.toJSON(contrato.getPrestador()));
		}
		if (contrato.getServico() != null) {
			jObjt.put("servico", categoriaUtil.toJSON(contrato.getServico()));
		}
		if (contrato.getAvaliacaoPrestador() != null) {
			jObjt.put("avaliacaoPrestador", avaliacaoPrestadorUtil.toJSON(contrato.getAvaliacaoPrestador()));
		}
		if (contrato.getDataAvaliacaoServico() != null) {
			jObjt.put("dataAvaliacaoServico", format.format(contrato.getDataAvaliacaoServico()));
		}
		if (contrato.getAvaliacaoContratante() != 0) {
			jObjt.put("avaliacaoContratante", contrato.getAvaliacaoContratante());
		}
		if (contrato.getCusto() != null) {
			jObjt.put("custo", contrato.getCusto());
		}
		return jObjt;
	}

	public Contrato toContrato(JSONObject json) {
		Contrato contrato = new Contrato();
		// 'idCliente' : $rootScope.data.id,
		if (!json.isNull("idCliente")) {
			Cliente c = new Cliente();
			c.setId(json.getString("idCliente"));
			contrato.setCliente(c);
		}
		// 'idPrestador' : $rootScope.data.amigo.id,
		if (!json.isNull("idPrestador")) {
			Prestador p = new Prestador();
			p.setId(json.getString("idPrestador"));
			contrato.setPrestador(p);
		}
		// 'idCategoriaServico' : $scope.servico.id,
		if (!json.isNull("idCategoriaServico")) {
			Categoria categoria = new Categoria();
			categoria.setId(json.getString("idCategoriaServico"));
			contrato.setServico(categoria);
		}
		return contrato;
	}
}
