package com.tcc.flyk.entity;

import com.tcc.flyk.entity.enumerator.TipoCadastroEnum;

public class Usuario {
	
	private String id;

	private String nome;

	private String usuario;

	private String email;

	private TipoCadastroEnum tipoCadastro;
	
	private String senha;

	private boolean ativo;
	
	private String fotoPerfil;

	public String getNome() {
		return nome;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getId() {
		return id;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public TipoCadastroEnum getTipoCadastro() {
		return tipoCadastro;
	}

	public void setTipoCadastro(TipoCadastroEnum tipoCadastro) {
		this.tipoCadastro = tipoCadastro;
	}

	public boolean isAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getFotoPerfil() {
		return fotoPerfil;
	}

	public void setFotoPerfil(String fotoPerfil) {
		this.fotoPerfil = fotoPerfil;
	}
	
	public void setTipoCadastro(int codigo) {
		switch (codigo) {
		case 1:
			this.tipoCadastro = TipoCadastroEnum.CLIENTE;
			break;
		case 2:
			this.tipoCadastro = TipoCadastroEnum.PRESTADOR;
			break;
		case 3:
			this.tipoCadastro = TipoCadastroEnum.PREMIUM;
			break;
		case 4:
			this.tipoCadastro = TipoCadastroEnum.ADMINISTRADOR;
			break;
		default:
			this.tipoCadastro = TipoCadastroEnum.CLIENTE;
		}
	}

	
}
