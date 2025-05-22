package com.loja.BackLoja.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.loja.BackLoja.dto.LoginRequestDto;
import com.loja.BackLoja.dto.PessoaClienteRequestDto;
import com.loja.BackLoja.entity.Pessoa;
import com.loja.BackLoja.security.JwtUtil;
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
	
	@PostMapping("/logar")
	public ResponseEntity<?> login(@RequestBody LoginRequestDto loginRequestDto)
	{
		return pessoaService.login(loginRequestDto);
	}
}
