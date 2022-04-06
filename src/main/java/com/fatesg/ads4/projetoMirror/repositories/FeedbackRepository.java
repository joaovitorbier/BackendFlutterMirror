package com.fatesg.ads4.projetoMirror.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fatesg.ads4.projetoMirror.domain.Feedback;
import com.fatesg.ads4.projetoMirror.domain.Pessoa;

@Repository
public interface FeedbackRepository extends JpaRepository<Feedback,Integer>{

	List<Feedback> findByAvaliado(Pessoa pessoa);
	
	List<Feedback> findByAvaliador(Pessoa pessoa);
	
}
