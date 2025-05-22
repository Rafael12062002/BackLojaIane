package com.loja.BackLoja.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.loja.BackLoja.entity.Categoria;
import com.loja.BackLoja.service.CategoriaService;

@RestController
@RequestMapping("/api/categoria")
public class CategoriaController {

	@Autowired
	private CategoriaService categoriaService;
	
	@GetMapping("/")
	public List<Categoria> buscarTodos()
	{
		return categoriaService.buscarTodos();
	}
	
	@PreAuthorize("hasRole('FUNCIONARIO')")
	@PostMapping("/")
	public Categoria inserir(@RequestBody Categoria categoria)
	{
		return categoriaService.inserir(categoria);
	}
	
	@PreAuthorize("hasRole('FUNCIONARIO')")
	@PutMapping("/")
	public Categoria alterar(@RequestBody Categoria categoria)
	{
		return categoriaService.alterar(categoria);
	}
	
	@PreAuthorize("hasRole('FUNCIONARIO')")
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> excluir(@PathVariable("id") Long id)
	{
		categoriaService.excluir(id);
		return ResponseEntity.ok().build();
	}
}
