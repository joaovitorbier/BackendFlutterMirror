package com.fatesg.ads4.projetoMirror.configs;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.fatesg.ads4.projetoMirror.services.DBServices;

@Configuration
@Profile("test")
public class ConfigTest {
	
	@Autowired
	private DBServices dbService;

	@Bean
	public boolean instanciaBancoDados() throws ParseException {
		dbService.instanciarBaseDados();
		return true;

	}
	
}
