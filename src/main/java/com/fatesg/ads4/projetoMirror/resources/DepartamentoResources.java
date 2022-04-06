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

import com.fatesg.ads4.projetoMirror.domain.Departamento;
import com.fatesg.ads4.projetoMirror.domain.Pessoa;
import com.fatesg.ads4.projetoMirror.services.DepartamentoService;

@RestController
@RequestMapping(value="/departamentos")
public class DepartamentoResources {

	@Autowired
	private DepartamentoService service;
	
	@RequestMapping(value = "/{id}", method=RequestMethod.GET)
	public ResponseEntity<Departamento> buscarPorId(@PathVariable Integer id){
		
		Departamento departamento = service.buscarId(id);
		
		return ResponseEntity.ok().body(departamento);
		
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<Departamento>> buscarTudo() {
		
		List<Departamento> departamentos = service.buscarTudo();
		
		return ResponseEntity.ok().body(departamentos);
		
	}
	
	@RequestMapping(value="/{id}/pessoas", method = RequestMethod.GET)
	public ResponseEntity<List<Pessoa>> buscarPessoas(@PathVariable Integer id){
		
		Departamento departamento = service.buscarId(id);
		
		List<Pessoa> pessoas = departamento.getPessoas();
		
		return ResponseEntity.ok().body(pessoas);
		
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> inserir(@RequestBody Departamento departamento){
		
		service.inserir(departamento);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(departamento.getId()).toUri();
		
		return ResponseEntity.created(uri).build();
		
	}
	
	@RequestMapping(value="/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> atualizar(@RequestBody Departamento departamento, @PathVariable Integer id){
		
		departamento.setId(id);
		service.atualizar(departamento);
		
		return ResponseEntity.noContent().build();
		
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<Void> deletar(@PathVariable Integer id) {
		
		service.deletePorId(id);
		
		return ResponseEntity.noContent().build();
		
	}
	
}
