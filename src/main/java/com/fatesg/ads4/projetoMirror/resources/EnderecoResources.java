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

import com.fatesg.ads4.projetoMirror.domain.Endereco;
import com.fatesg.ads4.projetoMirror.services.EnderecoService;

@RestController
@RequestMapping(value="/enderecos")
public class EnderecoResources {

	@Autowired
	 EnderecoService service;
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<Endereco> buscar(@PathVariable Integer id) {
		
		Endereco endereco = service.buscarId(id);
		
		return ResponseEntity.ok().body(endereco);
		
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<Endereco>> buscarTudo(){
		
		List<Endereco> enderecos = service.buscarTudo();
		
		return ResponseEntity.ok().body(enderecos);
		
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> inserir(@RequestBody Endereco endereco){
		
		service.inserir(endereco);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(endereco.getId()).toUri();
		
		return ResponseEntity.created(uri).build();
		
	}
	
	@RequestMapping(value="/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> atualizar(@RequestBody Endereco endereco, @PathVariable Integer id){
		
		endereco.setId(id);
		service.atualizar(endereco);
		
		return ResponseEntity.noContent().build();
		
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<Void> deletar(@PathVariable Integer id) {
		
		service.deletePorId(id);
		
		return ResponseEntity.noContent().build();
		
	}
	
}
