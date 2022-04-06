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

import com.fatesg.ads4.projetoMirror.domain.Feedback;
import com.fatesg.ads4.projetoMirror.domain.Motivo;
import com.fatesg.ads4.projetoMirror.services.MotivoService;

@RestController
@RequestMapping(value = "/motivos")
public class MotivoResources {

	@Autowired
	private MotivoService service;
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Motivo> buscarMotivo(@PathVariable Integer id) {
		
		Motivo motivo = service.buscarId(id);
		
		return ResponseEntity.ok().body(motivo);
		
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<Motivo>> buscarTudo() {
		
		List<Motivo> list = service.buscarTudo();
		
		return ResponseEntity.ok().body(list);
		
	}
	
	@RequestMapping(value="/{id}/feedbacks", method = RequestMethod.GET)
	public ResponseEntity<List<Feedback>> buscarFeedbacks(@PathVariable Integer id){
		
		Motivo motivo = service.buscarId(id);
		
		List<Feedback> feedbacks = motivo.getFeedbacks();
		
		return ResponseEntity.ok().body(feedbacks);
		
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> inserir(@RequestBody Motivo motivo){
		
		service.inserir(motivo);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(motivo.getId()).toUri();
		
		return ResponseEntity.created(uri).build();
		
	}
	
	@RequestMapping(value="/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> atualizar(@RequestBody Motivo motivo, @PathVariable Integer id){
		
		motivo.setId(id);
		service.atualizar(motivo);
		
		return ResponseEntity.noContent().build();
		
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<Void> deletar(@PathVariable Integer id) {
		
		service.deletePorId(id);
		
		return ResponseEntity.noContent().build();
		
	}
	
}
