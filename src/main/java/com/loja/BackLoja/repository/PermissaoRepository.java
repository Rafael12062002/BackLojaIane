package com.loja.BackLoja.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.loja.BackLoja.entity.Permissao;

@Repository
public interface PermissaoRepository extends JpaRepository<Permissao, Long>{

	List<Permissao> findByNome(String nome);
}
