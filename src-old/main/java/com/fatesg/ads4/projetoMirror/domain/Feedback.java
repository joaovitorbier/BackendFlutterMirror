package com.fatesg.ads4.projetoMirror.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import com.fatesg.ads4.projetoMirror.enumeradores.FeedBackStatus;


@Entity
public class Feedback implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	//Pessoas a participarem da avaliação
	@OneToOne
	@JoinColumn
	private Pessoa avaliador;

	@OneToOne
	@JoinColumn
	private Pessoa avaliado;
	
	private String textoFeedback;
	private String textoReplica;
	
	//Datas
	private Date dataLimiteReplica;
	private Date dataAplicacao;
	
	//Datas
	private Date dataCadastro;
	private Date dataAgendamento;
	
	@ManyToOne
	@JoinColumn
	private Motivo motivo;
	
	//FALHOU NA APLICAÇÃO, CORRIGIMOS DE OUTRA FORMA
	/*
	@OneToOne
	@JoinColumn
	private AplicacaoFeedback aplicacaoFeedback;
	*/
	
	private String anotacao;
	private FeedBackStatus status; //OS SEGUINTES STATUS: APLICADO, ATRASADO, 
	
	//CONSTRUTORES
	public Feedback() {
		super();
	}
	
	public Feedback(Pessoa avaliador, Pessoa avaliado,Date dataAgendamento, Motivo motivo,String anotacao) {
		super();
		this.id = null;
		this.avaliador = avaliador;
		this.avaliado = avaliado;
		this.dataAgendamento = dataAgendamento;
		this.motivo = motivo;
		this.anotacao = anotacao;
		
		setStatus(FeedBackStatus.PENDENTE);
		Date agora = new Date();
		this.dataCadastro = agora;
	}
	
	/*
	public Feedback(Integer idAvaliador, Integer idAvaliado,Date dataAgendamento, Integer idMotivo,String anotacao) {
		super();
		this.id = null;
		
		//BUSCANDO AS PESSOAS
		this.avaliador = pessoaService.buscarId(idAvaliador);
		this.avaliado =  pessoaService.buscarId(idAvaliado);
		
		this.dataAgendamento = dataAgendamento;
		
		this.motivo = motivoService.buscarId(idMotivo); 
		
		this.anotacao = anotacao;
		
		setStatus(FeedBackStatus.PENDENTE);
		Date agora = new Date();
		this.dataCadastro = agora;
	}
	*/
	
	//GETTERS E SETTERS
	
	
	
	public Integer getId() {
		return id;
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

	//DEVIDA A REMOÇÃO DA AplicacaoFeedback
	/*
	public AplicacaoFeedback getAplicacaoFeedback() {
		return aplicacaoFeedback;
	}

	public void setAplicacaoFeedback(AplicacaoFeedback aplicacaoFeedback) {
		this.aplicacaoFeedback = aplicacaoFeedback;
	}
	*/
	public void setId(Integer id) {
		this.id = id;
	}
	
	public Pessoa getAvaliador() {
		return avaliador;
	}
	public void setAvaliador(Pessoa avaliador) {
		this.avaliador = avaliador;
	}
	public Pessoa getAvaliado() {
		return avaliado;
	}
	public void setAvaliado(Pessoa avaliado) {
		this.avaliado = avaliado;
	}
	
	public Date getDataCadastro() {
		return dataCadastro;
	}
	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}
	public Date getDataAgendamento() {
		return dataAgendamento;
	}
	public void setDataAgendamento(Date dataAgendamento) {
		this.dataAgendamento = dataAgendamento;
	}
	
	public Motivo getMotivo() {
		return motivo;
	}
	public void setMotivo(Motivo motivo) {
		this.motivo = motivo;
	}
	public String getAnotacao() {
		return anotacao;
	}
	public void setAnotacao(String anotacao) {
		this.anotacao = anotacao;
	}
	public FeedBackStatus getStatus() {
		return status;
	}
	public void setStatus(FeedBackStatus status) {
		this.status = status;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public int hashCode() {
		return Objects.hash(anotacao, avaliado, avaliador, dataAgendamento, dataAplicacao, dataCadastro,
				dataLimiteReplica, id, motivo, status, textoFeedback, textoReplica);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Feedback other = (Feedback) obj;
		return Objects.equals(anotacao, other.anotacao) && Objects.equals(avaliado, other.avaliado)
				&& Objects.equals(avaliador, other.avaliador) && Objects.equals(dataAgendamento, other.dataAgendamento)
				&& Objects.equals(dataAplicacao, other.dataAplicacao)
				&& Objects.equals(dataCadastro, other.dataCadastro)
				&& Objects.equals(dataLimiteReplica, other.dataLimiteReplica) && Objects.equals(id, other.id)
				&& Objects.equals(motivo, other.motivo) && status == other.status
				&& Objects.equals(textoFeedback, other.textoFeedback)
				&& Objects.equals(textoReplica, other.textoReplica);
	}

	@Override
	public String toString() {
		return "Feedback [id=" + id + ", avaliador=" + avaliador + ", avaliado=" + avaliado + ", textoFeedback="
				+ textoFeedback + ", textoReplica=" + textoReplica + ", dataLimiteReplica=" + dataLimiteReplica
				+ ", dataAplicacao=" + dataAplicacao + ", dataCadastro=" + dataCadastro + ", dataAgendamento="
				+ dataAgendamento + ", motivo=" + motivo + ", anotacao=" + anotacao + ", status=" + status + "]";
	}
	
}