package com.fatesg.ads4.projetoMirror.resources;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.fatesg.ads4.projetoMirror.domain.AplicacaoFeedback;
import com.fatesg.ads4.projetoMirror.services.AplicacaofeedbackService;

@RestController
@RequestMapping(value="/aplicacoes")
public class AplicacaoFeedbackResources {
	
	@Autowired
	AplicacaofeedbackService service;
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<AplicacaoFeedback> buscar(@PathVariable Integer id) {
		
		AplicacaoFeedback aplicacao = service.buscarId(id);
		
		return ResponseEntity.ok().body(aplicacao);
		
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<AplicacaoFeedback>> buscarTudo(){
		
		List<AplicacaoFeedback> aplicacao = service.buscarTudo();
		
		return ResponseEntity.ok().body(aplicacao);
		
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> inserir(@RequestBody AplicacaoFeedback aplicacao){
		
		service.inserir(aplicacao);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(aplicacao.getId()).toUri();
		
		return ResponseEntity.created(uri).build();
		
	}
	
	@RequestMapping(value="/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> atualizar(@RequestBody AplicacaoFeedback aplicacao, @PathVariable Integer id){
		
		aplicacao.setId(id);
		service.atualizar(aplicacao);
		
		return ResponseEntity.noContent().build();
		
	}
	
	

}
