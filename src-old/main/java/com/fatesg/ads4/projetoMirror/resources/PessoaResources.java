package com.fatesg.ads4.projetoMirror.resources;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.fatesg.ads4.projetoMirror.domain.Feedback;
import com.fatesg.ads4.projetoMirror.domain.Pessoa;
import com.fatesg.ads4.projetoMirror.services.EnderecoService;
import com.fatesg.ads4.projetoMirror.services.PessoaService;

@RestController
@RequestMapping(value = "/pessoas")
public class PessoaResources {

	@Autowired
	PessoaService service;
	
	@Autowired
	EnderecoService enderecoService;
	
	@GetMapping(path = "/{id}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<Pessoa> buscar(@PathVariable Integer id) {
		
		Pessoa pessoa = service.buscarId(id);
		
		return ResponseEntity.ok().body(pessoa);
		
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<Pessoa>> buscarTudo(){
		
		List<Pessoa> pessoas = service.buscarTudo();
		
		return ResponseEntity.ok().body(pessoas);
		
	}
	
	@RequestMapping(value="/{id}/feedbacks", method = RequestMethod.GET)
	public ResponseEntity<List<Feedback>> buscarPessoas(@PathVariable Integer id){
		
		Pessoa pessoa = service.buscarId(id);
		
		List<Feedback> feedbacks = pessoa.getFeedbacks();
		
		return ResponseEntity.ok().body(feedbacks);
		
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> inserir(@RequestBody Pessoa pessoa){
		
		enderecoService.inserir(pessoa.getEndereco());
		
		service.inserir(pessoa);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(pessoa.getId()).toUri();
		
		return ResponseEntity.created(uri).build();
		
	}
	
	@RequestMapping(value="/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> atualizar(@RequestBody Pessoa pessoa, @PathVariable Integer id){
		
		pessoa.setId(id);
		service.atualizar(pessoa);
		
		return ResponseEntity.noContent().build();
		
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<Void> deletar(@PathVariable Integer id) {
		
		service.deletePorId(id);
		
		return ResponseEntity.noContent().build();
		
	}
	
}
