package com.tcc.flyk.controller;

import java.util.Date;

import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tcc.flyk.entity.Categoria;
import com.tcc.flyk.entity.Cliente;
import com.tcc.flyk.service.CategoriaService;
import com.tcc.flyk.service.ClienteService;

@Controller
@RequestMapping(value = "/cadastroCliente")
public class CadastroClienteController {

	@RequestMapping(method  = RequestMethod.POST )
	public @ResponseBody String CadastrarNovoCliente(@RequestBody String JSONN){
		JSONObject objeto = new JSONObject(JSONN);
		
		//Instancia um novo cliente
		Cliente novoCliente = new Cliente();

		//Instancia o retorno
		JSONObject ret = new JSONObject();
		
		//Instancia o servico
		ClienteService servico = new ClienteService();

		//Preenche o cliente com as informações da tela
		System.out.println("CONTROLLER CLIENTE OLÊLÊ OLÁLÁ");
		//nome*
		try{
			String nome = String.valueOf(objeto.get("nome"));
			novoCliente.setNome(nome);
			System.out.println(nome);
		}catch (Exception e){
			System.out.println("ERRO NO nome");
			ret.put("retorno", "erro");
			ret.put("mensagem", "O Nome é obrigatório");
			//return ret.toString();
		}
		
		//email
		try{
			String email = String.valueOf(objeto.get("email"));
			novoCliente.setEmail(email);
			System.out.println(email);
		}catch (Exception e){
			System.out.println("ERRO NO email");
			ret.put("retorno", "erro");
			ret.put("mensagem", "O Email é obrigatório");
			//return ret.toString();
		}
		
		//apelido
		try{
			String apelido = String.valueOf(objeto.get("apelido"));
			novoCliente.setApelido(apelido);
			System.out.println(apelido);
		}catch (Exception e){
			System.out.println("ERRO NO apelido");
			ret.put("retorno", "erro");
			ret.put("mensagem", "O Apelido é obrigatório");
			//return ret.toString();
		}
		
		//senha
		try{
			String senha = String.valueOf(objeto.get("senha"));
			novoCliente.setSenha(senha);
			System.out.println(senha);
		}catch (Exception e){
			System.out.println("ERRO NO senha");
			ret.put("retorno", "erro");
			ret.put("mensagem", "A Senha é obrigatória");
			//return ret.toString();
		}
		
		//cpf
		try{
			String cpf = String.valueOf(objeto.get("cpf"));
			novoCliente.setCPF(cpf);
			System.out.println(cpf);
		}catch (Exception e){
			System.out.println("ERRO NO cpf");
			ret.put("retorno", "erro");
			ret.put("mensagem", "O CPF é obrigatório");
			//return ret.toString();
		}
		
		//datanascimento
		try{
			//exemplo 01/01/2011
			
			//Quebra em substrings
			System.out.println("----71-----");
			Date dataNascimento = new Date();

			String dataNasc = objeto.getString("datanascimento");
			System.out.println(dataNasc);
			int dia = Integer.valueOf(dataNasc.substring(0, 2));
			System.out.println("dia:" + String.valueOf(dia));
			System.out.println(dataNasc.substring(3, 5));
			int mes = Integer.valueOf(dataNasc.substring(3, 5));
			System.out.println("mes:" + String.valueOf(mes));
			System.out.println(dataNasc.substring(6, 10));
			int ano = Integer.valueOf(dataNasc.substring(6, 10));
			System.out.println("ano:" + String.valueOf(ano));
			dataNascimento.setDate(dia);
			dataNascimento.setMonth(mes);
			dataNascimento.setYear(ano);
			System.out.println("data:" + String.valueOf(dataNascimento));
			novoCliente.setNascimento(dataNascimento);
			System.out.println("----5-----");
		}catch (Exception e){
			System.out.println("ERRO NO datanascimento");
		}

		System.out.println("----1-----");
		//telefone1
		try{
			JSONObject telefone = new JSONObject(objeto.get("telefone1").toString());
			//form.setEmail(jObjt.getString("email"));
			
			System.out.println(telefone.getString("number"));//{"number":"(12)1212-1212","operadora":{"id":6,"label":"OUTROS"}}
			System.out.println("----2-----i");
			System.out.println(telefone.getString("operadora"));
			System.out.println("----3-----");
			System.out.println(telefone.getString("label"));
			System.out.println("----4-----");
			
		}catch (Exception e){
			System.out.println("ERRO NO telefone1");
			ret.put("retorno", "erro");
			ret.put("mensagem", "O Telefone 1 é obrigatório");
			//return ret.toString();
		}
		
		//telefone2
		try{
			System.out.println(String.valueOf(objeto.get("telefone2")));
		}catch (Exception e){
			System.out.println("ERRO NO telefone2");
		}
		
		//prestador
		try{
			System.out.println(String.valueOf(objeto.get("prestador")));
		}catch (Exception e){
			System.out.println("ERRO NO prestador");
		}
		
		//cep
		try{
			System.out.println(String.valueOf(objeto.get("cep")));
		}catch (Exception e){
			System.out.println("ERRO NO cep");
		}
		
		//logradouro
		try{
			System.out.println(String.valueOf(objeto.get("logradouro")));
		}catch (Exception e){
			System.out.println("ERRO NO logradouro");
		}

		//numero
		try{
			System.out.println(String.valueOf(objeto.get("numero")));
		}catch (Exception e){
			System.out.println("ERRO NO numero");
		}
		
		//comp
		try{
			System.out.println(String.valueOf(objeto.get("comp")));
		}catch (Exception e){
			System.out.println("ERRO NO comp");
		}
		
		//bairro
		try{
			System.out.println(String.valueOf(objeto.get("bairro")));
		}catch (Exception e){
			System.out.println("ERRO NO bairro");
		}
		
		//cidade
		try{
			System.out.println(String.valueOf(objeto.get("cidade")));
		}catch (Exception e){
			System.out.println("ERRO NO cidade");
		}
		
		//estado
		try{
			System.out.println(String.valueOf(objeto.get("estado")));
		}catch (Exception e){
			System.out.println("ERRO NO estado");
		}
		
		//pais
		try{
			System.out.println(String.valueOf(objeto.get("pais")));
		}catch (Exception e){
			System.out.println("ERRO NO pais");
		}
		
		//imagem
		try{
			System.out.println(String.valueOf(objeto.get("imagem")));
		}catch (Exception e){
			System.out.println("ERRO NO imagem");
		}
		
			
		System.out.println("DONE DONE DONE DONE DONE DONE DONE ");
		
		if(1==1){
		//if(servico.CadastrarNovaCategoria(novaCategoria)){
			ret.put("retorno", "sucesso");
			ret.put("mensagem", "Cliente cadastrado com sucesso!");
		}else{
			ret.put("retorno", "erro");
			ret.put("mensagem", "Cliente não cadastrado.");
		}
		
		//Retorna para o .js
		return ret.toString();
	}
}
