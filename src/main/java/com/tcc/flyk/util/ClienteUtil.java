package com.tcc.flyk.util;

import java.text.SimpleDateFormat;
import java.util.List;

import javax.annotation.Resource;

import org.json.JSONObject;
import org.springframework.stereotype.Component;

import com.tcc.flyk.entity.Amizade;
import com.tcc.flyk.entity.Cliente;
import com.tcc.flyk.entity.Compromisso;
import com.tcc.flyk.entity.Conversa;
import com.tcc.flyk.entity.Prestador;
import com.tcc.flyk.entity.Telefone;
import com.tcc.flyk.entity.enumerator.OperadoraEnum;

@Component
public class ClienteUtil {

	@Resource
	private AmizadeUtil amizadeUtil;

	@Resource
	private PrivacidadeUtil privacidadeUtil;

	@Resource
	private PrestadorUtil prestadorUtil;

	@Resource
	private ConversaUtil conversaUtil;
	
	@Resource
	private EnderecoUtil enderecoUtil;
	
	@Resource
	private CompromissoUtil compromissoUtil;
	
	private SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

	public JSONObject toJSON(Cliente cliente) {
		JSONObject jObjt = new JSONObject();
		if (cliente.getId() != null) {
			jObjt.put("id", cliente.getId());
		}
		if (cliente.getNome() != null) {
			jObjt.put("nome", cliente.getNome());
		}
		if (cliente.getAlias() != null) {
			jObjt.put("alias", cliente.getAlias());
		}
		if (cliente.getEmail() != null) {
			jObjt.put("email", cliente.getEmail());
		}
		if (cliente.getSenha() != null) {
			jObjt.put("senha", cliente.getSenha());
		}
		if (cliente.getFacebookID() != null) {
			jObjt.put("facebookID", cliente.getFacebookID());
		}
		if (cliente.getFotoPerfil() != null) {
			jObjt.put("fotoPerfil", cliente.getFotoPerfil());
		}
		if (cliente.getCPF() != null) {
			jObjt.put("cpf", cliente.getCPF());
		}
		if (cliente.getEndereco() != null) {
			jObjt.put("endereco", enderecoUtil.toJSON(cliente.getEndereco()));
		}
		if (cliente.getListaTelefone() != null) {
			jObjt.put("listaTelefone", listaTelefoneJSON(cliente.getListaTelefone()));
		}
		if (cliente.getNascimento() != null) {
			jObjt.put("nascimento", format.format(cliente.getNascimento()));
		}
		if (cliente.getApelido() != null) {
			jObjt.put("apelido", cliente.getApelido());
		}
		if (cliente.getAgenda() != null) {
			jObjt.put("numServicosContratados", cliente.getQtdeServicosContratados());
			jObjt.put("agenda", agendaJSON(cliente.getAgenda()));
		}
		if (cliente.getStatus() != null) {
			jObjt.put("status", cliente.getStatus());
		}
		if (cliente.getListaAmigos() != null) {
			jObjt.put("listaAmigos", listaAmigosJSON(cliente.getListaAmigos()));
		}
		if (cliente.getPrivacidade() != null) {
			jObjt.put("privacidade", privacidadeUtil.toJSON(cliente.getPrivacidade()));
		}
		if (cliente.getTipoCadastro() != null) {
			jObjt.put("tipoCadastro", cliente.getTipoCadastro().getCodigo());
			jObjt.put("perfil", cliente.getTipoCadastro().getDescricao());
		}
		if (cliente.getListaPrestadoresRecomendados() != null) {
			jObjt.put("listaPrestadoresRecomendados",
					listaPrestadoresRecomendadosJSON(cliente.getListaPrestadoresRecomendados()));
		}
		if (cliente.getlistaMensagensConversa() != null) {
			jObjt.put("listaMensagensConversa", listaMensagensConversaJSON(cliente.getlistaMensagensConversa()));
		}
		return jObjt;
	}

	private JSONObject listaAmigosJSON(List<Amizade> listaAmigos) {
		JSONObject jObjt = new JSONObject();
		if (listaAmigos != null) {
			int i = 0;
			for (Amizade amizade : listaAmigos) {
				JSONObject jObjt1 = amizadeUtil.toJSON(amizade);
				jObjt.put("amizade" + i, jObjt1);
				i++;
			}
		}
		return jObjt;
	}

	private JSONObject listaTelefoneJSON(List<Telefone> listaTelefone) {
		JSONObject jObjt = new JSONObject();
		if (listaTelefone != null) {
			int i = 0;
			for (Telefone tel : listaTelefone) {
				JSONObject jObjt1 = new JSONObject();
				jObjt1.put("ddd", tel.getDdd());
				jObjt1.put("numero", tel.getNumero());
				jObjt1.put("categoria", tel.getCategoriaTelefone().getCodigo());
				jObjt1.put("categoriaDescricao", tel.getCategoriaTelefone().getDescricao());
				if (tel.getOperadora() != null) {
					jObjt1.put("operadora", tel.getOperadora().getCodigo());
					jObjt1.put("operadoraDescricao", tel.getOperadora().getDescricao());
				} else {
					jObjt1.put("operadora", OperadoraEnum.OUTROS.getCodigo());
					jObjt1.put("operadoraDescricao", OperadoraEnum.OUTROS.getDescricao());
				}
				jObjt.put("telefone" + i, jObjt1);
				i++;
			}
		}
		return jObjt;
	}

	private JSONObject listaPrestadoresRecomendadosJSON(List<Prestador> listaPrestadoresRecomendados) {
		JSONObject jObjt = new JSONObject();
		if (listaPrestadoresRecomendados != null) {
			int i = 0;
			for (Prestador prestador : listaPrestadoresRecomendados) {
				JSONObject jObjt1 = prestadorUtil.toJSON(prestador);
				jObjt.put("prestador" + i, jObjt1);
				i++;
			}
		}
		return jObjt;
	}

	private JSONObject listaMensagensConversaJSON(List<Conversa> listaMensagensConversa) {
		JSONObject jObjt = new JSONObject();
		if (listaMensagensConversa != null) {
			int i = 0;
			for (Conversa conversa : listaMensagensConversa) {
				JSONObject jObjt1 = conversaUtil.toJSON(conversa);
				jObjt.put("conversa" + i, jObjt1);
				i++;
			}
		}
		return jObjt;
	}
	
	private JSONObject agendaJSON(List<Compromisso> agenda) {
		JSONObject jObjt = new JSONObject();
		if (agenda != null) {
			int i = 0;
			for (Compromisso compromisso : agenda) {
				JSONObject jObjt1 = compromissoUtil.toJSON(compromisso);
				jObjt.put("compromisso" + i, jObjt1);
				i++;
			}
		}
		return jObjt;
	}

}
