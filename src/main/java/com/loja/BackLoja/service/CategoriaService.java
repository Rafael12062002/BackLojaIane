package com.loja.BackLoja.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.loja.BackLoja.entity.Categoria;
import com.loja.BackLoja.repository.CategoriaRepository;

@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository categoriaRepository;
	
	public List<Categoria> buscarTodos()
	{
		return categoriaRepository.findAll();
	}
	
	public Categoria inserir(Categoria categoria)
	{
		categoria.setDataCriacao(new Date());
		Categoria novaCategoria = categoriaRepository.saveAndFlush(categoria);
		return novaCategoria;
	}
	
	public Categoria alterar(Categoria categoria)
	{
		categoria.setDataAtualizacao(new Date());
		return categoriaRepository.saveAndFlush(categoria);
	}
	
	public void excluir (Long id)
	{
		Categoria categoria = categoriaRepository.findById(id).get();
		categoriaRepository.delete(categoria);
	}
}
