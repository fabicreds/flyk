package com.tcc.flyk.entity;

import javax.annotation.Resource;

import com.tcc.flyk.entity.enumerator.TipoCadastroEnum;
import com.tcc.flyk.util.TipoCadastroEnumUtil;

public class Usuario {

	private String nome;

	private String usuario;

	private String email;

	private TipoCadastroEnum tipoCadastro;

	private boolean ativo;

	@Resource
	private TipoCadastroEnumUtil util;

	public String getNome() {
		return nome;
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

	public void setTipoCadastro(String tipoCadastro) {
		this.tipoCadastro = util.definirTipoCadastro(tipoCadastro);
	}

	public boolean isAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}

	

}
