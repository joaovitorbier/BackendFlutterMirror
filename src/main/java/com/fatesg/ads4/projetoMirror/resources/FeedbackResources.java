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
import com.fatesg.ads4.projetoMirror.domain.TextosFeedbackDTO;
import com.fatesg.ads4.projetoMirror.enumeradores.FeedBackStatus;
import com.fatesg.ads4.projetoMirror.services.FeedbackService;
import com.fatesg.ads4.projetoMirror.services.PessoaService;
import com.fatesg.ads4.projetoMirror.services.TextosFeedbackDTOService;

@RestController
@RequestMapping(value="/feedbacks")
public class FeedbackResources {
	
	@Autowired
	FeedbackService service;
	
	@Autowired
	PessoaService pessoaService;
	
	@Autowired
	TextosFeedbackDTOService feedbackTextoService;

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
	
	@RequestMapping(value="/textoFeedback/{id}",method = RequestMethod.PUT)
	public ResponseEntity<Void> inserirTextoFeedback(@RequestBody TextosFeedbackDTO textoFeedback, @PathVariable Integer id){
		
		feedbackTextoService.InserirTexto(textoFeedback, id);
		
		return ResponseEntity.noContent().build();
		
	}
	
	@RequestMapping(value="/replicaFeedback/{id}",method = RequestMethod.PUT)
	public ResponseEntity<Void> inserirReplicaFeedback(@RequestBody TextosFeedbackDTO textoFeedback, @PathVariable Integer id){
		
		feedbackTextoService.InserirReplica(textoFeedback, id);
		
		return ResponseEntity.noContent().build();
		
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
	@RequestMapping(value="/pessoa/avaliado/{id}", method=RequestMethod.GET)
	public ResponseEntity<List<Feedback>> buscarPorAvaliado(@PathVariable Integer id) {
		
		List<Feedback> feedbacks = service.buscarTudo();
		
		feedbacks.removeIf(i -> (i.getAvaliado().getId() != id));
		/*
		for(int i = 0; i < feedbacks.size();i++) {
			if(feedbacks.get(i).getAvaliado().getId() != id) {
				feedbacks.remove(i);
			}
		}
		*/
		return ResponseEntity.ok().body(feedbacks);
		
	}
	
	//BUSCA TODOS OS FEEDBACKS DAQUELE PESSOA AVALIADOR
	@RequestMapping(value="/pessoa/avaliador/{id}", method=RequestMethod.GET)
	public ResponseEntity<List<Feedback>> buscarPorAvaliador(@PathVariable Integer id) {
		
		List<Feedback> feedbacks = service.buscarTudo();
		
		feedbacks.removeIf(i -> (i.getAvaliador().getId() != id));
		/*
		for(int i = 0; i < feedbacks.size();i++) {
			if(feedbacks.get(i).getAvaliado().getId() != id) {
				feedbacks.remove(i);
			}
		}
		*/
		return ResponseEntity.ok().body(feedbacks);
		
	}
	
	
	
	/* TENTATIVA FALHA DE FAZER UM ENDPOINT QUE BUSCA TODOS OS FEEDBACKS
	@RequestMapping(value="/avaliado/aplicados/pessoa/{id}", method = RequestMethod.GET)
	public ResponseEntity<List<Feedback>> buscarFeedbacksAvaliadoAplicados(@PathVariable Integer id){
		
		Pessoa pessoa = pessoaService.buscarId(id);
		
		List<Feedback> feedbacks = service.buscarPorAvaliado(pessoa);
		/*
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
		
	/*
	@RequestMapping(value="/avaliado/pendentes/pessoa/{id}", method = RequestMethod.GET)
	public ResponseEntity<List<Feedback>> buscarFeedbacksAvaliadoPendentes(@PathVariable Integer id){
		
		Pessoa pessoa = pessoaService.buscarId(id);
		
		List<Feedback> feedbacks = service.buscarPorAvaliado(pessoa);
		
		for(int i = 0; i < feedbacks.size(); i++) {
			
			if(feedbacks.get(i).getStatus() == FeedBackStatus.APLICADO) {
				
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
		
		
		
		
		
		
		return ResponseEntity.ok().body(feedbacks);
		
	}
	*/
	
	/*
	//BUSCA TODOS OS FEEDBACKS DAQUELE AVALIADOR
	@RequestMapping(value="/avaliador/pessoa/{id}", method = RequestMethod.GET)
	public ResponseEntity<List<Feedback>> buscarFeedbacksAvaliador(@PathVariable Integer id){
		
		Pessoa pessoa = pessoaService.buscarId(id);
		
		List<Feedback> feedbacks = service.buscarPorAvaliador(pessoa);
		
		return ResponseEntity.ok().body(feedbacks);
		
	}
	
	//BUSCA TODOS OS FEEDBACKS PENDENTES DAQUELE AVALIADOR
	@RequestMapping(value="/avaliador/pendentes/pessoa/{id}", method = RequestMethod.GET)
	public ResponseEntity<List<Feedback>> buscarFeedbacksAvaliadorPendentes(@PathVariable Integer id){
			
			Pessoa pessoa = pessoaService.buscarId(id);
			
			List<Feedback> feedbacks = service.buscarPorAvaliador(pessoa);
			
			List<Feedback> feedbacksPendentes = new ArrayList<Feedback>();
			
			//SELECIONA AQUI OS PENDENTES E JOGA NESSA LISTA
			for(int i = 0; i < feedbacks.size(); i++) {
				if(feedbacks.get(i).getStatus() != FeedBackStatus.APLICADO) {
					feedbacksPendentes.add(feedbacks.get(i));
				}
			}
			
			return ResponseEntity.ok().body(feedbacksPendentes);
		}
	


//BUSCA TODOS OS FEEDBACKS PENDENTES DAQUELE AVALIADOR
@RequestMapping(value="/avaliador/aplicados/pessoa/{id}", method = RequestMethod.GET)
public ResponseEntity<List<Feedback>> buscarFeedbacksAvaliadorAplicados(@PathVariable Integer id){
		
		Pessoa pessoa = pessoaService.buscarId(id);
		
		List<Feedback> feedbacks = service.buscarPorAvaliador(pessoa);
		
		List<Feedback> feedbacksPendentes = new ArrayList<Feedback>();
		
		//SELECIONA AQUI OS PENDENTES E JOGA NESSA LISTA
		for(int i = 0; i < feedbacks.size(); i++) {
			if(feedbacks.get(i).getStatus() == FeedBackStatus.APLICADO) {
				feedbacksPendentes.add(feedbacks.get(i));
			}
		}
		
		return ResponseEntity.ok().body(feedbacksPendentes);
	}
*/
}
	