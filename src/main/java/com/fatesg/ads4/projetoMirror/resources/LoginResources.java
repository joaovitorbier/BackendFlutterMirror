package com.fatesg.ads4.projetoMirror.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fatesg.ads4.projetoMirror.domain.Login;
import com.fatesg.ads4.projetoMirror.domain.Pessoa;
import com.fatesg.ads4.projetoMirror.services.LoginService;
import com.fatesg.ads4.projetoMirror.services.PessoaService;

@RestController
@RequestMapping(value = "/login")
public class LoginResources {

	@Autowired
	LoginService service;
	
	@RequestMapping(method = RequestMethod.GET)
	public boolean logar(@RequestBody Login login){
		
		return service.logar(login);
		
	}
	
}