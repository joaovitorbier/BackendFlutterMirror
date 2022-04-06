package com.fatesg.ads4.projetoMirror.services;

import java.util.List;
import java.util.Optional;

import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.fatesg.ads4.projetoMirror.domain.Feedback;
import com.fatesg.ads4.projetoMirror.domain.Pessoa;
import com.fatesg.ads4.projetoMirror.enumeradores.Perfil;
import com.fatesg.ads4.projetoMirror.repositories.PessoaRepository;
import com.fatesg.ads4.projetoMirror.security.UserSS;
import com.fatesg.ads4.projetoMirror.services.exceptions.AuthorizationException;
import com.fatesg.ads4.projetoMirror.services.exceptions.DataIntegrityException;

@Service
public class PessoaService {
	
	@Autowired
	private BCryptPasswordEncoder encoder;
	
	@Autowired
	PessoaRepository repositorio;
	
	//BUSCA UMA PESSOA POR ID
	//ADMIN PODE USAR PARA BUSCAR QUALQUER UM, USER SÓ PODE BUSCAR ELE MESMO
	public Pessoa buscarId(Integer id) {
		
		//SE A PESSOA NÃO FOR ADMIN SÓ PERMITE QUE CLIENTE BUSQUE ELE MESMO.
		UserSS user = UserService.authenticated();
		if(user == null || !user.hasRole(Perfil.ADMIN) && !id.equals(user.getId())) {
			
			throw new AuthorizationException("Acesso negado");
			
		}
		
		Optional<Pessoa> pessoa = repositorio.findById(id);
		
		return pessoa.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! ID: " + id + ", Tipo: " + Pessoa.class.getName(), null));
		
	}
	
	//LISTA TODAS AS PESSOAS
	//SÓ ADMIN PODE USAR
	public List<Pessoa> buscarTudo(){
		
		UserSS user = UserService.authenticated();
		
		if(user == null || !user.hasRole(Perfil.ADMIN) && !user.hasRole(Perfil.AVALIADOR)) {
			
			throw new AuthorizationException("Acesso negado");
			
		}
		return repositorio.findAll();
		
	}
	
	//LISTA TODOS OS FEEDBACKS DAQUELA PESSOA
	public List<Feedback> listaFeedbacks(Integer id){
		
		Optional<Pessoa> pessoa = repositorio.findById(id);
		
		return pessoa.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! ID: " + id + ", Tipo: " + Pessoa.class.getName(), null)).getFeedbacks();
		
	}
	
	public Pessoa buscarPorEmail(String email) {
		
		//SE A PESSOA NÃO FOR ADMIN SÓ PERMITE QUE CLIENTE BUSQUE ELE MESMO.
	
		return repositorio.findByEmail(email);
		
		//return pessoa.orElseThrow(() -> new ObjectNotFoundException(
		//		"Objeto não encontrado! ID: " + id + ", Tipo: " + Pessoa.class.getName(), null));
		
	}
	
	//INSERIR UMA PESSOA
	public void inserir(Pessoa pessoa) {
		
		String senha = pessoa.getSenha(); //PEGANDO A SENHA PARA CRIPTOGRAFAR
		
		senha = encoder.encode(senha); //CRIPTOGRAFANDO A SENHA
		
		pessoa.setSenha(senha); //COLOCANDO A PESSOA COM A SENHA CRIPTAGRAFADA PARA SER SALVA NO BANCO
		
		repositorio.save(pessoa);
		
	}
	
	//ATUALIZA UMA PESSOA
	public Pessoa atualizar(Pessoa pessoa) {
		
		buscarId(pessoa.getId());
		return repositorio.save(pessoa);
		
	}
	
	
	//DELETA UMA PESSOA
	public void deletePorId(Integer id) {
		buscarId(id);
		try {
			repositorio.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possivel excluir um usuário vinculado a um Feedback!");
		}

	}
	
}