package com.loja.BackLoja.entity;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;

@Entity
@Table(name = "cidade")
@Data
public class Cidade {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private String nome;
	
	@ManyToOne
	@JoinColumn(name = "idEstado")
	private Estado estado;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataCriacao;
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataAtualizacao;
	public Cidade(Long id, String nome, Estado estado, Date dataCriacao, Date dataAtualizacao) {
		super();
		this.id = id;
		this.nome = nome;
		this.estado = estado;
		this.dataCriacao = dataCriacao;
		this.dataAtualizacao = dataAtualizacao;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return nome;
	}
	public void setName(String nome) {
		this.nome = nome;
	}
	public Estado getEstado() {
		return estado;
	}
	public void setEstado(Estado estado) {
		this.estado = estado;
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
	
	
}
