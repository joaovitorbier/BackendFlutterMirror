package com.fatesg.ads4.projetoMirror;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.fatesg.ads4.projetoMirror.domain.Feedback;
import com.fatesg.ads4.projetoMirror.services.CargoService;
import com.fatesg.ads4.projetoMirror.services.DBServices;
import com.fatesg.ads4.projetoMirror.services.FeedbackService;

@SpringBootApplication
public class ProjetoMirrorApplication implements CommandLineRunner {
	
	@Autowired
	private DBServices dbService;
	
	@Autowired
	FeedbackService feedbackService;
	
	@Autowired
	CargoService cargoService;

	public static void main(String[] args) {
		SpringApplication.run(ProjetoMirrorApplication.class, args);
		
	}
	/*
	@Bean
	public boolean instanciaBancoDados() throws ParseException {
		dbService.instanciarBaseDados();
		return true;
		}
	*/
	@Override
	public void run(String... args) throws Exception {
		
		
	}

	
}
