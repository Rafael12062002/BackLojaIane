package com.loja.BackLoja.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.loja.BackLoja.entity.Pessoa;
import com.loja.BackLoja.service.PessoaGerenciamentoService;

@RestController
@RequestMapping("/api/pessoa-gerenciamento")
public class PessoaGerenciamentoController {

	@Autowired
	private PessoaGerenciamentoService pessoaGerenciamentoService;
	
	@PostMapping("/senha-codigo")
	public String recuperarCodigo(@RequestBody Pessoa pessoa)
	{
		return pessoaGerenciamentoService.solicitarCodigo(pessoa.getEmail());
	}
	@PutMapping("/alterar")
	public String alterarCodigo(@RequestBody Pessoa pessoa)
	{
		return pessoaGerenciamentoService.alterarSenha(pessoa);
	}
}
