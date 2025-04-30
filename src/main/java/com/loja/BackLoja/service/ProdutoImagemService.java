package com.loja.BackLoja.service;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.loja.BackLoja.entity.Produto;
import com.loja.BackLoja.entity.ProdutoImagem;
import com.loja.BackLoja.repository.ProdutoImagemRepository;
import com.loja.BackLoja.repository.ProdutoRepository;

@Service
public class ProdutoImagemService {

	@Autowired
	private ProdutoImagemRepository produtoImagemRepository;
	@Autowired
	private ProdutoRepository produtoRepository;
	
	public List<ProdutoImagem> buscarTodos()
	{
		return produtoImagemRepository.findAll();
	}
	
	public ProdutoImagem inserir(Long idProduto, MultipartFile file)
	{
		Produto produto = produtoRepository.findById(idProduto).get();
		ProdutoImagem produtoImagem = new ProdutoImagem();
		
		try {
			if(!file.isEmpty())
			{
				byte[] bytes = file.getBytes();
				String nomeImagem = String.valueOf(produto.getId()) + file.getOriginalFilename();
				Path caminho = Paths.get("c:/imagens/" + nomeImagem);
				Files.write(caminho, bytes);
				produtoImagem.setNome(nomeImagem);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		
		produtoImagem.setProduto(produto);
		produtoImagem.setDataCriacao(new Date());
		produtoImagem = produtoImagemRepository.saveAndFlush(produtoImagem);
		return produtoImagem;
	}
	
	public ProdutoImagem alterar(ProdutoImagem produtoImagem)
	{
		produtoImagem.setDataAtualizacao(new Date());
		produtoImagem.getDataCriacao();
		return produtoImagemRepository.saveAndFlush(produtoImagem);
	}
	
	public ResponseEntity<Void> excluir(Long id)
	{
		ProdutoImagem produtoImagem = produtoImagemRepository.findById(id).get();
		return ResponseEntity.ok().build();
	}
}
