package com.teste.loja.api.service;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.teste.loja.api.dao.ProductDAO;
import com.teste.loja.api.model.Produto;
import com.teste.loja.api.repository.ProdutoRepository;

@Service
public class ProdutoService {
	@Autowired
	private ProdutoRepository produtoRepository;
	@Autowired
	private ProductDAO productDAO;
	

	
	public Produto  editar(Long id, Produto produto){
		Produto produtoAtual = produtoRepository.findOne(id);
		if(produtoAtual == null){
			throw new EmptyResultDataAccessException(1);
		}
		BeanUtils.copyProperties(produto, produtoAtual,"id");
		return produtoRepository.save(produtoAtual);
	}
	public List<Produto> getByFilter(String name){
		return productDAO.getByFilter(name);
	}

}
