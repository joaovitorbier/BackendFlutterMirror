package com.fatesg.ads4.projetoMirror.services;

import java.util.List;
import java.util.Optional;

import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.fatesg.ads4.projetoMirror.domain.Pessoa;
import com.fatesg.ads4.projetoMirror.domain.Unidade;
import com.fatesg.ads4.projetoMirror.repositories.UnidadeRepository;
import com.fatesg.ads4.projetoMirror.services.exceptions.DataIntegrityException;

@Service
public class UnidadeService {

	@Autowired
	UnidadeRepository repositorio;
	
	//Busca uma unidade por id
	public Unidade buscarId(Integer id) {
		
		Optional<Unidade> unidade = repositorio.findById(id);

		return unidade.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! ID: " + id + ", Tipo: " + Unidade.class.getName(), null));
		
	}
	
	//LISTA TODAS AS UNIDADES
	public List<Unidade> buscarTudo(){
		
		return repositorio.findAll();
		
	}
	
	//BUSCA TODAS AS  PESSOAS NAQUELA UNIDADE
	public List<Pessoa> buscarPessoas(Integer id){
		
		Optional<Unidade> unidade = repositorio.findById(id);
		return unidade.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! ID: " + id + ", Tipo: " + Unidade.class.getName(), null)).getPessoas();
		
	}
	
	//INSERIR UMA UNIDADE
	public void inserir(Unidade unidade) {
		
		repositorio.save(unidade);
		
	}
	
	//update uma unidade
	public Unidade atualizar(Unidade unidade) {
		
		buscarId(unidade.getId());
		return repositorio.save(unidade);
		
	}
	
	//DELETA UMA UNIDADE
	public void deletePorId(Integer id) {
		buscarId(id);
		try {
			repositorio.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possivel excluir uma Unidade vinculada a cadastros de usuários!");
		}

	}
	
}
