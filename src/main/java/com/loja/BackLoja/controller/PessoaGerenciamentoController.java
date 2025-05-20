package com.loja.BackLoja.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.loja.BackLoja.entity.Pessoa;
import com.loja.BackLoja.security.JwtUtil;
import com.loja.BackLoja.service.PessoaGerenciamentoService;

@RestController
@RequestMapping("/api/pessoa-gerenciamento")
public class PessoaGerenciamentoController {

	@Autowired
	private PessoaGerenciamentoService pessoaGerenciamentoService;
	
	@Autowired
	private JwtUtil jwtUtil;
	
	@Autowired
	AuthenticationManager auth;
	
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
	
	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody Pessoa pessoa)
	{
		Authentication auth2 = auth.authenticate(new UsernamePasswordAuthenticationToken(pessoa.getEmail(), pessoa.getSenha()));
		SecurityContextHolder.getContext().setAuthentication(auth2);
		Pessoa logado = (Pessoa)auth2.getPrincipal();
		String token = jwtUtil.gerarTokenUserName(logado);
		return ResponseEntity.ok(token);
		
	}
	
	@GetMapping("/teste-senha")
	public ResponseEntity<String> testarSenha() {
	    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
	    boolean corresponde = encoder.matches("12345", "$2a$10$yYI25MDVL3bx1jcN8DcH6.PNdz14wXNcx86TGrWFRDCC2YAR.3xNC");

	    return ResponseEntity.ok(corresponde ? "Senha correta!" : "Senha errada.");
	}
}
