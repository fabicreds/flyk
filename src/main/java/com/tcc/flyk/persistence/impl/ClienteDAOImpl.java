package com.tcc.flyk.persistence.impl;

import java.text.ParseException;
import java.util.Date;

import org.bson.Document;

import com.mongodb.BasicDBObject;
import com.mongodb.Block;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.client.FindIterable;
import com.tcc.flyk.entity.Cliente;
import com.tcc.flyk.entity.Usuario;
import com.tcc.flyk.entity.enumerator.TipoCadastroEnum;
import com.tcc.flyk.persistence.ClienteDAO;
import com.tcc.flyk.persistence.MongoDB;

public class ClienteDAOImpl extends MongoDB implements ClienteDAO {

	public ClienteDAOImpl() {
		super();
	}

	@Override
	public void consulta() {
		DBCursor cursor = db.getCollection("cliente").find();

		while (cursor.hasNext()) {
			System.out.println(cursor.next());
		}
	}

	@Override
	public boolean inserirNovoCliente(Cliente pessoa) {
		System.out.println("inicio addCliente");
		//Nome completo
		Document doc = new Document("nome_completo", pessoa.getNome());
		
		//Alias
		if(!(pessoa.getAlias()==null)){
			doc.append("alias", pessoa.getAlias());
		}
		
		//Email
		if(!(pessoa.getEmail()==null)){
			doc.append("email", pessoa.getEmail());
		}
		
		//Senha
		if(!(pessoa.getSenha()==null)){
			doc.append("senha", pessoa.getSenha());
		}

		System.out.println("1");
		//facebookID
		if(!(pessoa.getFacebookID()==null)){
			doc.append("facebookID", pessoa.getFacebookID());
		}

		System.out.println("15");
		//CPF
		if(!(pessoa.getCPF()==null)){
			doc.append("CPF", pessoa.getCPF());
		}

		System.out.println("16");
		//Foto do perfil
		if(!(pessoa.getFotoPerfil()==null)){
			System.out.println("17");
			doc.append("foto", pessoa.getFotoPerfil());
		}
		System.out.println("2");
		
		//Tipo de perfil
		doc.append("tipo_perfil", pessoa.getTipoCadastro().getCodigo());
		
		//Status
		if(!(pessoa.getStatus()==null)){
			doc.append("status_pessoa", pessoa.getStatus());
		}
		
		//Logradouro
		if(!(pessoa.getEndereco().getLogradouro()==null)){
			doc.append("logradouro", pessoa.getEndereco().getLogradouro());
		}

		System.out.println("3");
		//Numero
		if(!(pessoa.getEndereco().getNumero()==0)){
			doc.append("numero", pessoa.getEndereco().getNumero());
		}

		System.out.println("31");
		//Complemento
		if(!(pessoa.getEndereco().getComplemento()==null)){
			doc.append("complemento", pessoa.getEndereco().getComplemento());
		}

		System.out.println("32");
		//Bairro
		if(!(pessoa.getEndereco().getBairro()==null)){
			doc.append("bairro", pessoa.getEndereco().getBairro());
		}

		System.out.println("33");
		//Cidade
		if(!(pessoa.getEndereco().getCidade()==null)){
			doc.append("cidade", pessoa.getEndereco().getCidade());
		}

		System.out.println("34");
		//Estado
		if(!(pessoa.getEndereco().getEstado()==null)){
			doc.append("estado", pessoa.getEndereco().getEstado());
		}

		System.out.println("4");
		//CEP
		if(!(pessoa.getEndereco().getCep()==null)){
			doc.append("CEP", pessoa.getEndereco().getCep());
		}

		System.out.println("5");
		if(pessoa.getTipoCadastro()==TipoCadastroEnum.PRESTADOR){
			//CNPJ
			if(!(pessoa.getPrestador().getCnpj()==null)){
				doc.append("CNPJ", pessoa.getPrestador().getCnpj());
			}
			
			if(pessoa.getTipoCadastro()==TipoCadastroEnum.PREMIUM){
				//Valor Premium
				doc.append("valor_premium", pessoa.getPrestador().getValorPremium());
			}			
		}
		

		System.out.println("meio addCliente: " + doc.values());
		
		//******************************Telefones******************************//
		String lista = "";		
		if(!(pessoa.getListaTelefone()==null)){
			lista = "[";
			
			int count = pessoa.getListaTelefone().size();
			System.out.println("qtd telefones: " + String.valueOf(count));
			
			//Varre a lista de telefones, inserindo um por um
			for(int i=0;i<count;i++){
				System.out.println(String.valueOf(pessoa.getListaTelefone().get(i).getNumero()));

				if(i>0) lista = lista + ","; //Adiciona v�rgula entre os documentos
				
				//Inicia o documento e grava o n�mero
				lista = lista + "Document{{";
				lista = lista + "numero=";
				lista = lista + String.valueOf(pessoa.getListaTelefone().get(i).getNumero());
				
				//Categoria
				if(!(pessoa.getListaTelefone().get(i).getCategoriaTelefone()==null)){
					lista = lista + ",";
					lista = lista + "categoria_telefone=";
					lista = lista + String.valueOf(pessoa.getListaTelefone().get(i).getCategoriaTelefone().getCodigo());
				}
				
				//DDD
				if(!(pessoa.getListaTelefone().get(i).getDdd()==0)){
					lista = lista + ",";
					lista = lista + "ddd=";
					lista = lista + String.valueOf(pessoa.getListaTelefone().get(i).getDdd());
				}
				
				//Operadora
				if(!(pessoa.getListaTelefone().get(i).getOperadora()==null)){
					lista = lista + ",";
					lista = lista + "operadora=";
					lista = lista + String.valueOf(pessoa.getListaTelefone().get(i).getOperadora().getCodigo());
				}

				lista = lista + "}}";
			}
			lista = lista + "]";
			System.out.println(lista);
			doc.append("telefones", lista);
		}else{
			System.out.println("sem telefones");
		}
		
		
		//******************************Amigos******************************//
		lista = "";		
		if(!(pessoa.getListaAmigos()==null)){
			lista = "[";
			
			int count = pessoa.getListaAmigos().size();
			System.out.println("qtd amigos: " + String.valueOf(count));
			
			//Varre a lista de amigos, inserindo um por um
			for(int i=0;i<count;i++){
				System.out.println("ID do amigo: " + String.valueOf(pessoa.getListaAmigos().get(i).getAmigo().getId()));

				if(i>0) lista = lista + ","; //Adiciona v�rgula entre os documentos
				
				
				// DAQUI PRA BAIXO � S� PREENCHER OS DADOS DA LISTA DE AMIGOS
				//Inicia o documento e grava o n�mero
				lista = lista + "Document{{";
				lista = lista + "id_amigo=";
				lista = lista + String.valueOf(pessoa.getListaAmigos().get(i).getAmigo().getId());
				
				//Data da amizade
				if(!(pessoa.getListaAmigos().get(i).getDataInicioAmizade()==null)){
					lista = lista + ",";
					lista = lista + "data_amizade=";
					lista = lista + String.valueOf(pessoa.getListaAmigos().get(i).getDataInicioAmizade());
				}
				
				//Status da amizade
				if(!(pessoa.getListaAmigos().get(i).getStatusEnum()==null)){
					lista = lista + ",";
					lista = lista + "status_amizade=";
					lista = lista + String.valueOf(pessoa.getListaAmigos().get(i).getStatusEnum());
				}

				lista = lista + "}}";
			}
			lista = lista + "]";
			System.out.println(lista);
			doc.append("amigos", lista);
		}else{
			System.out.println("sem amigos :(");
		}

		
		//******************************Recomenda��es dadas a outro usu�rio******************************//
		lista = "";		
		if(!(pessoa.getListaPrestadoresRecomendados()==null)){
			lista = "[";
			
			int count = pessoa.getListaPrestadoresRecomendados().size();
			System.out.println("qtd prestadores recomendados: " + String.valueOf(count));
			
			//Varre a lista de prestadores recomendados, inserindo um por um
			for(int i=0;i<count;i++){
				System.out.println("ID do prestador: " + String.valueOf(pessoa.getListaPrestadoresRecomendados().get(i).getId()));

				if(i>0) lista = lista + ","; //Adiciona v�rgula entre os documentos
				
				
				// DAQUI PRA BAIXO � S� PREENCHER OS DADOS DA LISTA DE PRESTADORES RECOMENDADOS
				//Inicia o documento e grava o n�mero
				lista = lista + "Document{{";
				lista = lista + "id_usuario_recomendado=";
				lista = lista + String.valueOf(pessoa.getListaPrestadoresRecomendados().get(i).getId());
				
				lista = lista + "}}";
			}
			lista = lista + "]";
			System.out.println(lista);
			doc.append("recomenda��es_dadas", lista);
		}else{
			System.out.println("sem recomenda��es dadas");
		}

		
		//******************************Mensagens de conversa******************************//
		lista = "";		
		if(!(pessoa.getlistaMensagensConversa()==null)){
			lista = "[";
			
			int count = pessoa.getlistaMensagensConversa().size();
			System.out.println("qtd msgs: " + String.valueOf(count));
			
			//Varre a lista de mensagens, inserindo uma por uma
			for(int i=0;i<count;i++){

				String idAmigo, flagEnviadoOuRecebido;
				if(pessoa.getlistaMensagensConversa().get(i).getIdDestino()==pessoa.getId()){
					idAmigo = pessoa.getlistaMensagensConversa().get(i).getIdOrigem();
					System.out.println(String.valueOf(pessoa.getlistaMensagensConversa().get(i).getIdOrigem()));
					flagEnviadoOuRecebido = "R";
				}else{
					idAmigo = pessoa.getlistaMensagensConversa().get(i).getIdDestino();
					System.out.println(String.valueOf(pessoa.getlistaMensagensConversa().get(i).getIdDestino()));
					flagEnviadoOuRecebido = "E";
				}

				if(i>0) lista = lista + ","; //Adiciona v�rgula entre os documentos
				
				//Inicia o documento e grava o id da pessoa com a qual o cliente est� conversando
				lista = lista + "Document{{";
				lista = lista + "id_usuario_conversa=";
				lista = lista + idAmigo;
				
				//flagEnviadoOuRecebido
				lista = lista + ",";
				lista = lista + "flagEnviadoOuRecebido =";
				lista = lista + flagEnviadoOuRecebido;
				
				//data_hora_mensagem
				if(!(pessoa.getlistaMensagensConversa().get(i).getData()==null)){
					lista = lista + ",";
					lista = lista + "data_hora_mensagem=";
					lista = lista + String.valueOf(pessoa.getlistaMensagensConversa().get(i).getData());
				}
				
				//mensagem
				if(!(pessoa.getlistaMensagensConversa().get(i).getMsg()==null)){
					lista = lista + ",";
					lista = lista + "mensagem=";
					lista = lista + pessoa.getlistaMensagensConversa().get(i).getMsg();
				}

				lista = lista + "}}";
			}
			lista = lista + "]";
			System.out.println(lista);
			doc.append("mensagens_de_conversa", lista);
		}else{
			System.out.println("sem conversas");
		}

		
		//******************************Servicos contratados******************************//
		lista = "";		
		if(!(pessoa.getlistaMensagensConversa()==null)){
			lista = "[";
			
			int count = pessoa.getlistaMensagensConversa().size();
			System.out.println("qtd msgs: " + String.valueOf(count));
			
			//Varre a lista de mensagens, inserindo uma por uma
			for(int i=0;i<count;i++){

				String idAmigo, flagEnviadoOuRecebido;
				if(pessoa.getlistaMensagensConversa().get(i).getIdDestino()==pessoa.getId()){
					idAmigo = pessoa.getlistaMensagensConversa().get(i).getIdOrigem();
					System.out.println(String.valueOf(pessoa.getlistaMensagensConversa().get(i).getIdOrigem()));
					flagEnviadoOuRecebido = "R";
				}else{
					idAmigo = pessoa.getlistaMensagensConversa().get(i).getIdDestino();
					System.out.println(String.valueOf(pessoa.getlistaMensagensConversa().get(i).getIdDestino()));
					flagEnviadoOuRecebido = "E";
				}

				if(i>0) lista = lista + ","; //Adiciona v�rgula entre os documentos
				
				//Inicia o documento e grava o id da pessoa com a qual o cliente est� conversando
				lista = lista + "Document{{";
				lista = lista + "id_usuario_conversa=";
				lista = lista + idAmigo;
				
				//flagEnviadoOuRecebido
				lista = lista + ",";
				lista = lista + "flagEnviadoOuRecebido =";
				lista = lista + flagEnviadoOuRecebido;
				
				//data_hora_mensagem
				if(!(pessoa.getlistaMensagensConversa().get(i).getData()==null)){
					lista = lista + ",";
					lista = lista + "data_hora_mensagem=";
					lista = lista + String.valueOf(pessoa.getlistaMensagensConversa().get(i).getData());
				}
				
				//mensagem
				if(!(pessoa.getlistaMensagensConversa().get(i).getMsg()==null)){
					lista = lista + ",";
					lista = lista + "mensagem=";
					lista = lista + pessoa.getlistaMensagensConversa().get(i).getMsg();
				}

				lista = lista + "}}";
			}
			lista = lista + "]";
			System.out.println(lista);
			doc.append("mensagens_de_conversa", lista);
		}else{
			System.out.println("sem conversas");
		}
		
		/*                  TRECHO QUE C�DIGO QUE VAI NO PRESTADORDAOIMPL
		//******************************Recomenda��es recebidas de outro usu�rio******************************
		lista = "";		
		if(!(pessoa.getListaRecomendacoesRecebidas()==null)){
			lista = "[";
			
			int count = pessoa.getListaRecomendacoesRecebidas().size();
			System.out.println("qtd recomendacoes recebidas: " + String.valueOf(count));
			
			//Varre a lista de recomendacoes recebidas, inserindo um por um
			for(int i=0;i<count;i++){
				System.out.println("ID do cliente: " + String.valueOf(pessoa.getListaRecomendacoesRecebidas().get(i).getId()));

				if(i>0) lista = lista + ","; //Adiciona v�rgula entre os documentos
				
				
				// DAQUI PRA BAIXO � S� PREENCHER OS DADOS DA LISTA DE RECOMENDA��ES RECEBIDAS
				//Inicia o documento e grava o n�mero
				lista = lista + "Document{{";
				lista = lista + "id_usuario_recomendador=";
				lista = lista + String.valueOf(pessoa.getListaRecomendacoesRecebidas().get(i).getId());
				
				lista = lista + "}}";
			}
			lista = lista + "]";
			System.out.println(lista);
			doc.append("recomenda��es_recebidas", lista);
		}else{
			System.out.println("sem recomenda��es recebidas");
		}

		
		//******************************Categoria de servi�os prestados******************************
		lista = "";		
		if(!(pessoa.getListaRecomendacoesRecebidas()==null)){
			lista = "[";
			
			int count = pessoa.getListaRecomendacoesRecebidas().size();
			System.out.println("qtd recomendacoes recebidas: " + String.valueOf(count));
			
			//Varre a lista de recomendacoes recebidas, inserindo um por um
			for(int i=0;i<count;i++){
				System.out.println("ID do cliente: " + String.valueOf(pessoa.getListaRecomendacoesRecebidas().get(i).getId()));

				if(i>0) lista = lista + ","; //Adiciona v�rgula entre os documentos
				
				
				// DAQUI PRA BAIXO � S� PREENCHER OS DADOS DA LISTA DE RECOMENDA��ES RECEBIDAS
				//Inicia o documento e grava o n�mero
				lista = lista + "Document{{";
				lista = lista + "id_usuario_recomendador=";
				lista = lista + String.valueOf(pessoa.getListaRecomendacoesRecebidas().get(i).getId());
				
				lista = lista + "}}";
			}
			lista = lista + "]";
			System.out.println(lista);
			doc.append("recomenda��es_recebidas", lista);
		}else{
			System.out.println("sem recomenda��es recebidas");
		}
		
		*/
		
		
		//insere o cliente
		System.out.println(doc.toString());
		try {
			super.mongoDatabase.getCollection("FLYK").insertOne(doc);
			System.out.println("Usu�rio cadastrado com sucesso:" + doc.values());
		} catch (Exception e) {
			System.out.println("ERRO:" + e.getStackTrace());
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		consultaTudo();
		return true;
	}

	@Override
	public Usuario consultaUsuario(String busca) {
		try {
			Usuario usuario = new Usuario();
			BasicDBObject query = new BasicDBObject();
			query.put("usuario", busca);

			DBObject object = db.getCollection("cliente").findOne(query);

			if(object!=null){
				usuario.setNome(object.get("nome").toString());
				usuario.setUsuario(object.get("usuario").toString());
				usuario.setTipoCadastro(TipoCadastroEnum.ADMINISTRADOR);
				if(object.get("status_pessoa").toString().equals("A")){
					usuario.setAtivo(true);
				}else{
					usuario.setAtivo(false);
				}
				return usuario;
			}else{
				return null;
			}

			
		} catch (Exception e) {
			return null;
		}
	}
	
	@Override
	public boolean atualizarStatusCliente(Usuario usuario) {
		try {
			BasicDBObject updateQuery = new BasicDBObject();
			updateQuery.append("$set", new BasicDBObject().append("status_pessoa", "I"));

			BasicDBObject searchQuery = new BasicDBObject();
			searchQuery.append("usuario", usuario.getUsuario());

			db.getCollection("cliente").update(searchQuery, updateQuery);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	//****************************CONSULTA TODOS OS DOCUMENTOS DO BANCO****************************//
	@Override
	public void consultaTudo(){

		FindIterable<Document> iterable = super.mongoDatabase.getCollection("FLYK").find();
		
		iterable.forEach(new Block<Document>() {
		    @Override
		    public void apply(final Document document) {
		        System.out.println(document);
		    }
		});
	}
}
