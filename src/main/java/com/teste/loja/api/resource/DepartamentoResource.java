package com.teste.loja.api.resource;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.teste.loja.api.model.Departamento;
import com.teste.loja.api.model.Produto;
import com.teste.loja.api.repository.DepartamentoRepository;
import com.teste.loja.api.service.DepartamentoService;

@RestController
@RequestMapping("/departamentos")
public class DepartamentoResource {
	@Autowired
	private DepartamentoRepository departamentoRepository;
	
	@Autowired
	private DepartamentoService departamentoService;
	

	@PostMapping
	public void salvarDepartamento(@Valid @RequestBody Departamento departamento){
		departamentoRepository.save(departamento);
	}
	@GetMapping
	public List<Departamento> listarTodos(){
		return departamentoRepository.findAll();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> buscarPorId(@PathVariable Long id){
		Departamento departamento = departamentoRepository.findOne(id);
		return departamento != null ? ResponseEntity.ok(departamento) :ResponseEntity.notFound().build(); 
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deletarDepartamento(@PathVariable Long id){
		departamentoRepository.delete(id);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> atualizar(@Valid @PathVariable Long id, @RequestBody Departamento departamento){
		Departamento departamentoAtual  = departamentoService.editar(id, departamento);
		return ResponseEntity.ok(departamentoAtual);
		
	}
	
}
