package com.loja.BackLoja.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.loja.BackLoja.entity.Permissao;
import com.loja.BackLoja.entity.PermissaoPessoa;
import com.loja.BackLoja.entity.Pessoa;
import com.loja.BackLoja.enumerator.Role;
import com.loja.BackLoja.repository.PermissaoPessoaRepository;
import com.loja.BackLoja.repository.PermissaoRepository;

@Service
public class PermissaoPessoaService {

	@Autowired
	private PermissaoPessoaRepository permissaoPessoaRepository;
	
	@Autowired
	private PermissaoRepository permissaoRepository;
	
	public void vincularPessoaPermissaoCliente(Pessoa pessoa, String nomePermissao)
	{
		System.out.println("Dentro de vincularPessoaService");
		List<Permissao> listaPermissao = permissaoRepository.findByNome(nomePermissao);
		if(!listaPermissao.isEmpty())
		{
			PermissaoPessoa permissaoPessoa = new PermissaoPessoa();
			permissaoPessoa.setPessoa(pessoa);
			permissaoPessoa.setPermissao(listaPermissao.get(0));
			permissaoPessoa.setDataCriacao(new Date());
			
			permissaoPessoaRepository.saveAndFlush(permissaoPessoa);
		}
		else
		{
			System.out.println("Permissão ROLE_CLIENTE não encontrada no banco!");
		}
	}
}
