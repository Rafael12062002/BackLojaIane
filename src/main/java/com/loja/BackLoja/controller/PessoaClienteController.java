package com.loja.BackLoja.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.loja.BackLoja.dto.PessoaClienteRequestDto;
import com.loja.BackLoja.entity.Pessoa;
import com.loja.BackLoja.service.PessoaClienteService;

@RestController
@RequestMapping("/api/cliente")
public class PessoaClienteController {

	@Autowired
	private PessoaClienteService pessoaService;
	
	@PostMapping("/")
	public Pessoa inserir(@RequestBody PessoaClienteRequestDto pessoaClienteRequestDto)
	{
		return pessoaService.registrar(pessoaClienteRequestDto);
	}
}
