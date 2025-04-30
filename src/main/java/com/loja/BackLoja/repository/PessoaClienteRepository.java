package com.loja.BackLoja.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.loja.BackLoja.entity.Pessoa;

public interface PessoaClienteRepository extends JpaRepository<Pessoa, Long>{

}
