package com.loja.BackLoja.dto;

import org.springframework.beans.BeanUtils;

import com.loja.BackLoja.entity.Cidade;
import com.loja.BackLoja.entity.Pessoa;

public class PessoaClienteRequestDto {

	private String nome;
	private String cpf;
	private String email;
	private String endereco;
	private String cep;
	private Cidade cidade;
	
	
	public PessoaClienteRequestDto() {
		super();
	}


	public PessoaClienteRequestDto(String nome, String cpf, String email, String endereco, String cep, Cidade cidade) {
		super();
		this.nome = nome;
		this.cpf = cpf;
		this.email = email;
		this.endereco = endereco;
		this.cep = cep;
		this.cidade = cidade;
	}

	

	public String getNome() {
		return nome;
	}


	public void setNome(String nome) {
		this.nome = nome;
	}


	public String getCpf() {
		return cpf;
	}


	public void setCpf(String cpf) {
		this.cpf = cpf;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getEndereco() {
		return endereco;
	}


	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}


	public String getCep() {
		return cep;
	}


	public void setCep(String cep) {
		this.cep = cep;
	}


	public Cidade getCidade() {
		return cidade;
	}


	public void setCidade(Cidade cidade) {
		this.cidade = cidade;
	}


	public Pessoa converter(PessoaClienteRequestDto pessoaClienteRequestDto)
	{
		Pessoa pessoa = new Pessoa();
		BeanUtils.copyProperties(pessoaClienteRequestDto, pessoa);
		return pessoa;
	}
}
