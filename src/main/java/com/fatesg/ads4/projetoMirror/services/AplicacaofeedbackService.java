package com.fatesg.ads4.projetoMirror.services;

import java.util.List;
import java.util.Optional;

import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fatesg.ads4.projetoMirror.domain.AplicacaoFeedback;
import com.fatesg.ads4.projetoMirror.repositories.AplicacaoFeedbackRepository;

@Service
public class AplicacaofeedbackService {

	@Autowired
	AplicacaoFeedbackRepository repositorio;
	
	//Busca uma aplicação por ID
	public AplicacaoFeedback buscarId(Integer id) {
		
		Optional<AplicacaoFeedback> obj = repositorio.findById(id);
		
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! ID: " + id + ", Tipo: " + AplicacaoFeedback.class.getName(), null));
		
	}
	
	//Lista TODAS as aplicações de feedback
	public List<AplicacaoFeedback> buscarTudo(){
		
		return repositorio.findAll();
		
	}
	
	//REPENSAR NISSO AQUI SOBRE COMO RELACIONAR ISSO
	//Insere uma aplicacao de feedback
	public void inserir(AplicacaoFeedback aplicacao) {
		
		aplicacao.setId(null);
		repositorio.save(aplicacao);
		
	}
	
	//Update
	public AplicacaoFeedback atualizar(AplicacaoFeedback aplicacao) {
			
		buscarId(aplicacao.getId());
		return repositorio.save(aplicacao);
			
	}
		
}