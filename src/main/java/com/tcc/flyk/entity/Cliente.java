package com.tcc.flyk.entity;

import java.util.Date;
import java.util.List;

public class Cliente {

	private int codigo;
	
	private String nome;
	
	private String email;
	
	private String senha;
	
	private int facebookIb;
	
	private String CPF;
	
	private Endereco endereco;
	
	private List<Telefone> listaTelefone;

	private Date nascimento;
	
	private String apelido;	
	
	private List<Compromisso> agenda;
	
	private String status;
	
	private List<Indicacao> listaIndicacoes;
	
	private List<Amizade> listaAmigos;
	
	private Privacidade privacidade;
	
	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
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

	public int getFacebookIb() {
		return facebookIb;
	}

	public void setFacebookIb(int facebookIb) {
		this.facebookIb = facebookIb;
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

	public List<Indicacao> getListaIndicacoes() {
		return listaIndicacoes;
	}

	public void setListaIndicacoes(List<Indicacao> listaIndicacoes) {
		this.listaIndicacoes = listaIndicacoes;
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

	
	
}
