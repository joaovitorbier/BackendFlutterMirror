package com.fatesg.ads4.projetoMirror.services;

import java.util.List;
import java.util.Optional;

import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fatesg.ads4.projetoMirror.domain.Endereco;
import com.fatesg.ads4.projetoMirror.domain.postmon.Adress;
import com.fatesg.ads4.projetoMirror.repositories.EnderecoRepository;
import com.fatesg.ads4.projetoMirror.services.exceptions.DataIntegrityException;

@Service
public class EnderecoService {

	@Autowired
	private EnderecoRepository repositorio;
	
	
	//BUSCA O ENDERECO POR ID
	public Endereco buscarId(Integer id) {
		
		Optional<Endereco> endereco = repositorio.findById(id);
		return endereco.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Endereco.class.getName(), null));
		
	}
	
	//RETORNA TODOS OS ENDERECOS
	public List<Endereco> buscarTudo() {
		return repositorio.findAll();
	}
	
	//INSERIR UM ENDEREÇO
	public void inserir(Endereco endereco) {
		
		String fullUrl = "https://api.postmon.com.br/v1/cep/" + endereco.getCep();
		
		RestTemplate restTemplate = new RestTemplate();
		
		Adress objetoRetornadoPelaApi = restTemplate.getForObject(fullUrl, Adress.class);
		
		endereco.setCidade(objetoRetornadoPelaApi.getCidade());
		endereco.setEstado(objetoRetornadoPelaApi.getEstado());
		endereco.setLogradouro(objetoRetornadoPelaApi.getLogradouro());
		endereco.setBairro(objetoRetornadoPelaApi.getBairro());
		
		repositorio.save(endereco);
		
	}
	
	//ALTERA UM ENDEREÇO
	public Endereco atualizar(Endereco endereco) {
		
		buscarId(endereco.getId());
		return repositorio.save(endereco);
		
	}
	
	//DELETA UM ENDEREÇO
	public void deletePorId(Integer id) {
		buscarId(id);
		try {
			repositorio.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possivel excluir um Endereço vinculado a cadastros de usuários!");
		}

	}
	
}
