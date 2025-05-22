package com.loja.BackLoja.service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;
import org.springframework.web.bind.annotation.RequestBody;

import com.loja.BackLoja.dto.LoginRequestDto;
import com.loja.BackLoja.dto.PessoaClienteRequestDto;
import com.loja.BackLoja.entity.Pessoa;
import com.loja.BackLoja.repository.PessoaClienteRepository;
import com.loja.BackLoja.security.JwtUtil;

@Service
public class PessoaClienteService {

	@Autowired
	private PessoaClienteRepository pessoaRepository;
	
	@Autowired
	private PermissaoPessoaService permissaoPessoaService;
	
	@Autowired
	private EmailService emailService;
	
	@Autowired
	private AuthenticationManager manager;
	
	@Autowired
	private JwtUtil util;
	
	public Pessoa registrar(PessoaClienteRequestDto pessoaClienteRequestDto)
	{
		System.out.println("Iniciando registro");
		Pessoa pessoa = new PessoaClienteRequestDto().converter(pessoaClienteRequestDto);
		pessoa.setDataCriacao(new Date());
		Pessoa novaPessoa = pessoaRepository.save(pessoa);
		System.out.println("Pessoa salva" + novaPessoa.getNome());
		permissaoPessoaService.vincularPessoaPermissaoCliente(novaPessoa, "CLIENTE");
		if (novaPessoa.getEmail() == null || novaPessoa.getEmail().isEmpty()) {
		    throw new IllegalArgumentException("O campo email é obrigatório.");
		}
		Map<String, Object> proprie = new HashMap<>();
		proprie.put("nome", novaPessoa.getNome());
		proprie.put("mensagem", "Registro realizado com sucesso, em breve chegará o código para alteração de senha");
		try
		{
			System.out.println("Tentando enviar email para: " + novaPessoa.getEmail());
		emailService.enviar(novaPessoa.getEmail(),
				"Cadastro realizado com sucesso!",
				proprie);
		System.out.println("Email enviado com sucesso");
		
		}
		catch(Exception e)
		{
			System.err.println("Erro ao enviar" + e.getMessage());
			e.printStackTrace();
		}
		return novaPessoa;
	}
	
	@TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
	public void enviarEmailAposCommit(Pessoa pessoa)
	{
		System.out.println("Transação concluida, enviando email");
		emailService.enviarEmailText(pessoa.getEmail(), "Passou no segundo método", "Funcionou após commit");
	}
	
	public ResponseEntity<?> login(@RequestBody LoginRequestDto loginRequestDto)
	{
		Authentication login = manager.authenticate(new UsernamePasswordAuthenticationToken(loginRequestDto.getEmail(), loginRequestDto.getSenha()));
		SecurityContextHolder.getContext().setAuthentication(login);
		Pessoa logado = pessoaRepository.findByEmail(loginRequestDto.getEmail());
		String token = util.gerarTokenUserName(logado);
		return ResponseEntity.ok(token);
		
		//Authentication auth2 = auth.authenticate(new UsernamePasswordAuthenticationToken(pessoa.getEmail(), pessoa.getSenha()));
		//SecurityContextHolder.getContext().setAuthentication(auth2);
		//Pessoa logado = (Pessoa)auth2.getPrincipal();
		//String token = jwtUtil.gerarTokenUserName(logado);
		//return ResponseEntity.ok(token);
	}
}
