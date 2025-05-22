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

import com.loja.BackLoja.entity.Marca;
import com.loja.BackLoja.service.MarcaService;

@RestController
@RequestMapping("/api/marca")
public class MarcaController {

	@Autowired
	private MarcaService marcaService;
	
	@GetMapping("/")
	public List<Marca> buscarTodos()
	{
		return marcaService.buscarTodos();
	}
	
	@PreAuthorize("hasRole('FUNCIONARIO')")
	@PostMapping("/")
	public Marca inserir(@RequestBody Marca marca)
	{
		return marcaService.inserir(marca);
	}
	
	@PreAuthorize("hasRole('FUNCIONARIO')")
	@PutMapping("/")
	public Marca alterar(@RequestBody Marca marca)
	{
		return marcaService.alterar(marca);
	}
	
	@PreAuthorize("hasRole('FUNCIONARIO')")
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> excluir(@PathVariable("id") Long id)
	{
		marcaService.excluir(id);
		return ResponseEntity.ok().build();
	}
}
