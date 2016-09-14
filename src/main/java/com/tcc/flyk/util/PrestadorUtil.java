package com.tcc.flyk.util;

import java.util.List;

import javax.annotation.Resource;

import org.json.JSONObject;
import org.springframework.stereotype.Component;

import com.tcc.flyk.entity.Categoria;
import com.tcc.flyk.entity.Cliente;
import com.tcc.flyk.entity.Compromisso;
import com.tcc.flyk.entity.Prestador;

@Component
public class PrestadorUtil {
	
	@Resource
	private ContratoUtil contratoUtil;
	
	@Resource
	private ClienteUtil clienteUtil;
	
	@Resource
	private CompromissoUtil compromissoUtil;
	
	public JSONObject toJSON(Prestador prestador) {
		JSONObject jObjt = new JSONObject();
		jObjt = clienteUtil.toJSON((Cliente) prestador);
		if(prestador.getCnpj()!=null){
		jObjt.put("cnpj", prestador.getCnpj());
		}
		if(prestador.getListaServicos()!=null){
		jObjt.put("listaServicos", listaServicosJSON(prestador.getListaServicos()));
		}
		if(prestador.getListaRecomendacao()!=null){
		jObjt.put("listaRecomendacao", listaRecomendacaoJSON(prestador.getListaRecomendacao()));
		}
		if(prestador.getListaServicosContratados()!=null){
		jObjt.put("listaServicosContratados", listaServicosContratadosJSON(prestador.getListaServicosContratados()));
		}
		if(prestador.getListaRecomendacoesRecebidas()!=null){
		jObjt.put("listaRecomendacoesRecebidas", listaRecomendacoesRecebidasJSON(prestador.getListaRecomendacoesRecebidas()));
		}
		if(prestador.getValorPremium()!=null){
		jObjt.put("valorPremium", prestador.getValorPremium());
		}
		if(prestador.getPesoBusca()!=0){
		jObjt.put("pesoBusca", prestador.getPesoBusca());
		}
		return jObjt;
	}

	private JSONObject listaServicosJSON(List<Categoria> listaServicos) {
		JSONObject jObjt = new JSONObject();
		if (listaServicos != null) {
			int i = 0;
			for (Categoria servico : listaServicos) {
				JSONObject jObjt1 = new JSONObject();
				jObjt1.put("descricao", servico.getDescricao_categoria());
				jObjt.put("servico" + i, jObjt1);
				i++;
			}
		}
		return jObjt;
	}
	
	private JSONObject listaRecomendacaoJSON(List<Cliente> listaRecomendacao) {
		JSONObject jObjt = new JSONObject();
		if (listaRecomendacao != null) {
			int i = 0;
			for (Cliente cliente : listaRecomendacao) {
				JSONObject jObjt1 = clienteUtil.toJSON(cliente);
				jObjt.put("recomendacao" + i, jObjt1);
				i++;
			}
		}
		return jObjt;
	}
	
	private JSONObject listaServicosContratadosJSON(List<Compromisso> listaServicosContratados) {
		JSONObject jObjt = new JSONObject();
		if (listaServicosContratados != null) {
			int i = 0;
			for (Compromisso compromisso : listaServicosContratados) {
				JSONObject jObjt1 = compromissoUtil.toJSON(compromisso);
				jObjt.put("compromisso" + i, jObjt1);
				i++;
			}
		}
		return jObjt;
	}
	
	private JSONObject listaRecomendacoesRecebidasJSON(List<Cliente> listaRecomendacoesRecebidas) {
		JSONObject jObjt = new JSONObject();
		if (listaRecomendacoesRecebidas != null) {
			int i = 0;
			for (Cliente cliente : listaRecomendacoesRecebidas) {
				JSONObject jObjt1 = clienteUtil.toJSON(cliente);
				jObjt.put("recomendacao" + i, jObjt1);
				i++;
			}
		}
		return jObjt;
	}
}
