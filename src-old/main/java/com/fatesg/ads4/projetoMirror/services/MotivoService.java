package com.fatesg.ads4.projetoMirror.services;

import java.util.List;
import java.util.Optional;

import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.fatesg.ads4.projetoMirror.domain.Feedback;
import com.fatesg.ads4.projetoMirror.domain.Motivo;
import com.fatesg.ads4.projetoMirror.repositories.MotivoRepository;
import com.fatesg.ads4.projetoMirror.services.exceptions.DataIntegrityException;

@Service
public class MotivoService {

	@Autowired
	MotivoRepository repositorio;
	
	//Busca um motivo por Id
	public Motivo buscarId(Integer id) {
		
		Optional<Motivo> motivo = repositorio.findById(id);
		
		return motivo.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! ID: " + id + ", Tipo: " + Motivo.class.getName(), null));
		
	}
	
	//Busca TODOS os motivos
	public List<Motivo> buscarTudo(){
		
		return repositorio.findAll();
		
	}
	
	public List<Feedback> buscarFeedbacks(Integer id){
		
		Optional<Motivo> motivo = repositorio.findById(id);
		
		return motivo.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! ID: " + id + ", Tipo: " + Motivo.class.getName(), null)).getFeedbacks();
		
	}
		
	//Atualiza um motivo
	public Motivo atualizar(Motivo motivo) {
			
			buscarId(motivo.getId());
			return repositorio.save(motivo);
			
	}
	
	//Insere um motivo
	public boolean inserir(Motivo motivo) {
		
		repositorio.save(motivo);
		return true;
		
	}
	
	//Deleta um motivo
	public void deletePorId(Integer id) {
		buscarId(id);
		try {
			repositorio.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possivel excluir um Motivo vinculado a um Feedback!");
		}

	}
	
}
