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

import com.loja.BackLoja.entity.Estado;
import com.loja.BackLoja.service.EstadoService;

@RestController
@RequestMapping("/api/estado")
public class EstadoController {

	@Autowired
	private EstadoService estadoService;

	@GetMapping({"", "/"})
	public List<Estado> buscarTodos()
	{
		return estadoService.buscarTodos();
	}
	
	@PreAuthorize("hasRole('FUNCIONARIO')")
	@PostMapping("/")
	public Estado insert(@RequestBody Estado estado)
	{
		return estadoService.inserir(estado);
	}
	
	@PreAuthorize("hasRole('FUNCIONARIO')")
	@PutMapping("/")
	public Estado alterar(@RequestBody Estado estado)
	{
		return estadoService.alterar(estado);
	}
	
	@PreAuthorize("hasRole('FUNCIONARIO')")
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> excluir(@PathVariable("id") Long id)
	{
		estadoService.excluir(id);
		return ResponseEntity.ok().build();
	}
}
