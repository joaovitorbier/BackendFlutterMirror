package com.fatesg.ads4.projetoMirror.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.fatesg.ads4.projetoMirror.domain.Pessoa;

@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, Integer>{

	@Transactional(readOnly=true)
	Pessoa findByEmail(String email);
	
	Pessoa findByNome(String nome);
	
}
