package com.loja.BackLoja.entity;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.loja.BackLoja.enumerator.Role;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

@Entity
@Table(name = "pessoa")
@Data
public class Pessoa implements UserDetails{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "idCidade")
	private Cidade cidade;
	
	private String nome;
	private String cpf;
	private String email;
	private String codigoSenha;
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataValidadeCodigo;
	private String senha;
	private String endereco;
	private String cep;
	
	@OneToMany(mappedBy = "pessoa", orphanRemoval = true, cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.EAGER)
	@Setter(value = AccessLevel.NONE)
	private List<PermissaoPessoa> permissaoPessoas;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataCriacao;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataAtualizacao;
	
	public Pessoa()
	{
		
	}
	
	public Pessoa(Long id,Cidade cidade, String nome, String cpf, String email, String codigoSenha, Date dataValidadeCodigo, String senha, String endereco, String cep, Date dataCriacao,
			Date dataAtualizacao) {
		super();
		this.id = id;
		this.cidade = cidade;
		this.nome = nome;
		this.cpf = cpf;
		this.email = email;
		this.codigoSenha = codigoSenha;
		this.dataValidadeCodigo = dataValidadeCodigo;
		this.senha = senha;
		this.endereco = endereco;
		this.cep = cep;
		this.dataCriacao = dataCriacao;
		this.dataAtualizacao = dataAtualizacao;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Cidade getCidade() {
		return cidade;
	}

	public void setCidade(Cidade cidade) {
		this.cidade = cidade;
	}

	public List<PermissaoPessoa> getPermissaoPessoas() {
		return permissaoPessoas;
	}

	public void setPermissaoPessoas(List<PermissaoPessoa> permissaoPessoas) {
		this.permissaoPessoas = permissaoPessoas;
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
	public void setCodigoSenha(String codigoSenha)
	{
		this.codigoSenha = codigoSenha;
	}
	public String getCodigoSenha()
	{
		return codigoSenha;
	}
	public Date getDataValidadeCodigo() {
		return dataValidadeCodigo;
	}
	public void setDataValidadeCodigo(Date dataValidadeCodigo) {
		this.dataValidadeCodigo = dataValidadeCodigo;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
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
	public Date getDataCriacao() {
		return dataCriacao;
	}
	public void setDataCriacao(Date dataCriacao) {
		this.dataCriacao = dataCriacao;
	}
	public Date getDataAtualizacao() {
		return dataAtualizacao;
	}
	public void setDataAtualizacao(Date dataAtualizacao) {
		this.dataAtualizacao = dataAtualizacao;
	}
	public void setPermissaoPessoa(List<PermissaoPessoa> pp)
	{
		for(PermissaoPessoa p:pp)
		{
			p.setPessoa(this);
		}
		this.permissaoPessoas = pp;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return permissaoPessoas.stream()
				.map(permissao -> new SimpleGrantedAuthority("ROLE_" + permissao.getPermissao().getNome()))
				.collect(Collectors.toList());
	}

	@Override
	public String getPassword() {
		return senha;
	}

	@Override
	public String getUsername() {
		return email;
	}
	
	@Override
	public boolean isAccountNonExpired() {
	    return true;
	}

	@Override
	public boolean isAccountNonLocked() {
	    return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
	    return true;
	}

	@Override
	public boolean isEnabled() {
	    return true;
	}
}
