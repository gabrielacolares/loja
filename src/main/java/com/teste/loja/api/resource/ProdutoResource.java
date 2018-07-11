package com.teste.loja.api.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.teste.loja.api.model.Produto;
import com.teste.loja.api.repository.ProdutoRepository;

@RestController
@RequestMapping("/produtos")
public class ProdutoResource {
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@GetMapping
	public List<Produto> listar(){
		return produtoRepository.findAll();
	}
	
	@PostMapping
	public void salvar(@RequestBody Produto produto){
		produtoRepository.save(produto);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> buscaPorId(@PathVariable Long  id)	{
		Produto produto = produtoRepository.findOne(id);
		return produto != null ? ResponseEntity.ok(produto) : ResponseEntity.notFound().build(); 
	}
	
}
