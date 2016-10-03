package com.tcc.flyk.persistence.impl;

import java.util.ArrayList;
import java.util.List;

import org.bson.types.ObjectId;

import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.tcc.flyk.entity.Amizade;
import com.tcc.flyk.entity.Categoria;
import com.tcc.flyk.entity.Cliente;
import com.tcc.flyk.entity.Compromisso;
import com.tcc.flyk.entity.Conversa;
import com.tcc.flyk.entity.Endereco;
import com.tcc.flyk.entity.Prestador;
import com.tcc.flyk.entity.Privacidade;
import com.tcc.flyk.entity.Telefone;
import com.tcc.flyk.entity.Usuario;
import com.tcc.flyk.entity.enumerator.PrivacidadeEnum;
import com.tcc.flyk.entity.enumerator.StatusAmizadeEnum;
import com.tcc.flyk.entity.enumerator.TipoCadastroEnum;
import com.tcc.flyk.persistence.MongoDB;
import com.tcc.flyk.persistence.PrestadorDAO;
import com.tcc.flyk.util.DataBaseUtil;

public class PrestadorDAOImpl extends MongoDB implements PrestadorDAO {

	private DataBaseUtil dbUtil = new DataBaseUtil();

	@Override
	public Usuario consultaLogin(String email) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Prestador consultaPrestadorPorId(String idPrestador) {
		consultaTudo();
		System.out.println("CONSULTA CLIENTE - INï¿½CIO");

		Prestador pessoa = new Prestador(); // Cliente que serï¿½ retornado

		DBCollection collection = db.getCollection("FLYK");
		// BasicDBObject filtro = new BasicDBObject(new Document("_id",new
		// ObjectId(idCliente)));
		// Metodo acima alterado para
		BasicDBObject filtro = new BasicDBObject("_id", new ObjectId(idPrestador));

		DBCursor cursor = collection.find(filtro);
		DBObject resultado;

		// Busca campos de resultado
		if (cursor.hasNext()) {
			resultado = cursor.next();
			System.out.println(resultado);
			System.out.println("************************");

			// ID
			pessoa.setId(idPrestador);
			dbUtil.montarDadosBasicosCliente(pessoa, resultado);

			// ENDEREï¿½O
			Endereco enderecoPessoa = dbUtil.montarDadosEndereco(resultado);
			pessoa.setEndereco(enderecoPessoa);

			// PRIVACIDADE
			Privacidade privacidade = dbUtil.montarDadosPrivacidade(resultado);
			pessoa.setPrivacidade(privacidade);

			// ********************* LISTA DE TELEFONES *********************
			// Busca a lista de telefones e coloca na telefonesBD
			BasicDBList telefonesDB = (BasicDBList) resultado.get("telefones");
			if (telefonesDB != null) {
				// Varre a lista de telefones, preenchendo o array telefones
				List<Telefone> telefones = dbUtil.montaDadosTelefones(telefonesDB);
				// Adiciona o array telefones na pessoa
				pessoa.setListaTelefone(telefones);
			}
			System.out.println(" telefones fim");

			// ********************* LISTA DE AMIGOS *********************
			// Busca a lista de telefones e coloca na telefonesBD
			BasicDBList amigosDB = (BasicDBList) resultado.get("amigos");

			if (amigosDB != null) {
				// Varre a lista de telefones, preenchendo o array telefones
				List<Amizade> amigos = dbUtil.montaDadosAmigos(amigosDB);
				// Adiciona o array telefones na pessoa
				pessoa.setListaAmigos(amigos);
			}

			// ********************* LISTA DE SERVICOS CONTRATADOS
			// *********************
			// Busca a lista de compromissos e coloca na servicosContratadosDB
			BasicDBList servicosContratadosDB = (BasicDBList) resultado.get("servicos_contratados");

			if (servicosContratadosDB != null) {
				//List<Compromisso> agenda = dbUtil.montarDadosServicosContratados(idPrestador, servicosContratadosDB);
				List<Compromisso> agenda = dbUtil.montarDadosServicosContratados(servicosContratadosDB);

				// Adiciona o array compromissos na pessoa
				pessoa.setListaContratosServicosPrestados(agenda);
			}

			// ********************* LISTA DE RECOMENDAï¿½ï¿½ES DADAS
			// *********************
			// Busca a lista de prestadores recomendados e coloca na telefonesBD
			BasicDBList recomendacoesDadasBD = (BasicDBList) resultado.get("recomendacoes_dadas");

			if (recomendacoesDadasBD != null) {
				// Varre a lista de prestadores recomendados, preenchendo o
				// array recomendacoesDadas
				List<Prestador> recomendacoesDadas = dbUtil.montarDadosRecomendacoesDadas(recomendacoesDadasBD);
				// Adiciona o array prestadores recomendados na pessoa
				pessoa.setListaPrestadoresRecomendados(recomendacoesDadas);
			}

			// ********************* LISTA DE CONVERSAS *********************
			// Busca a lista de conversa e coloca na conversaDB
			BasicDBList conversaDB = (BasicDBList) resultado.get("mensagens_de_conversa");

			if (conversaDB != null) {
				// Varre a lista de conversa, preenchendo o array mensagens
				List<Conversa> mensagens = dbUtil.montarDadosConversas(conversaDB);
				// Adiciona o array mensagens na pessoa
				pessoa.setlistaMensagensConversa(mensagens);
			}
			
			// ********************* LISTA DE SERVIÇOS *********************
						// Busca a lista de servicos prestador e coloca na servicosDB
						BasicDBList servicosDB = (BasicDBList) resultado.get("categorias_de_servicos_prestados");

						if (servicosDB != null) {
							// Varre a lista de conversa, preenchendo o array mensagens
							List<Categoria> listaServicosPrestados = montarDadosListaServicos(servicosDB);
							// Adiciona o array mensagens na pessoa
							pessoa.setListaCategoriaServicosPrestados(listaServicosPrestados);
						}

		} else {
			System.out.println("Consulta de clientes pelo id " + idPrestador + " nÃ£o encontrou valores.");
			return null;
		}

		// Retorna a pessoa para o chamador
		return pessoa;

	}

	@Override
	public boolean inserirNovoPrestador(Prestador prestador) {

		System.out.println("inicio addPrestador");

		// Nome completo
		BasicDBObject doc = new BasicDBObject();
		doc.put("nome_completo", prestador.getNome());

		// Usuario
		if (prestador.getUsuario() != null) {
			doc.put("usuario", prestador.getUsuario());
		}

		// Email
		if (prestador.getEmail() != null) {
			doc.put("email", prestador.getEmail());
		}

		// Data de Nascimento
		if (prestador.getNascimento() != null) {
			doc.put("data_nascimento", prestador.getNascimento());
		}

		// Senha
		if (prestador.getSenha() != null) {
			doc.put("senha", prestador.getSenha());
		}

		System.out.println("1");
		// facebookID
		if (prestador.getFacebookID() != null) {
			doc.put("facebookID", prestador.getFacebookID());
		}

		System.out.println("15");
		// CPF
		if (prestador.getCPF() != null) {
			doc.put("CPF", prestador.getCPF());
		}

		System.out.println("16");
		// Foto do perfil
		if (prestador.getFotoPerfil() != null) {
			System.out.println("17");
			doc.put("foto", prestador.getFotoPerfil());
		}
		System.out.println("2");

		// Tipo de perfil
		if (prestador.getTipoCadastro() != null) {
			doc.put("tipo_perfil", prestador.getTipoCadastro().getCodigo());
		}
		// Status
		if (prestador.getStatus() != null) {
			doc.put("status_pessoa", prestador.getStatus());
		}

		if (prestador.getEndereco() != null) {
			// Logradouro
			if (prestador.getEndereco().getLogradouro() != null) {
				doc.put("logradouro", prestador.getEndereco().getLogradouro());
			}

			System.out.println("3");
			// Numero
			if (prestador.getEndereco().getNumero() == 0) {
				doc.put("numero", prestador.getEndereco().getNumero());
			}

			System.out.println("31");
			// Complemento
			if (prestador.getEndereco().getComplemento() != null) {
				doc.put("complemento", prestador.getEndereco().getComplemento());
			}

			System.out.println("32");
			// Bairro
			if (prestador.getEndereco().getBairro() != null) {
				doc.put("bairro", prestador.getEndereco().getBairro());
			}

			System.out.println("33");
			// Cidade
			if (prestador.getEndereco().getCidade() != null) {
				doc.put("cidade", prestador.getEndereco().getCidade());
			}

			System.out.println("34");
			// Estado
			if (prestador.getEndereco().getEstado() != null) {
				doc.put("estado", prestador.getEndereco().getEstado());
			}

			System.out.println("4");
			// CEP
			if (prestador.getEndereco().getCep() != null) {
				doc.put("CEP", prestador.getEndereco().getCep());
			}
		}
		/*
		 * System.out.println("5"); //TIPO DE CADASTRO
		 * if(prestador.getTipoCadastro()==TipoCadastroEnum.PRESTADOR){ //CNPJ
		 * if(prestador.getPrestador().getCnpj()!=null)){ doc.put("CNPJ",
		 * prestador.getPrestador().getCnpj()); }
		 * 
		 * if(prestador.getTipoCadastro()==TipoCadastroEnum.PREMIUM){ //Valor
		 * Premium doc.put("valor_premium",
		 * prestador.getPrestador().getValorPremium()); } }
		 * 
		 * 
		 * 
		 * System.out.println("5"); //TIPO DE CADASTRO
		 * if(prestador.getTipoCadastro()==TipoCadastroEnum.PRESTADOR){ //CNPJ
		 * if(prestador.getPrestador().getCnpj()!=null)){ doc.put("CNPJ",
		 * prestador.getPrestador().getCnpj()); }
		 * 
		 * if(prestador.getTipoCadastro()==TipoCadastroEnum.PREMIUM){ //Valor
		 * Premium doc.put("valor_premium",
		 * prestador.getPrestador().getValorPremium()); } }
		 */
		// PRIVACIDADE
		if (prestador.getPrivacidade() != null) {

			// agenda
			if (prestador.getPrivacidade().getExibeAgenda() != null) {
				if (prestador.getTipoCadastro() == TipoCadastroEnum.CLIENTE) {
					doc.put("privacidade_bloco_servicos_contratados",
							prestador.getPrivacidade().getExibeAgenda().getCodigo());
				} else {
					doc.put("privacidade_bloco_servicos_prestados",
							prestador.getPrivacidade().getExibeAgenda().getCodigo());
				}
			}

			// CPF
			if (prestador.getPrivacidade().getExibeCPF() != null) {
				doc.put("privacidade_bloco_cpf_cnpj", prestador.getPrivacidade().getExibeCPF().getCodigo());
			}

			// Endereï¿½o
			if (prestador.getPrivacidade().getExibeEndereco() != null) {
				doc.put("privacidade_bloco_endereco", prestador.getPrivacidade().getExibeEndereco().getCodigo());
			}

			// Telefone
			if (prestador.getPrivacidade().getExibeTelefone() != null) {
				doc.put("privacidade_bloco_telefone", prestador.getPrivacidade().getExibeTelefone().getCodigo());
			}
		}

		// ******************************Telefones******************************//
		if (prestador.getListaTelefone() != null) {

			int count = prestador.getListaTelefone().size();
			System.out.println("qtd telefones: " + String.valueOf(count));

			// Varre a lista de telefones, inserindo um por um
			BasicDBList telefones = new BasicDBList();
			for (Telefone tel : prestador.getListaTelefone()) {

				BasicDBObject telefone = new BasicDBObject();

				// Numero
				telefone.put("numero_telefone", String.valueOf(tel.getNumero()));

				// Categoria
				if (tel.getCategoriaTelefone() != null) {
					telefone.put("categoria_telefone", String.valueOf(tel.getCategoriaTelefone().getCodigo()));
				}

				// DDD
				if (tel.getDdd() != 0) {
					telefone.put("ddd_telefone", String.valueOf(tel.getDdd()));
				}

				// Operadora
				if (tel.getOperadora() != null) {
					telefone.put("operadora_telefone", String.valueOf(tel.getOperadora().getCodigo()));
				}
				System.out.println(String.valueOf(tel.getNumero()));

				telefones.add(telefone);
				System.out.println("telefone adicionado");
			}
			doc.put("telefones", telefones);
		} else {
			System.out.println("sem telefones");
		}

		// ******************************Amigos******************************//
		if (prestador.getListaAmigos() != null) {

			int count = prestador.getListaAmigos().size();
			System.out.println("qtd amigos: " + String.valueOf(count));

			BasicDBList amigos = new BasicDBList();

			// Varre a lista de amigos, inserindo um por um
			for (int i = 0; i < count; i++) {
				System.out.println(
						"ID do amigo: " + String.valueOf(prestador.getListaAmigos().get(i).getAmigo().getId()));

				BasicDBObject amigo = new BasicDBObject();

				// DAQUI PRA BAIXO ï¿½ Sï¿½ PREENCHER OS DADOS DA LISTA DE
				// AMIGOS
				// Inicia o documento e grava o nï¿½mero
				amigo.put("id_amigo", String.valueOf(prestador.getListaAmigos().get(i).getAmigo().getId()));

				// Data da amizade
				if (prestador.getListaAmigos().get(i).getDataInicioAmizade() != null) {
					amigo.put("data_amizade", prestador.getListaAmigos().get(i).getDataInicioAmizade());
				}

				// Status da amizade
				if (prestador.getListaAmigos().get(i).getStatusEnum() != null) {
					if (prestador.getListaAmigos().get(i).getStatusEnum() == StatusAmizadeEnum.ATIVA) {
						amigo.put("status_amizade", "1");
					} else {
						amigo.put("status_amizade", "2");
					}
				}

				amigos.add(amigo);
			}
			doc.put("amigos", amigos);
		} else {
			System.out.println("sem amigos :(");
		}

		// ******************************Recomendaï¿½ï¿½es dadas a outro
		// usuï¿½rio******************************//
		if (prestador.getListaPrestadoresRecomendados() != null) {

			int count = prestador.getListaPrestadoresRecomendados().size();
			System.out.println("qtd prestadores recomendados: " + String.valueOf(count));

			BasicDBList prestadoresRecomendados = new BasicDBList();
			// Varre a lista de prestadores recomendados, inserindo um por um
			for (int i = 0; i < count; i++) {
				System.out.println("ID do prestador: "
						+ String.valueOf(prestador.getListaPrestadoresRecomendados().get(i).getId()));

				BasicDBObject prestadorRecomendado = new BasicDBObject();

				// Grava o nï¿½mero
				prestadorRecomendado.put("id_usuario_recomendado",
						String.valueOf(prestador.getListaPrestadoresRecomendados().get(i).getId()));

				prestadoresRecomendados.add(prestadorRecomendado);
			}

			doc.put("recomendacoes_dadas", prestadoresRecomendados);
		} else {
			System.out.println("sem recomendaï¿½ï¿½es dadas");
		}

		// ******************************Mensagens de
		// conversa******************************//
		if (prestador.getlistaMensagensConversa() != null) {

			int count = prestador.getlistaMensagensConversa().size();
			System.out.println("qtd msgs: " + String.valueOf(count));

			BasicDBList mensagens = new BasicDBList();
			// Varre a lista de mensagens, inserindo uma por uma
			for (int i = 0; i < count; i++) {
				BasicDBObject mensagem = new BasicDBObject();

				// Inicia o documento e grava o id da prestador com a qual o
				// cliente estï¿½ conversando
				mensagem.put("id_usuario_conversa", prestador.getlistaMensagensConversa().get(i).getIdUsuario());

				// flagEnviadoOuRecebido
				mensagem.put("flagEnviadoOuRecebido",
						prestador.getlistaMensagensConversa().get(i).getflagEnviadoRecebido());

				// data_hora_mensagem
				if (prestador.getlistaMensagensConversa().get(i).getData() != null) {
					mensagem.put("data_hora_mensagem", prestador.getlistaMensagensConversa().get(i).getData());
				}

				// mensagem
				if (prestador.getlistaMensagensConversa().get(i).getMsg() != null) {
					mensagem.put("mensagem", prestador.getlistaMensagensConversa().get(i).getMsg());
				}

				mensagens.add(mensagem);
			}

			doc.put("mensagens_de_conversa", mensagens);
		} else {
			System.out.println("sem conversas");
		}

		// ******************************Servicos
		// contratados******************************//
		if (prestador.getListaServicosContratados() != null) {

			int count = prestador.getListaServicosContratados().size();
			System.out.println("qtd de servicos contratados: " + String.valueOf(count));

			BasicDBList servicosContratados = new BasicDBList();

			// Varre a lista de compromissos, inserindo uma por uma
			for (Compromisso compromisso : prestador.getListaServicosContratados()) {

				BasicDBObject servicoContratado = new BasicDBObject();

				System.out.println("0");
				// Inicia o documento e grava o id do prestador
				if (compromisso.getContrato() != null && compromisso.getContrato().getPrestador() != null) {
					servicoContratado.put("id_prestador", compromisso.getContrato().getPrestador().getId());
				}
				System.out.println("1");
				// data_servico_contratado
				if (compromisso.getDataInicio() != null) {
					servicoContratado.put("data_servico_contratado", compromisso.getDataInicio());
				}
				System.out.println("2");

				// custo_servico_contratado
				if (compromisso.getContrato() != null) {
					servicoContratado.put("custo_servico_contratado",
							String.valueOf(compromisso.getContrato().getCusto()));
				}

				// categoria do servico contratado
				if (compromisso.getContrato() != null && compromisso.getContrato().getServico() != null) {
					servicoContratado.put("servico_contratado", compromisso.getContrato().getServico().getId());
				}

				System.out.println("3");
				// status_servico_contratado
				if (compromisso.getStatus() != null) {
					servicoContratado.put("status_servico_contratado", compromisso.getStatus().getCodigo());
				}

				System.out.println("4");
				// data_avaliacao_servico_contratado
				if (compromisso.getContrato() != null) {
					servicoContratado.put("data_avaliacao_servico_contratado",
							compromisso.getContrato().getDataAvaliacaoServico());
				}

				System.out.println("5");
				// nota_preco
				if (compromisso.getContrato() != null && compromisso.getContrato().getAvaliacaoPrestador() != null) {
					servicoContratado.put("nota_preco",
							String.valueOf(compromisso.getContrato().getAvaliacaoPrestador().getAvaliacaoPreco()));
				}
				System.out.println("6");

				// nota_pontualidade
				if (compromisso.getContrato() != null && compromisso.getContrato().getAvaliacaoPrestador() != null) {
					servicoContratado.put("nota_pontualidade", String
							.valueOf(compromisso.getContrato().getAvaliacaoPrestador().getAvaliacaoPontualidade()));
				}
				System.out.println("7");

				// nota_qualidade
				if (compromisso.getContrato() != null && compromisso.getContrato().getAvaliacaoPrestador() != null) {
					servicoContratado.put("nota_qualidade",
							String.valueOf(compromisso.getContrato().getAvaliacaoPrestador().getAvaliacaoQualidade()));
				}
				System.out.println("8");

				// nota_profissionalismo
				if (compromisso.getContrato() != null && compromisso.getContrato().getAvaliacaoPrestador() != null) {
					servicoContratado.put("nota_profissionalismo", String
							.valueOf(compromisso.getContrato().getAvaliacaoPrestador().getAvaliacaoProfissionalismo()));
				}

				System.out.println("9");
				servicosContratados.add(servicoContratado);
				System.out.println("10");
			}
			doc.put("servicos_contratados", servicosContratados);
		} else {
			System.out.println("sem servicos contratados");
		}

		System.out.println("11");
		System.out.println(doc);

		// ************************************
		// ***********************************
		// ***********************************
		// *********************************** DAQUI PARA BAIXO ESTï¿½O SENDO
		// TRAZIDAS AS INFORMAï¿½ï¿½ES DE PRESTADOR,
		// *********************************** ATï¿½ ESTE PONTO O Mï¿½TODO ï¿½
		// UMA Cï¿½PIA DO Mï¿½TODO DE INSERIR CLIENTE.
		// ************************************
		// ***********************************
		// ***********************************
		// CNPJ
		if (prestador.getCnpj() != null) {
			doc.put("alias", prestador.getCnpj());
		}

		// valor_premium
		if (prestador.getValorPremium() != null) {
			doc.put("valor_premium", prestador.getValorPremium());
		}

		// ******************************Recomendaï¿½ï¿½es recebidas de
		// outro******************************
		if (prestador.getListaRecomendacoesRecebidas() != null) {

			int count = prestador.getListaRecomendacoesRecebidas().size();
			System.out.println("qtd recomencaï¿½ï¿½es recebidas: " + String.valueOf(count));

			BasicDBList recomendacoesRecebidas = new BasicDBList();
			// Varre a lista de recomandacoes recebidas, inserindo uma por uma
			for (int i = 0; i < count; i++) {
				System.out.println(
						"ID do cliente: " + String.valueOf(prestador.getListaRecomendacoesRecebidas().get(i).getId()));

				BasicDBObject prestadorRecomendado = new BasicDBObject();

				// Grava o nï¿½mero
				prestadorRecomendado.put("id_usuario_recomendador",
						String.valueOf(prestador.getListaRecomendacoesRecebidas().get(i).getId()));

				recomendacoesRecebidas.add(prestadorRecomendado);
			}

			doc.put("recomendacoes_recebidas", recomendacoesRecebidas);
		} else {
			System.out.println("sem recomendaï¿½ï¿½es recebidas");
		}

		// ******************************categorias_de_servicos_prestados******************************
		if (prestador.getListaCategoriaServicosPrestados() != null) {

			int count = prestador.getListaCategoriaServicosPrestados().size();
			System.out.println("qtd categorias que o prestador oferece: " + String.valueOf(count));

			BasicDBList categoriasPrestadas = new BasicDBList();
			// Varre a lista de categorias, inserindo uma por uma
			for (int i = 0; i < count; i++) {
				System.out.println("Categoria: " + String.valueOf(prestador.getListaCategoriaServicosPrestados().get(i).getId()));

				BasicDBObject categoriaPrestada = new BasicDBObject();

				// Grava o nï¿½mero
				categoriaPrestada.put("id_categoria_servico_prestado", prestador.getListaCategoriaServicosPrestados().get(i).getId());

				categoriasPrestadas.add(categoriaPrestada);
			}

			doc.put("categorias_de_servicos_prestados", categoriasPrestadas);
		} else {
			System.out.println("prestador sem categoria de serviï¿½o.");
		}

		// ******************************serviï¿½os_prestados******************************//
		if (prestador.getListaContratosServicosPrestados() != null) {

			int count = prestador.getListaContratosServicosPrestados().size();
			System.out.println("qtd de servicos prestados: " + String.valueOf(count));

			BasicDBList servicosPrestados = new BasicDBList();

			// Varre a lista de compromissos, inserindo uma por uma
			for (int i = 0; i < count; i++) {

				BasicDBObject servicoPrestado = new BasicDBObject();

				System.out.println("0");
				// Inicia o documento e grava o id do prestador
				servicoPrestado.put("id_cliente_contratante",
						prestador.getListaContratosServicosPrestados().get(i).getContrato().getCliente().getId());

				System.out.println("1");
				// data_inicio_servico_prestado
				if (prestador.getListaContratosServicosPrestados().get(i).getDataInicio() != null) {
					servicoPrestado.put("data_inicio_servico_prestado",
							prestador.getListaContratosServicosPrestados().get(i).getDataInicio());
				}

				// data_fim_servico_prestado
				if (prestador.getListaContratosServicosPrestados().get(i).getDataFim() != null) {
					servicoPrestado.put("data_fim_servico_prestado",
							prestador.getListaContratosServicosPrestados().get(i).getDataFim());
				}
				System.out.println("2");

				// custo_servico_prestado
				if (prestador.getListaContratosServicosPrestados().get(i).getContrato().getCusto() != 0) {
					servicoPrestado.put("custo_servico_prestado",
							String.valueOf(prestador.getListaContratosServicosPrestados().get(i).getContrato().getCusto()));
				}

				System.out.println("3");
				// status_servico_prestado
				if (prestador.getListaContratosServicosPrestados().get(i).getStatus() != null) {
					servicoPrestado.put("status_servico_prestado",
							prestador.getListaContratosServicosPrestados().get(i).getStatus().getCodigo());
				}

				System.out.println("4");
				// data_avaliacao_servico_contratado
				if (prestador.getListaContratosServicosPrestados().get(i).getContrato().getDataAvaliacaoServico() != null) {
					servicoPrestado.put("data_avaliacao_servico_contratado",
							prestador.getListaContratosServicosPrestados().get(i).getContrato().getDataAvaliacaoServico());
				}

				System.out.println("5");
				// nota_preco
				if (prestador.getListaContratosServicosPrestados().get(i).getContrato().getAvaliacaoPrestador()
						.getAvaliacaoPreco() != 0) {
					servicoPrestado.put("nota_preco", String.valueOf(prestador.getListaContratosServicosPrestados().get(i)
							.getContrato().getAvaliacaoPrestador().getAvaliacaoPreco()));
				}
				System.out.println("6");

				// nota_pontualidade
				if (prestador.getListaContratosServicosPrestados().get(i).getContrato().getAvaliacaoPrestador()
						.getAvaliacaoPontualidade() != 0) {
					servicoPrestado.put("nota_pontualidade", String.valueOf(prestador.getListaContratosServicosPrestados().get(i)
							.getContrato().getAvaliacaoPrestador().getAvaliacaoPontualidade()));
				}
				System.out.println("7");

				// nota_qualidade
				if (prestador.getListaContratosServicosPrestados().get(i).getContrato().getAvaliacaoPrestador()
						.getAvaliacaoQualidade() != 0) {
					servicoPrestado.put("nota_qualidade", String.valueOf(prestador.getListaContratosServicosPrestados().get(i)
							.getContrato().getAvaliacaoPrestador().getAvaliacaoQualidade()));
				}
				System.out.println("8");

				// nota_profissionalismo
				if (prestador.getListaContratosServicosPrestados().get(i).getContrato().getAvaliacaoPrestador()
						.getAvaliacaoProfissionalismo() != 0) {
					servicoPrestado.put("nota_profissionalismo", String.valueOf(prestador.getListaContratosServicosPrestados()
							.get(i).getContrato().getAvaliacaoPrestador().getAvaliacaoProfissionalismo()));
				}

				// nota_cliente
				if (prestador.getListaContratosServicosPrestados().get(i).getContrato().getAvaliacaoPrestador()
						.getAvaliacaoProfissionalismo() != 0) {
					servicoPrestado.put("nota_cliente", String.valueOf(
							prestador.getListaContratosServicosPrestados().get(i).getContrato().getAvaliacaoContratante()));
				}

				System.out.println("9");
				servicosPrestados.add(servicoPrestado);
				System.out.println("10");
			}
			doc.put("servicos_prestados", servicosPrestados);
		} else {
			System.out.println("sem servicos prestados");
		}

		// Insere o prestador
		try {
			super.db.getCollection("FLYK").insert(doc);
			System.out.println("Usuï¿½rio cadastrado com sucesso");
		} catch (Exception e) {
			System.out.println("ERRO:" + e.getStackTrace());
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		consultaTudo();

		return true;
	}

	// Retorna o valor numï¿½rico da privacidde
	public int quebraPrivacidade(PrivacidadeEnum privacidade) {
		if (privacidade == PrivacidadeEnum.PUBLICO) {
			return 1;
		}
		if (privacidade == PrivacidadeEnum.APENAS_AMIGOS) {
			return 2;
		}
		if (privacidade == PrivacidadeEnum.PRIVADO) {
			return 3;
		}
		return 1;
	}

	@Override
	public void consultaTudo() {
		// TODO Auto-generated method stub

	}

	private List<Categoria> montarDadosListaServicos(BasicDBList servicosDB) {
		List<Categoria> listaServicos = new ArrayList<Categoria>();
		BasicDBObject[] lightArr = servicosDB.toArray(new BasicDBObject[0]);
		for (BasicDBObject servicoDB : lightArr) {
			Categoria categoriaServico = new Categoria();
			if (servicoDB.get("id_categoria_servico_prestado") != null) {
				categoriaServico.setId(servicoDB.getString("id_categoria_servico_prestado"));
			}
			listaServicos.add(categoriaServico);
		}
		return listaServicos;
	}
	
	@Override
	public Prestador atualizaPrestador(String idPrestador, Prestador prestador)
{
		try {
			Prestador  pdor  = new Prestador();
			BasicDBObject updateQuery = new BasicDBObject();
			pdor = this.consultaPrestadorPorId(String.valueOf(idPrestador));		
			
            System.out.println("pdor eh " + pdor.getEmail());
			
			if (!(prestador.getListaTelefone() == null)) {

				int count = prestador.getListaTelefone().size();
				System.out.println("qtd telefones: " + String.valueOf(count));

				// Varre a lista de telefones, inserindo um por um
				BasicDBList telefones = new BasicDBList();
				for (int i = 0; i < count; i++) {

					BasicDBObject telefone = new BasicDBObject();

					// Numero
					telefone.put("numero_telefone", String.valueOf(prestador.getListaTelefone().get(i).getNumero()));

					// Categoria
					if (!(prestador.getListaTelefone().get(i).getCategoriaTelefone() == null)) {
						telefone.put("categoria_telefone",
								String.valueOf(prestador.getListaTelefone().get(i).getCategoriaTelefone().getCodigo()));
					}

					// DDD
					if (!(prestador.getListaTelefone().get(i).getDdd() == 0)) {
						telefone.put("ddd_telefone", String.valueOf(prestador.getListaTelefone().get(i).getDdd()));
					}

					// Operadora
					if (!(prestador.getListaTelefone().get(i).getOperadora() == null)) {
						telefone.put("operadora_telefone",
								String.valueOf(prestador.getListaTelefone().get(i).getOperadora().getCodigo()));
					}
					System.out.println(String.valueOf(prestador.getListaTelefone().get(i).getNumero()));

					telefones.add(telefone);
					System.out.println("telefone adicionado uhul");
				}
				
				
				BasicDBList categoriasPrestadas = new BasicDBList();

				// ******************************categorias_de_servicos_prestados******************************
				if (prestador.getListaCategoriaServicosPrestados() != null) {

					 count = prestador.getListaCategoriaServicosPrestados().size();
					System.out.println("qtd categorias que o prestador oferece: " + String.valueOf(count));

					// Varre a lista de categorias, inserindo uma por uma
					for (int i = 0; i < count; i++) {
						System.out.println("Categoria: " + String.valueOf(prestador.getListaCategoriaServicosPrestados().get(i).getId()));

						BasicDBObject categoriaPrestada = new BasicDBObject();

						// Grava o nï¿½mero
						categoriaPrestada.put("id_categoria_servico_prestado", prestador.getListaCategoriaServicosPrestados().get(i).getId());

						categoriasPrestadas.add(categoriaPrestada);
					}

					
				} else {
					System.out.println("prestador sem categoria de serviï¿½o.");
				}
                
				

				updateQuery.append("$set", new BasicDBObject().
						append("CPF", prestador.getCPF())
						.append("email", prestador.getEmail())
						.append("telefones", telefones)
						.append("logradouro", prestador.getEndereco().getLogradouro())
						.append("bairro", prestador.getEndereco().getBairro()).append("cep", prestador.getEndereco().getCep())
						.append("cidade", prestador.getEndereco().getCidade()).append("estado", prestador.getEndereco().getEstado())
						.append("complemento", prestador.getEndereco().getComplemento())
						.append("numero", prestador.getEndereco().getNumero())
						.append("categorias_de_servicos_prestados", categoriasPrestadas)
						.append("tipo_perfil", 2) 

						//.append("privacidade_bloco_cpf_cnpj",pdor.getPrivacidade().getExibeCPF().getCodigo())
						//.append("privacidade_bloco_telefone",pdor.getPrivacidade().getExibeTelefone().getCodigo())
						//.append("privacidade_bloco_endereco",pdor.getPrivacidade().getExibeEndereco().getCodigo())						
						//.append("foto", pdor.getFotoPerfil())


					
								
						

				);

			}
			
	
			BasicDBObject searchQuery = new BasicDBObject();
			searchQuery.append("_id", new ObjectId(idPrestador));

			DBCollection collection = db.getCollection("FLYK");

			collection.update(searchQuery, updateQuery);
			
			pdor = this.consultaPrestadorPorId(String.valueOf(idPrestador));

			return pdor;
		} catch (Exception e) {
			System.out.println(e.getLocalizedMessage());
			return null;
		}

	}

}
