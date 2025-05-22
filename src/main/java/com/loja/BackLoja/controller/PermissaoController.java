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

import com.loja.BackLoja.entity.Permissao;
import com.loja.BackLoja.service.PermissaoService;

@RestController
@RequestMapping("/api/permissao")
public class PermissaoController {

	@Autowired
	private PermissaoService permissaoService;
	
	@PreAuthorize("hasRole('FUNCIONARIO')")
	@GetMapping("/")
	public List<Permissao> buscarTodos()
	{
		return permissaoService.buscarTodos();
	}
	
	@PreAuthorize("hasRole('FUNCIONARIO')")
	@PostMapping("/")
	public Permissao inserir(@RequestBody Permissao permissao)
	{
		return permissaoService.inserir(permissao);
	}
	
	@PreAuthorize("hasRole('FUNCIONARIO')")
	@PutMapping("/")
	public Permissao alterar(@RequestBody Permissao permissao)
	{
		return permissaoService.alterar(permissao);
	}
	
	@PreAuthorize("hasRole('FUNCIONARIO')")
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> excluir(@PathVariable("id") Long id)
	{
		permissaoService.excluir(id);
		return ResponseEntity.ok().build();
	}
}
