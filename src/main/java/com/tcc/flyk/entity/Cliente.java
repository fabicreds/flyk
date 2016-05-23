package com.tcc.flyk.entity;

import java.util.Date;
import java.util.List;

import com.tcc.flyk.entity.enumerator.TipoCadastroEnum;

public class Cliente {

	//identificador gerado no banco
	private String id;
	
	private String nome;
	
	//nome que irá aparecer no perfil do usuario
	private String alias;
	
	private String email;
	
	private String senha;
	
	//identificador de autenticação com o facebook
	private String facebookID;
	
	private String fotoPerfil;
	
	private String CPF;
	
	private Endereco endereco;
	
	private List<Telefone> listaTelefone;

	private Date nascimento;
	
	private String apelido;	
	
	private List<Compromisso> agenda;
	
	private String status;
	
	private List<Amizade> listaAmigos;
	
	private Privacidade privacidade;
	
	private TipoCadastroEnum tipoCadastro;
	
	private Prestador prestador;
	
	private List<Cliente> listaPrestadoresRecomendados;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getFacebookID() {
		return facebookID;
	}

	public void setFacebookID(String facebookID) {
		this.facebookID = facebookID;
	}

	public String getFotoPerfil() {
		return fotoPerfil;
	}

	public void setFotoPerfil(String fotoPerfil) {
		this.fotoPerfil = fotoPerfil;
	}

	public String getCPF() {
		return CPF;
	}

	public void setCPF(String cPF) {
		CPF = cPF;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public List<Telefone> getListaTelefone() {
		return listaTelefone;
	}

	public void setListaTelefone(List<Telefone> listaTelefone) {
		this.listaTelefone = listaTelefone;
	}

	public Date getNascimento() {
		return nascimento;
	}

	public void setNascimento(Date nascimento) {
		this.nascimento = nascimento;
	}

	public String getApelido() {
		return apelido;
	}

	public void setApelido(String apelido) {
		this.apelido = apelido;
	}

	public List<Compromisso> getAgenda() {
		return agenda;
	}

	public void setAgenda(List<Compromisso> agenda) {
		this.agenda = agenda;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<Amizade> getListaAmigos() {
		return listaAmigos;
	}

	public void setListaAmigos(List<Amizade> listaAmigos) {
		this.listaAmigos = listaAmigos;
	}

	public Privacidade getPrivacidade() {
		return privacidade;
	}

	public void setPrivacidade(Privacidade privacidade) {
		this.privacidade = privacidade;
	}

	public TipoCadastroEnum getTipoCadastro() {
		return tipoCadastro;
	}

	public void setTipoCadastro(TipoCadastroEnum tipoCadastro) {
		this.tipoCadastro = tipoCadastro;
	}

	public Prestador getPrestador() {
		return prestador;
	}

	public void setPrestador(Prestador prestador) {
		this.prestador = prestador;
	}

	public List<Cliente> getListaPrestadoresRecomendados() {
		return listaPrestadoresRecomendados;
	}

	public void setListaPrestadoresRecomendados(List<Cliente> listaPrestadoresRecomendados) {
		this.listaPrestadoresRecomendados = listaPrestadoresRecomendados;
	}
	
}
