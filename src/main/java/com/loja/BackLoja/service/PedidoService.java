package com.loja.BackLoja.service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.loja.BackLoja.dto.PedidoResponseDTO;
import com.loja.BackLoja.dto.ProdutoDTO;
import com.loja.BackLoja.entity.CarrinhoCompra;
import com.loja.BackLoja.entity.Pedido;
import com.loja.BackLoja.entity.Pessoa;
import com.loja.BackLoja.repository.CarrinhoCompraRepository;
import com.loja.BackLoja.repository.PedidoRepository;
import com.loja.BackLoja.repository.PessoaRepository;
import com.loja.BackLoja.security.JwtUtil;

@Service
public class PedidoService {

	@Autowired
	private PedidoRepository pedidoRepository;
	
	@Autowired
	private JwtUtil jwtUtil;
	
	@Autowired
	private PessoaRepository pessoaRepository;
	
	@Autowired
	private CarrinhoCompraRepository carrinhoCompraRepository;
	
	public PedidoResponseDTO finalizarPedido(Long idCarrinho, String token)
	{
		String email = jwtUtil.getEmailToken(token);
		Pessoa cliente = pessoaRepository.findByEmail(email);
		
		CarrinhoCompra carrinho = carrinhoCompraRepository.findById(idCarrinho)
				.orElseThrow(() -> new RuntimeException("Carrinho não encontrado"));
		
		if(!carrinho.getPessoa().getId().equals(cliente.getId()))
		{
			throw new RuntimeException("Este carrinho não pertence a você");
		}
		
		if("FINALIZADO".equals(carrinho.getSituacao()))
		{
			throw new RuntimeException("Carrinho já foi finalizado");
		}
		
		carrinho.setSituacao("FINALIZADO");
		
		Pedido pedido = new Pedido();
		pedido.setCarrinho(carrinho);
		pedido.setPessoa(cliente);
		pedido.setDataPedido(new Date());
		pedido.setValorTotal(carrinho.getValorTotal());
		pedido.setStatus("REALIZADO");
		
		pedidoRepository.save(pedido);
		carrinhoCompraRepository.save(carrinho);
		
		return converterParaDTO(pedido);
	}
	
	
	private PedidoResponseDTO converterParaDTO(Pedido pedido)
	{
		PedidoResponseDTO dto = new PedidoResponseDTO();
		dto.setIdPedido(pedido.getId());
		dto.setData(pedido.getDataPedido());
		dto.setStatus(pedido.getStatus());
		dto.setValor(pedido.getValorTotal());
		
		List<ProdutoDTO> produtos = pedido.getCarrinho().getItens().stream()
				.map(cp -> {
					ProdutoDTO p = new ProdutoDTO();
					p.getNome();
					p.getValor();
					return p;
				}).collect(Collectors.toList());
		dto.setProdutos(produtos);
		return dto;
	}
}
