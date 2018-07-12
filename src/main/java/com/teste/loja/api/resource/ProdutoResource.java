package com.teste.loja.api.resource;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.teste.loja.api.model.Produto;
import com.teste.loja.api.repository.ProdutoRepository;
import com.teste.loja.api.service.ProdutoService;
@CrossOrigin
@RestController
@RequestMapping("/produtos")
public class ProdutoResource {
	
	@Autowired
	private ProdutoRepository produtoRepository;
	@Autowired
	private ProdutoService produtoService;
	
	@GetMapping
	public List<Produto> listar(){
		return produtoRepository.findAll();
	}
	
	@PostMapping
	public void salvar(@Valid @RequestBody Produto produto){
		produtoRepository.save(produto);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> buscaPorId(@PathVariable Long  id)	{
		Produto produto = produtoRepository.findOne(id);
		return produto != null ? ResponseEntity.ok(produto) : ResponseEntity.notFound().build(); 
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deletar(@PathVariable Long id){
		produtoRepository.delete(id);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> atualizar(@Valid @PathVariable Long id, @RequestBody Produto produto){
		Produto produtoAtual  = produtoService.editar(id, produto);
		return ResponseEntity.ok(produtoAtual);
		
	}
}
