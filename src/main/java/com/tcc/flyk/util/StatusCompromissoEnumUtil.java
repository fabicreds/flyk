package com.tcc.flyk.util;

import org.springframework.stereotype.Component;

import com.tcc.flyk.entity.enumerator.StatusCompromissoEnum;

@Component
public class StatusCompromissoEnumUtil {

	public StatusCompromissoEnum defineStatusCompromisso(int codigo) {
		switch (codigo) {
		case 1:
			return StatusCompromissoEnum.SOLICITADO;
		case 2:
			return StatusCompromissoEnum.RECURUSADA;
		case 3:
			return StatusCompromissoEnum.ORCADO;
		case 4:
			return StatusCompromissoEnum.MARCADO;
		case 5:
			return StatusCompromissoEnum.CANCELADO;
		case 6:
			return StatusCompromissoEnum.REALIZADO;
		default:
			return StatusCompromissoEnum.SOLICITADO;
		}
	}

}
