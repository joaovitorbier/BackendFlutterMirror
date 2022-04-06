package com.fatesg.ads4.projetoMirror.services;

import java.util.List;
import java.util.Optional;

import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.fatesg.ads4.projetoMirror.domain.Cargo;
import com.fatesg.ads4.projetoMirror.domain.Pessoa;
import com.fatesg.ads4.projetoMirror.repositories.CargoRepository;
import com.fatesg.ads4.projetoMirror.services.exceptions.DataIntegrityException;

@Service
public class CargoService {

	@Autowired
	CargoRepository repositorio;
	
	//BUSCA UM CARGO POR ID
	public Cargo buscarId(Integer id) {
		
		Optional<Cargo> cargo = repositorio.findById(id);
		return cargo.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! ID: " + id + ", Tipo: " + Cargo.class.getName(), null));
		
	}
	
	//LISTA TODOS OS CARGOS
	public List<Cargo> buscarTudo(){
		
		return repositorio.findAll();
		
	}
	
	//INSERIR CARGO
	public void inserirCargo(Cargo cargo) {
		cargo.setId(null);
		repositorio.save(cargo);
		
	}
	
	//UPDATE CARGO
	public Cargo atualizar(Cargo cargo) {
		
		buscarId(cargo.getId());
		return repositorio.save(cargo);
		
	}
	
	
	//RETORNA TODAS AS PESSOAS NAQUELE CARGO
	public List<Pessoa> buscarPessoasCargo(Integer id){
		
		Optional<Cargo> cargo = repositorio.findById(id);
		return cargo.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! ID: " + id + ", Tipo: " + Cargo.class.getName(), null)).getPessoas();
		
	}
	
	//DELETA UM CARGO
	public void deletePorId(Integer id) {
		buscarId(id);
		try {
			repositorio.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possivel excluir um cargo vinculado a cadastros de usuários!");
		}

	}
	
}
