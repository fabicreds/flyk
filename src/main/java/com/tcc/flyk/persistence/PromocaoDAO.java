package com.tcc.flyk.persistence;

import java.util.List;

import com.tcc.flyk.entity.Promocao;

public interface PromocaoDAO {

	public boolean inserirNovaPromocao(Promocao prom);

	public List<Promocao> consultaPromocao();
}
