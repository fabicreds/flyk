package com.tcc.flyk.util;

import org.springframework.stereotype.Component;

import com.tcc.flyk.entity.enumerator.TipoCadastroEnum;

@Component
public class TipoCadastroEnumUtil {
	
	public TipoCadastroEnum definirTipoCadastro(int codigo) {
		switch (codigo) {
		case 1:
			return TipoCadastroEnum.CLIENTE;
		case 2:
			return TipoCadastroEnum.PRESTADOR;
		case 3:
			return TipoCadastroEnum.PREMIUM;
		case 4:
			return TipoCadastroEnum.ADMINISTRADOR;
		default:
			return TipoCadastroEnum.CLIENTE;
		}
	}

	public TipoCadastroEnum definirTipoCadastro(String tipoCadastro) {
		tipoCadastro = tipoCadastro.toUpperCase();
		if(tipoCadastro.equals("CLIENTE")){
			return TipoCadastroEnum.CLIENTE;
		}
		if(tipoCadastro.equals("PRESTADOR")){
			return TipoCadastroEnum.PRESTADOR;
		}
		if(tipoCadastro.equals("PREMIUM")){
			return TipoCadastroEnum.PREMIUM;
		}
		if(tipoCadastro.equals("ADMINISTRADOR")){
			return TipoCadastroEnum.ADMINISTRADOR;
		}
		return TipoCadastroEnum.CLIENTE;
	}
}
