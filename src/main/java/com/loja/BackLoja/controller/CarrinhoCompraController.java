package com.loja.BackLoja.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.loja.BackLoja.dto.CarrinhoRequestDTO;
import com.loja.BackLoja.dto.CarrinhoResponseDTO;
import com.loja.BackLoja.dto.ItemCarrinhoDTO;
import com.loja.BackLoja.service.CarrinhoCompraService;

@RestController
@RequestMapping("/api/cliente/carrinho")
public class CarrinhoCompraController {

	@Autowired
	private CarrinhoCompraService carrinhoCompraService;
	
	@PostMapping("/criar")
	public CarrinhoResponseDTO criarCarrinho(@RequestBody CarrinhoRequestDTO carrinho, @RequestHeader("Authorization") String authorizationHeader)
	{
		String token = authorizationHeader.replace("Bearer ", "");
		return carrinhoCompraService.criarCarrinho(carrinho, token);
	}
	
	@PostMapping("/adicionarProduto")
	public CarrinhoResponseDTO inserirProdutoCarrinho(@RequestParam Long idCarrinho, @RequestBody ItemCarrinhoDTO itemDTO)
	{
		return carrinhoCompraService.adicionarProduto(idCarrinho, itemDTO);
	}
	
	@DeleteMapping("/removerProduto")
	public CarrinhoResponseDTO removerProdutoCarrinho(@RequestParam Long idCarrinho, @RequestParam Long idProduto)
	{
		return carrinhoCompraService.removerProduto(idCarrinho, idProduto);
	}
	
	@DeleteMapping("/deletar/{id}")
	public ResponseEntity<Void> deletarCarrinho(@PathVariable("id") Long id)
	{
		carrinhoCompraService.deletarCarrinho(id);
		return ResponseEntity.ok().build();
	}
}
