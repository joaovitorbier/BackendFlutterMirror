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

import com.fatesg.ads4.projetoMirror.domain.Pessoa;

@SpringBootTest
@RunWith(SpringRunner.class)
@WebAppConfiguration
class PessoaServiceTest {

	@Autowired
	PessoaService service;
	
	@Test
	public void buscarId_test() {
		
		Pessoa objeto = service.buscarId(1);
		assertNotNull(objeto);
	}
	
	@Test
	public void inserir_test() {
		
		Pessoa objeto = new Pessoa("teste@teste.com","123456");
		
		service.inserir(objeto);
		
		List<Pessoa> lista = service.buscarTudo();
		
		for(int i=0; i < lista.size(); i++) {
			
				if(lista.get(i).getEmail() == "teste@teste.com") {
					assertEquals("teste@teste.com",lista.get(i).getEmail());
				}
		}
		
	}
	
	@Test
	public void atualizar_test() {
		
		Pessoa objeto = service.buscarId(1);
		
		objeto.setEmail("test@test.com");
		
		service.atualizar(objeto);
		
		Pessoa teste = service.buscarId(1);
		
		assertEquals("test@test.com",teste.getEmail());
		
	}
	
	//
	@Test
	public void deletar_test() {
		
		Pessoa objeto = new Pessoa("ferdinando@ferdinando.com","123");
		
		service.inserir(objeto);
		
		List<Pessoa> lista = service.buscarTudo();
		
		int tamanhoLista = lista.size();
		
		service.deletePorId(lista.size());
		
		List<Pessoa> listaAfter = service.buscarTudo();
		
		int tamanhoListaAfter = listaAfter.size();
		
		assertFalse(tamanhoLista == tamanhoListaAfter);
		
		
	}

}
