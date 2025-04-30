package com.loja.BackLoja.service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.loja.BackLoja.entity.Pessoa;
import com.loja.BackLoja.repository.PessoaRepository;

@Service
public class PessoaGerenciamentoService {

	@Autowired
	private PessoaRepository pessoaRepository;
	
	@Autowired
	private EmailService emailService;
	
	public String solicitarCodigo(String email)
	{
		Pessoa pessoa = pessoaRepository.findByEmail(email);
		pessoa.setCodigoSenha(getCodigoRecuperacaoSenha(pessoa.getId()));
		pessoa.setDataValidadeCodigo(new Date());
		pessoaRepository.saveAndFlush(pessoa);
		emailService.enviarEmailText(pessoa.getEmail(), "Código de recuperação se senha", "Olá, seu código de recuperação de senha é: " + pessoa.getCodigoSenha());
		return "Código enviado";
	}
	
	public String alterarSenha(Pessoa pessoa)
	{
		Pessoa pessoaBanco = pessoaRepository.findByEmailAndCodigoSenha(pessoa.getEmail(), pessoa.getCodigoSenha());
		Date diferenca = new Date(new Date().getTime() - pessoaBanco.getDataValidadeCodigo().getTime()); 
		
		if(diferenca.getTime()/1000 < 900)
		{
			//adicionar spring security
			pessoaBanco.setSenha(pessoa.getSenha());
			pessoaBanco.setCodigoSenha(null);
			pessoaRepository.saveAndFlush(pessoaBanco);
			return "Senha alterada com sucesso";
		}
		else
		{
			return "Tempo expirado, solicite um novo código";
		}
	}
	private String getCodigoRecuperacaoSenha(Long id)
	{
		DateFormat format = new SimpleDateFormat("ddMMyyyyHHmmssmm");
		return format.format(new Date()) + id;
	}
}
