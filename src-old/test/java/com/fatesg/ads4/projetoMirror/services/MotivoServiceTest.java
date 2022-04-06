package com.fatesg.ads4.projetoMirror.services;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.fatesg.ads4.projetoMirror.domain.Motivo;

class MotivoServiceTest {

	@Autowired
	MotivoService service;
	
	@BeforeEach
	void setUp() throws Exception {
	}

	@Test
	void insertTestSucess() {
		
		Motivo motivo = new Motivo("Atrasado","O funcionário não chegou no horário pelo menos 10 vezes");
		
		Boolean test = service.inserir(motivo);
		
		assertTrue(test.booleanValue() == true);
		
	}

}
