package com.loja.BackLoja.dto;

import java.util.List;

public class CarrinhoRequestDTO {

	private List<Long> idsprodutos;
	private List<ItemCarrinhoDTO> itens;
	
	public List<Long> getIdsprodutos() {
		return idsprodutos;
	}

	public void setIdsprodutos(List<Long> idsprodutos) {
		this.idsprodutos = idsprodutos;
	}

	public List<ItemCarrinhoDTO> getItens() {
		return itens;
	}

	public void setItens(List<ItemCarrinhoDTO> itens) {
		this.itens = itens;
	}
}
