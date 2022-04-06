package com.fatesg.ads4.projetoMirror.services;

import java.util.List;
import java.util.Optional;

import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.fatesg.ads4.projetoMirror.domain.Departamento;
import com.fatesg.ads4.projetoMirror.domain.Pessoa;
import com.fatesg.ads4.projetoMirror.repositories.DepartamentoRepository;
import com.fatesg.ads4.projetoMirror.services.exceptions.DataIntegrityException;

@Service
public class DepartamentoService {
	
	@Autowired
	private DepartamentoRepository repositorio;
	
	//Busca um departamento por ID
	public Departamento buscarId(Integer id) {
		
		Optional<Departamento> departamento = repositorio.findById(id);
		
		return departamento.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! ID: " + id + ", Tipo: " + Departamento.class.getName(), null));
		
	}
	
	//Lista todos os departamentos
	public List<Departamento> buscarTudo(){
		
		return repositorio.findAll();
		
	}
	
	//Lista todas as pessoas daquele departamento
	public List<Pessoa> buscarPessoas(Integer id){
		
		Optional<Departamento> departamento = repositorio.findById(id);
		
		return departamento.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! ID: " + id + ", Tipo: " + Departamento.class.getName(), null)).getPessoas();
		
	}
	
	//Edita um departamento
	public Departamento atualizar(Departamento departamento) {
		
		buscarId(departamento.getId());
		return repositorio.save(departamento);
		
	}
	
	//Inserir um departamento
	public void inserir(Departamento departamento) {
		
		repositorio.save(departamento);
		
	}
	
	//Deleta um departamento
	public void deletePorId(Integer id) {
		buscarId(id);
		try {
			repositorio.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possivel excluir um Departamento vinculado a cadastros de usuários!");
		}

	}

}