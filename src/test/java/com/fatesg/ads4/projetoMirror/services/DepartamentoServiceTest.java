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

import com.fatesg.ads4.projetoMirror.domain.Departamento;


@SpringBootTest
@RunWith(SpringRunner.class)
@WebAppConfiguration
class DepartamentoServiceTest {

	@Autowired
	DepartamentoService service;
	
	@Test
	public void buscarId_test() {
		
		Departamento objeto = service.buscarId(1);
		assertNotNull(objeto);
	}
	
	@Test
	public void inserir_test() {
		
		Departamento objeto = new Departamento("Teste");
		
		service.inserir(objeto);
		
		List<Departamento> lista = service.buscarTudo();
		
		for(int i=0; i < lista.size(); i++) {
			
				if(lista.get(i).getDescricao() == "Teste") {
					assertEquals("Teste",lista.get(i).getDescricao());
				}
		}
		
	}
	
	@Test
	public void atualizar_test() {
		
		Departamento objeto = service.buscarId(1);
		
		objeto.setDescricao("Teste2");
		
		service.atualizar(objeto);
		
		Departamento teste = service.buscarId(1);
		
		assertEquals("Teste2",teste.getDescricao());
		
	}
	
	//
	@Test
	public void deletar_test() {
		
		Departamento objeto = new Departamento("Ferdinando");
		
		service.inserir(objeto);
		
		List<Departamento> lista = service.buscarTudo();
		
		int tamanhoLista = lista.size();
		
		service.deletePorId(lista.size());
		
		List<Departamento> listaAfter = service.buscarTudo();
		
		int tamanhoListaAfter = listaAfter.size();
		
		assertFalse(tamanhoLista == tamanhoListaAfter);
		
		
	}

}
