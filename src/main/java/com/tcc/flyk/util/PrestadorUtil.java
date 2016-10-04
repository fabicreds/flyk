package com.tcc.flyk.util;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

import com.tcc.flyk.entity.Categoria;
import com.tcc.flyk.entity.Cliente;
import com.tcc.flyk.entity.Compromisso;
import com.tcc.flyk.entity.Prestador;
import com.tcc.flyk.entity.Privacidade;
import com.tcc.flyk.entity.Telefone;

@Component
public class PrestadorUtil {

	@Resource
	private ContratoUtil contratoUtil;

	@Resource
	private ClienteUtil clienteUtil = new ClienteUtil();

	@Resource
	private CompromissoUtil compromissoUtil;

	@Resource
	private TelefoneUtil telefoneUtil;

	@Resource
	private EnderecoUtil enderecoUtil;
	

	@Resource
	private PrivacidadeUtil privacidadeUtil = new PrivacidadeUtil();

	private SimpleDateFormat formatoSimples = new SimpleDateFormat("dd/MM/yyyy");

	public JSONObject toJSON(Prestador prestador) {
		JSONObject jObjt = new JSONObject();
		jObjt = clienteUtil.toJSON((Cliente) prestador);
		if (prestador.getCnpj() != null) {
			jObjt.put("cnpj", prestador.getCnpj());
		}
		if (prestador.getListaCategoriaServicosPrestados() != null) {
			jObjt.put("numCategoriaServicosPrestados",prestador.getListaCategoriaServicosPrestados().size());
			jObjt.put("listaCategoriaServicosPrestados",
					listaServicosPrestadorJSON(prestador.getListaCategoriaServicosPrestados()));
		}
		if (prestador.getListaContratosServicosPrestados() != null) {
			jObjt.put("listaContratosServicosPrestados",
					listaServicosContratadosJSON(prestador.getListaContratosServicosPrestados()));
		}
		if (prestador.getListaRecomendacoesRecebidas() != null) {
			jObjt.put("listaRecomendacoesRecebidas",
					listaRecomendacoesRecebidasJSON(prestador.getListaRecomendacoesRecebidas()));
		}
		if (prestador.getValorPremium() != null) {
			jObjt.put("valorPremium", prestador.getValorPremium());
		}
		if (prestador.getPesoBusca() != 0) {
			jObjt.put("pesoBusca", prestador.getPesoBusca());
		}
		return jObjt;
	}

	private JSONObject listaServicosPrestadorJSON(List<Categoria> listaServicos) {
		JSONObject jObjt = new JSONObject();
		if (listaServicos != null) {
			int i = 0;
			for (Categoria servico : listaServicos) {
				JSONObject jObjt1 = new JSONObject();
				jObjt1.put("id", servico.getId());
				jObjt1.put("nome", servico.getNomeCategoria());
				jObjt1.put("descricao", servico.getDescricaoCategoria());
				jObjt1.put("inicio", servico.getInicioVigencia());
				jObjt1.put("status", servico.getStatusCategoria());
				jObjt1.put("num", i + 1);
				jObjt.put("servico" + i, jObjt1);
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

	public Prestador toPrestador(JSONObject json) {
		Prestador prestador = new Prestador();

		if (!json.isNull("id") && !json.getString("id").isEmpty()) {
			prestador.setId(json.getString("id"));
		}
		
		if (!json.isNull("nome") && !json.getString("nome").isEmpty()) {
			prestador.setNome(json.getString("nome"));
		}
		if (!json.isNull("email") && !json.getString("email").isEmpty()) {
			prestador.setEmail(json.getString("email"));
		}
		if (!json.isNull("alias") && !json.getString("alias").isEmpty()) {
			prestador.setAlias(json.getString("alias"));
		}
		if (!json.isNull("senha") && !json.getString("senha").isEmpty()) {
			prestador.setSenha(json.getString("senha"));
		}
		if (!json.isNull("cpf") && !json.getString("cpf").isEmpty()) {
			prestador.setCPF(json.getString("cpf"));
		}
		if (!json.isNull("tipoCadastro")) {
			prestador.setTipoCadastro(json.getInt("tipoCadastro"));
		}
		if (!json.isNull("datanascimento") && !json.getString("datanascimento").isEmpty()) {
			try {
				prestador.setNascimento(formatoSimples.parse(json.getString("datanascimento")));
			} catch (Exception e) {
				prestador.setNascimento(null);
			}
		}
		List<JSONObject> listaTelefones = new ArrayList<JSONObject>();

		if (!json.isNull("listaTelefone") && json.getJSONObject("listaTelefone") != null) {
			List<Telefone> telefones = new ArrayList<Telefone>();

			telefones = telefoneUtil.JSONToListaTelefone(json.getJSONObject(("listaTelefone")), telefones);
			prestador.setListaTelefone(telefones);

		} else {
			if (!json.isNull("telefone1")) {
				listaTelefones.add((JSONObject) json.get("telefone1"));
			}
			if (!json.isNull("telefone2")) {
				listaTelefones.add((JSONObject) json.get("telefone2"));
			}
			if (!listaTelefones.isEmpty()) {
				prestador.setListaTelefone(telefoneUtil.jsonToListaTelefone(listaTelefones));
			}
		}

		// DADOS DO ENDERE�O
		if (!json.isNull("endereco")) {
			JSONObject enderecoJSON = (JSONObject) json.get("endereco");
			if (enderecoJSON != null) {
				 prestador.setEndereco(enderecoUtil.toEndereco(enderecoJSON));
			}
		}

		// 'imagem' :$scope.imageSrc
		if (!json.isNull("imagem")) {
			prestador.setFotoPerfil(json.getString("imagem"));
		}

		if (!json.isNull("servicos")) {
			List<Categoria> listaServicosPrestados = new ArrayList<Categoria>();
			JSONArray jsonServicos = (JSONArray) json.getJSONArray("servicos");
			for (int i = 0; i < jsonServicos.length(); i++) {
				JSONObject jsonCategoria = (JSONObject) jsonServicos.get(i);
				if (jsonCategoria != null) {
					Categoria cat = new Categoria();
					if (!jsonCategoria.isNull("id")) {
						cat.setId(jsonCategoria.getString("id"));
					}
					if (!jsonCategoria.isNull("nome")) {
						cat.setNomeCategoria(jsonCategoria.getString("nome"));
					}
					listaServicosPrestados.add(cat);
				}
			}
			prestador.setListaCategoriaServicosPrestados(listaServicosPrestados);
		}
		
		if (!json.isNull("privacidade")) {
			Privacidade privacidade = new Privacidade();
			privacidade = privacidadeUtil.toPrivacidade(json.getJSONObject("privacidade"));
			prestador.setPrivacidade(privacidade);

		}

		return prestador;
	}
}
