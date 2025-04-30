package com.loja.BackLoja.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.loja.BackLoja.entity.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long>{

}
