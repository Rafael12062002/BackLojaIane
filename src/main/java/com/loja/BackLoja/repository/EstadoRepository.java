package com.loja.BackLoja.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.loja.BackLoja.entity.Estado;

@Repository
public interface EstadoRepository extends JpaRepository<Estado, Long>{

	
}
