package com.fatesg.ads4.projetoMirror.resources;

import java.net.URI;
import java.util.ArrayList;
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
import com.fatesg.ads4.projetoMirror.domain.Pessoa;
import com.fatesg.ads4.projetoMirror.enumeradores.FeedBackStatus;
import com.fatesg.ads4.projetoMirror.services.FeedbackService;
import com.fatesg.ads4.projetoMirror.services.PessoaService;

@RestController
@RequestMapping(value="/feedbacks")
public class FeedbackResources {
	
	@Autowired
	FeedbackService service;
	
	@Autowired
	PessoaService pessoaService;

	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<Feedback> buscar(@PathVariable Integer id) {
		
		Feedback feedback = service.buscarId(id);
		
		return ResponseEntity.ok().body(feedback);
		
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<Feedback>> buscarTudo(){
		
		List<Feedback> feedbacks = service.buscarTudo();
		
		return ResponseEntity.ok().body(feedbacks);
		
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> inserir(@RequestBody Feedback feedback){
		
		service.inserir(feedback);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(feedback.getId()).toUri();
		
		return ResponseEntity.created(uri).build();
		
	}
	
	@RequestMapping(value="/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> atualizar(@RequestBody Feedback feedback, @PathVariable Integer id){
		
		feedback.setId(id);
		service.atualizar(feedback);
		
		return ResponseEntity.noContent().build();
		
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<Void> deletar(@PathVariable Integer id) {
		
		service.deletePorId(id);
		
		return ResponseEntity.noContent().build();
		
	}
	
	//BUSCA TODOS OS FEEDBACKS DAQUELA PESSOA AVALIADA
	@RequestMapping(value="/avaliado/pessoa/{id}", method = RequestMethod.GET)
	public ResponseEntity<List<Feedback>> buscarFeedbacksAvaliado(@PathVariable Integer id){
		
		Pessoa pessoa = pessoaService.buscarId(id);
		
		List<Feedback> feedbacks = service.buscarPorAvaliado(pessoa);
		
		for(int i = 0; i < feedbacks.size(); i++) {
			
			if(feedbacks.get(i).getStatus() != FeedBackStatus.APLICADO) {
				
				feedbacks.remove(i);
				
			}
			
		}
		
		/*
		List<Feedback> feedbacksFinalizados = new ArrayList<Feedback>();
		
		for(int i = 0; i < feedbacks.size(); i++) {
			
			if(feedbacks.get(i).getStatus() == FeedBackStatus.APLICADO) {
				
				feedbacksFinalizados.add(feedbacks.get(i));
				
			}
			
		}
		
		return ResponseEntity.ok().body(feedbacksFinalizados);
		*/
		
		return ResponseEntity.ok().body(feedbacks);
		
	}
	
	//BUSCA TODOS OS FEEDBACKS DAQUELE AVALIADOR
	@RequestMapping(value="/avaliador/pessoa/{id}", method = RequestMethod.GET)
	public ResponseEntity<List<Feedback>> buscarFeedbacksAvaliador(@PathVariable Integer id){
		
		Pessoa pessoa = pessoaService.buscarId(id);
		
		List<Feedback> feedbacks = service.buscarPorAvaliador(pessoa);
		
		return ResponseEntity.ok().body(feedbacks);
		
	}
	
	//BUSCA TODOS OS FEEDBACKS PENDENTES DAQUELE AVALIADOR
	@RequestMapping(value="/pendentes/pessoa/{id}", method = RequestMethod.GET)
	public ResponseEntity<List<Feedback>> buscarFeedbacksAvaliadorPendentes(@PathVariable Integer id){
			
			Pessoa pessoa = pessoaService.buscarId(id);
			
			List<Feedback> feedbacks = service.buscarPorAvaliador(pessoa);
			
			List<Feedback> feedbacksPendentes = new ArrayList<Feedback>();
			
			//SELECIONA AQUI OS PENDENTES E JOGA NESSA LISTA
			for(int i = 0; i < feedbacks.size(); i++) {
				if(feedbacks.get(i).getStatus() == FeedBackStatus.PENDENTE) {
					feedbacksPendentes.add(feedbacks.get(i));
				}
			}
			
			return ResponseEntity.ok().body(feedbacksPendentes);
		}
	
}