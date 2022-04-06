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

import com.fatesg.ads4.projetoMirror.domain.Cargo;
import com.fatesg.ads4.projetoMirror.domain.Pessoa;
import com.fatesg.ads4.projetoMirror.services.CargoService;

@RestController
@RequestMapping(value = "/cargos")
public class CargoResources {

	@Autowired
	CargoService service;
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<Cargo> buscar(@PathVariable Integer id) {
		
		Cargo cargo = service.buscarId(id);
		
		return ResponseEntity.ok().body(cargo);
		
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<Cargo>> buscarTudo(){
		
		List<Cargo> cargos = service.buscarTudo();
		
		return ResponseEntity.ok().body(cargos);
		
	}
	
	@RequestMapping(value="/{id}/pessoas", method = RequestMethod.GET)
	public ResponseEntity<List<Pessoa>> buscarPessoas(@PathVariable Integer id){
		
		Cargo cargo = service.buscarId(id);
		
		List<Pessoa> pessoas = cargo.getPessoas();
		
		return ResponseEntity.ok().body(pessoas);
		
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> inserir(@RequestBody Cargo cargo){
		
		service.inserirCargo(cargo);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(cargo.getId()).toUri();
		
		return ResponseEntity.created(uri).build();
		
	}
	
	@RequestMapping(value="/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> atualizar(@RequestBody Cargo cargo, @PathVariable Integer id){
		
		cargo.setId(id);
		service.atualizar(cargo);
		
		return ResponseEntity.noContent().build();
		
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<Void> deletar(@PathVariable Integer id) {
		
		service.deletePorId(id);
		
		return ResponseEntity.noContent().build();
		
	}
}