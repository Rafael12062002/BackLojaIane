package com.loja.BackLoja.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.loja.BackLoja.entity.Cidade;
import com.loja.BackLoja.repository.CidadeRepository;

@Service
public class CidadeService {

	@Autowired
	private CidadeRepository cidadeRepository;
	
	public List<Cidade> buscarTodas()
	{
		return cidadeRepository.findAll();
	}
	
	public Cidade inserir(Cidade cidade)
	{
		cidade.setDataCriacao(new Date());
		Cidade cidadeNovo = cidadeRepository.saveAndFlush(cidade);
		return cidadeNovo;
	}
	
	public Cidade alterar(Cidade cidade)
	{
		cidade.setDataAtualizacao(new Date());
		return cidadeRepository.saveAndFlush(cidade);
	}
	
	public void excluir(Long id)
	{
		Cidade cidade = cidadeRepository.findById(id).get();
		cidadeRepository.delete(cidade);
	}
}
