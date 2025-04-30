package com.loja.BackLoja.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.loja.BackLoja.entity.Pessoa;
import java.util.List;


public interface PessoaRepository extends JpaRepository<Pessoa, Long>{

	
	Pessoa findByEmail(String email);
	
	Pessoa findByEmailAndCodigoSenha(String email, String codigoSenha);
}
