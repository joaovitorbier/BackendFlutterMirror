package com.fatesg.ads4.projetoMirror.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

/*
 *  ESSA CLASSE DEU UM BUG INÊSPERADO PERTO
 *  DA DATA LIMITE, PARA ENTÃO EVITAR PROBLEMAS
 *  MAIORES E PERCEBENDO QUE ELA PODERIA SER SÓ
 *  ALGUMAS COLUNAS A MAIS NA CLASSE FEEDBACK
 *  OS SEGUINTES ATRIBUTOS FORAM MOVIDOS DIRETO
 *  PARA A CLASSE FEEDBACK:
 *  String textoFeedback
 *  String textoReplica
 *  Date dataLimiteReplica;
 *  Date dataAplicacao;
 *  
 *  O PROBLEMA FOI RESOLVIDO DEPOIS DESSA MUDANÇA
 *  MAS DEIXEI A CLASSE AQUI PARA ALGUMA FUTURA
 *  REFATORAÇÃO QUE SEJA CRIADO UMA SOLUÇÃO MELHOR
 *  PARA O PROBLEMA.
 */

@Entity
public class AplicacaoFeedback implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy  = GenerationType.IDENTITY)
	private Integer id;

	//O Feedback
	@OneToOne
	@JoinColumn
	private Feedback feedback;
	
	//Textos
	private String textoFeedback;
	private String textoReplica;
	
	//Datas
	private Date dataLimiteReplica;
	private Date dataAplicacao;
	
	//CONSTRUTORES
	public AplicacaoFeedback() {
		super();
	}
	
	//CONSTRUTOR QUE USA O FEEDBACK INTEIRO
	public AplicacaoFeedback(Feedback feedback, String textoFeedback,
			Date dataLimiteReplica, Date dataAplicacao) {
		super();
		this.id = null;
		this.feedback = feedback;
		this.textoFeedback = textoFeedback;
		this.textoReplica = textoReplica;
		this.dataLimiteReplica = dataLimiteReplica;
		Date agora = new Date();
		this.dataAplicacao = agora;
	}
	
	//CONSTRUTOR QUE USA APENAS O ID DO FEEDBACK
	public AplicacaoFeedback(Integer feedbackId, String textoFeedback, Date dataLimiteReplica, Date dataAplicacao) {
		super();
		this.id = null;
		this.feedback.setId(feedbackId);
		this.textoFeedback = textoFeedback;
		this.dataLimiteReplica = dataLimiteReplica;
		Date agora = new Date();
		this.dataAplicacao = agora;
	}
	
	//GETTERS E SETTERS
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Feedback getFeedback() {
		return feedback;
	}
	public void setFeedback(Feedback feedback) {
		this.feedback = feedback;
	}
	public String getTextoFeedback() {
		return textoFeedback;
	}
	public void setTextoFeedback(String textoFeedback) {
		this.textoFeedback = textoFeedback;
	}
	public String getTextoReplica() {
		return textoReplica;
	}
	public void setTextoReplica(String textoReplica) {
		this.textoReplica = textoReplica;
	}
	public Date getDataLimiteReplica() {
		return dataLimiteReplica;
	}
	public void setDataLimiteReplica(Date dataLimiteReplica) {
		this.dataLimiteReplica = dataLimiteReplica;
	}
	public Date getDataAplicacao() {
		return dataAplicacao;
	}
	public void setDataAplicacao(Date dataAplicacao) {
		this.dataAplicacao = dataAplicacao;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(dataAplicacao, dataLimiteReplica, feedback, id, textoFeedback, textoReplica);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AplicacaoFeedback other = (AplicacaoFeedback) obj;
		return Objects.equals(dataAplicacao, other.dataAplicacao)
				&& Objects.equals(dataLimiteReplica, other.dataLimiteReplica)
				&& Objects.equals(feedback, other.feedback) && Objects.equals(id, other.id)
				&& Objects.equals(textoFeedback, other.textoFeedback)
				&& Objects.equals(textoReplica, other.textoReplica);
	}
	@Override
	public String toString() {
		return "AplicacaoFeedback [id=" + id + ", feedback=" + feedback + ", textoFeedback=" + textoFeedback
				+ ", textoReplica=" + textoReplica + ", dataLimiteReplica=" + dataLimiteReplica + ", dataAplicacao="
				+ dataAplicacao + "]";
	}
	
}
