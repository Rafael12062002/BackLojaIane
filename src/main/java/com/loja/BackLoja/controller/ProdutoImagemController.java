package com.loja.BackLoja.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.loja.BackLoja.entity.ProdutoImagem;
import com.loja.BackLoja.service.ProdutoImagemService;

@RestController
@RequestMapping("/api/produtoImagens")
public class ProdutoImagemController {

	@Autowired
	private ProdutoImagemService produtoImagemService;
	
	@GetMapping("/")
	public List<ProdutoImagem> buscarTodos()
	{
		return produtoImagemService.buscarTodos();
	}
	
	@PostMapping("/")
	public ProdutoImagem inserir(@RequestParam("idProduto") Long idProduto, @RequestParam("file") MultipartFile file)
	{
		return produtoImagemService.inserir(idProduto, file);
	}
	
	@PutMapping("/")
	public ProdutoImagem alterar(@RequestBody ProdutoImagem produtoImagem)
	{
		return produtoImagemService.alterar(produtoImagem);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> excluir(@PathVariable("id") Long id)
	{
		return produtoImagemService.excluir(id);
	}
}
