package com.fatesg.ads4.projetoMirror.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.fatesg.ads4.projetoMirror.domain.Motivo;

@SpringBootTest
@RunWith(SpringRunner.class)
@WebAppConfiguration
class MotivoServiceTest {

	@Autowired
	MotivoService service;
	
	@Test
	public void buscarId_test() {
		
		Motivo objeto = service.buscarId(1);
		assertNotNull(objeto);
	}
	
	
	
	@Test
	public void inserir_test() {
		
		Motivo objeto = new Motivo("Teste","Resumo");
		
		service.inserir(objeto);
		
		List<Motivo> lista = service.buscarTudo();
		
		for(int i=0; i < lista.size(); i++) {
			
				if(lista.get(i).getDescricao() == "Teste") {
					assertEquals("Teste",lista.get(i).getDescricao());
				}
		}
		
	}
	
	@Test
	public void atualizar_test() {
		
		Motivo objeto = service.buscarId(1);
		
		objeto.setDescricao("Teste2");
		
		service.atualizar(objeto);
		
		Motivo teste = service.buscarId(1);
		
		assertEquals("Teste2",teste.getDescricao());
		
	}
	
	//
	@Test
	public void deletar_test() {
		
		Motivo objeto = new Motivo("Ferdinando","Sim");
		
		service.inserir(objeto);
		
		List<Motivo> lista = service.buscarTudo();
		
		int tamanhoLista = lista.size();
		
		service.deletePorId(lista.size());
		
		List<Motivo> listaAfter = service.buscarTudo();
		
		int tamanhoListaAfter = listaAfter.size();
		
		assertFalse(tamanhoLista == tamanhoListaAfter);
		
		
	}

}
