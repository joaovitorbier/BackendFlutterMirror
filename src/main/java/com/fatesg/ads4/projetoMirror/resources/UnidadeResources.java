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

import com.fatesg.ads4.projetoMirror.domain.Pessoa;
import com.fatesg.ads4.projetoMirror.domain.Unidade;
import com.fatesg.ads4.projetoMirror.services.UnidadeService;

@RestController
@RequestMapping(value = "/unidades")
public class UnidadeResources {
	
	@Autowired
	UnidadeService service;
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Unidade> buscarUnidade(@PathVariable Integer id){
		
		Unidade unidade = service.buscarId(id);
		
		return ResponseEntity.ok().body(unidade);
		
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<Unidade>> buscarTudo(){
		
		List<Unidade> unidades = service.buscarTudo();
		
		return ResponseEntity.ok().body(unidades);
		
	}
	
	@RequestMapping(value="/{id}/pessoas", method = RequestMethod.GET)
	public ResponseEntity<List<Pessoa>> buscarPessoas(@PathVariable Integer id){
		
		Unidade unidade = service.buscarId(id);
		
		List<Pessoa> pessoas = unidade.getPessoas();
		
		return ResponseEntity.ok().body(pessoas);
		
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> inserir(@RequestBody Unidade unidade){
		
		service.inserir(unidade);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(unidade.getId()).toUri();
		
		return ResponseEntity.created(uri).build();
		
	}
	
	@RequestMapping(value="/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> atualizar(@RequestBody Unidade unidade, @PathVariable Integer id){
		
		unidade.setId(id);
		service.atualizar(unidade);
		
		return ResponseEntity.noContent().build();
		
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<Void> deletar(@PathVariable Integer id) {
		
		service.deletePorId(id);
		
		return ResponseEntity.noContent().build();
		
	}

}