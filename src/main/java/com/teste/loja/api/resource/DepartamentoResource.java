package com.teste.loja.api.resource;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import com.teste.loja.api.model.Departamento;
import com.teste.loja.api.model.Notifications;
import com.teste.loja.api.repository.DepartamentoRepository;
import com.teste.loja.api.service.DepartamentoService;

@RestController
@RequestMapping("/departamentos")
public class DepartamentoResource {
	
	@Autowired
	private DepartamentoRepository departamentoRepository;

	@Autowired
	private SimpMessagingTemplate simpMessagingTemplate;
	
	@Autowired
	private DepartamentoService departamentoService;
	 private Notifications notifications = new Notifications(0);
	
	@PostMapping
	public void salvarDepartamento(@Valid @RequestBody Departamento departamento){
		departamentoRepository.save(departamento);
		 notifications.increment();
		 List<Departamento> departamentos = departamentoRepository.findAll();
		 simpMessagingTemplate.convertAndSend("/topic/notification",departamentos.size());
	}
	
	@GetMapping
	public List<Departamento> listarTodos(){
		return departamentoRepository.findAll();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> buscarPorId(@PathVariable Long id){
		Departamento departamento = departamentoRepository.findOne(id);
		return departamento != null ? ResponseEntity.ok(departamento):ResponseEntity.notFound().build();
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
	
//	@MessageMapping("/secured/room") 
//	public void sendSpecific(@Payload Message msg, Principal user, @Header("simpSessionId") String sessionId) throws Exception { 
//		OutputMessage out = new OutputMessage(msg.getFrom(), msg.getText(),new SimpleDateFormat("HH:mm").format(new Date())); 
//		simpMessagingTemplate.convertAndSendToUser(msg.getTo(), "/secured/user/queue/specific-user", out); 
//	}

	
	
//	@RequestMapping("habilita/promocao")
//	public SseEmitter enableQuickPromoNotifier(){
//		emiter.onCompletion(() -> {
//	            synchronized (this.sseEmitters) {
//	                this.sseEmitters.remove(emiter);
//	            }
//	        });
//
//		emiter.onTimeout(()-> {
//			emiter.complete();
//	        });
//
//	        // Put context in a map
//	        sseEmitters.add(emiter);
//
//	   return  emiter;
//	}
}
