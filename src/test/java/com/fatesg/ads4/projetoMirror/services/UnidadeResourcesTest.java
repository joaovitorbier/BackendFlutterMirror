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

import com.fatesg.ads4.projetoMirror.domain.Unidade;
import com.fatesg.ads4.projetoMirror.services.UnidadeService;

@SpringBootTest
@RunWith(SpringRunner.class)
@WebAppConfiguration
class UnidadeResourcesTest {

	@Autowired
	UnidadeService service;
	
	@Test
	public void buscarId_test() {
		
		Unidade objeto = service.buscarId(1);
		assertNotNull(objeto);
	}
	
	@Test
	public void inserir_test() {
		
		Unidade objeto = new Unidade("Bananais");
		
		service.inserir(objeto);
		
		List<Unidade> lista = service.buscarTudo();
		
		for(int i=0; i < lista.size(); i++) {
			
				if(lista.get(i).getDescricao() == "Bananais") {
					assertEquals("Bananais",lista.get(i).getDescricao());
				}
		}
		
	}
	
	@Test
	public void atualizar_test() {
		
		Unidade objeto = service.buscarId(1);
		
		objeto.setDescricao("Teste");
		
		service.atualizar(objeto);
		
		Unidade teste = service.buscarId(1);
		
		assertEquals("Teste",teste.getDescricao());
		
	}
	
	//
	@Test
	public void deletar_test() {
		
		Unidade objeto = new Unidade("Ferdinando");
		
		service.inserir(objeto);
		
		List<Unidade> lista = service.buscarTudo();
		
		int tamanhoLista = lista.size();
		
		service.deletePorId(lista.size());
		
		List<Unidade> listaAfter = service.buscarTudo();
		
		int tamanhoListaAfter = listaAfter.size();
		
		assertFalse(tamanhoLista == tamanhoListaAfter);
		
		
	}
}
