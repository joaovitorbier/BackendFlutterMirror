package com.fatesg.ads4.projetoMirror.domain;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class TextosFeedbackDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	//ID do feedback que vai receber o texto
	private Integer idFeedback;
	
	//Texto que vai ser inserido, seja o TextoFeedback ou Replica
	private String texto;
	
	public TextosFeedbackDTO() {
		super();
	}
	
	public TextosFeedbackDTO(String texto) {
		super();
		this.id = null;
		this.idFeedback = null;
		this.texto = texto;
	}
	//getters e setters e etc.
	public Integer getIdFeedback() {
		return idFeedback;
	}
	public void setIdFeedback(Integer idFeedback) {
		this.idFeedback = idFeedback;
	}
	public String getTexto() {
		return texto;
	}
	public void setTexto(String texto) {
		this.texto = texto;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public int hashCode() {
		return Objects.hash(idFeedback, texto);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TextosFeedbackDTO other = (TextosFeedbackDTO) obj;
		return Objects.equals(idFeedback, other.idFeedback) && Objects.equals(texto, other.texto);
	}
}
