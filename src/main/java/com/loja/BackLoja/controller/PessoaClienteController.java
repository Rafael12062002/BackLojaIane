package com.loja.BackLoja.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
	
	@Autowired
	private AuthenticationManager manager;
	
	@Autowired
	private JwtUtil util;
	
	@PostMapping("/")
	public Pessoa inserir(@RequestBody PessoaClienteRequestDto pessoaClienteRequestDto)
	{
		return pessoaService.registrar(pessoaClienteRequestDto);
	}
	
	@PostMapping("/logar")
	public ResponseEntity<?> login(@RequestBody LoginRequestDto loginRequestDto)
	{
		Authentication login = manager.authenticate(new UsernamePasswordAuthenticationToken(loginRequestDto.getEmail(), loginRequestDto.getSenha()));
		SecurityContextHolder.getContext().setAuthentication(login);
		Pessoa logado = (Pessoa)login.getPrincipal();
		String token = util.gerarTokenUserName(logado);
		return ResponseEntity.ok(token);
		
		//Authentication auth2 = auth.authenticate(new UsernamePasswordAuthenticationToken(pessoa.getEmail(), pessoa.getSenha()));
		//SecurityContextHolder.getContext().setAuthentication(auth2);
		//Pessoa logado = (Pessoa)auth2.getPrincipal();
		//String token = jwtUtil.gerarTokenUserName(logado);
		//return ResponseEntity.ok(token);
	}
}
