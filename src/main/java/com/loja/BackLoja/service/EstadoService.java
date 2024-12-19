package com.loja.BackLoja.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.loja.BackLoja.entity.Estado;
import com.loja.BackLoja.repository.EstadoRepository;

@Service
public class EstadoService {

	@Autowired
	private EstadoRepository estadoRepository;
	
	public List<Estado> buscarTodos()
	{
		return estadoRepository.findAll();
	}
	public Estado inserir(Estado estado)
	{
		estado.setDataCriacao(new Date());
		Estado estadonovo = estadoRepository.saveAndFlush(estado);
		return estadonovo;
	}
	
	public Estado alterar(Estado estado)
	{
		estado.setDataAtualizacao(new Date());
		return estadoRepository.saveAndFlush(estado);
	}
	
	public void excluir(Long id)
	{
		Estado estado = estadoRepository.findById(id).get();
		estadoRepository.delete(estado);
	}
}
