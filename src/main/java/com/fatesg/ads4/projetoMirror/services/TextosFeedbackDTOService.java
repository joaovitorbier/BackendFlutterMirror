package com.fatesg.ads4.projetoMirror.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fatesg.ads4.projetoMirror.domain.Feedback;
import com.fatesg.ads4.projetoMirror.domain.TextosFeedbackDTO;
import com.fatesg.ads4.projetoMirror.repositories.FeedbackRepository;

@Service
public class TextosFeedbackDTOService {
	
	@Autowired
	FeedbackRepository repository;
	
	public void InserirTexto(TextosFeedbackDTO textos, Integer id){
		
		Feedback feedback = repository.getById(id);
		
		feedback.setTextoFeedback(textos.getTexto());
		
		repository.save(feedback);
		
	}
	
	public void InserirReplica(TextosFeedbackDTO textos, Integer id){
		
		Feedback feedback = repository.getById(id);
		
		feedback.setTextoReplica(textos.getTexto());
		
		repository.save(feedback);
		
	}

}
