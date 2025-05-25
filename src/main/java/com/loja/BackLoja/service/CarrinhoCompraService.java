package com.loja.BackLoja.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.loja.BackLoja.dto.CarrinhoRequestDTO;
import com.loja.BackLoja.dto.CarrinhoResponseDTO;
import com.loja.BackLoja.dto.ItemCarrinhoDTO;
import com.loja.BackLoja.dto.ProdutoDTO;
import com.loja.BackLoja.entity.CarrinhoCompra;
import com.loja.BackLoja.entity.CarrinhoCompraProduto;
import com.loja.BackLoja.entity.Pessoa;
import com.loja.BackLoja.entity.Produto;
import com.loja.BackLoja.repository.CarrinhoCompraRepository;
import com.loja.BackLoja.repository.PessoaRepository;
import com.loja.BackLoja.repository.ProdutoRepository;
import com.loja.BackLoja.security.JwtUtil;

@Service
public class CarrinhoCompraService {

	@Autowired
	private CarrinhoCompraRepository carrinhoCompraRepository;
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private PessoaRepository pessoaRepository;
	
	@Autowired
	private JwtUtil jwtUtil;
	
	public CarrinhoResponseDTO criarCarrinho(CarrinhoRequestDTO request, String token)
	{
		String email = jwtUtil.getEmailToken(token);
		Pessoa pessoa = pessoaRepository.findByEmail(email);
		
		CarrinhoCompra carrinho = new CarrinhoCompra();
		carrinho.setPessoa(pessoa);
		carrinho.setDataCriacao(new Date());
		carrinho.setSituacao("PENDENTE");
		
		List<CarrinhoCompraProduto> produtos = new ArrayList<>();
		
		for(ItemCarrinhoDTO itemDTO : request.getItens())
		{
			Produto produto = produtoRepository.findById(itemDTO.getIdProduto())
					.orElseThrow(() -> new RuntimeException("Produto não encontrado"));
			
			CarrinhoCompraProduto item = new CarrinhoCompraProduto();
			item.setProduto(produto);
			item.setQuantidade(itemDTO.getQuantidade());
			item.setValor(itemDTO.getValor());
			item.setObservacao(itemDTO.getObservacao());
			item.setCarrinho(carrinho);
			item.setDataCriacao(new Date());
			produtos.add(item);
		}
		
		carrinho.setItens(produtos);
		carrinho.setValorTotal(calcularValorTotal(produtos));
		CarrinhoCompra salvo = carrinhoCompraRepository.save(carrinho);
		return converterParaDTO(salvo);
	}
	
	public CarrinhoResponseDTO removerProduto(Long idCarrinho, Long idProduto)
	{
		CarrinhoCompra carrinho = carrinhoCompraRepository.findById(idCarrinho)
				.orElseThrow(() -> new RuntimeException("Carrinho não encontrado"));
		
		carrinho.getItens().removeIf(item -> item.getProduto().getId().equals(idProduto));
		
		carrinho.setValorTotal(carrinho.getValorTotal());
		CarrinhoCompra salvo = carrinhoCompraRepository.saveAndFlush(carrinho);
		return converterParaDTO(salvo);
	}
	
	public CarrinhoResponseDTO adicionarProduto(Long idCarrinho, ItemCarrinhoDTO itemDTO)
	{
		CarrinhoCompra carrinho = carrinhoCompraRepository.findById(idCarrinho)
				.orElseThrow(() -> new RuntimeException("Carrinho não encontrado"));
		
		Produto produto = produtoRepository.findById(itemDTO.getIdProduto())
				.orElseThrow(() -> new RuntimeException("Produto nãp encontrado"));
		
		CarrinhoCompraProduto item = new CarrinhoCompraProduto();
		item.setProduto(produto);
		item.setQuantidade(itemDTO.getQuantidade());
		item.setValor(itemDTO.getValor());
		item.setObservacao(itemDTO.getObservacao());
		item.setCarrinho(carrinho);
		item.setDataCriacao(new Date());
		
		carrinho.getItens().add(item);
		carrinho.setDataAtualizacao(new Date());
		
		Double novoTotal = carrinho.getItens().stream()
			    .mapToDouble(CarrinhoCompraProduto::getValorTotal)
			    .sum();
			carrinho.setValorTotal(novoTotal);
		
		CarrinhoCompra salvo = carrinhoCompraRepository.save(carrinho);
		return converterParaDTO(salvo);
	}
	
	public void deletarCarrinho(Long idCarrinho)
	{
		CarrinhoCompra carrinho = carrinhoCompraRepository.findById(idCarrinho)
				.orElseThrow(() -> new RuntimeException("Carrinho não encontrado"));
		
		carrinhoCompraRepository.delete(carrinho);
	}
	
	//Metodos para usar nas operações do service como converter e calcular valor
	private CarrinhoResponseDTO converterParaDTO(CarrinhoCompra carrinho)
	{
		CarrinhoResponseDTO dto = new CarrinhoResponseDTO();
		dto.setId(carrinho.getId());
		dto.setValor(carrinho.getValorTotal());
		
		List<ProdutoDTO> produtosDTO = carrinho.getItens().stream()
				.map(cp -> {
					ProdutoDTO p = new ProdutoDTO();
					p.setNome(cp.getProduto().getNome());
					p.setValor(cp.getProduto().getValorVenda());
					return p;
				}).collect(Collectors.toList());
		dto.setProdutos(produtosDTO);
		return dto;
	}
	
	private Double calcularValorTotal(List<CarrinhoCompraProduto> itens)
	{
		return itens.stream()
				.map(item -> item.getValor() * item.getQuantidade())
				.reduce(0.0, Double::sum);
	}
}
