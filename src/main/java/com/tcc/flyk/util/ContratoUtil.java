package com.tcc.flyk.util;

import java.text.SimpleDateFormat;

import javax.annotation.Resource;

import org.json.JSONObject;
import org.springframework.stereotype.Component;

import com.tcc.flyk.entity.AvaliacaoPrestador;
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
			jObjt.put("custo", contrato.getCusto().toString().replace(".", ","));
		}
		if (contrato.getDescricaoServico() != null) {
			jObjt.put("descricaoServico", contrato.getDescricaoServico());
		}
		return jObjt;
	}

	public Contrato cadastroToContrato(JSONObject json) {
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

		if (!json.isNull("descricao_servico")) {
			contrato.setDescricaoServico(json.getString("descricao_servico"));
		}
		return contrato;
	}

	public Contrato toContrato(JSONObject json) {
		Contrato contrato = new Contrato();
		if (!json.isNull("cliente")) {
			Cliente cliente = new Cliente();
			JSONObject jsonCliente = (JSONObject) json.get("cliente");
			if (!jsonCliente.isNull("id")) {
				cliente.setId(jsonCliente.getString("id"));
			}
			contrato.setCliente(cliente);
		}
		if (!json.isNull("prestador")) {
			Prestador prestador = new Prestador();
			JSONObject jsonPrestador = (JSONObject) json.get("prestador");
			if (!jsonPrestador.isNull("id")) {
				prestador.setId(jsonPrestador.getString("id"));
			}
			contrato.setPrestador(prestador);
		}
		if (!json.isNull("servico")) {
			Categoria categoria = new Categoria();
			JSONObject jsonCategoria = (JSONObject) json.get("servico");
			if (!jsonCategoria.isNull("id")) {
				categoria.setId(jsonCategoria.getString("id"));
			}
			contrato.setServico(categoria);
		}
		if (!json.isNull("avaliacaoPrestador")) {
			AvaliacaoPrestador avaliacaoPrestador = new AvaliacaoPrestador();
			JSONObject jsonAvaliacaoPrestador = (JSONObject) json.get("avaliacaoPrestador");
			if (!jsonAvaliacaoPrestador.isNull("avaliacaoTempo")) {
				avaliacaoPrestador.setAvaliacaoPontualidade(jsonAvaliacaoPrestador.getInt("avaliacaoTempo"));
			}
			if (!jsonAvaliacaoPrestador.isNull("avaliacaoQualidade")) {
				avaliacaoPrestador.setAvaliacaoQualidade(jsonAvaliacaoPrestador.getInt("avaliacaoQualidade"));
			}
			if (!jsonAvaliacaoPrestador.isNull("avaliacaoPreco")) {
				avaliacaoPrestador.setAvaliacaoPreco(jsonAvaliacaoPrestador.getInt("avaliacaoPreco"));
			}
			if (!jsonAvaliacaoPrestador.isNull("avaliacaoProfissionalismo")) {
				avaliacaoPrestador.setAvaliacaoProfissionalismo(jsonAvaliacaoPrestador.getInt("avaliacaoProfissionalismo"));
			}
			contrato.setAvaliacaoPrestador(avaliacaoPrestador);
		}
		if (!json.isNull("descricaoServico")) {
			contrato.setDescricaoServico(json.getString("descricaoServico"));
		}
		if (!json.isNull("custo")) {
			Double custo = new Double(json.getString("custo").replace(",", "."));
			contrato.setCusto(custo);
		}
		return contrato;
	}
}
