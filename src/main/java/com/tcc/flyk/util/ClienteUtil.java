package com.tcc.flyk.util;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.json.JSONObject;
import org.springframework.stereotype.Component;

import com.tcc.flyk.entity.Amizade;
import com.tcc.flyk.entity.Cliente;
import com.tcc.flyk.entity.Compromisso;
import com.tcc.flyk.entity.Conversa;
import com.tcc.flyk.entity.Endereco;
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
		if (cliente.getUsuario() != null) {
			jObjt.put("usuario", cliente.getUsuario());
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
			// jObjt.put("endereco",
			// enderecoUtil.toJSON(cliente.getEndereco()));
			jObjt.put("endereco", endToJSON(cliente.getEndereco()));
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
		    //jObjt.put("privacidade", privacidadeUtil.toJSON(cliente.getPrivacidade()));
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
				//jObjt1.put("categoria", tel.getCategoriaTelefone().getCodigo());
				//jObjt1.put("categoriaDescricao", tel.getCategoriaTelefone().getDescricao());
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

	public JSONObject endToJSON(Endereco end) {
		JSONObject jObjt = new JSONObject(end);
		if (end.getLogradouro() != null) {
			jObjt.put("logradouro", end.getLogradouro());
		}
		if (end.getBairro() != null) {
			jObjt.put("bairro", end.getBairro());
			
		}
		/*
		 * if (end.getNumero() != null) { { jObjt.put("numero",
		 * end.getNumero()); }
		 */
		if (end.getComplemento() != null) {
			jObjt.put("complemento", end.getComplemento());
		}
		if (end.getCep() != null) {
			jObjt.put("cep", end.getCep());
		}
		if (end.getCidade() != null) {
			jObjt.put("cidade", end.getCidade());
		}
		if (end.getEstado() != null) {
			jObjt.put("estado", end.getEstado());
		}
		return jObjt;
	}

	public Endereco JSONToEnd(JSONObject j) {
		Endereco e = new Endereco();
		if (j.getString("logradouro") != null) {
			e.setLogradouro(j.getString("logradouro"));
		}
		if (j.getString("bairro") != null) {
			e.setBairro(j.getString("bairro"));
		}
		// if (j.getInt("numero") != null) {
		// e.setNumero(j.getInt("numero"));
		// }
		System.out.println("METODO JSONTOEnd " + j.toString());

		if (j.getString("complemento") != null) {
			e.setComplemento(j.getString("complemento"));
		}
		if (j.getString("cep") != null) {
			e.setCep(j.getString("cep"));
		}
		if (j.getString("cidade") != null) {
			e.setCidade(j.getString("cidade"));
		}
		if (j.getString("estado") != null) {
			e.setEstado(j.getString("estado"));
		}

		return e;
	}

	public Cliente toCliente(JSONObject json) {
		Cliente cli = new Cliente();
		JSONObject jsonCliente = json.getJSONObject("cliente");
		

		if (jsonCliente.getString("cpf") != null) {

			cli.setCPF(jsonCliente.getString("cpf"));
		}
		if (jsonCliente.getString("email") != null) {
			cli.setEmail(jsonCliente.getString("email"));
		}

		if (jsonCliente.getString("id") != null) {
			cli.setId(jsonCliente.getString("id"));
		}

		
		JSONObject jEndereco = new JSONObject();
		
		if (jsonCliente.getJSONObject("endereco") != null) {
			jEndereco = jsonCliente.getJSONObject("endereco");
			cli.setEndereco(JSONToEnd(jEndereco));
		}
		
		List<Telefone> telefones = new ArrayList<Telefone>();

		telefones = JSONToListaTelefone(json.getJSONObject("listaTelefone"), telefones);
		
		cli.setListaTelefone(telefones);

		

		/*
		 * Comentado pois falta adicionar na pagina de atualizacao
		 * if (jsonCliente.getString("nome") != null) {
		 * cli.setNome(jsonCliente.getString("nome"));
		 * 
		 * } if (jsonCliente.getString("apelido") != null) {
		 * cli.setApelido(jsonCliente.getString("apelido")); } if
		 * (jsonCliente.getString("facebookID") != null) {
		 * cli.setFacebookID(jsonCliente.getString("facebookID")); } if
		 * (jsonCliente.getString("foto") != null) {
		 * cli.setFotoPerfil(jsonCliente.getString("foto")); } if
		 * (jsonCliente.getString("nascimento") != null) {
		 * cli.setNascimento((Date) jsonCliente.get("nascimento")); }
		 * 
		 * if (jsonCliente.getString("senha") != null) {
		 * cli.setSenha(jsonCliente.getString("senha")); } if
		 * (jsonCliente.getString("status") != null) {
		 * cli.setStatus(jsonCliente.getString("status")); }
		 */
		return cli;
	}

	private List<Telefone> JSONToListaTelefone(JSONObject lista, List<Telefone> telefones) {

	

		for (Object k : lista.keySet()) {
			String chave = (String) k;			
			JSONObject jsonTel = new JSONObject();
			jsonTel = lista.getJSONObject(chave);
			Telefone t = new Telefone();
			t.setDdd(jsonTel.getInt("ddd"));
			t.setNumero(jsonTel.getInt("numero"));
			telefones.add(t);

		}

		return telefones;
	}

}
