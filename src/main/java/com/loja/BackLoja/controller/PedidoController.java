package com.loja.BackLoja.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.loja.BackLoja.dto.PedidoResponseDTO;
import com.loja.BackLoja.service.PedidoService;

@RestController
@RequestMapping("/api/pedido")
public class PedidoController {

	@Autowired
	private PedidoService pedidoService;
	
	@PostMapping("/finalizar")
	public ResponseEntity<PedidoResponseDTO> finalizarPedido(@RequestParam Long idCarrinho, @RequestHeader("Authorization") String token)
	{
		String tokenLimpo = token.replace("Bearer ", "");
		return ResponseEntity.ok(pedidoService.finalizarPedido(idCarrinho, tokenLimpo));
	}
}
