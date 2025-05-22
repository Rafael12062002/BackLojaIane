package com.loja.BackLoja.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import com.loja.BackLoja.entity.Produto;
import com.loja.BackLoja.repository.ProdutoRepository;

@Service
public class ProdutoService {

	@Autowired
	private ProdutoRepository produtoRepository;
	
	public List<Produto> buscarTodos()
	{
		return produtoRepository.findAll();
	}
	
	public Produto inserir(Produto produto)
	{
		produto.setDataCriacao(new Date());
		Produto novoProduto = produtoRepository.saveAndFlush(produto);
		return novoProduto;
	}
	
	public Produto alterar(Produto produto)
	{
		produto.setDataAtualizacao(new Date());
		return produtoRepository.saveAndFlush(produto);
	}
	
	public void excluir(Long id)
	{
		Produto produto = produtoRepository.findById(id).get();
		produtoRepository.delete(produto);
	}
}
