package com.fatesg.ads4.projetoMirror.services;

import java.util.Optional;

import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fatesg.ads4.projetoMirror.domain.Login;
import com.fatesg.ads4.projetoMirror.domain.Pessoa;
import com.fatesg.ads4.projetoMirror.repositories.PessoaRepository;

@Service
public class LoginService {

	@Autowired
	PessoaRepository repositorio;
	
	
	public boolean logar(Login login) {
		
		try {
			Pessoa pessoa = repositorio.findByEmail(login.getEmail());
			
			String senhaPessoa = pessoa.getSenha();
			String senhaLogin = login.getSenha();
			
			senhaPessoa.trim();
			senhaLogin.trim();
			
			if(senhaPessoa.equals(senhaLogin)) {
				return true;
			}else{
				return false;
			}
				
		}catch (Exception e) {
			return false;
		}
		
	}
	
}