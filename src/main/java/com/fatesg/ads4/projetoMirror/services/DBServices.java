package com.fatesg.ads4.projetoMirror.services;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fatesg.ads4.projetoMirror.domain.Cargo;
import com.fatesg.ads4.projetoMirror.domain.Departamento;
import com.fatesg.ads4.projetoMirror.domain.Endereco;
import com.fatesg.ads4.projetoMirror.domain.Feedback;
import com.fatesg.ads4.projetoMirror.domain.Motivo;
import com.fatesg.ads4.projetoMirror.domain.Pessoa;
import com.fatesg.ads4.projetoMirror.domain.Unidade;
import com.fatesg.ads4.projetoMirror.enumeradores.FeedBackStatus;
import com.fatesg.ads4.projetoMirror.enumeradores.Perfil;

@Service
public class DBServices {

	@Autowired
	PessoaService pessoaService;
	
	@Autowired
	CargoService cargoService;
	
	@Autowired
	DepartamentoService departamentoService;
	
	@Autowired
	MotivoService motivoService;
	
	@Autowired
	UnidadeService unidadeService;
	
	@Autowired
	EnderecoService enderecoService;
	
	@Autowired
	FeedbackService feedbackService;
	
	public void instanciarBaseDados() {
		
		//INSTANCIANDO USUÁRIOS BÁSICOS
		

			
			//INSTANCIANDO UM USER
			//Pessoa user = new Pessoa("user@user","user");
			//pessoaService.inserir(user);
		
		//INSTANCIANDO CARGO
			Cargo cargo = new Cargo("Diretor");
			cargoService.inserirCargo(cargo);
			
			Cargo cargo2 = new Cargo("Recursos Humanos");
			cargoService.inserirCargo(cargo2);
			
		//INSTANCIANDO DEPARTAMENTOS
			Departamento departamento = new Departamento("Vendas");
			departamentoService.inserir(departamento);
			
			Departamento departamento2 = new Departamento("Desenvolvimento");
			departamentoService.inserir(departamento2);
			
		//INSTANCIANDO MOTIVOS
			Motivo motivo = new Motivo("Atraso","O funcionário se atrasa demais");
			motivoService.inserir(motivo);
			
			Motivo motivo2 = new Motivo("Abuso","O funcionário pratica abuso");
			motivoService.inserir(motivo2);
			
		//INSTANCIANDO UNIDADES
			Unidade unidade = new Unidade("Setor Perim");
			unidadeService.inserir(unidade);
			
			Unidade unidade2 = new Unidade("Italo Bolonhesa");
			unidadeService.inserir(unidade2);
		
		//INSTANCIANDO ENDERECOS
			Endereco endereco = new Endereco("74435060");
			enderecoService.inserir(endereco);
			
			Endereco endereco2 = new Endereco("70083900");
			enderecoService.inserir(endereco2);
			
			
			
		//INSTANCIAR UMA PESSOA INTEIRA
			Date date = new Date(); //SÓ UMA DATA DE TESTE
			
			//INSTANCIANDO UM ADMIN
			Pessoa admin = new Pessoa("admin", "admin@admin", "123456","70164574190","3271-7804","Esse rapaz é bem dedicado",date,endereco,departamento,cargo,unidade);
			admin.addPerfil(Perfil.ADMIN);
			pessoaService.inserir(admin);
			
			//INSTANCIANDO UM AVALIADOR
			Pessoa avaliador = new Pessoa("avaliador", "avaliador@avaliador", "123456","70164574190","3271-7804","Esse rapaz é bem dedicado",date,endereco,departamento,cargo,unidade);
			avaliador.addPerfil(Perfil.AVALIADOR);
			pessoaService.inserir(avaliador);
			
			
			Pessoa pessoa = new Pessoa("Cleberson","clebin@hotmail.com","123456","70164574190","3271-7804","Esse rapaz é bem dedicado",date,endereco,departamento,cargo,unidade);
			avaliador.addPerfil(Perfil.CLIENTE);
			pessoaService.inserir(pessoa);
			
			Pessoa pessoa2 = new Pessoa("Fátima","fafa@gmail.com","123456","123456789123","98310-2020","Teste", date,endereco,departamento,cargo,unidade);
			avaliador.addPerfil(Perfil.AVALIADOR);
			pessoaService.inserir(pessoa2);
			
		//CRIANDO UM FEEDBACK PARA SER APLICADO
			Feedback feedback = new Feedback(pessoa,pessoa2, date, motivo, "Fazer urgente");
			feedback.setStatus(FeedBackStatus.PENDENTE);
			feedbackService.inserir(feedback);
			
			Feedback feedback2 = new Feedback(pessoa2,pessoa, date, motivo, "Fazer urgente");
			feedback2.setStatus(FeedBackStatus.APLICADO);
			feedbackService.inserir(feedback2);
			
	}

}